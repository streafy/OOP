package ru.nsu.fit;

import ru.nsu.fit.algorithms.AhoCorasick;
import ru.nsu.fit.algorithms.KnuthMorrisPratt;
import ru.nsu.fit.algorithms.SubstringFinderAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Class for finding all occurrences of substring in a given string.
 */
public class SubstringFinder {
    SubstringFinderAlgorithm finder;

    /**
     * Constructs finder with given algorithm and pattern.
     *
     * @param algorithm algorithm for substring finder
     * @param pattern pattern for substring finder
     */
    public SubstringFinder(String algorithm, String pattern) {
        switch (algorithm) {
            case "AC" -> finder = new AhoCorasick(pattern);
            case "KMP" -> finder = new KnuthMorrisPratt(pattern);
            default -> throw new IllegalArgumentException("Unsupported algorithm");
        }
    }

    /**
     * Searches all occurrences of substrings in a buffered input from an input stream.
     *
     * @param inputStream input stream in which search will be performed
     * @return list with indices of all occurrences of substring in given input stream
     */
    public List<Integer> search(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int c;
            while ((c = reader.read()) != -1) {
                finder.processChar((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finder.getSubstringIndices();
    }
}
