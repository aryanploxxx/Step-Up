package com.example.barebears;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    private Button b,loginbtn;
    private EditText e,p;
    FirebaseAuth mAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        b=findViewById(R.id.button5);
        loginbtn=findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent (SignIn.this, SignUp.class);
                startActivity(intent);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });
    }

    private void loginUser(){

        e = findViewById(R.id.editTextTextPersonName2);
        p = findViewById(R.id.editTextTextPassword);
        
        String email = e.getText().toString();
        String password = p.getText().toString();

        String username=email.substring(0,email.indexOf('@'));

        if (TextUtils.isEmpty(email)){
            e.setError("Email cannot be empty");
            e.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            p.setError("Password cannot be empty");
            p.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SignIn.this, "User logged in successfully", Toast.LENGTH_SHORT).show();

                        rootNode=FirebaseDatabase.getInstance();
                        reference=rootNode.getReference("users");

                        Query checkUser = reference.orderByChild("username").equalTo(username);

                        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists())
                                {
                                    Intent intent = new Intent(SignIn.this,Steps.class);
                                    intent.putExtra("username",username);
                                    intent.putExtra("target",0);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Intent intent = new Intent(SignIn.this,gender.class);
                                    intent.putExtra("username",username);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


                    }else
                        {
                        Toast.makeText(SignIn.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}