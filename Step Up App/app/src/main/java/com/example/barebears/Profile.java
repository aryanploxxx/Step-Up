package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    private float height,weight,bmi;
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

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                height=Float.parseFloat(th.getText().toString())/100;
                weight=Float.parseFloat(tw.getText().toString());

                bmi=weight/height/height;
                System.out.println("BMI= "+height);
                System.out.println("BMI= "+weight);
                System.out.println("BMI= "+bmi);
            }
        });
    }
}