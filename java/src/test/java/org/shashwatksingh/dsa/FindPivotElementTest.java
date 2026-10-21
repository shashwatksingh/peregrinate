package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Find Pivot Index Tests")
class FindPivotElementTest {

    private FindPivotElement instance;

    @BeforeEach
    void setUp() {
        instance = new FindPivotElement();
    }

    // ═══════════════════════════════════════════════════════════
    //  pivotIndex()  — prefix sum vs. remaining sum, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("pivotIndex() — leftmost index where left sum equals right sum")
    class PivotIndexTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,7,3,6,5,6] → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3] → -1")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.pivotIndex(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [2,1,-1] → 0")
        void testLeetCodeExample3() {
            assertEquals(0, instance.pivotIndex(new int[]{2, 1, -1}));
        }

        @Test
        @DisplayName("Single element, positive: [5] → 0 (both sides empty)")
        void testSingleElementPositive() {
            assertEquals(0, instance.pivotIndex(new int[]{5}));
        }

        @Test
        @DisplayName("Single element, negative: [-5] → 0")
        void testSingleElementNegative() {
            assertEquals(0, instance.pivotIndex(new int[]{-5}));
        }

        @Test
        @DisplayName("Single element, zero: [0] → 0")
        void testSingleElementZero() {
            assertEquals(0, instance.pivotIndex(new int[]{0}));
        }

        @Test
        @DisplayName("Two equal non-zero elements: [1,1] → -1")
        void testTwoEqualElements() {
            assertEquals(-1, instance.pivotIndex(new int[]{1, 1}));
        }

        @Test
        @DisplayName("Two elements, zero second: [1,0] → 0")
        void testTwoElementsZeroSecond() {
            assertEquals(0, instance.pivotIndex(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Two elements, zero first: [0,1] → 1")
        void testTwoElementsZeroFirst() {
            assertEquals(1, instance.pivotIndex(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Uniform values, odd length: [2,2,2] → 1")
        void testUniformOddLength() {
            assertEquals(1, instance.pivotIndex(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Uniform values, even length: [3,3,3,3] → -1")
        void testUniformEvenLength() {
            assertEquals(-1, instance.pivotIndex(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0,0] → 0 (leftmost of many pivots)")
        void testAllZeros() {
            assertEquals(0, instance.pivotIndex(new int[]{0, 0, 0, 0}));
        }

        @Test
        @DisplayName("Pivot at last index: [1,-1,5] → 2 (left sum 0)")
        void testPivotAtLastIndex() {
            assertEquals(2, instance.pivotIndex(new int[]{1, -1, 5}));
        }

        @Test
        @DisplayName("Pivot at first index: [0,1,-1] → 0")
        void testPivotAtFirstIndex() {
            assertEquals(0, instance.pivotIndex(new int[]{0, 1, -1}));
        }

        @Test
        @DisplayName("Pivot with zero in the middle: [1,0,1] → 1")
        void testZeroInMiddle() {
            assertEquals(1, instance.pivotIndex(new int[]{1, 0, 1}));
        }

        @Test
        @DisplayName("Multiple pivots, leftmost wins: [-1,1,0,0] → 2")
        void testMultiplePivotsLeftmost() {
            assertEquals(2, instance.pivotIndex(new int[]{-1, 1, 0, 0}));
        }

        @Test
        @DisplayName("All negative values: [-1,-1,-4,-1,-1] → 2")
        void testAllNegative() {
            assertEquals(2, instance.pivotIndex(new int[]{-1, -1, -4, -1, -1}));
        }

        @Test
        @DisplayName("Mixed signs, no pivot: [1,-1,2,-2,3,1] → -1")
        void testMixedSignsNoPivot() {
            assertEquals(-1, instance.pivotIndex(new int[]{1, -1, 2, -2, 3, 1}));
        }

        @Test
        @DisplayName("Value constraint boundaries: [1000,-1000,1000] → 0 (right sum is 0)")
        void testValueBoundariesFirstIndex() {
            assertEquals(0, instance.pivotIndex(new int[]{1000, -1000, 1000}));
        }

        @Test
        @DisplayName("Value constraint boundaries: [1000,1000,2000,1000,1000] → 2")
        void testValueBoundariesPivot() {
            assertEquals(2, instance.pivotIndex(new int[]{1000, 1000, 2000, 1000, 1000}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=10000 all zeros → 0")
        void testMaxBoundaryAllZeros() {
            assertEquals(0, instance.pivotIndex(new int[10000]));
        }

        @Test
        @DisplayName("Max constraint boundary: n=10000 all 1000 → -1")
        void testMaxBoundaryAllMax() {
            int[] nums = new int[10000];
            Arrays.fill(nums, 1000);
            assertEquals(-1, instance.pivotIndex(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=9999 all 1 → 4999 (exact center)")
        void testMaxBoundaryOddCenter() {
            int[] nums = new int[9999];
            Arrays.fill(nums, 1);
            assertEquals(4999, instance.pivotIndex(nums));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  pivotIndexPrefixSumArray()  — prefix sum array lookup, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("pivotIndexPrefixSumArray() — leftmost index where left sum equals right sum")
    class PivotIndexPrefixSumArrayTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,7,3,6,5,6] → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.pivotIndexPrefixSumArray(new int[]{1, 7, 3, 6, 5, 6}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3] → -1")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.pivotIndexPrefixSumArray(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [2,1,-1] → 0")
        void testLeetCodeExample3() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{2, 1, -1}));
        }

        @Test
        @DisplayName("Single element, positive: [5] → 0 (both sides empty)")
        void testSingleElementPositive() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{5}));
        }

        @Test
        @DisplayName("Single element, negative: [-5] → 0")
        void testSingleElementNegative() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{-5}));
        }

        @Test
        @DisplayName("Single element, zero: [0] → 0")
        void testSingleElementZero() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{0}));
        }

        @Test
        @DisplayName("Two equal non-zero elements: [1,1] → -1")
        void testTwoEqualElements() {
            assertEquals(-1, instance.pivotIndexPrefixSumArray(new int[]{1, 1}));
        }

        @Test
        @DisplayName("Two elements, zero second: [1,0] → 0")
        void testTwoElementsZeroSecond() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Two elements, zero first: [0,1] → 1")
        void testTwoElementsZeroFirst() {
            assertEquals(1, instance.pivotIndexPrefixSumArray(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Uniform values, odd length: [2,2,2] → 1")
        void testUniformOddLength() {
            assertEquals(1, instance.pivotIndexPrefixSumArray(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Uniform values, even length: [3,3,3,3] → -1")
        void testUniformEvenLength() {
            assertEquals(-1, instance.pivotIndexPrefixSumArray(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0,0] → 0 (leftmost of many pivots)")
        void testAllZeros() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{0, 0, 0, 0}));
        }

        @Test
        @DisplayName("Pivot at last index: [1,-1,5] → 2 (left sum 0)")
        void testPivotAtLastIndex() {
            assertEquals(2, instance.pivotIndexPrefixSumArray(new int[]{1, -1, 5}));
        }

        @Test
        @DisplayName("Pivot at first index: [0,1,-1] → 0")
        void testPivotAtFirstIndex() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{0, 1, -1}));
        }

        @Test
        @DisplayName("Pivot with zero in the middle: [1,0,1] → 1")
        void testZeroInMiddle() {
            assertEquals(1, instance.pivotIndexPrefixSumArray(new int[]{1, 0, 1}));
        }

        @Test
        @DisplayName("Multiple pivots, leftmost wins: [-1,1,0,0] → 2")
        void testMultiplePivotsLeftmost() {
            assertEquals(2, instance.pivotIndexPrefixSumArray(new int[]{-1, 1, 0, 0}));
        }

        @Test
        @DisplayName("All negative values: [-1,-1,-4,-1,-1] → 2")
        void testAllNegative() {
            assertEquals(2, instance.pivotIndexPrefixSumArray(new int[]{-1, -1, -4, -1, -1}));
        }

        @Test
        @DisplayName("Mixed signs, no pivot: [1,-1,2,-2,3,1] → -1")
        void testMixedSignsNoPivot() {
            assertEquals(-1, instance.pivotIndexPrefixSumArray(new int[]{1, -1, 2, -2, 3, 1}));
        }

        @Test
        @DisplayName("Value constraint boundaries: [1000,-1000,1000] → 0 (right sum is 0)")
        void testValueBoundariesFirstIndex() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[]{1000, -1000, 1000}));
        }

        @Test
        @DisplayName("Value constraint boundaries: [1000,1000,2000,1000,1000] → 2")
        void testValueBoundariesPivot() {
            assertEquals(2, instance.pivotIndexPrefixSumArray(new int[]{1000, 1000, 2000, 1000, 1000}));
        }

        @Test
        @DisplayName("Max constraint boundary: n=10000 all zeros → 0")
        void testMaxBoundaryAllZeros() {
            assertEquals(0, instance.pivotIndexPrefixSumArray(new int[10000]));
        }

        @Test
        @DisplayName("Max constraint boundary: n=10000 all 1000 → -1")
        void testMaxBoundaryAllMax() {
            int[] nums = new int[10000];
            Arrays.fill(nums, 1000);
            assertEquals(-1, instance.pivotIndexPrefixSumArray(nums));
        }

        @Test
        @DisplayName("Max constraint boundary: n=9999 all 1 → 4999 (exact center)")
        void testMaxBoundaryOddCenter() {
            int[] nums = new int[9999];
            Arrays.fill(nums, 1);
            assertEquals(4999, instance.pivotIndexPrefixSumArray(nums));
        }
    }
}
