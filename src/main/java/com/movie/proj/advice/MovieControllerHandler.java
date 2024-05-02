package com.movie.proj.advice;

import com.movie.proj.exceptions.MovieNotFoundException;
import com.movie.proj.exceptions.MovieNotFoundHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = MovieNotFoundHandler.class)
public class MovieControllerHandler {


    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
