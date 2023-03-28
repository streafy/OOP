package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.converters.BakerConverter;
import ru.nsu.fit.converters.CourierConverter;
import ru.nsu.fit.converters.WarehouseConverter;
import ru.nsu.fit.interfaces.Pizzeria;
import ru.nsu.fit.interfaces.Warehouse;
import ru.nsu.fit.serialization.PizzeriaConfig;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class PizzeriaImpl implements Pizzeria {

    private static final String ORDER_ACCEPTED_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is accepted at pizzeria%n";

    private final List<Thread> bakers;
    private final List<Thread> couriers;
    private final SynchronousQueue<Order> orderQueue;
    private final Warehouse warehouse;

    public PizzeriaImpl(PizzeriaConfig config, SynchronousQueue<Order> orderQueue) {
        this.warehouse = WarehouseConverter.convert(config.getWarehouse());
        this.bakers = config.getBakers()
                .stream()
                .map(bakerModel -> BakerConverter.convert(bakerModel, orderQueue, warehouse))
                .toList();
        this.couriers = config.getCouriers()
                .stream()
                .map(courierModel -> CourierConverter.convert(courierModel, warehouse))
                .toList();
        this.orderQueue = orderQueue;
    }

    @Override
    public void startWorking() {
        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);
    }

    @Override
    public void orderPizza(Order order) {
        int orderId = order.getId();
        System.out.printf(ORDER_ACCEPTED_MESSAGE_TEMPLATE, orderId, order.getStatus(), orderId);

        try {
            orderQueue.put(order);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopWorking() {
        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);
    }
}
