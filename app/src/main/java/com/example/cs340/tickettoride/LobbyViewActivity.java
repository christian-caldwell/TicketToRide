package com.example.cs340.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Map;

import game.Game;
import viewInterfaces.IGameLobby;

public class LobbyViewActivity extends AppCompatActivity implements IGameLobby {

    // Member variables
    private ArrayList<String> mGameNames = new ArrayList<>();
    private ArrayList<String> mCurrentNumOfPlayers = new ArrayList<>();
    private ArrayList<String> mMaxNumOfPlayers = new ArrayList<>();
    private Button startGameButton, createGameButton;

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
                // When clicked, the GameBoardActivity will be started
                Intent intent = new Intent(LobbyViewActivity.this, GameBoardActivity.class);
                startActivity(intent);
            }
        });


        // Initialize createGameButton and set onClickListener
        createGameButton = findViewById(R.id.createGameButton);
        createGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the button is clicked, the user will be brought to to the createGameFragment
                //Intent intent = new Intent(LobbyViewActivity.this, CreateGameFragment.class);
                //startActivity(intent);
            }
        });

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
