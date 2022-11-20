package ru.nsu.fit;

import ru.nsu.fit.algorithms.AhoCorasick;
import ru.nsu.fit.algorithms.KnuthMorrisPratt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class SubstringFinder {

    public SubstringFinder() {

    }

    public List<Integer> search(InputStream inputStream, String pattern) {
        AhoCorasick ac = new AhoCorasick(pattern);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int c;
            while ((c = reader.read()) != -1) {
                ac.processChar((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ac.getSubstringIndices();
    }

    public List<Integer> searchKMP(InputStream inputStream, String pattern) {
        KnuthMorrisPratt ac = new KnuthMorrisPratt(pattern);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            int c;
            while ((c = reader.read()) != -1) {
                ac.processChar((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ac.getSubstringIndices();
    }
}
