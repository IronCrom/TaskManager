package ru.netology.javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;

public class TodoServer {
    private final int port;
    private final Todos todos;

    public TodoServer(int port, Todos todos) {
        this.todos = todos;
        this.port = port;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Deque<Operation> deque = new ArrayDeque<>();
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream())
                ) {
                    Operation operation = Operation.fromJson(in.readLine());
                    if (!operation.type().equals(Type.RESTORE) && deque.size() < todos.getAmountOfTasks()) {
                        deque.add(operation);
                    }

                    switch (operation.type()) {
                        case ADD -> todos.addTask(operation.task());
                        case REMOVE -> todos.removeTask(operation.task());
                        case RESTORE -> {
                            if (deque.isEmpty()) {
                                out.println("Операции для восстаовления отсутствуют");
                                continue;
                            } else {
                                todos.restoreTask(deque, todos);
                            }
                        }
                    }
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}