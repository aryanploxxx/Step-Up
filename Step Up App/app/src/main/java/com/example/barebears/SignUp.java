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

public class SignUp extends AppCompatActivity {

    FirebaseAuth mAuth;

    private EditText e, p, cp;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();

        b = findViewById(R.id.button);
        e = findViewById(R.id.editTextTextPersonName2);
        p = findViewById(R.id.editTextTextPassword);
        cp = findViewById(R.id.editTextTextPassword2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAuth = FirebaseAuth.getInstance();

                String pass = "", cpass = "";
                pass = p.getText().toString();
                cpass = cp.getText().toString();

                if (pass.equals(cpass)) {
                    createUser();
                } else {
                    Toast.makeText(SignUp.this, "Passwords not matched, Enter Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUser() {

        e = findViewById(R.id.editTextTextPersonName2);
        p = findViewById(R.id.editTextTextPassword);

        String email = e.getText().toString();
        String password = p.getText().toString();

        if (TextUtils.isEmpty(email)) {
            e.setError("Email cannot be empty");
            e.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            p.setError("Password cannot be empty");
            p.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this, SignIn.class));
                    } else {
                        Toast.makeText(SignUp.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
 