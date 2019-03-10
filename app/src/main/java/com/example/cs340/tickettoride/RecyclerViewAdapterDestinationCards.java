package com.example.cs340.tickettoride;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import view.presenter.CardDeckPresenter;
import view.presenterInterface.ICardDeckPresenter;
import view.presenterInterface.IPlayerInfoPresenter;

public class RecyclerViewAdapterDestinationCards extends RecyclerView.Adapter<RecyclerViewAdapterDestinationCards.ViewHolder> {

    private ArrayList<String> mDestinationRoutes;
    private Context mContext;
    private IPlayerInfoPresenter playerInfoPresenter;

    public RecyclerViewAdapterDestinationCards(ArrayList<String> mDestinationRoutes, Context mContext, IPlayerInfoPresenter playerInfoPresenter) {
        this.mDestinationRoutes = mDestinationRoutes;
        this.mContext = mContext;
        this.playerInfoPresenter = playerInfoPresenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_destination_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.destinationRoute.setText(mDestinationRoutes.get(position));
        holder.discardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getBackground().setColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY);
                v.setAlpha(.5f);
                v.setEnabled(false);
                //TODO: CALL METHOD IN PRESENTER TO DISCARD CARDS
                //if result is successful, make toast saying it will be discarded
                //if not, display toast saying user cannot discard it
                Toast.makeText(mContext, mDestinationRoutes.get(position) +
                        "\nThis card will be discarded", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mDestinationRoutes.size();
    }

    public void setListOfDestinationCards(ArrayList<String> destinationCardList) {
        mDestinationRoutes = destinationCardList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView destinationRoute;
        Button discardButton;

        public ViewHolder(View itemView) {
            super(itemView);
            destinationRoute = itemView.findViewById(R.id.textview_for_destination_card);
            discardButton = itemView.findViewById(R.id.discard_button);
        }
    }

}
