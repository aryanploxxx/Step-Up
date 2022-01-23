package com.example.barebears;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;

public class Steps extends AppCompatActivity implements SensorEventListener {


    private TextView pedoMeter,pointMeter,dailytarget,calMeter,result;
    private Button redeem;
    String username="";

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    SensorManager sensorManager;
    boolean running=false;
    int f = 0,target=20,step_count,calories,points,initial,DBstep_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);

        getSupportActionBar().hide();

        pedoMeter = findViewById(R.id.progress_text);
        pointMeter = findViewById(R.id.textView13);
        dailytarget = findViewById(R.id.textView7);
        calMeter = findViewById(R.id.textView11);
        result = findViewById(R.id.textView10);
        redeem = findViewById(R.id.button5);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        target = intent.getIntExtra("target",0);

        step_count=calories=points=0;

        rootNode=FirebaseDatabase.getInstance();
        reference=rootNode.getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(username);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists())
                {
                    UserhelperClass helperClass=new UserhelperClass(username,target,step_count,calories,points);
                    reference.child(username).setValue(helperClass);

                    dailytarget.setText("daily target : "+target+" steps");
                    pedoMeter.setText(""+step_count);
                    calMeter.setText(""+calories);
                    pointMeter.setText(""+points);


                }
                else
                {
                    //collect targetsteps, steps count, calories, points;
                    target=dataSnapshot.child(username).child("target").getValue(Integer.class);
                    step_count=dataSnapshot.child(username).child("step_count").getValue(Integer.class);
                    calories=dataSnapshot.child(username).child("calories").getValue(Integer.class);
                    points=dataSnapshot.child(username).child("points").getValue(Integer.class);
                    DBstep_count=step_count;

                    dailytarget.setText("daily target : "+target+" steps");
                    pedoMeter.setText(""+step_count);
                    calMeter.setText(""+calories);
                    pointMeter.setText(""+points);

                    if(target<=step_count)
                    {
                        result.setText("target achieved");

                    }
                    else
                    {
                        result.setText("");

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);


        redeem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUri("https://step-up.netlify.app/");
            }
        });





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

            String s=String.valueOf(event.values[0]);

            if(f==0)
            {
                initial=Integer.parseInt(s.substring(0,(s.length()-2)));
                f++;
            }

            step_count=Integer.parseInt(s.substring(0,(s.length()-2)))-initial+DBstep_count;
            calories=step_count/10;
            points=calories/2;

            if(target<=step_count)
            {
                result.setText("target achieved");

            }
            else
            {
                result.setText("");

            }

            pedoMeter.setText(""+step_count);
            calMeter.setText(""+calories);
            pointMeter.setText(""+points);

            rootNode=FirebaseDatabase.getInstance();
            reference=rootNode.getReference("users");

            UserhelperClass helperClass=new UserhelperClass(username,target,step_count,calories,points);
            reference.child(username).setValue(helperClass);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void gotoUri(String s)
    {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}