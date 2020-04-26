package com.example.myfirstapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private List<Region> regionList;

    private static final  String URL_DATA ="https://raw.githubusercontent.com/Meganenou/MyFirstApplication/master/MyAPI_pokemon.json";

    private Gson gson;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        regionList = new ArrayList<>();

        loadRecyclerViewData();

       /* gson = new GsonBuilder()
                .setLenient()
                .create();*/

      // sharedPreferences = getSharedPreferences(Constants.NAME_POKEMON_APP, Context.MODE_PRIVATE);

  /*     List<Region> regionList = getDataFromCache();
        if(regionList != null){
            showList(regionList);
        } else { make..
        }*/
    }

    private void loadRecyclerViewData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loadind data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("region");

                            for(int i = 0; i < array.length(); i++){
                                JSONObject o = array.getJSONObject(i);
                                Region region = new Region(
                                        o.getString("name"),
                                        o.getString("description"),
                                        o.getString("story"),
                                        o.getString("image")
                                );
                                regionList.add(region);
                            }

                            mAdapter = new ListAdapter(regionList, getApplicationContext());
                            recyclerView.setAdapter(mAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

   /* private List<Region> getDataFromCache() {
        String jsonPokemon = sharedPreferences.getString(Constants.KEY_REGION_LIST, null);
        if(jsonPokemon == null) {
            return null;
        } else {
            Type listType = new TypeToken<List<Region>>(){}.getType();
            return gson.fromJson(jsonPokemon, listType);
        }
    }*/

  /*  private void showList(List<Region> regionList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        regionList = new ArrayList<>();
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

          /* API */
    /*private void makeApiCall() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PokeApi pokeApi = retrofit.create(PokeApi.class);

        Call<PokemonRestResponse> call = pokeApi.getPokemonResponse();
        call.enqueue(new Callback<PokemonRestResponse>() {
            @Override
            public void onResponse(Call<PokemonRestResponse> call, Response<PokemonRestResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Region> region = response.body().getReg();
                    saveList(region);
                    showList(region);
                } else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<PokemonRestResponse> call, Throwable t) {
                showError();
            }
        });
    }*/

    /*private void saveList(List<Region> regionList) {
        String jsonString = gson.toJson(regionList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_REGION_LIST, jsonString)
                .apply();

        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();

    }


    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }*/
}
