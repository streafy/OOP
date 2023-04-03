package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.pizzeria_implementation.order.Order;
import ru.nsu.fit.pizzeria_implementation.order.OrderStatus;
import ru.nsu.fit.pizzeria_implementation.pizzeria.Pizzeria;
import ru.nsu.fit.pizzeria_implementation.pizzeria.PizzeriaImpl;
import ru.nsu.fit.utils.serialization.PizzeriaConfig;
import ru.nsu.fit.utils.serialization.PizzeriaConfigDeserializer;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class PizzeriaTest {

    @Test
    public void ordersCompletionTest() {
        SynchronousQueue<Order> orderQueue = new SynchronousQueue<>();

        PizzeriaConfigDeserializer deserializer = new PizzeriaConfigDeserializer();
        PizzeriaConfig config = deserializer.deserialize("/config.json");

        Pizzeria pizzeria = new PizzeriaImpl(config, orderQueue);

        pizzeria.startWorking();

        Order order1 = new Order(1, 1);
        pizzeria.orderPizza(order1);
        Order order2 = new Order(2, 1);
        pizzeria.orderPizza(order2);
        Order order3 = new Order(3, 1);
        pizzeria.orderPizza(order3);
        Order order4 = new Order(4, 1);
        pizzeria.orderPizza(order4);
        Order order5 = new Order(5, 1);
        pizzeria.orderPizza(order5);
        Order order6 = new Order(6, 1);
        pizzeria.orderPizza(order6);

        try {
            TimeUnit.SECONDS.sleep(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(order1.getStatus(), OrderStatus.DELIVERED);
        Assertions.assertEquals(order2.getStatus(), OrderStatus.DELIVERED);
        Assertions.assertEquals(order3.getStatus(), OrderStatus.DELIVERED);
        Assertions.assertEquals(order4.getStatus(), OrderStatus.DELIVERED);
        Assertions.assertEquals(order5.getStatus(), OrderStatus.DELIVERED);
        Assertions.assertEquals(order6.getStatus(), OrderStatus.DELIVERED);

        pizzeria.stopWorkingForced();
    }
}
