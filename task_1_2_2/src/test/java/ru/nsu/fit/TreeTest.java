package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreeTest {

    Tree<String> createTestTree() {
        Tree<String> tree = new Tree<>("root");
        Tree<String> tree1 = tree.add(tree, "A");
        Tree<String> tree2 = tree.add(tree, "B");
        Tree<String> tree3 = tree.add(tree, "C");
        tree.add("D");
        Tree<String> tree4 = tree.add(tree1, "AB");
        tree.add(tree2, "BB");
        tree.add(tree3, "CB");
        tree.add(tree4, "ABC");
        return tree;
    }

    @Test
    void testIterator() {
        Tree<String> tree = createTestTree();

        int i = 0;
        String[] testStrings = {"root", "A", "AB", "ABC", "B", "BB", "C", "CB", "D"};
        //BFS
        //String[] testStrings = {"root", "A", "B", "C", "D", "AB", "BB", "CB", "ABC" };

        for (String s : tree) {
            Assertions.assertEquals(testStrings[i++], s);
        }
    }

    @Test
    void testRemove() {
        Tree<String> tree = createTestTree();

        tree.remove("AB");

        int i = 0;
        String[] testStrings = {"root", "A", "ABC", "B", "BB", "C", "CB", "D"};
        for (String s : tree) {
            Assertions.assertEquals(testStrings[i++], s);
        }
        Assertions.assertFalse(tree.remove("AB"));

        Tree<String> tree1 = new Tree<>("BB");
        tree1.add("D");
        tree.removeAll(tree1);

        i = 0;
        String[] testStrings1 = {"root", "A", "ABC", "B", "C", "CB"};
        for (String s : tree) {
            Assertions.assertEquals(testStrings1[i++], s);
        }
    }

    @Test
    void testClear() {
        Tree<String> tree = createTestTree();

        tree.clear();
        Assertions.assertTrue(tree.isEmpty());
        Assertions.assertFalse(tree.remove("A"));
    }

    @Test
    void testContains() {
        Tree<String> tree = createTestTree();

        Assertions.assertTrue(tree.contains("A"));

        Tree<String> tree1 = new Tree<>("A");
        tree1.add("B");
        Assertions.assertTrue(tree.containsAll(tree1));
    }
}
