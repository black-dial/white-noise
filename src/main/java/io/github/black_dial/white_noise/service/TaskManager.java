package io.github.black_dial.white_noise.service;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.store.Repository;

import java.util.List;

public class TaskManager {
    public Task createTask(String desc) {return null;}
    public Task updateTask(Integer id, Task.Status status) {return null;}
    public List<Task> filterTasksByStatus(Task.Status status) {return null;}

    TaskManager(Repository<Task> repository) {}
}
