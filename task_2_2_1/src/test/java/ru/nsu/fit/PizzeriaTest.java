package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    private Pizzeria pizzeria;

    @BeforeEach
    public void init() {
        SynchronousQueue<Order> orderQueue = new SynchronousQueue<>();

        PizzeriaConfigDeserializer deserializer = new PizzeriaConfigDeserializer();
        PizzeriaConfig config = deserializer.deserialize("/config.json");

        pizzeria = new PizzeriaImpl(config, orderQueue);
        pizzeria.startWorking();
    }

    @Test
    public void ordersCompletionTest() {
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
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(OrderStatus.DELIVERED, order1.getStatus());
        Assertions.assertEquals(OrderStatus.DELIVERED, order2.getStatus());
        Assertions.assertEquals(OrderStatus.DELIVERED, order3.getStatus());
        Assertions.assertEquals(OrderStatus.DELIVERED, order4.getStatus());
        Assertions.assertEquals(OrderStatus.DELIVERED, order5.getStatus());
        Assertions.assertEquals(OrderStatus.DELIVERED, order6.getStatus());

        pizzeria.stopWorkingForced();
    }

    @Test
    public void forcedShutDownTest() {
        Order order1 = new Order(1, 1);
        pizzeria.orderPizza(order1);

        pizzeria.stopWorkingForced();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(OrderStatus.AWAITING_DELIVERY, order1.getStatus());
    }

    @Test
    public void softShutdownTest() {
        Order order1 = new Order(1, 1);
        pizzeria.orderPizza(order1);

        pizzeria.stopWorkingSoft();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(OrderStatus.DELIVERED, order1.getStatus());
    }
}
