package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

enum Type {
    ADD,
    REMOVE,
    RESTORE
}

public record Operation(Type type, String task) {

    static Operation fromJson(String jsonString) {
        GsonBuilder builderGson = new GsonBuilder();
        Gson gson = builderGson.create();
        return gson.fromJson(jsonString, Operation.class);
    }
}