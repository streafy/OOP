package ru.nsu.fit;

public class Shared {

    public static boolean isNonPrime(int elem) {
        for (int i = 2; i * i <= elem; i++) {
            if (elem % i == 0) {
                return true;
            }
        }
        return false;
    }
}
