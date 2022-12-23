package ru.nsu.fit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Notebook implementation.
 */
public class Notebook {
    private final List<Note> notes = new ArrayList<>();

    public void addNote(String title, String description) {
        notes.add(new Note(title, description));
    }

    public void removeNote(String title) {
        notes.remove(notes.stream()
                          .filter(n -> n.getTitle()
                                        .equals(title))
                          .findAny()
                          .orElseThrow());
    }

    public void show() {
        notes.forEach(System.out::println);
    }

    public void show(LocalDateTime from, LocalDateTime to, List<String> keywords) {
        notes.stream()
             .filter(note ->
                     from.isBefore(note.getDate())
                             && to.isAfter(note.getDate())
                             && keywords.stream()
                                        .anyMatch(note.getTitle()::contains))
             .forEach(System.out::println);
    }

    private void serialize() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                                     .create();

        String filename = "src/main/java/ru/nsu/fit/data/notebook.json";

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(notes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
