package ru.nsu.fit.utils.converters;

import ru.nsu.fit.pizzeria_implementation.warehouse.Warehouse;
import ru.nsu.fit.pizzeria_implementation.warehouse.WarehouseImpl;
import ru.nsu.fit.utils.models.WarehouseModel;

public class WarehouseConverter {

    public WarehouseConverter() {

    }

    public static Warehouse convert(WarehouseModel from) {
        return new WarehouseImpl(from.getCapacity());
    }
}
