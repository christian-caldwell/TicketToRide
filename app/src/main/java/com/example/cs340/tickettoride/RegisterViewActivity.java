package com.example.cs340.tickettoride;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterViewActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private EditText passwordChecker;
    private Button registerButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        passwordChecker = findViewById(R.id.password_checker);
        registerButton = findViewById(R.id.register_button);
        cancelButton = findViewById(R.id.cancel_button);


        // Login button is pushed
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.equals(password.getText(), passwordChecker.getText())) {
                    Intent intent = new Intent(RegisterViewActivity.this, LobbyViewActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(RegisterViewActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }



            }
        });

        // Register button is pushed
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterViewActivity.this, LoginViewActivity.class);
                startActivity(intent);
            }
        });
    }
}