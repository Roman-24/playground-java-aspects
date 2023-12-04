package com.example.todolistapp.service.impl;

import com.example.todolistapp.event.AsEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AsEventPublisherImpl {

    private final ApplicationEventPublisher eventPublisher;

    public AsEventPublisherImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishAsEvent(String message) {
        AsEvent customEvent = new AsEvent(this, message);
        eventPublisher.publishEvent(customEvent);
    }
}
