package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PasswordsActivity extends AppCompatActivity {

    ArrayList<Password> passwords = new ArrayList<>();
    DatabaseReference databasePasswords;
    ListView listView;
    PasswordAdapter passwordAdapter;

    void println(Object line) {
        System.out.println(line);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);

        listView = findViewById(R.id.list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasePasswords = database.getReference().child("passwords");

        passwordAdapter = new PasswordAdapter(this, passwords);
        listView.setAdapter(passwordAdapter);

        databasePasswords.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                passwords.add(dataSnapshot.getValue(Password.class));
                passwordAdapter.notifyDataSetChanged();
                println("Pass Act: " + passwords);
            }

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
}
