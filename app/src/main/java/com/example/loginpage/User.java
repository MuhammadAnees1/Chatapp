package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class User extends AppCompatActivity {
    Button Register;
    Button Login;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Register = findViewById(R.id.Register);
        Login = findViewById(R.id.Login);
        progressBar1 = findViewById(R.id.progressBar1);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide(); //This Line hides the action

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                progressBar1.setVisibility(View.GONE);


            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar1.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                progressBar1.setVisibility(View.GONE);


            }
        });


    }


    }
