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
        Subject s1 = new Subject("Intro to algebra and analysis", ExamGrade.GOOD, false);
        Subject s2 = new Subject("Intro to discrete math and mathematical logic", ExamGrade.GOOD, false);
        Subject s3 = new Subject("Declarative programming", DifCreditGrade.EXCELLENT, false);
        Subject s4 = new Subject("Imperative programming", DifCreditGrade.GOOD, false);
        Subject s5 = new Subject("Foreign language", CreditGrade.PASSED, false);
        Subject s6 = new Subject("History", DifCreditGrade.GOOD, true);
        Subject s7 = new Subject("Basics of speech culture", DifCreditGrade.EXCELLENT, true);
        Subject s8 = new Subject("Physical Culture and sport", CreditGrade.PASSED, false);
        Subject s9 = new Subject("Digital platforms", CreditGrade.PASSED, false);

        gb.addSubject(s1, 1);
        gb.addSubject(s2, 1);
        gb.addSubject(s3, 1);
        gb.addSubject(s4, 1);
        gb.addSubject(s5, 1);
        gb.addSubject(s6, 1);
        gb.addSubject(s7, 1);
        gb.addSubject(s8, 1);
        gb.addSubject(s9, 1);

        Subject s10 = new Subject("Intro to algebra and analysis", ExamGrade.GOOD, true);
        Subject s11 = new Subject("Intro to discrete math and mathematical logic", ExamGrade.GOOD, true);
        Subject s12 = new Subject("Declarative programming", DifCreditGrade.EXCELLENT, true);
        Subject s13 = new Subject("Imperative programming", ExamGrade.GOOD, true);
        Subject s14 = new Subject("Physical Culture and sport", CreditGrade.PASSED, false);
        Subject s15 = new Subject("Foreign language", DifCreditGrade.GOOD, false);
        Subject s16 = new Subject("Digital platforms", DifCreditGrade.GOOD, true);

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

    @Test
    public void testGetSubjectByType() {
        Assertions.assertEquals(4, gb.getSemesterSubjectsCountByType(AttestationForm.DIF_CREDIT, 1));
        Assertions.assertEquals(3, gb.getSemesterSubjectsCountByType(AttestationForm.CREDIT, 1));
        Assertions.assertEquals(2, gb.getSemesterSubjectsCountByType(AttestationForm.EXAM, 1));

        Assertions.assertEquals(3, gb.getSemesterSubjectsCountByType(AttestationForm.DIF_CREDIT, 2));
        Assertions.assertEquals(1, gb.getSemesterSubjectsCountByType(AttestationForm.CREDIT, 2));
        Assertions.assertEquals(3, gb.getSemesterSubjectsCountByType(AttestationForm.EXAM, 2));
    }
}
