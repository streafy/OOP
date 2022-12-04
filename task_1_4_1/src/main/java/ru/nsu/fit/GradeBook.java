package ru.nsu.fit;

import java.util.Collections;
import java.util.List;

public class GradeBook {
    private final int id;
    private final String studentName;
    private final String department;
    private final List<Semester> semesters;
    private final int currentSemester;

    private int averageMark = 0;

    private class Semester {
        List<Subject> subjects;
    }

    public class Subject {
        private final String subjectName;
        private final int grade;
        private final List<Integer> semesters;

        public Subject(String subjectName, int grade, List<Integer> semesters) {
            this.subjectName = subjectName;
            this.grade = grade;
            this.semesters = semesters;
        }
    }

    public GradeBook(int id, String studentName, String department, int currentSemester, List<Subject> subjects) {
        this.id = id;
        this.studentName = studentName;
        this.department = department;
        this.currentSemester = currentSemester;
        semesters = Collections.nCopies(8, new Semester());

        subjects.forEach(subject -> subject.semesters.forEach(
                semesterNumber -> semesters.get(semesterNumber).subjects.add(subject)
        ));
    }

    public double getAverageMark() {
        return averageMark;
    }

    private void calculateAverageMark() {
        for (Semester sem : semesters) {
            for (Subject subj : sem.subjects) {
                averageMark += subj.grade;
            }
        }
        averageMark /= currentSemester;
    }
}