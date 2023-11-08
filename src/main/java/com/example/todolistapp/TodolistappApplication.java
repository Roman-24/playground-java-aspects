package com.example.todolistapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAspectJAutoProxy
//@EntityScan(basePackages = "com.example.todolistapp.model")
public class TodolistappApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodolistappApplication.class, args);
    }
}
