package com.example.cs340.tickettoride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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

import models.data.Game;
import models.data.Result;
import view.activityInterface.IGameLobby;
import view.presenter.GameLobbyPresenter;
import view.presenterInterface.IGameLobbyPresenter;

public class LobbyViewActivity extends AppCompatActivity implements IGameLobby {

    // Member variables
    private ArrayList<Game> listOfGames;
    static private Button startGameButton, createGameButton;
    private boolean createGameOpen = false;
    private String create_game_text = "";
    static private RecyclerViewAdapter adapter;
    private IGameLobbyPresenter presenter = new GameLobbyPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_lobby_view);
        initRecyclerView();

        // Initialize startGameButton and set onClickListener
        // If the user is not a host, disable the 'Start Game' button
        startGameButton = findViewById(R.id.startGameButton);
        if (!presenter.getPlayer().isHost()) {
            startGameButton.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
            startGameButton.setAlpha(.5f);
            startGameButton.setEnabled(false);
        }
        else {
            startGameButton.getBackground().setColorFilter(null);
            startGameButton.setAlpha(1);
            startGameButton.setEnabled(true);
        }

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the user is a host, the button will be enabled
//              // When clicked, the GameBoardActivity will be started
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
                        

                        // Disable 'create game' button

                        Game game = new Game(input.getText().toString());
                        Result result = presenter.createGame(game);
                        startGameButton.getBackground().setColorFilter(null);
                        startGameButton.setAlpha(1);
                        startGameButton.setEnabled(true);

                        createGameButton.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
                        createGameButton.setAlpha(.5f);
                        createGameButton.setEnabled(false);

                        //FIXME: THIS TWO LINES SHOULDN'T BE CALLED - IT SHOULD BE THE
                        //PULLER THAT UPDATES THE GAME LIST
                        //AND THE CLIENT ARRAY OF GAMES DOESN'T GET UPDATED CORRECTLY - IT IS
                        //INSTANTIATED WITH 0 AMOUNT OF PLAYERS
                        //TO FIX THIS, CHECK OUT THE SERVERPROXY
                        listOfGames = presenter.getGameList();
                        listOfGames.get(listOfGames.size()-1).addPlayer("this is the host");
                        adapter.notifyDataSetChanged();
                        if (result.isSuccessful()) {
                            Toast.makeText(LobbyViewActivity.this, "Succesfully created game:" + game.getGameName(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LobbyViewActivity.this, result.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }
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
    public void updateGameList(ArrayList<Game> lobbyGames) {
        listOfGames = lobbyGames;
        adapter.notifyDataSetChanged();
    }

    public void updateGameListAfterClickingOnGame(ArrayList<Game> lobbyGames) {
        // Disable 'create game' button
        createGameButton.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
        createGameButton.setAlpha(.5f);
        createGameButton.setEnabled(false);

        listOfGames = lobbyGames;
        if (listOfGames.size() > 1) {
            // Enable 'start game' button
            startGameButton.getBackground().setColorFilter(null);
            startGameButton.setAlpha(1);
            startGameButton.setEnabled(true);
        }
    }

    @Override
    public void onGameCreated() {

    }

    @Override
    public void onCreateGameFailed(String errorMessage) {

    }

}
