package ru.nsu.fit.pizzeria_implementation.pizzeria;

import ru.nsu.fit.pizzeria_implementation.order.Order;
import ru.nsu.fit.pizzeria_implementation.warehouse.Warehouse;
import ru.nsu.fit.pizzeria_implementation.workers.Baker;
import ru.nsu.fit.pizzeria_implementation.workers.Courier;
import ru.nsu.fit.pizzeria_implementation.workers.Worker;
import ru.nsu.fit.utils.converters.BakerConverter;
import ru.nsu.fit.utils.converters.CourierConverter;
import ru.nsu.fit.utils.converters.WarehouseConverter;
import ru.nsu.fit.utils.serialization.PizzeriaConfig;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class PizzeriaImpl implements Pizzeria {

    private static final String ORDER_ACCEPTED_MESSAGE_TEMPLATE = "[ORDER%s:%s] Order %s is accepted at pizzeria%n";

    private final List<Baker> bakers;
    private final List<Courier> couriers;
    private final List<Thread> bakersThreads;
    private final List<Thread> couriersThreads;
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
        this.bakersThreads = bakers.stream()
                                   .map(Thread::new)
                                   .toList();
        this.couriersThreads = couriers.stream()
                                       .map(Thread::new)
                                       .toList();
        this.orderQueue = orderQueue;
    }

    @Override
    public void startWorking() {
        bakersThreads.forEach(Thread::start);
        couriersThreads.forEach(Thread::start);
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
    public void stopWorkingForced() {
        bakers.forEach(Worker::forcedShutdown);
        couriers.forEach(Worker::forcedShutdown);
    }

    @Override
    public void stopWorkingSoft() {
        bakers.forEach(Worker::softShutdown);
        couriers.forEach(Worker::softShutdown);
    }
}
