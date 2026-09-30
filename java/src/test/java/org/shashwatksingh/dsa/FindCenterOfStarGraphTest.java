package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("FindCenterOfStarGraph Tests")
class FindCenterOfStarGraphTest {

    private FindCenterOfStarGraph instance;

    @BeforeEach
    void setUp() {
        instance = new FindCenterOfStarGraph();
    }

    private static int[][] maxSizeStarGraph() {
        int n = 100000;
        int[][] edges = new int[n - 1][2];
        for (int i = 0; i < n - 1; i++) {
            edges[i] = new int[]{1, i + 2};
        }
        return edges;
    }

    // ═══════════════════════════════════════════════════════════
    //  findCenterAdjacencyListSolution()  — degree count via HashMap, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findCenterAdjacencyListSolution() — degree count via HashMap")
    class FindCenterAdjacencyListSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,2],[2,3],[4,2]] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.findCenterAdjacencyListSolution(new int[][]{{1, 2}, {2, 3}, {4, 2}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,2],[5,1],[1,3],[1,4]] → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.findCenterAdjacencyListSolution(new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed first in both edges: [[1,2],[1,3]] → 1")
        void testMinimumSizeCenterFirst() {
            assertEquals(1, instance.findCenterAdjacencyListSolution(new int[][]{{1, 2}, {1, 3}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed second in both edges: [[2,1],[3,1]] → 1")
        void testMinimumSizeCenterSecond() {
            assertEquals(1, instance.findCenterAdjacencyListSolution(new int[][]{{2, 1}, {3, 1}}));
        }

        @Test
        @DisplayName("Center is the highest-numbered node: [[1,5],[2,5],[3,5],[4,5]] → 5")
        void testCenterIsHighestNumberedNode() {
            assertEquals(5, instance.findCenterAdjacencyListSolution(new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
        }

        @Test
        @DisplayName("Center position alternates across edges: [[3,1],[1,4],[5,1],[1,2]] → 1")
        void testCenterPositionAlternates() {
            assertEquals(1, instance.findCenterAdjacencyListSolution(new int[][]{{3, 1}, {1, 4}, {5, 1}, {1, 2}}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=100000 nodes, center=1 → 1")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.findCenterAdjacencyListSolution(maxSizeStarGraph()));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  findCenter()  — degree count via incidence array, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findCenter() — degree count via incidence array")
    class FindCenterTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,2],[2,3],[4,2]] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.findCenter(new int[][]{{1, 2}, {2, 3}, {4, 2}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,2],[5,1],[1,3],[1,4]] → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.findCenter(new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed first in both edges: [[1,2],[1,3]] → 1")
        void testMinimumSizeCenterFirst() {
            assertEquals(1, instance.findCenter(new int[][]{{1, 2}, {1, 3}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed second in both edges: [[2,1],[3,1]] → 1")
        void testMinimumSizeCenterSecond() {
            assertEquals(1, instance.findCenter(new int[][]{{2, 1}, {3, 1}}));
        }

        @Test
        @DisplayName("Center is the highest-numbered node: [[1,5],[2,5],[3,5],[4,5]] → 5")
        void testCenterIsHighestNumberedNode() {
            assertEquals(5, instance.findCenter(new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
        }

        @Test
        @DisplayName("Center position alternates across edges: [[3,1],[1,4],[5,1],[1,2]] → 1")
        void testCenterPositionAlternates() {
            assertEquals(1, instance.findCenter(new int[][]{{3, 1}, {1, 4}, {5, 1}, {1, 2}}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=100000 nodes, center=1 → 1")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.findCenter(maxSizeStarGraph()));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  findCenterConstantTimeSolutino()  — first-two-edges comparison, O(1) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findCenterConstantTimeSolutino() — first-two-edges comparison")
    class FindCenterConstantTimeSolutinoTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,2],[2,3],[4,2]] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.findCenterConstantTimeSolutino(new int[][]{{1, 2}, {2, 3}, {4, 2}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,2],[5,1],[1,3],[1,4]] → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.findCenterConstantTimeSolutino(new int[][]{{1, 2}, {5, 1}, {1, 3}, {1, 4}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed first in both edges: [[1,2],[1,3]] → 1")
        void testMinimumSizeCenterFirst() {
            assertEquals(1, instance.findCenterConstantTimeSolutino(new int[][]{{1, 2}, {1, 3}}));
        }

        @Test
        @DisplayName("Minimum-size input, center listed second in both edges: [[2,1],[3,1]] → 1")
        void testMinimumSizeCenterSecond() {
            assertEquals(1, instance.findCenterConstantTimeSolutino(new int[][]{{2, 1}, {3, 1}}));
        }

        @Test
        @DisplayName("Center is the highest-numbered node: [[1,5],[2,5],[3,5],[4,5]] → 5")
        void testCenterIsHighestNumberedNode() {
            assertEquals(5, instance.findCenterConstantTimeSolutino(new int[][]{{1, 5}, {2, 5}, {3, 5}, {4, 5}}));
        }

        @Test
        @DisplayName("Center position alternates across edges: [[3,1],[1,4],[5,1],[1,2]] → 1")
        void testCenterPositionAlternates() {
            assertEquals(1, instance.findCenterConstantTimeSolutino(new int[][]{{3, 1}, {1, 4}, {5, 1}, {1, 2}}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=100000 nodes, center=1 → 1")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.findCenterConstantTimeSolutino(maxSizeStarGraph()));
        }
    }
}
