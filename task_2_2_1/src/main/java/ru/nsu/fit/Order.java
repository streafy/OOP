package ru.nsu.fit;

public class Order {

    private final int id;
    private OrderStatus status = OrderStatus.ACCEPTED;
    private final int pizzaCount;

    public Order(int id, int pizzaCount) {
        this.id = id;
        this.pizzaCount = pizzaCount;
    }

    public void changeStatus(OrderStatus newStatus) {
        this.status = newStatus;
    }

    public int getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }
 }
