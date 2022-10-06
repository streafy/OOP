package ru.nsu.fit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tree<T> implements Iterable<Tree<T>>{
    private final List<Tree<T>> children = new ArrayList<>();
    private Tree<T> parent = null;
    private T data = null;

    public int size() {
        if (data == null) {
            return 0;
        }
        int size = 1;
        for (Tree<T> child : children) {
            if (child != null) {
                size += child.size();
            }
        }
        return size;
    }
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return
     */
    @Override
    public Iterator<Tree<T>> iterator() {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<Tree<T>> {

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public Tree<T> next() {
            return children.iterator().next();
        }
    }

    public Tree() {
    }

    public Tree(T data) {
        this.data = data;
    }

    public Tree<T> add1(T elem) {
        if (data == null) {
            data = elem;
            return this;
        } else {
            Tree<T> newChild = new Tree<>(elem);
            newChild.parent = this;
            newChild.data = elem;
            newChild.parent.children.add(newChild);
            return newChild;
        }
    }

    public Tree<T> add1(Tree<T> tree, T elem) {
        return tree.add1(elem);
    }

    public T remove1(T elem) {
        for (Tree<T> child : children) {
            if (child.data == elem) {
                System.out.println("\nremove " + child.data);
                for (Tree<T> subChild : child.children) {
                    System.out.println("bind " + subChild.data + " to " + child.parent.data);
                    subChild.parent = child.parent;
                }
                child.parent.children.addAll(child.children);
                child.parent.children.remove(child);
                return elem;
            }
        }
        return null;
    }

    //Debug only
    public void printTree() {
        System.out.print("\n" + data);
        for (Tree<T> t : children) {
            t.printTree();
        }
    }
}