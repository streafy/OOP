package ru.nsu.fit;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayNonPrimeThreadChecker {

    private static final int LARGE_PRIME = 1000000007;
    private static final int NUMBERS_COUNT = 100000;

    private static final int[] benchmarkArray = new int[NUMBERS_COUNT];

    @Param({ "1", "2", "4", "6", "8", "12", "16" })
    private int threadCount;

    @Setup
    public void loadArray() {
        for (int i = 0; i < NUMBERS_COUNT; i++) {
            benchmarkArray[i] = LARGE_PRIME;
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ThreadCheckerBenchmark() {
        ArrayNonPrimeChecker tc = new ThreadChecker(threadCount);
        tc.hasNonPrime(benchmarkArray);
        return tc;
    }
}
