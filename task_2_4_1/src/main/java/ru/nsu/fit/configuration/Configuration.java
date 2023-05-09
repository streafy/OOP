package ru.nsu.fit.configuration;

import ru.nsu.fit.configuration.model.Student;
import ru.nsu.fit.configuration.model.Task;

import java.util.List;
import java.util.Map;

public class Configuration {

    private List<Task> tasks;
    private Map<Student, List<Task>> taskCompletionPlan;
}
