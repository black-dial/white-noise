package io.github.black_dial.white_noise.store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Repository<E> {
    public static final String STORAGE_DIR = ".storage/";

    private final List<E> memory;

    public Repository() {
        memory = new ArrayList<>();
    }

    public List<E> getMemory() {
        return memory;
    }

    public void add(E element) {
        getMemory().add(element);
    }

    public void update(E element) {
        getMemory().set(getMemory().indexOf(element), element);
    }

    public abstract void load() throws IOException;
    public abstract void write() throws IOException;
}
