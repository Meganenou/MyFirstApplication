package com.example.myfirstapplication.presentation.controller;

import android.content.Intent;

import com.example.myfirstapplication.presentation.view.MainActivity;
import com.example.myfirstapplication.presentation.view.RegionListActivity;

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
