package io.github.black_dial.white_noise.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.black_dial.white_noise.data.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileRepositoryTest {
    public static final String TEST_FILE_NAME = "test_tasks.json";

    private FileRepository<Task> fileRepository;
    private final File testFile = new File(Repository.STORAGE_DIR+TEST_FILE_NAME);
    @Spy
    private ObjectMapper mapper;

    public FileRepositoryTest() {
        super();
        FileRepository.setMapper(mapper);
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fileRepository = new FileRepository<>(testFile);
        if(testFile.exists()) assertTrue(testFile.delete());
    }

    @Test
    void shouldLoadObjectsFromStorage() {
        fileRepository.load();

        try {
            verify(mapper).readValue(testFile, Task[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldWriteObjectsIntoStorage() {
        fileRepository.write();

        try {
            verify(mapper).writeValue(testFile, fileRepository.getMemory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
