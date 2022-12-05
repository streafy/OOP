package ru.nsu.fit;

/**
 * Grade value for a Subject.
 */
public enum Grade {
    FAILED,
    PASSED,
    POOR,
    SATISFACTORY,
    GOOD,
    EXCELLENT;

    /**
     * Convert Grade to int to calculate average grade.
     *
     * @return integer value of Grade
     */
    public int toInt() {
        int grade = -1;
        switch (this) {
            case FAILED:
                grade = 0;
                break;
            case PASSED, EXCELLENT:
                grade = 5;
                break;
            case POOR:
                grade = 2;
                break;
            case SATISFACTORY:
                grade = 3;
                break;
            case GOOD:
                grade = 4;
                break;
        }
        return grade;
    }

    /**
     * Returns String representation of Grade.
     *
     * @return String representation of Grade
     */
    @Override
    public String toString() {
        String grade = "";
        switch (this) {
            case FAILED:
                grade = "FAILED";
                break;
            case PASSED:
                grade = "PASSED";
                break;
            case POOR:
                grade = "POOR";
                break;
            case SATISFACTORY:
                grade = "SATISFACTORY";
                break;
            case GOOD:
                grade = "GOOD";
                break;
            case EXCELLENT:
                grade = "EXCELLENT";
                break;
        }
        return grade;
    }
}
