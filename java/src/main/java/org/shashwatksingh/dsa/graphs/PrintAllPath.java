package org.shashwatksingh.dsa.graphs;

import java.util.ArrayList;
import java.util.List;

import org.shashwatksingh.dsa.helpers.Edge;

public class PrintAllPath {

    private final List<Edge>[] graph;

    /** The graph is supplied by the caller: graph[i] holds the outgoing edges of vertex i. */
    public PrintAllPath(List<Edge>[] graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        int V = 8;
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 6, 7 } };

        @SuppressWarnings("unchecked")
        List<Edge>[] graph = new List[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[0], e[1]));
            graph[e[1]].add(new Edge(e[1], e[0]));
        }

        new PrintAllPath(graph).dfs(0, new boolean[V]);
    }

    public void dfs(int curr, boolean[] visited) {
        if(visited[curr]) return;

        visited[curr] = true;
        System.out.print(curr + " -->" );

        for (int i = 0; i < graph[curr].size(); i++) {
            Edge neighbor = graph[curr].get(i);
            if(!visited[neighbor.getDest()]) dfs(neighbor.getDest(), visited);
        }
    }
}
