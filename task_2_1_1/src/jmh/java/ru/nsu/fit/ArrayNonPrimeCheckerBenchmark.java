package ru.nsu.fit;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayNonPrimeCheckerBenchmark {

    private static final int LARGE_PRIME = 1000000007;
    private static final int NUMBERS_COUNT = 100000;

    private static final int[] benchmarkArray = new int[NUMBERS_COUNT];

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
    public ArrayNonPrimeChecker SequentialCheckerBenchmark() {
        ArrayNonPrimeChecker sc = new SequentialChecker();
        sc.hasNonPrime(benchmarkArray);
        return sc;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ParallelStreamCheckerBenchmark() {
        ArrayNonPrimeChecker pc = new ParallelStreamChecker();
        pc.hasNonPrime(benchmarkArray);
        return pc;
    }
}
