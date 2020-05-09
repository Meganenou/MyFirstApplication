package com.example.myfirstapplication.presentation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.presentation.controller.MainController;

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
