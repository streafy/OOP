package ru.nsu.fit;

import java.util.*;


//todo: add support for functions (log, pow, sqrt, sin, cos).
/**
 * Prefix calculator implementation.
 */
public class Calculator {
    /**
     * Evaluates prefix arithmetic expression.
     *
     * @param expression arithmetic expression to evaluate
     * @return double value of evaluated expression
     */
    public double evaluate(String expression) {
        List<String> tokens = Arrays.asList(expression.split("\\s"));
        Collections.reverse(tokens);
        String reversed = String.join(" ", tokens);

        Deque<Double> stack = new ArrayDeque<>();
        Scanner scanner = new Scanner(reversed);
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                stack.push(scanner.nextDouble());
            } else {
                String operation = scanner.next();

                if (stack.isEmpty()) {
                    scanner.close();
                    throw new IllegalArgumentException("Incorrect expression - too many operations");
                }
                double operand1 = stack.pop();

                if (stack.isEmpty()) {
                    scanner.close();
                    throw new IllegalArgumentException("Incorrect expression - too many operations");
                }
                double operand2 = stack.pop();

                switch (operation) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
