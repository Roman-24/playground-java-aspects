package com.example.todolistapp.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AsEvent extends ApplicationEvent {

    private String eventData;

    public AsEvent(Object source, String eventData) {
        super(source);
        this.eventData = eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
