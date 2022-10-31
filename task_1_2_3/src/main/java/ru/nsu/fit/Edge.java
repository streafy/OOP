package ru.nsu.fit;

/**
 * Edge of the Graph.
 *
 * @param <T> type of element stored in the Edge vertices
 */
public class Edge<T> {
    private int weight;
    private Vertex<T> sourceVertex;
    private Vertex<T> targetVertex;

    /**
     * Edge constructor.
     *
     * @param weight weight of the Edge
     * @param sourceVertex source vertex of the Edge
     * @param targetVertex target vertex of the Edge
     */
    public Edge(int weight, Vertex<T> sourceVertex, Vertex<T> targetVertex) {
        this.weight = weight;
        this.sourceVertex = sourceVertex;
        this.targetVertex = targetVertex;
    }

    /**
     * Get weight of the Edge.
     *
     * @return weight of the Edge
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Set weight of the Edge.
     *
     * @param weight weight to be set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Get source vertex of the Edge.
     *
     * @return Vertex object that represents source vertex of the Edge
     */
    public Vertex<T> getSourceVertex() {
        return sourceVertex;
    }

    /**
     * Set source vertex of the Edge.
     *
     * @param sourceVertex - source vertex to be set
     */
    public void setSourceVertex(Vertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    /**
     * Get target vertex of the Edge.
     *
     * @return Vertex object that represents target vertex of the Edge
     */
    public Vertex<T> getTargetVertex() {
        return targetVertex;
    }

    /**
     * Set source vertex of the Edge.
     *
     * @param targetVertex - source vertex to be set
     */
    public void setTargetVertex(Vertex<T> targetVertex) {
        this.targetVertex = targetVertex;
    }
}
