package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.fit.graph.*;
import ru.nsu.fit.graph.utilities.Edge;
import ru.nsu.fit.graph.utilities.Vertex;
import ru.nsu.fit.parser.GraphParser;

import java.util.HashMap;
import java.util.Map;

public class GraphTest {

    @Test
    void testEveryRepresentation() {
        GraphParser parser = new GraphParser();

        Graph<String> adjMatrixGraph = new AdjMatrixGraph<>();
        parser.parseAsAdjMatrix(adjMatrixGraph, "src/test/java/ru/nsu/fit/adjm.txt");
        testGraph(adjMatrixGraph);

        Graph<String> incMatrixGraph = new IncMatrixGraph<>();
        parser.parseAsAdjMatrix(incMatrixGraph, "src/test/java/ru/nsu/fit/adjm.txt");
        testGraph(incMatrixGraph);

        /*
        Graph<String> adjListGraph = new AdjListGraph<>();
        testGraph(adjListGraph);*/
    }

    void testGraph(Graph<String> graph) {
        Vertex<String> s = graph.getVertex("s");
        Vertex<String> a = graph.getVertex("a");
        Vertex<String> b = graph.getVertex("b");
        Vertex<String> c = graph.getVertex("c");
        Vertex<String> f = graph.getVertex("f");
        Vertex<String> t = graph.getVertex("t");

        Edge<String> saEdge = graph.getEdge(s, a);
        Edge<String> sbEdge = graph.getEdge(s, b);

        System.out.println(graph);
        Map<Vertex<String>, Integer> expected = new HashMap<>();
        expected.put(s, 0);
        expected.put(a, 3);
        expected.put(b, 4);
        expected.put(c, 5);
        expected.put(f, 6);
        expected.put(t, 10);
        Map<Vertex<String>, Integer> sp = graph.shortestPath(s);
        Assertions.assertTrue(expected.entrySet()
                .stream()
                .allMatch(e -> e.getValue().equals(sp.get(e.getKey())))
        );

        sp.forEach((vertex, distance) ->
                System.out.println(vertex.getValue() + "(" + distance + ")"));
        System.out.println();

        Assertions.assertTrue(graph.containsVertex("t"));
        Assertions.assertEquals("t", graph.getVertex("t").getValue());

        Assertions.assertTrue(graph.containsEdge("s", "a"));
        Assertions.assertEquals("s", graph.getEdge(s, a).getSourceVertex().getValue());
        Assertions.assertEquals("a", graph.getEdge(s, a).getTargetVertex().getValue());

        Assertions.assertEquals(6, graph.getVerticesCount());
        Assertions.assertEquals(9, graph.getEdgesCount());

        s.setValue("q");
        Assertions.assertEquals("q", s.getValue());

        saEdge.setWeight(5);
        Assertions.assertEquals(5, saEdge.getWeight());

        Assertions.assertTrue(graph.removeVertex(t));
        Assertions.assertFalse(graph.removeVertex(t));


        Assertions.assertTrue(graph.removeEdge(saEdge));
        Assertions.assertFalse(graph.removeEdge(saEdge));

        sbEdge.setTargetVertex(t);
        Assertions.assertEquals(t, sbEdge.getTargetVertex());

        sbEdge.setSourceVertex(b);
        Assertions.assertEquals(b, sbEdge.getSourceVertex());
    }
}
