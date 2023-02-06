package com.example.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;



public class Register extends AppCompatActivity {
    EditText Email, password, Retypepassword;
    Button sign_up;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        Email = findViewById(R.id.Email);
        password = findViewById(R.id.password);
        Retypepassword = findViewById(R.id.Retypepassword);
        sign_up = findViewById(R.id.SignUp);
        progressBar = findViewById(R.id.progressBar);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line hides the action


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });
    }

    private void registerNewUser() {
        String username = String.valueOf(Email.getText());
        String userpassword = String.valueOf(password.getText());
        String Retype = String.valueOf(Retypepassword.getText());
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(Register.this, "Please enter email!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;

        }
        else if (!isValidEmail(username)) {
            Toast.makeText(Register.this, "Please enter valid email!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

       else if (TextUtils.isEmpty(userpassword)) {
            Toast.makeText(Register.this, "Please enter password!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
       if (userpassword.length() < 6 && isValidPassword(userpassword)) {
            Toast.makeText(Register.this, "Enter at least 6 Character password or Special Characters", Toast.LENGTH_LONG).show();
            return;
        }
       else if (TextUtils.isEmpty(Retype)) {
            Toast.makeText(Register.this, "Please enter Verify password!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        else if (!userpassword.equals(Retype)) {
            Toast.makeText(Register.this, "Password not match!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }


        mAuth.createUserWithEmailAndPassword(username, userpassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "SignUp successfully .",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                            Intent intent = new Intent(getApplicationContext(), User.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Sign up failed, ",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }



    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "(?=.*[0-6]) (?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }


    }
