package algos.graph;

import java.util.ArrayList;

public class UnweightedIncidentalityListBasedGraph<V, E extends Arc> extends IncidentalityListBasedGraph<V, E> {

    public UnweightedIncidentalityListBasedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public void addEdge(E edge) {
        arcs.get(edge.from).add(edge);
        arcs.get(edge.to).add((E) edge.reversed());
    }

    public void  addEdge(int from, int to) {
        addEdge(from, to);
    }

    public void addEdge(V first, V second) {
        addEdge((E) new Arc(indexOf(first), indexOf(second)));
    }


}