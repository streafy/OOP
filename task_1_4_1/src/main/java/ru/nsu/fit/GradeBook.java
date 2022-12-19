package ru.nsu.fit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of student's grade book.
 */
public class GradeBook {
    private final int id;
    private final String studentName;
    private final String speciality;
    private final List<Semester> semesters = new ArrayList<>();
    private int currentSemester;

    private double overallAverageGrade = 0;
    private double diplomaAverageGrade = 0;

    private boolean isRedDiploma = false;
    private boolean hasScholarship = true;

    private int qualificationWorkGrade = 0;

    private static class Semester {
        int number;
        List<Subject> subjects = new ArrayList<>();

        public Semester(int number) {
            this.number = number;
        }
    }

    /**
     * Creates Student's GradeBook.
     *
     * @param id              id of the grade book
     * @param studentName     - student name
     * @param speciality      - student's speciality
     * @param currentSemester - current semester
     * @param subjects        - list of initial subjects in the grade book
     */
    public GradeBook(int id, String studentName, String speciality, int currentSemester, Map<Subject, Integer> subjects) {
        this.id = id;
        this.studentName = studentName;
        this.speciality = speciality;
        this.currentSemester = currentSemester;

        for (int i = 0; i < 8; i++) {
            semesters.add(new Semester(i + 1));
        }

        subjects.forEach(this::addSubject);
    }

    /**
     * Adds subject to specific semester.
     *
     * @param subject        subject to be added
     * @param semesterNumber semester number to which subject will be added
     */
    public void addSubject(Subject subject, int semesterNumber) {
        semesters.get(semesterNumber - 1).subjects.add(subject);
        overallAverageGrade = calculateOverallAverageGrade();
        hasScholarship = calculateScholarship();
        if (subject.isFinalSemester()) {
            diplomaAverageGrade = calculateDiplomaAverageGrade();
            isRedDiploma = calculateRedDiploma();
        }
    }

    private double calculateOverallAverageGrade() {
        int gradesSum = semesters.stream()
                .mapToInt(s -> s.subjects.stream()
                        .mapToInt(Subject::getGrade)
                        .sum())
                .sum();
        int gradesCount = semesters.stream()
                .mapToInt(s -> s.subjects.size())
                .sum();

        return (double) gradesSum / gradesCount;
    }

    /**
     * Returns overall average grade.
     *
     * @return overall average grade
     */
    public double getOverallAverageGrade() {
        return overallAverageGrade;
    }

    private double calculateDiplomaAverageGrade() {
        int gradesSum = semesters.stream()
                .mapToInt(s -> s.subjects.stream()
                        .filter(Subject::isFinalSemester)
                        .mapToInt(Subject::getGrade)
                        .sum())
                .sum();
        int gradesCount = semesters.stream()
                .mapToInt(s -> (int) s.subjects.stream()
                        .filter(Subject::isFinalSemester)
                        .count())
                .sum();

        return (double) gradesSum / gradesCount;
    }

    /**
     * Returns diploma average grade.
     *
     * @return diploma average grade.
     */
    public double getDiplomaAverageGrade() {
        return diplomaAverageGrade;
    }

    private boolean calculateRedDiploma() {
        int excellentCount = 0;
        int gradesCount = 0;

        for (Semester semester : semesters) {
            for (Subject subject : semester.subjects) {
                if (!subject.isFinalSemester()) {
                    continue;
                }
                if (subject.getGrade() < 4) {
                    return false;
                }
                if (subject.getGrade() == 5) {
                    excellentCount++;
                }
                gradesCount++;
            }
        }

        return (double) excellentCount / gradesCount >= 0.75 && qualificationWorkGrade == 5;
    }

    /**
     * Student can get red diploma.
     *
     * @return true if student can get red diploma, false otherwise
     */
    public boolean isRedDiploma() {
        return isRedDiploma;
    }

    private boolean calculateScholarship() {
        int previousSemesterIndex = currentSemester - 2;
        return semesters.get(previousSemesterIndex).subjects.stream()
                .noneMatch(s -> s.getGrade() < 4);
    }

    /**
     * Student has scholarship in current semester.
     *
     * @return true if student has scholarship in current semester, false otherwise
     */
    public boolean hasScholarship() {
        return hasScholarship;
    }

    /**
     * Get number of subjects of given type in given semester.
     *
     * @param type type of subject (it's attestation form)
     * @param semesterNumber number of semester from which we get subjects
     * @return if student has scholarship then true, false otherwise
     */
    public int getSemesterSubjectsCountByType(AttestationForm type, int semesterNumber) {
        return 0;
    }

    /**
     * Returns String representation of GradeBook.
     *
     * @return String representation of GradeBook
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Grade book\n")
                .append("ID: ")
                .append(id)
                .append("\n")
                .append("Student: ")
                .append(studentName)
                .append("\n")
                .append("Speciality: ")
                .append(speciality)
                .append("\n");

        semesters.forEach(semester -> {
            sb.append("=== ")
                    .append(semester.number)
                    .append(" Semester ===\n");
            semester.subjects.forEach(subject -> sb.append(subject)
                    .append("\n"));
        });
        return sb.toString();
    }
}
