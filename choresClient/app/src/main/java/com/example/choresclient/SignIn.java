package com.example.choresclient;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn extends AppCompatActivity implements View.OnClickListener, CustomEventListener {

    Button signInBtn;

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

            if (email == null || email.isEmpty() || pass == null || pass.isEmpty() ) {
                Toast toast = Toast.makeText(getApplicationContext(), "Please enter an email and a password.", Toast.LENGTH_LONG);
                toast.show();
            } else {
                new CustomAsyncTask(this).execute(
                    "http://10.0.0.201:8000/user/validate_user",
                    "POST",
                    "username_or_email",
                    email,
                    "password",
                    pass
                );
            }
        }

    }

    @Override
    public void onEventCompleted(String server_response) throws JSONException {
        JSONObject signInResponse = new JSONObject(server_response);
        if (signInResponse.get("id").toString() != null) {
            Intent home = new Intent(this, Homepage.class);
            home.putExtra("firstName", signInResponse.get("first_name").toString());
            home.putExtra("lastName", signInResponse.get("last_name").toString());
            home.putExtra("userId", signInResponse.get("id").toString());
            startActivity(home);
        }
    }

    @Override
    public void onEventFailed() {

    }
}
