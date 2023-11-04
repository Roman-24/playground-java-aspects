package com.example.todolistapp.service.impl;

import com.example.todolistapp.model.Task;
import com.example.todolistapp.repository.TaskRepository;
import com.example.todolistapp.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
        // Update logic...
        return taskRepository.save(task); // Simplified for brevity
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }
}
