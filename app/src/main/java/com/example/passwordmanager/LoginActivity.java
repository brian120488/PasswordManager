package com.example.passwordmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    public static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    //user is signed in
                    Toast.makeText(LoginActivity.this, "Signed in.", Toast.LENGTH_LONG).show();
                } else {
                    //user is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.EmailBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);

                }


            }
        };

        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if(authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        firebaseAuth.addAuthStateListener(authStateListener);
    }
}