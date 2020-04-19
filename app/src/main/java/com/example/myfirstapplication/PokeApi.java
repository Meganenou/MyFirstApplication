package com.example.myfirstapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("/api/v2/pokemon")
    Call<PokemonRestResponse> getPokemonResponse();

    @GET("/api/v2/pokemon-habitat")
    Call<PokemonRestResponse> getHabitat();
}
