package ru.nsu.fit.converters;

import ru.nsu.fit.Order;
import ru.nsu.fit.implementations.BakerImpl;
import ru.nsu.fit.interfaces.Warehouse;
import ru.nsu.fit.models.BakerModel;

import java.util.concurrent.SynchronousQueue;

public class BakerConverter {

    public BakerConverter() {

    }

    public static Thread convert(BakerModel from, SynchronousQueue<Order> orderQueue, Warehouse warehouse) {
        return new Thread(new BakerImpl(from.getId(), from.getEfficiency(), orderQueue, warehouse));
    }
}
