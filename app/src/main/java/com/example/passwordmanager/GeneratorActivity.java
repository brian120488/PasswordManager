package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Hashtable;

public class GeneratorActivity extends AppCompatActivity {

    TextView generatorTextView;
    EditText websiteEditText;
    Button generateButton;
    TextView generatorMessageTextView;


    ArrayList<Password> passwords = new ArrayList<>();

    DatabaseReference databasePasswords;

    PasswordGenerator passwordGenerator = new PasswordGenerator();

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

        databasePasswords.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                passwords.add(dataSnapshot.getValue(Password.class));
                System.out.println(passwords);
            }

            //option to regenerate/change password?
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });
    }

    public void generateButtonPressed(View view) {
        String website = websiteEditText.getText().toString().trim();
        String message = checkWebsite(website);

        if(message != "") {
            generatorMessageTextView.setTextColor(Color.RED);
            generatorMessageTextView.setText(message);
        }
        else {
            databasePasswords.push().setValue(new Password(passwordGenerator.getPassword(), website));
            generatorMessageTextView.setTextColor(Color.GREEN);
            generatorMessageTextView.setText("Password created for " + website);
        }
    }

    public String checkWebsite(String website) {
        if(website.matches("")) {
            return "Please enter in a website.";
        }
        for(int i = 0; i < passwords.size(); i++) {
            System.out.println(passwords.get(i).getSite());
            System.out.println("Website: " + website);
            if(passwords.get(i).getSite().matches(website)) {
                return "You already have a password created for this website";
            }
        }
        return "";
    }
}
