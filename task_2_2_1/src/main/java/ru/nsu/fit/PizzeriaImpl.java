package ru.nsu.fit;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PizzeriaImpl implements Pizzeria {

    private final Queue<Order> orderQueue = new ArrayDeque<>();
    private final List<Baker> bakers = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();

    @Override
    public void orderPizza(int count) {

    }
}
