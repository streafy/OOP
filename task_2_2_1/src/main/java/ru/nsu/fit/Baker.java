package ru.nsu.fit;

public interface Baker {

    void takeOrder(Order order);

    void makePizza();

    void reservePizzaStorage();

    void movePizzaToWarehouse();
}
