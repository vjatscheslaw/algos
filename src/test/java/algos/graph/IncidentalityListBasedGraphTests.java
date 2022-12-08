/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import algos.maze.PathNode;
import algos.maze.PathNodeUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static algos.graph.IncidentalityListBasedGraphTests.City.*;

public class IncidentalityListBasedGraphTests {
    static ArrayList<String> listOfCities;
    UnweightedIncidentalityListBasedGraph<City, Arc> routeGraph1;
    WeightedIncidentalityListBasedGraph<City, WeightedArc> routeGraph2, routeGraph3;

    enum City {

        SAINT_PETERSBURG("Saint-Petersburg"),
        GREAT_NOVGOROD("Novgorod the Great"),
        PSKOV("Pskov"),
        MOSCOW("Moscow"),
        TVER("Tver"),
        VYBORG("Vyborg"),
        VOLOGDA("Vologda"),
        YAROSLAVL("Yaroslavl"),
        KOSTROMA("Kostroma"),
        PETROZAVODSK("Petrozavodsk"),
        TIHVIN("Tihvin"),
        VITEBSK("Vitebsk"),
        TALLIN("Tallin"),
        HELSINKI("Helsinki"),
        RYAZAN("Ryazan'"),
        KALUGA("Kaluga"),
        TULA("Tula"),
        SMOLENSK("Smolensk"),
        MOGILEV("Mogilev"),
        MINSK("Minsk"),
        SHLISSELBURG("Shlisselburg"),
        SARANSK("Saransk"),
        BRYANSK("Bryansk"),
        KIEV("Kiev"),
        KHARKIV("Kharkiv"),
        VINNITSA("Vinnitsa"),
        DNIPRO("Dnipro"),
        VORONEZH("Voronezh"),
        LVOV("Lvov"),
        ODESSA("Odessa"),
        VLADIMIR("Vladimir"),
        KAZAN("Kazan"),
        KIROV("Kirov");

        private String enTextual;

        City(String s) {
            this.enTextual = s;
        }

        public boolean equals(City other) {
            return this.toString().equals(other.toString());
        }

        public String toString() {
            return enTextual;
        }

    }

    @BeforeAll
    static void initCities() {
        listOfCities = new ArrayList(List.of(
                SAINT_PETERSBURG
                ,GREAT_NOVGOROD
                ,PSKOV
                ,TVER
                ,MOSCOW
                ,VYBORG
                ,VOLOGDA
                ,YAROSLAVL
                ,KOSTROMA
                ,PETROZAVODSK
                ,TIHVIN
                ,VITEBSK
                ,TALLIN
                ,HELSINKI
                ,RYAZAN
                ,KALUGA
                ,TULA
                ,SMOLENSK
                ,MOGILEV
                ,MINSK
                ,SHLISSELBURG
                ,SARANSK
                ,BRYANSK
                ,KIEV
                ,KHARKIV
                ,VINNITSA
                ,DNIPRO
                ,VORONEZH
                ,LVOV
                ,ODESSA
                ,VLADIMIR
                ,KAZAN
                ,KIROV
        ));
    }

