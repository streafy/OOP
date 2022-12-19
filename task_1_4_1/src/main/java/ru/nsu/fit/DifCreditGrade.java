package ru.nsu.fit;

/**
 * Grade value for a Subject in form of exam.
 */
public enum DifCreditGrade implements Grade {
    POOR(2),
    SATISFACTORY(3),
    GOOD(4),
    EXCELLENT(5);

    private final int gradeValue;
    private final AttestationForm attestationForm;

    /**
     * DifCreditGrade constructor.
     *
     * @param gradeValue integer value of the grade
     */
    DifCreditGrade(int gradeValue) {
        this.gradeValue = gradeValue;
        this.attestationForm = AttestationForm.DIF_CREDIT;
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
