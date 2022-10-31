package ru.nsu.fit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GraphTest {

    @Test
    void testEveryRepresentation() {
        Graph<String> adjMatrixGraph = new AdjMatrixGraph<>();
        testGraph(adjMatrixGraph);

        Graph<String> incMatrixGraph = new IncMatrixGraph<>();
        testGraph(incMatrixGraph);

        Graph<String> adjListGraph = new AdjListGraph<>();
        testGraph(adjListGraph);
    }

    void testGraph(Graph<String> graph) {
        Vertex<String> s = new Vertex<>("s");
        Vertex<String> a = new Vertex<>("a");
        Vertex<String> b = new Vertex<>("b");
        Vertex<String> c = new Vertex<>("c");
        Vertex<String> f = new Vertex<>("f");
        Vertex<String> t = new Vertex<>("t");

        Edge<String> saEdge = new Edge<>(3, s, a);
        Edge<String> sbEdge = new Edge<>(4, s, b);
        Edge<String> abEdge = new Edge<>(6, a, b);
        Edge<String> acEdge = new Edge<>(2, a, c);
        Edge<String> afEdge = new Edge<>(7, a, f);
        Edge<String> bfEdge = new Edge<>(5, b, f);
        Edge<String> cfEdge = new Edge<>(1, c, f);
        Edge<String> ctEdge = new Edge<>(8, c, t);
        Edge<String> ftEdge = new Edge<>(4, f, t);

        Assertions.assertTrue(graph.addVertex(s));
        Assertions.assertTrue(graph.addVertex(a));
        Assertions.assertTrue(graph.addVertex(b));
        Assertions.assertTrue(graph.addVertex(c));
        Assertions.assertTrue(graph.addVertex(f));
        Assertions.assertTrue(graph.addVertex(t));

        Assertions.assertTrue(graph.addEdge(saEdge));
        Assertions.assertTrue(graph.addEdge(sbEdge));
        Assertions.assertTrue(graph.addEdge(abEdge));
        Assertions.assertTrue(graph.addEdge(acEdge));
        Assertions.assertTrue(graph.addEdge(afEdge));
        Assertions.assertTrue(graph.addEdge(bfEdge));
        Assertions.assertTrue(graph.addEdge(cfEdge));
        Assertions.assertTrue(graph.addEdge(ctEdge));
        Assertions.assertTrue(graph.addEdge(ftEdge));

        System.out.println(graph);
        Map<Vertex<String>, Integer> sp = graph.shortestPath(s);
        sp.forEach((vertex, distance) -> System.out.println(vertex.getValue() + "(" + distance + ")"));
        System.out.println();

        Assertions.assertTrue(graph.contains("t"));
        Assertions.assertEquals("t", graph.getVertex("t").getValue());

        Assertions.assertTrue(graph.contains("s", "a"));
        Assertions.assertEquals("s", graph.getEdge(s, a).getSourceVertex().getValue());
        Assertions.assertEquals("a", graph.getEdge(s, a).getTargetVertex().getValue());

        Assertions.assertEquals(6, graph.getVerticesCount());
        Assertions.assertEquals(9, graph.getEdgesCount());

        Assertions.assertTrue(graph.removeVertex(t));
        Assertions.assertFalse(graph.removeVertex(t));

        Assertions.assertTrue(graph.removeEdge(saEdge));
        Assertions.assertFalse(graph.removeEdge(saEdge));
    }
}
