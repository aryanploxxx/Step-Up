package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();
        b=findViewById(R.id.button2);



        //continue with custom email
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (LoginPage.this,SignIn.class);
                startActivity(intent);
            }
        });
    }

}