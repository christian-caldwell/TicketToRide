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


// This class will perform all the logic for anything within the recyclerView

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mGameNames = new ArrayList<>();
    private ArrayList<String> mCurrentNumOfPlayers = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mGameNames, ArrayList<String> mCurrentNumOfPlayers, Context mContext) {
        this.mGameNames = mGameNames;
        this.mCurrentNumOfPlayers = mCurrentNumOfPlayers;
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
        holder.currentNumOfPlayers.setText("Current players: " + mCurrentNumOfPlayers.get(position));
        holder.gameName.setText(mGameNames.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "You've been added to " + mGameNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGameNames.size();
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
