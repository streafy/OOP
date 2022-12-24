package ru.nsu.fit.notebook;

import java.time.LocalDateTime;

/**
 * Note to use in Notebook.
 */
public class Note {
    private String title;
    private String description;
    private final LocalDateTime creationDate;

    /**
     * Note constructor by title and description Strings.
     *
     * @param title Note title
     * @param description Note description
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Note's title getter.
     *
     * @return Note's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Note's title setter.
     *
     * @param title title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Note's description getter.
     *
     * @return Note's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Note's description setter.
     *
     * @param description description String
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Note's creationDate getter.
     *
     * @return Note's creationDate
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Title            | " + title + "\n" +
               "Description      | " + description + "\n" +
               "Date of creation | " + creationDate.getDayOfMonth() + "." + creationDate.getMonthValue() + "."
                + creationDate.getYear() + " " + creationDate.getHour() + ":" + creationDate.getMinute() + "\n";
    }
}
