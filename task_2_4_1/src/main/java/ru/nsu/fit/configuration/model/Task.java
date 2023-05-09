package ru.nsu.fit.configuration.model;

import java.time.LocalDateTime;

public class Task {

    private String id;
    private String name;
    private int points;

    private boolean isGiven;
    private LocalDateTime softDeadlineDate;
    private LocalDateTime hardDeadlineDate;
}
