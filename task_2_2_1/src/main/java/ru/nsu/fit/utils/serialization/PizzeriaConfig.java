package ru.nsu.fit.utils.serialization;

import ru.nsu.fit.utils.models.BakerModel;
import ru.nsu.fit.utils.models.CourierModel;
import ru.nsu.fit.utils.models.WarehouseModel;

import java.util.List;

public class PizzeriaConfig {
    private final List<BakerModel> bakers;
    private final List<CourierModel> couriers;
    private final WarehouseModel warehouse;

    public PizzeriaConfig(List<BakerModel> bakers, List<CourierModel> couriers, WarehouseModel warehouse) {
        this.bakers = bakers;
        this.couriers = couriers;
        this.warehouse = warehouse;
    }

    public List<BakerModel> getBakers() {
        return bakers;
    }

    public List<CourierModel> getCouriers() {
        return couriers;
    }

    public WarehouseModel getWarehouse() {
        return warehouse;
    }
}
