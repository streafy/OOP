package ru.nsu.fit;

import java.util.HashMap;

public class AdjMatrixGraph<T> extends Graph<T>{
    private int verticesCount = 0;
    private int edgesCount = 0;
    private HashMap<Vertex<T>, HashMap<Vertex<T>, Integer>> adjMatrix = new HashMap<>();

    public AdjMatrixGraph() {

    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (adjMatrix.containsKey(vertex)) {
            return false;
        }

        adjMatrix.put(vertex, new HashMap<>());
        adjMatrix.forEach((v, hm) -> {
            adjMatrix.get(v).put(vertex, 0);
            adjMatrix.get(vertex).put(v, 0);
        });
        verticesCount++;
        return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (!adjMatrix.containsKey(vertex)) {
            return false;
        }

        adjMatrix.forEach((v, hm) -> adjMatrix.get(v).put(vertex, 0));
        adjMatrix.remove(vertex);
        verticesCount--;
        return true;
    }

    @Override
    public boolean addEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!adjMatrix.containsKey(source) || !adjMatrix.containsKey(target)) {
            return false;
        }

        adjMatrix.get(source).put(target, edge.getWeight());
        edgesCount++;
        return true;
    }

    @Override
    public boolean removeEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!adjMatrix.containsKey(source) || !adjMatrix.containsKey(target)) {
            return false;
        }

        adjMatrix.get(source).put(target, 0);
        adjMatrix.get(target).put(source, 0);
        edgesCount--;
        return true;
    }
}
