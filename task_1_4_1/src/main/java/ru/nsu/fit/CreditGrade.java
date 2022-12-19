package ru.nsu.fit;

/**
 * Grade value for a Subject in form of exam.
 */
public enum CreditGrade implements Grade {
    FAILED(2),
    PASSED(5);

    private final int gradeValue;
    private final AttestationForm attestationForm;

    /**
     * Credit Grade constructor.
     *
     * @param gradeValue integer value of the grade
     */
    CreditGrade(int gradeValue) {
        this.gradeValue = gradeValue;
        this.attestationForm = AttestationForm.CREDIT;
    }

    /**
     * Convert Grade to int to calculate average grade.
     *
     * @return integer value of Grade
     */
    @Override
    public int toInt() {
        return gradeValue;
    }

    /**
     * Returns attestation form for the grade.
     *
     * @return attestation form for the grade
     */
    @Override
    public AttestationForm getAttestationForm() {
        return attestationForm;
    }
}
