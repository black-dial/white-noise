package io.github.black_dial.white_noise.store;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.black_dial.white_noise.data.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FileRepositoryTest {
    public static final String TEST_FILE_NAME = "test_tasks.json";

    private FileRepository<Task> fileRepository;
    private final File testFile = new File(Repository.STORAGE_DIR+TEST_FILE_NAME);
    @Spy
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        FileRepository.setMapper(mapper);
        if(testFile.exists()) assertTrue(testFile.delete());
        try {
            fileRepository = new FileRepository<>(testFile);
            Files.write(testFile.toPath(), List.of("[", "]"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldLoadObjectsFromStorage() {
        try {
            fileRepository.load();

            verify(mapper).readValue(testFile, fileRepository.getArrayTypeReference());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldWriteObjectsIntoStorage() {
        try {
            fileRepository.write();

            verify(mapper).writeValue(testFile, fileRepository.getMemory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
