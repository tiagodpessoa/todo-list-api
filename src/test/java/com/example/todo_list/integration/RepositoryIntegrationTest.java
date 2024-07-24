package com.example.todo_list.integration;

import com.example.todo_list.model.Task;
import com.example.todo_list.repository.TaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RepositoryIntegrationTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testCreatedAndDelete() {

        Task task = new Task(1L, "dry the dishes", true);
        task = taskRepository.save(task);

        Assertions.assertNotNull(task.getId());
        Task foundTask = taskRepository.findById(task.getId()).orElse(null);
        Assertions.assertNotNull(foundTask);
        Assertions.assertEquals("dry the dishes", foundTask.getDescription());
        Assertions.assertTrue(foundTask.getIsDone());

        taskRepository.deleteById(task.getId());
        foundTask = taskRepository.findById(task.getId()).orElse(null);

        Assertions.assertNull(foundTask);

    }
}
