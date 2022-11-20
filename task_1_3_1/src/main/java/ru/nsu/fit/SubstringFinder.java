package ru.nsu.fit;

import ru.nsu.fit.algorithms.AhoCorasick;
import ru.nsu.fit.algorithms.KnuthMorrisPratt;
import ru.nsu.fit.algorithms.SubstringFinderAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SubstringFinder {
    SubstringFinderAlgorithm finder;

    public SubstringFinder(String algorithm, String pattern) {
        finder = switch (algorithm) {
            case "AC" -> new AhoCorasick(pattern);
            case "KMP" -> new KnuthMorrisPratt(pattern);
            default -> throw new IllegalArgumentException("Unsupported algorithm");
        };
    }

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
