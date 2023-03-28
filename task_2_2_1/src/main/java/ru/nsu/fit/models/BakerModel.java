package ru.nsu.fit.models;

public class BakerModel {

    private final int id;
    private final int efficiency;

    public BakerModel(int id, int efficiency) {
        this.id = id;
        this.efficiency = efficiency;
    }

    public int getId() {
        return id;
    }

    public int getEfficiency() {
        return efficiency;
    }
}
