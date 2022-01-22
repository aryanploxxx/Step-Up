package com.example.barebears;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Steps extends AppCompatActivity implements SensorEventListener {

    private ProgressBar progressBar;
    private TextView progressText,time,steps;
    private ToggleButton t;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    SensorManager sensorManager;
    boolean running=false;
    int i = 0,target=20,step_count,calories,points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        int target = intent.getIntExtra("target",0);

        step_count=calories=points=0;

        if(false)//user data found
        {
            target=target;
        }
        else//user data not found
        {
            //register karna hai
            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference("users");

            UserhelperClass helperClass=new UserhelperClass(username,target,step_count,calories,points);


            reference.child(username).setValue(helperClass);
        }

        // set the id for the progressbar and progress text
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        time = findViewById(R.id.textView13);
        t=findViewById(R.id.toggleButton);

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t.isChecked())
                {
                    time.setText("started");
                }
                else
                {
                    time.setText("paused");
                }
            }
        });

//        final Handler handler = new Handler();
//
//        handler.postDelayed(new Runnable()
//        {
//            @Override
//            public void run() {
//                // set the limitations for the numeric
//                // text under the progress bar
//                if (i <= target) {
//                    progressText.setText("" + i);
//                    progressBar.setProgress(i);
//                    i++;
//                    handler.postDelayed(this, 200);
//                } else {
//                    handler.removeCallbacks(this);
//                }
//            }
//        }, 200);


    }


    @Override
    protected void onResume() {
        super.onResume();
        running=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this,countSensor, SensorManager.SENSOR_DELAY_UI);
        }
        else
        {
            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        running=false;
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(running){
            progressText.setText(String.valueOf(event.values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}