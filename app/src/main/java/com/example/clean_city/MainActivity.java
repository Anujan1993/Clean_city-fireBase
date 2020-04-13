package com.example.clean_city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    UserLocalStorage userLocalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLocalStorage = new UserLocalStorage(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate()==true){

        }
    }

    private boolean authenticate(){
        return userLocalStorage.getUserLockedIn();
    }
}
