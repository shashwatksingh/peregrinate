package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SetMatrixZeroes Tests")
class SetMatrixZeroesTest {

    private SetMatrixZeroes instance;

    @BeforeEach
    void setUp() {
        instance = new SetMatrixZeroes();
    }

    private static int[][] uniformMatrix(int rows, int cols, int value) {
        int[][] matrix = new int[rows][cols];
        for (int[] row : matrix) {
            Arrays.fill(row, value);
        }
        return matrix;
    }

    // ═══════════════════════════════════════════════════════════
    //  setZeroesListMarkers()  — list-based row/col markers, O(mn) time / O(m+n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setZeroesListMarkers() — list-based row/col markers")
    class SetZeroesListMarkersTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,1,1],[1,0,1],[1,1,1]] → [[1,0,1],[0,0,0],[1,0,1]]")
        void testLeetCodeExample1() {
            int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            int[][] expected = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[0,1,2,0],[3,4,5,2],[1,3,1,5]] → [[0,0,0,0],[0,4,5,0],[0,3,1,0]]")
        void testLeetCodeExample2() {
            int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
            int[][] expected = {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, zero present: [[0]] → [[0]]")
        void testMinimumSizeWithZero() {
            int[][] matrix = {{0}};
            int[][] expected = {{0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, no zero: [[5]] → [[5]] (unchanged)")
        void testMinimumSizeNoZero() {
            int[][] matrix = {{5}};
            int[][] expected = {{5}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All-same values, no zero present: [[1,1],[1,1]] → unchanged")
        void testAllSameValuesNoZero() {
            int[][] matrix = {{1, 1}, {1, 1}};
            int[][] expected = {{1, 1}, {1, 1}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in a corner: [[1,2,3],[4,5,6],[7,8,0]] → [[1,2,0],[4,5,0],[0,0,0]]")
        void testZeroInCorner() {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Multiple zeros with overlapping rows/cols: [[1,0,3],[4,5,0],[7,8,9]] → [[0,0,0],[0,0,0],[7,0,0]]")
        void testMultipleZerosOverlappingRowsAndCols() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 0}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {7, 0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square tall matrix, zero in first column: [[1,2],[0,4],[5,6],[7,8]] → [[0,2],[0,0],[0,6],[0,8]]")
        void testNonSquareZeroInFirstColumn() {
            int[][] matrix = {{1, 2}, {0, 4}, {5, 6}, {7, 8}};
            int[][] expected = {{0, 2}, {0, 0}, {0, 6}, {0, 8}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at the center → row/col 100 zeroed")
        void testMaxConstraintBoundary() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[100][100] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[100][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][100] = 0;
            }

            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  setZeroesMarking()  — (in-progress alternate implementation)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setZeroesMarking() — alternate implementation")
    class SetZeroesMarkingTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,1,1],[1,0,1],[1,1,1]] → [[1,0,1],[0,0,0],[1,0,1]]")
        void testLeetCodeExample1() {
            int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            int[][] expected = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[0,1,2,0],[3,4,5,2],[1,3,1,5]] → [[0,0,0,0],[0,4,5,0],[0,3,1,0]]")
        void testLeetCodeExample2() {
            int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
            int[][] expected = {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, zero present: [[0]] → [[0]]")
        void testMinimumSizeWithZero() {
            int[][] matrix = {{0}};
            int[][] expected = {{0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, no zero: [[5]] → [[5]] (unchanged)")
        void testMinimumSizeNoZero() {
            int[][] matrix = {{5}};
            int[][] expected = {{5}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All-same values, no zero present: [[1,1],[1,1]] → unchanged")
        void testAllSameValuesNoZero() {
            int[][] matrix = {{1, 1}, {1, 1}};
            int[][] expected = {{1, 1}, {1, 1}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in a corner: [[1,2,3],[4,5,6],[7,8,0]] → [[1,2,0],[4,5,0],[0,0,0]]")
        void testZeroInCorner() {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Multiple zeros with overlapping rows/cols: [[1,0,3],[4,5,0],[7,8,9]] → [[0,0,0],[0,0,0],[7,0,0]]")
        void testMultipleZerosOverlappingRowsAndCols() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 0}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {7, 0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at the center → row/col 100 zeroed")
        void testMaxConstraintBoundary() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[100][100] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[100][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][100] = 0;
            }

            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }
    }
}
