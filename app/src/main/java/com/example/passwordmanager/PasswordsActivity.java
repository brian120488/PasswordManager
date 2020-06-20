package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PasswordsActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);

        list = findViewById(R.id.list);
        ArrayList<Password> arr = new ArrayList<>();
        //arr.add(new Password());
        PasswordAdapter adapter = new PasswordAdapter(this, arr);
        list.setAdapter(adapter);
    }
}
