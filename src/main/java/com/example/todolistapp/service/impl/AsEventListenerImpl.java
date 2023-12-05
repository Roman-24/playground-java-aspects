package com.example.todolistapp.service.impl;

import com.example.todolistapp.event.AsEvent;
import com.example.todolistapp.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsEventListenerImpl {

    @Autowired
    TaskService taskService = new TaskServiceImpl();

    @KafkaListener(topics = "as-topic", groupId = "as-group")
    public void onApplicationEvent(AsEvent event) {
        log.info("Received custom event: " + event.getEventData());
        taskService.createNew(event.getEventData());
    }
}


