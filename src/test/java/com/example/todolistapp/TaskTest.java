package com.example.todolistapp;

import com.example.todolistapp.dto.TaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // Use a random port to avoid conflicts during tests
@AutoConfigureMockMvc
public class TaskTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For JSON conversion

    // Simulate the sequence of actions a user would take
    @Test
    public void testCreateReadUpdateDeleteTaskFlow() throws Exception {
        // Create a new task
        TaskDto newTask = new TaskDto(null, "New Task", false);
        String newTaskJson = objectMapper.writeValueAsString(newTask);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newTask.getName()))
                .andExpect(jsonPath("$.completed").value(newTask.isCompleted()))
                .andDo(result -> {
                    // Assuming the Task object returns an ID upon creation
                    TaskDto createdTask = objectMapper.readValue(result.getResponse().getContentAsString(), TaskDto.class);
                    Long createdTaskId = createdTask.getId();

                    // Read the created task by ID
                    mockMvc.perform(get("/api/tasks/{id}", createdTaskId))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name").value(newTask.getName()));

                    // Update the task
                    TaskDto updatedTask = new TaskDto(createdTaskId, "Updated Task", true);
                    mockMvc.perform(put("/api/tasks/{id}", createdTaskId)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(updatedTask)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.name").value("Updated Task"))
                            .andExpect(jsonPath("$.completed").value(true));

                    // Delete the task
                    mockMvc.perform(delete("/api/tasks/{id}", createdTaskId))
                            .andExpect(status().isOk());

                    // Verify the task is no longer there
                    mockMvc.perform(get("/api/tasks/{id}", createdTaskId))
                            .andExpect(status().isNotFound());
                });
    }
}

