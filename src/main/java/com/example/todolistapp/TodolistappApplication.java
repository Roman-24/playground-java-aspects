package com.example.todolistapp;

import com.example.todolistapp.service.impl.AsEventPublisherImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class TodolistappApplication {

    @Autowired
    private AsEventPublisherImpl eventPublisher;

    public static void main(String[] args) {
        SpringApplication.run(TodolistappApplication.class, args);
    }

}
