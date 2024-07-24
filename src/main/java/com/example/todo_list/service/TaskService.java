package com.example.todo_list.service;

import com.example.todo_list.exception.TaskNotFoundException;
import com.example.todo_list.model.Task;
import com.example.todo_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public void saveTask(Task task){
        taskRepository.save(task);
    }

    public void updateTask(Long id, Boolean isDone){
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
            task.setIsDone(isDone);
            taskRepository.save(task);
        }

    public void deleteTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

}
