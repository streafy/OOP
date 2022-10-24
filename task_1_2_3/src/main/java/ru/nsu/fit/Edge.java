package ru.nsu.fit;

public class Edge<V> {
    private V sourceVertex;
    private V targetVertex;

    public Edge(V sourceVertex, V targetVertex) {
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }
}
