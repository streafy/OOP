package ru.nsu.fit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Notebook {
    private final List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        notes.add(note);
    }

    public void removeNote(String name) {
        notes.remove(notes.stream()
                          .filter(n -> n.getName()
                                        .equals(name))
                          .findAny()
                          .orElseThrow());
    }

    public void show() {
        notes.forEach(System.out::println);
    }

    private void serialize() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String filename = "src/main/java/ru/nsu/fit/data/notebook.json";

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(notes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
