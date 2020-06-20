package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class GeneratorActivity extends AppCompatActivity {

    TextView generatorTextView;
    EditText websiteEditText;
    Button generateButton;
    TextView generatorMessageTextView;

    DatabaseReference databasePasswords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        generatorTextView = findViewById(R.id.generatorTextView);
        websiteEditText = findViewById(R.id.websiteEditText);
        generateButton = findViewById(R.id.generateButton);
        generatorMessageTextView = findViewById(R.id.generatorMessageTextView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePasswords = database.getReference().child("passwords");

        generatorMessageTextView.setText("");
    }

    public void generateButtonPressed(View view) {
        generatorMessageTextView.setTextColor(Color.RED);

        String website = websiteEditText.getText().toString().trim();

        if(website == "") {
            generatorMessageTextView.setText("Please enter in a website.");
        }
        /*if(website == ){

        }*/
    }

    public void generatePassword() {

    }

    public void displayError() {

    }
}
