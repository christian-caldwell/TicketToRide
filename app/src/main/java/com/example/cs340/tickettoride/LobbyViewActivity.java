package com.example.cs340.tickettoride;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class LobbyViewActivity extends AppCompatActivity {

    // Member variables
    private ArrayList<String> mGameNames = new ArrayList<>();
    private ArrayList<String> mCurrentNumOfPlayers = new ArrayList<>();
    private ArrayList<String> mMaxNumOfPlayers = new ArrayList<>();
    private Button startGameButton, createGameButton;
    private boolean createGameOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_view);
        initArraysTest();

        final Dialog createGameDialogue = new Dialog(LobbyViewActivity.this);
        // Initialize startGameButton and set onClickListener
        startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the user is a host, the button will be enabled
//                // When clicked, the GameBoardActivity will be started
                Intent intent = new Intent(LobbyViewActivity.this, GameBoardActivity.class);
                startActivity(intent);


            }
        });


        // Initialize createGameButton and set onClickListener
        // This is the button at the bottom of the recycler view
        createGameButton = findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(LobbyViewActivity.this, CreateGameActivity.class);
//                startActivity(intent);

                AlertDialog alertDialog = new AlertDialog.Builder(LobbyViewActivity.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Alert message to be shown");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();

//                createGameDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
//                createGameDialogue.setContentView(R.layout.fragment_create_game);
//                createGameDialogue.setCancelable(true);
//                createGameDialogue.show();
//                createGameOpen = true;
            }
        });

        // This is the button to create game in the dialogue box
        final Button createGameButtonDialog;
        createGameButtonDialog = findViewById(R.id.create_game_button);
        if(createGameOpen) {
            createGameButtonDialog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LobbyViewActivity.this, "Game Created", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    // This will initialize the test arrays to display in recyclerview
    // it will not be called in the real project
    private void initArraysTest() {
        mGameNames.add("game1");
        mGameNames.add("game2");
        mCurrentNumOfPlayers.add("3");
        mCurrentNumOfPlayers.add("2");
        mMaxNumOfPlayers.add("5");
        mMaxNumOfPlayers.add("4");
        initRecyclerView();

    }

    // This initializes the recyclerView
    // It will be called
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mGameNames, mCurrentNumOfPlayers, mMaxNumOfPlayers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
