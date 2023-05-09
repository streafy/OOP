package ru.nsu.fit;

import ru.nsu.fit.report.ReportGenerator;

public class Main {
    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.generate();
    }
}