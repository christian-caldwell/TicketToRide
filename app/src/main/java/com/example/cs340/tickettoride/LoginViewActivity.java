package com.example.cs340.tickettoride;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import models.data.Result;
import view.activityInterface.ILoginView;
import view.activityInterface.IRegisterView;
import view.presenter.LoginPresenter;

public class LoginViewActivity extends AppCompatActivity implements ILoginView, IRegisterView {

    private EditText username;
    private EditText password;
    private Button registerButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        username = findViewById(R.id.username);
        password = findViewById(R.id.game_name);
        registerButton = findViewById(R.id.register_button);
        loginButton = findViewById(R.id.create_game);

        // Login button is pushed
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginPresenter loginPresenter = new LoginPresenter();
                Result result = loginPresenter.loginUser(username.getText().toString(), password.getText().toString());
                if (result.isSuccessful()){
                    Intent intent = new Intent(LoginViewActivity.this, LobbyViewActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginViewActivity.this, "Successfully logged in " + username, Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(LoginViewActivity.this, result.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
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


    @Override
    public void updateServerStatus(String status) {

    }

    @Override
    public void sendResponse(String message) {

    }

    @Override
    public void notifyRegisterStarted() {

    }

    @Override
    public void notifyLoginStarted() {

    }
}
