package algos.graph;

/**
 * Arc is a representation of a connection between two nodes of a directed graph.
 */
public class Arc {

    public final int from, to;

    public Arc(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public Arc reversed() {
        return new Arc(to, from);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arc arc = (Arc) o;

        if (from != arc.from) return false;
        return to == arc.to;
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