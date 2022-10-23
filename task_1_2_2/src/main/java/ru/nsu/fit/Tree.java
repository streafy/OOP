package ru.nsu.fit;

import java.util.*;

/**
 * Tree data structure implementation.
 *
 * @param <E> type of elements that are stored in tree
 */
public class Tree<E> implements Collection<E> {
    private Node<E> root;
    private int size = 0;

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
            size--;
            if (lastVisited.parent != null) {
                lastVisited.parent.children.addAll(lastVisited.children);
                lastVisited.parent.children.remove(lastVisited);
            }
            if (lastVisited != null) {
                for (Node<E> nodeChild : lastVisited.children) {
                    nodeChild.parent = lastVisited.parent;
                }
            }
        }
    }

    /**
     * BFS Iterator over Tree
     */
    private class BFSIterator implements Iterator<E> {
        private final Deque<Node<E>> queue = new ArrayDeque<>();
        private Node<E> lastVisited;

        public BFSIterator() {
            if (root != null) {
                lastVisited = root;
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> node = queue.poll();
            lastVisited = node;
            queue.addAll(node.children);
            return node.elem;
        }

        @Override
        public void remove() {
            if (lastVisited.parent == null) {
                return;
            }
            lastVisited.parent.children.addAll(lastVisited.children);
            lastVisited.parent.children.remove(lastVisited);
            for (Node<E> nodeChild : lastVisited.children) {
                nodeChild.parent = lastVisited.parent;
            }
            size--;
        }
    }

    /**
     * Creates an iterator over Tree
     *
     * @return iterator over Tree
     */
    @Override
    public Iterator<E> iterator() {
        return new DFSIterator();
    }

    /**
     * Returns array that contains all elements of Tree
     *
     * @return array that contains all elements of Tree
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        int i = 0;
        for (E elem : this) {
            arr[i++] = elem;
        }
        return arr;
    }

    /**
     * Returns array that contains all elements of Tree
     *
     * @param a   the array into which the elements of this collection are to be
     *            stored, if it is big enough; otherwise, a new array of the same
     *            runtime type is allocated for this purpose.
     * @param <T> type of elements in array
     * @return array that contains all elements of Tree
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            a = (T[]) new Object[size];
        }
        a = (T[]) toArray();
        return a;
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
     * @param elem element added to Tree
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
     * @param elem element added to Tree
     * @return true
     */
    @Override
    public boolean add(E elem) {
        add(this, elem);
        return true;
    }

    /**
     * Adds element to the specific Tree.
     *
     * @param tree Tree to which element are added
     * @param elem element added to the tree
     * @return Tree with added element
     */
    public Tree<E> add(Tree<E> tree, E elem) {
        if (elem == null) {
            throw new IllegalArgumentException("Added element can't be null");
        }

        Node<E> newNode = new Node<>(elem);
        if (tree.root == null) {
            tree.root = newNode;
        } else {
            newNode.parent = tree.root;
            tree.root.children.add(newNode);
        }
        size++;
        return new Tree<>(newNode);
    }

    /**
     * Removes element from tree and returns it if it was successful.
     *
     * @param elem element to be removed
     * @return removed element or null if element isn't present
     */
    @Override
    public boolean remove(Object elem) {
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E e = i.next();
            if (e == elem) {
                i.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for element in tree.
     *
     * @param elem element to be found
     * @return true if found, false otherwise
     */
    @Override
    public boolean contains(Object elem) {
        for (E e : this) {
            if (e == elem) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all elements of another collection contain in collection
     *
     * @param c collection to be checked for containment in this collection
     * @return true if all elements of another collections contain in collection, false otherwise
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    /**
     * Adds to collection elements from another collection
     *
     * @param c collection containing elements to be added to this collection
     * @return true
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E elem : c) {
            add(elem);
        }
        return true;
    }

    /**
     * Removes elements from collection that are contained in other collection
     *
     * @param c collection containing elements to be removed from this collection
     * @return true if collection changed and false otherwise
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E e = i.next();
            if (c.contains(e)) {
                i.remove();
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * Removes elements from collection that are not contained in other collection
     *
     * @param c collection containing elements to be retained in this collection
     * @return true if collection changed and false otherwise
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isChanged = false;
        Iterator<E> i = iterator();
        while (i.hasNext()) {
            E e = i.next();
            if (!c.contains(e)) {
                i.remove();
                isChanged = true;
            }
        }
        return isChanged;
    }

    /**
     * Removes all nodes from Tree
     */
    @Override
    public void clear() {
        size = 0;
        root.children.clear();
        root = null;
    }

    /**
     * Returns number of nodes in Tree
     *
     * @return number of nodes in Tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if Tree is empty
     *
     * @return true if Tree is empty and false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}