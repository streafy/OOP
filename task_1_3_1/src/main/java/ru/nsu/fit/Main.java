package ru.nsu.fit;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SubstringFinder finder = new SubstringFinder();
        System.out.println("here");
        List<Integer> indices = finder.search(System.in, "be");
        System.out.println("and here");
        indices.forEach(System.out::println);
    }
}