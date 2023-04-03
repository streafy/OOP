package ru.nsu.fit.pizzeria_implementation.workers;

import ru.nsu.fit.pizzeria_implementation.order.Order;
import ru.nsu.fit.pizzeria_implementation.order.OrderStatus;
import ru.nsu.fit.pizzeria_implementation.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class Courier extends Worker implements Runnable  {

    private static final int DELIVERY_TIME_IN_SECONDS = 5;
    private static final String ORDER_DELIVERING_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is delivering by courier %s%n";
    private static final String ORDER_DELIVERED_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is delivered by courier %s%n";

    private final int id;
    private final int trunkCapacity;
    private final List<Order> trunkContent = new ArrayList<>();
    private final Warehouse warehouse;

    public Courier(int id, int trunkCapacity, Warehouse warehouse) {
        this.id = id;
        this.trunkCapacity = trunkCapacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted() && !isSoftShutdown) {
            while (!warehouse.isEmpty() && trunkContent.size() < trunkCapacity) {
                Order nextOrder = warehouse.giveOrder();
                trunkContent.add(nextOrder);
            }
            trunkContent.forEach(this::deliver);
            trunkContent.clear();
        }
    }

    private void deliver(Order order) {
        int orderId = order.getId();

        order.changeStatus(OrderStatus.DELIVERING);
        System.out.printf(ORDER_DELIVERING_MESSAGE_TEMPLATE, orderId, order.getStatus(), orderId, id);

        try {
            TimeUnit.SECONDS.sleep(DELIVERY_TIME_IN_SECONDS);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }

        order.changeStatus(OrderStatus.DELIVERED);
        System.out.printf(ORDER_DELIVERED_MESSAGE_TEMPLATE, orderId, order.getStatus(), orderId, id);


    }
}
