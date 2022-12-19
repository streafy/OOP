package ru.nsu.fit;

/**
 * Class that represents Subject in GradeBook.
 */
public class Subject {
    private final String name;
    private Grade grade;
    private final boolean isFinalSemester;

    /**
     * Subject Constructor.
     *
     * @param name name of the subject
     * @param grade grade for that subject
     * @param isFinalSemester is final semester for this subject
     */
    public Subject(String name, Grade grade, boolean isFinalSemester) {
        this.name = name;
        this.grade = grade;
        this.isFinalSemester = isFinalSemester;
    }

    /**
     * Returns subject's name.
     *
     * @return subject's name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes grade value.
     *
     * @param grade new grade value
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * Returns grade value for subject.
     *
     * @return grade value for subject
     */
    public int getGrade() {
        return grade.toInt();
    }

    /**
     * Returns true if it is a final semester, false otherwise.
     *
     * @return true if it is a final semester, false otherwise
     */
    public boolean isFinalSemester() {
        return isFinalSemester;
    }

    /**
     * Returns attestation form for the subject.
     *
     * @return attestation form for the subject
     */
    public AttestationForm getAttestationForm() {
        return grade.getAttestationForm();
    }

    /**
     * Returns String representation of Subject.
     *
     * @return String representation of Subject
     */
    @Override
    public String toString() {
        return name + " - " + grade + " - " + grade.getAttestationForm();
    }
}