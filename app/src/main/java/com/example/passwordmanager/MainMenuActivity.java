package com.example.passwordmanager;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    TextView description1;
    TextView description2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        description1 = findViewById(R.id.description1);
        description2 = findViewById(R.id.description2);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        description1.setMaxWidth((int) (0.8*width));
        description2.setMaxWidth((int) (0.8*width));
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