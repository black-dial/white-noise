package io.github.black_dial.white_noise;

import io.github.black_dial.white_noise.cli.Session;
import io.github.black_dial.white_noise.service.TaskManager;
import io.github.black_dial.white_noise.store.FileRepository;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public enum Verb {ADD, LIST, CHECK}

    public static final String FILE_NAME = "tasks.json";

    private static Session session;

    public static void main(String[] args) {
        try {
            if(getSession() == null) setSession(new Session(new TaskManager(new FileRepository<>(
                    new File(FileRepository.STORAGE_DIR+FILE_NAME))
            )));
            assert null != getSession();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(args.length > 0) {
            Verb verb = Arrays.stream(Verb.values()).filter(
                    v -> v.toString().toLowerCase().equals(args[0])
            ).findFirst().orElse(null);
            if(verb != null) {
                switch(verb) {
                    case ADD -> getSession().add(Arrays.copyOfRange(args, 1, 2));
                    case LIST -> getSession().list(Arrays.copyOfRange(args, 1, args.length == 2 ? 2 : 1));
                    case CHECK -> getSession().check(Arrays.copyOfRange(args, 1, 2));
                }
            }
        }
    }

    static Session getSession() {
        return session;
    }

    static void setSession(Session session) {
        Main.session = session;
    }
}
