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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText username, userpassword;
    Button button;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView sign;

    //    @Override
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//            Intent intent = new Intent(getApplicationContext(), Login.class);
//            startActivity(intent);
//            finish();
//
//        }
//    }
//
//    @SuppressLint("MissingInflatedId")
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        userpassword = findViewById(R.id.userpassword);
        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        sign = findViewById(R.id.sign);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); //This Line hides the action

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                progressBar.setVisibility(View.GONE);

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loginUserAccount();
            }
        });
    }

    private void loginUserAccount() {


        String name = String.valueOf(username.getText());
        String password = String.valueOf(userpassword.getText());

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(Login.this, "Please enter email!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if(!this.isValidEmail(name)){
            Toast.makeText(Login.this, "Please enter valid email!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Please enter password!!", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }


        try {
        mAuth.signInWithEmailAndPassword(name, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login successful!!",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        finish();
                    } else {
                        Toast.makeText(Login.this, "Check Password!!",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        return;

                    }
                });
        } catch (Exception e) {
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}