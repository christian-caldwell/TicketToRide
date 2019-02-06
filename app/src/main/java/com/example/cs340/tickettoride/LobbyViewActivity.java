package com.example.cs340.tickettoride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import models.Game;
import viewInterfaces.IGameLobby;

public class LobbyViewActivity extends AppCompatActivity implements IGameLobby {

    // Member variables
    private ArrayList<String> mGameNames = new ArrayList<>();
    private ArrayList<String> mCurrentNumOfPlayers = new ArrayList<>();
    private ArrayList<String> mMaxNumOfPlayers = new ArrayList<>();
    private Button startGameButton, createGameButton;
    private boolean createGameOpen = false;
    private String create_game_text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_view);
        initArraysTest();

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

                AlertDialog.Builder builder = new AlertDialog.Builder(LobbyViewActivity.this);
                builder.setTitle("Create Game");

                final EditText input = new EditText(LobbyViewActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        create_game_text = input.getText().toString();
                        Toast.makeText(LobbyViewActivity.this, create_game_text + " created!", Toast.LENGTH_SHORT).show();
                        mGameNames.add(create_game_text);
                        // Make calls to presenter from here
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
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


    @Override
    public void updateGameList(Map<String, Game> map) {
    }

    @Override
    public void updateGamePlayers(Game game) {
    }
}
