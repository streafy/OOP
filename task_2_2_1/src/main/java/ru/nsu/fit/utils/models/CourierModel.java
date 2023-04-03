package ru.nsu.fit.utils.models;

public class CourierModel {

    private final int id;
    private final int trunkCapacity;

    public CourierModel(int id, int trunkCapacity) {
        this.id = id;
        this.trunkCapacity = trunkCapacity;
    }

    public int getId() {
        return id;
    }

    public int getTrunkCapacity() {
        return trunkCapacity;
    }
}
