package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Squares of a Sorted Array Tests")
class SquaresOfSortedArraysTest {

    private SquaresOfSortedArrays instance;

    @BeforeEach
    void setUp() {
        instance = new SquaresOfSortedArrays();
    }

    // ═══════════════════════════════════════════════════════════
    //  sortedSquaresSorting()  — map to squares then sort, O(n log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("sortedSquaresSorting() — square then sort")
    class SortedSquaresSortingTests {

        @Test
        @DisplayName("LeetCode Example 1: nums=[-4,-1,0,3,10] → [0,1,9,16,100]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[]{0, 1, 9, 16, 100}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-4, -1, 0, 3, 10})));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums=[-7,-3,2,3,11] → [4,9,9,49,121]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[]{4, 9, 9, 49, 121}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-7, -3, 2, 3, 11})));
        }

        @Test
        @DisplayName("Minimum-size input: nums=[-4] → [16]")
        void testMinimumSizeInput() {
            assertEquals(Arrays.toString(new int[]{16}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-4})));
        }

        @Test
        @DisplayName("All-same values: nums=[-2,-2,-2] → [4,4,4]")
        void testAllSameValues() {
            assertEquals(Arrays.toString(new int[]{4, 4, 4}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-2, -2, -2})));
        }

        @Test
        @DisplayName("All non-negative, strictly increasing: nums=[1,2,3,4,5] → [1,4,9,16,25]")
        void testAllNonNegativeStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[]{1, 4, 9, 16, 25}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{1, 2, 3, 4, 5})));
        }

        @Test
        @DisplayName("All negative, increasing toward zero: nums=[-5,-4,-3,-2,-1] → [1,4,9,16,25]")
        void testAllNegativeIncreasingTowardZero() {
            assertEquals(Arrays.toString(new int[]{1, 4, 9, 16, 25}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-5, -4, -3, -2, -1})));
        }

        @Test
        @DisplayName("Crosses zero with symmetric duplicates: nums=[-3,-1,0,1,3] → [0,1,1,9,9]")
        void testCrossesZeroWithSymmetricDuplicates() {
            assertEquals(Arrays.toString(new int[]{0, 1, 1, 9, 9}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-3, -1, 0, 1, 3})));
        }

        @Test
        @DisplayName("Max constraint boundary values: nums=[-10000,10000] → [100000000,100000000]")
        void testMaxConstraintBoundaryValues() {
            assertEquals(Arrays.toString(new int[]{100_000_000, 100_000_000}),
                    Arrays.toString(instance.sortedSquaresSorting(new int[]{-10_000, 10_000})));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  sortedSquaresTwoPointer()  — converge from the outside in, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("sortedSquaresTwoPointer() — converge from both ends")
    class SortedSquaresTwoPointerTests {

        @Test
        @DisplayName("LeetCode Example 1: nums=[-4,-1,0,3,10] → [0,1,9,16,100]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[]{0, 1, 9, 16, 100}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-4, -1, 0, 3, 10})));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums=[-7,-3,2,3,11] → [4,9,9,49,121]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[]{4, 9, 9, 49, 121}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-7, -3, 2, 3, 11})));
        }

        @Test
        @DisplayName("Minimum-size input: nums=[-4] → [16]")
        void testMinimumSizeInput() {
            assertEquals(Arrays.toString(new int[]{16}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-4})));
        }

        @Test
        @DisplayName("All-same values: nums=[-2,-2,-2] → [4,4,4]")
        void testAllSameValues() {
            assertEquals(Arrays.toString(new int[]{4, 4, 4}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-2, -2, -2})));
        }

        @Test
        @DisplayName("All non-negative, strictly increasing: nums=[1,2,3,4,5] → [1,4,9,16,25]")
        void testAllNonNegativeStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[]{1, 4, 9, 16, 25}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{1, 2, 3, 4, 5})));
        }

        @Test
        @DisplayName("All negative, increasing toward zero: nums=[-5,-4,-3,-2,-1] → [1,4,9,16,25]")
        void testAllNegativeIncreasingTowardZero() {
            assertEquals(Arrays.toString(new int[]{1, 4, 9, 16, 25}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-5, -4, -3, -2, -1})));
        }

        @Test
        @DisplayName("Crosses zero with symmetric duplicates: nums=[-3,-1,0,1,3] → [0,1,1,9,9]")
        void testCrossesZeroWithSymmetricDuplicates() {
            assertEquals(Arrays.toString(new int[]{0, 1, 1, 9, 9}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-3, -1, 0, 1, 3})));
        }

        @Test
        @DisplayName("Max constraint boundary values: nums=[-10000,10000] → [100000000,100000000]")
        void testMaxConstraintBoundaryValues() {
            assertEquals(Arrays.toString(new int[]{100_000_000, 100_000_000}),
                    Arrays.toString(instance.sortedSquaresTwoPointer(new int[]{-10_000, 10_000})));
        }
    }
}
