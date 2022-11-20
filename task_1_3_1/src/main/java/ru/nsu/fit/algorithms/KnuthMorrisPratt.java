package ru.nsu.fit.algorithms;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPratt implements SubstringFinderAlgorithm {
    List<Integer> prefix = new ArrayList<>();
    String concatString;

    int processedCount = 1;
    int patternLength;
    List<Integer> substringIndices = new ArrayList<>();

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

    public void processChar(char c) {
        concatString += c;
        prefix.add(prefixFunction(c, processedCount));
        if (prefix.get(processedCount) == patternLength) {
            substringIndices.add(processedCount - patternLength - 2);
        }
        processedCount++;
    }

    public List<Integer> getSubstringIndices() {
        return substringIndices;
    }
}
