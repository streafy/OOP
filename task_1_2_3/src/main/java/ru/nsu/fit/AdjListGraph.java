package ru.nsu.fit;

import java.util.*;

/**
 * Graph implementation that uses Adjacency List.
 *
 * @param <T> type of elements stored in graph
 */
public class AdjListGraph<T> implements Graph<T> {
    private int verticesCount = 0;
    private int edgesCount = 0;
    private final Set<Edge<T>> edges = new HashSet<>();
    private final Map<Vertex<T>, List<Vertex<T>>> matrix = new HashMap<>();

    /**
     * Empty AdjListGraph constructor.
     */
    public AdjListGraph() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addVertex(Vertex<T> vertex) {
        if (matrix.containsKey(vertex)) {
            return false;
        }

        matrix.put(vertex, new ArrayList<>());
        verticesCount++;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeVertex(Vertex<T> vertex) {
        if (!matrix.containsKey(vertex)) {
            return false;
        }

        matrix.remove(vertex);
        matrix.keySet().forEach(v -> matrix.get(v).remove(vertex));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vertex<T> getVertex(T value) {
        return matrix.keySet()
                .stream()
                .filter(v -> v.getValue().equals(value))
                .findAny()
                .orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(Edge<T> edge) {
        Vertex<T> source = edge.getSourceVertex();
        Vertex<T> target = edge.getTargetVertex();

        if (!matrix.containsKey(source) || !matrix.containsKey(target)
                || !contains(source.getValue(), target.getValue())) {
            return false;
        }

        edges.remove(edge);
        matrix.get(source).remove(target);
        edgesCount--;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Edge<T> getEdge(Vertex<T> sourceVertex, Vertex<T> targetVertex) {
        if (!matrix.containsKey(sourceVertex) || !matrix.containsKey(targetVertex)) {
            throw new IllegalArgumentException();
        }
        if (!contains(sourceVertex.getValue(), targetVertex.getValue())) {
            throw new IllegalArgumentException();
        }

        return edges.stream()
                .filter(e -> e.getSourceVertex().equals(sourceVertex)
                        && e.getTargetVertex().equals(targetVertex))
                .findAny()
                .orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T value) {
        return matrix.keySet()
                .stream()
                .anyMatch(v -> v.getValue().equals(value));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(T sourceValue, T targetValue) {
        return edges.stream()
                .anyMatch(e -> e.getSourceVertex().getValue().equals(sourceValue)
                        && e.getTargetVertex().getValue().equals(targetValue));
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int getVerticesCount() {
        return verticesCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEdgesCount() {
        return edgesCount;
    }
}
