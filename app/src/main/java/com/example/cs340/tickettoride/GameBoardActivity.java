package com.example.cs340.tickettoride;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GameBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_game_board);
    }
}
