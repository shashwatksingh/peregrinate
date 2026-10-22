package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Split Array Largest Sum Tests")
class SplitArrayLargestSumTest {

    private SplitArrayLargestSum instance;

    @BeforeEach
    void setUp() {
        instance = new SplitArrayLargestSum();
    }

    private int[] filled(int n, int value) {
        int[] nums = new int[n];
        Arrays.fill(nums, value);
        return nums;
    }

    // ═══════════════════════════════════════════════════════════
    //  splitArrayBinarySearch()  — binary search on the answer, O(n log(sum))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("splitArrayBinarySearch() — minimized largest subarray sum over k splits")
    class SplitArrayBinarySearchTests {

        @Test
        @DisplayName("LeetCode Example 1: [7,2,5,10,8], k=2 → 18")
        void testLeetCodeExample1() {
            assertEquals(18, instance.splitArrayBinarySearch(new int[]{7, 2, 5, 10, 8}, 2));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,4,5], k=2 → 9")
        void testLeetCodeExample2() {
            assertEquals(9, instance.splitArrayBinarySearch(new int[]{1, 2, 3, 4, 5}, 2));
        }

        @Test
        @DisplayName("Single element: [5], k=1 → 5")
        void testSingleElement() {
            assertEquals(5, instance.splitArrayBinarySearch(new int[]{5}, 1));
        }

        @Test
        @DisplayName("Single zero element: [0], k=1 → 0")
        void testSingleZero() {
            assertEquals(0, instance.splitArrayBinarySearch(new int[]{0}, 1));
        }

        @Test
        @DisplayName("k=1 (no split): [1,2,3], k=1 → 6 (sum of all)")
        void testKEqualsOne() {
            assertEquals(6, instance.splitArrayBinarySearch(new int[]{1, 2, 3}, 1));
        }

        @Test
        @DisplayName("k=n (every element alone): [10,50,5,1], k=4 → 50 (max element)")
        void testKEqualsLength() {
            assertEquals(50, instance.splitArrayBinarySearch(new int[]{10, 50, 5, 1}, 4));
        }

        @Test
        @DisplayName("Two elements, k=2: [3,9], k=2 → 9")
        void testTwoElementsTwoParts() {
            assertEquals(9, instance.splitArrayBinarySearch(new int[]{3, 9}, 2));
        }

        @Test
        @DisplayName("Uniform values: [4,4,4,4], k=2 → 8")
        void testUniformTwoParts() {
            assertEquals(8, instance.splitArrayBinarySearch(new int[]{4, 4, 4, 4}, 2));
        }

        @Test
        @DisplayName("Uniform values: [4,4,4,4], k=3 → 8 (one part must hold two elements)")
        void testUniformThreeParts() {
            assertEquals(8, instance.splitArrayBinarySearch(new int[]{4, 4, 4, 4}, 3));
        }

        @Test
        @DisplayName("Uniform values: [4,4,4,4], k=4 → 4")
        void testUniformFourParts() {
            assertEquals(4, instance.splitArrayBinarySearch(new int[]{4, 4, 4, 4}, 4));
        }

        @Test
        @DisplayName("All zeros: [0,0,0], k=2 → 0")
        void testAllZeros() {
            assertEquals(0, instance.splitArrayBinarySearch(new int[]{0, 0, 0}, 2));
        }

        @Test
        @DisplayName("Zeros with a single non-zero: [0,0,5,0], k=2 → 5")
        void testZerosWithOneNonZero() {
            assertEquals(5, instance.splitArrayBinarySearch(new int[]{0, 0, 5, 0}, 2));
        }

        @Test
        @DisplayName("Heaviest element first: [10,1,1,1], k=2 → 10")
        void testHeaviestAtStart() {
            assertEquals(10, instance.splitArrayBinarySearch(new int[]{10, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Heaviest element last: [1,1,1,10], k=2 → 10")
        void testHeaviestAtEnd() {
            assertEquals(10, instance.splitArrayBinarySearch(new int[]{1, 1, 1, 10}, 2));
        }

        @Test
        @DisplayName("Increasing values: [1,2,3,4,5], k=3 → 6")
        void testIncreasingThreeParts() {
            assertEquals(6, instance.splitArrayBinarySearch(new int[]{1, 2, 3, 4, 5}, 3));
        }

        @Test
        @DisplayName("Increasing values: [1,2,3,4,5], k=4 → 5")
        void testIncreasingFourParts() {
            assertEquals(5, instance.splitArrayBinarySearch(new int[]{1, 2, 3, 4, 5}, 4));
        }

        @Test
        @DisplayName("Same input as Example 1 with more parts: [7,2,5,10,8], k=3 → 14")
        void testExample1ThreeParts() {
            assertEquals(14, instance.splitArrayBinarySearch(new int[]{7, 2, 5, 10, 8}, 3));
        }

        @Test
        @DisplayName("Exactly one pair must merge: [2,3,1,2,4,3], k=5 → 4")
        void testOnePairMerges() {
            assertEquals(4, instance.splitArrayBinarySearch(new int[]{2, 3, 1, 2, 4, 3}, 5));
        }

        @Test
        @DisplayName("Duplicated max: [1,4,4], k=3 → 4")
        void testDuplicatedMax() {
            assertEquals(4, instance.splitArrayBinarySearch(new int[]{1, 4, 4}, 3));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 all 10^6, k=1 → 1000000000 (sum near int limit)")
        void testMaxBoundarySingleSplit() {
            assertEquals(1000000000, instance.splitArrayBinarySearch(filled(1000, 1000000), 1));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 all 10^6, k=50 → 20000000")
        void testMaxBoundaryMaxK() {
            assertEquals(20000000, instance.splitArrayBinarySearch(filled(1000, 1000000), 50));
        }

        @Test
        @DisplayName("Max constraint boundary: n=50 all 10^6, k=50 → 1000000")
        void testMaxBoundaryKEqualsLength() {
            assertEquals(1000000, instance.splitArrayBinarySearch(filled(50, 1000000), 50));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 all 1, k=3 → 334")
        void testMaxBoundaryUnevenDivision() {
            assertEquals(334, instance.splitArrayBinarySearch(filled(1000, 1), 3));
        }

        @Test
        @DisplayName("Max constraint boundary: n=1000 all zeros, k=50 → 0")
        void testMaxBoundaryAllZeros() {
            assertEquals(0, instance.splitArrayBinarySearch(new int[1000], 50));
        }
    }
}
