package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.Map;

/*
1791. Find Center of Star Graph
There is an undirected star graph consisting of n nodes labeled from 1 to n. 
A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.

Example 1:
Input: edges = [[1,2],[2,3],[4,2]]
Output: 2
Explanation: As shown in the figure above, node 2 is connected to every other node, so 2 is the center.

Example 2:
Input: edges = [[1,2],[5,1],[1,3],[1,4]]
Output: 1
 

Constraints:
3 <= n <= 105
edges.length == n - 1
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
The given edges represent a valid star graph.
*/

public class FindCenterOfStarGraph {

    public static void main(String[] args) {
        FindCenterOfStarGraph fcg = new FindCenterOfStarGraph();
        // System.out.println(fcg.findCenter(new int[][]{{1,2},{5,1},{1,3},{1,4}}));
        System.out.println(fcg.findCenter(new int[][]{{1,2},{2,3},{4,2}}));
        System.out.println(fcg.findCenterAdjacencyListSolution(new int[][]{{1,2},{2,3},{4,2}}));
    }

    public int findCenterAdjacencyListSolution(int[][] edges) {
        Map<Integer, Integer> degree = new HashMap<>();
        for (int[] edge : edges) {
            degree.put(edge[0], degree.getOrDefault(edge[0], 0) + 1);
            degree.put(edge[1], degree.getOrDefault(edge[1], 0)+1);
        }

        for (int node : degree.keySet()) {
            if (degree.get(node) == edges.length) {
                return node;
            }
        }

        return -1;
    }

    public int findCenter(int[][] edges) {
        // how to create an adjacency list
        // how to create a adjacency matrix
        // The edge list is given
        // how to create incidence matrix

        int n = edges.length;
        int[] edgeCount = new int[n+2];

        for (int[] relation : edges) {
            edgeCount[relation[0]]++;
            edgeCount[relation[1]]++;
        }

        for (int i = 1; i < n+2; i++) {
            if(edgeCount[i]==n) return i;
        }
        return -1;
    }

    public int findCenterConstantTimeSolutino(int[][] edges) {
        int[] firstEdge = edges[0];
        int[] secondEdge = edges[1];

        return (firstEdge[0] == secondEdge[0] || firstEdge[0] == secondEdge[1]) ? firstEdge[0] : firstEdge[1];
    }
}
