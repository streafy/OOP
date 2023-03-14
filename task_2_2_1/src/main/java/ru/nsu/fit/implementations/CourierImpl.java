package ru.nsu.fit.implementations;

import ru.nsu.fit.Order;
import ru.nsu.fit.interfaces.Courier;
import ru.nsu.fit.interfaces.Warehouse;

public class CourierImpl implements Courier {

    private int id;
    private int trunkCapacity;
    private Warehouse warehouse;

    public CourierImpl(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void deliver() {
        if (warehouse.isEmpty()) {
            //Wait
        }
        Order order = warehouse.giveOrder();
        System.out.println("Courier #" + id + " delivering order #" + order.getId());
    }

    public int getId() {
        return id;
    }
}
