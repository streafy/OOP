package ru.nsu.fit;

import java.util.Arrays;

/**
 * Stack implementation
 * @param <T> - type of elements that could be stored inside this stack
 */
public class Stack<T> {
    private int size = 0;
    private T[] arr;

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    /**
     * Creates empty stack with initial capacity of 8 elements
     */
    public Stack() {
        this(8);
    }

    /**
     * Creates empty stack with custom initial capacity
     * @param capacity - initial capacity of a stack
     */
    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        arr = (T[]) new Object[capacity];
    }

    /**
     * Creates stack from input array
     * @param arr - input array
     */
    public Stack(T[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array can't be null");
        } else {
            this.arr = arr.clone();
            this.size = arr.length;
        }
    }

    /**
     * Adds element to the top of the stack
     * @param elem - added element
     */
    public void push(T elem) {
        if (size >= arr.length) {
            resize();
        }
        arr[size++] = elem;
    }

    /**
     * Adds another stack's elements to the stack
     * @param stack - another stack, which elements will be added into stack
     */
    public void pushStack(Stack<T> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("Stack can't be null");
        }
        for (int i = 0; i < stack.size; i++) {
            push(stack.arr[i]);
        }
    }

    /**
     * Deletes element from the top of the stack and returns it
     * @return top element of the stack
     */
    public T pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return arr[--size];
    }

    /**
     * Deletes some elements from the top of the stack and returns them as a Stack object
     * @param amount - amount of elements to pop from stack
     * @return Stack object with elements popped from stack
     */
    public Stack<T> popStack(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Can't pop negative amount of elements");
        }
        if (amount > size) {
            throw new IllegalArgumentException("Can't pop more elements from stack than it currently has");
        }
        T[] popArr = Arrays.copyOfRange(arr, size - amount, size);
        size -= amount;
        return new Stack<>(popArr);
    }

    /**
     * Returns stack elements count
     * @return stack elements count
     */
    public int count() {
        return size;
    }
}
