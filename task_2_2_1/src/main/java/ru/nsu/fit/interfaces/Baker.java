package ru.nsu.fit.interfaces;

import ru.nsu.fit.Order;

public interface Baker {

    void takeOrder(Order order);

    void reservePizzaStorage(int pizzaCount);

    void movePizzaToWarehouse(int number, Order order);
}
