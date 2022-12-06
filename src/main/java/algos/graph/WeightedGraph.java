package algos.graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph<V, E extends Edge> extends Graph<V, E> {

    public WeightedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public void addEdge(E edge) {
        edges.get(edge.from).add(edge);
        edges.get(edge.to).add((E) edge.reversed());
    }

    public void addEdge(int from, int to, double weight) {
        addEdge((E) new WeightedEdge(from, to, weight));
    }

    public void addEdge(V from, V to, double weight) {
        addEdge(indexOf(from), indexOf(to), weight);
    }

    public static double totalWeight(List<WeightedEdge> path) {
        return path.parallelStream().mapToDouble(we -> we.weight).sum();
    }

}