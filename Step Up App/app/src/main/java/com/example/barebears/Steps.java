package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Steps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        getSupportActionBar().hide();
    }
}