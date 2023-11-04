package com.example.todolistapp.service;

import com.example.todolistapp.model.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task save(Task task);

    Task update(Long id, Task task);

    void deleteById(Long id);
}
