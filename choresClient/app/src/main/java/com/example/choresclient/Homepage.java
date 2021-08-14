package com.example.choresclient;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity implements CustomEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        new CustomAsyncTask(this).execute(
            "http://10.0.0.201:8000/tasks/all"
        );
    }

    @Override
    public void onEventCompleted(String server_response) {
        Log.v("CatalogClient", server_response);
    }

    @Override
    public void onEventFailed() {

    }
}
