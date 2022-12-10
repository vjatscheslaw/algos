/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph.objects;

import algos.graph.WeightedIncidentalityListDirectedGraph;

import java.util.ArrayList;

public class CityWeightedIncidentalityListDirectedGraph<N extends CityNode, A extends WeightedArc> extends WeightedIncidentalityListDirectedGraph<N, A> {
    public CityWeightedIncidentalityListDirectedGraph(ArrayList<N> vertices) {
        super(vertices);
    }
    public int indexOf(City city) {
        for (int i = 0; i < this.getNodes().size(); i++) if (this.getNodes().get(i).getCity().equals(city)) return i;
        return -1;
    }
}
