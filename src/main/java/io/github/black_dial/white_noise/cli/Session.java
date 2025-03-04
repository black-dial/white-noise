package io.github.black_dial.white_noise.cli;

import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.service.TaskManager;

public class Session {
    private final TaskManager taskManager;

    public Session(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    TaskManager getTaskManager() {
        return taskManager;
    }

    public void add(String[] args) {
        getTaskManager().createTask(args[0]);
    }

    public void list(String[] args) {
        Task.Status status = null;
        if(args.length == 1) {
            for(Task.Status s : Task.Status.values())
                if(args[0].toUpperCase().equals(s.toString())) status = s;
            if(status == null) throw new RuntimeException();
        }
        getTaskManager().filterTasksByStatus(status);
    }

    public void check(String[] args) {
        getTaskManager().updateTask(Integer.valueOf(args[0]), Task.Status.COMPLETED);
    }
}
