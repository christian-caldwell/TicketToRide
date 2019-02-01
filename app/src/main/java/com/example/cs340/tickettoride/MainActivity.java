package com.example.cs340.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize button and set OnClickListener
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Switch to the LobbyViewActivity when the button is pressed by creating an
                // intent and switching to the new activity with it
                Intent intent = new Intent(MainActivity.this, LobbyViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
