package com.movie.proj.services;

import com.movie.proj.api.MovieApiMethods;
import com.movie.proj.dao.MovieDao;
import com.movie.proj.dao.MovieDaoImpl;
import com.movie.proj.entities.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieDaoImpl movieDao;

    public void saveMovies() throws ExecutionException, InterruptedException {

            try {
                for (int i = 1; i < 85; i++) {
                    List<Movie> movies = MovieApiMethods.fetchMoviesAsync(String.valueOf(i)).get();
                    movieDao.addAll(movies);
                }
            }
            catch (InterruptedException e){
                throw new InterruptedException("problem with getting movies");
            }
    }

    public List<String> getAllImdbIds() {
        List<String> imdbIds = new ArrayList<>();
        Map<String, String> allMovies = movieDao.findAllMovies();
        for (String imdbId : allMovies.keySet()) {
            imdbIds.add(imdbId);
        }
        return imdbIds;
    }


    public Map<String, String> getAllMovies(){
        return movieDao.findAllMovies();
    }
}
