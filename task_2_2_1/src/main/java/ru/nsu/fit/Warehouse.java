package ru.nsu.fit;

public interface Warehouse {

    boolean reservePizzaStorage(int pizzaCount);

    void acceptPizza();

    void givePizza();
}
