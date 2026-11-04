package org.shashwatksingh.dsa.graphs;

import java.util.ArrayList;

import org.shashwatksingh.dsa.helpers.EdgeWithWeights;

public class DFS {

    public static void main(String[] args) {
        DFS dfs = new DFS();
        int V = 7;
        ArrayList<EdgeWithWeights>[] graph = new ArrayList[V];
        dfs.createGraph(graph);

        boolean[] visited = new boolean[V];

        dfs.execute(graph, 0, visited);


        // for (int i = 0; i < graph.length; i++) {
        //     if(!visited[i]) {
        //         visited[i] = true;
        //         dfs.execute(graph, V, visited);
        //     }
        // }
    }

    public void createGraph(ArrayList<EdgeWithWeights> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        graph[0].add(new EdgeWithWeights(0, 1, 1));
        graph[0].add(new EdgeWithWeights(0, 2, 1));
        graph[1].add(new EdgeWithWeights(1, 0, 1));
        graph[1].add(new EdgeWithWeights(1, 3, 1));

        graph[2].add(new EdgeWithWeights(2, 0, 1));
        graph[2].add(new EdgeWithWeights(2, 4, 1));
        graph[3].add(new EdgeWithWeights(3, 1, 1));
        graph[3].add(new EdgeWithWeights(3, 4, 1));
        graph[3].add(new EdgeWithWeights(3, 5, 1));
        graph[4].add(new EdgeWithWeights(4, 2, 1));
        graph[4].add(new EdgeWithWeights(4, 3, 1));
        graph[4].add(new EdgeWithWeights(4, 5, 1));
        graph[5].add(new EdgeWithWeights(5, 3, 1));
        graph[5].add(new EdgeWithWeights(5, 4, 1));
        graph[5].add(new EdgeWithWeights(5, 6, 1));
        graph[6].add(new EdgeWithWeights(6, 5, 1));
    }

    public void execute(ArrayList<EdgeWithWeights>[] graph, int curr, boolean[] visited) {
        if(visited[curr]) return;

        visited[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            EdgeWithWeights e = graph[curr].get(i);
            if(!visited[e.getDest()]) execute(graph, e.getDest(), visited);
        }
    }
}
