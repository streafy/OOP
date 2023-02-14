package ru.nsu.fit;

import org.openjdk.jmh.annotations.Benchmark;

public class ArrayNonPrimeCheckerBenchmark {

    private static final int[] benchmarkArray = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
    private static final int THREAD_COUNT = 6;

    @Benchmark
    public void SequentialCheckerBenchmark() {
        ArrayNonPrimeChecker sc = new SequentialChecker();
        sc.hasNonPrime(benchmarkArray);
    }

    @Benchmark
    public void ThreadCheckerBenchmark() {
        ArrayNonPrimeChecker sc = new ThreadChecker(THREAD_COUNT);
        sc.hasNonPrime(benchmarkArray);
    }

    @Benchmark
    public void ParallelStreamCheckerBenchmark() {
        ArrayNonPrimeChecker pc = new ParallelStreamChecker();
        pc.hasNonPrime(benchmarkArray);
    }
}
