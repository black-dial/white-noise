package io.github.black_dial.white_noise.service;

import io.github.black_dial.white_noise.store.Repository;

public abstract class Manager<T> {
    private final Repository<T> repository;

    public Manager(Repository<T> repository) {
        this.repository = repository;
    }

    public Repository<T> getRepository() {
        return repository;
    }
}
