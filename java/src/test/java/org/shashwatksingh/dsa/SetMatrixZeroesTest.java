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
    //  setZeroesSetMarkers()  — list-based row/col markers, O(mn·(m+n)) time / O(m+n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setZeroesSetMarkers() — list-based row/col markers")
    class SetZeroesSetMarkersTests {

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
        @DisplayName("All-same values, no zero present: [[1,1],[1,1]] → [[1,1],[1,1]] (unchanged)")
        void testAllSameValuesNoZero() {
            int[][] matrix = {{1, 1}, {1, 1}};
            int[][] expected = {{1, 1}, {1, 1}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All zeros: [[0,0],[0,0]] → [[0,0],[0,0]] (unchanged)")
        void testAllZeros() {
            int[][] matrix = {{0, 0}, {0, 0}};
            int[][] expected = {{0, 0}, {0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in bottom-right corner: [[1,2,3],[4,5,6],[7,8,0]] → [[1,2,0],[4,5,0],[0,0,0]]")
        void testZeroInCorner() {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in top-left corner: [[0,2,3],[4,5,6],[7,8,9]] → [[0,0,0],[0,5,6],[0,8,9]]")
        void testZeroInTopLeftCorner() {
            int[][] matrix = {{0, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 5, 6}, {0, 8, 9}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first row only (not corner): [[1,0,3],[4,5,6],[7,8,9]] → [[0,0,0],[4,0,6],[7,0,9]]")
        void testZeroInFirstRowOnly() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {4, 0, 6}, {7, 0, 9}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first column only (not corner): [[1,2,3],[0,5,6],[7,8,9]] → [[0,2,3],[0,0,0],[0,8,9]]")
        void testZeroInFirstColumnOnly() {
            int[][] matrix = {{1, 2, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 2, 3}, {0, 0, 0}, {0, 8, 9}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in the middle of the first row and first column: [[1,0,3],[0,5,6],[7,8,9]] → [[0,0,0],[0,0,0],[0,0,9]]")
        void testZerosInFirstRowAndFirstColumn() {
            int[][] matrix = {{1, 0, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 9}};
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
        @DisplayName("Non-square tall matrix, zero in last row: [[1,2],[3,4],[0,6]] → [[0,2],[0,4],[0,0]]")
        void testNonSquareTallZeroInLastRow() {
            int[][] matrix = {{1, 2}, {3, 4}, {0, 6}};
            int[][] expected = {{0, 2}, {0, 4}, {0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square wide matrix, zero in last row: [[1,2,3,4],[5,0,7,8]] → [[1,0,3,4],[0,0,0,0]]")
        void testNonSquareWideZeroInLastRow() {
            int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}};
            int[][] expected = {{1, 0, 3, 4}, {0, 0, 0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row with a zero: [[1,0,3]] → [[0,0,0]]")
        void testSingleRowWithZero() {
            int[][] matrix = {{1, 0, 3}};
            int[][] expected = {{0, 0, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single column with a zero: [[1],[0],[3]] → [[0],[0],[0]]")
        void testSingleColumnWithZero() {
            int[][] matrix = {{1}, {0}, {3}};
            int[][] expected = {{0}, {0}, {0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row, no zero: [[1,2,3]] → [[1,2,3]] (unchanged)")
        void testSingleRowNoZero() {
            int[][] matrix = {{1, 2, 3}};
            int[][] expected = {{1, 2, 3}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Int boundary values with a zero: [[Integer.MIN_VALUE,0],[3,Integer.MAX_VALUE]] → [[0,0],[3,0]]")
        void testIntBoundaryValues() {
            int[][] matrix = {{Integer.MIN_VALUE, 0}, {3, Integer.MAX_VALUE}};
            int[][] expected = {{0, 0}, {3, 0}};
            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [100][100] → row 100 and column 100 zeroed")
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

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [0][0] → row 0 and column 0 zeroed")
        void testMaxConstraintBoundaryTopLeft() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[0][0] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[0][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][0] = 0;
            }

            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [199][199] → row 199 and column 199 zeroed")
        void testMaxConstraintBoundaryBottomRight() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[199][199] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[199][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][199] = 0;
            }

            instance.setZeroesSetMarkers(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  setZeroesSetMarkersOptimised()  — HashSet row/col markers, O(mn) time / O(m+n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setZeroesSetMarkersOptimised() — HashSet row/col markers")
    class SetZeroesSetMarkersOptimisedTests {

        @Test
        @DisplayName("LeetCode Example 1: [[1,1,1],[1,0,1],[1,1,1]] → [[1,0,1],[0,0,0],[1,0,1]]")
        void testLeetCodeExample1() {
            int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            int[][] expected = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[0,1,2,0],[3,4,5,2],[1,3,1,5]] → [[0,0,0,0],[0,4,5,0],[0,3,1,0]]")
        void testLeetCodeExample2() {
            int[][] matrix = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
            int[][] expected = {{0, 0, 0, 0}, {0, 4, 5, 0}, {0, 3, 1, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, zero present: [[0]] → [[0]]")
        void testMinimumSizeWithZero() {
            int[][] matrix = {{0}};
            int[][] expected = {{0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Minimum-size 1x1 input, no zero: [[5]] → [[5]] (unchanged)")
        void testMinimumSizeNoZero() {
            int[][] matrix = {{5}};
            int[][] expected = {{5}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All-same values, no zero present: [[1,1],[1,1]] → [[1,1],[1,1]] (unchanged)")
        void testAllSameValuesNoZero() {
            int[][] matrix = {{1, 1}, {1, 1}};
            int[][] expected = {{1, 1}, {1, 1}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All zeros: [[0,0],[0,0]] → [[0,0],[0,0]] (unchanged)")
        void testAllZeros() {
            int[][] matrix = {{0, 0}, {0, 0}};
            int[][] expected = {{0, 0}, {0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in bottom-right corner: [[1,2,3],[4,5,6],[7,8,0]] → [[1,2,0],[4,5,0],[0,0,0]]")
        void testZeroInCorner() {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in top-left corner: [[0,2,3],[4,5,6],[7,8,9]] → [[0,0,0],[0,5,6],[0,8,9]]")
        void testZeroInTopLeftCorner() {
            int[][] matrix = {{0, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 5, 6}, {0, 8, 9}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first row only (not corner): [[1,0,3],[4,5,6],[7,8,9]] → [[0,0,0],[4,0,6],[7,0,9]]")
        void testZeroInFirstRowOnly() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {4, 0, 6}, {7, 0, 9}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first column only (not corner): [[1,2,3],[0,5,6],[7,8,9]] → [[0,2,3],[0,0,0],[0,8,9]]")
        void testZeroInFirstColumnOnly() {
            int[][] matrix = {{1, 2, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 2, 3}, {0, 0, 0}, {0, 8, 9}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in the middle of the first row and first column: [[1,0,3],[0,5,6],[7,8,9]] → [[0,0,0],[0,0,0],[0,0,9]]")
        void testZerosInFirstRowAndFirstColumn() {
            int[][] matrix = {{1, 0, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 9}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Multiple zeros with overlapping rows/cols: [[1,0,3],[4,5,0],[7,8,9]] → [[0,0,0],[0,0,0],[7,0,0]]")
        void testMultipleZerosOverlappingRowsAndCols() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 0}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {7, 0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square tall matrix, zero in first column: [[1,2],[0,4],[5,6],[7,8]] → [[0,2],[0,0],[0,6],[0,8]]")
        void testNonSquareZeroInFirstColumn() {
            int[][] matrix = {{1, 2}, {0, 4}, {5, 6}, {7, 8}};
            int[][] expected = {{0, 2}, {0, 0}, {0, 6}, {0, 8}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square tall matrix, zero in last row: [[1,2],[3,4],[0,6]] → [[0,2],[0,4],[0,0]]")
        void testNonSquareTallZeroInLastRow() {
            int[][] matrix = {{1, 2}, {3, 4}, {0, 6}};
            int[][] expected = {{0, 2}, {0, 4}, {0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square wide matrix, zero in last row: [[1,2,3,4],[5,0,7,8]] → [[1,0,3,4],[0,0,0,0]]")
        void testNonSquareWideZeroInLastRow() {
            int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}};
            int[][] expected = {{1, 0, 3, 4}, {0, 0, 0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row with a zero: [[1,0,3]] → [[0,0,0]]")
        void testSingleRowWithZero() {
            int[][] matrix = {{1, 0, 3}};
            int[][] expected = {{0, 0, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single column with a zero: [[1],[0],[3]] → [[0],[0],[0]]")
        void testSingleColumnWithZero() {
            int[][] matrix = {{1}, {0}, {3}};
            int[][] expected = {{0}, {0}, {0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row, no zero: [[1,2,3]] → [[1,2,3]] (unchanged)")
        void testSingleRowNoZero() {
            int[][] matrix = {{1, 2, 3}};
            int[][] expected = {{1, 2, 3}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Int boundary values with a zero: [[Integer.MIN_VALUE,0],[3,Integer.MAX_VALUE]] → [[0,0],[3,0]]")
        void testIntBoundaryValues() {
            int[][] matrix = {{Integer.MIN_VALUE, 0}, {3, Integer.MAX_VALUE}};
            int[][] expected = {{0, 0}, {3, 0}};
            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [100][100] → row 100 and column 100 zeroed")
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

            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [0][0] → row 0 and column 0 zeroed")
        void testMaxConstraintBoundaryTopLeft() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[0][0] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[0][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][0] = 0;
            }

            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [199][199] → row 199 and column 199 zeroed")
        void testMaxConstraintBoundaryBottomRight() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[199][199] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[199][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][199] = 0;
            }

            instance.setZeroesSetMarkersOptimised(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  setZeroesMarking()  — first row/col as markers, O(mn) time / O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setZeroesMarking() — first row/col as markers")
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
        @DisplayName("All-same values, no zero present: [[1,1],[1,1]] → [[1,1],[1,1]] (unchanged)")
        void testAllSameValuesNoZero() {
            int[][] matrix = {{1, 1}, {1, 1}};
            int[][] expected = {{1, 1}, {1, 1}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("All zeros: [[0,0],[0,0]] → [[0,0],[0,0]] (unchanged)")
        void testAllZeros() {
            int[][] matrix = {{0, 0}, {0, 0}};
            int[][] expected = {{0, 0}, {0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in bottom-right corner: [[1,2,3],[4,5,6],[7,8,0]] → [[1,2,0],[4,5,0],[0,0,0]]")
        void testZeroInCorner() {
            int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
            int[][] expected = {{1, 2, 0}, {4, 5, 0}, {0, 0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in top-left corner: [[0,2,3],[4,5,6],[7,8,9]] → [[0,0,0],[0,5,6],[0,8,9]]")
        void testZeroInTopLeftCorner() {
            int[][] matrix = {{0, 2, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 5, 6}, {0, 8, 9}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first row only (not corner): [[1,0,3],[4,5,6],[7,8,9]] → [[0,0,0],[4,0,6],[7,0,9]]")
        void testZeroInFirstRowOnly() {
            int[][] matrix = {{1, 0, 3}, {4, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {4, 0, 6}, {7, 0, 9}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in first column only (not corner): [[1,2,3],[0,5,6],[7,8,9]] → [[0,2,3],[0,0,0],[0,8,9]]")
        void testZeroInFirstColumnOnly() {
            int[][] matrix = {{1, 2, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 2, 3}, {0, 0, 0}, {0, 8, 9}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Zero in the middle of the first row and first column: [[1,0,3],[0,5,6],[7,8,9]] → [[0,0,0],[0,0,0],[0,0,9]]")
        void testZerosInFirstRowAndFirstColumn() {
            int[][] matrix = {{1, 0, 3}, {0, 5, 6}, {7, 8, 9}};
            int[][] expected = {{0, 0, 0}, {0, 0, 0}, {0, 0, 9}};
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
        @DisplayName("Non-square tall matrix, zero in first column: [[1,2],[0,4],[5,6],[7,8]] → [[0,2],[0,0],[0,6],[0,8]]")
        void testNonSquareZeroInFirstColumn() {
            int[][] matrix = {{1, 2}, {0, 4}, {5, 6}, {7, 8}};
            int[][] expected = {{0, 2}, {0, 0}, {0, 6}, {0, 8}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square tall matrix, zero in last row: [[1,2],[3,4],[0,6]] → [[0,2],[0,4],[0,0]]")
        void testNonSquareTallZeroInLastRow() {
            int[][] matrix = {{1, 2}, {3, 4}, {0, 6}};
            int[][] expected = {{0, 2}, {0, 4}, {0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Non-square wide matrix, zero in last row: [[1,2,3,4],[5,0,7,8]] → [[1,0,3,4],[0,0,0,0]]")
        void testNonSquareWideZeroInLastRow() {
            int[][] matrix = {{1, 2, 3, 4}, {5, 0, 7, 8}};
            int[][] expected = {{1, 0, 3, 4}, {0, 0, 0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row with a zero: [[1,0,3]] → [[0,0,0]]")
        void testSingleRowWithZero() {
            int[][] matrix = {{1, 0, 3}};
            int[][] expected = {{0, 0, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single column with a zero: [[1],[0],[3]] → [[0],[0],[0]]")
        void testSingleColumnWithZero() {
            int[][] matrix = {{1}, {0}, {3}};
            int[][] expected = {{0}, {0}, {0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Single row, no zero: [[1,2,3]] → [[1,2,3]] (unchanged)")
        void testSingleRowNoZero() {
            int[][] matrix = {{1, 2, 3}};
            int[][] expected = {{1, 2, 3}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Int boundary values with a zero: [[Integer.MIN_VALUE,0],[3,Integer.MAX_VALUE]] → [[0,0],[3,0]]")
        void testIntBoundaryValues() {
            int[][] matrix = {{Integer.MIN_VALUE, 0}, {3, Integer.MAX_VALUE}};
            int[][] expected = {{0, 0}, {3, 0}};
            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [100][100] → row 100 and column 100 zeroed")
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

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [0][0] → row 0 and column 0 zeroed")
        void testMaxConstraintBoundaryTopLeft() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[0][0] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[0][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][0] = 0;
            }

            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }

        @Test
        @DisplayName("Max constraint boundary: 200x200 matrix of max int value with a single zero at [199][199] → row 199 and column 199 zeroed")
        void testMaxConstraintBoundaryBottomRight() {
            int size = 200;
            int[][] matrix = uniformMatrix(size, size, Integer.MAX_VALUE);
            matrix[199][199] = 0;

            int[][] expected = uniformMatrix(size, size, Integer.MAX_VALUE);
            for (int j = 0; j < size; j++) {
                expected[199][j] = 0;
            }
            for (int i = 0; i < size; i++) {
                expected[i][199] = 0;
            }

            instance.setZeroesMarking(matrix);
            assertEquals(Arrays.deepToString(expected), Arrays.deepToString(matrix));
        }
    }
}
