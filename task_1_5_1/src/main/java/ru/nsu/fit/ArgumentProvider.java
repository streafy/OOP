package ru.nsu.fit;

import ru.nsu.fit.functions.Number;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Provides arguments for Functions.
 */
public class ArgumentProvider {
    Deque<Number> stack;

    /**
     * ArgumentProvider constructor.
     *
     * @param stack - Numbers stack
     */
    public ArgumentProvider(Deque<Number> stack) {
        this.stack = stack;
    }

    /**
     * Returns List with count Numbers
     *
     * @param count count of Numbers added to List
     * @return List with count Numbers
     */
    public List<Number> get(int count) {
        List<Number> arguments = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            arguments.add(stack.pop());
        }

        return arguments;
    }
}
