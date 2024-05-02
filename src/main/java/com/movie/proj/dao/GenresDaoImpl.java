package com.movie.proj.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.proj.entities.Genre;
import com.movie.proj.exceptions.SerializeException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class GenresDaoImpl implements GenresDao {

    private static final String KEY = "Genre";

    private final JedisPool jedisPool;

    public GenresDaoImpl() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(100);

        jedisPool = new JedisPool(poolConfig, "localhost", 6379);
    }

    @Override
    public void add(Genre genre) {
        try (Jedis jedis = jedisPool.getResource()) {
            jedis.hset(KEY, genre.getId(), serializeGenre(genre));
        } catch (JedisException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAll(List<Genre> genres) {
        try (Jedis jedis = jedisPool.getResource()) {
            genres.forEach(genre -> jedis.hset(KEY, genre.getId(), serializeGenre(genre)));
        } catch (JedisException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String serializeGenre(Genre genre) {
        log.info("Serializing genre");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(genre);
        } catch (IOException e) {
            throw new SerializeException(e);
        }
    }

    @Override
    public Genre deserializeGenre(String genreString) {
        log.info("Deserializing genre");
        JSONObject jsonObject = new JSONObject(genreString);
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        return new Genre(id, name);
    }

    @Override
    public Set<Genre> getAllGenres(){
        Set<Genre> genres = new HashSet<>();
        try (Jedis jedis = jedisPool.getResource()) {
            Map<String, String> genreMap = jedis.hgetAll(KEY);
            for (Map.Entry<String, String> entry : genreMap.entrySet()) {
                Genre genre = deserializeGenre(entry.getValue());
                genres.add(genre);
            }
        } catch (JedisException e) {
            e.printStackTrace();
        }
        return genres;
    }
}
