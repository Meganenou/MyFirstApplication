package com.example.pokehistory;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pokehistory.data.PokeAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singletons {

    private static Gson gsonInstance;
    private static PokeAPI pokeApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static PokeAPI getPokeAPI(){
        if(pokeApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            pokeApiInstance= retrofit.create(PokeAPI.class);
        }
        return pokeApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){
        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences(Constants.NAME_POKEMON_APP, Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
