package ru.nsu.fit.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AhoCorasick implements SubstringFinderAlgorithm {
    private final Trie trie = new Trie();
    private Node current = trie.root;
    private int processedCount = 0;
    private final int patternLength ;
    private final List<Integer> substringIndices = new ArrayList<>();

    private static class Node {
        private final Map<Character, Node> next = new HashMap<>();
        private boolean isTerminal = false;
        private final Node parent;
        private final char parentChar;
        private Node suffixLink = null;
        private final Map<Character, Node> transitions = new HashMap<>();

        public Node(Node parent, char parentChar) {
            this.parent = parent;
            this.parentChar = parentChar;
        }
    }

    private static class Trie {
        List<Node> nodes;
        Node root;

        public Trie() {
            this.nodes = new ArrayList<>();
            this.nodes.add(new Node(null, Character.MIN_VALUE));
            this.root = nodes.get(0);
        }

        public int size() {
            return nodes.size();
        }

        public Node last() {
            return nodes.get(size() - 1);
        }

        public void add(String pattern) {
            Node current = root;
            for (char c : pattern.toCharArray()) {
                if (!current.next.containsKey(c)) {
                    nodes.add(new Node(current, c));
                    current.next.put(c, last());
                }
                current = current.next.get(c);
            }
            current.isTerminal = true;
        }

        public boolean find(String pattern) {
            Node current = root;
            for (char c : pattern.toCharArray()) {
                if (!current.next.containsKey(c)) {
                    return false;
                }
                current = current.next.get(c);
            }
            return current.isTerminal;
        }

        public Node getSuffixLink(Node node) {
            if (node.suffixLink == null) {
                if (node == root || node.parent == root) {
                    node.suffixLink = root;
                } else {
                    node.suffixLink = transition(getSuffixLink(node.parent), node.parentChar);
                }
            }
            return node.suffixLink;
        }

        public Node transition(Node node, char c) {
            if (!node.transitions.containsKey(c)) {
                if (node.next.containsKey(c)) {
                    node.transitions.put(c, node.next.get(c));
                } else if (node == root) {
                    node.transitions.put(c, root);
                } else {
                    node.transitions.put(c, transition(getSuffixLink(node), c));
                }
            }
            return node.transitions.get(c);
        }
    }

    public AhoCorasick(String pattern) {
        trie.add(pattern);
        patternLength = pattern.length();
    }

    public void processChar(char c) {
        current = trie.transition(current, c);
        processedCount++;
        if (current.isTerminal) {
            substringIndices.add(processedCount - patternLength);
        }
    }

    public List<Integer> getSubstringIndices() {
        return substringIndices;
    }
}
