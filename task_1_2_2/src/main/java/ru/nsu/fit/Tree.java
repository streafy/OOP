package ru.nsu.fit;

import java.util.*;

public class Tree<E> implements Iterable<E> {
    private Node<E> root;

    private static class Node<T> {
        private final T elem;
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

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    public Tree() {
        root = null;
    }

    public Tree(E elem) {
        add(elem);
    }

    private Tree(Node<E> node) {
        root = node;
    }

    public Tree<E> add(E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Added element can't be null");
        }

        Node<E> newNode = new Node<>(elem);
        if (root == null) {
            root = newNode;
        } else {
            root.children.add(newNode);
        }
        return new Tree<>(newNode);
    }

    public Tree<E> add(Tree<E> tree, E elem) {
        return tree.add(elem);
    }

    public E remove(E elem) {

        return null;
    }
}