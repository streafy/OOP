package ru.nsu.fit;

import java.util.*;

/**
 * Tree data structure implementation.
 *
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

    /**
     * DFS Iterator over Tree
     */
    private class DFSIterator implements Iterator<E> {
        private final Deque<Node<E>> stack = new ArrayDeque<>();
        private Node<E> lastVisited;

        public DFSIterator() {
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
            lastVisited = node;
            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
            return node.elem;
        }

        @Override
        public void remove() {
            lastVisited.parent.children.addAll(lastVisited.children);
            lastVisited.parent.children.remove(lastVisited);
            for (Node<E> nodeChild : lastVisited.children) {
                nodeChild.parent = lastVisited.parent;
            }
        }
    }

    /**
     * Creates an iterator over Tree
     *
     * @return - iterator over Tree
     */
    @Override
    public Iterator<E> iterator() {
        return new DFSIterator();
    }

    /**
     * Creates an empty Tree.
     */
    public Tree() {
        root = null;
    }

    /**
     * Creates Tree with single element.
     *
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
     *
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
     *
     * @param tree - Tree to which element are added
     * @param elem - element added to the tree
     * @return - Tree with added element
     */
    public Tree<E> add(Tree<E> tree, E elem) {
        return tree.add(elem);
    }

    /**
     * Removes element from tree and returns it if it was successful.
     *
     * @param elem - element to be removed
     * @return - removed element or null if element isn't present
     */
    public E remove(E elem) {
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E e = i.next();
            if (e == elem) {
                i.remove();
                return elem;
            }
        }
        return null;
    }

    /**
     * Searches for element in tree.
     *
     * @param elem - element to be found
     * @return - true if found, false otherwise
     */
    public boolean contains(E elem) {
        for (E e : this) {
            if (e == elem) {
                return true;
            }
        }
        return false;
    }
}