package ru.nsu.fit;

import java.util.ArrayList;

public class Vertex<V> {
    V value;
    private ArrayList<Vertex<V>> inNeighbors;
    private ArrayList<Vertex<V>> outNeighbors;
}
