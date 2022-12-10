package algos.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generic implementation of an adjacency list based graph.
 * Adjacency list is an ArrayList of vertices which have an index matching to lists of their arcs,
 * which are themselves contained in a separate ArrayList for the reason of index matching.
 * Each of the arcs must have two index references to their vertices (from, to)
 *
 * @param <V> generic vertex type
 * @param <E> generified arc type
 */
public class AdjacencyListDirectedGraph<V, E extends Arc> {
    private ArrayList<V> vertices = new ArrayList<>();
    protected ArrayList<List<E>> arcs = new ArrayList<>();

    public AdjacencyListDirectedGraph(ArrayList<V> vertices) {
        this.vertices = vertices;
        for (V vertex : vertices) this.arcs.add(new ArrayList<E>());
    }

    public int getVertexCount() {
        return this.vertices.size();
    }

    public int getArcCount() {
        return this.arcs.stream().mapToInt(List::size).sum();
    }

    public int addVertex(V vertex) {
        this.vertices.add(vertex);
        this.arcs.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public V vertexAt(int index) {
        return this.vertices.get(index);
    }

    public int indexOf(V vertex) {
        return this.vertices.indexOf(vertex);
    }

    public List<V> neighborsOf(int index) {
        return this.arcs.get(index).stream()
                .map(edge -> vertexAt(edge.to))
                .toList();
    }

    public List<V> neighborsOf(V vertex) {
        return neighborsOf(indexOf(vertex));
    }

    public List<E> arcsOf(int index) {
        return this.arcs.get(index);
    }

    public List<E> arcsOf(V vertex) {
        return arcsOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getVertexCount(); i++)
            sb.append(vertexAt(i)).append(" -> ").append(Arrays.toString(neighborsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }
}