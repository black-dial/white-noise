package io.github.black_dial.white_noise.store;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileRepository<E> extends Repository<E> {
    private final File file;
    private final TypeReference<List<E>> listType;
    private static ObjectMapper mapper;

    public FileRepository(File file, TypeReference<List<E>> listType) throws IOException {
        super();
        this.file = file;
        this.listType = listType;
    }

    private File getFile() {
        return file;
    }

    TypeReference<List<E>> getListType() {
        return listType;
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static void setMapper(ObjectMapper mapper) {
        FileRepository.mapper = mapper;
    }

    public void load() throws IOException {
        getMemory().clear();
        getMemory().addAll(getMapper().readValue(getFile(), getListType()));
    }

    public void write() throws IOException {
        getMapper().writerWithDefaultPrettyPrinter().writeValue(getFile(), getMemory());
    }
}
