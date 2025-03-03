package io.github.black_dial.white_noise.store;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class FileRepository<E> extends Repository<E> {
    private File file;
    private static ObjectMapper mapper; //TODO: mark static in diagram

    public FileRepository(File file) {}

    public static ObjectMapper getMapper() {return null;} //TODO: add method in diagram
    public static void setMapper(ObjectMapper mapper) {} //TODO: add method in diagram

    public void load() {}
    public void write() {}
}
