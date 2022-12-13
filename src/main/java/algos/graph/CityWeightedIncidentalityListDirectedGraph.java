/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.objects.City;
import algos.graph.objects.CityNode;
import algos.graph.objects.WeightedArc;

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
