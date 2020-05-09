package com.example.myfirstapplication.presentation.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.presentation.controller.HistoryController;

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
