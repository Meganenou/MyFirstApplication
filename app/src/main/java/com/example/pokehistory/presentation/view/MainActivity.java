package com.example.pokehistory.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.pokehistory.R;
import com.example.pokehistory.presentation.controller.MainController;

public class MainActivity extends AppCompatActivity {

    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainController = new MainController(this);
    }

    public void goToList(View view){
        mainController.onButtonGoToListClick(this);
    }

}
