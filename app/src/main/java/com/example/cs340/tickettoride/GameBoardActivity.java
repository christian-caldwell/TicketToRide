package com.example.cs340.tickettoride;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import models.data.ChatMessage;
import view.presenter.ChatPresenter;
import view.presenterInterface.IChatPresenter;

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
        View decorView = getWindow().getDecorView();
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        presenter = new ChatPresenter();


        initRecyclerView();

    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);
        chatMessages = presenter.getMessages();
        adapter = new ChatRecyclerViewAdapter(chatMessages);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.ViewHolder> {

        private ArrayList<ChatMessage> listOfMessages = new ArrayList<>();

        public ChatRecyclerViewAdapter(ArrayList<ChatMessage> listOfMessages) {
            this.listOfMessages = listOfMessages;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layoutlist_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            holder.currentMessage.setText(listOfMessages.get(position).getMessageContents());
            holder.timestamp.setText(listOfMessages.get(position).getTimeStamp());
            holder.user.setText(presenter.getSenderName());
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    // do nothing
                }
            });
        }

        @Override
        public int getItemCount() {
            return listOfMessages.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView user, currentMessage, timestamp;
            ConstraintLayout parentLayout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                user = itemView.findViewById(R.id.gameName);
                currentMessage = itemView.findViewById(R.id.currentNumOfPlayers);
                timestamp = itemView.findViewById(R.id.currentNumOfPlayers);
                //TODO: add parent layout.
                //parentLayout = itemView.findViewById(R.id.parent_layout_chat);
            }
        }
    }


}