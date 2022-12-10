/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.*;
import algos.maze.PathNode;
import algos.maze.PathNodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static algos.graph.objects.City.*;

public class AdjacencyMatrixDirectedGraphTests {
    static CityNode[] nodes;
    boolean[][] adjacency;
    double[][] weightedAdjacency;
    AtomicInteger stepsCounter;
    AdjacencyMatrixDirectedGraph<CityNode, Arc> directedGraph;
    CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc> weightedDirectedGraph;
    CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib> weightedGraph;

    @BeforeAll
    static void initCities() {
        nodes = new CityNode[]{
                new CityNode(SAINT_PETERSBURG),
                new CityNode(VYBORG),
                new CityNode(HELSINKI),
                new CityNode(PETROZAVODSK),
                new CityNode(SHLISSELBURG),
                new CityNode(GREAT_NOVGOROD),
                new CityNode(PSKOV),
                new CityNode(VOLOGDA),
                new CityNode(YAROSLAVL),
                new CityNode(KOSTROMA),
                new CityNode(VLADIMIR),
                new CityNode(MOSCOW),
                new CityNode(RYAZAN),
                new CityNode(TULA),
                new CityNode(TVER)
        };
    }

    @BeforeEach
    void init() throws GraphInstantiationException {
        adjacency = new boolean[nodes.length][nodes.length];
        stepsCounter = new AtomicInteger(0);
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToAllTest() throws GraphInstantiationException {

        directedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(nodes, adjacency);
        for (int i = 0; i < nodes.length; i++)
            for (int j = 0; j < nodes.length; j++) {
                if (i == j) continue;
                directedGraph.connectNodes(directedGraph.nodeAt(i), directedGraph.nodeAt(j));
            }

        for (int i = 0; i < nodes.length; i++) Assertions.assertEquals(i, directedGraph.indexOf(nodes[i]));

        PathNode<CityNode> bfsResult = GraphUtil.bfs(nodes[0], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[14]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + SAINT_PETERSBURG + " to " + MOSCOW + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToOneTest() throws GraphInstantiationException {

        directedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(nodes, adjacency);
        for (int i = 0; i < nodes.length; i++) {
            if (i == 3) continue;
            directedGraph.connectNodes(directedGraph.nodeAt(i), directedGraph.nodeAt(3));
        }

        for (int i = 0; i < nodes.length; i++) Assertions.assertEquals(i, directedGraph.indexOf(nodes[i]));

        PathNode<CityNode> bfsResult = GraphUtil.bfs(nodes[0], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[14]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[0].getCity() + " to " + nodes[14].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        bfsResult = GraphUtil.bfs(nodes[0], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[3]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[0].getCity() + " to " + nodes[3].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        directedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(nodes, adjacency);
        directedGraph.connectNodes(0, 1);
        directedGraph.connectNodes(1, 2);
        directedGraph.connectNodes(0, 3);
        directedGraph.connectNodes(0, 1);
        directedGraph.connectNodes(0, 1);
        directedGraph.connectNodes(0, 4);
        directedGraph.connectNodes(4, 3);
        directedGraph.connectNodes(0, 5);
        directedGraph.connectNodes(5, 6);
        directedGraph.connectNodes(4, 7);
        directedGraph.connectNodes(5, 14);
        directedGraph.connectNodes(14, 8);
        directedGraph.connectNodes(8, 7);

        for (int i = 0; i < nodes.length; i++) Assertions.assertEquals(i, directedGraph.indexOf(nodes[i]));

        PathNode<CityNode> bfsResult = GraphUtil.bfs(nodes[0], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[7]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[0].getCity() + " to " + nodes[7].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        bfsResult = GraphUtil.bfs(nodes[0], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[2]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[0].getCity() + " to " + nodes[2].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        bfsResult = GraphUtil.bfs(nodes[2], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[7]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[2].getCity() + " to " + nodes[7].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        directedGraph.connectNodes(2, 1);
        directedGraph.connectNodes(1, 0);
        bfsResult = GraphUtil.bfs(nodes[2], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[7]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[2].getCity() + " to " + nodes[7].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        directedGraph.disconnectNodes(0, 4);
        bfsResult = GraphUtil.bfs(nodes[2], c -> directedGraph.indexOf(c) == directedGraph.indexOf(nodes[7]), directedGraph, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<CityNode> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from " + nodes[2].getCity() + " to " + nodes[7].getCity() + "\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }
    }

    @Test
    public void bfsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {

        City[] allCities = City.values();
        CityNode[] allNodes = new CityNode[allCities.length];
        for (int i = 0; i < allCities.length; i++) allNodes[i] = new CityNode(allCities[i]);

        weightedAdjacency = new double[allCities.length][allCities.length];
        weightedGraph = new CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib>(allNodes, weightedAdjacency);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(GREAT_NOVGOROD), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[0][4] = 193.0d;
        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(GREAT_NOVGOROD), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedAdjacency[4][0] = 193.0d;
        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(GREAT_NOVGOROD), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(MOSCOW), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedGraph.connectNodes(weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph.indexOf(MOSCOW), 710.0d);
        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(MOSCOW), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedGraph.connectNodes(weightedGraph.indexOf(MOSCOW), weightedGraph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(MOSCOW), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedGraph.disconnectNodes(weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph.indexOf(MOSCOW));
        forth = GraphUtil.bfs(allNodes[0], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(MOSCOW), weightedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedGraph.indexOf(c) == weightedGraph.indexOf(SAINT_PETERSBURG), weightedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);
    }

    @Test
    public void bfsWeightedAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        City[] allCities = City.values();
        CityNode[] allNodes = new CityNode[allCities.length];
        for (int i = 0; i < allCities.length; i++) allNodes[i] = new CityNode(allCities[i]);

        weightedAdjacency = new double[allCities.length][allCities.length];
        weightedDirectedGraph = new CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc>(allNodes, weightedAdjacency);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(GREAT_NOVGOROD), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[0][4] = 193.0d;
        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(GREAT_NOVGOROD), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[4][0] = 193.0d;
        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(GREAT_NOVGOROD), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[4], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(MOSCOW), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedDirectedGraph.connectNodes(weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph.indexOf(MOSCOW), 710.0d);
        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(MOSCOW), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        weightedDirectedGraph.connectNodes(weightedDirectedGraph.indexOf(MOSCOW), weightedDirectedGraph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(MOSCOW), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedDirectedGraph.disconnectNodes(weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph.indexOf(MOSCOW));
        forth = GraphUtil.bfs(allNodes[0], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(MOSCOW), weightedDirectedGraph, stepsCounter);
        back = GraphUtil.bfs(allNodes[6], c -> weightedDirectedGraph.indexOf(c) == weightedDirectedGraph.indexOf(SAINT_PETERSBURG), weightedDirectedGraph, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNotNull(back);
    }

//        weightedAdjacency[0][4] = 193.0d;
//        weightedAdjacency[0][6] = 710.0d;
//        weightedAdjacency[6][7] = 183.0d;
//        weightedAdjacency[7][4] = 360.0d;
//        weightedAdjacency[0][2] = 136.0d;
//        weightedAdjacency[3][0] = 430.0d;
//        weightedAdjacency[2][3] = 500.0d;
//        weightedAdjacency[1][2] = 245.0d;
//        weightedAdjacency[0][21] = 54.0d;
//        weightedAdjacency[0][5] = 293.0d;
//        weightedAdjacency[25][30] = 540.0d;
//        weightedAdjacency[25][31] = 480.0d;
//        weightedAdjacency[25][26] = 460.0d;
//        weightedAdjacency[25][29] = 710.0d;
//        weightedAdjacency[28][26] = 217.0d;
//        weightedAdjacency[32][6] = 810.0d;
//        weightedAdjacency[6][24] = 380.0d;
//        weightedAdjacency[25][24] = 470.0d;
//        weightedAdjacency[25][27] = 266.0d;
//        weightedAdjacency[6][10] = 185.0d;
//        weightedAdjacency[20][19] = 199.0d;
//        weightedAdjacency[16][5] = 350.0d;
//        weightedAdjacency[8][9] = 107.0d;
//        weightedAdjacency[9][11] = 182.0d;
//        weightedAdjacency[18][6] = 400.0d;
//        weightedAdjacency[18][19] = 203.0d;
//        weightedAdjacency[33][14] = 620.0d;
//        weightedAdjacency[14][13] = 84.0d;
//        weightedAdjacency[13][6] = 265.0d;
//        weightedAdjacency[12][15] = 440.0d;
//        weightedAdjacency[15][0] = 220.0d;
//        weightedAdjacency[17][0] = 370.0d;
//        weightedAdjacency[22][11] = 450.0d;
//        weightedAdjacency[0][8] = 840.0d;

//        for (int i = 0; i < nodes.length; i++)
//            for (int j = 0; j < nodes.length; j++) {
//                if (i == j) continue;
//                weightedRouteGraph.connectNodes(weightedRouteGraph.nodeAt(i), weightedRouteGraph.nodeAt(j), weightedAdjacency[i][j]);
//            }
//
//        for (int i = 0; i < allNodes.length; i++) Assertions.assertEquals(i, weightedRouteGraph.indexOf(allNodes[i]));

//        bfsResult = GraphUtil.bfs(allNodes[25], c -> weightedRouteGraph.indexOf(c) == weightedRouteGraph.indexOf(SAINT_PETERSBURG), weightedRouteGraph, stepsCounter);

//        if (forth == null) {
//            System.out.println("Unreachable");
//        } else {
//            List<CityNode> path = PathNodeUtil.nodeToPath(forth);
//            System.out.print("Path from " + City.values()[weightedRouteGraph.indexOf(KIEV)] + " to " + City.values()[weightedRouteGraph.indexOf(SAINT_PETERSBURG)] + "\n");
//            System.out.println(path);
//            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
//        }

}