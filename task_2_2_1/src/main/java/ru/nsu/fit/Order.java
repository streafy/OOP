package ru.nsu.fit;

public class Order {

    private int id;
    private OrderStatus status = OrderStatus.TAKEN;

    public Order(int id) {
        this.id = id;
    }

    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }
}
