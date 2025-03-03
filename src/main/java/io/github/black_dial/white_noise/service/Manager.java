package io.github.black_dial.white_noise.service;

import io.github.black_dial.white_noise.store.Repository;

public abstract class Manager<T> { //TODO: change generic type in class diagram
    private Repository<T> repository;

    Manager(Repository<T> repository) {}
}
