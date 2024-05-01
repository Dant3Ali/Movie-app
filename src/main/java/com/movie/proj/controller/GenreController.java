package com.movie.proj.controller;

import com.movie.proj.api.GenreApiMethods;
import com.movie.proj.models.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreApiMethods genreApiMethods;

//    @GetMapping
//    public List<Genre> getGenres() throws ExecutionException, InterruptedException {
//        return genreApiMethods.fetchGenresAsync().get();
//    }
}
