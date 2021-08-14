package com.example.choresclient;

public interface CustomEventListener {
    void onEventCompleted(String server_response);
    void onEventFailed();
}
