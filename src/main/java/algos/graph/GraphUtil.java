/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import java.util.*;
import java.util.function.IntConsumer;

public class GraphUtil {

    /**
     * This algorithm is also known as Robert C. Prim's algorithm (1957), though firstly invented by Vojtech Jarnik in 1930
     * The algorithm returns a minimum spanning tree of the graph as a list of edges
     *
     * @param graph the graph object
     * @param start an index of an edge where we start our journey from
     * @param <V>   generic vertex type
     * @param <E>   generic edge (rib, arc) type
     * @param <T>   generic graph implementation type
     * @return a minimum spanning tree of the graph
     */
    public static <V, E extends WeightedArc, T extends WeightedIncidentalityListBasedGraph<V, E>> List<E> jarnik(T graph, int start) {
        LinkedList<E> result = new LinkedList<>();

        if (start < 0 || start > (graph.getVertexCount() - 1)) {
            return result;
        }
        final PriorityQueue<E> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.getVertexCount()];

        IntConsumer visit = index -> {
            visited[index] = true;
            for (E edge : graph.arcsOf(index)) if (!visited[edge.to]) pq.offer(edge);
        };

        visit.accept(start);
        while (!pq.isEmpty()) {
            E edge = pq.poll();
            if (visited[edge.to]) continue;
            result.add(edge);
            visit.accept(edge.to);
        }

        return result;
    }

    /**
     * The method returns a String representation of the path on the given graph
     *
     * @param path  list of interconnected edges of the graph
     * @param graph the graph
     * @param <T>   generic vertex type
     * @param <V>   generic graph implementation type
     * @param <E>   generic edge (rib, arc) type
     * @return String representation of the path
     */
    public static <T, V extends WeightedIncidentalityListBasedGraph<T, E>, E extends WeightedArc> String printWeightedPath(List<E> path, V graph) {
        StringBuilder sb = new StringBuilder();
        for (E edge : path)
            sb.append(graph.vertexAt(edge.from)).append(" =").append(edge.weight).append("=> ").append(graph.vertexAt(edge.to)).append("\n");
        return sb.toString();
    }

    public static final class DijkstraNode implements Comparable<DijkstraNode> {
        public final int vertex;
        public final double distance;

        public DijkstraNode(int vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(DijkstraNode o) {
            Double mine = this.distance;
            Double their = o.distance;
            return mine.compareTo(their);
        }
    }

    public static final class DijkstraResult<E extends WeightedArc> {
        public final double[] distances;
        public final Map<Integer, E> pathMap;

        public DijkstraResult(double[] distances, Map<Integer, E> pathMap) {
            this.distances = distances;
            this.pathMap = pathMap;
        }
    }

    /**
     * The method calculates the shortest distance from the root node to any other node of the given graph
     *
     * @param root - the root node
     * @param <N>  - graph node generic type
     * @param <T>  - graph edge (arc, rib) generic type
     * @param <G>  - generic graph implementation type
     * @return an encapsulated result of the calculation
     */
    public static <N, T extends WeightedArc, G extends WeightedIncidentalityListBasedGraph<N, T>> DijkstraResult<T> dijkstra(N root, G graph) {
        int start = graph.indexOf(root);
        double[] distances = new double[graph.getVertexCount()];
        distances[start] = .0d;
        boolean[] visited = new boolean[graph.getVertexCount()];
        visited[start] = true;
        HashMap<Integer, T> pathMap = new HashMap<>();
        PriorityQueue<DijkstraNode> pQueue = new PriorityQueue<>();
        pQueue.offer(new DijkstraNode(start, distances[start]));

        while (!pQueue.isEmpty()) {
            int nodeIndex = pQueue.poll().vertex;
            double nodeDistance = distances[nodeIndex];
            for (T wEdge : graph.arcsOf(nodeIndex)) {
                double oldDistance = distances[wEdge.to];
                double pathWeight = wEdge.weight + nodeDistance;
                if (!visited[wEdge.to] || (oldDistance > pathWeight)) {
                    visited[wEdge.to] = true;
                    distances[wEdge.to] = pathWeight;
                    pathMap.put(wEdge.to, wEdge);
                    pQueue.offer(new DijkstraNode(wEdge.to, distances[wEdge.to]));
                }
            }
        }
        return new DijkstraResult<>(distances, pathMap);
    }

    public static <N, T extends WeightedArc, G extends WeightedIncidentalityListBasedGraph<N, T>> Map<N, Double> distanceArrayToDistanceMap(double[] distances, G graph) {
        HashMap<N, Double> distanceMap = new HashMap<>();
        for (int i = 0; i < distances.length; i++) distanceMap.put(graph.vertexAt(i), distances[i]);
        return distanceMap;
    }

    public static List<WeightedArc> pathMapToPathList(int start, int end, Map<Integer, WeightedArc> pathMap) {
        if (pathMap.size() == 0) return List.of();
        LinkedList<WeightedArc> path = new LinkedList<>();
        WeightedArc wArc = pathMap.get(end);
        path.add(wArc);
        while (wArc.from != start) {
            wArc = pathMap.get(wArc.from);
            path.add(wArc);
        }
        Collections.reverse(path);
        return path;
    }

}