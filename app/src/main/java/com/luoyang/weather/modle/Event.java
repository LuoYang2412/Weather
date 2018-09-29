package com.luoyang.weather.modle;

public class Event {
    private final EventMessage message;

    public Event(EventMessage message) {
        this.message = message;
    }

    public EventMessage getMessage() {
        return message;
    }
}
