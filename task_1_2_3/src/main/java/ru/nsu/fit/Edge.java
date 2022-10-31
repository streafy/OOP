package ru.nsu.fit;

public class Edge<T> {
    private int weight;
    private Vertex<T> sourceVertex;
    private Vertex<T> targetVertex;

    public Edge(int weight, Vertex<T> sourceVertex, Vertex<T> targetVertex) {
        this.weight = weight;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex<T> getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    public Vertex<T> getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex<T> targetVertex) {
        this.targetVertex = targetVertex;
    }
}
