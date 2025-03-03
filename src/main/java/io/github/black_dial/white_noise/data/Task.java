package io.github.black_dial.white_noise.data;

public class Task {
    public enum Status {PENDING, COMPLETED}

    private Integer id;
    private String desc;
    private Status status;

    public Task(Integer id, String desc, Status status) {}

    public Integer getId() {return null;}
    public String getDesc() {return null;}
    public Status getStatus() {return null;}
    public void setStatus(Status status) {}
}
