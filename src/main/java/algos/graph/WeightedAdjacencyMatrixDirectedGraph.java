/*
 * Copyright (c) 2022. This code was written by Viacheslav Mikhailov. You may contact him (me) via email taleskeeper@yandex.ru
 */
package algos.graph;

import algos.graph.exception.GraphInstantiationException;
import algos.graph.objects.Arc;
import algos.graph.objects.Node;

public class UnweightedAdjacencyMatrixDirectedGraph<N extends Node, A extends Arc> extends AdjacencyMatrixDirectedGraph<N, A> {
    public UnweightedAdjacencyMatrixDirectedGraph(N[] nodes, boolean[][] adjacency) throws GraphInstantiationException {
        super(nodes, adjacency);
    }
}
