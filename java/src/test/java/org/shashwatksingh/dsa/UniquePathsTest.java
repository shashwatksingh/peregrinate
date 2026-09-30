package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unique Paths Tests")
class UniquePathsTest {

    private UniquePaths instance;

    @BeforeEach
    void setUp() {
        instance = new UniquePaths();
    }

    // ═══════════════════════════════════════════════════════════
    //  uniquePathsRecursive()  — plain recursion, O(2^(m+n)) time / O(m+n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("uniquePathsRecursive() — plain recursion, O(2^(m+n)) time / O(m+n) space")
    class UniquePathsRecursiveTests {

        @Test
        @DisplayName("LeetCode Example 1: m=3, n=7 → 28")
        void testLeetCodeExample1() {
            assertEquals(28, instance.uniquePathsRecursive(3, 7));
        }

        @Test
        @DisplayName("LeetCode Example 2: m=3, n=2 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.uniquePathsRecursive(3, 2));
        }

        @Test
        @DisplayName("Minimum-size grid: m=1, n=1 → 1 (already at destination)")
        void testMinimumSizeGrid() {
            assertEquals(1, instance.uniquePathsRecursive(1, 1));
        }

        @Test
        @DisplayName("Single row: m=1, n=5 → 1")
        void testSingleRow() {
            assertEquals(1, instance.uniquePathsRecursive(1, 5));
        }

        @Test
        @DisplayName("Single column: m=5, n=1 → 1")
        void testSingleColumn() {
            assertEquals(1, instance.uniquePathsRecursive(5, 1));
        }

        @Test
        @DisplayName("Smallest square: m=2, n=2 → 2")
        void testTwoByTwo() {
            assertEquals(2, instance.uniquePathsRecursive(2, 2));
        }

