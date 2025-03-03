package io.github.black_dial.white_noise.store;

import java.util.List;

public abstract class Repository<E> {
    public static final String STORAGE_DIR = ".storage/";

    private List<E> memory;

    public List<E> getMemory() {return null;} //TODO: add method in diagram

    public void add(E element) {}
    public void update(E element) {}
    public abstract void load(); //TODO: mark abstract in diagram
    public abstract void write(); //TODO: mark abstract in diagram
}
