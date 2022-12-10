/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

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
            if (this.getNodes()[i].getCity().equals(city)) return i;
        }
        return -1;
    }
}