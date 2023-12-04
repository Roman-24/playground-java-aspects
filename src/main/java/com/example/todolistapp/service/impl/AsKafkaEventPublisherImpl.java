package com.example.todolistapp.service.impl;

import com.example.todolistapp.event.AsKafkaEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AsKafkaEventPublisherImpl {

    private final KafkaTemplate<String, AsKafkaEvent> kafkaTemplate;

    public AsKafkaEventPublisherImpl(KafkaTemplate<String, AsKafkaEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishAsKafkaEvent(final String message) {
        AsKafkaEvent customEvent = new AsKafkaEvent();
        customEvent.setEventData(message);
        kafkaTemplate.send("as-topic", customEvent);
    }
}
