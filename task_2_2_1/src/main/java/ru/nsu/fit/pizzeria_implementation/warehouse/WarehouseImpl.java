package ru.nsu.fit.pizzeria_implementation.warehouse;

import ru.nsu.fit.pizzeria_implementation.order.Order;
import ru.nsu.fit.pizzeria_implementation.order.OrderStatus;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class WarehouseImpl implements Warehouse {

    private static final String ORDER_ACCEPTED_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is accepted at warehouse%n";

    private final int capacity;
    private final Queue<Order> readyOrders;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
        this.readyOrders = new ArrayDeque<>(capacity);
    }

    @Override
    public synchronized void acceptOrder(Order order) {
        int orderId = order.getId();
        try {
            while (this.readyOrders.size() >= this.capacity) {
                super.wait();
            }

            this.readyOrders.add(order);
            order.changeStatus(OrderStatus.AWAITING_DELIVERY);
            System.out.printf(ORDER_ACCEPTED_MESSAGE_TEMPLATE, orderId, order.getStatus(), orderId);
            super.notifyAll();
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    @Override
    public synchronized Order giveOrder() {
        try {
            while (this.readyOrders.isEmpty()) {
                super.wait();
            }

            Order givenOrder = this.readyOrders.poll();
            super.notifyAll();
            return givenOrder;
        } catch (InterruptedException e) {
            currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return readyOrders.isEmpty();
    }
}
