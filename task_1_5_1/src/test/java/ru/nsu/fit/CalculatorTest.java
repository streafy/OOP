package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator = new Calculator();

    @Test
    public void testOperations() {
        Assertions.assertEquals(100.0, calculator.evaluate("+ 25 75"));
        Assertions.assertEquals(0.0, calculator.evaluate("+ - 1 2 1"));
        Assertions.assertEquals(1.0, calculator.evaluate("/ - 22 * 6 2 10"));
        Assertions.assertEquals(2.6, calculator.evaluate("+ 1.5 1.1"));
    }

    @Test
    public void testFunctions() {
        Assertions.assertEquals(0.0, calculator.evaluate("sin 0"));
        Assertions.assertEquals(1.0, calculator.evaluate("cos 0"));
        Assertions.assertEquals(4.0, calculator.evaluate("pow 2 2"));
        Assertions.assertEquals(2.0, calculator.evaluate("sqrt 4"));
    }
}
