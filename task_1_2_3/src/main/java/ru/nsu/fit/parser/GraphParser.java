package ru.nsu.fit.parser;

import ru.nsu.fit.graph.AdjListGraph;
import ru.nsu.fit.graph.AdjMatrixGraph;
import ru.nsu.fit.graph.Graph;
import ru.nsu.fit.graph.IncMatrixGraph;
import ru.nsu.fit.graph.utilities.Edge;
import ru.nsu.fit.graph.utilities.Vertex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphParser {
    private List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(filename))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public Graph<String> parseAsAdjMatrix(String filename) {
        List<String> lines = readFile(filename);

        List<Vertex<String>> vertices = new ArrayList<>();
        Arrays.stream(lines.get(0)
                .strip()
                .split("\\s+"))
                .forEach(s ->
                        vertices.add(new Vertex<>(s))
                );

        List<Edge<String>> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            String[] row = lines.get(i + 1).strip().split("\\s+");
            for (int j = 0; j < vertices.size(); j++) {
                if (row[j + 1].equals("X") || row[j + 1].equals("0")) {
                    continue;
                }
                int weight = Integer.parseInt(row[j + 1]);
                edges.add(new Edge<>(weight, vertices.get(i), vertices.get(j)));
            }
        }

        Graph<String> graph = new AdjMatrixGraph<>();
        vertices.forEach(graph::addVertex);
        edges.forEach(graph::addEdge);

        return graph;
    }

    public Graph<String> parseAsIncMatrix(String filename) {
        List<String> lines = readFile(filename);


        List<Vertex<String>> vertices = new ArrayList<>();

        Arrays.stream(lines.get(0).strip().split("\\s+"))
                .forEach(v -> vertices.add(new Vertex<>(v)));

        List<Edge<String>> edges = new ArrayList<>();
        lines.stream().skip(1).forEach(s -> {
            String[] tokens = s.split("\\s+");
            int weight = Integer.parseInt(tokens[2]);
            Vertex<String> sVertex = vertices.stream()
                    .filter(v -> v.getValue().equals(tokens[0]))
                    .findAny()
                    .orElseThrow();
            Vertex<String> tVertex = vertices.stream()
                    .filter(v -> v.getValue().equals(tokens[1]))
                    .findAny()
                    .orElseThrow();

            edges.add(new Edge<>(weight, sVertex, tVertex));
        });

        Graph<String> graph = new IncMatrixGraph<>();
        vertices.forEach(graph::addVertex);
        edges.forEach(graph::addEdge);

        return graph;
    }

    public Graph<String> parseAsAdjList(String filename) {
        List<String> lines = readFile(filename);

        List<Vertex<String>> vertices = new ArrayList<>();
        lines.forEach(s ->
                vertices.add(new Vertex<>(s.split("\\s+")[0]))
        );

        List<Edge<String>> edges = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] row = lines.get(i).strip().split("\\s+");
            for (int j = 2; j < row.length; j += 2) {
                String t = row[j];
                Vertex<String> tVertex = vertices.stream()
                        .filter(v -> v.getValue().equals(t))
                        .findAny()
                        .orElseThrow();

                edges.add(new Edge<>(Integer.parseInt(row[j + 1]), vertices.get(i), tVertex));
            }
        }

        Graph<String> graph = new AdjListGraph<>();
        vertices.forEach(graph::addVertex);
        edges.forEach(graph::addEdge);

        return graph;
    }
}
