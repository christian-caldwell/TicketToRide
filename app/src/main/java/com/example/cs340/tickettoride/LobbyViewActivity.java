package com.example.cs340.tickettoride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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

import models.data.Game;
import view.activityInterface.IGameLobby;
import view.presenter.GameLobbyPresenter;

public class LobbyViewActivity extends AppCompatActivity implements IGameLobby {

    // Member variables
    private ArrayList<Game> listOfGames;
    private Button startGameButton, createGameButton;
    private boolean createGameOpen = false;
    private String create_game_text = "";
    private RecyclerViewAdapter adapter;
    private GameLobbyPresenter presenter = new GameLobbyPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_lobby_view);
        initRecyclerView();

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
                builder.setMessage("Choose a name for your game");

                final EditText input = new EditText(LobbyViewActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        create_game_text = input.getText().toString();
                        GameLobbyPresenter presenter = new GameLobbyPresenter();
                        Game game = new Game(create_game_text);
                        presenter.createGame(game);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(LobbyViewActivity.this, create_game_text + " created!", Toast.LENGTH_SHORT).show();
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


    // This initializes the recyclerView
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        listOfGames = presenter.getGameList();
        adapter = new RecyclerViewAdapter(listOfGames, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void updateGameList(Map<String, Game> map) {
    }

    @Override
    public void updateGamePlayers(Game game) {

    }

    @Override
    public void onGameCreated() {

    }

    @Override
    public void onCreateGameFailed(String errorMessage) {

    }

}
