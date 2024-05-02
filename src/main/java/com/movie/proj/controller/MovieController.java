package com.movie.proj.controller;

import com.movie.proj.api.MovieApiMethods;
import com.movie.proj.entities.Movie;
import com.movie.proj.exceptions.MovieNotFoundException;
import com.movie.proj.exceptions.MovieNotFoundHandler;
import com.movie.proj.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
@MovieNotFoundHandler
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        try {
            return movieService.getAllMovies();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch all movies");
        }
    }

    @GetMapping("/{imdb}")
    public Movie getMovieByImdb(@PathVariable String imdb){
        try {
            return movieService.getMovieByImdb(imdb);
        } catch (MovieNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch movie with IMDb: " + imdb);
        }
    }

    @GetMapping("/getMap")
    public Map<String, String> getAllMoviesMap(){
        try {
            return movieService.getAllMoviesMap();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch all movies as map");
        }
    }
}
