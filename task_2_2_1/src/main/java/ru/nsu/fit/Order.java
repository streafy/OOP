package ru.nsu.fit;

public class Order {

    private int id;
    private OrderStatus status = OrderStatus.TAKEN;
    private int pizzaCount;

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