        @Test
        @DisplayName("Square grid: m=4, n=4 → 20")
        void testFourByFour() {
            assertEquals(20, instance.uniquePathsRecursive(4, 4));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=7, n=3 → 28 (mirror of Example 1)")
        void testTallMirrorOfExample1() {
            assertEquals(28, instance.uniquePathsRecursive(7, 3));
        }

        @Test
        @DisplayName("Asymmetric, wide: m=3, n=5 → 15")
        void testWideThreeByFive() {
            assertEquals(15, instance.uniquePathsRecursive(3, 5));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=5, n=3 → 15")
        void testTallFiveByThree() {
            assertEquals(15, instance.uniquePathsRecursive(5, 3));
        }

        @Test
        @DisplayName("Larger square: m=10, n=10 → 48620")
        void testTenByTen() {
            assertEquals(48620, instance.uniquePathsRecursive(10, 10));
        }

        @Test
        @DisplayName("Max constraint boundary: m=1, n=100 → 1")
        void testMaxSingleRow() {
            assertEquals(1, instance.uniquePathsRecursive(1, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=1 → 1")
        void testMaxSingleColumn() {
            assertEquals(1, instance.uniquePathsRecursive(100, 1));
        }

        @Test
        @DisplayName("Max constraint boundary: m=2, n=100 → 100")
        void testMaxTwoRows() {
            assertEquals(100, instance.uniquePathsRecursive(2, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=2 → 100")
        void testMaxTwoColumns() {
            assertEquals(100, instance.uniquePathsRecursive(100, 2));
        }

        @Test
        @DisplayName("Max constraint boundary: m=3, n=100 → 5050")
        void testMaxThreeRows() {
            assertEquals(5050, instance.uniquePathsRecursive(3, 100));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  uniquePathsTopDownWithMemoization()  — top-down memoization, O(mn) time / O(mn) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("uniquePathsTopDownWithMemoization() — top-down memoization, O(mn) time / O(mn) space")
    class UniquePathsTopDownWithMemoizationTests {

        @Test
        @DisplayName("LeetCode Example 1: m=3, n=7 → 28")
        void testLeetCodeExample1() {
            assertEquals(28, instance.uniquePathsTopDownWithMemoization(3, 7));
        }

        @Test
        @DisplayName("LeetCode Example 2: m=3, n=2 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.uniquePathsTopDownWithMemoization(3, 2));
        }

        @Test
        @DisplayName("Minimum-size grid: m=1, n=1 → 1 (already at destination)")
        void testMinimumSizeGrid() {
            assertEquals(1, instance.uniquePathsTopDownWithMemoization(1, 1));
        }

        @Test
        @DisplayName("Single row: m=1, n=5 → 1")
        void testSingleRow() {
            assertEquals(1, instance.uniquePathsTopDownWithMemoization(1, 5));
        }

        @Test
        @DisplayName("Single column: m=5, n=1 → 1")
        void testSingleColumn() {
            assertEquals(1, instance.uniquePathsTopDownWithMemoization(5, 1));
        }

        @Test
        @DisplayName("Smallest square: m=2, n=2 → 2")
        void testTwoByTwo() {
            assertEquals(2, instance.uniquePathsTopDownWithMemoization(2, 2));
        }

        @Test
        @DisplayName("Square grid: m=4, n=4 → 20")
        void testFourByFour() {
            assertEquals(20, instance.uniquePathsTopDownWithMemoization(4, 4));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=7, n=3 → 28 (mirror of Example 1)")
        void testTallMirrorOfExample1() {
            assertEquals(28, instance.uniquePathsTopDownWithMemoization(7, 3));
        }

        @Test
        @DisplayName("Asymmetric, wide: m=3, n=5 → 15")
        void testWideThreeByFive() {
            assertEquals(15, instance.uniquePathsTopDownWithMemoization(3, 5));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=5, n=3 → 15")
        void testTallFiveByThree() {
            assertEquals(15, instance.uniquePathsTopDownWithMemoization(5, 3));
        }

        @Test
        @DisplayName("Larger square: m=10, n=10 → 48620")
        void testTenByTen() {
            assertEquals(48620, instance.uniquePathsTopDownWithMemoization(10, 10));
        }

        @Test
        @DisplayName("Max constraint boundary: m=1, n=100 → 1")
        void testMaxSingleRow() {
            assertEquals(1, instance.uniquePathsTopDownWithMemoization(1, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=1 → 1")
        void testMaxSingleColumn() {
            assertEquals(1, instance.uniquePathsTopDownWithMemoization(100, 1));
        }

        @Test
        @DisplayName("Max constraint boundary: m=2, n=100 → 100")
        void testMaxTwoRows() {
            assertEquals(100, instance.uniquePathsTopDownWithMemoization(2, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=2 → 100")
        void testMaxTwoColumns() {
            assertEquals(100, instance.uniquePathsTopDownWithMemoization(100, 2));
        }

        @Test
        @DisplayName("Max constraint boundary: m=3, n=100 → 5050")
        void testMaxThreeRows() {
            assertEquals(5050, instance.uniquePathsTopDownWithMemoization(3, 100));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  uniquePathsBottomUp()  — bottom-up 2D DP, O(mn) time / O(mn) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("uniquePathsBottomUp() — bottom-up 2D DP, O(mn) time / O(mn) space")
    class UniquePathsBottomUpTests {

        @Test
        @DisplayName("LeetCode Example 1: m=3, n=7 → 28")
        void testLeetCodeExample1() {
            assertEquals(28, instance.uniquePathsBottomUp(3, 7));
        }

        @Test
        @DisplayName("LeetCode Example 2: m=3, n=2 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.uniquePathsBottomUp(3, 2));
        }

        @Test
        @DisplayName("Minimum-size grid: m=1, n=1 → 1 (already at destination)")
        void testMinimumSizeGrid() {
            assertEquals(1, instance.uniquePathsBottomUp(1, 1));
        }

        @Test
        @DisplayName("Single row: m=1, n=5 → 1")
        void testSingleRow() {
            assertEquals(1, instance.uniquePathsBottomUp(1, 5));
        }

        @Test
        @DisplayName("Single column: m=5, n=1 → 1")
        void testSingleColumn() {
            assertEquals(1, instance.uniquePathsBottomUp(5, 1));
        }

        @Test
        @DisplayName("Smallest square: m=2, n=2 → 2")
        void testTwoByTwo() {
            assertEquals(2, instance.uniquePathsBottomUp(2, 2));
        }

        @Test
        @DisplayName("Square grid: m=4, n=4 → 20")
        void testFourByFour() {
            assertEquals(20, instance.uniquePathsBottomUp(4, 4));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=7, n=3 → 28 (mirror of Example 1)")
        void testTallMirrorOfExample1() {
            assertEquals(28, instance.uniquePathsBottomUp(7, 3));
        }

        @Test
        @DisplayName("Asymmetric, wide: m=3, n=5 → 15")
        void testWideThreeByFive() {
            assertEquals(15, instance.uniquePathsBottomUp(3, 5));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=5, n=3 → 15")
        void testTallFiveByThree() {
            assertEquals(15, instance.uniquePathsBottomUp(5, 3));
        }

        @Test
        @DisplayName("Larger square: m=10, n=10 → 48620")
        void testTenByTen() {
            assertEquals(48620, instance.uniquePathsBottomUp(10, 10));
        }

        @Test
        @DisplayName("Max constraint boundary: m=1, n=100 → 1")
        void testMaxSingleRow() {
            assertEquals(1, instance.uniquePathsBottomUp(1, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=1 → 1")
        void testMaxSingleColumn() {
            assertEquals(1, instance.uniquePathsBottomUp(100, 1));
        }

        @Test
        @DisplayName("Max constraint boundary: m=2, n=100 → 100")
        void testMaxTwoRows() {
            assertEquals(100, instance.uniquePathsBottomUp(2, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=2 → 100")
        void testMaxTwoColumns() {
            assertEquals(100, instance.uniquePathsBottomUp(100, 2));
        }

        @Test
        @DisplayName("Max constraint boundary: m=3, n=100 → 5050")
        void testMaxThreeRows() {
            assertEquals(5050, instance.uniquePathsBottomUp(3, 100));
        }

        @Test
        @DisplayName("Large answer within 2×10^9: m=17, n=17 → 601080390")
        void testLargeSquare17() {
            assertEquals(601080390, instance.uniquePathsBottomUp(17, 17));
        }

        @Test
        @DisplayName("Large answer within 2×10^9: m=23, n=12 → 193536720")
        void testLarge23By12() {
            assertEquals(193536720, instance.uniquePathsBottomUp(23, 12));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  uniquePathsBottomUpSpaceOptimised()  — bottom-up 1D DP, O(mn) time / O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("uniquePathsBottomUpSpaceOptimised() — bottom-up 1D DP, O(mn) time / O(n) space")
    class UniquePathsBottomUpSpaceOptimisedTests {

        @Test
        @DisplayName("LeetCode Example 1: m=3, n=7 → 28")
        void testLeetCodeExample1() {
            assertEquals(28, instance.uniquePathsBottomUpSpaceOptimised(3, 7));
        }

        @Test
        @DisplayName("LeetCode Example 2: m=3, n=2 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.uniquePathsBottomUpSpaceOptimised(3, 2));
        }

        @Test
        @DisplayName("Minimum-size grid: m=1, n=1 → 1 (already at destination)")
        void testMinimumSizeGrid() {
            assertEquals(1, instance.uniquePathsBottomUpSpaceOptimised(1, 1));
        }

        @Test
        @DisplayName("Single row: m=1, n=5 → 1")
        void testSingleRow() {
            assertEquals(1, instance.uniquePathsBottomUpSpaceOptimised(1, 5));
        }

        @Test
        @DisplayName("Single column: m=5, n=1 → 1")
        void testSingleColumn() {
            assertEquals(1, instance.uniquePathsBottomUpSpaceOptimised(5, 1));
        }

        @Test
        @DisplayName("Smallest square: m=2, n=2 → 2")
        void testTwoByTwo() {
            assertEquals(2, instance.uniquePathsBottomUpSpaceOptimised(2, 2));
        }

        @Test
        @DisplayName("Square grid: m=4, n=4 → 20")
        void testFourByFour() {
            assertEquals(20, instance.uniquePathsBottomUpSpaceOptimised(4, 4));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=7, n=3 → 28 (mirror of Example 1)")
        void testTallMirrorOfExample1() {
            assertEquals(28, instance.uniquePathsBottomUpSpaceOptimised(7, 3));
        }

        @Test
        @DisplayName("Asymmetric, wide: m=3, n=5 → 15")
        void testWideThreeByFive() {
            assertEquals(15, instance.uniquePathsBottomUpSpaceOptimised(3, 5));
        }

        @Test
        @DisplayName("Asymmetric, tall: m=5, n=3 → 15")
        void testTallFiveByThree() {
            assertEquals(15, instance.uniquePathsBottomUpSpaceOptimised(5, 3));
        }

        @Test
        @DisplayName("Larger square: m=10, n=10 → 48620")
        void testTenByTen() {
            assertEquals(48620, instance.uniquePathsBottomUpSpaceOptimised(10, 10));
        }

        @Test
        @DisplayName("Max constraint boundary: m=1, n=100 → 1")
        void testMaxSingleRow() {
            assertEquals(1, instance.uniquePathsBottomUpSpaceOptimised(1, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=1 → 1")
        void testMaxSingleColumn() {
            assertEquals(1, instance.uniquePathsBottomUpSpaceOptimised(100, 1));
        }

        @Test
        @DisplayName("Max constraint boundary: m=2, n=100 → 100")
        void testMaxTwoRows() {
            assertEquals(100, instance.uniquePathsBottomUpSpaceOptimised(2, 100));
        }

        @Test
        @DisplayName("Max constraint boundary: m=100, n=2 → 100")
        void testMaxTwoColumns() {
            assertEquals(100, instance.uniquePathsBottomUpSpaceOptimised(100, 2));
        }

        @Test
        @DisplayName("Max constraint boundary: m=3, n=100 → 5050")
        void testMaxThreeRows() {
            assertEquals(5050, instance.uniquePathsBottomUpSpaceOptimised(3, 100));
        }

        @Test
        @DisplayName("Large answer within 2×10^9: m=17, n=17 → 601080390")
        void testLargeSquare17() {
            assertEquals(601080390, instance.uniquePathsBottomUpSpaceOptimised(17, 17));
        }

        @Test
        @DisplayName("Large answer within 2×10^9: m=23, n=12 → 193536720")
        void testLarge23By12() {
            assertEquals(193536720, instance.uniquePathsBottomUpSpaceOptimised(23, 12));
        }
    }
}
