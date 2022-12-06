package algos.genetic;

import java.util.List;

public abstract class Chromosome<T extends Chromosome<T>> implements Comparable<T> {
    public abstract double fitness();
    public abstract void mutate();
    public abstract List<T> crossover(T other);
    public abstract T copy();

    @Override
    public int compareTo(T other) {
        Double mine = this.fitness();
        Double theirs = other.fitness();
        return mine.compareTo(theirs);
    }
}
