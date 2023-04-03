package ru.nsu.fit.pizzeria_implementation.warehouse;

import ru.nsu.fit.pizzeria_implementation.order.Order;

public interface Warehouse {

    void acceptOrder(Order order);

    Order giveOrder();

    boolean isEmpty();
}
