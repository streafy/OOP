package ru.nsu.fit;

import ru.nsu.fit.implementations.PizzeriaImpl;
import ru.nsu.fit.interfaces.Pizzeria;

public class Main {

    public static void main(String[] args) {
        Pizzeria pizzeria = new PizzeriaImpl();

        pizzeria.orderPizza(2);
    }
}
