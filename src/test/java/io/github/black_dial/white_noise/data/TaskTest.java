package io.github.black_dial.white_noise.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {
    public static final Integer TEST_ID = 1;
    public static final String TEST_DESC = "read \"The Checklist Manifesto\"";
    public static final Task.Status TEST_STATUS = Task.Status.PENDING;

    @Test
    void shouldSetFields() {
        Task task = new Task(TEST_ID, TEST_DESC, TEST_STATUS);

        assertEquals(TEST_ID, task.getId());
        assertEquals(TEST_DESC, task.getDesc());
        assertEquals(TEST_STATUS, task.getStatus());
    }

    @Test
    void shouldUpdateStatus() {
        Task task = new Task(TEST_ID, TEST_DESC, Task.Status.PENDING);
        task.setStatus(Task.Status.COMPLETED);

        assertEquals(Task.Status.COMPLETED, task.getStatus());
    }

    @Test
    void shouldTestEqualityById() {
        Task[] tasks = {
                new Task(TEST_ID, TEST_DESC, TEST_STATUS),
                new Task(TEST_ID, TEST_DESC, TEST_STATUS),
                new Task(TEST_ID+1, TEST_DESC, TEST_STATUS)
        };

        assertEquals(tasks[0], tasks[1]);
        assertNotEquals(tasks[0], tasks[2]);
    }

    @Test
    void shouldGenSameHash() {
        Task[] tasks = {
                new Task(TEST_ID, TEST_DESC, Task.Status.PENDING),
                new Task(TEST_ID, TEST_DESC, Task.Status.COMPLETED)
        };

        assertEquals(tasks[0].hashCode(),tasks[1].hashCode());
    }
}
