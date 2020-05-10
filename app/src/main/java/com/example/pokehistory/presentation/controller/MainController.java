package com.example.pokehistory.presentation.controller;

import android.content.Intent;

import com.example.pokehistory.presentation.view.MainActivity;
import com.example.pokehistory.presentation.view.RegionListActivity;

public class MainController {

    private MainActivity view;

    public MainController(MainActivity mainActivity) {
        this.view = mainActivity;
    }

    public void onButtonGoToListClick(MainActivity view){
        Intent intent = new Intent(view, RegionListActivity.class);
        view.startActivity(intent);
    }
}
