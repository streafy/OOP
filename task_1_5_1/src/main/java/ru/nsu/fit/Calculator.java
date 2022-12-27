package ru.nsu.fit;

import ru.nsu.fit.functions.*;
import ru.nsu.fit.functions.Number;

import java.util.*;

/**
 * Prefix calculator implementation.
 */
public class Calculator {
    Deque<Number> stack = new ArrayDeque<>();
    List<Function> templates = new ArrayList<>();

    public Calculator() {
        templates.add(new Number(0));
        templates.add(new Plus());
        templates.add(new Minus());
        templates.add(new Multiply());
        templates.add(new Divide());
        templates.add(new Sin());
        templates.add(new Cos());
        templates.add(new Pow());
        templates.add(new Sqrt());
        templates.add(new Log());
    }

    /**
     * Evaluates prefix arithmetic expression.
     *
     * @param expression arithmetic expression to evaluate
     * @return double value of evaluated expression
     */
    public double evaluate(String expression) {
        List<String> tokens = Arrays.asList(expression.split("\\s"));
        Collections.reverse(tokens);

        ArgumentProvider ap = new ArgumentProvider(stack);

        for (String token : tokens) {
            for (Function template : templates) {
                Function function;
                if ((function = template.parse(token)) != null) {
                    stack.push(function.execute(ap.get(function.getArity())));
                }
            }
        }

        Number result = stack.pop();
        return result.execute(ap.get(result.getArity())).value();
    }
}
