package com.luoyang.weather.modle;

public class MessageEvent {
    public final String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}