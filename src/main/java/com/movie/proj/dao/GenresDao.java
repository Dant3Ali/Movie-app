package com.movie.proj.dao;

import com.movie.proj.entities.Genre;

import java.util.List;
import java.util.Set;

public interface GenresDao {

    public void add(Genre genre);
    public void addAll(List<Genre> genres);
    public String serializeGenre(Genre genre);
    public Genre deserializeGenre(String genre);
    public Set<Genre> getAllGenres();
}
