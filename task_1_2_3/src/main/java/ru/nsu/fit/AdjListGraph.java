package ru.nsu.fit;

import java.util.*;

public class AdjListGraph<T> implements Graph<T> {
    private int verticesCount = 0;
    private int edgesCount = 0;
    private final Set<Edge<T>> edges = new HashSet<>();
    private final Map<Vertex<T>, List<Vertex<T>>> matrix = new HashMap<>();

    public AdjListGraph() {

    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (matrix.containsKey(vertex)) {
            return false;
        }

        matrix.put(vertex, new ArrayList<>());
        verticesCount++;
        return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (!matrix.containsKey(vertex)) {
            return false;
        }

        matrix.remove(vertex);
        matrix.keySet().forEach(v -> matrix.get(v).remove(vertex));
        return true;
    }

    @Override
    public Vertex<T> getVertex(T value) {
        return matrix.keySet()
                .stream()
                .filter(v -> v.getValue().equals(value))
                .findAny()
                .orElseThrow();
    }

    @Override
    public boolean contains(T value) {
        return matrix.keySet()
                .stream()
                .anyMatch(v -> v.getValue().equals(value));
    }

    @Override
    public boolean addEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!matrix.containsKey(source) || !matrix.containsKey(target)) {
            return false;
        }

        edges.add(edge);
        matrix.get(source).add(target);
        edgesCount++;
        return true;
    }

    @Override
    public boolean removeEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!matrix.containsKey(source) || !matrix.containsKey(target)) {
            return false;
        }

        edges.remove(edge);
        matrix.get(source).remove(target);
        edgesCount--;
        return true;
    }

    @Override
    public Edge<T> getEdge(Vertex<T> sourceVertex, Vertex<T> targetVertex) {
        if (!matrix.containsKey(sourceVertex) || !matrix.containsKey(targetVertex)) {
            throw new IllegalArgumentException();
        }
        if (!contains(sourceVertex.getValue(), targetVertex.getValue())) {
            throw new IllegalArgumentException();
        }

        return null;
    }

    @Override
    public boolean contains(T sourceValue, T targetValue) {
        return false;
    }

    @Override
    public Map<Vertex<T>, Integer> shortestPath(Vertex<T> from) {
        return null;
    }
}
