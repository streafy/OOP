package ru.nsu.fit;

/**
 * Vertex of the Graph.
 *
 * @param <T> type of element stored in the Vertex
 */
public class Vertex<T> {
    private T value;

    /**
     * Vertex constructor.
     *
     * @param value value of the created vertex
     */
    public Vertex(T value) {
        this.value = value;
    }

    /**
     * Get value of the Vertex.
     *
     * @return value of the Vertex
     */
    public T getValue() {
        return value;
    }

    /**
     * Set value of the Vertex.
     *
     * @param value value to be set
     */
    public void setValue(T value) {
        this.value = value;
    }
}