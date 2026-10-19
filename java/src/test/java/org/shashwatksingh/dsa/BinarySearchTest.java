package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Binary Search Tests")
class BinarySearchTest {

    private BinarySearch instance;

    @BeforeEach
    void setUp() {
        instance = new BinarySearch();
    }

    // ═══════════════════════════════════════════════════════════
    //  search()  — iterative binary search, O(log n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("search() — iterative binary search")
    class SearchTests {

        @Test
        @DisplayName("LeetCode Example 1: [-1,0,3,5,9,12], target=9 → 4")
        void testLeetCodeExample1() {
            assertEquals(4, instance.search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        }

        @Test
        @DisplayName("LeetCode Example 2: [-1,0,3,5,9,12], target=2 → -1")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
        }

        @Test
        @DisplayName("Single element, match: [5], target=5 → 0")
        void testSingleElementMatch() {
            assertEquals(0, instance.search(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Single element, no match: [5], target=3 → -1")
        void testSingleElementNoMatch() {
            assertEquals(-1, instance.search(new int[]{5}, 3));
        }

        @Test
        @DisplayName("Target is the first element: [1,3,5,7,9], target=1 → 0")
        void testTargetFirst() {
            assertEquals(0, instance.search(new int[]{1, 3, 5, 7, 9}, 1));
        }

        @Test
        @DisplayName("Target is the last element: [1,3,5,7,9], target=9 → 4")
        void testTargetLast() {
            assertEquals(4, instance.search(new int[]{1, 3, 5, 7, 9}, 9));
        }

        @Test
        @DisplayName("Target is the middle element: [1,3,5,7,9], target=5 → 2")
        void testTargetMiddle() {
            assertEquals(2, instance.search(new int[]{1, 3, 5, 7, 9}, 5));
        }

        @Test
        @DisplayName("Two elements, target is the first: [1,2], target=1 → 0")
        void testTwoElementsFirst() {
            assertEquals(0, instance.search(new int[]{1, 2}, 1));
        }

        @Test
        @DisplayName("Two elements, target is the second: [1,2], target=2 → 1")
        void testTwoElementsSecond() {
            assertEquals(1, instance.search(new int[]{1, 2}, 2));
        }

        @Test
        @DisplayName("Two elements, target above both: [1,2], target=3 → -1")
        void testTwoElementsAbove() {
            assertEquals(-1, instance.search(new int[]{1, 2}, 3));
        }

        @Test
        @DisplayName("Two elements, target below both: [1,2], target=0 → -1")
        void testTwoElementsBelow() {
            assertEquals(-1, instance.search(new int[]{1, 2}, 0));
        }

        @Test
        @DisplayName("Three elements, target is the last: [1,2,3], target=3 → 2")
        void testThreeElementsLast() {
            assertEquals(2, instance.search(new int[]{1, 2, 3}, 3));
        }

        @Test
        @DisplayName("Even length, target left of centre: [1,3,5,7], target=3 → 1")
        void testEvenLengthLeft() {
            assertEquals(1, instance.search(new int[]{1, 3, 5, 7}, 3));
        }

        @Test
        @DisplayName("Even length, target right of centre: [1,3,5,7], target=5 → 2")
        void testEvenLengthRight() {
            assertEquals(2, instance.search(new int[]{1, 3, 5, 7}, 5));
        }

        @Test
        @DisplayName("Even length, target is the last: [1,3,5,7], target=7 → 3")
        void testEvenLengthLast() {
            assertEquals(3, instance.search(new int[]{1, 3, 5, 7}, 7));
        }

        @Test
        @DisplayName("Target below every element: [1,3,5,7], target=0 → -1")
        void testTargetBelowAll() {
            assertEquals(-1, instance.search(new int[]{1, 3, 5, 7}, 0));
        }

        @Test
        @DisplayName("Target above every element: [1,3,5,7], target=8 → -1")
        void testTargetAboveAll() {
            assertEquals(-1, instance.search(new int[]{1, 3, 5, 7}, 8));
        }

        @Test
        @DisplayName("Target absent between two elements: [1,3,5,7], target=4 → -1")
        void testTargetAbsentBetween() {
            assertEquals(-1, instance.search(new int[]{1, 3, 5, 7}, 4));
        }

        @Test
        @DisplayName("All-negative values, match: [-9,-5,-3,-1], target=-5 → 1")
        void testNegativeMatch() {
            assertEquals(1, instance.search(new int[]{-9, -5, -3, -1}, -5));
        }

        @Test
        @DisplayName("All-negative values, no match: [-9,-5,-3,-1], target=-4 → -1")
        void testNegativeNoMatch() {
            assertEquals(-1, instance.search(new int[]{-9, -5, -3, -1}, -4));
        }

        @Test
        @DisplayName("Value boundary -9999 is the first element: [-9999,0,9999], target=-9999 → 0")
        void testBoundaryLow() {
            assertEquals(0, instance.search(new int[]{-9999, 0, 9999}, -9999));
        }

        @Test
        @DisplayName("Value boundary 9999 is the last element: [-9999,0,9999], target=9999 → 2")
        void testBoundaryHigh() {
            assertEquals(2, instance.search(new int[]{-9999, 0, 9999}, 9999));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): first element -5000 → 0")
        void testMaxSizeFirst() {
            assertEquals(0, instance.search(sequence(10000, -5000), -5000));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): second element -4999 → 1")
        void testMaxSizeSecond() {
            assertEquals(1, instance.search(sequence(10000, -5000), -4999));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): middle element 0 → 5000")
        void testMaxSizeMiddle() {
            assertEquals(5000, instance.search(sequence(10000, -5000), 0));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): second to last 4998 → 9998")
        void testMaxSizeSecondToLast() {
            assertEquals(9998, instance.search(sequence(10000, -5000), 4998));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): last element 4999 → 9999")
        void testMaxSizeLast() {
            assertEquals(9999, instance.search(sequence(10000, -5000), 4999));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): 5000 is above every element → -1")
        void testMaxSizeAbove() {
            assertEquals(-1, instance.search(sequence(10000, -5000), 5000));
        }

        @Test
        @DisplayName("Max size (n=10000, -5000..4999): -5001 is below every element → -1")
        void testMaxSizeBelow() {
            assertEquals(-1, instance.search(sequence(10000, -5000), -5001));
        }
    }

    /** Returns {@code length} consecutive ascending values starting at {@code start}. */
    private static int[] sequence(int length, int start) {
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = start + i;
        }
        return nums;
    }
}
