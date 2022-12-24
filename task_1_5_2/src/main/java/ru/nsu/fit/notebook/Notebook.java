package ru.nsu.fit.notebook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.nsu.fit.serialization.LocalDateTimeDeserializer;
import ru.nsu.fit.serialization.LocalDateTimeSerializer;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Notebook implementation.
 */
public class Notebook {
    private final List<Note> notes;

    private static final String FILENAME = "task_1_5_2/src/main/java/ru/nsu/fit/data/notebook.json";

    /**
     * Notebook constructor
     */
    public Notebook() {
        notes = Objects.requireNonNullElseGet(deserialize(), ArrayList::new);
    }

    /**
     * Adds new Note to the Notebook.
     *
     * @param title       title of the new Note
     * @param description description of the new Note
     */
    public void addNote(String title, String description) {
        notes.add(new Note(title, description));
        serialize();
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
                          .findFirst()
                          .orElseThrow());
        serialize();
    }

    /**
     * Prints all Notes from Notebook.
     */
    public void show() {
        notes.forEach(System.out::println);
    }

    /**
     * Prints Notes filtered by date and keywords.
     *
     * @param after    Notes with creation date after this date will be printed
     * @param before   Notes with creation date before this date will be printed
     * @param keywords Notes with title containing any of the keywords will be printed
     */
    public void showFiltered(LocalDateTime after, LocalDateTime before, List<String> keywords) {
        notes.stream()
             .filter(note ->
                     after.isBefore(note.getCreationDate())
                             && before.isAfter(note.getCreationDate())
                             && keywords.stream()
                                        .anyMatch(note.getTitle()::contains))
             .forEach(System.out::println);
    }

    /**
     * Serializes list of Notes from Notebook to the json file.
     */
    private void serialize() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                                     .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                                     .create();

        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(notes, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deserializes list of Notes from the json file.
     */
    private List<Note> deserialize() {
        Gson gson = new GsonBuilder().setPrettyPrinting()
                                     .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
                                     .create();

        List<Note> notes;

        try (JsonReader reader = new JsonReader(new FileReader(FILENAME))) {
            TypeToken<List<Note>> notesListType = new TypeToken<>() {
            };
            notes = gson.fromJson(reader, notesListType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return notes;
    }
}
