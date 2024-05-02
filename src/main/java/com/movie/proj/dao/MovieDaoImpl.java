package com.movie.proj.dao;

import com.movie.proj.entities.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
@Slf4j
public class MovieDaoImpl implements MovieDao {
    private static final String KEY = "Movie";

    private final JedisPool jedisPool;

    public MovieDaoImpl() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);

        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }


    @Override
    public void add(final Movie movie) {
        log.info("Adding movie: {}", movie.getTitle());

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(KEY, movie.getImdbId(), serializeMovie(movie));
        }
    }

    @Override
    public void addAll(final List<Movie> movies) {
        log.info("Start adding a list og movies ...");
        try (Jedis jedis = jedisPool.getResource()) {
            for (Movie m : movies){
                jedis.hset(KEY, m.getImdbId(), serializeMovie(m));
            }
        }
        log.info("All movies was added");
    }

    @Override
    public void delete(final String imdbId) {
        log.info("Deleting movie by imdbID: {}", imdbId);

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hdel(KEY, imdbId);
        }

        log.info("Deletion completed");
    }

    @Override
    public Movie findMovie(final String imdbId) {
        log.info("Search for movie by imdbID: {}", imdbId);

        try (Jedis jedis = jedisPool.getResource()) {
            String movieData = jedis.hget(KEY, imdbId);
            if (movieData != null) {
                return deserializeMovie(movieData);
            } else {
                log.warn("Movie not found with imdbID: {}", imdbId);
                return null;
            }
        }
    }

    @Override
    public Map<String, String> findAllMovies() {
        log.info("Searching for all movies");
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.hgetAll(KEY);
        }
    }

    @Override
    public String serializeMovie(Movie movie) {
        log.info("Serializing movie: {}", movie.getTitle());
        return movie.getTitle() + "|" +
                movie.getImdbId() + "|" +
                movie.getImdbRating() + "|" +
                movie.getReleasedYear() + "|" +
                movie.getSynopsis() + "|" +
                String.join(",", movie.getGenres()) + "|" +
                String.join(",", movie.getImageUrl());
    }

    @Override
    public Movie deserializeMovie(String serializedMovie) {
        log.info("Deserializing movie");
        String[] parts = serializedMovie.split("\\|");
        log.info("Parts.length is : {}", parts.length);

        String title = parts[0];
        String imdbId = parts[1];
        double imdbRating = Double.parseDouble(parts[2]);
        int releasedYear = Integer.parseInt(parts[3]);
        String synopsis = parts[4];
        String[] genres = parts[5].split(",");

        String[] imageUrl = null;
        if (parts.length == 7){
            imageUrl = parts[6].split(",");
            return new Movie(title, imdbId, imdbRating, releasedYear, synopsis, Arrays.stream(genres).toList(), Arrays.stream(imageUrl).toList());
        }

        return new Movie(title, imdbId, imdbRating, releasedYear, synopsis, Arrays.stream(genres).toList(), null);
    }

}
