package com.movie.proj.dao;

import com.movie.proj.entities.Movie;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
@Transactional
public class MovieDaoImpl implements MovieDao {
    private static final String KEY = "Movie";

    private final JedisPool jedisPool;

    public MovieDaoImpl() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);

        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    public void add(final Movie movie) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(KEY, movie.getImdbId(), serialize(movie));
        }
    }

    public void addAll(final List<Movie> movies) {
        try (Jedis jedis = jedisPool.getResource()) {
            for (Movie m : movies){
                jedis.hset(KEY, m.getImdbId(), serialize(m));
            }
        }
    }

    public void delete(final String imdbId) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel(KEY, imdbId);
        }
    }

    public Movie findMovie(final String imdbId) {
        try (Jedis jedis = jedisPool.getResource()) {
            String movieData = jedis.hget(KEY, imdbId);
            if (movieData != null) {
                return deserialize(movieData);
            } else {
                return null;
            }
        }
    }

    public Map<String, String> findAllMovies() {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(KEY);
        }
    }

    private String serialize(Movie movie) {
        return movie.getTitle() + "|" +
                movie.getImdbId() + "|" +
                movie.getImdbRating() + "|" +
                movie.getReleasedYear() + "|" +
                movie.getSynopsis() + "|" +
                String.join(",", movie.getGenres()) + "|" +
                String.join(",", movie.getImageUrl());
    }

    private Movie deserialize(String movieData) {
        List<String> parts = new ArrayList<>();
        Matcher m = Pattern.compile("\"([^\"]*)\"|([^|]+)").matcher(movieData);
        while (m.find()) {
            String part = m.group(1) != null ? m.group(1) : m.group(2);
            parts.add(part);
        }

        Movie movie = new Movie();
        movie.setTitle(parts.get(0));
        movie.setImdbId(parts.get(1));
        movie.setImdbRating(Double.parseDouble(parts.get(2)));
        movie.setReleasedYear(Integer.parseInt(parts.get(3)));
        movie.setSynopsis(parts.get(4));
        movie.setGenres(List.of(parts.get(5).split(",")));
        movie.setImageUrl(List.of(parts.get(6).split(",")));
        return movie;
    }
}
