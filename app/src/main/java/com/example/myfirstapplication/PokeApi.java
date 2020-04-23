package com.example.myfirstapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeApi {
    @GET("/api/v2/pokemon_accueil")
    Call<PokemonRestResponse> getPokemonResponse();

    @GET("/api/v2/pokemon_accueil-habitat")
    Call<Habitat> getHabitat();

    @GET("/api/v2//api/v2/pokedex/")
    Call<Pokedex> getPokedex();

    @GET("/api/v2/region/")
    Call<Region> getRegion();
}
