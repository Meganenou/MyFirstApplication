package com.example.myfirstapplication.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.Constants;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.data.PokeAPI;
import com.example.myfirstapplication.presentation.model.Region;
import com.example.myfirstapplication.presentation.model.RestPokemonResponse;
import com.example.myfirstapplication.presentation.view.ListAdapter;
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

public class RegionListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private static final  String BASE_URL ="https://raw.githubusercontent.com/";

    private Gson gson;

    private SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_region_list);

        ActionBar actionBar;
        actionBar = getSupportActionBar();

        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(getString(R.string.color_skin)));
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(colorDrawable);
        }

        gson = new GsonBuilder()
                .setLenient()
                .create();

        sharedPreferences = getSharedPreferences(Constants.NAME_POKEMON_APP, Context.MODE_PRIVATE);

        List<Region> regionList = getDataFromCache();
        if(regionList != null){
            showList(regionList);
        } else {
            makeApiCall();
        }
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

    private void showList(List<Region> regionList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(regionList, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
                    showList(regionList);
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Region> regionList) {

        String jsonString = gson.toJson(regionList);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_REGION_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }


}
