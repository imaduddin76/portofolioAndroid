package com.limaefdua.bootcamp.network;
//
// Created by maftuhin on 10/18/2019.
//

import com.limaefdua.bootcamp.main.model.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("movie.json")
    Call<List<Film>> movieList();
}
