package com.example.todolistapp.resource.impl;


import com.example.todolistapp.annotation.AOSWatchApi;
import com.example.todolistapp.dto.TaskDto;
import com.example.todolistapp.model.Task;
import com.example.todolistapp.resource.TaskResource;
import com.example.todolistapp.service.TaskService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@AOSWatchApi
public class TaskResourceImpl implements TaskResource {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        List<TaskDto> taskDtos = tasks.stream()
                .map(task -> modelMapper.map(task, TaskDto.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDtos);
    }

    @Override
    public ResponseEntity<TaskDto> createTask(TaskDto taskDto) {
        Task taskRequest = modelMapper.map(taskDto, Task.class);
        Task task = taskService.save(taskRequest);
        TaskDto response = modelMapper.map(task, TaskDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TaskDto> updateTask(Long id, TaskDto taskDto) {
        // Before update, you can check if the task exists.
        Task taskRequest = modelMapper.map(taskDto, Task.class);
        Task updatedTask = taskService.update(id, taskRequest);
        TaskDto response = modelMapper.map(updatedTask, TaskDto.class);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
