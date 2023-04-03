package ru.nsu.fit.utils.converters;

import ru.nsu.fit.pizzeria_implementation.order.Order;
import ru.nsu.fit.pizzeria_implementation.warehouse.Warehouse;
import ru.nsu.fit.pizzeria_implementation.workers.Baker;
import ru.nsu.fit.utils.models.BakerModel;

import java.util.concurrent.SynchronousQueue;

public class BakerConverter {

    public BakerConverter() {

    }

    public static Baker convert(BakerModel from, SynchronousQueue<Order> orderQueue, Warehouse warehouse) {
        return new Baker(from.getId(), from.getEfficiency(), orderQueue, warehouse);
    }
}
