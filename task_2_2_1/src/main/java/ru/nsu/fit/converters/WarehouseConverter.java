package ru.nsu.fit.converters;

import ru.nsu.fit.implementations.WarehouseImpl;
import ru.nsu.fit.interfaces.Warehouse;
import ru.nsu.fit.models.WarehouseModel;

public class WarehouseConverter {

    public WarehouseConverter() {

    }

    public static Warehouse convert(WarehouseModel from) {
        return new WarehouseImpl(from.getCapacity());
    }
}
