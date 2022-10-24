package ru.nsu.fit;

abstract public class Graph<T> {

    abstract boolean addVertex(Vertex<T> vertex);

    abstract boolean removeVertex(Vertex<T> vertex);

    abstract boolean addEdge(Edge<T> edge);

    abstract boolean removeEdge(Edge<T> edge);
}