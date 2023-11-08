package com.example.todolistapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@EnableAspectJAutoProxy
//@EntityScan(basePackages = "com.example.todolistapp.model")
public class TodolistappApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodolistappApplication.class, args);
        System.out.println("LOGOVANIE: " + log.isInfoEnabled());
    }
}
