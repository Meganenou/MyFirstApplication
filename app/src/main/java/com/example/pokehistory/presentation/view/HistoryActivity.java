package com.example.pokehistory.presentation.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokehistory.R;
import com.example.pokehistory.presentation.controller.HistoryController;

public class HistoryActivity extends AppCompatActivity {

    private HistoryController historyController;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyController = new HistoryController(this);

        historyController.onGetIncomingIntent(this);
    }

}
