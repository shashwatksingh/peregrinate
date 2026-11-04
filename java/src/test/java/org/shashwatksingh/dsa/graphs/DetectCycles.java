package org.shashwatksingh.dsa.graphs;

import java.util.List;

import org.shashwatksingh.dsa.helpers.Edge;

public class DetectCycles {

    public boolean detectCycles(List<Edge>[] graph, int curr, int parent, boolean[] visited) {
        visited[curr] = true;

        for (int i = 0; i < graph[curr].size(); i++) {
            int next = graph[curr].get(i).getDest();
            if(next == parent) continue;
            if(visited[next]) return true;
            if(detectCycles(graph, next, curr, visited)) return true;
        }

        return false;
    }

}
