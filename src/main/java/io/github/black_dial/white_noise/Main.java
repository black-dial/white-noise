package io.github.black_dial.white_noise;

import io.github.black_dial.white_noise.cli.Session;

public class Main {
    public enum VERB {ADD, LIST, CHECK}

    private static Session session;

    public static void main(String[] args) {
        System.out.println("hello world");
    }

    static Session getSession() {return null;}
    static void setSession(Session session) {}
}
