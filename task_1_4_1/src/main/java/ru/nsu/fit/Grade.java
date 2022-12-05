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
        return switch (this) {
            case FAILED -> 0;
            case PASSED, EXCELLENT -> 5;
            case POOR -> 2;
            case SATISFACTORY -> 3;
            case GOOD -> 4;
        };
    }

    /**
     * Returns String representation of Grade.
     *
     * @return String representation of Grade
     */
    @Override
    public String toString() {
        return switch (this) {
            case FAILED -> "FAILED";
            case PASSED -> "PASSED";
            case POOR -> "POOR";
            case SATISFACTORY -> "SATISFACTORY";
            case GOOD -> "GOOD";
            case EXCELLENT -> "EXCELLENT";
        };
    }
}
