package com.example.cs340.tickettoride;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class GameBoardActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You committed to this game. No turning back.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_game_board);
    }
}
