package ru.nsu.fit.functions;

import java.util.List;

/**
 * Function interface.
 */
public interface Function {
    /**
     * Returns arity of Function.
     *
     * @return arity of Function
     */
    int getArity();

    /**
     * Parses function from token.
     *
     * @param token String token
     * @return Function if it can be parsed from token, null otherwise
     */
    Function parse(String token);

    /**
     * Executes Function.
     *
     * @param arguments Function arguments
     * @return Number with value equals to result of Function
     */
    Number execute(List<Number> arguments);
}
