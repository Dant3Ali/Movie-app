package com.movie.proj.dao;

import com.movie.proj.entities.Movie;

import java.util.List;
import java.util.Map;

public interface MovieDao {

    public void add(Movie movie);
    public void addAll(List<Movie> movies);
    public String serializeMovie(Movie movie);
    public Movie findMovie(final String imdbId);
    public void delete(final String imdbId);
    public Map<String, String> findAllMovies();
    public Movie deserializeMovie(String serializedMovie);
}