    @BeforeEach
    void init() {
        routeGraph1 = new UnweightedIncidentalityListBasedGraph(listOfCities);
        routeGraph2 = new WeightedIncidentalityListBasedGraph(listOfCities);
        routeGraph3 = new WeightedIncidentalityListBasedGraph(listOfCities);

        routeGraph1.addEdge(SAINT_PETERSBURG, GREAT_NOVGOROD);
        routeGraph1.addEdge(SAINT_PETERSBURG, MOSCOW);
        routeGraph1.addEdge(MOSCOW, TVER);
        routeGraph1.addEdge(TVER, GREAT_NOVGOROD);
        routeGraph1.addEdge(SAINT_PETERSBURG, VYBORG);
        routeGraph1.addEdge(SAINT_PETERSBURG, PETROZAVODSK);
        routeGraph1.addEdge(HELSINKI, VYBORG);
        routeGraph1.addEdge(SAINT_PETERSBURG, SHLISSELBURG);
        routeGraph1.addEdge(SAINT_PETERSBURG, PSKOV);
        routeGraph1.addEdge(KIEV, LVOV);
        routeGraph1.addEdge(KIEV, ODESSA);
        routeGraph1.addEdge(KIEV, KHARKIV);
        routeGraph1.addEdge(KIEV, VORONEZH);
        routeGraph1.addEdge(DNIPRO, KHARKIV);
        routeGraph1.addEdge(KAZAN, MOSCOW);
        routeGraph1.addEdge(MOSCOW, BRYANSK);
        routeGraph1.addEdge(KIEV, BRYANSK);
        routeGraph1.addEdge(KIEV, VINNITSA);
        routeGraph1.addEdge(MOSCOW, VLADIMIR);
        routeGraph1.addEdge(MINSK, MOGILEV);
        routeGraph1.addEdge(VITEBSK, PSKOV);
        routeGraph1.addEdge(KALUGA, TULA);
        routeGraph1.addEdge(TULA, RYAZAN);
        routeGraph1.addEdge(SMOLENSK, MOSCOW);
        routeGraph1.addEdge(SMOLENSK, MOGILEV);
        routeGraph1.addEdge(KIROV, KOSTROMA);
        routeGraph1.addEdge(KOSTROMA, YAROSLAVL);
        routeGraph1.addEdge(YAROSLAVL, MOSCOW);
        routeGraph1.addEdge(VOLOGDA, TIHVIN);
        routeGraph1.addEdge(TIHVIN, SAINT_PETERSBURG);
        routeGraph1.addEdge(TALLIN, SAINT_PETERSBURG);
        routeGraph1.addEdge(SARANSK, RYAZAN);
        routeGraph1.addEdge(MOSCOW, KALUGA);

        routeGraph2.addEdge(SAINT_PETERSBURG, GREAT_NOVGOROD, 193);
        routeGraph2.addEdge(SAINT_PETERSBURG, MOSCOW, 710);
        routeGraph2.addEdge(MOSCOW, TVER, 183);
        routeGraph2.addEdge(TVER, GREAT_NOVGOROD, 360);
        routeGraph2.addEdge(SAINT_PETERSBURG, VYBORG, 136);
        routeGraph2.addEdge(PETROZAVODSK, SAINT_PETERSBURG, 430);
        routeGraph2.addEdge(VYBORG, PETROZAVODSK, 500);
        routeGraph2.addEdge(HELSINKI, VYBORG, 245);
        routeGraph2.addEdge(SAINT_PETERSBURG, SHLISSELBURG, 54);
        routeGraph2.addEdge(SAINT_PETERSBURG, PSKOV, 293);
        routeGraph2.addEdge(KIEV, LVOV, 540);
        routeGraph2.addEdge(KIEV, ODESSA, 480);
        routeGraph2.addEdge(KIEV, KHARKIV, 460);
        routeGraph2.addEdge(KIEV, VORONEZH, 710);
        routeGraph2.addEdge(DNIPRO, KHARKIV, 217);
        routeGraph2.addEdge(KAZAN, MOSCOW, 810);
        routeGraph2.addEdge(MOSCOW, BRYANSK, 380);
        routeGraph2.addEdge(KIEV, BRYANSK, 470);
        routeGraph2.addEdge(KIEV, VINNITSA, 266);
        routeGraph2.addEdge(MOSCOW, VLADIMIR, 185);
        routeGraph2.addEdge(MINSK, MOGILEV, 199);
        routeGraph2.addEdge(VITEBSK, PSKOV, 350);
        routeGraph2.addEdge(KALUGA, TULA, 107);
        routeGraph2.addEdge(TULA, RYAZAN, 182);
        routeGraph2.addEdge(SMOLENSK, MOSCOW, 400);
        routeGraph2.addEdge(SMOLENSK, MOGILEV, 203);
        routeGraph2.addEdge(KIROV, KOSTROMA, 620);
        routeGraph2.addEdge(KOSTROMA, YAROSLAVL, 84);
        routeGraph2.addEdge(YAROSLAVL, MOSCOW, 265);
        routeGraph2.addEdge(VOLOGDA, TIHVIN, 440);
        routeGraph2.addEdge(TIHVIN, SAINT_PETERSBURG, 220);
        routeGraph2.addEdge(TALLIN, SAINT_PETERSBURG, 370);
        routeGraph2.addEdge(SARANSK, RYAZAN, 450);
        routeGraph2.addEdge(SAINT_PETERSBURG, KALUGA, 840);

        routeGraph3.addEdge(SAINT_PETERSBURG, GREAT_NOVGOROD, 193);
        routeGraph3.addEdge(MOSCOW, TVER, 183);
        routeGraph3.addEdge(SAINT_PETERSBURG, MOSCOW, 710);
        routeGraph3.addEdge(SAINT_PETERSBURG, VYBORG, 136);
        routeGraph3.addEdge(SAINT_PETERSBURG, PETROZAVODSK, 430);
        routeGraph3.addEdge(TVER, GREAT_NOVGOROD, 360);
    }


