package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PerfectSquares Tests")
class PerfectSquaresTest {

    private PerfectSquares instance;

    @BeforeEach
    void setUp() {
        instance = new PerfectSquares();
    }

    // ═══════════════════════════════════════════════════════════
    //  numSquaresRecursive()  — plain recursive DFS, exponential time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numSquaresRecursive() — plain recursive DFS")
    class NumSquaresRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: n=12 → 3 (4+4+4)")
        void testLeetCodeExample1() {
            assertEquals(3, instance.numSquaresRecursive(12));
        }

        @Test
        @DisplayName("LeetCode Example 2: n=13 → 2 (4+9)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.numSquaresRecursive(13));
        }

        @Test
        @DisplayName("Minimum-size input: n=1 → 1 (1 is itself a perfect square)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numSquaresRecursive(1));
        }

        @Test
        @DisplayName("Perfect square input: n=4 → 1 (2^2)")
        void testPerfectSquareInput() {
            assertEquals(1, instance.numSquaresRecursive(4));
        }

        @Test
        @DisplayName("Four squares required (Legendre form 4^a(8b+7)): n=7 → 4 (4+1+1+1)")
        void testFourSquaresRequired() {
            assertEquals(4, instance.numSquaresRecursive(7));
        }

        @Test
        @DisplayName("Two squares suffice: n=5 → 2 (4+1)")
        void testTwoSquaresSuffice() {
            assertEquals(2, instance.numSquaresRecursive(5));
        }

        @Test
        @DisplayName("Three squares required: n=11 → 3 (9+1+1)")
        void testThreeSquaresRequired() {
            assertEquals(3, instance.numSquaresRecursive(11));
        }

