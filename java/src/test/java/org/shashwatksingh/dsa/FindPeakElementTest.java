package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Find Peak Element Tests")
class FindPeakElementTest {

    private FindPeakElement instance;

    @BeforeEach
    void setUp() {
        instance = new FindPeakElement();
    }

    /** True if idx is a valid index and nums[idx] is strictly greater than both neighbors (outside = -∞). */
    private boolean isPeakIndex(int[] nums, int idx) {
        if (idx < 0 || idx >= nums.length) return false;
        boolean leftOk = idx == 0 || nums[idx] > nums[idx - 1];
        boolean rightOk = idx == nums.length - 1 || nums[idx] > nums[idx + 1];
        return leftOk && rightOk;
    }

    // ═══════════════════════════════════════════════════════════
    //  findPeakElementBruteForce()  — returns the index of any peak, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findPeakElementBruteForce() — index of a peak element")
    class FindPeakElementBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.findPeakElementBruteForce(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,1,3,5,6,4] → index 1 or 5 (any peak is valid)")
        void testLeetCodeExample2() {
            int[] nums = {1, 2, 1, 3, 5, 6, 4};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElementBruteForce(nums)));
        }

        @Test
        @DisplayName("Single element: [1] → 0")
        void testSingleElement() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{1}));
        }

        @Test
        @DisplayName("Single element, nonzero value: [7] → 0 (index, not value)")
        void testSingleElementNonZeroValue() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{7}));
        }

        @Test
        @DisplayName("Two elements ascending: [1,2] → 1")
        void testTwoElementsAscending() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Two elements descending: [2,1] → 0")
        void testTwoElementsDescending() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{2, 1}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 4 (peak at last index)")
        void testStrictlyIncreasing() {
            assertEquals(4, instance.findPeakElementBruteForce(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 0 (peak at first index)")
        void testStrictlyDecreasing() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Peak in the middle: [1,3,2] → 1")
        void testPeakInMiddle() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{1, 3, 2}));
        }

        @Test
        @DisplayName("All negative values: [-3,-1,-2] → 1")
        void testAllNegative() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{-3, -1, -2}));
        }

        @Test
        @DisplayName("Negative two elements: [-1,-2] → 0")
        void testNegativeTwoElements() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{-1, -2}));
        }

        @Test
        @DisplayName("Int boundary values: [MIN,MAX,MIN] → 1")
        void testIntBoundaryMiddle() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        @DisplayName("Int boundary values: [MAX,MIN] → 0")
        void testIntBoundaryFirst() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        @DisplayName("Int boundary values: [MIN,MAX] → 1")
        void testIntBoundaryLast() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
        }

        @Test
        @DisplayName("Zigzag with several peaks: [1,3,2,4,3,5,1] → any of indices 1, 3, 5")
        void testZigzagMultiplePeaks() {
            int[] nums = {1, 3, 2, 4, 3, 5, 1};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElementBruteForce(nums)));
        }

        @Test
        @DisplayName("Peaks at both ends: [9,1,2,8] → index 0 or 3")
        void testPeaksAtBothEnds() {
            int[] nums = {9, 1, 2, 8};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElementBruteForce(nums)));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 strictly increasing → 999")
        void testMaxBoundaryIncreasing() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = i;
            assertEquals(999, instance.findPeakElementBruteForce(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 strictly decreasing → 0")
        void testMaxBoundaryDecreasing() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = 1000 - i;
            assertEquals(0, instance.findPeakElementBruteForce(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 mountain, single peak at index 500 → 500")
        void testMaxBoundaryMountain() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = i <= 500 ? i : 1000 - i;
            assertEquals(500, instance.findPeakElementBruteForce(nums));
        }

        @Test
        @DisplayName("Single element at int minimum: [MIN] → 0")
        void testSingleElementIntMin() {
            assertEquals(0, instance.findPeakElementBruteForce(new int[]{Integer.MIN_VALUE}));
        }

        @Test
        @DisplayName("Int minimum at the start: [MIN,MIN+1] → 1")
        void testIntMinAtStart() {
            assertEquals(1, instance.findPeakElementBruteForce(new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE + 1}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  findPeakElement()  — returns the index of any peak, O(log n) binary search
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findPeakElement() — index of a peak element, O(log n)")
    class FindPeakElementTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.findPeakElement(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,1,3,5,6,4] → index 1 or 5 (any peak is valid)")
        void testLeetCodeExample2() {
            int[] nums = {1, 2, 1, 3, 5, 6, 4};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElement(nums)));
        }

        @Test
        @DisplayName("Single element: [1] → 0")
        void testSingleElement() {
            assertEquals(0, instance.findPeakElement(new int[]{1}));
        }

        @Test
        @DisplayName("Single element, nonzero value: [7] → 0 (index, not value)")
        void testSingleElementNonZeroValue() {
            assertEquals(0, instance.findPeakElement(new int[]{7}));
        }

        @Test
        @DisplayName("Two elements ascending: [1,2] → 1")
        void testTwoElementsAscending() {
            assertEquals(1, instance.findPeakElement(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Two elements descending: [2,1] → 0")
        void testTwoElementsDescending() {
            assertEquals(0, instance.findPeakElement(new int[]{2, 1}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 4 (peak at last index)")
        void testStrictlyIncreasing() {
            assertEquals(4, instance.findPeakElement(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 0 (peak at first index)")
        void testStrictlyDecreasing() {
            assertEquals(0, instance.findPeakElement(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Peak in the middle: [1,3,2] → 1")
        void testPeakInMiddle() {
            assertEquals(1, instance.findPeakElement(new int[]{1, 3, 2}));
        }

        @Test
        @DisplayName("All negative values: [-3,-1,-2] → 1")
        void testAllNegative() {
            assertEquals(1, instance.findPeakElement(new int[]{-3, -1, -2}));
        }

        @Test
        @DisplayName("Negative two elements: [-1,-2] → 0")
        void testNegativeTwoElements() {
            assertEquals(0, instance.findPeakElement(new int[]{-1, -2}));
        }

        @Test
        @DisplayName("Int boundary values: [MIN,MAX,MIN] → 1")
        void testIntBoundaryMiddle() {
            assertEquals(1, instance.findPeakElement(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        @DisplayName("Int boundary values: [MAX,MIN] → 0")
        void testIntBoundaryFirst() {
            assertEquals(0, instance.findPeakElement(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE}));
        }

        @Test
        @DisplayName("Int boundary values: [MIN,MAX] → 1")
        void testIntBoundaryLast() {
            assertEquals(1, instance.findPeakElement(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}));
        }

        @Test
        @DisplayName("Zigzag with several peaks: [1,3,2,4,3,5,1] → any of indices 1, 3, 5")
        void testZigzagMultiplePeaks() {
            int[] nums = {1, 3, 2, 4, 3, 5, 1};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElement(nums)));
        }

        @Test
        @DisplayName("Peaks at both ends: [9,1,2,8] → index 0 or 3")
        void testPeaksAtBothEnds() {
            int[] nums = {9, 1, 2, 8};
            assertEquals(true, isPeakIndex(nums, instance.findPeakElement(nums)));
        }

        @Test
        @DisplayName("Peak at index 1 of four: [1,5,3,2] → 1")
        void testPeakNearStart() {
            assertEquals(1, instance.findPeakElement(new int[]{1, 5, 3, 2}));
        }

        @Test
        @DisplayName("Peak at second-to-last index: [1,2,3,9,4] → 3")
        void testPeakNearEnd() {
            assertEquals(3, instance.findPeakElement(new int[]{1, 2, 3, 9, 4}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 strictly increasing → 999")
        void testMaxBoundaryIncreasing() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = i;
            assertEquals(999, instance.findPeakElement(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 strictly decreasing → 0")
        void testMaxBoundaryDecreasing() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = 1000 - i;
            assertEquals(0, instance.findPeakElement(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 mountain, single peak at index 500 → 500")
        void testMaxBoundaryMountain() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = i <= 500 ? i : 1000 - i;
            assertEquals(500, instance.findPeakElement(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 zigzag (even indices high) → any peak index")
        void testMaxBoundaryZigzag() {
            int[] nums = new int[1000];
            for (int i = 0; i < nums.length; i++) nums[i] = i % 2 == 0 ? 10 + i : i;
            assertEquals(true, isPeakIndex(nums, instance.findPeakElement(nums)));
        }
    }
}
