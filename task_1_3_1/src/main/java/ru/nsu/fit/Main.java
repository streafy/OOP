package ru.nsu.fit;

import ru.nsu.fit.algorithms.AhoCorasick;

public class Main {
    public static void main(String[] args) {
        AhoCorasick ah = new AhoCorasick();
        ah.processText("qweqwe bebebe", "be");
    }
}