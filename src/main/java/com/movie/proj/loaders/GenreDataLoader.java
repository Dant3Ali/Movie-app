package com.movie.proj.loaders;

import com.movie.proj.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreDataLoader implements ApplicationRunner {

    private final GenreService genreService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        genreService.saveGenres();
    }
}
