package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Climb Stairs Tests")
class ClimbStairsTest {

    private ClimbStairs climbStairs;

    @BeforeEach
    void setUp() {
        climbStairs = new ClimbStairs();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForceRecursion()  — plain recursion, O(2^n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForceRecursion() — plain recursion O(2^n)")
    class BruteForceRecursionTests {

        @Test
        @DisplayName("n=1 → 1 (single step)")
        void testMinConstraint() {
            assertEquals(1, climbStairs.bruteForceRecursion(1));
        }

        @Test
        @DisplayName("Example 1: n=2 → 2")
        void testExample1() {
            assertEquals(2, climbStairs.bruteForceRecursion(2));
        }

        @Test
        @DisplayName("Example 2: n=3 → 3")
        void testExample2() {
            assertEquals(3, climbStairs.bruteForceRecursion(3));
        }

        @Test
        @DisplayName("n=4 → 5")
        void testFourSteps() {
            assertEquals(5, climbStairs.bruteForceRecursion(4));
        }

        @Test
        @DisplayName("n=5 → 8")
        void testFiveSteps() {
            assertEquals(8, climbStairs.bruteForceRecursion(5));
        }

        @Test
        @DisplayName("n=6 → 13")
        void testSixSteps() {
            assertEquals(13, climbStairs.bruteForceRecursion(6));
        }

        @Test
        @DisplayName("n=10 → 89")
        void testTenSteps() {
            assertEquals(89, climbStairs.bruteForceRecursion(10));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionMemoization()  — top-down DP, O(n) time & space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionMemoization() — top-down DP O(n)")
    class MemoizationTests {

        @Test
        @DisplayName("n=1 → 1 (single step)")
        void testMinConstraint() {
            assertEquals(1, climbStairs.solutionMemoization(1));
        }

        @Test
        @DisplayName("Example 1: n=2 → 2")
        void testExample1() {
            assertEquals(2, climbStairs.solutionMemoization(2));
        }

        @Test
        @DisplayName("Example 2: n=3 → 3")
        void testExample2() {
            assertEquals(3, climbStairs.solutionMemoization(3));
        }

        @Test
        @DisplayName("n=4 → 5")
        void testFourSteps() {
            assertEquals(5, climbStairs.solutionMemoization(4));
        }

        @Test
        @DisplayName("n=5 → 8")
        void testFiveSteps() {
            assertEquals(8, climbStairs.solutionMemoization(5));
        }

        @Test
        @DisplayName("n=6 → 13")
        void testSixSteps() {
            assertEquals(13, climbStairs.solutionMemoization(6));
        }

        @Test
        @DisplayName("n=10 → 89")
        void testTenSteps() {
            assertEquals(89, climbStairs.solutionMemoization(10));
        }

        @Test
        @DisplayName("n=45 → 1836311903 (max constraint)")
        void testMaxConstraint() {
            assertEquals(1_836_311_903, climbStairs.solutionMemoization(45));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionBottomUp()  — bottom-up DP, O(n) time & space
    //  NOTE: solutionBottomUp has a bug — when n=1, the dp array
    //        has length 2 (indices 0..1) but dp[2]=2 is hardcoded,
    //        causing ArrayIndexOutOfBoundsException.
    //        The n=1 test below documents the correct expected behaviour
    //        and will fail until the bug is fixed.
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionBottomUp() — bottom-up DP O(n)")
    class BottomUpTests {

        @Test
        @DisplayName("n=1 → 1 (single step) [BUG: ArrayIndexOutOfBoundsException for n=1]")
        void testMinConstraint() {
            assertEquals(1, climbStairs.solutionBottomUp(1));
        }

        @Test
        @DisplayName("Example 1: n=2 → 2")
        void testExample1() {
            assertEquals(2, climbStairs.solutionBottomUp(2));
        }

        @Test
        @DisplayName("Example 2: n=3 → 3")
        void testExample2() {
            assertEquals(3, climbStairs.solutionBottomUp(3));
        }

        @Test
        @DisplayName("n=4 → 5")
        void testFourSteps() {
            assertEquals(5, climbStairs.solutionBottomUp(4));
        }

        @Test
        @DisplayName("n=5 → 8")
        void testFiveSteps() {
            assertEquals(8, climbStairs.solutionBottomUp(5));
        }

        @Test
        @DisplayName("n=6 → 13")
        void testSixSteps() {
            assertEquals(13, climbStairs.solutionBottomUp(6));
        }

        @Test
        @DisplayName("n=10 → 89")
        void testTenSteps() {
            assertEquals(89, climbStairs.solutionBottomUp(10));
        }

        @Test
        @DisplayName("n=45 → 1836311903 (max constraint)")
        void testMaxConstraint() {
            assertEquals(1_836_311_903, climbStairs.solutionBottomUp(45));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionBottomUpOptimised()  — O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionBottomUpOptimised() — O(n) time, O(1) space")
    class BottomUpOptimisedTests {

        @Test
        @DisplayName("n=1 → 1 (single step)")
        void testMinConstraint() {
            assertEquals(1, climbStairs.solutionBottomUpOptimised(1));
        }

        @Test
        @DisplayName("Example 1: n=2 → 2")
        void testExample1() {
            assertEquals(2, climbStairs.solutionBottomUpOptimised(2));
        }

        @Test
        @DisplayName("Example 2: n=3 → 3")
        void testExample2() {
            assertEquals(3, climbStairs.solutionBottomUpOptimised(3));
        }

        @Test
        @DisplayName("n=4 → 5")
        void testFourSteps() {
            assertEquals(5, climbStairs.solutionBottomUpOptimised(4));
        }

        @Test
        @DisplayName("n=5 → 8")
        void testFiveSteps() {
            assertEquals(8, climbStairs.solutionBottomUpOptimised(5));
        }

        @Test
        @DisplayName("n=6 → 13")
        void testSixSteps() {
            assertEquals(13, climbStairs.solutionBottomUpOptimised(6));
        }

        @Test
        @DisplayName("n=10 → 89")
        void testTenSteps() {
            assertEquals(89, climbStairs.solutionBottomUpOptimised(10));
        }

        @Test
        @DisplayName("n=45 → 1836311903 (max constraint)")
        void testMaxConstraint() {
            assertEquals(1_836_311_903, climbStairs.solutionBottomUpOptimised(45));
        }
    }
}
