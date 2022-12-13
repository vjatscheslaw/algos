/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph.specialized;

import algos.graph.WeightedAdjacencyMatrixDirectedGraph;
import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.City;
import algos.graph.objects.CityNode;
import algos.graph.objects.WeightedArc;

public class CityWeightedAdjacencyMatrixDirectedGraph<N extends CityNode, A extends WeightedArc> extends WeightedAdjacencyMatrixDirectedGraph<N, A> {
    public CityWeightedAdjacencyMatrixDirectedGraph(N[] vertices, double[][] adjacency) throws GraphInstantiationException {
        super(vertices, adjacency);
    }

    @Override
    public int indexOf(CityNode node) {
        return this.indexOf(node.getCity());
    }

    public int indexOf(City city) {
        for (int i = 0; i < this.getNodes().length; i++) {
            if (this.getNodes()[i].getCity() == city) return i;
        }
        return -1;
    }
}