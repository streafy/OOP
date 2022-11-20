package ru.nsu.fit.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aho-Corasick algorithm to find all occurrences of substring in a given string.
 */
public class AhoCorasick implements SubstringFinderAlgorithm {
    private final Trie trie = new Trie();
    private Node current = trie.root;
    private int processedCount = 0;
    private final int patternLength ;
    private final List<Integer> substringIndices = new ArrayList<>();

    /**
     * Node element for Trie data structure and finite state machine construction.
     */
    private static class Node {
        private final Map<Character, Node> next = new HashMap<>();
        private boolean isTerminal = false;
        private final Node parent;
        private final char parentChar;
        private Node suffixLink = null;
        private final Map<Character, Node> transitions = new HashMap<>();

        /**
         * Creates new node and sets its parent and parent character.
         *
         * @param parent parent node link for created node
         * @param parentChar character by which there is transition from parent to created node in finite state machine
         */
        public Node(Node parent, char parentChar) {
            this.parent = parent;
            this.parentChar = parentChar;
        }
    }

    /**
     * Trie data structure to store pattern and construct finite state machine from it.
     */
    private static class Trie {
        List<Node> nodes;
        Node root;

        /**
         * Initialize empty Trie.
         */
        public Trie() {
            this.nodes = new ArrayList<>();
            this.nodes.add(new Node(null, Character.MIN_VALUE));
            this.root = nodes.get(0);
        }

        /**
         * Returns size of the Trie.
         *
         * @return size of the Trie.
         */
        public int size() {
            return nodes.size();
        }

        /**
         * Returns last node in the Trie.
         *
         * @return last node in the Trie.
         */
        public Node last() {
            return nodes.get(size() - 1);
        }

        /**
         * Adds pattern string to the trie.
         */
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

        /**
         * Gets suffix link for a given Node.
         *
         * @param node node for which suffix link is returned
         * @return Node which represents suffix link for given Node
         */
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

        /**
         * Creates transition from given node by given character to another node in finite state machine and returns
         * that node or just returns it if this node already exists.
         *
         * @param node given node
         * @param c given character
         * @return Node to which there is transition from given node by given character in finite state machine.
         */
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

    /**
     * Initializes data for algorithm from pattern.
     *
     * @param pattern substring pattern to find
     */
    public AhoCorasick(String pattern) {
        trie.add(pattern);
        patternLength = pattern.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processChar(char c) {
        current = trie.transition(current, c);
        processedCount++;
        if (current.isTerminal) {
            substringIndices.add(processedCount - patternLength);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> getSubstringIndices() {
        return substringIndices;
    }
}
