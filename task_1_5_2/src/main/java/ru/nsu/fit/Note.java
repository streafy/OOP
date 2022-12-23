package ru.nsu.fit;

import java.time.LocalDateTime;

public class Note {
    private String title;
    private String description;
    private LocalDateTime date;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.date = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
