package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.notebook.Note;
import ru.nsu.fit.notebook.Notebook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class NotebookTest {
    Notebook notebook = new Notebook();

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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime from = LocalDateTime.parse("24.12.2022 23:00", formatter);
        LocalDateTime to = LocalDateTime.parse("24.12.2022 23:30", formatter);
        List<String> keywords = new ArrayList<>();
        keywords.add("Note");
        keywords.add("Test");
        notebook.showFiltered(from, to, keywords);
        notebook.show();
    }

    @Test
    public void testApp() {
        App.main(new String[] { "-add", "Note", "Desc"});
        App.main(new String[] { "-rm", "Note"});
        App.main(new String[] { "-show" });
        App.main(new String[] { "-show", "-a", "24.12.2022 23:00", "-b", "24.12.2022 23:30", "Note", "Test" });
    }
}
