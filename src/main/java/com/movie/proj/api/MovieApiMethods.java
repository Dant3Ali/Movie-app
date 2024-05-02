package com.movie.proj.api;

import com.movie.proj.entities.Movie;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MovieApiMethods {

    private static final String API_URL = "https://ott-details.p.rapidapi.com/advancedsearch";
    private static final String API_KEY = "f91f5a0a04msh416e8d9e211ab55p15a7e0jsneb415a2840c9";
    private static final String API_HOST = "ott-details.p.rapidapi.com";

    public static CompletableFuture<List<Movie>> fetchMoviesAsync(String page) {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        CompletableFuture<List<Movie>> future = new CompletableFuture<>();

//        CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
//            client.prepare("GET", API_URL)
//                    .addQueryParam("start_year", "1970")
//                    .addQueryParam("end_year", "2022")
//                    .addQueryParam("min_imdb", "6")
//                    .addQueryParam("max_imdb", "10")
//                    .addQueryParam("language", "english")
//                    .addQueryParam("type", "movie")
//                    .addQueryParam("sort", "latest")
//                    .addQueryParam("page", page)
//                    .setHeader("X-RapidAPI-Key", API_KEY)
//                    .setHeader("X-RapidAPI-Host", API_HOST)
//                    .execute()
//                    .toCompletableFuture()
//                    .thenApply(response -> {
//                        List<Movie> movies = new ArrayList<>();
//                        try {
//                            log.info("Starting json decomposition");
//                            JSONObject jsonResponse = new JSONObject(response.getResponseBody());
//                            JSONArray resultsArray = jsonResponse.getJSONArray("results");
//                            for (int i = 0; i < resultsArray.length(); i++) {
//                                JSONObject movieJson = resultsArray.getJSONObject(i);
//                                String title = movieJson.getString("title");
//                                String imdbId = movieJson.getString("imdbid");
//                                double imdbRating = movieJson.getDouble("imdbrating");
//                                int releasedYear = movieJson.getInt("released");
//                                String synopsis = movieJson.getString("synopsis");
//                                List<String> genres = new ArrayList<>();
//                                JSONArray genresArray = movieJson.getJSONArray("genre");
//                                for (int j = 0; j < genresArray.length(); j++) {
//                                    genres.add(genresArray.getString(j));
//                                }
//                                List<String> imageUrl = new ArrayList<>();
//                                JSONArray imageUrlArray = movieJson.getJSONArray("imageurl");
//                                for (int k = 0; k < imageUrlArray.length(); k++) {
//                                    imageUrl.add(imageUrlArray.getString(k));
//                                }
//                                movies.add(new Movie(title, imdbId, imdbRating, releasedYear, synopsis, genres, imageUrl));
//                            }
//                            future.complete(movies);
//                        } catch (JSONException e) {
//                            future.completeExceptionally(e);
//                        }
//                        return null;
//                    })
//                    .whenComplete((result, exception) -> {
//                        try {
//                            client.close();
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    });
//        });

        return future;
    }
}

