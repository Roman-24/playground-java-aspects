package com.example.todolistapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private boolean completed;

}

