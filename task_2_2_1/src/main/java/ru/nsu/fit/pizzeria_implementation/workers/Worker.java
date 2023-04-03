package ru.nsu.fit.pizzeria_implementation.workers;

public abstract class Worker {

    protected boolean isSoftShutdown = false;

    public void softShutdown() {
        this.isSoftShutdown = true;
    }
}
