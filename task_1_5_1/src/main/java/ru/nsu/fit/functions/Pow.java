package ru.nsu.fit.functions;

import java.util.List;

public class Pow implements Function {
    /**
     * Returns arity of Function.
     *
     * @return arity of Function
     */
    @Override
    public int getArity() {
        return 2;
    }

    /**
     * Parses function from token.
     *
     * @param token String token
     * @return Function if it can be parsed from token, null otherwise
     */
    @Override
    public Function parse(String token) {
        return token.equals("pow") ? new Pow() : null;
    }

    /**
     * Executes Function.
     *
     * @param arguments Function arguments
     * @return Number with value equals to result of Function
     */
    @Override
    public Number execute(List<Number> arguments) {
        return new Number(Math.pow(arguments.get(0).value(), arguments.get(1).value()));
    }
}
