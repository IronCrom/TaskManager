package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;

class TodosTest {
    Todos todos = new Todos(3);
    Deque<Operation> deque = new ArrayDeque<>(Collections.singleton(new Operation(Type.ADD, "Первая")));

    @Test
    void addTask() {
        todos.addTask("Первая");
    }

    @Test
    void removeTask() {
        todos.removeTask("Первая");
    }

    @Test
    void restoreTask() {
        todos.restoreTask(deque,todos);

    }

    @Test
    void getAllTasks() {
        Assertions.assertEquals(todos.getAllTasks(), "Список задач пуст");
    }
}