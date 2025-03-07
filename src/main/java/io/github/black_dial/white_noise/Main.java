package io.github.black_dial.white_noise;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.black_dial.white_noise.cli.Session;
import io.github.black_dial.white_noise.data.Task;
import io.github.black_dial.white_noise.service.TaskManager;
import io.github.black_dial.white_noise.store.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.function.Function;

public class Main {
    public enum Verb {ADD, LIST, CHECK}

    public static final String FILE_NAME = "tasks.json";

    private static Session session;

    public static String[] extractArgs(String[] args) {
        return Arrays.copyOfRange(args, 1, args.length);
    }

    public static void main(String[] args) {
        FileRepository.setMapper(new ObjectMapper());
        if(getSession() == null) {
            try {
                File file = new File(FileRepository.STORAGE_DIR+FILE_NAME);
                if(file.createNewFile())
                    Files.write(file.toPath(), "[ ]".getBytes());
                FileRepository<Task> taskRepository = new FileRepository<>(
                        file, new TypeReference<>() {}
                );
                setSession(new Session(new TaskManager(taskRepository)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        assert getSession() != null;
        if(args.length > 0) {
            Verb verb = Arrays.stream(Verb.values()).filter(
                    v -> v.toString().toLowerCase().equals(args[0])
            ).findFirst().orElse(null);
            if(verb != null) {
                Function<String[], Void> command;
                command = switch(verb) {
                    case ADD -> a -> getSession().add(a);
                    case LIST -> a -> getSession().list(a);
                    case CHECK -> a -> getSession().check(a);
                };
                command.apply(extractArgs(args));
            }
        }
        try {
            getSession().end();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Session getSession() {
        return session;
    }

    public static void setSession(Session session) {
        Main.session = session;
    }
}
