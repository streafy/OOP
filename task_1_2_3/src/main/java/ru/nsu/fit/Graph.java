package ru.nsu.fit;

import java.util.Map;

public interface Graph<T> {
    boolean addVertex(Vertex<T> vertex);

    boolean removeVertex(Vertex<T> vertex);

    Vertex<T> getVertex(T value);

    boolean contains(T value);

    boolean addEdge(Edge<T> edge);

    boolean removeEdge(Edge<T> edge);

    Edge<T> getEdge(Vertex<T> sourceVertex, Vertex<T> targetVertex);

    boolean contains(T sourceValue, T targetValue);

    Map<Vertex<T>, Integer> shortestPath(Vertex<T> from);

    String toString();
}