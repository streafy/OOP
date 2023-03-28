package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.OrderStatus;
import ru.nsu.fit.interfaces.Courier;
import ru.nsu.fit.interfaces.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.currentThread;

public class CourierImpl implements Courier, Runnable {

    private static final int DELIVERY_TIME_IN_SECONDS = 5;
    private static final String ORDER_DELIVERING_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is delivering by courier %s%n";
    private static final String ORDER_DELIVERED_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is delivered by courier %s%n";

    private final int id;
    private final int trunkCapacity;
    private final List<Order> trunkContent = new ArrayList<>();
    private final Warehouse warehouse;

    public CourierImpl(int id, int trunkCapacity, Warehouse warehouse) {
        this.id = id;
        this.trunkCapacity = trunkCapacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (!currentThread().isInterrupted()) {
            while (!warehouse.isEmpty() && trunkContent.size() < trunkCapacity) {
                Order nextOrder = warehouse.giveOrder();
                trunkContent.add(nextOrder);
            }
            trunkContent.forEach(this::deliver);
            trunkContent.clear();
        }
    }

    @Override
    public void deliver(Order order) {
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
