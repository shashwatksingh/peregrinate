package org.shashwatksingh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.EdgeWithWeights;

@DisplayName("Depth-First Search Tests")
class DFSTest {

    private DFS dfs;

    @BeforeEach
    void setUp() {
        dfs = new DFS();
    }

    // ── helpers ────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private ArrayList<EdgeWithWeights>[] emptyGraph(int v) {
        ArrayList<EdgeWithWeights>[] graph = new ArrayList[v];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    /** Each {a, b} adds a->b then b->a, in the order given. */
    private ArrayList<EdgeWithWeights>[] undirected(int v, int[][] edges) {
        ArrayList<EdgeWithWeights>[] graph = emptyGraph(v);
        for (int[] e : edges) {
            graph[e[0]].add(new EdgeWithWeights(e[0], e[1], 1));
            graph[e[1]].add(new EdgeWithWeights(e[1], e[0], 1));
        }
        return graph;
    }

    /** Each {a, b} adds only a->b. */
    private ArrayList<EdgeWithWeights>[] directed(int v, int[][] edges) {
        ArrayList<EdgeWithWeights>[] graph = emptyGraph(v);
        for (int[] e : edges) {
            graph[e[0]].add(new EdgeWithWeights(e[0], e[1], 1));
        }
        return graph;
    }

