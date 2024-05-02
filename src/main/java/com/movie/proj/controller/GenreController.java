package com.movie.proj.controller;

import com.movie.proj.api.GenreApiMethods;
import com.movie.proj.entities.Genre;
import com.movie.proj.services.GenreService;
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

    private final GenreService genreService;

    @GetMapping
    public List<Genre> getGenres(){
        return genreService.getAllGenres();
    }
}
