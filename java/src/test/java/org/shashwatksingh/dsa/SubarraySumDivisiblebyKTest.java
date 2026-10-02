package org.shashwatksingh.dsa;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Subarray Sums Divisible by K Tests")
class SubarraySumDivisiblebyKTest {

    private SubarraySumDivisiblebyK instance;

    @BeforeEach
    void setUp() {
        instance = new SubarraySumDivisiblebyK();
    }

    private static int[] ones(int length) {
        int[] arr = new int[length];
        Arrays.fill(arr, 1);
        return arr;
    }

    // ═══════════════════════════════════════════════════════════
    //  subarraysDivByKPrefixArray()  — O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("subarraysDivByKPrefixArray() — prefix array with nested loop, O(n^2)")
    class BruteForcePrefixArrayTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,0,-2,-3,1], k=5 → 7")
        void testLeetCodeExample1() {
            assertEquals(7, instance.subarraysDivByKPrefixArray(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5], k=9 → 0")
        void testLeetCodeExample2() {
            assertEquals(0, instance.subarraysDivByKPrefixArray(new int[] { 5 }, 9));
        }

        @Test
        @DisplayName("Scenario: [5], k=5 → 1 (single element divisible)")
        void testSingleElementDivisible() {
            assertEquals(1, instance.subarraysDivByKPrefixArray(new int[] { 5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0], k=2 → 1 (single zero)")
        void testSingleZero() {
            assertEquals(1, instance.subarraysDivByKPrefixArray(new int[] { 0 }, 2));
        }

        @Test
        @DisplayName("Scenario: [3], k=2 → 0 (single element not divisible)")
        void testSingleElementNotDivisible() {
            assertEquals(0, instance.subarraysDivByKPrefixArray(new int[] { 3 }, 2));
        }

        @Test
        @DisplayName("Scenario: [-5], k=5 → 1 (single negative divisible)")
        void testSingleNegativeDivisible() {
            assertEquals(1, instance.subarraysDivByKPrefixArray(new int[] { -5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0,0,0,0], k=3 → 10 (all zeros, every subarray)")
        void testAllZeros() {
            assertEquals(10, instance.subarraysDivByKPrefixArray(new int[] { 0, 0, 0, 0 }, 3));
        }

        @Test
        @DisplayName("Scenario: [1,1,1,1], k=2 → 4 (uniform, even-length subarrays)")
        void testUniformValues() {
            assertEquals(4, instance.subarraysDivByKPrefixArray(new int[] { 1, 1, 1, 1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=3 → 3 (strictly increasing)")
        void testStrictlyIncreasing() {
            assertEquals(3, instance.subarraysDivByKPrefixArray(new int[] { 1, 2, 3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [3,2,1], k=3 → 3 (strictly decreasing)")
        void testStrictlyDecreasing() {
            assertEquals(3, instance.subarraysDivByKPrefixArray(new int[] { 3, 2, 1 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-2,-3], k=3 → 3 (all negative)")
        void testAllNegative() {
            assertEquals(3, instance.subarraysDivByKPrefixArray(new int[] { -1, -2, -3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-1], k=2 → 1 (negative prefix sums)")
        void testNegativePrefixSums() {
            assertEquals(1, instance.subarraysDivByKPrefixArray(new int[] { -1, -1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [2,-2,2,-2], k=4 → 4 (running remainder resets repeatedly)")
        void testRemainderResets() {
            assertEquals(4, instance.subarraysDivByKPrefixArray(new int[] { 2, -2, 2, -2 }, 4));
        }

        @Test
        @DisplayName("Scenario: [1,1,1], k=5 → 0 (no valid subarray)")
        void testNoValidSubarray() {
            assertEquals(0, instance.subarraysDivByKPrefixArray(new int[] { 1, 1, 1 }, 5));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=10000 → 0 (k larger than every sum)")
        void testKLargerThanSums() {
            assertEquals(0, instance.subarraysDivByKPrefixArray(new int[] { 1, 2, 3 }, 10000));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000,10000], k=10000 → 6 (max magnitude values)")
        void testMaxMagnitudeValues() {
            assertEquals(6, instance.subarraysDivByKPrefixArray(new int[] { 10000, -10000, 10000 }, 10000));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  subarraysDivByKMapSolution()  — O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("subarraysDivByKMapSolution() — prefix remainder HashMap, O(n)")
    class MapSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,0,-2,-3,1], k=5 → 7")
        void testLeetCodeExample1() {
            assertEquals(7, instance.subarraysDivByKMapSolution(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5], k=9 → 0")
        void testLeetCodeExample2() {
            assertEquals(0, instance.subarraysDivByKMapSolution(new int[] { 5 }, 9));
        }

        @Test
        @DisplayName("Scenario: [5], k=5 → 1 (single element divisible)")
        void testSingleElementDivisible() {
            assertEquals(1, instance.subarraysDivByKMapSolution(new int[] { 5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0], k=2 → 1 (single zero)")
        void testSingleZero() {
            assertEquals(1, instance.subarraysDivByKMapSolution(new int[] { 0 }, 2));
        }

        @Test
        @DisplayName("Scenario: [3], k=2 → 0 (single element not divisible)")
        void testSingleElementNotDivisible() {
            assertEquals(0, instance.subarraysDivByKMapSolution(new int[] { 3 }, 2));
        }

        @Test
        @DisplayName("Scenario: [-5], k=5 → 1 (single negative divisible)")
        void testSingleNegativeDivisible() {
            assertEquals(1, instance.subarraysDivByKMapSolution(new int[] { -5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0,0,0,0], k=3 → 10 (all zeros, every subarray)")
        void testAllZeros() {
            assertEquals(10, instance.subarraysDivByKMapSolution(new int[] { 0, 0, 0, 0 }, 3));
        }

        @Test
        @DisplayName("Scenario: [1,1,1,1], k=2 → 4 (uniform, even-length subarrays)")
        void testUniformValues() {
            assertEquals(4, instance.subarraysDivByKMapSolution(new int[] { 1, 1, 1, 1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=3 → 3 (strictly increasing)")
        void testStrictlyIncreasing() {
            assertEquals(3, instance.subarraysDivByKMapSolution(new int[] { 1, 2, 3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [3,2,1], k=3 → 3 (strictly decreasing)")
        void testStrictlyDecreasing() {
            assertEquals(3, instance.subarraysDivByKMapSolution(new int[] { 3, 2, 1 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-2,-3], k=3 → 3 (all negative)")
        void testAllNegative() {
            assertEquals(3, instance.subarraysDivByKMapSolution(new int[] { -1, -2, -3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-1], k=2 → 1 (negative prefix sums)")
        void testNegativePrefixSums() {
            assertEquals(1, instance.subarraysDivByKMapSolution(new int[] { -1, -1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [2,-2,2,-2], k=4 → 4 (running remainder resets repeatedly)")
        void testRemainderResets() {
            assertEquals(4, instance.subarraysDivByKMapSolution(new int[] { 2, -2, 2, -2 }, 4));
        }

        @Test
        @DisplayName("Scenario: [1,1,1], k=5 → 0 (no valid subarray)")
        void testNoValidSubarray() {
            assertEquals(0, instance.subarraysDivByKMapSolution(new int[] { 1, 1, 1 }, 5));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=10000 → 0 (k larger than every sum)")
        void testKLargerThanSums() {
            assertEquals(0, instance.subarraysDivByKMapSolution(new int[] { 1, 2, 3 }, 10000));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000,10000], k=10000 → 6 (max magnitude values)")
        void testMaxMagnitudeValues() {
            assertEquals(6, instance.subarraysDivByKMapSolution(new int[] { 10000, -10000, 10000 }, 10000));
        }

        @Test
        @DisplayName("Scenario: 30,000 zeros, k=2 → 450,015,000 (max length, all zeros)")
        void testMaxLengthZeros() {
            assertEquals(450015000, instance.subarraysDivByKMapSolution(new int[30_000], 2));
        }

        @Test
        @DisplayName("Scenario: 30,000 ones, k=2 → 225,000,000 (max length, alternating remainders)")
        void testMaxLengthAlternating() {
            assertEquals(225000000, instance.subarraysDivByKMapSolution(ones(30_000), 2));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  subarraysDivByKArraySolution()  — O(n + k) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("subarraysDivByKArraySolution() — prefix remainder int[k] array, O(n + k)")
    class ArraySolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,0,-2,-3,1], k=5 → 7")
        void testLeetCodeExample1() {
            assertEquals(7, instance.subarraysDivByKArraySolution(new int[] { 4, 5, 0, -2, -3, 1 }, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5], k=9 → 0")
        void testLeetCodeExample2() {
            assertEquals(0, instance.subarraysDivByKArraySolution(new int[] { 5 }, 9));
        }

        @Test
        @DisplayName("Scenario: [5], k=5 → 1 (single element divisible)")
        void testSingleElementDivisible() {
            assertEquals(1, instance.subarraysDivByKArraySolution(new int[] { 5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0], k=2 → 1 (single zero)")
        void testSingleZero() {
            assertEquals(1, instance.subarraysDivByKArraySolution(new int[] { 0 }, 2));
        }

        @Test
        @DisplayName("Scenario: [3], k=2 → 0 (single element not divisible)")
        void testSingleElementNotDivisible() {
            assertEquals(0, instance.subarraysDivByKArraySolution(new int[] { 3 }, 2));
        }

        @Test
        @DisplayName("Scenario: [-5], k=5 → 1 (single negative divisible)")
        void testSingleNegativeDivisible() {
            assertEquals(1, instance.subarraysDivByKArraySolution(new int[] { -5 }, 5));
        }

        @Test
        @DisplayName("Scenario: [0,0,0,0], k=3 → 10 (all zeros, every subarray)")
        void testAllZeros() {
            assertEquals(10, instance.subarraysDivByKArraySolution(new int[] { 0, 0, 0, 0 }, 3));
        }

        @Test
        @DisplayName("Scenario: [1,1,1,1], k=2 → 4 (uniform, even-length subarrays)")
        void testUniformValues() {
            assertEquals(4, instance.subarraysDivByKArraySolution(new int[] { 1, 1, 1, 1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=3 → 3 (strictly increasing)")
        void testStrictlyIncreasing() {
            assertEquals(3, instance.subarraysDivByKArraySolution(new int[] { 1, 2, 3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [3,2,1], k=3 → 3 (strictly decreasing)")
        void testStrictlyDecreasing() {
            assertEquals(3, instance.subarraysDivByKArraySolution(new int[] { 3, 2, 1 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-2,-3], k=3 → 3 (all negative)")
        void testAllNegative() {
            assertEquals(3, instance.subarraysDivByKArraySolution(new int[] { -1, -2, -3 }, 3));
        }

        @Test
        @DisplayName("Scenario: [-1,-1], k=2 → 1 (negative prefix sums)")
        void testNegativePrefixSums() {
            assertEquals(1, instance.subarraysDivByKArraySolution(new int[] { -1, -1 }, 2));
        }

        @Test
        @DisplayName("Scenario: [2,-2,2,-2], k=4 → 4 (running remainder resets repeatedly)")
        void testRemainderResets() {
            assertEquals(4, instance.subarraysDivByKArraySolution(new int[] { 2, -2, 2, -2 }, 4));
        }

        @Test
        @DisplayName("Scenario: [1,1,1], k=5 → 0 (no valid subarray)")
        void testNoValidSubarray() {
            assertEquals(0, instance.subarraysDivByKArraySolution(new int[] { 1, 1, 1 }, 5));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], k=10000 → 0 (k larger than every sum)")
        void testKLargerThanSums() {
            assertEquals(0, instance.subarraysDivByKArraySolution(new int[] { 1, 2, 3 }, 10000));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000,10000], k=10000 → 6 (max magnitude values)")
        void testMaxMagnitudeValues() {
            assertEquals(6, instance.subarraysDivByKArraySolution(new int[] { 10000, -10000, 10000 }, 10000));
        }

        @Test
        @DisplayName("Scenario: 30,000 zeros, k=2 → 450,015,000 (max length, all zeros)")
        void testMaxLengthZeros() {
            assertEquals(450015000, instance.subarraysDivByKArraySolution(new int[30_000], 2));
        }

        @Test
        @DisplayName("Scenario: 30,000 ones, k=2 → 225,000,000 (max length, alternating remainders)")
        void testMaxLengthAlternating() {
            assertEquals(225000000, instance.subarraysDivByKArraySolution(ones(30_000), 2));
        }
    }
}
