package com.example.todo_list.controller;

import com.example.todo_list.model.Task;
import com.example.todo_list.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/tasks")
@Tag(name = "Task Controller", description = "Operations pertaining to tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<Void> createATask(@Valid @RequestBody Task task) {
        taskService.saveTask(task);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Update an existing task")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateATask(@PathVariable Long id, @RequestParam Boolean isDone) {
        taskService.updateTask(id, isDone);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Delete a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteATask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
