package com.example.cs340.tickettoride;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import client.ClientModel;
import models.TTR_Constants;
import models.data.ChatMessage;
import models.data.Player;
import models.data.Route;
import view.presenter.CardDeckPresenter;
import view.presenter.ChatPresenter;
import view.presenter.DemoPresenter;
import view.presenter.PlayerInfoPresenter;
import view.presenter.PlayersHandPresenter;
import view.presenterInterface.ICardDeckPresenter;
import view.presenterInterface.IChatPresenter;
import view.presenterInterface.IPlayerInfoPresenter;
import view.presenterInterface.IPlayersHandPresenter;

public class GameBoardActivity extends AppCompatActivity {

    private static ArrayList<ChatMessage> chatMessages;
    private static ArrayList<String> destinationCardList;
    private static IChatPresenter chatPresenter;
    private static IPlayersHandPresenter playersHandPresenter;
    private static IPlayerInfoPresenter playerInfoPresenter;
    private static ICardDeckPresenter cardDeckPresenter;
    private static RecyclerViewAdapterChat adapter;
    private static RecyclerViewAdapterDestinationCards destinationCardsAdapter;
    private static EditText inputChatEditText;
    private static Button gameDemoButton;
    private DemoPresenter mDemoPresenter;
    private static Button sendMessageButton, playerInfoButton;
    private static Button mGreenTrainCard, mRedTrainCard, mPinkTrainCard, mYellowTrainCard,
            mWhiteTrainCard, mBlackTrainCard, mWildTrainCard, mBlueTrainCard, mOrangeTrainCard;
    private static Button destinationCardDeck, trainCardDeck, cardOne, cardTwo, cardThree, cardFour, cardFive;
    private static ImageView gameBoard;
    private static Map playerColorValues;
    private static Map trainCardImages;
    private static PopupWindow mPopupWindow;
    private static TextView one_destinationCards, one_trainCards, one_score, one_trainsLeft;
    private static TextView two_destinationCards, two_trainCards, two_score, two_trainsLeft;
    private static TextView three_destinationCards, three_trainCards, three_score, three_trainsLeft;
    private static TextView four_destinationCards, four_trainCards, four_score, four_trainsLeft;
    private static TextView five_destinationCards, five_trainCards, five_score, five_trainsLeft;
    private static DrawerLayout activityLayout;

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
        playerColorValues = new HashMap();
        trainCardImages = new HashMap<>();
        //FIXME: the hard colored playerColor ints are incorrect. To get the correct colors, do Constants.Player_COLOR for each.
        playerColorValues.put(5,R.drawable.black_background);
        playerColorValues.put(3,R.drawable.blue_background);
        playerColorValues.put(2,R.drawable.green_background);
        playerColorValues.put(1,R.drawable.red_background);
        playerColorValues.put(4,R.drawable.yellow_background);

        trainCardImages.put(TTR_Constants.getInstance().BLACK,R.drawable.train_card_black);
        trainCardImages.put(TTR_Constants.getInstance().BLUE,R.drawable.train_card_blue);
        trainCardImages.put(TTR_Constants.getInstance().GREEN,R.drawable.train_card_green);
        trainCardImages.put(TTR_Constants.getInstance().PURPLE,R.drawable.train_card_purple);
        trainCardImages.put(TTR_Constants.getInstance().ORANGE,R.drawable.train_card_orange);
        trainCardImages.put(TTR_Constants.getInstance().RED,R.drawable.train_card_red);
        trainCardImages.put(TTR_Constants.getInstance().WHITE,R.drawable.train_card_white);
        trainCardImages.put(TTR_Constants.getInstance().WILD,R.drawable.train_card_wild);
        trainCardImages.put(TTR_Constants.getInstance().YELLOW,R.drawable.train_card_yellow);

