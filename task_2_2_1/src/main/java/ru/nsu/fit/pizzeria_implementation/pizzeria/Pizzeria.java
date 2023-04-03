package ru.nsu.fit.pizzeria_implementation.pizzeria;

import ru.nsu.fit.pizzeria_implementation.order.Order;

public interface Pizzeria {

    void startWorking();

    void orderPizza(Order order);

    void stopWorkingForced();

    void stopWorkingSoft();
}
