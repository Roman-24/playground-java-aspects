package com.example.todolistapp.resource;


import com.example.todolistapp.service.impl.AsEventPublisherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/event")
public class EventResource {

    @Autowired
    private AsEventPublisherImpl eventPublisher;

    @PostMapping
    private void showDemo() {
        eventPublisher.publishCustomEvent("Create event demo");
    }
}
