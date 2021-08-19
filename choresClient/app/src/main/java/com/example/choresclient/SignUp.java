package com.example.choresclient;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    boolean signUp = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    @Override
    public void onClick(View view){
        if(view.getId() == R.id.btnSignUpSignUp) {

            String email = ((EditText)findViewById(R.id.editTextSignUpEmailAddress)).getText().toString();
            String pass = ((EditText)findViewById(R.id.editTextSignUpPassword)).getText().toString();
            String confirm_pass = ((EditText)findViewById(R.id.editTextConfirmSignUpPassword)).getText().toString();
            String FName = ((EditText)findViewById(R.id.editTextFirstName)).getText().toString();
            String LName = ((EditText)findViewById(R.id.editTextLastName)).getText().toString();

            if(email == null || email.isEmpty() || pass == null || pass.isEmpty() || FName == null || FName.isEmpty() || LName == null || LName.isEmpty() || confirm_pass == null || confirm_pass.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), "Please fill missing information.", Toast.LENGTH_LONG);
                toast.show();
            }
            else if (pass == confirm_pass){
                signUp = true;
                //sign up check
                //check database for same email/username
                //if unique, then add info into database
                //if not unique, then return email taken message
            }

            if(signUp){
                Intent home = new Intent(this, Homepage.class);
                startActivity(home);
            }
        }

    }
}