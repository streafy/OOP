package ru.nsu.fit.utils.converters;

import ru.nsu.fit.pizzeria_implementation.warehouse.Warehouse;
import ru.nsu.fit.pizzeria_implementation.workers.Courier;
import ru.nsu.fit.utils.models.CourierModel;

public class CourierConverter {

    public CourierConverter() {

    }

    public static Thread convert(CourierModel from, Warehouse warehouse) {
        return new Thread(new Courier(from.getId(), from.getTrunkCapacity(), warehouse));
    }
}
