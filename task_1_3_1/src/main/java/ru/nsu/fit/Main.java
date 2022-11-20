package ru.nsu.fit;

import ru.nsu.fit.algorithms.KnuthMorrisPratt;

import java.io.ByteArrayInputStream;

public class Main {
    public static void main(String[] args) {
        SubstringFinder sf = new SubstringFinder();
        sf.searchKMP(new ByteArrayInputStream("be1234besbe".getBytes()), "be").forEach(System.out::println);
    }
}