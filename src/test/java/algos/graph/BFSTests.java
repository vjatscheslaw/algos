/*
 * Copyright © 2022. This code's author is Viacheslav Mikhailov (mikhailowvw@gmail.com)
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

import java.util.concurrent.atomic.AtomicInteger;

import static algos.graph.objects.City.*;

public class BFSTests {

    static CityNode[] someNodes;
    boolean[][] adjacency;
    double[][] weightedAdjacency;
    AtomicInteger stepsCounter;
    static CityNode[] allNodes = new CityNode[City.values().length];
    WeightedAdjacencyMatrixGraph<String, WeightedRib> weightedGraph;

    @BeforeAll
    static void initCities() {
        for (int i = 0; i < City.values().length; i++) allNodes[i] = new CityNode(City.values()[i]);
        someNodes = new CityNode[]{
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
    void init() {
        adjacency = new boolean[someNodes.length][someNodes.length];
        weightedAdjacency = new double[City.values().length][City.values().length];
        stepsCounter = new AtomicInteger(0);

        weightedAdjacency[0][4] = 193.0d;
        weightedAdjacency[0][6] = 710.0d;
        weightedAdjacency[6][7] = 183.0d;
        weightedAdjacency[7][4] = 360.0d;
        weightedAdjacency[0][2] = 136.0d;
        weightedAdjacency[3][0] = 430.0d;
        weightedAdjacency[2][3] = 500.0d;
        weightedAdjacency[1][2] = 245.0d;
        weightedAdjacency[0][21] = 54.0d;
        weightedAdjacency[0][5] = 293.0d;
        weightedAdjacency[25][30] = 540.0d;
        weightedAdjacency[25][31] = 480.0d;
        weightedAdjacency[25][26] = 460.0d;
        weightedAdjacency[25][29] = 710.0d;
        weightedAdjacency[28][26] = 217.0d;
        weightedAdjacency[32][6] = 810.0d;
        weightedAdjacency[6][24] = 380.0d;
        weightedAdjacency[25][24] = 470.0d;
        weightedAdjacency[25][27] = 266.0d;
        weightedAdjacency[6][10] = 185.0d;
        weightedAdjacency[20][19] = 199.0d;
        weightedAdjacency[16][5] = 350.0d;
        weightedAdjacency[8][9] = 107.0d;
        weightedAdjacency[9][11] = 182.0d;
        weightedAdjacency[18][6] = 400.0d;
        weightedAdjacency[18][19] = 203.0d;
        weightedAdjacency[33][14] = 620.0d;
        weightedAdjacency[14][13] = 84.0d;
        weightedAdjacency[13][6] = 265.0d;
        weightedAdjacency[12][15] = 440.0d;
        weightedAdjacency[15][0] = 220.0d;
        weightedAdjacency[17][0] = 370.0d;
        weightedAdjacency[22][11] = 450.0d;
        weightedAdjacency[0][8] = 840.0d;
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToAllTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);
        for (int i = 0; i < someNodes.length; i++)
            for (int j = 0; j < someNodes.length; j++) {
                if (i == j) continue;
                orientedGraph.connectNodes(orientedGraph.nodeAt(i), orientedGraph.nodeAt(j));
            }

        for (int i = 0; i < someNodes.length; i++) Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[14]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(2, PathNodeUtil.nodeToPath(bfsResult).size());
    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphAllToOneTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph = new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);
        for (int i = 0; i < someNodes.length; i++) {
            if (i == 3) continue;
            orientedGraph.connectNodes(orientedGraph.nodeAt(i), orientedGraph.nodeAt(3));
        }

        for (int i = 0; i < someNodes.length; i++) Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[14]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNull(bfsResult);

        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[3]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(2, PathNodeUtil.nodeToPath(bfsResult).size());

        orientedGraph.connectNodes(0, 5);
        orientedGraph.connectNodes(5, 6);
        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[6]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());

    }

    @Test
    public void bfsAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        AdjacencyMatrixDirectedGraph<CityNode, Arc> orientedGraph =
                new AdjacencyMatrixDirectedGraph<CityNode, Arc>(someNodes, adjacency);

        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(1, 2);
        orientedGraph.connectNodes(0, 3);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 1);
        orientedGraph.connectNodes(0, 4);
        orientedGraph.connectNodes(4, 3);
        orientedGraph.connectNodes(0, 5);
        orientedGraph.connectNodes(5, 6);
        orientedGraph.connectNodes(4, 7);
        orientedGraph.connectNodes(5, 14);
        orientedGraph.connectNodes(14, 8);
        orientedGraph.connectNodes(8, 7);

        for (int i = 0; i < someNodes.length; i++)
            Assertions.assertEquals(i, orientedGraph.indexOf(someNodes[i]));

        PathNode<CityNode> bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());
        
        bfsResult = PathNodeUtil.bfs(someNodes[0], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[2]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(3, PathNodeUtil.nodeToPath(bfsResult).size());
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNull(bfsResult);
        orientedGraph.connectNodes(2, 1);
        orientedGraph.connectNodes(1, 0);
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(5, PathNodeUtil.nodeToPath(bfsResult).size());
        orientedGraph.disconnectNodes(0, 4);
        
        bfsResult = PathNodeUtil.bfs(someNodes[2], c -> orientedGraph.indexOf(c) == orientedGraph.indexOf(someNodes[7]), orientedGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(bfsResult);
        Assertions.assertEquals(7, PathNodeUtil.nodeToPath(bfsResult).size());
    }

    @Test
    public void bfsWeightedAdjacencyMatrixGraphTest() throws GraphInstantiationException {

        CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib> weightedCityGraph = new CityWeightedAdjacencyMatrixGraph<CityNode, WeightedRib>(allNodes, weightedAdjacency);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[0][4] = 193.0d;
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedAdjacency[4][0] = 193.0d;
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(GREAT_NOVGOROD), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedCityGraph.connectNodes(weightedCityGraph.indexOf(MOSCOW), weightedCityGraph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedCityGraph.disconnectNodes(weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(MOSCOW), weightedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedCityGraph.indexOf(c) == weightedCityGraph.indexOf(SAINT_PETERSBURG), weightedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);
    }

    @Test
    public void bfsWeightedAdjacencyMatrixDirectedGraphTest() throws GraphInstantiationException {

        CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc> weightedOrientedCityGraph =
                new CityWeightedAdjacencyMatrixDirectedGraph<CityNode, WeightedArc>(allNodes, weightedAdjacency);

        PathNode<CityNode> forth;
        PathNode<CityNode> back;

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(GREAT_NOVGOROD), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[0][4] = 193.0d;
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(GREAT_NOVGOROD), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        weightedAdjacency[4][0] = 193.0d;
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(GREAT_NOVGOROD), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[4], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(MOSCOW), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNull(back);

        weightedOrientedCityGraph.connectNodes(weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph.indexOf(MOSCOW), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(MOSCOW), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNull(back);

        weightedOrientedCityGraph.connectNodes(weightedOrientedCityGraph.indexOf(MOSCOW), weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), 710.0d);
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(MOSCOW), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNotNull(forth);
        Assertions.assertNotNull(back);

        weightedOrientedCityGraph.disconnectNodes(weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph.indexOf(MOSCOW));
        forth = PathNodeUtil.bfs(allNodes[0], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(MOSCOW), weightedOrientedCityGraph::successorsOf, stepsCounter);
        back = PathNodeUtil.bfs(allNodes[6], c -> weightedOrientedCityGraph.indexOf(c) == weightedOrientedCityGraph.indexOf(SAINT_PETERSBURG), weightedOrientedCityGraph::successorsOf, stepsCounter);
        Assertions.assertNull(forth);
        Assertions.assertNotNull(back);
    }
}
