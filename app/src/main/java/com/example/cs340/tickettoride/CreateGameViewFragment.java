package com.example.cs340.tickettoride;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class CreateGameViewFragment extends Fragment {

    Button createGameButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createGameButton = getView().findViewById(R.id.create_game_button);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_game, container, false);

    }

}
