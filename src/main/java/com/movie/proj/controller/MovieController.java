package com.movie.proj.controller;

import com.movie.proj.api.MovieApiMethods;
import com.movie.proj.entities.Movie;
import com.movie.proj.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public Map<String, String> getAllMovies() throws ExecutionException, InterruptedException {
        return movieService.getAllMovies();
    }

}
