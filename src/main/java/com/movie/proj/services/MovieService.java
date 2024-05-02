package com.movie.proj.services;

import com.movie.proj.api.MovieApiMethods;
import com.movie.proj.dao.MovieDaoImpl;
import com.movie.proj.entities.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

    private final MovieDaoImpl movieDao;

    public void saveMovies() throws ExecutionException, InterruptedException {
            log.info("Saving all movies");
            try {
                for (int i = 2; i < 85; i++) {
                    List<Movie> movies = MovieApiMethods.fetchMoviesAsync(String.valueOf(i)).get();
                    movieDao.addAll(movies);
                }
            }
            catch (InterruptedException e){
                throw new InterruptedException("problem with getting movies");
            }
    }

    public Movie getMovieByImdb(String imdb){
        return movieDao.findMovie(imdb);
    }

    public List<String> getAllImdbIds() {
        log.info("Start getting all imdbID's");
        List<String> imdbIds = new ArrayList<>();
        Map<String, String> allMovies = movieDao.findAllMovies();
        for (String imdbId : allMovies.keySet()) {
            imdbIds.add(imdbId);
        }

        return imdbIds;
    }


    public List<Movie> getAllMovies(){
        log.info("Getting all available movies");
        List<Movie> movies = new ArrayList<>();
        for (String m : getAllImdbIds()){
            movies.add(getMovieByImdb(m));
        }

        return movies;
    }

    public Map<String, String> getAllMoviesMap(){
        log.info("Getting all available movies as map");
        return movieDao.findAllMovies();
    }
}
