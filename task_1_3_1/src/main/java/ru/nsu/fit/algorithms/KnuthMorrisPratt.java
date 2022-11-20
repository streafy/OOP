package ru.nsu.fit.algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Knuth-Morris-Pratt algorithm to find all occurrences of substring in a given string.
 */
public class KnuthMorrisPratt implements SubstringFinderAlgorithm {
    private final List<Integer> prefix = new ArrayList<>();
    private String concatString;
    private int processedCount = 1;
    private final int patternLength;
    private final List<Integer> substringIndices = new ArrayList<>();

    /**
     * Initializes data for algorithm from pattern.
     *
     * @param pattern substring pattern to find
     */
    public KnuthMorrisPratt(String pattern) {
        concatString = pattern + '#';
        patternLength = pattern.length();

        prefix.add(0);
        concatString.chars().skip(1).forEach(c -> prefix.add(prefixFunction((char) c, processedCount++)));
    }

    private int prefixFunction(char c, int i) {
        int j = prefix.get(i - 1);
        while (j > 0 && c != concatString.charAt(j)) {
            j = prefix.get(j - 1);
        }
        if (c == concatString.charAt(j)) {
            j++;
        }
        return j;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processChar(char c) {
        concatString += c;
        prefix.add(prefixFunction(c, processedCount));
        if (prefix.get(processedCount) == patternLength) {
            substringIndices.add(processedCount - patternLength * 2);
        }

        processedCount++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getSubstringIndices() {
        return substringIndices;
    }
}
