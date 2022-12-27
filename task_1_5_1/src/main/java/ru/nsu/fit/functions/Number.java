package ru.nsu.fit.functions;

import java.util.List;

public record Number(double value) implements Function {
    /**
     * Get value of Number
     *
     * @return double value of Number
     */
    @Override
    public double value() {
        return value;
    }

    /**
     * Returns arity of Function.
     *
     * @return arity of Function
     */
    @Override
    public int getArity() {
        return 0;
    }

    /**
     * Parses function from token.
     *
     * @param token String token
     * @return Function if it can be parsed from token, null otherwise
     */
    @Override
    public Function parse(String token) {
        Number number;
        try {
            number = new Number(Double.parseDouble(token));
        } catch (NumberFormatException e) {
            return null;
        }
        return number;
    }

    /**
     * Executes Function.
     *
     * @param arguments Function arguments
     * @return Number with value equals to result of Function
     */
    @Override
    public Number execute(List<Number> arguments) {
        return new Number(value);
    }
}
