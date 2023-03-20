package ru.nsu.fit;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayNonPrimeThreadChecker {

    private static final int LARGE_PRIME = 1000000007;

    @Param({ "1", "2", "4", "6", "8", "12", "16" })
    private int threadCount;

    @Param({ "100", "1000", "10000", "100000" })
    private int arraySize;

    private int[] benchmarkArray;

    @Setup
    public void loadArray() {
        benchmarkArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
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
