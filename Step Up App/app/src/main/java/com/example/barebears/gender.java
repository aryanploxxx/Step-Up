package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class gender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        getSupportActionBar().hide();
    }
}