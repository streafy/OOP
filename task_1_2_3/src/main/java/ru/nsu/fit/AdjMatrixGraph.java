package ru.nsu.fit;

import java.util.*;

public class AdjMatrixGraph<T> implements Graph<T> {
    private int verticesCount = 0;
    private int edgesCount = 0;
    private final Map<Vertex<T>, Map<Vertex<T>, Integer>> matrix = new HashMap<>();

    public AdjMatrixGraph() {

    }

    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (matrix.containsKey(vertex)) {
            return false;
        }

        matrix.put(vertex, new HashMap<>());
        matrix.forEach((v, hm) -> {
            matrix.get(v).put(vertex, 0);
            matrix.get(vertex).put(v, 0);
        });
        verticesCount++;
        return true;
    }

    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (!matrix.containsKey(vertex)) {
            return false;
        }

        matrix.forEach((v, hm) -> matrix.get(v).put(vertex, 0));
        matrix.remove(vertex);
        verticesCount--;
        return true;
    }

    @Override
    public Vertex<T> getVertex(T value) throws NoSuchElementException {
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

        matrix.get(source).put(target, edge.getWeight());
        edgesCount++;
        return true;
    }

    @Override
    public boolean removeEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!matrix.containsKey(source) || !matrix.containsKey(target)
                || !contains(source.getValue(), target.getValue())) {
            return false;
        }


        matrix.get(source).put(target, 0);
        matrix.get(target).put(source, 0);
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

        int weight = matrix.get(sourceVertex).get(targetVertex);
        return new Edge<>(weight, sourceVertex, targetVertex);
    }

    @Override
    public boolean contains(T sourceValue, T targetValue) {
        if (!contains(sourceValue) || !contains(targetValue)) {
            return false;
        }
        Vertex<T> sourceVertex = getVertex(sourceValue);
        Vertex<T> targetVertex = getVertex(targetValue);

        return matrix.get(sourceVertex).get(targetVertex) != 0;
    }

    @Override
    public Map<Vertex<T>, Integer> shortestPath(Vertex<T> source) {
        Map<Vertex<T>, Integer> distances = new HashMap<>();
        Set<Vertex<T>> unvisited = new HashSet<>(matrix.keySet());

        for (Vertex<T> v : matrix.keySet()) {
            distances.put(v, v == source ? 0 : Integer.MAX_VALUE);
        }

        for (int i = 0; i < matrix.keySet().size(); i++) {
            Vertex<T> u = distances.entrySet()
                    .stream()
                    .filter(entry -> unvisited.contains(entry.getKey()))
                    .min(Map.Entry.comparingByValue())
                    .orElseThrow()
                    .getKey();
            unvisited.remove(u);

            for (Vertex<T> v : matrix.keySet()) {
                if (!contains(u.getValue(), v.getValue())) {
                    continue;
                }
                int newDistance = distances.get(u) + getEdge(u, v).getWeight();
                if (unvisited.contains(v) && (newDistance < distances.get(v))) {
                    distances.put(v, newDistance);
                }
            }
        }
        return distances;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int paddingValue = matrix.keySet()
                .stream()
                .map(vertex -> vertex.getValue().toString())
                .mapToInt(String::length)
                .max()
                .orElse(0) + 1;
        builder.append(" ".repeat(paddingValue));

        matrix.keySet().forEach(vtx -> builder.append(vtx.getValue()).append(" "));
        builder.append("\n");

        for (Vertex<T> v : matrix.keySet()) {
            String vertexString = v.getValue().toString();

            builder.append(vertexString).append(" ".repeat(paddingValue - vertexString.length()));
            matrix.get(v).forEach((vertex, weight) -> {
                if (weight == 0 && v != vertex) {
                    builder.append("X").append(" ".repeat(vertexString.length()));
                } else {
                    builder.append(weight).append(" ".repeat(vertexString.length()));
                }
            });
            builder.append("\n");
        }

        return builder.toString();
    }
}
