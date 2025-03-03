package io.github.black_dial.white_noise.cli;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.data.TaskTest;
import io.github.black_dial.white_noise.service.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SessionTest {
    private Session session;
    @Mock
    private TaskManager taskManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        session = new Session(taskManager);
    }

    @Test
    void shouldAddTask() {
        session.add(new String[]{TaskTest.TEST_DESC});
        verify(taskManager).createTask(TaskTest.TEST_DESC);
    }

    @Test
    void shouldListTasks() {
        session.list(new String[]{});
        verify(taskManager).filterTasksByStatus(null);
    }

    @Test
    void shouldListPendingTasks() {
        session.list(new String[]{"--status", "PENDING"});
        verify(taskManager).filterTasksByStatus(Task.Status.PENDING);
    }

    @Test
    void shouldCheckTask() {
        session.check(new String[]{"1"});
        verify(taskManager).updateTask(1, Task.Status.COMPLETED);
    }
}
