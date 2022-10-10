package ru.nsu.fit;

import java.util.*;

/**
 * Tree data structure implementation.
 * @param <E> - type of elements that are stored in tree
 */
public class Tree<E> implements Iterable<E> {
    private Node<E> root;

    private static class Node<T> {
        private final T elem;
        private Node<T> parent;
        private final List<Node<T>> children = new ArrayList<>();

        private Node(T elem) {
            this.elem = elem;
        }
    }

    private class TreeIterator implements Iterator<E> {
        private final Stack<Node<E>> stack = new Stack<>();

        public TreeIterator() {
            if (root != null) {
                stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = stack.pop();
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
            return node.elem;
        }
    }

    /**
     * Creates an iterator over Tree
     * @return - iterator over Tree
     */
    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    /**
     * Creates an empty Tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Creates Tree with single element.
     * @param elem - element added to Tree
     */
    public Tree(E elem) {
        add(elem);
    }

    private Tree(Node<E> node) {
        root = node;
    }

    /**
     * Adds new element to tree.
     * @param elem - element added to Tree
     * @return - Tree with added element
     */
    public Tree<E> add(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Added element can't be null");
        }

        Node<E> newNode = new Node<>(elem);
        if (root == null) {
            root = newNode;
        } else {
            newNode.parent = root;
            root.children.add(newNode);
        }
        return new Tree<>(newNode);
    }

    /**
     * Adds element to the specific Tree.
     * @param tree - Tree to which element are added
     * @param elem - element added to the tree
     * @return - Tree with added element
     */
    public Tree<E> add(Tree<E> tree, E elem) {
        return tree.add(elem);
    }

    /**
     * Removes element from tree and returns it if it was successful.
     * @param elem - element to be removed
     * @return - removed element or null if element isn't present
     */
    public E remove(E elem) {
        Stack<Node<E>> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }
        while (!stack.isEmpty()) {
            Node<E> node = stack.pop();

            if (node.elem == elem) {
                node.parent.children.addAll(node.children);
                node.parent.children.remove(node);
                for (Node<E> nodeChild : node.children) {
                    nodeChild.parent = node.parent;
                }
                return elem;
            }

            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }
        return null;
    }

    /**
     * Searches for element in tree.
     * @param elem - element to be found
     * @return - true if found, false otherwise
     */
    public boolean search(E elem) {
        for (E e : this) {
            if (e == elem) {
                return true;
            }
        }
        return false;
    }
}