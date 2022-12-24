package ru.nsu.fit;

import org.junit.jupiter.api.Test;
import ru.nsu.fit.notebook.Notebook;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class NotebookTest {
    Notebook notebook = new Notebook();

    @Test
    public void test() {
        notebook.addNote("Test 1", "description");
        notebook.addNote("Note 2", "description");
        notebook.addNote("Qwer 3", "description");

        LocalDate from = LocalDate.of(2022, 12, 23);
        LocalDate to = LocalDate.of(2022, 12, 25);
        LocalTime noon = LocalTime.of(0, 0);

        List<String> keywords = new ArrayList<>();
        keywords.add("Test");
        keywords.add("Qwer");
        notebook.showSorted(LocalDateTime.of(from, noon), LocalDateTime.of(to, noon), keywords);

        notebook.removeNote("Qwer 3");

        notebook.show();
    }
}
