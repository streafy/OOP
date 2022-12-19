package ru.nsu.fit;

/**
 * Interface for different grade types.
 */
public interface Grade {
    /**
     * Convert Grade to int to calculate average grade.
     *
     * @return integer value of Grade
     */
    int toInt();

    /**
     * Returns attestation form for the grade.
     *
     * @return attestation form for the grade
     */
    AttestationForm getAttestationForm();
}
