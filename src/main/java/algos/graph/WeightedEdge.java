package algos.graph;

/**
 * WeightedEdge is a class representing a connection between to vertices of a graph,
 * which has its cost of reach
 */
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {

    public final double weight;

    public WeightedEdge(int from, int to, double weight) {
        super(from, to);
        this.weight = weight;
    }

    @Override
    public WeightedEdge reversed() {
        return new WeightedEdge(to, from, this.weight);
    }

    public int compareTo(WeightedEdge other) {
        Double mine = this.weight;
        Double their = other.weight;
        return mine.compareTo(their);
    }

    @Override
    public String toString() {
        return from + " " + weight + "> " + to;
    }

}