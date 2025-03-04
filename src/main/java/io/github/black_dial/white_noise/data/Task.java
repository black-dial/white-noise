package io.github.black_dial.white_noise.data;

import java.util.Objects;

public class Task implements Comparable<Task> {
    public enum Status {PENDING, COMPLETED}

    private final Integer id;
    private final String desc;
    private Status status;

    public Task(Integer id, String desc, Status status) {
        this.id = id;
        this.desc = desc;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return Objects.equals(getId(), task.getId());
    }

    @Override
    public int compareTo(Task o) {
        return getId()-o.getId();
    }
}
