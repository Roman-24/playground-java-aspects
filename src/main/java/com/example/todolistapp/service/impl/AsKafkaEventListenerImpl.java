package com.example.todolistapp.service.impl;

import com.example.todolistapp.event.AsKafkaEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "as-topic", groupId = "as-group")
@Slf4j
public class AsKafkaEventListenerImpl {

    @KafkaHandler
    public void handleCustomEvent(AsKafkaEvent customEvent) {
        log.info("Received custom event: " + customEvent.getEventData());
        // Perform actions based on the received event
    }

}
