package algos.graph;

import algos.graph.objects.Arc;
import algos.graph.objects.DirectedGraph;
import algos.graph.objects.Node;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Generic implementation of an incidentality list based graph.
 * Incidentality list is an ArrayList of vertices which have an index matching to lists of their arcs,
 * which are themselves contained in a separate ArrayList for the reason of index matching.
 * Each of the arcs must have two index references to their vertices (from, to)
 *
 * @param <V> generic vertex type
 * @param <E> generified arc type
 */
public class IncidentalityListDirectedGraph<V extends Node, E extends Arc> implements DirectedGraph<V, E> {
    private ArrayList<V> nodes = new ArrayList<>();
    protected ArrayList<Set<E>> arcs = new ArrayList<>();

    public IncidentalityListDirectedGraph(ArrayList<V> nodes) {
        this.nodes = nodes;
        for (V vertex : nodes) this.arcs.add(new HashSet<>());
    }

    @Override
    public int getNodeCount() {
        return this.nodes.size();
    }

    public int getArcCount() {
        return this.arcs.stream().mapToInt(Set::size).sum();
    }

    public int addVertex(V vertex) {
        this.nodes.add(vertex);
        this.arcs.add(new HashSet<>());
        return getNodeCount() - 1;
    }

    @Override
    public V nodeAt(int index) {
        return this.nodes.get(index);
    }

    @Override
    public int getRibCount() {
        return getArcCount();
    }

    public int indexOf(V vertex) {
        return this.nodes.indexOf(vertex);
    }

    @Override
    public void connectNodes(V from, V to) {

    }

    @Override
    public void connectNodes(int from, int to) {
        this.arcs.get(from).add((E) new Arc(from, to));
        this.arcs.get(to).add((E) new Arc(to, from));
    }

    @Override
    public void disconnectNodes(V from, V to) {

    }

    @Override
    public void disconnectNodes(int from, int to) {
        Set<E> arcsFrom = this.arcs.get(from);
        Set<E> arcsTo = this.arcs.get(to);
    }

    public Set<V> successorsOf(int index) {
        return this.arcs.get(index).stream()
                .map(edge -> nodeAt(edge.to))
                .collect(Collectors.toSet());
    }

    public Set<V> successorsOf(V vertex) {
        return successorsOf(indexOf(vertex));
    }

    public Set<E> arcsOf(int index) {
        return this.arcs.get(index);
    }

    public Set<E> arcsOf(V vertex) {
        return arcsOf(indexOf(vertex));
    }

    public ArrayList<V> getNodes() {
        return nodes;
    }

    public ArrayList<Set<E>> getArcs() {
        return arcs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getNodeCount(); i++)
            sb.append(nodeAt(i)).append(" -> ").append(Arrays.toString(successorsOf(i).toArray())).append(System.lineSeparator());
        return sb.toString();
    }
}