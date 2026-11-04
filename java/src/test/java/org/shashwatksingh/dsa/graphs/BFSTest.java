package org.shashwatksingh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.StringJoiner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.EdgeWithWeights;

@DisplayName("Breadth-First Search Tests")
class BFSTest {

    private BFS bfs;

    @BeforeEach
    void setUp() {
        bfs = new BFS();
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

    private String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString().trim();
    }

    private String run(ArrayList<EdgeWithWeights>[] graph, int v) {
        return capture(() -> bfs.execute(graph, v));
    }

    private String dests(ArrayList<EdgeWithWeights> list) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (EdgeWithWeights e : list) {
            joiner.add(String.valueOf(e.getDest()));
        }
        return joiner.toString();
    }

    // ═══════════════════════════════════════════════════════════
    //  create()  — builds the 7-vertex sample graph, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("create() — builds the 7-vertex sample graph")
    class CreateTests {

        @Test
        @DisplayName("Scenario: V=7 → every slot holds a non-null list (no NPE)")
        void testAllSlotsInitialised() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
            for (int i = 0; i < 7; i++) {
                assertEquals(true, graph[i] != null);
            }
        }

        @Test
        @DisplayName("Scenario: V=7 → neighbours of 0,1,2 are [1,2], [0,3], [0,4]")
        void testAdjacencyOfLowVertices() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
            assertEquals("[1,2]", dests(graph[0]));
            assertEquals("[0,3]", dests(graph[1]));
            assertEquals("[0,4]", dests(graph[2]));
        }

        @Test
        @DisplayName("Scenario: V=7 → neighbours of 3,4,5 are [1,4,5], [2,3,5], [3,4,6]")
        void testAdjacencyOfMiddleVertices() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
            assertEquals("[1,4,5]", dests(graph[3]));
            assertEquals("[2,3,5]", dests(graph[4]));
            assertEquals("[3,4,6]", dests(graph[5]));
        }

        @Test
        @DisplayName("Scenario: V=7 → vertex 6 has exactly one neighbour, 5 (leaf)")
        void testLeafVertex() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
            assertEquals("[5]", dests(graph[6]));
        }

        @Test
        @DisplayName("Scenario: V=7 → every edge in graph[i] has src == i")
        void testEdgeSourcesMatchBucket() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
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
            bfs.create(graph);
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
            bfs.create(graph);
            for (int i = 0; i < 7; i++) {
                for (EdgeWithWeights e : graph[i]) {
                    assertEquals(1, e.getWeight());
                }
            }
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  execute()  — breadth-first traversal from vertex 0, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("execute() — breadth-first traversal from vertex 0")
    class ExecuteTests {

        @Test
        @DisplayName("Scenario: sample graph from create() → 0 1 2 3 4 5 6")
        void testSampleGraph() {
            ArrayList<EdgeWithWeights>[] graph = new ArrayList[7];
            bfs.create(graph);
            assertEquals("0 1 2 3 4 5 6", run(graph, 7));
        }

        @Test
        @DisplayName("Scenario: single vertex, no edges → 0")
        void testSingleVertex() {
            assertEquals("0", run(emptyGraph(1), 1));
        }

        @Test
        @DisplayName("Scenario: two vertices 0-1 → 0 1")
        void testTwoVertices() {
            assertEquals("0 1", run(undirected(2, new int[][] { { 0, 1 } }), 2));
        }

        @Test
        @DisplayName("Scenario: 3 vertices, no edges → 0 (nothing reachable)")
        void testNoEdges() {
            assertEquals("0", run(emptyGraph(3), 3));
        }

        @Test
        @DisplayName("Scenario: disconnected 0-1 and 2-3 → 0 1 (other component unvisited)")
        void testDisconnectedComponents() {
            int[][] edges = { { 0, 1 }, { 2, 3 } };
            assertEquals("0 1", run(undirected(4, edges), 4));
        }

        @Test
        @DisplayName("Scenario: only directed edge 1→0 → 0 (cannot walk against direction)")
        void testDirectedEdgeIntoStart() {
            assertEquals("0", run(directed(2, new int[][] { { 1, 0 } }), 2));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4 → 0 1 2 3 4")
        void testChain() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
            assertEquals("0 1 2 3 4", run(undirected(5, edges), 5));
        }

        @Test
        @DisplayName("Scenario: chain labelled 0-4-3-2-1 → 0 4 3 2 1 (follows edges, not label order)")
        void testChainWithReversedLabels() {
            int[][] edges = { { 0, 4 }, { 4, 3 }, { 3, 2 }, { 2, 1 } };
            assertEquals("0 4 3 2 1", run(undirected(5, edges), 5));
        }

        @Test
        @DisplayName("Scenario: star, neighbours of 0 added as 3,1,2 → 0 3 1 2 (adjacency order)")
        void testStarFollowsAdjacencyOrder() {
            int[][] edges = { { 0, 3 }, { 0, 1 }, { 0, 2 } };
            assertEquals("0 3 1 2", run(undirected(4, edges), 4));
        }

        @Test
        @DisplayName("Scenario: edges 0-3, 0-1, 3-2, 1-4 → 0 3 1 2 4 (level by level)")
        void testLevelOrder() {
            int[][] edges = { { 0, 3 }, { 0, 1 }, { 3, 2 }, { 1, 4 } };
            assertEquals("0 3 1 2 4", run(undirected(5, edges), 5));
        }

        @Test
        @DisplayName("Scenario: deep branch 0-1-2-3 plus shallow 0-4 → 0 1 4 2 3 (not depth-first)")
        void testNotDepthFirst() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 4 } };
            assertEquals("0 1 4 2 3", run(undirected(5, edges), 5));
        }

        @Test
        @DisplayName("Scenario: triangle 0-1-2 → 0 1 2 (cycle, each vertex printed once)")
        void testCycle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
            assertEquals("0 1 2", run(undirected(3, edges), 3));
        }

        @Test
        @DisplayName("Scenario: self-loop on 0 and duplicate 0-1 edges → 0 1")
        void testSelfLoopAndDuplicateEdges() {
            int[][] edges = { { 0, 0 }, { 0, 1 }, { 0, 1 } };
            assertEquals("0 1", run(undirected(2, edges), 2));
        }

        @Test
        @DisplayName("Scenario: diamond 0-1, 0-2, 1-3, 2-3 → 0 1 2 3 (3 reached once)")
        void testDiamond() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };
            assertEquals("0 1 2 3", run(undirected(4, edges), 4));
        }

        @Test
        @DisplayName("Scenario: chain with 10,000 vertices → 0 1 2 ... 9999")
        void testLargeChain() {
            int n = 10_000;
            int[][] edges = new int[n - 1][];
            StringJoiner expected = new StringJoiner(" ");
            expected.add("0");
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { i - 1, i };
                expected.add(String.valueOf(i));
            }
            assertEquals(expected.toString(), run(undirected(n, edges), n));
        }

        @Test
        @DisplayName("Scenario: star with 10,000 leaves added in descending order → 0 9999 9998 ... 1")
        void testLargeStarDescending() {
            int n = 10_001;
            int[][] edges = new int[n - 1][];
            StringJoiner expected = new StringJoiner(" ");
            expected.add("0");
            for (int i = n - 1; i >= 1; i--) {
                edges[n - 1 - i] = new int[] { 0, i };
                expected.add(String.valueOf(i));
            }
            assertEquals(expected.toString(), run(undirected(n, edges), n));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  main()  — builds the sample graph and traverses it
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("main() — builds sample graph and prints traversal")
    class MainTests {

        @Test
        @DisplayName("Scenario: no args → prints 0 1 2 3 4 5 6")
        void testMainOutput() {
            assertEquals("0 1 2 3 4 5 6", capture(() -> BFS.main(new String[0])));
        }
    }
}
