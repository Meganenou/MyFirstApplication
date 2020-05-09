package com.example.myfirstapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeAPI {

    @GET("/Meganenou/MyFirstApplication/master/MyAPI_pokemon.json")
    Call<RestPokemonResponse> getPokemonResponse();
    
}
