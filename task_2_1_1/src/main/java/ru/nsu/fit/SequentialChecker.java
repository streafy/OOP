package ru.nsu.fit;

import java.util.Arrays;

public class SequentialChecker implements ArrayNonPrimeChecker {

    @Override
    public boolean hasNonPrime(int[] array) {
        return Arrays.stream(array).anyMatch(Shared::isNonPrime);
    }
}