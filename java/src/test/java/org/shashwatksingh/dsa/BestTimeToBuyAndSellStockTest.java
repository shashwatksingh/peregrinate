package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Best Time To Buy And Sell Stock Tests")
class BestTimeToBuyAndSellStockTest {

    private BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock;

    @BeforeEach
    void setUp() {
        bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
    }

    // ═══════════════════════════════════════════════════════════
    //  solution()  — sliding min/max, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution() — sliding min/max O(n)")
    class SolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [7,1,5,3,6,4] → 5 (buy at 1, sell at 6)")
        void testLeetCodeExample1() {
            assertEquals(5, bestTimeToBuyAndSellStock.solution(new int[]{7, 1, 5, 3, 6, 4}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [7,6,4,3,1] → 0 (strictly decreasing, no profit)")
        void testLeetCodeExample2() {
            assertEquals(0, bestTimeToBuyAndSellStock.solution(new int[]{7, 6, 4, 3, 1}));
        }

        @Test
        @DisplayName("Single element: [5] → 0 (cannot buy and sell on same day)")
        void testSingleElement() {
            assertEquals(0, bestTimeToBuyAndSellStock.solution(new int[]{5}));
        }

        @Test
        @DisplayName("Two elements increasing: [1,2] → 1")
        void testTwoElementsIncreasing() {
            assertEquals(1, bestTimeToBuyAndSellStock.solution(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Two elements decreasing: [5,1] → 0")
        void testTwoElementsDecreasing() {
            assertEquals(0, bestTimeToBuyAndSellStock.solution(new int[]{5, 1}));
        }

        @Test
        @DisplayName("All same prices: [3,3,3,3] → 0 (no profit possible)")
        void testAllSamePrices() {
            assertEquals(0, bestTimeToBuyAndSellStock.solution(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 4 (buy at 1, sell at 5)")
        void testStrictlyIncreasing() {
            assertEquals(4, bestTimeToBuyAndSellStock.solution(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Min reset mid-array: [2,4,1,5] → 4 (buy at 1, sell at 5)")
        void testMinResetMidArray() {
            assertEquals(4, bestTimeToBuyAndSellStock.solution(new int[]{2, 4, 1, 5}));
        }

        @Test
        @DisplayName("Valley then higher peak: [3,1,2,8] → 7 (buy at 1, sell at 8)")
        void testValleyThenHigherPeak() {
            assertEquals(7, bestTimeToBuyAndSellStock.solution(new int[]{3, 1, 2, 8}));
        }

        @Test
        @DisplayName("Max constraint values: [0,10000] → 10000")
        void testMaxConstraintValues() {
            assertEquals(10000, bestTimeToBuyAndSellStock.solution(new int[]{0, 10000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  optimizedSolution()  — single-pass min tracking, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("optimizedSolution() — single-pass min tracking O(n)")
    class OptimizedSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [7,1,5,3,6,4] → 5 (buy at 1, sell at 6)")
        void testLeetCodeExample1() {
            assertEquals(5, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{7, 1, 5, 3, 6, 4}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [7,6,4,3,1] → 0 (strictly decreasing, no profit)")
        void testLeetCodeExample2() {
            assertEquals(0, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{7, 6, 4, 3, 1}));
        }

        @Test
        @DisplayName("Single element: [5] → 0 (cannot buy and sell on same day)")
        void testSingleElement() {
            assertEquals(0, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{5}));
        }

        @Test
        @DisplayName("Two elements increasing: [1,2] → 1")
        void testTwoElementsIncreasing() {
            assertEquals(1, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Two elements decreasing: [5,1] → 0")
        void testTwoElementsDecreasing() {
            assertEquals(0, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{5, 1}));
        }

        @Test
        @DisplayName("All same prices: [3,3,3,3] → 0 (no profit possible)")
        void testAllSamePrices() {
            assertEquals(0, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 4 (buy at 1, sell at 5)")
        void testStrictlyIncreasing() {
            assertEquals(4, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Min reset mid-array: [2,4,1,5] → 4 (buy at 1, sell at 5)")
        void testMinResetMidArray() {
            assertEquals(4, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{2, 4, 1, 5}));
        }

        @Test
        @DisplayName("Valley then higher peak: [3,1,2,8] → 7 (buy at 1, sell at 8)")
        void testValleyThenHigherPeak() {
            assertEquals(7, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{3, 1, 2, 8}));
        }

        @Test
        @DisplayName("Max constraint values: [0,10000] → 10000")
        void testMaxConstraintValues() {
            assertEquals(10000, bestTimeToBuyAndSellStock.optimizedSolution(new int[]{0, 10000}));
        }
    }
}
