package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    private float height,weight,bmi;
    private int target;
    private Button b;
    private EditText th,tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();

        b=findViewById(R.id.button);
        th=findViewById(R.id.editTextTextPersonName2);
        tw=findViewById(R.id.editTextTextPersonName5);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                height=Float.parseFloat(th.getText().toString())/100;
                weight=Float.parseFloat(tw.getText().toString());

                bmi=weight/height/height;

                if(bmi<10.0)
                {
                    target=10;
                }
                else if(bmi<20.0)
                {
                    target=20;
                }
                else if(bmi<30.0)
                {
                    target=40;
                }
                else
                {
                    target=50;
                }



                Intent intent1= new Intent(Profile.this,Steps.class);
                intent1.putExtra("username",username);
                intent1.putExtra("target",target);
                startActivity(intent1);
            }
        });
    }
}