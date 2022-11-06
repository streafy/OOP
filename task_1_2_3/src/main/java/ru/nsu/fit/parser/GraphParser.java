package ru.nsu.fit.parser;

import ru.nsu.fit.graph.Graph;
import ru.nsu.fit.graph.utilities.Edge;
import ru.nsu.fit.graph.utilities.Vertex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GraphParser {
    public Graph<String> parseAsAdjMatrix(Graph<String> graph, String filename) {
        List<String> lines = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Path.of(filename))) {
            lines = stream.toList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Vertex<String>> vertices = new ArrayList<>();
        Arrays.stream(lines.get(0)
                .strip()
                .split("\\s+"))
                .forEach(s ->
                        vertices.add(new Vertex<>(s))
                );

        List<Edge<String>> edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                String[] row = lines.get(i + 1).strip().split("\\s+");
                if (row[j + 1].equals("X") || row[j + 1].equals("0")) {
                    continue;
                }
                int weight = Integer.parseInt(row[j + 1]);
                edges.add(new Edge<>(weight, vertices.get(i), vertices.get(j)));
            }
        }

        vertices.forEach(graph::addVertex);
        edges.forEach(graph::addEdge);

        return graph;
    }

    public Graph<String> parseAsIncMatrix(Graph<String> graph, String filename) {
        return graph;
    }

    public Graph<String> parseAsAdjList(Graph<String> graph, String filename) {
        return graph;
    }
}
