package ru.nsu.fit.interfaces;

import ru.nsu.fit.Order;

public interface Warehouse {

    boolean reservePizzaStorage(int pizzaCount);

    void acceptPizza(int number, Order order);

    Order giveOrder();

    boolean isEmpty();
}
