package io.github.black_dial.white_noise.store;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.data.TaskTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {
    private TestRepository<Task> repository;

    @BeforeEach
    void setUp() {
        repository = new TestRepository<>();
    }

    @Test
    void shouldAddElementToMemory() {
        Task task = new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, TaskTest.TEST_STATUS);
        repository.add(task);

        assertNotNull(repository.getMemory());
        assertEquals(1, repository.getMemory().size());
        assertTrue(repository.getMemory().contains(task));
    }

    @Test
    void shouldUpdateElementInMemory() {
        repository.add(new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.PENDING));
        Task task = new Task(TaskTest.TEST_ID, TaskTest.TEST_DESC, Task.Status.COMPLETED);
        repository.update(task);

        assertNotNull(repository.getMemory());
        assertEquals(1, repository.getMemory().size());
        assertTrue(repository.getMemory().contains(task));
    }
}