    /** Runs execute() from start and returns the visited vertices, e.g. "[0, 1, 3]". */
    private String reachable(ArrayList<EdgeWithWeights>[] graph, int v, int start) {
        boolean[] visited = new boolean[v];
        dfs.execute(graph, start, visited);
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < v; i++) {
            if (visited[i]) {
                joiner.add(String.valueOf(i));
            }
        }
        return joiner.toString();
    }

    private String dests(ArrayList<EdgeWithWeights> list) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (EdgeWithWeights e : list) {
            joiner.add(String.valueOf(e.getDest()));
        }
        return joiner.toString();
    }

    private String range(int from, int toInclusive) {
        int[] values = new int[toInclusive - from + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = from + i;
        }
        return Arrays.toString(values);
    }

    // ═══════════════════════════════════════════════════════════
    //  createGraph()  — builds the 7-vertex sample graph, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("createGraph() — builds the 7-vertex sample graph")
    class CreateGraphTests {

        @Test
        @DisplayName("Scenario: V=7 → every slot holds a non-null list (no NPE)")
        void testAllSlotsInitialised() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            for (int i = 0; i < 7; i++) {
                assertEquals(true, graph[i] != null);
            }
        }

        @Test
        @DisplayName("Scenario: V=7 → neighbours of 0,1,2 are [1,2], [0,3], [0,4]")
        void testAdjacencyOfLowVertices() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            assertEquals("[1,2]", dests(graph[0]));
            assertEquals("[0,3]", dests(graph[1]));
            assertEquals("[0,4]", dests(graph[2]));
        }

        @Test
        @DisplayName("Scenario: V=7 → neighbours of 3,4,5 are [1,4,5], [2,3,5], [3,4,6]")
        void testAdjacencyOfMiddleVertices() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            assertEquals("[1,4,5]", dests(graph[3]));
            assertEquals("[2,3,5]", dests(graph[4]));
            assertEquals("[3,4,6]", dests(graph[5]));
        }

        @Test
        @DisplayName("Scenario: V=7 → vertex 6 has exactly one neighbour, 5 (leaf)")
        void testLeafVertex() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            assertEquals("[5]", dests(graph[6]));
        }

        @Test
        @DisplayName("Scenario: V=7 → every edge in graph[i] has src == i")
        void testEdgeSourcesMatchBucket() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            for (int i = 0; i < 7; i++) {
                for (EdgeWithWeights e : graph[i]) {
                    assertEquals(i, e.getSrc());
                }
            }
        }

        @Test
        @DisplayName("Scenario: V=7 → total edge count is 16 (8 undirected edges, both directions)")
        void testTotalEdgeCount() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            int total = 0;
            for (int i = 0; i < 7; i++) {
                total += graph[i].size();
            }
            assertEquals(16, total);
        }

        @Test
        @DisplayName("Scenario: V=7 → every edge has weight 1")
        void testUniformWeights() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            for (int i = 0; i < 7; i++) {
                for (EdgeWithWeights e : graph[i]) {
                    assertEquals(1, e.getWeight());
                }
            }
        }

        @Test
        @DisplayName("Scenario: V=7 → every edge has a reverse edge (graph is undirected)")
        void testSymmetric() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            for (int i = 0; i < 7; i++) {
                for (EdgeWithWeights e : graph[i]) {
                    boolean found = false;
                    for (EdgeWithWeights back : graph[e.getDest()]) {
                        if (back.getDest() == i) {
                            found = true;
                        }
                    }
                    assertEquals(true, found);
                }
            }
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  execute()  — marks every vertex reachable from curr, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("execute() — marks every vertex reachable from the start")
    class ExecuteTests {

        @Test
        @DisplayName("Scenario: sample graph from createGraph(), start 0 → all 7 vertices visited")
        void testSampleGraphFromZero() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            assertEquals("[0, 1, 2, 3, 4, 5, 6]", reachable(graph, 7, 0));
        }

        @Test
        @DisplayName("Scenario: sample graph from createGraph(), start 6 → all 7 vertices visited")
        void testSampleGraphFromLeaf() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            dfs.createGraph(graph);
            assertEquals("[0, 1, 2, 3, 4, 5, 6]", reachable(graph, 7, 6));
        }

        @Test
        @DisplayName("Scenario: single vertex, no edges → [0]")
        void testSingleVertex() {
            assertEquals("[0]", reachable(emptyGraph(1), 1, 0));
        }

        @Test
        @DisplayName("Scenario: 3 vertices, no edges, start 1 → [1] (only the start)")
        void testNoEdges() {
            assertEquals("[1]", reachable(emptyGraph(3), 3, 1));
        }

        @Test
        @DisplayName("Scenario: two vertices 0-1, start 0 → [0, 1]")
        void testTwoVertices() {
            assertEquals("[0, 1]", reachable(undirected(2, new int[][] { { 0, 1 } }), 2, 0));
        }

        @Test
        @DisplayName("Scenario: disconnected 0-1 and 2-3, start 0 → [0, 1] (other component unvisited)")
        void testDisconnectedFromFirstComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 } };
            assertEquals("[0, 1]", reachable(undirected(4, edges), 4, 0));
        }

        @Test
        @DisplayName("Scenario: disconnected 0-1 and 2-3, start 2 → [2, 3]")
        void testDisconnectedFromSecondComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 } };
            assertEquals("[2, 3]", reachable(undirected(4, edges), 4, 2));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4, start 0 → [0, 1, 2, 3, 4]")
        void testChainFromEnd() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
            assertEquals("[0, 1, 2, 3, 4]", reachable(undirected(5, edges), 5, 0));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4, start 2 (middle) → [0, 1, 2, 3, 4]")
        void testChainFromMiddle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
            assertEquals("[0, 1, 2, 3, 4]", reachable(undirected(5, edges), 5, 2));
        }

        @Test
        @DisplayName("Scenario: chain labelled 0-4-3-2-1, start 0 → [0, 1, 2, 3, 4] (follows edges, not label order)")
        void testChainWithReversedLabels() {
            int[][] edges = { { 0, 4 }, { 4, 3 }, { 3, 2 }, { 2, 1 } };
            assertEquals("[0, 1, 2, 3, 4]", reachable(undirected(5, edges), 5, 0));
        }

        @Test
        @DisplayName("Scenario: star centred on 0 with leaves 1,2,3, start 0 → [0, 1, 2, 3]")
        void testStar() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
            assertEquals("[0, 1, 2, 3]", reachable(undirected(4, edges), 4, 0));
        }

        @Test
        @DisplayName("Scenario: star centred on 0 with leaves 1,2,3, start 3 (leaf) → [0, 1, 2, 3]")
        void testStarFromLeaf() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
            assertEquals("[0, 1, 2, 3]", reachable(undirected(4, edges), 4, 3));
        }

        @Test
        @DisplayName("Scenario: directed edge 1→0 only, start 0 → [0] (cannot walk against direction)")
        void testDirectedEdgeIntoStart() {
            assertEquals("[0]", reachable(directed(2, new int[][] { { 1, 0 } }), 2, 0));
        }

        @Test
        @DisplayName("Scenario: directed edge 1→0 only, start 1 → [0, 1]")
        void testDirectedEdgeOutOfStart() {
            assertEquals("[0, 1]", reachable(directed(2, new int[][] { { 1, 0 } }), 2, 1));
        }

        @Test
        @DisplayName("Scenario: directed path 2→0→1, start 2 → [0, 1, 2] (vertex index != edge position)")
        void testDirectedPathWithNonSequentialLabels() {
            int[][] edges = { { 2, 0 }, { 0, 1 } };
            assertEquals("[0, 1, 2]", reachable(directed(3, edges), 3, 2));
        }

        @Test
        @DisplayName("Scenario: triangle 0-1-2, start 0 → [0, 1, 2] (cycle terminates)")
        void testCycle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
            assertEquals("[0, 1, 2]", reachable(undirected(3, edges), 3, 0));
        }

        @Test
        @DisplayName("Scenario: self-loop on 0 and duplicate 0-1 edges, start 0 → [0, 1]")
        void testSelfLoopAndDuplicateEdges() {
            int[][] edges = { { 0, 0 }, { 0, 1 }, { 0, 1 } };
            assertEquals("[0, 1]", reachable(undirected(2, edges), 2, 0));
        }

        @Test
        @DisplayName("Scenario: diamond 0-1, 0-2, 1-3, 2-3, start 0 → [0, 1, 2, 3]")
        void testDiamond() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };
            assertEquals("[0, 1, 2, 3]", reachable(undirected(4, edges), 4, 0));
        }

        @Test
        @DisplayName("Scenario: start already marked visited → visited array unchanged")
        void testStartAlreadyVisited() {
            ArrayList<EdgeWithWeights>[] graph = undirected(3, new int[][] { { 0, 1 }, { 1, 2 } });
            boolean[] visited = new boolean[3];
            visited[0] = true;
            dfs.execute(graph, 0, visited);
            assertEquals("[true, false, false]", Arrays.toString(visited));
        }

        @Test
        @DisplayName("Scenario: pre-visited vertex 1 on chain 0-1-2, start 0 → [0, 1] (blocked at 1, 2 unreached)")
        void testPreVisitedVertexBlocksTraversal() {
            ArrayList<EdgeWithWeights>[] graph = undirected(3, new int[][] { { 0, 1 }, { 1, 2 } });
            boolean[] visited = new boolean[3];
            visited[1] = true;
            dfs.execute(graph, 0, visited);
            assertEquals("[true, true, false]", Arrays.toString(visited));
        }

        @Test
        @DisplayName("Scenario: chain with 1,000 vertices, start 0 → all visited")
        void testLongChain() {
            int n = 1_000;
            int[][] edges = new int[n - 1][];
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { i - 1, i };
            }
            assertEquals(range(0, n - 1), reachable(undirected(n, edges), n, 0));
        }

        @Test
        @DisplayName("Scenario: star with 10,000 leaves, start 0 → all visited")
        void testLargeStar() {
            int n = 10_001;
            int[][] edges = new int[n - 1][];
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { 0, i };
            }
            assertEquals(range(0, n - 1), reachable(undirected(n, edges), n, 0));
        }
    }
}
