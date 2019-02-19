package com.example.cs340.tickettoride;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
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
import models.data.User;
import view.presenter.GameLobbyPresenter;
import view.presenterInterface.IGameLobbyPresenter;

public class LobbyViewActivity extends AppCompatActivity /*implements IGameLobby*/ {

    // Member variables
    private static ArrayList<Game> listOfGames;
    static private Button startGameButton, createGameButton;
    private boolean createGameOpen = false;
    private String create_game_text = "";
    private static RecyclerViewAdapter adapter;
    private IGameLobbyPresenter presenter = new GameLobbyPresenter();
    private static LobbyViewActivity singleton;


    public static LobbyViewActivity create() {
        if (singleton == null) {
            singleton = new LobbyViewActivity();
        }
        return singleton;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_lobby_view);
        initRecyclerView();
        final GameLobbyPresenter lobbyPresenter = new GameLobbyPresenter();


        lobbyPresenter.onCreate();


        // Initialize startGameButton and set onClickListener
        // If the user is not a host, disable the 'Start Game' button
        startGameButton = findViewById(R.id.startGameButton);
        if (!presenter.getPlayer().isHost()) {
            disableStartGameButton();
        }
        else {
            enableStartGameButton();
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
                        

                        // Enable startGameButton and disable createGameButton
                        Game game = new Game(input.getText().toString());
                        Result result = presenter.createGame(game);
                        //enableStartGameButton();
                        //disableCreateGameButton();

                        if (result.isSuccessful()) {
                            //enableStartGameButton();
                            disableCreateGameButton();
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



    public static Void updateGameList(ArrayList<Game> lobbyGames, User user) {


        // FIXME: The poller reaches this line of code, then breaks at 'adapter.notifyDataSetChanged()'
        // because adapter has not been instantiated yet.  It gets instantiated once the LobbyViewActivity
        // opens up.  If there is a way for the poller to start calling either once LobbyViewActivity
        // has openned, or when the create game function has been called, then that would work
        //listOfGames = lobbyGames;
        // System.out.println(listOfGames.toString());
        //adapter.notifyDataSetChanged();
        // System.out.println("done");

        listOfGames = lobbyGames;
        // If user is a host and the game they are a part of has
        // more than 2 people, enable start game button
        /*
        if (user.isHost()) {
            // if (user.getGame().getPlayers().size() > 1)

            enableStartGameButton();
        }

        // If not a host, disable the 'Start game' button
        else
            disableStartGameButton();
*/

        //Toast.makeText(LobbyViewActivity.this, "Poller successfully updated", Toast.LENGTH_SHORT).show();

        return null;
    }


    public void updateGameListAfterClickingOnGame(ArrayList<Game> lobbyGames) {
        // Disable 'create game' button
        disableCreateGameButton();

        listOfGames = lobbyGames;
        if (listOfGames.size() > 1) {
            // Enable 'start game' button
            enableStartGameButton();
        }
    }



    public static void disableStartGameButton(){
        startGameButton.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
        startGameButton.setAlpha(.5f);
        startGameButton.setEnabled(false);
    }

    public static void enableStartGameButton(){
        startGameButton.getBackground().setColorFilter(null);
        startGameButton.setAlpha(1);
        startGameButton.setEnabled(true);
    }

    public static void disableCreateGameButton(){
        createGameButton.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
        createGameButton.setAlpha(.5f);
        createGameButton.setEnabled(false);
    }

    public void enableCreateGameButton(){
        createGameButton.getBackground().setColorFilter(null);
        createGameButton.setAlpha(1);
        createGameButton.setEnabled(true);
    }



    // AsyncTask class
    public static class UpdateGameListAsyncTask extends AsyncTask<ArrayList<Game>, Void, Void> {
        //private IGameLobby gameLobby = new LobbyViewActivity();
        private User user;



        //Constructor to make
        public UpdateGameListAsyncTask(User user) {
            this.user = user;
        }

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         */

        // It's possible that having this return 'void' could cause a problem
        @Override
        protected Void doInBackground(ArrayList<Game>... arrayLists) {
            return updateGameList(arrayLists[0], user);
        }

        @Override
        protected void onPostExecute(Void result) {

            adapter.setListOfGames(listOfGames);
            adapter.notifyDataSetChanged();

            // If user is part of a game, disable the createGameButton
            if (user.getGame() != null)
                disableCreateGameButton();

            // If user isn't part of a game, disable the startGameButton
            else
                disableStartGameButton();


            // If user is a host and the amount of players in the game is greater than 1, then
            // enable the startGamebutton
            if (user.isHost() && user.getGame().getPlayers().size() > 1)
                enableStartGameButton();

            // If not a host or if there aren't enough players, disable the 'Start game' button
            else
                disableStartGameButton();

        }


    }

}
