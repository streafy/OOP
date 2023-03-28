package ru.nsu.fit.interfaces;

import ru.nsu.fit.Order;

public interface Pizzeria {

    void startWorking();

    void orderPizza(Order order);

    void stopWorking();
}
