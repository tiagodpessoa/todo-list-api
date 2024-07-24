package com.example.todo_list.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    @Test
    public void testArgs(){

        Task task = new Task(1L, "wash the dishes", false);

        Assertions.assertEquals(1L, task.getId());
        Assertions.assertEquals("wash the dishes", task.getDescription());
        Assertions.assertFalse(task.getIsDone());
    }
}