    @Test
    public void unweightedGraphTest() {
        AtomicInteger stepsCounter = new AtomicInteger(0);

        PathNode<City> bfsResult = PathNodeUtil.bfs(SAINT_PETERSBURG, v -> v.equals(MINSK), routeGraph1::neighborsOf, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<City> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from Saint-Petersburg to Minsk\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }

        bfsResult = PathNodeUtil.bfs(SAINT_PETERSBURG, v -> v.equals(KIEV), routeGraph1::neighborsOf, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<City> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from Saint-Petersburg to Kiev\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }
    }

    @Test
    public void weightedGraphTest() {
        AtomicInteger stepsCounter = new AtomicInteger(0);

        PathNode<City> bfsResult = PathNodeUtil.bfs(SAINT_PETERSBURG, v -> v.equals(ODESSA), routeGraph2::neighborsOf, stepsCounter);

        if (bfsResult == null) {
            System.out.println("Unreachable");
        } else {
            List<City> path = PathNodeUtil.nodeToPath(bfsResult);
            System.out.print("Path from Saint-Petersburg to Odessa\n");
            System.out.println(path);
            System.out.print("The path was discovered in " + stepsCounter.get() + " steps\n");
        }
    }

    @Test
    public void jarnikTest() {
        List<WeightedArc> jarnikPath = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>jarnik(routeGraph2, 0);
        Assertions.assertEquals(66, routeGraph2.getArcCount());
        Assertions.assertEquals(32, jarnikPath.size());
        routeGraph2.addEdge(SAINT_PETERSBURG, MINSK, 800);
        List<WeightedArc> jarnikPath1 = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>jarnik(routeGraph2, 0);
        Assertions.assertEquals(68, routeGraph2.getArcCount());
        Assertions.assertEquals(32, jarnikPath1.size());
        routeGraph2.addEdge(SAINT_PETERSBURG, DNIPRO, 1640);
        List<WeightedArc> jarnikPath2 = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>jarnik(routeGraph2, 14);
        Assertions.assertEquals(70, routeGraph2.getArcCount());
        Assertions.assertEquals(32, jarnikPath2.size());

        System.out.println(GraphUtil.printWeightedPath(jarnikPath2, routeGraph2));
    }

    @Test
    public void dijkstraTest() {
        GraphUtil.DijkstraResult<WeightedArc> dijkstraPath = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>dijkstra(SAINT_PETERSBURG, routeGraph2);
        Map<City, Double> nameDistance = GraphUtil.distanceArrayToDistanceMap(dijkstraPath.distances, routeGraph2);
        System.out.println("Distances from " + SAINT_PETERSBURG);
        nameDistance.forEach((name, distance) -> System.out.println(name + " : " + distance));
        System.out.println("Shortest path from " + SAINT_PETERSBURG + " to " + RYAZAN);
        List<WeightedArc> path = GraphUtil.pathMapToPathList(routeGraph2.indexOf(SAINT_PETERSBURG), routeGraph2.indexOf(RYAZAN), dijkstraPath.pathMap);
        System.out.println(GraphUtil.printWeightedPath(path, routeGraph2));

        dijkstraPath = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>dijkstra(KIEV, routeGraph2);
        System.out.println("Shortest path from " + KIEV + " to " + PETROZAVODSK);
        path = GraphUtil.pathMapToPathList(routeGraph2.indexOf(KIEV), routeGraph2.indexOf(PETROZAVODSK), dijkstraPath.pathMap);
        System.out.println(GraphUtil.printWeightedPath(path, routeGraph2));

        dijkstraPath = GraphUtil.<City, WeightedArc, WeightedIncidentalityListBasedGraph<City, WeightedArc>>dijkstra(TVER, routeGraph3);
    }


}