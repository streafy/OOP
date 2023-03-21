package ru.nsu.fit;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayNonPrimeThreadChecker {

    private static final int LARGE_PRIME = 1000000007;
    private static final int LARGE_NON_PRIME = 1000000000;

    @Param({ "1", "2", "4", "6", "8", "12", "16" })
    private int threadCount;

    @Param({ "100", "1000", "10000", "100000" })
    private int arraySize;

    private int[] primeArray;
    private int[] mixedArray;

    @Setup
    public void setupData() {
        primeArray = new int[arraySize];
        mixedArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            primeArray[i] = LARGE_PRIME;
            mixedArray[i] = i == arraySize * 0.5 ? LARGE_NON_PRIME : LARGE_PRIME;
        }
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ThreadCheckerPrimeArrayBenchmark() {
        ArrayNonPrimeChecker tc = new ThreadChecker(threadCount);
        tc.hasNonPrime(primeArray);
        return tc;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ThreadCheckerMixedArrayBenchmark() {
        ArrayNonPrimeChecker tc = new ThreadChecker(threadCount);
        tc.hasNonPrime(mixedArray);
        return tc;
    }
}
