package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Find Minimum in Rotated Sorted Array Tests")
class FindMinimumInRotatedSortedArrayTest {

    private FindMinimumInRotatedSortedArray instance;

    @BeforeEach
    void setUp() {
        instance = new FindMinimumInRotatedSortedArray();
    }

    /** Ascending array of n unique values starting at {@code start}, rotated left by {@code shift}. */
    private static int[] rotated(int n, int start, int shift) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = start + (i + shift) % n;
        }
        return arr;
    }

    // ═══════════════════════════════════════════════════════════
    //  findMin()  — binary search, O(log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findMin() — binary search")
    class FindMinTests {

        @Test
        @DisplayName("LeetCode Example 1: [3,4,5,1,2] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.findMin(new int[]{3, 4, 5, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,5,6,7,0,1,2] → 0")
        void testLeetCodeExample2() {
            assertEquals(0, instance.findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [11,13,15,17] → 11 (rotated n times, unchanged)")
        void testLeetCodeExample3() {
            assertEquals(11, instance.findMin(new int[]{11, 13, 15, 17}));
        }

        @Test
        @DisplayName("Scenario: [1] → 1 (single element)")
        void testSingleElement() {
            assertEquals(1, instance.findMin(new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: [2,1] → 1 (two elements, rotated)")
        void testTwoElementsRotated() {
            assertEquals(1, instance.findMin(new int[]{2, 1}));
        }

        @Test
        @DisplayName("Scenario: [1,2] → 1 (two elements, not rotated)")
        void testTwoElementsSorted() {
            assertEquals(1, instance.findMin(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Scenario: [2,3,4,5,1] → 1 (minimum at last index)")
        void testMinAtLastIndex() {
            assertEquals(1, instance.findMin(new int[]{2, 3, 4, 5, 1}));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3,4] → 1 (minimum at index 1)")
        void testMinAtSecondIndex() {
            assertEquals(1, instance.findMin(new int[]{5, 1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5] → 1 (already sorted, strictly increasing)")
        void testAlreadySorted() {
            assertEquals(1, instance.findMin(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Scenario: [0,1,-5,-3,-1] → -5 (negatives, crossing zero)")
        void testNegativesCrossingZero() {
            assertEquals(-5, instance.findMin(new int[]{0, 1, -5, -3, -1}));
        }

        @Test
        @DisplayName("Scenario: [-3,-2,-1,-5,-4] → -5 (all negative)")
        void testAllNegative() {
            assertEquals(-5, instance.findMin(new int[]{-3, -2, -1, -5, -4}));
        }

        @Test
        @DisplayName("Scenario: [5000,-5000] → -5000 (value bounds)")
        void testValueBounds() {
            assertEquals(-5000, instance.findMin(new int[]{5000, -5000}));
        }

        @Test
        @DisplayName("Scenario: [6,7,1,2,3,4,5] → 1 (pivot in the left half)")
        void testPivotInLeftHalf() {
            assertEquals(1, instance.findMin(new int[]{6, 7, 1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Scenario: [3,4,5,6,7,1,2] → 1 (pivot in the right half)")
        void testPivotInRightHalf() {
            assertEquals(1, instance.findMin(new int[]{3, 4, 5, 6, 7, 1, 2}));
        }

        @Test
        @DisplayName("Scenario: n=5000, rotated 1234 → -2500 (max size)")
        void testMaxSizeRotated() {
            assertEquals(-2500, instance.findMin(rotated(5000, -2500, 1234)));
        }

        @Test
        @DisplayName("Scenario: n=5000, rotated 4999 → -2500 (max size, min at last index)")
        void testMaxSizeMinAtEnd() {
            assertEquals(-2500, instance.findMin(rotated(5000, -2500, 4999)));
        }

        @Test
        @DisplayName("Scenario: n=5000, not rotated → -2500 (max size, sorted)")
        void testMaxSizeSorted() {
            assertEquals(-2500, instance.findMin(rotated(5000, -2500, 0)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  findMinByFindingPivot()  — linear scan over doubled array, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findMinByFindingPivot() — linear pivot scan")
    class FindMinByFindingPivotTests {

        @Test
        @DisplayName("LeetCode Example 1: [3,4,5,1,2] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{3, 4, 5, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,5,6,7,0,1,2] → 0")
        void testLeetCodeExample2() {
            assertEquals(0, instance.findMinByFindingPivot(new int[]{4, 5, 6, 7, 0, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [11,13,15,17] → 11 (rotated n times, unchanged)")
        void testLeetCodeExample3() {
            assertEquals(11, instance.findMinByFindingPivot(new int[]{11, 13, 15, 17}));
        }

        @Test
        @DisplayName("Scenario: [1] → 1 (single element)")
        void testSingleElement() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: [2,1] → 1 (two elements, rotated)")
        void testTwoElementsRotated() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{2, 1}));
        }

        @Test
        @DisplayName("Scenario: [1,2] → 1 (two elements, not rotated)")
        void testTwoElementsSorted() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Scenario: [2,3,4,5,1] → 1 (minimum at last index)")
        void testMinAtLastIndex() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{2, 3, 4, 5, 1}));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3,4] → 1 (minimum at index 1)")
        void testMinAtSecondIndex() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{5, 1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5] → 1 (already sorted, strictly increasing)")
        void testAlreadySorted() {
            assertEquals(1, instance.findMinByFindingPivot(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Scenario: [0,1,-5,-3,-1] → -5 (negatives, crossing zero)")
        void testNegativesCrossingZero() {
            assertEquals(-5, instance.findMinByFindingPivot(new int[]{0, 1, -5, -3, -1}));
        }

        @Test
        @DisplayName("Scenario: [-3,-2,-1,-5,-4] → -5 (all negative)")
        void testAllNegative() {
            assertEquals(-5, instance.findMinByFindingPivot(new int[]{-3, -2, -1, -5, -4}));
        }

        @Test
        @DisplayName("Scenario: [5000,-5000] → -5000 (value bounds)")
        void testValueBounds() {
            assertEquals(-5000, instance.findMinByFindingPivot(new int[]{5000, -5000}));
        }

        @Test
        @DisplayName("Scenario: n=5000, rotated 1234 → -2500 (max size)")
        void testMaxSizeRotated() {
            assertEquals(-2500, instance.findMinByFindingPivot(rotated(5000, -2500, 1234)));
        }

        @Test
        @DisplayName("Scenario: n=5000, rotated 4999 → -2500 (max size, min at last index)")
        void testMaxSizeMinAtEnd() {
            assertEquals(-2500, instance.findMinByFindingPivot(rotated(5000, -2500, 4999)));
        }

        @Test
        @DisplayName("Scenario: n=5000, not rotated → -2500 (max size, sorted)")
        void testMaxSizeSorted() {
            assertEquals(-2500, instance.findMinByFindingPivot(rotated(5000, -2500, 0)));
        }
    }
}
