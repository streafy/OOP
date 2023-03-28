package ru.nsu.fit.interfaces;

import ru.nsu.fit.Order;

public interface Warehouse {

    void acceptOrder(Order order);

    Order giveOrder();

    boolean isEmpty();
}
