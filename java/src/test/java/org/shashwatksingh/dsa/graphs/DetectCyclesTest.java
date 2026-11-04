package org.shashwatksingh.dsa.graphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.shashwatksingh.dsa.helpers.Edge;

@DisplayName("Detect Cycles (undirected graph) Tests")
class DetectCyclesTest {

    private DetectCycles detector;

    @BeforeEach
    void setUp() {
        detector = new DetectCycles();
    }

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

    /** Runs the detector from {@code start}, which has no parent (-1), with a fresh visited[]. */
    private boolean detect(List<Edge>[] graph, int start) {
        return detector.detectCycles(graph, start, -1, new boolean[graph.length]);
    }

    private int[][] path(int n) {
        int[][] edges = new int[n - 1][];
        for (int i = 1; i < n; i++) {
            edges[i - 1] = new int[] { i - 1, i };
        }
        return edges;
    }

    // ═══════════════════════════════════════════════════════════
    //  detectCycles()  — cycle reachable from a start vertex, O(V + E)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("detectCycles() — is a cycle reachable from the start vertex?")
    class DetectCyclesTests {

        // ── acyclic graphs → false ─────────────────────────────

        @Test
        @DisplayName("Scenario: single vertex, no edges → false")
        void testSingleVertex() {
            assertEquals(false, detect(emptyGraph(1), 0));
        }

        @Test
        @DisplayName("Scenario: 3 vertices, no edges → false")
        void testNoEdges() {
            assertEquals(false, detect(emptyGraph(3), 0));
        }

