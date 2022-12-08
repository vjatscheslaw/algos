package algos.graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedIncidentalityListBasedGraph<V, E extends WeightedArc> extends IncidentalityListBasedGraph<V, E> {

    public WeightedIncidentalityListBasedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public void addEdge(E edge) {
        arcs.get(edge.from).add(edge);
        arcs.get(edge.to).add((E) edge.reversed());
    }

    public void addEdge(int from, int to, double weight) {
        addEdge((E) new WeightedArc(from, to, weight));
    }

    public void addEdge(V from, V to, double weight) {
        addEdge(indexOf(from), indexOf(to), weight);
    }

    public static double totalWeight(List<WeightedArc> path) {
        return path.parallelStream().mapToDouble(we -> we.weight).sum();
    }

}