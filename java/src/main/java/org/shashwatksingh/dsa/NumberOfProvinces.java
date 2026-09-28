package org.shashwatksingh.dsa;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfProvinces {
    public int solutionDfs(int[][] isConnected) {
        int m = isConnected.length, n = isConnected[0].length;
        boolean[] visited = new boolean[m];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i]) {
                    dfs(isConnected, visited, i);
                    count++;
                }
            }

        }
        return count;
    }

    public void dfs(int[][] isConnected, boolean[] visited, int node) {
        visited[node] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (isConnected[node][i] == 1 && !visited[i]) {
                dfs(isConnected, visited, i);
            }
        }
    }

    public int solutionBfs(int[][] isConnected) {
        int m = isConnected.length;
        boolean[] visited = new boolean[m];
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (!visited[i]) {
                count++;
                bfs(isConnected, i, visited);
            }
        }
        return count;
    }

    public void bfs(int[][] isConnected, int node, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        visited[node] = true;

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[temp][i] == 1 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
