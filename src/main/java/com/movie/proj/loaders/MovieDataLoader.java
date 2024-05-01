package com.movie.proj.loaders;

import com.movie.proj.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MovieDataLoader implements ApplicationRunner {

    private final MovieService movieService;

    @Autowired
    public MovieDataLoader(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        movieService.saveMovies();
    }
}
