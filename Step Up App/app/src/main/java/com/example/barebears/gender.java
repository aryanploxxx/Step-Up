package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class gender extends AppCompatActivity {

    private ImageButton b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        getSupportActionBar().hide();

        b1=findViewById(R.id.imageButton);
        b2=findViewById(R.id.imageButton2);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(gender.this,Profile.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(gender.this,Profile.class);
                intent1.putExtra("username",username);
                startActivity(intent1);
            }
        });
    }
}