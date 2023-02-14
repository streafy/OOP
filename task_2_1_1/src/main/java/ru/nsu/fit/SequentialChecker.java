package ru.nsu.fit;

public class SequentialChecker implements ArrayNonPrimeChecker {

    @Override
    public boolean hasNonPrime(int[] array) {
        for (int elem : array) {
            if (Shared.isNonPrime(elem)) {
                return true;
            }
        }
        return false;
    }
}
