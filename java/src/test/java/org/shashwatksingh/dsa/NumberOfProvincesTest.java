package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Number of Provinces Tests")
class NumberOfProvincesTest {

    private NumberOfProvinces instance;

    @BeforeEach
    void setUp() {
        instance = new NumberOfProvinces();
    }

    private static int[][] identityMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) matrix[i][i] = 1;
        return matrix;
    }

    private static int[][] fullyConnectedMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int[] row : matrix) java.util.Arrays.fill(row, 1);
        return matrix;
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionDfs()  — recursive DFS over the adjacency matrix
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionDfs() — count connected components via DFS")
    class SolutionDfsTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,1,0],[1,1,0],[0,0,1]] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.solutionDfs(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,0,0],[0,1,0],[0,0,1]] → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.solutionDfs(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
        }

        @Test
        @DisplayName("Minimum-size input: [[1]] → 1 (single city)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.solutionDfs(new int[][]{{1}}));
        }

        @Test
        @DisplayName("All connected: 3x3 matrix of all 1's → 1 province")
        void testAllConnected() {
            assertEquals(1, instance.solutionDfs(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
        }

        @Test
        @DisplayName("All isolated: 4x4 identity matrix → 4 provinces")
        void testAllIsolated() {
            assertEquals(4, instance.solutionDfs(identityMatrix(4)));
        }

        @Test
        @DisplayName("Transitive chain (0-1-2-3 with no direct 0-3 link) → 1 province")
        void testTransitiveChainFormsOneProvince() {
            int[][] matrix = {
                    {1, 1, 0, 0},
                    {1, 1, 1, 0},
                    {0, 1, 1, 1},
                    {0, 0, 1, 1}
            };
            assertEquals(1, instance.solutionDfs(matrix));
        }

        @Test
        @DisplayName("Multiple components of different sizes: {0,1}, {2}, {3,4} → 3 provinces")
        void testMultipleComponentsOfDifferentSizes() {
            int[][] matrix = {
                    {1, 1, 0, 0, 0},
                    {1, 1, 0, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 1},
                    {0, 0, 0, 1, 1}
            };
            assertEquals(3, instance.solutionDfs(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 fully-connected matrix → 1 province")
        void testMaxConstraintBoundaryValues() {
            assertEquals(1, instance.solutionDfs(fullyConnectedMatrix(200)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionBfs()  — iterative BFS over the adjacency matrix
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionBfs() — count connected components via BFS")
    class SolutionBfsTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,1,0],[1,1,0],[0,0,1]] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.solutionBfs(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[1,0,0],[0,1,0],[0,0,1]] → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.solutionBfs(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
        }

        @Test
        @DisplayName("Minimum-size input: [[1]] → 1 (single city)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.solutionBfs(new int[][]{{1}}));
        }

        @Test
        @DisplayName("All connected: 3x3 matrix of all 1's → 1 province")
        void testAllConnected() {
            assertEquals(1, instance.solutionBfs(new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}}));
        }

        @Test
        @DisplayName("All isolated: 4x4 identity matrix → 4 provinces")
        void testAllIsolated() {
            assertEquals(4, instance.solutionBfs(identityMatrix(4)));
        }

        @Test
        @DisplayName("Transitive chain (0-1-2-3 with no direct 0-3 link) → 1 province")
        void testTransitiveChainFormsOneProvince() {
            int[][] matrix = {
                    {1, 1, 0, 0},
                    {1, 1, 1, 0},
                    {0, 1, 1, 1},
                    {0, 0, 1, 1}
            };
            assertEquals(1, instance.solutionBfs(matrix));
        }

        @Test
        @DisplayName("Multiple components of different sizes: {0,1}, {2}, {3,4} → 3 provinces")
        void testMultipleComponentsOfDifferentSizes() {
            int[][] matrix = {
                    {1, 1, 0, 0, 0},
                    {1, 1, 0, 0, 0},
                    {0, 0, 1, 0, 0},
                    {0, 0, 0, 1, 1},
                    {0, 0, 0, 1, 1}
            };
            assertEquals(3, instance.solutionBfs(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 fully-connected matrix → 1 province")
        void testMaxConstraintBoundaryValues() {
            assertEquals(1, instance.solutionBfs(fullyConnectedMatrix(200)));
        }
    }
}
