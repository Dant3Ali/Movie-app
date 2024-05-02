package com.movie.proj.api;

import com.movie.proj.entities.Genre;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class GenreApiMethods {

    private static final String API_URL = "https://ott-details.p.rapidapi.com/getParams?param=genre";
    @Value(value = "${api.key}")
    private static final String API_KEY = "";
    private static final String API_HOST = "ott-details.p.rapidapi.com";


    public static CompletableFuture<List<Genre>> fetchGenresAsync() {
        AsyncHttpClient client = new DefaultAsyncHttpClient();

        return client.prepare("GET", API_URL)
                .setHeader("X-RapidAPI-Key", API_KEY)
                .setHeader("X-RapidAPI-Host", API_HOST)
                .execute()
                .toCompletableFuture()
                .thenApply(response -> {
                    List<Genre> genres = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.getResponseBody());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String name = jsonArray.getString(i);
                        genres.add(new Genre(String.valueOf(i), name));
                    }
                    return genres;
                })
                .whenComplete((result, exception) -> {
                    try {
                        client.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
