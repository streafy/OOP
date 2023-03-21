package ru.nsu.fit;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadChecker implements ArrayNonPrimeChecker {

    private final int threadCount;

    public ThreadChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    private Runnable createRunnable(AtomicBoolean hasNonPrime, int[] array, int left, int right) {
        return () -> {
            for (int i = left; i < right; i++) {
                if (Shared.isNonPrime(array[i])) {
                    hasNonPrime.set(true);
                    break;
                }
            }
        };
    }

    @Override
    public boolean hasNonPrime(int[] array) {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);

        int interval = array.length / threadCount;
        int rest = array.length % threadCount;

        List<Thread> threads = Stream.iterate(0, i -> i + 1)
                                     .limit(threadCount)
                                     .map(i -> {
                                         int left = i * interval;
                                         int right = (i + 1) * interval + ((i + 1) == threadCount ? rest : 0);
                                         return new Thread(createRunnable(hasNonPrime, array, left, right));
                                     })
                                     .peek(Thread::start)
                                     .collect(Collectors.toList());

        while (true) {
            if (!threads.stream().allMatch(Thread::isAlive)) {
                break;
            }
            if (hasNonPrime.get()) {
                threads.forEach(Thread::interrupt);
                break;
            }
        }

        return hasNonPrime.get();
    }
}