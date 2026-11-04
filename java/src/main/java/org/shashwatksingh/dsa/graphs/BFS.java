package org.shashwatksingh.dsa.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

import org.shashwatksingh.dsa.helpers.EdgeWithWeights;

public class BFS {
    public void create(ArrayList<EdgeWithWeights>[] graph) {
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

    public void execute(ArrayList<EdgeWithWeights>[] graph, int V) {
        boolean[] visited = new boolean[V];
        Queue<Integer> pq = new ArrayDeque<>();
        pq.add(0);

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            if(!visited[curr]) {
                System.out.print(curr+" ");
                visited[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    EdgeWithWeights neighbor = graph[curr].get(i);
                    pq.add(neighbor.getDest());
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BFS bfs = new BFS();
        int V = 7;
        ArrayList<EdgeWithWeights>[] graph = new ArrayList[V];
        bfs.create(graph);
        bfs.execute(graph, V);
    }
}
