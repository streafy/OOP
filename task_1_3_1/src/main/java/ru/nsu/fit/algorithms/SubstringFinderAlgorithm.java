package ru.nsu.fit.algorithms;

import java.util.List;

/**
 * Interface for substring finding algorithms.
 */
public interface SubstringFinderAlgorithm {
    /**
     * Processes next char from buffered input.
     *
     * @param c character to process
     */
    void processChar(char c);

    /**
     * Get a list with indices of all occurrences of substring in a given string.
     *
     * @return list with indices of all occurrences of substring in given string.
     */
    List<Integer> getSubstringIndices();
}
