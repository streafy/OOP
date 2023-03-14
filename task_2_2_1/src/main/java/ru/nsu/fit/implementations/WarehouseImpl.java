package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.interfaces.Warehouse;

import java.util.ArrayDeque;
import java.util.Queue;

public class WarehouseImpl implements Warehouse {

    private int capacity;
    private int currentLoad = 0;

    private final Queue<Order> readyOrders = new ArrayDeque<>();

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean reservePizzaStorage(int pizzaCount) {
        if (currentLoad + pizzaCount > capacity) {
            return false;
        }
        currentLoad += pizzaCount;
        return true;
    }

    @Override
    public void acceptPizza(int number, Order order) {
        int orderId = order.getId();
        System.out.println("Warehouse accepted pizza #" + number + " from order #" + orderId);

        if (number == order.getPizzaCount()) {
            System.out.println("Order #" + orderId + " is ready, waiting for delivery");
            readyOrders.add(order);
        }
    }

    @Override
    public Order giveOrder() {
        Order order = readyOrders.remove();
        int pizzaCount = order.getPizzaCount();

        System.out.println("Order #" + order.getId() + " with " + pizzaCount + " pizzas was given to courier");
        currentLoad -= pizzaCount;

        return order;
    }

    @Override
    public boolean isEmpty() {
        return currentLoad == 0;
    }
}
