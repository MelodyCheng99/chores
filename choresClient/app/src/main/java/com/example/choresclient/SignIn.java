package com.example.choresclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity implements View.OnClickListener {

    Button signInBtn;
    boolean login = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        signInBtn = findViewById(R.id.btnSignInSignIn);
        signInBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.btnSignInSignIn) {

            String email = ((EditText)findViewById(R.id.editTextTextEmailAddress)).getText().toString();
            String pass = ((EditText)findViewById(R.id.editTextTextPassword)).getText().toString();

            if(email == null || email.isEmpty() || pass == null || pass.isEmpty() ) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please enter an email and a password.", Toast.LENGTH_LONG);
                toast.show();
            }

            //login check stuff

            else{
                login = true;
            }

            if(login){
                Intent home = new Intent(this, Homepage.class);
                startActivity(home);
            }
        }

    }

}
