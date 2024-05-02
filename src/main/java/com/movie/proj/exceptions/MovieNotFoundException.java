package com.movie.proj.exceptions;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MovieNotFoundException(Throwable cause) {
        super(cause);
    }
}
