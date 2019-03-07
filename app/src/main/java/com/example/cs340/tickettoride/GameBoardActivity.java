package com.example.cs340.tickettoride;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import client.Poller;
import models.data.ChatMessage;
import models.data.Game;
import models.data.User;
import view.presenter.ChatPresenter;
import view.presenterInterface.IChatPresenter;

public class GameBoardActivity extends AppCompatActivity {

    private static ArrayList<ChatMessage> chatMessages;
    IChatPresenter presenter;
    private static RecyclerViewAdapterChat adapter;
    private static EditText inputChatEditText;
    private static Button sendMessageButton;



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
        View decorView = getWindow().getDecorView();

            // Hide both the navigation bar and the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        presenter = new ChatPresenter();
        initRecyclerView();
        inputChatEditText = findViewById(R.id.input_edit_text);
        sendMessageButton = findViewById(R.id.send_message_button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            // When the sendMessage button is clicked, send the text to the presenter.addMessage function
            @Override
            public void onClick(View v) {
                String newMessage = inputChatEditText.getText().toString();
                presenter.addMessage(newMessage);
            }
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);
        chatMessages = presenter.getMessages();
        adapter = new RecyclerViewAdapterChat(chatMessages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Void updateChatList(ArrayList<ChatMessage> newChatMessages) {
        chatMessages = newChatMessages;
        return null;
    }



    public static class UpdateChatListAsyncTask extends AsyncTask<ArrayList<ChatMessage>, Void, Void> {
        //private IGameLobby gameLobby = new LobbyViewActivity();
        private Context context;


        //Constructor to make
        public UpdateChatListAsyncTask(Context context) {
            this.context = context.getApplicationContext();
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
        protected Void doInBackground(ArrayList<ChatMessage>... arrayLists) {
            return updateChatList(arrayLists[0]);
        }

        @Override
        protected void onPostExecute(Void result) {

            adapter.setListOfMessages(chatMessages);
            adapter.notifyDataSetChanged();

            /*
            // If user is part of a game, disable the createGameButton
            if (user.getGame() != null)
                disableCreateGameButton();

                // If user isn't part of a game, disable the startGameButton
            else
                disableStartGameButton();


            // If user is a host and the amount of players in the game is greater than 1, then
            // enable the startGamebutton
            if (user.getGame() != null) {
                if (user.isHost() && user.getGame().getPlayerUsernames().size() > 1) {
                    enableStartGameButton();
                }
                // If not a host or if there aren't enough players, disable the 'Start game' button
                else {
                    disableStartGameButton();
                }
                if (user.getGame().isStarted()) {
                    Poller.instance().startPollingGame();
                    Intent intent = new Intent(context, GameBoardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
            */


        }
    }
}