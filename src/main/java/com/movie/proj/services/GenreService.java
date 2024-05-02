package com.movie.proj.services;

import com.movie.proj.api.GenreApiMethods;
import com.movie.proj.dao.GenresDao;
import com.movie.proj.entities.Genre;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreService {

    private final GenresDao genresDao;

    public void saveGenres() {
        log.info("Starting saving all genres");
        try {
            List<Genre> genres = GenreApiMethods.fetchGenresAsync().get();
            genresDao.addAll(genres);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("Invoked function was interrupted: " + e.getMessage());
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                log.warn("I/O error: " + cause.getMessage());
            } else {
                log.warn("Error during the async operation: " + e.getMessage());
            }
        }
    }

    public List<Genre> getAllGenres(){
        log.info("Getting all genres");
        return genresDao.getAllGenres().stream().toList();
    }
}
