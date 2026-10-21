package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Search a 2D Matrix Tests")
class Search2DMatrixTest {

    private Search2DMatrix instance;

    @BeforeEach
    void setUp() {
        instance = new Search2DMatrix();
    }

    /** m x n matrix satisfying the problem's properties, with value(i) = -10000 + 2*i (all even). */
    private static int[][] evenMatrix(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = -10000 + 2 * (i * n + j);
            }
        }
        return matrix;
    }

    // ═══════════════════════════════════════════════════════════
    //  searchMatrix()  — binary search over flattened matrix, O(log(m*n))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchMatrix() — binary search over flattened matrix")
    class SearchMatrixTests {

        private final int[][] example = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        @Test
        @DisplayName("LeetCode Example 1: target 3 in 3x4 matrix → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.searchMatrix(example, 3));
        }

        @Test
        @DisplayName("LeetCode Example 2: target 13 in 3x4 matrix → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.searchMatrix(example, 13));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 1 → true (1x1, present)")
        void testSingleCellFound() {
            assertEquals(true, instance.searchMatrix(new int[][]{{1}}, 1));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 2 → false (1x1, absent)")
        void testSingleCellNotFound() {
            assertEquals(false, instance.searchMatrix(new int[][]{{1}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 5 → true (single row)")
        void testSingleRowFound() {
            assertEquals(true, instance.searchMatrix(new int[][]{{1, 3, 5}}, 5));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 4 → false (single row, between values)")
        void testSingleRowNotFound() {
            assertEquals(false, instance.searchMatrix(new int[][]{{1, 3, 5}}, 4));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 3 → true (single column)")
        void testSingleColumnFound() {
            assertEquals(true, instance.searchMatrix(new int[][]{{1}, {3}, {5}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 4 → false (single column)")
        void testSingleColumnNotFound() {
            assertEquals(false, instance.searchMatrix(new int[][]{{1}, {3}, {5}}, 4));
        }

        @Test
        @DisplayName("Scenario: target 1 → true (first element of matrix)")
        void testFirstElement() {
            assertEquals(true, instance.searchMatrix(example, 1));
        }

        @Test
        @DisplayName("Scenario: target 60 → true (last element of matrix)")
        void testLastElement() {
            assertEquals(true, instance.searchMatrix(example, 60));
        }

        @Test
        @DisplayName("Scenario: target 10 → true (first element of a middle row)")
        void testFirstOfMiddleRow() {
            assertEquals(true, instance.searchMatrix(example, 10));
        }

        @Test
        @DisplayName("Scenario: target 20 → true (last element of a middle row)")
        void testLastOfMiddleRow() {
            assertEquals(true, instance.searchMatrix(example, 20));
        }

        @Test
        @DisplayName("Scenario: target 0 → false (smaller than every element)")
        void testBelowMinimum() {
            assertEquals(false, instance.searchMatrix(example, 0));
        }

        @Test
        @DisplayName("Scenario: target 61 → false (larger than every element)")
        void testAboveMaximum() {
            assertEquals(false, instance.searchMatrix(example, 61));
        }

        @Test
        @DisplayName("Scenario: target 8 → false (gap between end of row 0 and start of row 1)")
        void testGapBetweenRows() {
            assertEquals(false, instance.searchMatrix(example, 8));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 2 → true (all same values)")
        void testUniformFound() {
            assertEquals(true, instance.searchMatrix(new int[][]{{2, 2, 2}, {2, 2, 2}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 3 → false (all same values)")
        void testUniformNotFound() {
            assertEquals(false, instance.searchMatrix(new int[][]{{2, 2, 2}, {2, 2, 2}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[-9,-5],[-3,0]], target -3 → true (negatives)")
        void testNegativesFound() {
            assertEquals(true, instance.searchMatrix(new int[][]{{-9, -5}, {-3, 0}}, -3));
        }

        @Test
        @DisplayName("Scenario: [[-10000,10000]], target -10000 → true (value bounds)")
        void testValueBounds() {
            assertEquals(true, instance.searchMatrix(new int[][]{{-10000, 10000}}, -10000));
            assertEquals(true, instance.searchMatrix(new int[][]{{-10000, 10000}}, 10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = last element → true (max size)")
        void testMaxSizeLast() {
            assertEquals(true, instance.searchMatrix(evenMatrix(100, 100), -10000 + 2 * 9999));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = first element → true (max size)")
        void testMaxSizeFirst() {
            assertEquals(true, instance.searchMatrix(evenMatrix(100, 100), -10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target in middle → true (max size)")
        void testMaxSizeMiddle() {
            assertEquals(true, instance.searchMatrix(evenMatrix(100, 100), -10000 + 2 * 5050));
        }

        @Test
        @DisplayName("Scenario: 100x100, odd target between values → false (max size)")
        void testMaxSizeAbsent() {
            assertEquals(false, instance.searchMatrix(evenMatrix(100, 100), -10000 + 2 * 5050 + 1));
        }

        @Test
        @DisplayName("Scenario: 1x100 and 100x1 matrices, last element → true (max-length extremes)")
        void testMaxLengthDegenerateShapes() {
            assertEquals(true, instance.searchMatrix(evenMatrix(1, 100), -10000 + 2 * 99));
            assertEquals(true, instance.searchMatrix(evenMatrix(100, 1), -10000 + 2 * 99));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchMatrixStreams()  — binary search over flattened matrix, O(log(m*n))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchMatrixStreams() — binary search over flattened matrix")
    class SearchMatrixStreamsTests {

        private final int[][] example = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        @Test
        @DisplayName("LeetCode Example 1: target 3 in 3x4 matrix → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.searchMatrixStreams(example, 3));
        }

        @Test
        @DisplayName("LeetCode Example 2: target 13 in 3x4 matrix → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.searchMatrixStreams(example, 13));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 1 → true (1x1, present)")
        void testSingleCellFound() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{1}}, 1));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 2 → false (1x1, absent)")
        void testSingleCellNotFound() {
            assertEquals(false, instance.searchMatrixStreams(new int[][]{{1}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 5 → true (single row)")
        void testSingleRowFound() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{1, 3, 5}}, 5));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 4 → false (single row, between values)")
        void testSingleRowNotFound() {
            assertEquals(false, instance.searchMatrixStreams(new int[][]{{1, 3, 5}}, 4));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 3 → true (single column)")
        void testSingleColumnFound() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{1}, {3}, {5}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 4 → false (single column)")
        void testSingleColumnNotFound() {
            assertEquals(false, instance.searchMatrixStreams(new int[][]{{1}, {3}, {5}}, 4));
        }

        @Test
        @DisplayName("Scenario: target 1 → true (first element of matrix)")
        void testFirstElement() {
            assertEquals(true, instance.searchMatrixStreams(example, 1));
        }

        @Test
        @DisplayName("Scenario: target 60 → true (last element of matrix)")
        void testLastElement() {
            assertEquals(true, instance.searchMatrixStreams(example, 60));
        }

        @Test
        @DisplayName("Scenario: target 10 → true (first element of a middle row)")
        void testFirstOfMiddleRow() {
            assertEquals(true, instance.searchMatrixStreams(example, 10));
        }

        @Test
        @DisplayName("Scenario: target 20 → true (last element of a middle row)")
        void testLastOfMiddleRow() {
            assertEquals(true, instance.searchMatrixStreams(example, 20));
        }

        @Test
        @DisplayName("Scenario: target 0 → false (smaller than every element)")
        void testBelowMinimum() {
            assertEquals(false, instance.searchMatrixStreams(example, 0));
        }

        @Test
        @DisplayName("Scenario: target 61 → false (larger than every element)")
        void testAboveMaximum() {
            assertEquals(false, instance.searchMatrixStreams(example, 61));
        }

        @Test
        @DisplayName("Scenario: target 8 → false (gap between end of row 0 and start of row 1)")
        void testGapBetweenRows() {
            assertEquals(false, instance.searchMatrixStreams(example, 8));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 2 → true (all same values)")
        void testUniformFound() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{2, 2, 2}, {2, 2, 2}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 3 → false (all same values)")
        void testUniformNotFound() {
            assertEquals(false, instance.searchMatrixStreams(new int[][]{{2, 2, 2}, {2, 2, 2}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[-9,-5],[-3,0]], target -3 → true (negatives)")
        void testNegativesFound() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{-9, -5}, {-3, 0}}, -3));
        }

        @Test
        @DisplayName("Scenario: [[-10000,10000]], target -10000 → true (value bounds)")
        void testValueBounds() {
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{-10000, 10000}}, -10000));
            assertEquals(true, instance.searchMatrixStreams(new int[][]{{-10000, 10000}}, 10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = last element → true (max size)")
        void testMaxSizeLast() {
            assertEquals(true, instance.searchMatrixStreams(evenMatrix(100, 100), -10000 + 2 * 9999));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = first element → true (max size)")
        void testMaxSizeFirst() {
            assertEquals(true, instance.searchMatrixStreams(evenMatrix(100, 100), -10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target in middle → true (max size)")
        void testMaxSizeMiddle() {
            assertEquals(true, instance.searchMatrixStreams(evenMatrix(100, 100), -10000 + 2 * 5050));
        }

        @Test
        @DisplayName("Scenario: 100x100, odd target between values → false (max size)")
        void testMaxSizeAbsent() {
            assertEquals(false, instance.searchMatrixStreams(evenMatrix(100, 100), -10000 + 2 * 5050 + 1));
        }

        @Test
        @DisplayName("Scenario: 1x100 and 100x1 matrices, last element → true (max-length extremes)")
        void testMaxLengthDegenerateShapes() {
            assertEquals(true, instance.searchMatrixStreams(evenMatrix(1, 100), -10000 + 2 * 99));
            assertEquals(true, instance.searchMatrixStreams(evenMatrix(100, 1), -10000 + 2 * 99));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchMatrixStreamsUsingBinarySearchImplementation()  — binary search over flattened matrix, O(log(m*n))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchMatrixStreamsUsingBinarySearchImplementation() — binary search over flattened matrix")
    class SearchMatrixStreamsUsingBinarySearchImplementationTests {

        private final int[][] example = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        @Test
        @DisplayName("LeetCode Example 1: target 3 in 3x4 matrix → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 3));
        }

        @Test
        @DisplayName("LeetCode Example 2: target 13 in 3x4 matrix → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 13));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 1 → true (1x1, present)")
        void testSingleCellFound() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1}}, 1));
        }

        @Test
        @DisplayName("Scenario: [[1]], target 2 → false (1x1, absent)")
        void testSingleCellNotFound() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 5 → true (single row)")
        void testSingleRowFound() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1, 3, 5}}, 5));
        }

        @Test
        @DisplayName("Scenario: [[1,3,5]], target 4 → false (single row, between values)")
        void testSingleRowNotFound() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1, 3, 5}}, 4));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 3 → true (single column)")
        void testSingleColumnFound() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1}, {3}, {5}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[1],[3],[5]], target 4 → false (single column)")
        void testSingleColumnNotFound() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{1}, {3}, {5}}, 4));
        }

        @Test
        @DisplayName("Scenario: target 1 → true (first element of matrix)")
        void testFirstElement() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 1));
        }

        @Test
        @DisplayName("Scenario: target 60 → true (last element of matrix)")
        void testLastElement() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 60));
        }

        @Test
        @DisplayName("Scenario: target 10 → true (first element of a middle row)")
        void testFirstOfMiddleRow() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 10));
        }

        @Test
        @DisplayName("Scenario: target 20 → true (last element of a middle row)")
        void testLastOfMiddleRow() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 20));
        }

        @Test
        @DisplayName("Scenario: target 0 → false (smaller than every element)")
        void testBelowMinimum() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 0));
        }

        @Test
        @DisplayName("Scenario: target 61 → false (larger than every element)")
        void testAboveMaximum() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 61));
        }

        @Test
        @DisplayName("Scenario: target 8 → false (gap between end of row 0 and start of row 1)")
        void testGapBetweenRows() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(example, 8));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 2 → true (all same values)")
        void testUniformFound() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{2, 2, 2}, {2, 2, 2}}, 2));
        }

        @Test
        @DisplayName("Scenario: [[2,2,2],[2,2,2]], target 3 → false (all same values)")
        void testUniformNotFound() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{2, 2, 2}, {2, 2, 2}}, 3));
        }

        @Test
        @DisplayName("Scenario: [[-9,-5],[-3,0]], target -3 → true (negatives)")
        void testNegativesFound() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{-9, -5}, {-3, 0}}, -3));
        }

        @Test
        @DisplayName("Scenario: [[-10000,10000]], target -10000 → true (value bounds)")
        void testValueBounds() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{-10000, 10000}}, -10000));
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(new int[][]{{-10000, 10000}}, 10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = last element → true (max size)")
        void testMaxSizeLast() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(100, 100), -10000 + 2 * 9999));
        }

        @Test
        @DisplayName("Scenario: 100x100, target = first element → true (max size)")
        void testMaxSizeFirst() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(100, 100), -10000));
        }

        @Test
        @DisplayName("Scenario: 100x100, target in middle → true (max size)")
        void testMaxSizeMiddle() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(100, 100), -10000 + 2 * 5050));
        }

        @Test
        @DisplayName("Scenario: 100x100, odd target between values → false (max size)")
        void testMaxSizeAbsent() {
            assertEquals(false, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(100, 100), -10000 + 2 * 5050 + 1));
        }

        @Test
        @DisplayName("Scenario: 1x100 and 100x1 matrices, last element → true (max-length extremes)")
        void testMaxLengthDegenerateShapes() {
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(1, 100), -10000 + 2 * 99));
            assertEquals(true, instance.searchMatrixStreamsUsingBinarySearchImplementation(evenMatrix(100, 1), -10000 + 2 * 99));
        }
    }
}
