package ru.nsu.fit.converters;

import ru.nsu.fit.implementations.CourierImpl;
import ru.nsu.fit.interfaces.Warehouse;
import ru.nsu.fit.models.CourierModel;

public class CourierConverter {

    public CourierConverter() {

    }

    public static Thread convert(CourierModel from, Warehouse warehouse) {
        return new Thread(new CourierImpl(from.getId(), from.getTrunkCapacity(), warehouse));
    }
}
