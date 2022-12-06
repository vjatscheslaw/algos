package algos.graph;

/**
 * Edge is a class representing a connection between to vertices of a graph
 */
public class Edge {

    public final int from, to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Edge reversed() {
        return new Edge(to, from);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (from != edge.from) return false;
        return to == edge.to;
    }

    @Override
    public int hashCode() {
        int result = from;
        result = 31 * result + to;
        return result;
    }

    @Override
    public String toString() {
        return from + " -> " + to ;
    }
}