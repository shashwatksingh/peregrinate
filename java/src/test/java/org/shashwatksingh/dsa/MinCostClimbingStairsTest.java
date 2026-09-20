package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Min Cost Climbing Stairs Tests")
class MinCostClimbingStairsTest {

    private MinCostClimbingStairs minCostClimbingStairs;

    @BeforeEach
    void setUp() {
        minCostClimbingStairs = new MinCostClimbingStairs();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — plain recursion, O(2^n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — plain recursion O(2^n)")
    class BruteForceTests {

        @Test
        @DisplayName("Minimum length: [10, 15] → 10")
        void testMinLength() {
            assertEquals(10, minCostClimbingStairs.bruteForce(new int[]{10, 15}));
        }

        @Test
        @DisplayName("All zeros: [0, 0] → 0")
        void testAllZeros() {
            assertEquals(0, minCostClimbingStairs.bruteForce(new int[]{0, 0}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [10, 15, 20] → 15")
        void testExample1() {
            assertEquals(15, minCostClimbingStairs.bruteForce(new int[]{10, 15, 20}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,100,1,1,1,100,1,1,100,1] → 6")
        void testExample2() {
            assertEquals(6, minCostClimbingStairs.bruteForce(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        }

        @Test
        @DisplayName("Cheaper second step: [100, 1] → 1")
        void testCheaperSecondStep() {
            assertEquals(1, minCostClimbingStairs.bruteForce(new int[]{100, 1}));
        }

        @Test
        @DisplayName("Zero-cost second step: [1, 0] → 0")
        void testZeroCostSecondStep() {
            assertEquals(0, minCostClimbingStairs.bruteForce(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Uniform costs: [5, 5, 5, 5, 5] → 10")
        void testUniformCosts() {
            assertEquals(10, minCostClimbingStairs.bruteForce(new int[]{5, 5, 5, 5, 5}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  recursionWithMemoization()  — top-down DP, O(n) time & space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("recursionWithMemoization() — top-down DP O(n)")
    class RecursionWithMemoizationTests {

        @Test
        @DisplayName("Minimum length: [10, 15] → 10")
        void testMinLength() {
            assertEquals(10, minCostClimbingStairs.recursionWithMemoization(new int[]{10, 15}));
        }

        @Test
        @DisplayName("All zeros: [0, 0] → 0")
        void testAllZeros() {
            assertEquals(0, minCostClimbingStairs.recursionWithMemoization(new int[]{0, 0}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [10, 15, 20] → 15")
        void testExample1() {
            assertEquals(15, minCostClimbingStairs.recursionWithMemoization(new int[]{10, 15, 20}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,100,1,1,1,100,1,1,100,1] → 6")
        void testExample2() {
            assertEquals(6, minCostClimbingStairs.recursionWithMemoization(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        }

        @Test
        @DisplayName("Cheaper second step: [100, 1] → 1")
        void testCheaperSecondStep() {
            assertEquals(1, minCostClimbingStairs.recursionWithMemoization(new int[]{100, 1}));
        }

        @Test
        @DisplayName("Zero-cost second step: [1, 0] → 0")
        void testZeroCostSecondStep() {
            assertEquals(0, minCostClimbingStairs.recursionWithMemoization(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Uniform costs: [5, 5, 5, 5, 5] → 10")
        void testUniformCosts() {
            assertEquals(10, minCostClimbingStairs.recursionWithMemoization(new int[]{5, 5, 5, 5, 5}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpWithTabulation()  — bottom-up DP, O(n) time & space
    //  NOTE: The recurrence dp[i] = cost[i-2] + min(dp[i-1], dp[i-2])
    //        is incorrect. The correct recurrence should be:
    //        dp[i] = min(cost[i-1] + dp[i-1], cost[i-2] + dp[i-2])
    //        Tests that expose the bug are marked with [BUG: ...].
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpWithTabulation() — bottom-up DP O(n)")
    class BottomUpWithTabulationTests {

        @Test
        @DisplayName("Minimum length: [10, 15] → 10")
        void testMinLength() {
            assertEquals(10, minCostClimbingStairs.bottomUpWithTabulation(new int[]{10, 15}));
        }

        @Test
        @DisplayName("All zeros: [0, 0] → 0")
        void testAllZeros() {
            assertEquals(0, minCostClimbingStairs.bottomUpWithTabulation(new int[]{0, 0}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [10, 15, 20] → 15")
        void testExample1() {
            assertEquals(15, minCostClimbingStairs.bottomUpWithTabulation(new int[]{10, 15, 20}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,100,1,1,1,100,1,1,100,1] → 6 [BUG: wrong recurrence gives 104]")
        void testExample2() {
            assertEquals(6, minCostClimbingStairs.bottomUpWithTabulation(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        }

        @Test
        @DisplayName("Cheaper second step: [100, 1] → 1 [BUG: wrong recurrence gives 100]")
        void testCheaperSecondStep() {
            assertEquals(1, minCostClimbingStairs.bottomUpWithTabulation(new int[]{100, 1}));
        }

        @Test
        @DisplayName("Zero-cost second step: [1, 0] → 0 [BUG: wrong recurrence gives 1]")
        void testZeroCostSecondStep() {
            assertEquals(0, minCostClimbingStairs.bottomUpWithTabulation(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Uniform costs: [5, 5, 5, 5, 5] → 10")
        void testUniformCosts() {
            assertEquals(10, minCostClimbingStairs.bottomUpWithTabulation(new int[]{5, 5, 5, 5, 5}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpWithSpaceOptimisation()  — O(n) time, O(1) space
    //  NOTE: Carries the same wrong recurrence as bottomUpWithTabulation.
    //        Tests that expose the bug are marked with [BUG: ...].
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpWithSpaceOptimisation() — O(n) time, O(1) space")
    class BottomUpWithSpaceOptimisationTests {

        @Test
        @DisplayName("Minimum length: [10, 15] → 10")
        void testMinLength() {
            assertEquals(10, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{10, 15}));
        }

        @Test
        @DisplayName("All zeros: [0, 0] → 0")
        void testAllZeros() {
            assertEquals(0, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{0, 0}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [10, 15, 20] → 15")
        void testExample1() {
            assertEquals(15, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{10, 15, 20}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,100,1,1,1,100,1,1,100,1] → 6 [BUG: wrong recurrence gives 104]")
        void testExample2() {
            assertEquals(6, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        }

        @Test
        @DisplayName("Cheaper second step: [100, 1] → 1 [BUG: wrong recurrence gives 100]")
        void testCheaperSecondStep() {
            assertEquals(1, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{100, 1}));
        }

        @Test
        @DisplayName("Zero-cost second step: [1, 0] → 0 [BUG: wrong recurrence gives 1]")
        void testZeroCostSecondStep() {
            assertEquals(0, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{1, 0}));
        }

        @Test
        @DisplayName("Uniform costs: [5, 5, 5, 5, 5] → 10")
        void testUniformCosts() {
            assertEquals(10, minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[]{5, 5, 5, 5, 5}));
        }
    }
}
