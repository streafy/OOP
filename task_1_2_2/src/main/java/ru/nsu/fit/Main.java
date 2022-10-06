package ru.nsu.fit;

public class Main {
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("root");
        Tree<String> tree1 = tree.add1("A");
        Tree<String> tree2 = tree.add1("B");
        Tree<String> tree3 = tree.add1("C");
        tree.add1("D");
        Tree<String> tree4 = tree.add1(tree1, "AB");
        tree.add1(tree2, "BB");
        tree.add1(tree3, "CB");
        System.out.println("Printing test Tree");
        tree.add1(tree4, "ABC");
        tree.printTree();
        tree.remove1("A");
        tree.printTree();

        Tree<String> test = new Tree<>();
        test.add1("1");
        test.add1("2");
    }
}