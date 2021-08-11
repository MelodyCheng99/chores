package com.example.choresclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button signInBtn, signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInBtn = findViewById(R.id.btnSignIn);
        signInBtn.setOnClickListener(this);

        signUpBtn = findViewById(R.id.btnSignUp);
        signUpBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if (view.getId() == R.id.btnSignIn) {
            setContentView(R.layout.activity_signin);
        }
        else if (view.getId() == R.id.btnSignUp) {
            setContentView(R.layout.activity_signup);
        }

    }

}