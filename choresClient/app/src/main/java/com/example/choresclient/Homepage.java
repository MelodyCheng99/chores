package com.example.choresclient;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity implements CustomEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        String firstName = getIntent().getStringExtra("firstName");
        String lastName = getIntent().getStringExtra("lastName");
        String welcomeMessage = getResources().getString(R.string.welcome_personalized) + " " +
            firstName + " " + lastName + "!";
        TextView welcomeTextView = findViewById(R.id.welcomeText);
        welcomeTextView.setText(welcomeMessage);

        new CustomAsyncTask(this).execute(
            "http://10.0.0.201:8000/tasks/all"
        );
    }

    @Override
    public void onEventCompleted(String server_response) throws JSONException {
        JSONArray tasksJson = new JSONArray(server_response);
        ArrayList<String> tasksArray = new ArrayList<>();

        for (int i = 0 ; i < tasksJson.length(); i++) {
            JSONObject task = tasksJson.getJSONObject(i);
            tasksArray.add(task.get("room") + " : " + task.get("task"));
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(
            this,
            R.layout.task_item,
            tasksArray
        );
        ListView listView = findViewById(R.id.tasksList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onEventFailed() {

    }
}
