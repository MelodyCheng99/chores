package com.example.choresclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
//            Toast toast = Toast.makeText(getApplicationContext(), "Hellow HERE", Toast.LENGTH_SHORT);
//            toast.show();
//            setContentView(R.layout.activity_signin);
            Intent goSignIn = new Intent(this, SignIn.class);
            startActivity(goSignIn);

        }
        else if (view.getId() == R.id.btnSignUp) {
            setContentView(R.layout.activity_signup);
        }
    }

}