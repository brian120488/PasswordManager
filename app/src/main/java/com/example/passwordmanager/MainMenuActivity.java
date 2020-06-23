package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void goToPasswords(View view) {
        Intent intent = new Intent(this, PasswordsActivity.class);
        startActivity(intent);
    }

    public void goToHome(View view) {

    }

    public void goToGenerator(View view) {
        Intent intent = new Intent(this, GeneratorActivity.class);
        startActivity(intent);
    }
}