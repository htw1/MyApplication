package com.example.htw.myapplication.DataStorage;

public class CustomMessageEvent {

    private String customMessage;

    public CustomMessageEvent(String customMessage) {
        this.customMessage = customMessage;

    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
}