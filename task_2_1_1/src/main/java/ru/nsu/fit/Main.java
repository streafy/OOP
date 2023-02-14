package ru.nsu.fit;

public class Main {

    private static final int THREAD_COUNT = 6;

    public static void main(String[] args) {
        int[] simple_array = {6, 8, 7, 13, 9, 4};
        int[] array = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};

        SequentialChecker sc = new SequentialChecker();
        ThreadChecker tc = new ThreadChecker(THREAD_COUNT);
        ParallelStreamChecker pc = new ParallelStreamChecker();

        System.out.println(sc.hasNonPrime(simple_array));
        System.out.println(tc.hasNonPrime(simple_array));
        System.out.println(pc.hasNonPrime(simple_array));

        System.out.println(sc.hasNonPrime(array));
        System.out.println(tc.hasNonPrime(array));
        System.out.println(pc.hasNonPrime(array));
    }
}