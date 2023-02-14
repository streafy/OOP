package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArrayNonPrimeCheckerTest {

    private static final int THREAD_COUNT = 6;

    private static Stream<ArrayNonPrimeChecker> arrayNonPrimeCheckerStream() {
        return Stream.of(new SequentialChecker(), new ThreadChecker(THREAD_COUNT), new ParallelStreamChecker());
    }

    @ParameterizedTest
    @MethodSource("arrayNonPrimeCheckerStream")
    public void simpleArrayTest(ArrayNonPrimeChecker arrayNonPrimeChecker) {
        int[] array = {6, 8, 7, 13, 9, 4};

        Assertions.assertTrue(arrayNonPrimeChecker.hasNonPrime(array));
    }

    @ParameterizedTest
    @MethodSource("arrayNonPrimeCheckerStream")
    public void arrayTest(ArrayNonPrimeChecker arrayNonPrimeChecker) {
        int[] array = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};

        Assertions.assertFalse(arrayNonPrimeChecker.hasNonPrime(array));
    }
}
