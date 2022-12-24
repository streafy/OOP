package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.notebook.Note;
import ru.nsu.fit.notebook.Notebook;

import java.time.LocalDateTime;

public class NotebookTest {
    Notebook notebook = new Notebook("src/main/java/ru/nsu/fit/data/notebook.json");

    @Test
    public void testNote() {
        Note note = new Note("Note", "Description");

        System.out.println(note);

        Assertions.assertEquals("Note", note.getTitle());
        Assertions.assertEquals("Description", note.getDescription());

        note.setTitle("Changed");
        note.setDescription("Changed");

        Assertions.assertEquals("Changed", note.getTitle());
        Assertions.assertEquals("Changed", note.getDescription());
    }

    @Test
    public void testNotebook() {
        int initialSize = notebook.getNotes().size();

        notebook.addNote("TEST", "123");
        Assertions.assertEquals(initialSize + 1, notebook.getNotes().size());
        notebook.removeNote("TEST");
        Assertions.assertEquals(initialSize, notebook.getNotes().size());
    }
}
