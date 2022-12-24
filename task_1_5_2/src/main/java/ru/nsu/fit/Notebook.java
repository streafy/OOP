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

    /**
     * Adds new Note to the Notebook.
     *
     * @param title title of the new Note
     * @param description description of the new Note
     */
    public void addNote(String title, String description) {
        notes.add(new Note(title, description));
    }

    /**
     * Removes Note from the Notebook by title.
     *
     * @param title title of the Note to be deleted
     */
    public void removeNote(String title) {
        notes.remove(notes.stream()
                          .filter(n -> n.getTitle()
                                        .equals(title))
                          .findAny()
                          .orElseThrow());
    }

    /**
     * Prints all Notes from Notebook.
     */
    public void show() {
        notes.forEach(System.out::println);
    }

    /**
     * Prints Notes sorted by date and keywords.
     *
     * @param after Notes with creation date after this date will be printed
     * @param before Notes with creation date before this date will be printed
     * @param keywords Notes with title containing any of the keywords will be printed
     */
    public void showSorted(LocalDateTime after, LocalDateTime before, List<String> keywords) {
        notes.stream()
             .filter(note ->
                     after.isBefore(note.getCreationDate())
                             && before.isAfter(note.getCreationDate())
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
