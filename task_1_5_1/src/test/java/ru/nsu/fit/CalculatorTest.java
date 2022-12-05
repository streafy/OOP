package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    @Test
    public void test() {
        Calculator calculator = new Calculator();

        Assertions.assertEquals(100.0, calculator.evaluate("+ 25 75"));
        Assertions.assertEquals(0.0, calculator.evaluate("+ - 1 2 1"));
        Assertions.assertEquals(1.0, calculator.evaluate("/ - 22 * 6 2 10"));
        Assertions.assertEquals(2.6, calculator.evaluate("+ 1.5 1.1"));
    }
}
