package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.interfaces.Baker;
import ru.nsu.fit.interfaces.Warehouse;

public class BakerImpl implements Baker {

    private static final int DEFAULT_EFFICIENCY = 500;

    private final int id;
    private final int efficiency;

    private final Warehouse warehouse;

    public BakerImpl(int id, Warehouse warehouse) {
        this(id, DEFAULT_EFFICIENCY, warehouse);
    }

    public BakerImpl(int id, int efficiency, Warehouse warehouse) {
        this.id = id;
        this.efficiency = efficiency;
        this.warehouse = warehouse;
    }

    @Override
    public void takeOrder(Order order) {
        int pizzaCount = order.getPizzaCount();

        reservePizzaStorage(pizzaCount);

        for (int i = 1; i <= pizzaCount; i++) {
            makePizza(i);
            movePizzaToWarehouse(i, order);
        }
    }


    @Override
    public void reservePizzaStorage(int pizzaCount) {
        if (!warehouse.reservePizzaStorage(pizzaCount)) {
            //Wait?
        }
        System.out.println("Baker #" + id + " reserved storage for " + pizzaCount + "pizzas");
    }

    private void makePizza(int number) {
        System.out.println("Baker #" + id + " is making pizza #" + number);
    }

    public void movePizzaToWarehouse(int number, Order order) {
        System.out.println("Baker #" + id + " moved pizza to warehouse");
        warehouse.acceptPizza(number, order);
    }
}
