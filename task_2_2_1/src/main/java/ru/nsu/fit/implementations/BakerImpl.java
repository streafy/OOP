package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.OrderStatus;
import ru.nsu.fit.interfaces.Baker;
import ru.nsu.fit.interfaces.Warehouse;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class BakerImpl implements Baker, Runnable {

    private static final int DEFAULT_EFFICIENCY = 25;

    private static final String PIZZA_BAKING_MESSAGE_TEMPLATE = "[ORDER%s:%s] Baker %s is making pizza #%s for order %s%n";

    private final int id;
    private final int bakingTimeInSeconds;

    private final SynchronousQueue<Order> orderQueue;
    private final Warehouse warehouse;

    public BakerImpl(int id, SynchronousQueue<Order> orderQueue, Warehouse warehouse) {
        this(id, DEFAULT_EFFICIENCY, orderQueue, warehouse);
    }

    public BakerImpl(int id, int efficiency, SynchronousQueue<Order> orderQueue, Warehouse warehouse) {
        this.id = id;
        this.bakingTimeInSeconds = 100 / efficiency;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            try {
                Order order = orderQueue.take();
                makeOrder(order);

            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

    @Override
    public void makeOrder(Order order) {
        int orderId = order.getId();
        int pizzaCount = order.getPizzaCount();

        order.changeStatus(OrderStatus.COOKING);
        for (int i = 1; i <= pizzaCount; i++) {
            try {
                TimeUnit.SECONDS.sleep(bakingTimeInSeconds);
                System.out.printf(PIZZA_BAKING_MESSAGE_TEMPLATE, orderId, order.getStatus(), id, i, orderId);

            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }

        warehouse.acceptOrder(order);
    }
}
