package io.github.black_dial.white_noise.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileRepository<E> extends Repository<E> {
    private final File file;
    private final TypeReference<E[]> arrayTypeReference = new TypeReference<>() {};
    private static ObjectMapper mapper;

    public FileRepository(File file) throws IOException {
        this.file = file;
        assert file.exists() || file.createNewFile();
    }

    private File getFile() {
        return file;
    }

    TypeReference<E[]> getArrayTypeReference() {
        return arrayTypeReference;
    }

    private static ObjectMapper getMapper() {
        return mapper;
    }

    static void setMapper(ObjectMapper mapper) {
        FileRepository.mapper = mapper;
    }

    public void load() throws IOException {
        getMemory().clear();
        getMemory().addAll(List.of(getMapper().readValue(getFile(), getArrayTypeReference())));
    }

    public void write() throws IOException {
        getMapper().writeValue(getFile(), getMemory());
    }
}
