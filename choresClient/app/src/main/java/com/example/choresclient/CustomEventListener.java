package com.example.choresclient;

import org.json.JSONException;

public interface CustomEventListener {
    void onEventCompleted(String server_response) throws JSONException;
    void onEventFailed();
}
