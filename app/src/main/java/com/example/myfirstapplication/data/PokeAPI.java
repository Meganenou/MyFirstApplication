package com.example.myfirstapplication.data;

import com.example.myfirstapplication.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeAPI {

    @GET("/Meganenou/MyFirstApplication/master/MyAPI_pokemon.json")
    Call<RestPokemonResponse> getPokemonResponse();
    
}
