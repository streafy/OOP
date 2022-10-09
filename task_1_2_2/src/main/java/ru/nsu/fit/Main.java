package ru.nsu.fit;

public class Main {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("root");
        Tree<String> tree1 = tree.add("A");
        Tree<String> tree2 = tree.add("B");
        Tree<String> tree3 = tree.add("C");
        tree.add("D");
        Tree<String> tree4 = tree.add(tree1, "AB");
        tree.add(tree2, "BB");
        tree.add(tree3, "CB");
        System.out.println("Printing test Tree");
        tree.add(tree4, "ABC");

        for (String s : tree) {
            System.out.println(s);
        }
    }
}