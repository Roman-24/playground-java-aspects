package com.example.todolistapp.resource;


import com.example.todolistapp.dto.TaskDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public interface TaskResource {

    @GetMapping
    ResponseEntity<List<TaskDto>> getAllTasks();

    @PostMapping
    ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto);

    @PutMapping("/{id}")
    ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}
