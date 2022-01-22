package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();

        b1=findViewById(R.id.button3);
        b2=findViewById(R.id.button2);

        //continue with google
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (LoginPage.this,Profile.class);
                startActivity(intent);
            }
        });

        //continue with custom email
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (LoginPage.this,Profile.class);
                startActivity(intent);
            }
        });
    }

}