        // Hide both the navigation bar and the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        chatPresenter = new ChatPresenter(this);
        cardDeckPresenter = new CardDeckPresenter(this);
        playerInfoPresenter = new PlayerInfoPresenter(this);
        playersHandPresenter = new PlayersHandPresenter(this);
        initRecyclerView();
        initDestinationCardsRecyclerView();
        inputChatEditText = findViewById(R.id.input_edit_text);
        sendMessageButton = findViewById(R.id.send_message_button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            // When the sendMessage button is clicked, send the text to the chatPresenter.addMessage function
            @Override
            public void onClick(View v) {
                String newMessage = inputChatEditText.getText().toString();
                chatPresenter.addMessage(newMessage);
                inputChatEditText.setText("");
            }
        });
        gameDemoButton = findViewById(R.id.gameDemoButon);

        ClientModel.create().setGameBoardActivity(this);

        gameDemoButton.setOnClickListener(new View.OnClickListener() {
            // When the sendMessage button is clicked, send the text to the presenter.addMessage function
            @Override
            public void onClick(View v) {
                Toast.makeText(GameBoardActivity.this, "Starting Game Demo", Toast.LENGTH_SHORT).show();
            }
        });

        playerInfoButton = findViewById(R.id.get_player_info_button);
        playerInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAtLocation(activityLayout, Gravity.CENTER,0,0);
            }
        });

        mGreenTrainCard = findViewById(R.id.greenCard);
        mRedTrainCard = findViewById(R.id.redCard);
        mPinkTrainCard = findViewById(R.id.pinkCard);
        mYellowTrainCard = findViewById(R.id.yellowCard);
        mWhiteTrainCard = findViewById(R.id.whiteCard);
        mBlackTrainCard = findViewById(R.id.blackCard);
        mWildTrainCard = findViewById(R.id.wildCard);
        mBlueTrainCard = findViewById(R.id.blueCard);
        mOrangeTrainCard = findViewById(R.id.orangeCard);
        one_destinationCards = findViewById(R.id.player1_destination_cards_text_view);
        one_score = findViewById(R.id.player1_score_text_view);
        one_trainCards = findViewById(R.id.player1_train_cards_text_view);
        one_trainsLeft = findViewById(R.id.player1_trains_left_text_view);
        two_destinationCards = findViewById(R.id.player2_destination_cards_text_view);
        two_score = findViewById(R.id.player2_score_text_view);
        two_trainCards = findViewById(R.id.player2_train_cards_text_view);
        two_trainsLeft = findViewById(R.id.player2_trains_left_text_view);
        three_destinationCards = findViewById(R.id.player3_destination_cards_text_view);
        three_score = findViewById(R.id.player3_score_text_view);
        three_trainCards = findViewById(R.id.player3_train_cards_text_view);
        three_trainsLeft = findViewById(R.id.player3_trains_left_text_view);
        four_destinationCards = findViewById(R.id.player4_destination_cards_text_view);
        four_score = findViewById(R.id.player4_score_text_view);
        four_trainCards = findViewById(R.id.player4_train_cards_text_view);
        four_trainsLeft = findViewById(R.id.player4_trains_left_text_view);
        five_destinationCards = findViewById(R.id.player5_destination_cards_text_view);
        five_score = findViewById(R.id.player5_score_text_view);
        five_trainCards = findViewById(R.id.player5_train_cards_text_view);
        five_trainsLeft = findViewById(R.id.player5_trains_left_text_view);

        chatMessages = chatPresenter.getMessages();
        destinationCardList = playerInfoPresenter.getDestinationCardStrings();
        mGreenTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(1));
        mRedTrainCard.setText(""+ playersHandPresenter.getTrainCardAmount(2));
        mPinkTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(6));
        mYellowTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(3));
        mWhiteTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(7));
        mBlackTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(8));
        mWildTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(9));
        mBlueTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(4));
        mOrangeTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(5));

        destinationCardDeck = findViewById(R.id.destination_card_deck);
        destinationCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawDestinationCard();
            }
        });

        trainCardDeck = findViewById(R.id.train_card_deck);
        trainCardDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(0);
            }
        });

        cardOne = findViewById(R.id.card_index_zero);
        cardOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(1);
            }
        });

        cardTwo = findViewById(R.id.card_index_one);
        cardTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(2);
            }
        });

        cardThree = findViewById(R.id.card_index_two);
        cardThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(3);
            }
        });

        cardFour = findViewById(R.id.card_index_three);
        cardFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(4);
            }
        });

        cardFive = findViewById(R.id.card_index_four);
        cardFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardDeckPresenter.drawTrainCard(5);
            }
        });

        cardFive.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(5)));
        cardFour.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(4)));
        cardThree.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(3)));
        cardTwo.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(2)));
        cardOne.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(1)));
        destinationCardDeck.setText("" + cardDeckPresenter.getDestinationCardsLeft());
        trainCardDeck.setText("" + cardDeckPresenter.getTrainCardsLeft());
        gameBoard = findViewById(R.id.game_board_pic);

    }

    private void initDestinationCardsRecyclerView() {
        destinationCardList = playerInfoPresenter.getDestinationCardStrings();

        // Initialize a new instance of LayoutInflater service
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

        // Inflate the custom layout/view
        View customView = inflater.inflate(R.layout.player_info_popup_window,null);

        // Initialize a new instance of popup window
        mPopupWindow = new PopupWindow(customView, 900,
                800, true);

        RecyclerView destinationCardsRecyclerView = mPopupWindow.getContentView().findViewById(R.id.recycler_view_destination_cards);
        destinationCardList = playerInfoPresenter.getNewDestinationCardStrings();
        destinationCardsAdapter = new RecyclerViewAdapterDestinationCards(destinationCardList, mPopupWindow.getContentView().getContext(), playerInfoPresenter);
        destinationCardsRecyclerView.setHasFixedSize(true);
        destinationCardsRecyclerView.setAdapter(destinationCardsAdapter);
        destinationCardsRecyclerView.setLayoutManager(new LinearLayoutManager(mPopupWindow.getContentView().getContext()));


        // Set an elevation value for popup window
        if(Build.VERSION.SDK_INT>=21)
            mPopupWindow.setElevation(10);

        activityLayout = findViewById(R.id.game_board_activity);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);
        chatMessages = chatPresenter.getMessages();
        adapter = new RecyclerViewAdapterChat(chatMessages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public static Void updateChatList(ArrayList<ChatMessage> newChatMessages) {
        chatMessages = newChatMessages;
        return null;
    }

    public void change_color_nashville_littlerock_g1(View view) {
        findViewById(R.id.nashville_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.nashville_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b2).setAlpha(1);
        findViewById(R.id.nashville_littlerock_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_littlerock_g1b3).setAlpha(1);
    }

    public void change_color_neworleans_houston_g1(View view) {
        findViewById(R.id.neworleans_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_houston_g1b1).setAlpha(1);
        findViewById(R.id.neworleans_houston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_houston_g1b2).setAlpha(1);
    }

    public void change_color_littlerock_neworleans_g1(View view) {
        findViewById(R.id.littlerock_neworleans_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b1).setAlpha(1);
        findViewById(R.id.littlerock_neworleans_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b2).setAlpha(1);
        findViewById(R.id.littlerock_neworleans_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_neworleans_g1b3).setAlpha(1);
    }

    public void change_color_dallas_houston_g2(View view) {
        findViewById(R.id.dallas_houston_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.dallas_houston_g2b1).setAlpha(1);

    }

    public void change_color_littlerock_dallas_g1(View view) {
        findViewById(R.id.littlerock_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_dallas_g1b1).setAlpha(1);
        findViewById(R.id.littlerock_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.littlerock_dallas_g1b2).setAlpha(1);
    }

    public void change_color_oklahomacity_dallas_g2(View view) {
        findViewById(R.id.oklahomacity_dallas_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g2b1).setAlpha(1);
        findViewById(R.id.oklahomacity_dallas_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g2b2).setAlpha(1);
    }

    public void change_color_oklahomacity_littlerock_g1(View view) {
        findViewById(R.id.oklahomacity_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_littlerock_g1b2).setAlpha(1);
    }

    public void change_color_saintlouis_nashville_g1(View view) {
        findViewById(R.id.saintlouis_nashville_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_nashville_g1b1).setAlpha(1);
        findViewById(R.id.saintlouis_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_nashville_g1b2).setAlpha(1);
    }

    public void change_color_saintlouis_littlerock_g1(View view) {
        findViewById(R.id.saintlouis_littlerock_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_littlerock_g1b1).setAlpha(1);
        findViewById(R.id.saintlouis_littlerock_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saintlouis_littlerock_g1b2).setAlpha(1);
    }

    public void change_color_kansascity_saintlouis_g2(View view) {//2
        findViewById(R.id.kansascity_saintlouis_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g2b1).setAlpha(1);
        findViewById(R.id.kansascity_saintlouis_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g2b2).setAlpha(1);
    }

    public void change_color_kansascity_saintlouis_g1(View view) {//2
        findViewById(R.id.kansascity_saintlouis_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g1b1).setAlpha(1);
        findViewById(R.id.kansascity_saintlouis_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_saintlouis_g1b2).setAlpha(1);
    }

    public void change_color_kansascity_oklahomacity_g2(View view) {//2
        findViewById(R.id.kansascity_oklahomacity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_oklahomacity_g2b1).setAlpha(1);
        findViewById(R.id.kansascity_oklahomacity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.kansascity_oklahomacity_g2b2).setAlpha(1);
    }

    public void change_color_omaha_kansascity_g2(View view) {//1
        findViewById(R.id.omaha_kansascity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_kansascity_g2b1).setAlpha(1);
    }

    public void change_color_neworleans_miami_g1(View view) {//6
        findViewById(R.id.neworleans_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b1).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b2).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b3).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b4).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b5).setAlpha(1);
        findViewById(R.id.neworleans_miami_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.neworleans_miami_g1b6).setAlpha(1);
    }

    public void change_color_charleston_miami_g1(View view) {//4
        findViewById(R.id.charleston_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b1).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b2).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b3).setAlpha(1);
        findViewById(R.id.charleston_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.charleston_miami_g1b4).setAlpha(1);
    }

    public void change_color_atlanta_charleston_g1(View view) {//2
        findViewById(R.id.atlanta_charleston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_charleston_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_charleston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_charleston_g1b2).setAlpha(1);
    }

    public void change_color_atlanta_miami_g1(View view) {//5
        findViewById(R.id.atlanta_miami_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b2).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b3).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b4).setAlpha(1);
        findViewById(R.id.atlanta_miami_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_miami_g1b5).setAlpha(1);

    }

    public void change_color_atlanta_neworleans_g2(View view) {//4
        findViewById(R.id.atlanta_neworleans_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b1).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b2).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b3).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g2b4).setAlpha(1);

    }

    public void change_color_atlanta_neworleans_g1(View view) {//4
        findViewById(R.id.atlanta_neworleans_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b1).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b2).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b3).setAlpha(1);
        findViewById(R.id.atlanta_neworleans_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.atlanta_neworleans_g1b4).setAlpha(1);
    }

    public void change_color_nashville_atlanta_g1(View view) {//1
        findViewById(R.id.nashville_atlanta_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.nashville_atlanta_g1b1).setAlpha(1);
    }

    public void change_color_raleigh_nashville_g1(View view) {//3
        findViewById(R.id.raleigh_nashville_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b1).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b2).setAlpha(1);
        findViewById(R.id.raleigh_nashville_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_nashville_g1b3).setAlpha(1);
    }

    public void change_color_pittsburgh_raleigh_g1(View view) {//2
        findViewById(R.id.pittsburgh_raleigh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_raleigh_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_raleigh_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_raleigh_g1b2).setAlpha(1);
    }

    public void change_color_pittsburgh_nashville_g1(View view) {//4
        findViewById(R.id.pittsburgh_nashville_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_nashville_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_nashville_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_nashville_g1b2).setAlpha(1);
        findViewById(R.id.pittsburgh_nashville_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_nashville_g1b3).setAlpha(1);
        findViewById(R.id.pittsburgh_nashville_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_nashville_g1b4).setAlpha(1);
    }

    public void change_color_pittsburgh_saintlouis_g1(View view) {//5
        findViewById(R.id.pittsburgh_saintlouis_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_saintlouis_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_saintlouis_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_saintlouis_g1b2).setAlpha(1);
        findViewById(R.id.pittsburgh_saintlouis_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_saintlouis_g1b3).setAlpha(1);
        findViewById(R.id.pittsburgh_saintlouis_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_saintlouis_g1b4).setAlpha(1);
        findViewById(R.id.pittsburgh_saintlouis_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_saintlouis_g1b5).setAlpha(1);

    }

    public void change_color_pittsburgh_chicago_g2(View view) {//3
        findViewById(R.id.pittsburgh_chicago_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g2b1).setAlpha(1);
        findViewById(R.id.pittsburgh_chicago_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g2b2).setAlpha(1);
        findViewById(R.id.pittsburgh_chicago_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g2b3).setAlpha(1);
    }

    public void change_color_pittsburgh_chicago_g1(View view) {//3
        findViewById(R.id.pittsburgh_chicago_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_chicago_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g1b2).setAlpha(1);
        findViewById(R.id.pittsburgh_chicago_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_chicago_g1b3).setAlpha(1);
    }

    public void change_color_chicago_saintlouis_g2(View view) {//2
        findViewById(R.id.chicago_saintlouis_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_saintlouis_g2b1).setAlpha(1);
        findViewById(R.id.chicago_saintlouis_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_saintlouis_g2b2).setAlpha(1);
    }

    public void change_color_chicago_saintlouis_g1(View view) {//2
        findViewById(R.id.chicago_saintlouis_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_saintlouis_g1b1).setAlpha(1);
        findViewById(R.id.chicago_saintlouis_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_saintlouis_g1b2).setAlpha(1);
    }

    public void change_color_chicago_omaha_g1(View view) {//4
        findViewById(R.id.chicago_omaha_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_omaha_g1b1).setAlpha(1);
        findViewById(R.id.chicago_omaha_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_omaha_g1b2).setAlpha(1);
        findViewById(R.id.chicago_omaha_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_omaha_g1b3).setAlpha(1);
        findViewById(R.id.chicago_omaha_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.chicago_omaha_g1b4).setAlpha(1);

    }

    public void change_color_duluth_chicago_g1(View view) {//3
        findViewById(R.id.duluth_chicago_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.duluth_chicago_g1b1).setAlpha(1);
        findViewById(R.id.duluth_chicago_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.duluth_chicago_g1b2).setAlpha(1);
        findViewById(R.id.duluth_chicago_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.duluth_chicago_g1b3).setAlpha(1);
    }

    public void change_color_duluth_omaha_g2(View view) {//2
        findViewById(R.id.duluth_omaha_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.duluth_omaha_g2b1).setAlpha(1);
        findViewById(R.id.duluth_omaha_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.duluth_omaha_g2b2).setAlpha(1);
    }

    public void change_color_saulstmarie_duluth_g1(View view) {//3
        findViewById(R.id.saulstmarie_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saulstmarie_duluth_g1b1).setAlpha(1);
        findViewById(R.id.saulstmarie_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saulstmarie_duluth_g1b2).setAlpha(1);
        findViewById(R.id.saulstmarie_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saulstmarie_duluth_g1b3).setAlpha(1);

    }

    public void change_color_winnipeg_saulstmarie_g1(View view) {//6
        findViewById(R.id.winnipeg_saulstmarie_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b1).setAlpha(1);
        findViewById(R.id.winnipeg_saulstmarie_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b2).setAlpha(1);
        findViewById(R.id.winnipeg_saulstmarie_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b3).setAlpha(1);
        findViewById(R.id.winnipeg_saulstmarie_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b4).setAlpha(1);
        findViewById(R.id.winnipeg_saulstmarie_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b5).setAlpha(1);
        findViewById(R.id.winnipeg_saulstmarie_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_saulstmarie_g1b6).setAlpha(1);
    }

    public void change_color_toronto_duluth_g1(View view) {//6
        findViewById(R.id.toronto_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b1).setAlpha(1);
        findViewById(R.id.toronto_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b2).setAlpha(1);
        findViewById(R.id.toronto_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b3).setAlpha(1);
        findViewById(R.id.toronto_duluth_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b4).setAlpha(1);
        findViewById(R.id.toronto_duluth_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b5).setAlpha(1);
        findViewById(R.id.toronto_duluth_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_duluth_g1b6).setAlpha(1);
    }

    public void change_color_toronto_pittsburgh_g1(View view) {//2
        findViewById(R.id.toronto_pittsburgh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_pittsburgh_g1b1).setAlpha(1);
        findViewById(R.id.toronto_pittsburgh_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_pittsburgh_g1b2).setAlpha(1);
    }

    public void change_color_toronto_chicago_g1(View view) {//4
        findViewById(R.id.toronto_chicago_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_chicago_g1b1).setAlpha(1);
        findViewById(R.id.toronto_chicago_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_chicago_g1b2).setAlpha(1);
        findViewById(R.id.toronto_chicago_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_chicago_g1b3).setAlpha(1);
        findViewById(R.id.toronto_chicago_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.toronto_chicago_g1b4).setAlpha(1);
    }

    public void change_color_saulstmarie_toronto_g1(View view) {//2
        findViewById(R.id.saulstmarie_toronto_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saulstmarie_toronto_g1b1).setAlpha(1);
        findViewById(R.id.saulstmarie_toronto_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saulstmarie_toronto_g1b2).setAlpha(1);
    }

    public void change_color_montreal_saulstmarie_g1(View view) {//5
        findViewById(R.id.montreal_saulstmarie_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_saulstmarie_g1b1).setAlpha(1);
        findViewById(R.id.montreal_saulstmarie_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_saulstmarie_g1b2).setAlpha(1);
        findViewById(R.id.montreal_saulstmarie_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_saulstmarie_g1b3).setAlpha(1);
        findViewById(R.id.montreal_saulstmarie_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_saulstmarie_g1b4).setAlpha(1);
        findViewById(R.id.montreal_saulstmarie_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_saulstmarie_g1b5).setAlpha(1);

    }

    public void change_color_montreal_toronto_g1(View view) {//3
        findViewById(R.id.montreal_toronto_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_toronto_g1b1).setAlpha(1);
        findViewById(R.id.montreal_toronto_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_toronto_g1b2).setAlpha(1);
        findViewById(R.id.montreal_toronto_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_toronto_g1b3).setAlpha(1);
    }

    public void change_color_raleigh_atlanta_g2(View view) {//2
        findViewById(R.id.raleigh_atlanta_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_atlanta_g2b1).setAlpha(1);
        findViewById(R.id.raleigh_atlanta_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_atlanta_g2b2).setAlpha(1);
    }

    public void change_color_raleigh_atlanta_g1(View view) {//2
        findViewById(R.id.raleigh_atlanta_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_atlanta_g1b1).setAlpha(1);
        findViewById(R.id.raleigh_atlanta_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_atlanta_g1b2).setAlpha(1);
    }

    public void change_color_raleigh_charleston_g1(View view) {//2
        findViewById(R.id.raleigh_charleston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_charleston_g1b1).setAlpha(1);
        findViewById(R.id.raleigh_charleston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.raleigh_charleston_g1b2).setAlpha(1);
    }

    public void change_color_washington_raleigh_g2(View view) {//2
        findViewById(R.id.washington_raleigh_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.washington_raleigh_g2b1).setAlpha(1);
        findViewById(R.id.washington_raleigh_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.washington_raleigh_g2b2).setAlpha(1);
    }

    public void change_color_washington_raleigh_g1(View view) {//2
        findViewById(R.id.washington_raleigh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.washington_raleigh_g1b1).setAlpha(1);
        findViewById(R.id.washington_raleigh_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.washington_raleigh_g1b2).setAlpha(1);
    }

    public void change_color_pittsburgh_washington_g1(View view) {//2
        findViewById(R.id.pittsburgh_washington_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_washington_g1b1).setAlpha(1);
        findViewById(R.id.pittsburgh_washington_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.pittsburgh_washington_g1b2).setAlpha(1);
    }

    public void change_color_newyork_washington_g2(View view) {//2
        findViewById(R.id.newyork_washington_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_washington_g2b1).setAlpha(1);
        findViewById(R.id.newyork_washington_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_washington_g2b2).setAlpha(1);
    }

    public void change_color_newyork_washington_g1(View view) {//2
        findViewById(R.id.newyork_washington_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_washington_g1b1).setAlpha(1);
        findViewById(R.id.newyork_washington_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_washington_g1b2).setAlpha(1);
    }

    public void change_color_newyork_pittsburgh_g2(View view) {//2
        findViewById(R.id.newyork_pittsburgh_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_pittsburgh_g2b1).setAlpha(1);
        findViewById(R.id.newyork_pittsburgh_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_pittsburgh_g2b2).setAlpha(1);
    }

    public void change_color_newyork_pittsburgh_g1(View view) {//2
        findViewById(R.id.newyork_pittsburgh_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_pittsburgh_g1b1).setAlpha(1);
        findViewById(R.id.newyork_pittsburgh_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.newyork_pittsburgh_g1b2).setAlpha(1);
    }

    public void change_color_boston_newyork_g2(View view) {//2
        findViewById(R.id.boston_newyork_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.boston_newyork_g2b1).setAlpha(1);
        findViewById(R.id.boston_newyork_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.boston_newyork_g2b2).setAlpha(1);
    }

    public void change_color_boston_newyork_g1(View view) {//2
        findViewById(R.id.boston_newyork_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.boston_newyork_g1b1).setAlpha(1);
        findViewById(R.id.boston_newyork_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.boston_newyork_g1b2).setAlpha(1);
    }

    public void change_color_montreal_boston_g2(View view) {//2
        findViewById(R.id.montreal_boston_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_boston_g2b1).setAlpha(1);
        findViewById(R.id.montreal_boston_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_boston_g2b2).setAlpha(1);
    }

    public void change_color_montreal_boston_g1(View view) {//2
        findViewById(R.id.montreal_boston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_boston_g1b1).setAlpha(1);
        findViewById(R.id.montreal_boston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_boston_g1b2).setAlpha(1);
    }

    public void change_color_montreal_newyork_g1(View view) {//3
        findViewById(R.id.montreal_newyork_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_newyork_g1b1).setAlpha(1);
        findViewById(R.id.montreal_newyork_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_newyork_g1b2).setAlpha(1);
        findViewById(R.id.montreal_newyork_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.montreal_newyork_g1b3).setAlpha(1);
    }

    public void change_color_portland_sanfransisco_g2(View v) {
        findViewById(R.id.portland_sanfransisco_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b1).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b2).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b3).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b4).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g2b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g2b5).setAlpha(1);
    }

    public void change_color_vancouver_seattle_g1(View view) {
        findViewById(R.id.vancouver_seattle_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_seattle_g1b1).setAlpha(1);
    }

    public void change_color_vancouver_seattle_g2(View view) {
        findViewById(R.id.vancouver_seattle_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_seattle_g2b1).setAlpha(1);
    }

    public void change_color_seattle_portland_g1(View view) {
        findViewById(R.id.seattle_portland_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_portland_g1b1).setAlpha(1);
    }

    public void change_color_seattle_portland_g2(View view) {
        findViewById(R.id.seattle_portland_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_portland_g2b1).setAlpha(1);
    }

    public void change_color_portland_sanfransisco_g1(View view) {
        findViewById(R.id.portland_sanfransisco_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b1).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b2).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b3).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b4).setAlpha(1);
        findViewById(R.id.portland_sanfransisco_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_sanfransisco_g1b5).setAlpha(1);
    }

    public void change_color_sanfransisco_losangeles_g1(View view) {
        findViewById(R.id.sanfransisco_losangeles_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b1).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b2).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g1b3).setAlpha(1);
    }

    public void change_color_sanfransisco_losangeles_g2(View view) {
        findViewById(R.id.sanfransisco_losangeles_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b1).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b2).setAlpha(1);
        findViewById(R.id.sanfransisco_losangeles_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_losangeles_g2b3).setAlpha(1);
    }

    public void change_color_losangeles_elpaso_g1(View view) {
        findViewById(R.id.losangeles_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b2).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b3).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b4).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b5).setAlpha(1);
        findViewById(R.id.losangeles_elpaso_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_elpaso_g1b6).setAlpha(1);
    }

    public void change_color_vancouver_calgary_g1(View view) {
        findViewById(R.id.vancouver_calgary_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b1).setAlpha(1);
        findViewById(R.id.vancouver_calgary_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b2).setAlpha(1);
        findViewById(R.id.vancouver_calgary_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.vancouver_calgary_g1b3).setAlpha(1);
    }

    public void change_color_seattle_calgary_g1(View view) {
        findViewById(R.id.seattle_calgary_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b1).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b2).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b3).setAlpha(1);
        findViewById(R.id.seattle_calgary_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_calgary_g1b4).setAlpha(1);
    }

    public void change_color_seattle_helena_g1(View view) {
        findViewById(R.id.seattle_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b1).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b2).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b3).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b4).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b5).setAlpha(1);
        findViewById(R.id.seattle_helena_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.seattle_helena_g1b6).setAlpha(1);
    }

    public void change_color_calgary_winnipeg_g1(View view) {
        findViewById(R.id.calgary_winnipeg_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b1).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b2).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b3).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b4).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b5).setAlpha(1);
        findViewById(R.id.calgary_winnipeg_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_winnipeg_g1b6).setAlpha(1);
    }

    public void change_color_calgary_helena_g1(View view) {
        findViewById(R.id.calgary_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b1).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b2).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b3).setAlpha(1);
        findViewById(R.id.calgary_helena_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.calgary_helena_g1b4).setAlpha(1);
    }

    public void change_color_helena_winnipeg_g1(View view) {
        findViewById(R.id.helena_winnipeg_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b1).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b2).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b3).setAlpha(1);
        findViewById(R.id.helena_winnipeg_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_winnipeg_g1b4).setAlpha(1);
    }

    public void change_color_portland_saltlakecity_g1(View view) {
        findViewById(R.id.portland_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b3).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b4).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b5).setAlpha(1);
        findViewById(R.id.portland_saltlakecity_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.portland_saltlakecity_g1b6).setAlpha(1);
    }

    public void change_color_sanfransisco_saltlakecity_g1(View view) {
        findViewById(R.id.sanfransisco_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b3).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b4).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g1b5).setAlpha(1);
    }

    public void change_color_sanfransisco_saltlakecity_g2(View view) {
        findViewById(R.id.sanfransisco_saltlakecity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b1).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b2).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b3).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b4).setAlpha(1);
        findViewById(R.id.sanfransisco_saltlakecity_g2b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.sanfransisco_saltlakecity_g2b5).setAlpha(1);
    }

    public void change_color_losangeles_lasvegas_g1(View view) {
        findViewById(R.id.losangeles_lasvegas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_lasvegas_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_lasvegas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_lasvegas_g1b2).setAlpha(1);
    }

    public void change_color_lasvegas_saltlakecity_g1(View view) {
        findViewById(R.id.lasvegas_saltlakecity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b1).setAlpha(1);
        findViewById(R.id.lasvegas_saltlakecity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b2).setAlpha(1);
        findViewById(R.id.lasvegas_saltlakecity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.lasvegas_saltlakecity_g1b3).setAlpha(1);
    }

    public void change_color_losangeles_phoenix_g1(View view) {
        findViewById(R.id.losangeles_phoenix_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b1).setAlpha(1);
        findViewById(R.id.losangeles_phoenix_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b2).setAlpha(1);
        findViewById(R.id.losangeles_phoenix_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.losangeles_phoenix_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_elpaso_g1(View view) {
        findViewById(R.id.phoenix_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_elpaso_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_elpaso_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_santafe_g1(View view) {
        findViewById(R.id.phoenix_santafe_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_santafe_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_santafe_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_santafe_g1b3).setAlpha(1);
    }

    public void change_color_phoenix_denver_g1(View view) {
        findViewById(R.id.phoenix_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b1).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b2).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b3).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b4).setAlpha(1);
        findViewById(R.id.phoenix_denver_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.phoenix_denver_g1b5).setAlpha(1);
    }

    public void change_color_saltlakecity_denver_g1(View view) {
        findViewById(R.id.saltlakecity_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b1).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b2).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g1b3).setAlpha(1);
    }

    public void change_color_saltlakecity_denver_g2(View view) {
        findViewById(R.id.saltlakecity_denver_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b1).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b2).setAlpha(1);
        findViewById(R.id.saltlakecity_denver_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_denver_g2b3).setAlpha(1);
    }

    public void change_color_saltlakecity_helena_g1(View view) {
        findViewById(R.id.saltlakecity_helena_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b1).setAlpha(1);
        findViewById(R.id.saltlakecity_helena_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b2).setAlpha(1);
        findViewById(R.id.saltlakecity_helena_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.saltlakecity_helena_g1b3).setAlpha(1);
    }

    public void change_color_helena_denver_g1(View view) {
        findViewById(R.id.helena_denver_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b1).setAlpha(1);
        findViewById(R.id.helena_denver_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b2).setAlpha(1);
        findViewById(R.id.helena_denver_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b3).setAlpha(1);
        findViewById(R.id.helena_denver_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_denver_g1b4).setAlpha(1);
    }

    public void change_color_helena_duluth_g1(View view) {
        findViewById(R.id.helena_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b1).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b2).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b3).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b4).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b5).setAlpha(1);
        findViewById(R.id.helena_duluth_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_duluth_g1b6).setAlpha(1);
    }

    public void change_color_helena_omaha_g1(View view) {
        findViewById(R.id.helena_omaha_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b1).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b2).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b3).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b4).setAlpha(1);
        findViewById(R.id.helena_omaha_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.helena_omaha_g1b5).setAlpha(1);
    }

    public void change_color_omaha_duluth_g1(View view) {
        findViewById(R.id.omaha_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_duluth_g1b1).setAlpha(1);
        findViewById(R.id.omaha_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_duluth_g1b2).setAlpha(1);
    }

    public void change_color_denver_omaha_g1(View view) {
        findViewById(R.id.denver_omaha_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b1).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b2).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b3).setAlpha(1);
        findViewById(R.id.denver_omaha_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_omaha_g1b4).setAlpha(1);
    }

    public void change_color_denver_kansascity_g1(View view) {
        findViewById(R.id.denver_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b1).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b2).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b3).setAlpha(1);
        findViewById(R.id.denver_kansascity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g1b4).setAlpha(1);
    }

    public void change_color_denver_kansascity_g2(View view) {
        findViewById(R.id.denver_kansascity_g2b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b1).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b2).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b3).setAlpha(1);
        findViewById(R.id.denver_kansascity_g2b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_kansascity_g2b4).setAlpha(1);
    }

    public void change_color_denver_oklahomacity_g1(View view) {
        findViewById(R.id.denver_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b3).setAlpha(1);
        findViewById(R.id.denver_oklahomacity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_oklahomacity_g1b4).setAlpha(1);
    }

    public void change_color_denver_santafe_g1(View view) {
        findViewById(R.id.denver_santafe_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_santafe_g1b1).setAlpha(1);
        findViewById(R.id.denver_santafe_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.denver_santafe_g1b2).setAlpha(1);
    }

    public void change_color_santafe_elpaso_g1(View view) {
        findViewById(R.id.santafe_elpaso_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_elpaso_g1b1).setAlpha(1);
        findViewById(R.id.santafe_elpaso_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_elpaso_g1b2).setAlpha(1);
    }

    public void change_color_santafe_oklahomacity_g1(View view) {
        findViewById(R.id.santafe_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.santafe_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.santafe_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.santafe_oklahomacity_g1b3).setAlpha(1);
    }

    public void change_color_elpaso_oklahomacity_g1(View view) {
        findViewById(R.id.elpaso_oklahomacity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b4).setAlpha(1);
        findViewById(R.id.elpaso_oklahomacity_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_oklahomacity_g1b5).setAlpha(1);
    }

    public void change_color_elpaso_dallas_g1(View view) {
        findViewById(R.id.elpaso_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_dallas_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_dallas_g1b4).setAlpha(1);
    }

    public void change_color_elpaso_houston_g1(View view) {
        findViewById(R.id.elpaso_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b1).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b2).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b3).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b4).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b5).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b5).setAlpha(1);
        findViewById(R.id.elpaso_houston_g1b6).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.elpaso_houston_g1b6).setAlpha(1);
    }

    public void change_color_winnipeg_duluth_g1(View view) {
        findViewById(R.id.winnipeg_duluth_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b1).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b2).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b3).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b3).setAlpha(1);
        findViewById(R.id.winnipeg_duluth_g1b4).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.winnipeg_duluth_g1b4).setAlpha(1);
    }

    public void change_color_omaha_kansascity_g1(View view) {
        findViewById(R.id.omaha_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.omaha_kansascity_g1b1).setAlpha(1);
    }

    public void change_color_oklahomacity_kansascity_g1(View view) {
        findViewById(R.id.oklahomacity_kansascity_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_kansascity_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_kansascity_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_kansascity_g1b2).setAlpha(1);
    }

    public void change_color_oklahomacity_dallas_g1(View view) {
        findViewById(R.id.oklahomacity_dallas_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g1b1).setAlpha(1);
        findViewById(R.id.oklahomacity_dallas_g1b2).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.oklahomacity_dallas_g1b2).setAlpha(1);
    }

    public void change_color_dallas_houston_g1(View view) {
        findViewById(R.id.dallas_houston_g1b1).setBackgroundResource((int)playerColorValues.get(playersHandPresenter.getCurrentPlayerColor()));
        findViewById(R.id.dallas_houston_g1b1).setAlpha(1);
    }


    public static class UpdateAsyncTask extends AsyncTask<Void, Void, Void> {

        //Empty constructor
        public UpdateAsyncTask() {
        }

        /**
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         */

        @Override
        protected Void doInBackground(Void... result) {
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            adapter.setListOfMessages(chatMessages);
            adapter.notifyDataSetChanged();
            chatMessages = chatPresenter.getMessages();

            //FIXME: GET THE ARRAYLIST OF OLD DESTINATION CARDS TO ALSO SHOW IN THE RECYCLERVIEW
            destinationCardList = playerInfoPresenter.getNewDestinationCardStrings();
            destinationCardsAdapter.setListOfDestinationCards(destinationCardList);
            destinationCardsAdapter.notifyDataSetChanged();


            mGreenTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(1));
            mRedTrainCard.setText(""+ playersHandPresenter.getTrainCardAmount(2));
            mPinkTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(6));
            mYellowTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(3));
            mWhiteTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(7));
            mBlackTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(8));
            mWildTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(9));
            mBlueTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(4));
            mOrangeTrainCard.setText("" + playersHandPresenter.getTrainCardAmount(5));
            destinationCardDeck.setText("" + cardDeckPresenter.getDestinationCardsLeft());
            trainCardDeck.setText("" + cardDeckPresenter.getTrainCardsLeft());
            cardFive.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(5)));
            cardFour.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(4)));
            cardThree.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(3)));
            cardTwo.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(2)));
            cardOne.setBackgroundResource((int)trainCardImages.get(cardDeckPresenter.getTrainCardAtPosition(1)));

            for (int i = 0; i < playerInfoPresenter.getNumOfPlayers(); i++) {
                Player player = playerInfoPresenter.getPlayerByOrder(i);
                if (player == null) {
                    break;
                }
                int destCardSize;
                if (player.getDestinationCardHand() == null) {
                    destCardSize = 0;
                }
                else {
                    destCardSize = player.getDestinationCardHand().size();
                }
                if (i == 0) {
                    one_destinationCards.setText("Destination Cards: " + destCardSize);
                    one_score.setText("Score: " + player.getScore());
                    one_trainCards.setText("Train Cards: " + player.getTickets().values().size());
                    one_trainsLeft.setText("Trains Left: " + player.getTrainsRemaining());
                }
                else if (i == 1) {
                    two_destinationCards.setText("Destination Cards: " + destCardSize);
                    two_score.setText("Score: " + player.getScore());
                    two_trainCards.setText("Train Cards: " + player.getTickets().values().size());
                    two_trainsLeft.setText("Trains Left: " + player.getTrainsRemaining());
                }
                else if (i == 2) {
                    three_destinationCards.setText("Destination Cards: " + destCardSize);
                    three_score.setText("Score: " + player.getScore());
                    three_trainCards.setText("Train Cards: " + player.getTickets().values().size());
                    three_trainsLeft.setText("Trains Left: " + player.getTrainsRemaining());
                }
                else if (i == 3) {
                    four_destinationCards.setText("Destination Cards: " + destCardSize);
                    four_score.setText("Score: " + player.getScore());
                    four_trainCards.setText("Train Cards: " + player.getTickets().values().size());
                    four_trainsLeft.setText("Trains Left: " + player.getTrainsRemaining());
                }
                else if (i == 4) {
                    five_destinationCards.setText("Destination Cards: " + destCardSize);
                    five_score.setText("Score: " + player.getScore());
                    five_trainCards.setText("Train Cards: " + player.getTickets().values().size());
                    five_trainsLeft.setText("Trains Left: " + player.getTrainsRemaining());
                }
            }
        }
    }
}