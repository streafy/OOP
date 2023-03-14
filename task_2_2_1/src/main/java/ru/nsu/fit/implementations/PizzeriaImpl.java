package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.interfaces.Baker;
import ru.nsu.fit.interfaces.Courier;
import ru.nsu.fit.interfaces.Pizzeria;
import ru.nsu.fit.interfaces.Warehouse;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PizzeriaImpl implements Pizzeria {

    private static final int DEFAULT_WAREHOUSE_CAPACITY = 50;

    private final Queue<Order> orderQueue = new ArrayDeque<>();
    private final List<Baker> bakers = new ArrayList<>();     //TODO: load from json
    private final List<Courier> couriers = new ArrayList<>();
    private final Warehouse warehouse = new WarehouseImpl(DEFAULT_WAREHOUSE_CAPACITY);

    private int ordersTaken = 0;

    public PizzeriaImpl() {
        bakers.add(new BakerImpl(1, warehouse));
        couriers.add(new CourierImpl(warehouse));
    }

    @Override
    public void orderPizza(int count) {
        Order order = new Order(++ordersTaken, count);
        System.out.println("Accepted order #" + ordersTaken + " on " + count + " pizzas");

        orderQueue.add(order);

        bakers.get(0).takeOrder(order);

        couriers.get(0).deliver();

        System.out.println();
    }
}
