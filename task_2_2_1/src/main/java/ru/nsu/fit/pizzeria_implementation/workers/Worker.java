package ru.nsu.fit.pizzeria_implementation.workers;

public abstract class Worker {

    protected boolean isForcedShutdown = false;
    protected boolean isSoftShutdown = false;

    public void forcedShutdown() {
        this.isForcedShutdown = true;
    }

    public void softShutdown() {
        this.isSoftShutdown = true;
    }
}
