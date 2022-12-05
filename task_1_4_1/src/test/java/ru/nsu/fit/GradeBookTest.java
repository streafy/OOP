package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class GradeBookTest {
    private final GradeBook gb = new GradeBook(123456,
            "Ivan Badin",
            "Informatics and Computer Engineering",
            3,
            new HashMap<>());

    @BeforeEach
    public void init() {
        Subject s1 = new Subject("Intro to algebra and analysis", Grade.GOOD, AttestationForm.EXAM, false);
        Subject s2 = new Subject("Intro to discrete math and mathematical logic", Grade.GOOD, AttestationForm.EXAM, false);
        Subject s3 = new Subject("Declarative programming", Grade.EXCELLENT, AttestationForm.DIF_CREDIT, false);
        Subject s4 = new Subject("Imperative programming", Grade.GOOD, AttestationForm.DIF_CREDIT, false);
        Subject s5 = new Subject("Foreign language", Grade.PASSED, AttestationForm.CREDIT, false);
        Subject s6 = new Subject("History", Grade.GOOD, AttestationForm.DIF_CREDIT, true);
        Subject s7 = new Subject("Basics of speech culture", Grade.EXCELLENT, AttestationForm.DIF_CREDIT, true);
        Subject s8 = new Subject("Physical Culture and sport", Grade.PASSED, AttestationForm.CREDIT, false);
        Subject s9 = new Subject("Digital platforms", Grade.PASSED, AttestationForm.CREDIT, false);

        gb.addSubject(s1, 1);
        gb.addSubject(s2, 1);
        gb.addSubject(s3, 1);
        gb.addSubject(s4, 1);
        gb.addSubject(s5, 1);
        gb.addSubject(s6, 1);
        gb.addSubject(s7, 1);
        gb.addSubject(s8, 1);
        gb.addSubject(s9, 1);

        Subject s10 = new Subject("Intro to algebra and analysis", Grade.GOOD, AttestationForm.EXAM, true);
        Subject s11 = new Subject("Intro to discrete math and mathematical logic", Grade.GOOD, AttestationForm.EXAM, true);
        Subject s12 = new Subject("Declarative programming", Grade.EXCELLENT, AttestationForm.DIF_CREDIT, true);
        Subject s13 = new Subject("Imperative programming", Grade.GOOD, AttestationForm.EXAM, true);
        Subject s14 = new Subject("Physical Culture and sport", Grade.PASSED, AttestationForm.CREDIT, false);
        Subject s15 = new Subject("Foreign language", Grade.GOOD, AttestationForm.DIF_CREDIT, false);
        Subject s16 = new Subject("Digital platforms", Grade.GOOD, AttestationForm.DIF_CREDIT, true);

        gb.addSubject(s10, 2);
        gb.addSubject(s11, 2);
        gb.addSubject(s12, 2);
        gb.addSubject(s13, 2);
        gb.addSubject(s14, 2);
        gb.addSubject(s15, 2);
        gb.addSubject(s16, 2);

        System.out.println(gb);
    }

    @Test
    public void testAverageGrades() {
        Assertions.assertEquals(4.4, (double) Math.round(gb.getOverallAverageGrade() * 10) / 10);
        Assertions.assertEquals(4.3, (double) Math.round(gb.getDiplomaAverageGrade() * 10) / 10);
    }

    @Test
    public void testRedDiploma() {
        Assertions.assertFalse(gb.isRedDiploma());
    }

    @Test
    public void testScholarship() {
        Assertions.assertTrue(gb.hasScholarship());
    }
}
