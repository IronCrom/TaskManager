package ru.netology.javacore;

import java.util.Deque;
import java.util.Set;
import java.util.TreeSet;

public class Todos {
    private final Set<String> tasks = new TreeSet<>();
    private final int amountOfTasks;

    public int getAmountOfTasks() {
        return amountOfTasks;
    }

    public Todos(int amountOfTasks) {
        this.amountOfTasks = amountOfTasks;
    }

    public void addTask(String task) {
        if (tasks.size() < amountOfTasks) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        if (!tasks.isEmpty()) {
            tasks.remove(task);
        }
    }

    public void restoreTask(Deque<Operation> deque, Todos todos) {
        if (!deque.isEmpty()) {
            Operation operation = deque.pollLast();
            switch (operation.type()) {
                case ADD -> todos.removeTask(operation.task());
                case REMOVE -> todos.addTask(operation.task());
            }
        }
    }

    public String getAllTasks() {
        if (tasks.isEmpty()) {
            return "Список задач пуст";
        } else {
            return String.join(" ", tasks);
        }
    }
}