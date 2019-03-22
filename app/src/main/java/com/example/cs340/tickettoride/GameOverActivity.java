package com.example.cs340.tickettoride;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import models.TTR_Constants;

public class GameOverActivity extends AppCompatActivity {
    ImageView winnerTop, winnerBottom, p2Top, p2Bottom, p3Top, p3Bottom, p4Top, p4Bottom, p5Top, p5Bottom;
    TextView winnerName, winnerScore, winnerGainedDestinationCards, winnerLostDestinationCards,
            p2Name, p2Score, p2gainedDestinationCards, p2LostDestinationCards,
            p3Name, p3Score, p3gainedDestinationCards, p3LostDestinationCards,
            p4Name, p4Score, p4gainedDestinationCards, p4LostDestinationCards,
            p5Name, p5Score, p5gainedDestinationCards, p5LostDestinationCards;
    Button backToGameLobbyButton;
    private static Map playerColorValues;
    private static IGameOverPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        playerColorValues = new HashMap();
        presenter = new GameOverPresenter();
        playerColorValues.put(TTR_Constants.getInstance().BLACK_PLAYER, R.drawable.black_background);
        playerColorValues.put(TTR_Constants.getInstance().BLUE_PLAYER, R.drawable.blue_background);
        playerColorValues.put(TTR_Constants.getInstance().GREEN_PLAYER, R.drawable.green_background);
        playerColorValues.put(TTR_Constants.getInstance().RED_PLAYER, R.drawable.red_background);
        playerColorValues.put(TTR_Constants.getInstance().YELLOW_PLAYER, R.drawable.yellow_background);



        winnerTop = findViewById(R.id.winner_color_top);
        winnerTop.setImageDrawable (presenter.getPlayersInWinningOrder().get(0));
    }


}
