package ru.netology.javacore;

public class Main {

    public static void main(String[] args) {
        Todos todos = new Todos(7);
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}