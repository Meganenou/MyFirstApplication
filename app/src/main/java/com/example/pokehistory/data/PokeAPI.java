package com.example.pokehistory.data;

import com.example.pokehistory.presentation.model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeAPI {

    @GET("/Meganenou/MyFirstApplication/master/MyAPI_pokemon.json")
    Call<RestPokemonResponse> getPokemonResponse();
    
}
