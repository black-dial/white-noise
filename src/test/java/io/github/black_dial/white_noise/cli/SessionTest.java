package io.github.black_dial.white_noise.cli;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.data.TaskTest;
import io.github.black_dial.white_noise.service.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SessionTest {
    private Session session;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    @Mock
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new Session(taskManager);
        outStream.reset();
        System.setOut(new PrintStream(outStream));
    }

    @Test
    void shouldAddTask() {
        session.add(new String[]{TaskTest.TEST_DESC});

        verify(taskManager).createTask(TaskTest.TEST_DESC);
    }

    @Test
    void shouldListTasks() {
        List<Task> tasks = List.of(
                new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.PENDING),
                new Task(TaskTest.TEST_ID+1, TaskTest.TEST_DESC, Task.Status.COMPLETED)
        );
        when(taskManager.filterTasksByStatus(null)).thenReturn(tasks);
        session.list(new String[]{});

        verify(taskManager).filterTasksByStatus(null);
        assertTrue(outStream.toString().contains(String.format("%s\n%s", tasks.get(0), tasks.get(1))));
    }

    @Test
    void shouldListPendingTasks() {
        List<Task> pendingTasks = List.of(
                new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.PENDING),
                new Task(TaskTest.TEST_ID+1, TaskTest.TEST_DESC, Task.Status.PENDING)
        );
        when(taskManager.filterTasksByStatus(Task.Status.PENDING)).thenReturn(pendingTasks);
        session.list(new String[]{Task.Status.PENDING.toString().toLowerCase()});

        verify(taskManager).filterTasksByStatus(Task.Status.PENDING);
        assertTrue(outStream.toString().contains(String.format("%s\n%s", pendingTasks.get(0), pendingTasks.get(1))));
    }

    @Test
    void shouldCheckTask() {
        session.check(new String[]{TaskTest.TEST_ID.toString()});

        verify(taskManager).updateTask(1, Task.Status.COMPLETED);
    }
}
