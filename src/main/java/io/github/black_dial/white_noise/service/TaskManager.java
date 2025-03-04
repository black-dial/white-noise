package io.github.black_dial.white_noise.service;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.store.Repository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager extends Manager<Task> {
    private Integer lastIssuedId;

    TaskManager(Repository<Task> repository) throws IOException {
        super(repository);
        Task lastIssuedTask = (repository.getMemory()).stream().max(Comparator.naturalOrder()).orElse(null);
        lastIssuedId = lastIssuedTask == null ? 0 : lastIssuedTask.getId();
    }

    public Task createTask(String desc) {
        Task task = new Task(++lastIssuedId, desc, Task.Status.PENDING);
        getRepository().add(task);
        return task;
    }

    public Task updateTask(Integer id, Task.Status status) {
        Task task = getRepository().getMemory().stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
        if(task != null) {
            task.setStatus(status);
            getRepository().update(task);
        }
        return task;
    }

    public List<Task> filterTasksByStatus(Task.Status status) {
        return getRepository().getMemory().stream().filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }
}
