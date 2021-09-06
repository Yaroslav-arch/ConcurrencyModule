package ua.lysenko.entity;

import java.util.concurrent.atomic.AtomicInteger;


public class Counter {
    private static AtomicInteger counter = new AtomicInteger();

    public static void incrementCounter() {
        counter.incrementAndGet();
    }

    public static int getCounter() {
        return counter.get();
    }
}
