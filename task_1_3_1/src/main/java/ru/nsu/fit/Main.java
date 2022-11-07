package ru.nsu.fit;

public class Main {
    public static void main(String[] args) {
        String pattern = "test";
        String text = "This is a test string";

        SubstringFinder sf = new SubstringFinder(pattern);

        int index = sf.search(text);
        System.out.println(index);
    }
}