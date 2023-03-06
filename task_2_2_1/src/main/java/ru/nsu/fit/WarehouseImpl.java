package ru.nsu.fit;

public class WarehouseImpl implements Warehouse {

    private int capacity;
    private int currentLoad = 0;

    public WarehouseImpl(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean reservePizzaStorage(int pizzaCount) {
        return false;
    }

    @Override
    public void acceptPizza() {

    }

    @Override
    public void givePizza() {

    }
}
