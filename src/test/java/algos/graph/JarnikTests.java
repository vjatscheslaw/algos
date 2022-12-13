/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.City;
import algos.graph.objects.CityNode;
import algos.graph.objects.WeightedRib;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static algos.graph.objects.City.*;

public class JarnikTests {

    @Test
    public void jarnickWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {

        WeightedAdjacencyMatrixGraph graph =
                new WeightedAdjacencyMatrixGraph<String, WeightedRib>(
                        new String[]{"A", "B", "C", "D", "E", "F", "J", "K", "Y"},
                        new double[9][9]
                );

        graph.connectNodes(graph.indexOf("A"), graph.indexOf("B"), 1.0d);
        graph.connectNodes(graph.indexOf("A"), graph.indexOf("D"), 10.0d);
        graph.connectNodes(graph.indexOf("B"), graph.indexOf("C"), 1.0d);
        graph.connectNodes(graph.indexOf("C"), graph.indexOf("F"), 1.0d);
        graph.connectNodes(graph.indexOf("B"), graph.indexOf("E"), 1.0d);
        graph.connectNodes(graph.indexOf("F"), graph.indexOf("E"), 1.0d);
        graph.connectNodes(graph.indexOf("E"), graph.indexOf("D"), 1.0d);
        graph.connectNodes(graph.indexOf("D"), graph.indexOf("J"), 1.0d);
        graph.connectNodes(graph.indexOf("J"), graph.indexOf("K"), 5.0d);
        graph.connectNodes(graph.indexOf("K"), graph.indexOf("Y"), 10.0d);
        graph.connectNodes(graph.indexOf("F"), graph.indexOf("Y"), 15.0d);
        graph.connectNodes(graph.indexOf("E"), graph.indexOf("K"), 1.0d);

        System.out.println(graph.toString());

        List<WeightedRib> minimalSpanningTree = null;
        for (int i = 0; i < 9; i++) {
            minimalSpanningTree = GraphUtil.jarnik(graph, i);
            Assertions.assertEquals(17.0d, minimalSpanningTree.stream().mapToDouble(WeightedRib::getWeight).sum());
        }
        GraphUtil.printWeighted(minimalSpanningTree, graph);
    }

    @Test
    public void jarnickCityWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {
        City[] arr = City.values();

        CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib> graph =
                new CityWeightedAdjacencyMatrixGraph<>(Arrays.stream(arr).map(CityNode::new).toArray(CityNode[]::new), new double[arr.length][arr.length]);

        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(GREAT_NOVGOROD), 193.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(MOSCOW), 710.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(TVER), 183.0d);
        graph.connectNodes(graph.indexOf(TVER), graph.indexOf(GREAT_NOVGOROD), 360.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(VYBORG), 136.0d);
        graph.connectNodes(graph.indexOf(PETROZAVODSK), graph.indexOf(SAINT_PETERSBURG), 430.0d);
        graph.connectNodes(graph.indexOf(VYBORG), graph.indexOf(PETROZAVODSK), 500.0d);
        graph.connectNodes(graph.indexOf(HELSINKI), graph.indexOf(VYBORG), 245.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(SHLISSELBURG), 54.0d);
        graph.connectNodes(graph.indexOf(SAINT_PETERSBURG), graph.indexOf(PSKOV), 293.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(LVOV), 540.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(ODESSA), 480.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(KHARKIV), 460.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(VORONEZH), 710.0d);
        graph.connectNodes(graph.indexOf(DNIPRO), graph.indexOf(KHARKIV), 217.0d);
        graph.connectNodes(graph.indexOf(KAZAN), graph.indexOf(MOSCOW), 810.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(BRYANSK), 380.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(BRYANSK), 470.0d);
        graph.connectNodes(graph.indexOf(KIEV), graph.indexOf(VINNITSA), 266.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(VLADIMIR), 185.0d);
        graph.connectNodes(graph.indexOf(MINSK), graph.indexOf(MOGILEV), 199.0d);
        graph.connectNodes(graph.indexOf(VITEBSK), graph.indexOf(PSKOV), 350.0d);
        graph.connectNodes(graph.indexOf(KALUGA), graph.indexOf(TULA), 107.0d);
        graph.connectNodes(graph.indexOf(TULA), graph.indexOf(RYAZAN), 182.0d);
        graph.connectNodes(graph.indexOf(SMOLENSK), graph.indexOf(MOSCOW), 400.0d);
        graph.connectNodes(graph.indexOf(SMOLENSK), graph.indexOf(MOGILEV), 203.0d);
        graph.connectNodes(graph.indexOf(KOSTROMA), graph.indexOf(KIROV), 620.0d);
        graph.connectNodes(graph.indexOf(YAROSLAVL), graph.indexOf(KOSTROMA), 84.0d);
        graph.connectNodes(graph.indexOf(MOSCOW), graph.indexOf(YAROSLAVL), 265.0d);
        graph.connectNodes(graph.indexOf(VOLOGDA), graph.indexOf(TIHVIN), 440.0d);
        graph.connectNodes(graph.indexOf(TIHVIN), graph.indexOf(SAINT_PETERSBURG), 220.0d);
        graph.connectNodes(graph.indexOf(TALLIN), graph.indexOf(VORONEZH), 370.0d);
        graph.connectNodes(graph.indexOf(SARANSK), graph.indexOf(RYAZAN), 450.0d);

        List<WeightedRib> minimalSpanningTree = null;
        for (int i = 0; i < 6; i++) { // whatever node you start from, the result must be the same
            minimalSpanningTree = GraphUtil.jarnik(graph, i);
            Assertions.assertEquals(9563.0d, minimalSpanningTree.stream().mapToDouble(WeightedRib::getWeight).sum());
        }
        GraphUtil.printWeighted(minimalSpanningTree, graph);
    }

}