package org.shashwatksingh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.Edge;

@DisplayName("Print All Path (DFS traversal) Tests")
class PrintAllPathTest {

    // ── helpers ────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private List<Edge>[] emptyGraph(int v) {
        List<Edge>[] graph = new List[v];
        for (int i = 0; i < v; i++) {
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    /** Each {a, b} adds a->b then b->a, in the order given. */
    private List<Edge>[] undirected(int v, int[][] edges) {
        List<Edge>[] graph = emptyGraph(v);
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[0], e[1]));
            graph[e[1]].add(new Edge(e[1], e[0]));
        }
        return graph;
    }

    /** Each {a, b} adds only a->b. */
    private List<Edge>[] directed(int v, int[][] edges) {
        List<Edge>[] graph = emptyGraph(v);
        for (int[] e : edges) {
            graph[e[0]].add(new Edge(e[0], e[1]));
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
        return buffer.toString();
    }

    /** Pulls the vertex numbers out of the printed text, ignoring arrows/spacing: "0 -->1 -->" -> "0 1". */
    private String vertices(String output) {
        StringJoiner joiner = new StringJoiner(" ");
        for (String token : output.split("[^0-9]+")) {
            if (!token.isEmpty()) {
                joiner.add(token);
            }
        }
        return joiner.toString();
    }

    private String order(List<Edge>[] graph, int start) {
        PrintAllPath printAllPath = new PrintAllPath(graph);
        return vertices(capture(() -> printAllPath.dfs(start, new boolean[graph.length])));
    }

    /** The 8-vertex sample graph: a 4-cycle 0-1-2-3 with a branch 3-4 that splits into 5 and 6-7. */
    private List<Edge>[] sample() {
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 4, 6 }, { 6, 7 } };
        return undirected(8, edges);
    }

    private String dests(List<Edge> list) {
        StringJoiner joiner = new StringJoiner(",", "[", "]");
        for (Edge e : list) {
            joiner.add(String.valueOf(e.getDest()));
        }
        return joiner.toString();
    }

    // ═══════════════════════════════════════════════════════════
    //  dfs()  — prints vertices in depth-first order, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("dfs() — prints vertices in depth-first order")
    class DfsTests {

        @Test
        @DisplayName("Scenario: sample graph from create(), start 0 → 0 1 2 3 4 5 6 7")
        void testSampleGraphFromZero() {
            List<Edge>[] graph = sample();
            assertEquals("0 1 2 3 4 5 6 7", order(graph, 0));
        }

        @Test
        @DisplayName("Scenario: sample graph from create(), start 7 → 7 6 4 3 0 1 2 5")
        void testSampleGraphFromLeaf() {
            List<Edge>[] graph = sample();
            assertEquals("7 6 4 3 0 1 2 5", order(graph, 7));
        }

        @Test
        @DisplayName("Scenario: single vertex, no edges → 0")
        void testSingleVertex() {
            assertEquals("0", order(emptyGraph(1), 0));
        }

        @Test
        @DisplayName("Scenario: 3 vertices, no edges, start 1 → 1 (only the start)")
        void testNoEdges() {
            assertEquals("1", order(emptyGraph(3), 1));
        }

        @Test
        @DisplayName("Scenario: two vertices 0-1, start 0 → 0 1")
        void testTwoVertices() {
            assertEquals("0 1", order(undirected(2, new int[][] { { 0, 1 } }), 0));
        }

        @Test
        @DisplayName("Scenario: disconnected 0-1 and 2-3, start 0 → 0 1 (other component unvisited)")
        void testDisconnectedFromFirstComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 } };
            assertEquals("0 1", order(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: disconnected 0-1 and 2-3, start 2 → 2 3")
        void testDisconnectedFromSecondComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 } };
            assertEquals("2 3", order(undirected(4, edges), 2));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4, start 0 → 0 1 2 3 4")
        void testChainFromEnd() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
            assertEquals("0 1 2 3 4", order(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4, start 2 (middle) → 2 1 0 3 4 (first neighbour fully explored first)")
        void testChainFromMiddle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 } };
            assertEquals("2 1 0 3 4", order(undirected(5, edges), 2));
        }

        @Test
        @DisplayName("Scenario: chain labelled 0-4-3-2-1, start 0 → 0 4 3 2 1 (follows edges, not label order)")
        void testChainWithReversedLabels() {
            int[][] edges = { { 0, 4 }, { 4, 3 }, { 3, 2 }, { 2, 1 } };
            assertEquals("0 4 3 2 1", order(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: star, neighbours of 0 added as 3,1,2 → 0 3 1 2 (adjacency order)")
        void testStarFollowsAdjacencyOrder() {
            int[][] edges = { { 0, 3 }, { 0, 1 }, { 0, 2 } };
            assertEquals("0 3 1 2", order(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: deep branch 0-1-2-3 plus shallow 0-4 → 0 1 2 3 4 (depth before breadth)")
        void testDepthBeforeBreadth() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 0, 4 } };
            assertEquals("0 1 2 3 4", order(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: diamond 0-1, 0-2, 1-3, 2-3 → 0 1 3 2 (3 reached via 1, printed once)")
        void testDiamond() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };
            assertEquals("0 1 3 2", order(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: triangle 0-1-2 → 0 1 2 (cycle, each vertex printed once)")
        void testCycle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
            assertEquals("0 1 2", order(undirected(3, edges), 0));
        }

        @Test
        @DisplayName("Scenario: complete graph on 5 vertices → 0 1 2 3 4 (each printed once)")
        void testCompleteGraph() {
            List<Edge>[] graph = emptyGraph(5);
            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    if (a != b) {
                        graph[a].add(new Edge(a, b));
                    }
                }
            }
            assertEquals("0 1 2 3 4", order(graph, 0));
        }

        @Test
        @DisplayName("Scenario: self-loop on 0 and duplicate 0-1 edges → 0 1")
        void testSelfLoopAndDuplicateEdges() {
            int[][] edges = { { 0, 0 }, { 0, 1 }, { 0, 1 } };
            assertEquals("0 1", order(undirected(2, edges), 0));
        }

        @Test
        @DisplayName("Scenario: directed edge 1→0 only, start 0 → 0 (cannot walk against direction)")
        void testDirectedEdgeIntoStart() {
            assertEquals("0", order(directed(2, new int[][] { { 1, 0 } }), 0));
        }

        @Test
        @DisplayName("Scenario: directed edge 1→0 only, start 1 → 1 0")
        void testDirectedEdgeOutOfStart() {
            assertEquals("1 0", order(directed(2, new int[][] { { 1, 0 } }), 1));
        }

        @Test
        @DisplayName("Scenario: directed path 2→0→1, start 2 → 2 0 1 (vertex number != adjacency index)")
        void testDirectedPathWithNonSequentialLabels() {
            int[][] edges = { { 2, 0 }, { 0, 1 } };
            assertEquals("2 0 1", order(directed(3, edges), 2));
        }

        @Test
        @DisplayName("Scenario: start already marked visited → prints nothing")
        void testStartAlreadyVisited() {
            List<Edge>[] graph = undirected(3, new int[][] { { 0, 1 }, { 1, 2 } });
            boolean[] visited = new boolean[3];
            visited[0] = true;
            PrintAllPath printAllPath = new PrintAllPath(graph);
            assertEquals("", vertices(capture(() -> printAllPath.dfs(0, visited))));
        }

        @Test
        @DisplayName("Scenario: pre-visited vertex 1 on chain 0-1-2, start 0 → 0 (blocked at 1, 2 unreached)")
        void testPreVisitedVertexBlocksTraversal() {
            List<Edge>[] graph = undirected(3, new int[][] { { 0, 1 }, { 1, 2 } });
            boolean[] visited = new boolean[3];
            visited[1] = true;
            PrintAllPath printAllPath = new PrintAllPath(graph);
            assertEquals("0", vertices(capture(() -> printAllPath.dfs(0, visited))));
        }

        @Test
        @DisplayName("Scenario: after traversal, visited[] is true for exactly the reachable vertices")
        void testVisitedArrayAfterTraversal() {
            List<Edge>[] graph = undirected(4, new int[][] { { 0, 1 }, { 2, 3 } });
            boolean[] visited = new boolean[4];
            PrintAllPath printAllPath = new PrintAllPath(graph);
            capture(() -> printAllPath.dfs(0, visited));
            assertEquals("[true, true, false, false]", Arrays.toString(visited));
        }

        @Test
        @DisplayName("Scenario: same instance traversed twice with fresh visited[] → same order both times")
        void testInstanceReusable() {
            PrintAllPath printAllPath = new PrintAllPath(sample());
            String first = vertices(capture(() -> printAllPath.dfs(0, new boolean[8])));
            String second = vertices(capture(() -> printAllPath.dfs(0, new boolean[8])));
            assertEquals("0 1 2 3 4 5 6 7", first);
            assertEquals(first, second);
        }

        @Test
        @DisplayName("Scenario: complete binary tree with 15 vertices → 0 1 3 7 8 4 9 10 2 5 11 12 6 13 14")
        void testBinaryTree() {
            int[][] edges = new int[14][];
            int k = 0;
            for (int i = 0; i < 7; i++) {
                edges[k++] = new int[] { i, 2 * i + 1 };
                edges[k++] = new int[] { i, 2 * i + 2 };
            }
            assertEquals("0 1 3 7 8 4 9 10 2 5 11 12 6 13 14", order(undirected(15, edges), 0));
        }

        @Test
        @DisplayName("Scenario: sample graph, start 4 (junction) → 4 3 0 1 2 5 6 7")
        void testSampleFromJunction() {
            assertEquals("4 3 0 1 2 5 6 7", order(sample(), 4));
        }

        @Test
        @DisplayName("Scenario: ring of 6 vertices, start 3 → 3 2 1 0 5 4 (goes one way round)")
        void testRing() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 0 } };
            assertEquals("3 2 1 0 5 4", order(undirected(6, edges), 3));
        }

        @Test
        @DisplayName("Scenario: chain with 1,000 vertices → 0 1 2 ... 999")
        void testLongChain() {
            int n = 1_000;
            int[][] edges = new int[n - 1][];
            StringJoiner expected = new StringJoiner(" ");
            expected.add("0");
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { i - 1, i };
                expected.add(String.valueOf(i));
            }
            assertEquals(expected.toString(), order(undirected(n, edges), 0));
        }

        @Test
        @DisplayName("Scenario: star with 10,000 leaves added in descending order → 0 10000 9999 ... 1")
        void testLargeStarDescending() {
            int n = 10_001;
            int[][] edges = new int[n - 1][];
            StringJoiner expected = new StringJoiner(" ");
            expected.add("0");
            for (int i = n - 1; i >= 1; i--) {
                edges[n - 1 - i] = new int[] { 0, i };
                expected.add(String.valueOf(i));
            }
            assertEquals(expected.toString(), order(undirected(n, edges), 0));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  main()  — builds the sample graph and traverses it from 0
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("main() — builds sample graph and prints traversal")
    class MainTests {

        @Test
        @DisplayName("Scenario: no args → prints 0 1 2 3 4 5 6 7")
        void testMainOutput() {
            assertEquals("0 1 2 3 4 5 6 7", vertices(capture(() -> PrintAllPath.main(new String[0]))));
        }
    }
}
