package com.example.cs340.tickettoride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import view.presenter.LoginPresenter;

public class LoginViewActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.game_name);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.create_game);

        // Login button is pushed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginViewActivity.this, LobbyViewActivity.class);
                startActivity(intent);

                LoginPresenter loginPresenter = new LoginPresenter();
                loginPresenter.loginUser(username.getText().toString(), password.getText().toString());

            }
        });

        // Register button is pushed
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginViewActivity.this, RegisterViewActivity.class);
                startActivity(intent);
            }
        });
    }



}
