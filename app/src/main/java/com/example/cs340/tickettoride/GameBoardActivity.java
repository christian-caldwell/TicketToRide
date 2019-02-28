package com.example.cs340.tickettoride;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.data.ChatMessage;
import models.data.Game;
import models.data.Result;
import view.presenter.ChatPresenter;
import view.presenter.GameLobbyPresenter;
import view.presenterInterface.IChatPresenter;
import view.presenterInterface.IGameLobbyPresenter;

public class GameBoardActivity extends AppCompatActivity {

    ArrayList<ChatMessage> chatMessages;
    IChatPresenter presenter;
    private static ChatRecyclerViewAdapter adapter;

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
        presenter = new ChatPresenter();


        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        chatMessages = presenter.getMessages();
        adapter = new ChatRecyclerViewAdapter(chatMessages, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private ArrayList<ChatMessage> listOfGames = new ArrayList<>();
        private Context mContext;


        public ChatRecyclerViewAdapter(ArrayList<ChatMessage> listOfGames, Context mContext) {
            this.listOfGames = listOfGames;
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutlist_item, viewGroup, false);
            ViewHolder holder = new ChatRecyclerViewAdapter(view);
            return holder;
        }

        public void setListOfGames(ArrayList<Game> listOfGames) {
            this.listOfGames = listOfGames;
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.currentNumOfPlayers.setText("Current players: " +
                    listOfGames.get(position).getPlayerUsernames().size());
            holder.gameName.setText(listOfGames.get(position).getGameName());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    // Check if user is already in a game. If not, add them to game
                    // Don't allow more than 5 people join a game
                }
            });
        }

        @Override
        public int getItemCount() {
            return listOfGames.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView gameName, currentNumOfPlayers;
            ConstraintLayout parentLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                gameName = itemView.findViewById(R.id.gameName);
                currentNumOfPlayers = itemView.findViewById(R.id.currentNumOfPlayers);
                parentLayout = itemView.findViewById(R.id.parent_layout);
            }
        }
    }


}
