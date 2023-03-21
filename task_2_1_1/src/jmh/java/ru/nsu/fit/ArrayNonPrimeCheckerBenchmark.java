package ru.nsu.fit;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ArrayNonPrimeCheckerBenchmark {

    private static final int LARGE_PRIME = 1000000007;
    private static final int LARGE_NON_PRIME = 1000000000;

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
    public ArrayNonPrimeChecker SequentialCheckerPrimeArrayBenchmark() {
        ArrayNonPrimeChecker sc = new SequentialChecker();
        sc.hasNonPrime(primeArray);
        return sc;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker SequentialCheckerMixedArrayBenchmark() {
        ArrayNonPrimeChecker sc = new SequentialChecker();
        sc.hasNonPrime(mixedArray);
        return sc;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ParallelStreamCheckerPrimeArrayBenchmark() {
        ArrayNonPrimeChecker pc = new ParallelStreamChecker();
        pc.hasNonPrime(primeArray);
        return pc;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    public ArrayNonPrimeChecker ParallelStreamCheckerMixedArrayBenchmark() {
        ArrayNonPrimeChecker pc = new ParallelStreamChecker();
        pc.hasNonPrime(mixedArray);
        return pc;
    }
}
