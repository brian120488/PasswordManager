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

public class GeneratorActivity extends AppCompatActivity {

    TextView generatorTextView;
    View websiteDivider;
    EditText websiteEditText;
    Button generateButton;
    TextView generatorMessageTextView;
    EditText passwordLengthEditText;
    EditText passwordUpperEditText;
    EditText passwordDigitsEditText;
    EditText passwordPunctuationEditText;


    ArrayList<Password> passwords = new ArrayList<>();

    DatabaseReference databasePasswords;

    void println(Object line) {
        System.out.println(line);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        generatorTextView = findViewById(R.id.generator_text_view);
        websiteDivider = findViewById(R.id.website_divider);
        websiteEditText = findViewById(R.id.website_edit_text);
        generateButton = findViewById(R.id.generate_button);
        generatorMessageTextView = findViewById(R.id.generator_message_text_view);
        passwordLengthEditText = findViewById(R.id.password_length_edit_text);
        passwordUpperEditText = findViewById(R.id.password_upper_edit_text);
        passwordDigitsEditText = findViewById(R.id.password_digits_edit_text);
        passwordPunctuationEditText = findViewById(R.id.password_punctuation_edit_text);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePasswords = database.getReference().child("passwords");

        generatorMessageTextView.setText("");

        databasePasswords.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                passwords.add(dataSnapshot.getValue(Password.class));
                println("Gen Activity: " + passwords);
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
        String length = passwordLengthEditText.getText().toString().trim();
        String upper = passwordUpperEditText.getText().toString().trim();
        String digits = passwordDigitsEditText.getText().toString().trim();
        String punctuation = passwordPunctuationEditText.getText().toString().trim();

        String message = checkWebsite(website);

        if(message != "") {
            generatorMessageTextView.setTextColor(Color.RED);
            generatorMessageTextView.setText(message);
        }
        else if(length.matches("") || upper.matches("") || digits.matches("") || punctuation.matches("")) {
            generatorMessageTextView.setTextColor(Color.RED);
            generatorMessageTextView.setText("Password information not completely filled out.");
        }
        else if(!isNum(length) || !isNum(upper) || !isNum(digits) || !isNum(punctuation)) {
            generatorMessageTextView.setTextColor(Color.RED);
            generatorMessageTextView.setText("Please use numbers in the password information");
        }
        else if(Integer.parseInt(length) < Integer.parseInt(upper) + Integer.parseInt(digits) + Integer.parseInt(punctuation)) {
            generatorMessageTextView.setTextColor(Color.RED);
            generatorMessageTextView.setText("Password length is too small.");
        }
        else {
            databasePasswords.push().setValue(new Password(new PasswordGenerator(Integer.parseInt(length), Integer.parseInt(upper), Integer.parseInt(digits), Integer.parseInt(punctuation)).getPassword(), website));
            websiteEditText.setText("");
            generatorMessageTextView.setTextColor(Color.GREEN);
            generatorMessageTextView.setText("Password created for " + website);
        }
    }

    public String checkWebsite(String website) {
        if(website.matches("")) {
            return "Please enter in a website.";
        }
        for(int i = 0; i < passwords.size(); i++) {
            if(passwords.get(i).getSite().matches(website)) {
                return "You already have a password created for this website";
            }
        }
        return "";
    }

    public static boolean isNum(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
