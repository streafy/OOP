package ru.nsu.fit;

import java.util.Arrays;

public class ParallelStreamChecker implements ArrayNonPrimeChecker {

    @Override
    public boolean hasNonPrime(int[] array) {
        return Arrays.stream(array).parallel().anyMatch(Shared::isNonPrime);
    }
}
