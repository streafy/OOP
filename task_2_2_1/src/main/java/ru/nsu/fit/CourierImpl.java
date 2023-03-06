package ru.nsu.fit;

public class CourierImpl implements Courier{

    private Warehouse warehouse;

    public CourierImpl(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void deliver(Order order) {

    }
}
