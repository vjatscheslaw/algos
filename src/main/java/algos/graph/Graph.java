package algos.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph<V, E extends Edge> {
    private ArrayList<V> vertices = new ArrayList<>();
    protected ArrayList<ArrayList<E>> edges = new ArrayList<>();

    public Graph(ArrayList<V> vertices) {
        this.vertices = vertices;
        for (V vertex : vertices) this.edges.add(new ArrayList<E>());
    }

    public int getVertexCount() {
        return this.vertices.size();
    }

    public int getEdgeCount() {
        return this.edges.stream().mapToInt(ArrayList::size).sum();
    }

    public int addVertex(V vertex) {
        this.vertices.add(vertex);
        this.edges.add(new ArrayList<>());
        return getVertexCount() - 1;
    }

    public V vertexAt(int index) {
        return this.vertices.get(index);
    }

    public int indexOf(V vertex) {
        return this.vertices.indexOf(vertex);
    }

    public List<V> neighborsOf(int index) {
        return this.edges.get(index).stream()
                .map(edge -> vertexAt(edge.to))
                .toList();
    }

    public List<V> neighborsOf(V vertex) {
        return neighborsOf(indexOf(vertex));
    }

    public List<E> edgesOf(int index) {
        return this.edges.get(index);
    }

    public List<E> edgesOf(V vertex) {
        return edgesOf(indexOf(vertex));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getVertexCount(); i++)
            sb.append(vertexAt(i)).append(" -> ").append(Arrays.toString(neighborsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }
}