        @Test
        @DisplayName("Scenario: two vertices 0-1 → false (the edge back to the parent is not a cycle)")
        void testSingleEdge() {
            assertEquals(false, detect(undirected(2, new int[][] { { 0, 1 } }), 0));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4 → false")
        void testChain() {
            assertEquals(false, detect(undirected(5, path(5)), 0));
        }

        @Test
        @DisplayName("Scenario: chain 0-1-2-3-4, start 2 (middle) → false")
        void testChainFromMiddle() {
            assertEquals(false, detect(undirected(5, path(5)), 2));
        }

        @Test
        @DisplayName("Scenario: chain labelled 0-4-3-2-1 → false (follows edges, not label order)")
        void testChainWithReversedLabels() {
            int[][] edges = { { 0, 4 }, { 4, 3 }, { 3, 2 }, { 2, 1 } };
            assertEquals(false, detect(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: star centred on 0 with leaves 1,2,3 → false")
        void testStar() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
            assertEquals(false, detect(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: star centred on 0, start at leaf 3 → false")
        void testStarFromLeaf() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
            assertEquals(false, detect(undirected(4, edges), 3));
        }

        @Test
        @DisplayName("Scenario: complete binary tree with 15 vertices → false")
        void testBinaryTree() {
            int[][] edges = new int[14][];
            int k = 0;
            for (int i = 0; i < 7; i++) {
                edges[k++] = new int[] { i, 2 * i + 1 };
                edges[k++] = new int[] { i, 2 * i + 2 };
            }
            assertEquals(false, detect(undirected(15, edges), 0));
        }

        @Test
        @DisplayName("Scenario: complete binary tree with 15 vertices, start at leaf 14 → false")
        void testBinaryTreeFromLeaf() {
            int[][] edges = new int[14][];
            int k = 0;
            for (int i = 0; i < 7; i++) {
                edges[k++] = new int[] { i, 2 * i + 1 };
                edges[k++] = new int[] { i, 2 * i + 2 };
            }
            assertEquals(false, detect(undirected(15, edges), 14));
        }

        @Test
        @DisplayName("Scenario: tree 0-1, 0-2, 1-3, 1-4, 2-5 → false (branches explored after the first)")
        void testTreeWithSeveralBranches() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 1, 4 }, { 2, 5 } };
            assertEquals(false, detect(undirected(6, edges), 0));
        }

        @Test
        @DisplayName("Scenario: acyclic graph fully traversed → visited[] is all true")
        void testVisitedArrayOnTree() {
            List<Edge>[] graph = undirected(5, new int[][] { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 2, 4 } });
            boolean[] visited = new boolean[5];
            detector.detectCycles(graph, 0, -1, visited);
            assertEquals("[true, true, true, true, true]", Arrays.toString(visited));
        }

        // ── graphs with a cycle → true ─────────────────────────

        @Test
        @DisplayName("Scenario: triangle 0-1-2 → true")
        void testTriangle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 } };
            assertEquals(true, detect(undirected(3, edges), 0));
        }

        @Test
        @DisplayName("Scenario: square 0-1-2-3-0 → true")
        void testSquare() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } };
            assertEquals(true, detect(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: ring of 6 vertices, start 3 → true")
        void testRingFromMiddle() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 0 } };
            assertEquals(true, detect(undirected(6, edges), 3));
        }

        @Test
        @DisplayName("Scenario: diamond 0-1, 0-2, 1-3, 2-3 → true")
        void testDiamond() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 } };
            assertEquals(true, detect(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: complete graph on 4 vertices → true")
        void testCompleteGraph() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 2 }, { 1, 3 }, { 2, 3 } };
            assertEquals(true, detect(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: triangle 1-2-3 hanging off vertex 0 (start is not on the cycle) → true")
        void testCycleNotThroughStart() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 1 } };
            assertEquals(true, detect(undirected(4, edges), 0));
        }

        @Test
        @DisplayName("Scenario: path 0-1-2-3 ending in triangle 3-4-5 → true (cycle deep in the graph)")
        void testCycleAtEndOfLongPath() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 3 } };
            assertEquals(true, detect(undirected(6, edges), 0));
        }

        @Test
        @DisplayName("Scenario: first branch of 0 is acyclic, second branch holds a triangle → true")
        void testCycleInLaterBranch() {
            int[][] edges = { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
            assertEquals(true, detect(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: two triangles sharing vertex 2 (figure eight) → true")
        void testFigureEight() {
            int[][] edges = { { 0, 1 }, { 1, 2 }, { 2, 0 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
            assertEquals(true, detect(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: 3x3 grid → true")
        void testGrid() {
            int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 1, 4 }, { 2, 5 }, { 3, 4 }, { 3, 6 }, { 4, 5 }, { 4, 7 },
                    { 5, 8 }, { 6, 7 }, { 7, 8 } };
            assertEquals(true, detect(undirected(9, edges), 0));
        }

        @Test
        @DisplayName("Scenario: self-loop on vertex 0 → true")
        void testSelfLoop() {
            assertEquals(true, detect(undirected(1, new int[][] { { 0, 0 } }), 0));
        }

        // ── components ─────────────────────────────────────────

        @Test
        @DisplayName("Scenario: acyclic 0-1 plus triangle 2-3-4, start 0 → false (cycle is in another component)")
        void testCycleInUnreachableComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
            assertEquals(false, detect(undirected(5, edges), 0));
        }

        @Test
        @DisplayName("Scenario: acyclic 0-1 plus triangle 2-3-4, start 2 → true")
        void testCycleInStartComponent() {
            int[][] edges = { { 0, 1 }, { 2, 3 }, { 3, 4 }, { 4, 2 } };
            assertEquals(true, detect(undirected(5, edges), 2));
        }

        @Test
        @DisplayName("Scenario: isolated start vertex 2 next to triangle 0-1 / 3-4-5 → false")
        void testIsolatedStart() {
            int[][] edges = { { 0, 1 }, { 3, 4 }, { 4, 5 }, { 5, 3 } };
            assertEquals(false, detect(undirected(6, edges), 2));
        }

        // ── size ───────────────────────────────────────────────

        @Test
        @DisplayName("Scenario: chain with 3,000 vertices → false")
        void testLongChain() {
            int n = 3_000;
            assertEquals(false, detect(undirected(n, path(n)), 0));
        }

        @Test
        @DisplayName("Scenario: ring with 3,000 vertices (chain closed by one extra edge) → true")
        void testLongRing() {
            int n = 3_000;
            List<Edge>[] graph = undirected(n, path(n));
            graph[n - 1].add(new Edge(n - 1, 0));
            graph[0].add(new Edge(0, n - 1));
            assertEquals(true, detect(graph, 0));
        }

        @Test
        @DisplayName("Scenario: star with 10,000 leaves → false")
        void testLargeStar() {
            int n = 10_001;
            int[][] edges = new int[n - 1][];
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { 0, i };
            }
            assertEquals(false, detect(undirected(n, edges), 0));
        }

        @Test
        @DisplayName("Scenario: star with 10,000 leaves plus one edge between two leaves → true")
        void testLargeStarWithLeafEdge() {
            int n = 10_001;
            int[][] edges = new int[n][];
            for (int i = 1; i < n; i++) {
                edges[i - 1] = new int[] { 0, i };
            }
            edges[n - 1] = new int[] { n - 2, n - 1 };
            assertEquals(true, detect(undirected(n, edges), 0));
        }
    }
}