        @Test
        @DisplayName("Larger perfect square (scaled down from the 10^4 constraint to keep unmemoized recursion runnable): n=9 → 1 (3^2)")
        void testLargerPerfectSquare() {
            assertEquals(1, instance.numSquaresRecursive(9));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  numSquaresTopDownWithMemoization()  — recursive DFS with memo array, O(n*sqrt(n)) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numSquaresTopDownWithMemoization() — recursive DFS with memo array")
    class NumSquaresTopDownWithMemoizationTests {

        @Test
        @DisplayName("LeetCode Example 1: n=12 → 3 (4+4+4)")
        void testLeetCodeExample1() {
            assertEquals(3, instance.numSquaresTopDownWithMemoization(12));
        }

        @Test
        @DisplayName("LeetCode Example 2: n=13 → 2 (4+9)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.numSquaresTopDownWithMemoization(13));
        }

        @Test
        @DisplayName("Minimum-size input: n=1 → 1 (1 is itself a perfect square)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numSquaresTopDownWithMemoization(1));
        }

        @Test
        @DisplayName("Perfect square input: n=4 → 1 (2^2)")
        void testPerfectSquareInput() {
            assertEquals(1, instance.numSquaresTopDownWithMemoization(4));
        }

        @Test
        @DisplayName("Four squares required (Legendre form 4^a(8b+7)): n=7 → 4 (4+1+1+1)")
        void testFourSquaresRequired() {
            assertEquals(4, instance.numSquaresTopDownWithMemoization(7));
        }

        @Test
        @DisplayName("Two squares suffice, larger magnitude: n=50 → 2 (49+1)")
        void testTwoSquaresSuffice() {
            assertEquals(2, instance.numSquaresTopDownWithMemoization(50));
        }

        @Test
        @DisplayName("Three squares required: n=11 → 3 (9+1+1)")
        void testThreeSquaresRequired() {
            assertEquals(3, instance.numSquaresTopDownWithMemoization(11));
        }

        @Test
        @DisplayName("Max constraint boundary, perfect square: n=10000 → 1 (100^2)")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.numSquaresTopDownWithMemoization(10000));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  numSquaresBottomUpWithTabulation()  — bottom-up DP array, O(n*sqrt(n)) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numSquaresBottomUpWithTabulation() — bottom-up DP array")
    class NumSquaresBottomUpWithTabulationTests {

        @Test
        @DisplayName("LeetCode Example 1: n=12 → 3 (4+4+4)")
        void testLeetCodeExample1() {
            assertEquals(3, instance.numSquaresBottomUpWithTabulation(12));
        }

        @Test
        @DisplayName("LeetCode Example 2: n=13 → 2 (4+9)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.numSquaresBottomUpWithTabulation(13));
        }

        @Test
        @DisplayName("Minimum-size input: n=1 → 1 (1 is itself a perfect square)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numSquaresBottomUpWithTabulation(1));
        }

        @Test
        @DisplayName("Perfect square input: n=4 → 1 (2^2)")
        void testPerfectSquareInput() {
            assertEquals(1, instance.numSquaresBottomUpWithTabulation(4));
        }

        @Test
        @DisplayName("Four squares required (Legendre form 4^a(8b+7)): n=7 → 4 (4+1+1+1)")
        void testFourSquaresRequired() {
            assertEquals(4, instance.numSquaresBottomUpWithTabulation(7));
        }

        @Test
        @DisplayName("Two squares suffice, larger magnitude: n=50 → 2 (49+1)")
        void testTwoSquaresSuffice() {
            assertEquals(2, instance.numSquaresBottomUpWithTabulation(50));
        }

        @Test
        @DisplayName("Three squares required: n=11 → 3 (9+1+1)")
        void testThreeSquaresRequired() {
            assertEquals(3, instance.numSquaresBottomUpWithTabulation(11));
        }

        @Test
        @DisplayName("Max constraint boundary, perfect square: n=10000 → 1 (100^2)")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.numSquaresBottomUpWithTabulation(10000));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  numSquaresBottomUpWithSpaceOptimisation()  — bottom-up DP array, O(n*sqrt(n)) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numSquaresBottomUpWithSpaceOptimisation() — bottom-up DP array")
    class NumSquaresBottomUpWithSpaceOptimisationTests {

        @Test
        @DisplayName("LeetCode Example 1: n=12 → 3 (4+4+4)")
        void testLeetCodeExample1() {
            assertEquals(3, instance.numSquaresBottomUpWithSpaceOptimisation(12));
        }

        @Test
        @DisplayName("LeetCode Example 2: n=13 → 2 (4+9)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.numSquaresBottomUpWithSpaceOptimisation(13));
        }

        @Test
        @DisplayName("Minimum-size input: n=1 → 1 (1 is itself a perfect square)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numSquaresBottomUpWithSpaceOptimisation(1));
        }

        @Test
        @DisplayName("Perfect square input: n=4 → 1 (2^2)")
        void testPerfectSquareInput() {
            assertEquals(1, instance.numSquaresBottomUpWithSpaceOptimisation(4));
        }

        @Test
        @DisplayName("Four squares required (Legendre form 4^a(8b+7)): n=7 → 4 (4+1+1+1)")
        void testFourSquaresRequired() {
            assertEquals(4, instance.numSquaresBottomUpWithSpaceOptimisation(7));
        }

        @Test
        @DisplayName("Two squares suffice, larger magnitude: n=50 → 2 (49+1)")
        void testTwoSquaresSuffice() {
            assertEquals(2, instance.numSquaresBottomUpWithSpaceOptimisation(50));
        }

        @Test
        @DisplayName("Three squares required: n=11 → 3 (9+1+1)")
        void testThreeSquaresRequired() {
            assertEquals(3, instance.numSquaresBottomUpWithSpaceOptimisation(11));
        }

        @Test
        @DisplayName("Max constraint boundary, perfect square: n=10000 → 1 (100^2)")
        void testMaxConstraintBoundary() {
            assertEquals(1, instance.numSquaresBottomUpWithSpaceOptimisation(10000));
        }
    }
}
