package com.example.myfirstapplication.presentation.controller;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.myfirstapplication.Constants;
import com.example.myfirstapplication.Singletons;
import com.example.myfirstapplication.presentation.model.Region;
import com.example.myfirstapplication.presentation.model.RestPokemonResponse;
import com.example.myfirstapplication.presentation.view.RegionListActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionListController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private RegionListActivity view;

    public RegionListController(RegionListActivity regionListActivity, Gson gson, SharedPreferences sharedPreferences) {
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

        Call<RestPokemonResponse> call = Singletons.getPokeAPI().getPokemonResponse();
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
}
