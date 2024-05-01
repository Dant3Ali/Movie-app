package com.movie.proj.api;

import com.movie.proj.models.Genre;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class GenreApiMethods {

//    public static CompletableFuture<List<Genre>> fetchGenresAsync() {
//        AsyncHttpClient client = new DefaultAsyncHttpClient();
//
//        return client.prepare("GET", "https://moviesminidatabase.p.rapidapi.com/genres/")
//                .setHeader("X-RapidAPI-Key", "6b84a38777mshda618d1086b4327p15bd48jsn0a5eca414e7d")
//                .setHeader("X-RapidAPI-Host", "moviesminidatabase.p.rapidapi.com")
//                .execute()
//                .toCompletableFuture()
//                .thenApply(response -> {
//                    List<Genre> genres = new ArrayList<>();
//                    // Parse the response and populate genres list
//                    // Assuming response format is JSON
//                    JSONArray jsonArray = new JSONArray(response.getResponseBody());
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject genreJson = jsonArray.getJSONObject(i);
//                        String id = genreJson.getString("id");
//                        String name = genreJson.getString("name");
//                        genres.add(new Genre(id, name));
//                    }
//                    return genres;
//                })
//                .whenComplete((result, exception) -> {
//                    try {
//                        client.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                });
//    }
}
