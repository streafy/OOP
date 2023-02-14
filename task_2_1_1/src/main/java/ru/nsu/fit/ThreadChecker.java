package ru.nsu.fit;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ThreadChecker implements ArrayNonPrimeChecker {

    private final int threadCount;

    public ThreadChecker(int threadCount) {
        this.threadCount = threadCount;
    }

    @Override
    public boolean hasNonPrime(int[] array) {
        AtomicBoolean hasNonPrime = new AtomicBoolean(false);
        AtomicInteger nextIndex = new AtomicInteger(0);

        Runnable r = () -> {
            while (nextIndex.get() < array.length) {
                int currentIndex = nextIndex.getAndIncrement();
                //System.out.println("Is non-prime: " + array[currentIndex] + " = " + Shared.isNonPrime(array[currentIndex]) + ", " + Thread.currentThread().getName());
                if (Shared.isNonPrime(array[currentIndex])) {
                    hasNonPrime.set(true);
                }
            }
        };

        List<Thread> threads = Stream.generate(() -> new Thread(r))
                                     .limit(threadCount)
                                     .peek(Thread::start)
                                     .toList();


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return hasNonPrime.get();
    }
}