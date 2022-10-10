package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTest {

    @Test
    void testIterator() {
        Tree<String> tree = new Tree<>("root");
        Tree<String> tree1 = tree.add("A");
        Tree<String> tree2 = tree.add("B");
        Tree<String> tree3 = tree.add("C");
        tree.add("D");
        Tree<String> tree4 = tree.add(tree1, "AB");
        tree.add(tree2, "BB");
        tree.add(tree3, "CB");
        tree.add(tree4, "ABC");

        int i = 0;
        String[] testStrings = {"root", "A", "AB", "ABC", "B", "BB", "C", "CB", "D"};

        for (String s : tree) {
            Assertions.assertEquals(testStrings[i++], s);
        }
    }

    @Test
    void testRemove() {
        Tree<String> tree = new Tree<>("root");
        Tree<String> tree1 = tree.add("A");
        Tree<String> tree2 = tree.add("B");
        Tree<String> tree3 = tree.add("C");
        tree.add("D");
        Tree<String> tree4 = tree.add(tree1, "AB");
        tree.add(tree2, "BB");
        tree.add(tree3, "CB");
        tree.add(tree4, "ABC");

        Assertions.assertEquals("AB", tree.remove("AB"));
        Assertions.assertNull(tree.remove("AB"));
    }
}
