package com.example.todolistapp.service.impl;

import com.example.todolistapp.event.AsEvent;
import com.example.todolistapp.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AsEventListenerImpl implements ApplicationListener<AsEvent> {

    @Autowired
    TaskService taskService = new TaskServiceImpl();

    @Override
    public void onApplicationEvent(AsEvent event) {
        log.info("Received custom event: " + event.getEventData());
        taskService.createNew(event.getEventData());
    }
}


