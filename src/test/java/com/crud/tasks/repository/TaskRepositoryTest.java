package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void shouldFindById() {
        // Given
        Task task = new Task(null, "Test", "Test");
        long taskId = taskRepository.save(task).getId();

        // When
        Optional<Task> retrievedTask = taskRepository.findById(taskId);

        // Then
        assertTrue(retrievedTask.isPresent());
    }

    @Test
    public void shouldFindAll() {
        // Given
        Task task = new Task(null, "Test", "Test");
        taskRepository.save(task).getId();

        // When
        List<Task> retrievedTasks = taskRepository.findAll();

        // Then
        assertTrue(retrievedTasks.size() >=1);
    }

    @Test
    public void shouldSave() {
        // Given
        Task task = new Task(null, "Test", "Test");

        // When
        Task savedTask = taskRepository.save(task);
        long savedTaskId = savedTask.getId();

        // Then
        assertNotEquals(0L, savedTaskId);
        assertNotNull(savedTaskId);
    }

    @Test
    public void shouldDeleteById() {
        // Given
        Task task = new Task(null, "Test", "Test");
        long taskId = taskRepository.save(task).getId();

        // When
        taskRepository.deleteById(taskId);

        // Then
        Optional<Task> retrievedTask = taskRepository.findById(taskId);
        assertFalse(retrievedTask.isPresent());
    }

    @Test
    public void shouldCount() {
        // When
        long taskCount = taskRepository.count();

        // Then
        assertTrue(taskCount >= 0);
    }
}