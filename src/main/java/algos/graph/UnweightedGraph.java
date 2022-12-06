package algos.graph;

import java.util.ArrayList;

public class UnweightedGraph<V, E extends Edge> extends Graph<V, E> {

    public UnweightedGraph(ArrayList<V> vertices) {
        super(vertices);
    }

    public void addEdge(E edge) {
        edges.get(edge.from).add(edge);
        edges.get(edge.to).add((E) edge.reversed());
    }

    public void  addEdge(int from, int to) {
        addEdge(from, to);
    }

    public void addEdge(V first, V second) {
        addEdge((E) new Edge(indexOf(first), indexOf(second)));
    }


}