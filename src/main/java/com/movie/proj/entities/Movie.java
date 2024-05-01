package com.movie.proj.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    private String title;
    private String imdbId;
    private double imdbRating;
    private int releasedYear;
    private String synopsis;
    private List<String> genres;
    private List<String> imageUrl;
}
