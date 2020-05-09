package com.example.myfirstapplication.presentation.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.Constants;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.data.PokeAPI;
import com.example.myfirstapplication.presentation.model.Region;
import com.example.myfirstapplication.presentation.model.RestPokemonResponse;
import com.example.myfirstapplication.presentation.view.ListAdapter;
import com.example.myfirstapplication.presentation.view.RegionListActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private RegionListActivity view;

    public MainController(RegionListActivity regionListActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = regionListActivity;
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public void onStart(){



        List<Region> regionList = getDataFromCache();
        if(regionList != null){
            view.showList(regionList);
        } else {
            makeApiCall();
        }
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeAPI pokeAPI = retrofit.create(PokeAPI.class);

        Call<RestPokemonResponse> call = pokeAPI.getPokemonResponse();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Region> regionList = response.body().getRegion();
                    saveList(regionList);
                    view.showList(regionList);
                }
            }
            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                view.showError();
            }
        });
    }

    private List<Region> getDataFromCache() {
        String jsonRegion = sharedPreferences.getString(Constants.KEY_REGION_LIST, null);

        if(jsonRegion == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Region>>(){}.getType();
            return gson.fromJson(jsonRegion, listType);
        }
    }

    private void saveList(List<Region> regionList) {

        String jsonString = gson.toJson(regionList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_REGION_LIST, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    public void onButtonGoToListClick(){

    }
}
