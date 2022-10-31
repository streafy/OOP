package ru.nsu.fit;

import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Interface with methods to work with graphs
 *
 * @param <T> type of elements stored in graph
 */
public interface Graph<T> {
    /**
     * Add vertex to graph
     *
     * @param vertex Vertex object to be added
     * @return true if vertex successfully added, false otherwise
     */
    boolean addVertex(Vertex<T> vertex);

    /**
     * Remove vertex from graph
     *
     * @param vertex Vertex object to be removed
     * @return true if vertex successfully removed, false otherwise
     */
    boolean removeVertex(Vertex<T> vertex);

    /**
     * Get Vertex object from graph by its value
     *
     * @param value value of possibly returned Vertex
     * @return Vertex object if it's present in graph
     * @throws NoSuchElementException if there is no Vertex with this value
     */
    Vertex<T> getVertex(T value) throws NoSuchElementException;

    /**
     * Check if Vertex with specific value is present in graph
     *
     * @param value value of Vertex which presence we check
     * @return true if such Vertex present in graph, false otherwise
     */
    boolean contains(T value);

    /**
     * Add edge to graph
     *
     * @param edge Edge object to be added
     * @return true if edge successfully added, false otherwise
     */
    boolean addEdge(Edge<T> edge);

    /**
     * Remove edge from graph
     *
     * @param edge Edge object to be removed
     * @return true if edge successfully removed, false otherwise
     */
    boolean removeEdge(Edge<T> edge);

    /**
     * Get Edge from Graph by its source vertex and target vertex
     *
     * @param sourceVertex source vertex object of the edge we are looking for
     * @param targetVertex target vertex object of the edge we are looking for
     * @return Edge object if it's present in graph
     * @throws IllegalArgumentException if there is no source or target vertex present in graph
     * @throws NoSuchElementException   if there is no such edge in graph
     */
    Edge<T> getEdge(Vertex<T> sourceVertex, Vertex<T> targetVertex)
            throws IllegalArgumentException, NoSuchElementException;

    /**
     * Check if Edge with specific source and target vertices values
     *
     * @param sourceValue source vertex value
     * @param targetValue target vertex value
     * @return true if there is such edge, false otherwise
     */
    boolean contains(T sourceValue, T targetValue);

    /**
     * Find the shortest path from specific Vertex to every Vertex in graph
     *
     * @param from Vertex from which we're finding paths
     * @return Map with vertices as keys and path's lengths as values
     */
    Map<Vertex<T>, Integer> shortestPath(Vertex<T> from);

    /**
     * Returns String representation of the Graph
     *
     * @return String representation of the Graph
     */
    String toString();

    /**
     * Get vertices count in the Graph
     *
     * @return vertices count in the Graph
     */
    int getVerticesCount();

    /**
     * Get edges count in the Graph
     *
     * @return edges count in the Graph
     */
    int getEdgesCount();
}