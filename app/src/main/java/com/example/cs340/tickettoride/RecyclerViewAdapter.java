package com.example.cs340.tickettoride;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.data.Game;
import view.presenter.GameLobbyPresenter;
import view.presenterInterface.IGameLobbyPresenter;


// This class will perform all the logic for anything within the recyclerView

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Game> listOfGames = new ArrayList<>();
    private Context mContext;
    private IGameLobbyPresenter presenter = new GameLobbyPresenter();


    public RecyclerViewAdapter(ArrayList<Game> listOfGames, Context mContext) {
        this.listOfGames = listOfGames;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutlist_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.currentNumOfPlayers.setText("Current players: " +
                listOfGames.get(position).getPlayers().size());
        holder.gameName.setText(listOfGames.get(position).getGameName());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                // Check if user is already in a game. If not, add them to game
                if (presenter.getPlayer().getGame() != null)
                    Toast.makeText(mContext, "Already part of a game", Toast.LENGTH_SHORT).show();
                else {
                    //FIXME: THIS NEEDS TO CALL SOMETHING TO ADD THE PLAYER TO THE GAME

                    Toast.makeText(mContext, "You've been added to " +
                            listOfGames.get(position).getGameName(), Toast.LENGTH_SHORT).show();
                }
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
