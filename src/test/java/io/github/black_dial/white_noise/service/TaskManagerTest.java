package io.github.black_dial.white_noise.service;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.data.TaskTest;
import io.github.black_dial.white_noise.store.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TaskManagerTest {
    private TaskManager taskManager;
    @Mock
    Repository<Task> repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            taskManager = new TaskManager(repository);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldCreateTask() {
        when(repository.getMemory()).thenReturn(new ArrayList<>());
        Task task = taskManager.createTask(TaskTest.TEST_DESC);

        assertNotNull(task);
        assertEquals(TaskTest.TEST_DESC, task.getDesc());
        assertEquals(Task.Status.PENDING, task.getStatus());
        verify(repository).add(task);
    }

    @Test
    void shouldUpdateTask() {
        when(repository.getMemory()).thenReturn(List.of(
                new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.PENDING)
        ));
        Task task = taskManager.updateTask(TaskTest.TEST_ID, Task.Status.COMPLETED);

        assertNotNull(task);
        assertEquals(Task.Status.COMPLETED, task.getStatus());
        verify(repository).update(task);
    }

    @Test
    void shouldFilterTasksByStatus() {
        when(repository.getMemory()).thenReturn(List.of(
                new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.PENDING),
                new Task(TaskTest.TEST_ID+1, TaskTest.TEST_DESC+1, Task.Status.COMPLETED)
        ));
        List<Task> pendingTasks = taskManager.filterTasksByStatus(Task.Status.PENDING);
        assertNotNull(pendingTasks);
        assertEquals(1, pendingTasks.size());
        assertEquals(TaskTest.TEST_DESC, pendingTasks.get(0).getDesc());
    }
}
