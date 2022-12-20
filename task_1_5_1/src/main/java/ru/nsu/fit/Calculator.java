package ru.nsu.fit;

import java.util.*;

/**
 * Prefix calculator implementation.
 */
public class Calculator {
    Deque<Double> stack = new ArrayDeque<>();

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

        Scanner scanner = new Scanner(reversed);
        scanner.useLocale(Locale.US);

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                stack.push(scanner.nextDouble());
            } else {
                String token = scanner.next();

                if (isFunction(token)) {
                    if (stack.isEmpty()) {
                        scanner.close();
                        throw new IllegalArgumentException("Incorrect expression - too many operations or functions");
                    }

                    double operand = stack.pop();

                    switch (token) {
                        case "log":
                            stack.push(Math.log(operand));
                            break;
                        case "pow":
                            if (stack.isEmpty()) {
                                scanner.close();
                                throw new IllegalArgumentException("Incorrect expression - too many operations or functions");
                            }
                            double power = stack.pop();
                            stack.push(Math.pow(operand, power));
                            break;
                        case "sqrt":
                            stack.push(Math.sqrt(operand));
                            break;
                        case "sin":
                            stack.push(Math.sin(operand));
                            break;
                        case "cos":
                            stack.push(Math.cos(operand));
                            break;
                    }
                } else {
                    if (stack.isEmpty()) {
                        scanner.close();
                        throw new IllegalArgumentException("Incorrect expression - too many operations or functions");
                    }
                    double operand1 = stack.pop();

                    if (stack.isEmpty()) {
                        scanner.close();
                        throw new IllegalArgumentException("Incorrect expression - too many operations or functions");
                    }
                    double operand2 = stack.pop();

                    switch (token) {
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
        }
        return stack.pop();
    }

    private boolean isFunction(String token) {
        Set<String> functions = new HashSet<>(Arrays.asList("log", "pow", "sqrt", "sin", "cos"));
        return functions.contains(token);
    }
}
