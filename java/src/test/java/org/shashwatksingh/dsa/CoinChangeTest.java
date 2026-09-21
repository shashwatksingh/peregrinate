package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Coin Change Tests")
class CoinChangeTest {

    private CoinChange coinChange;

    @BeforeEach
    void setUp() {
        coinChange = new CoinChange();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForceSolution()  — plain DFS recursion, exponential time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForceSolution() — plain DFS recursion")
    class BruteForceSolutionTests {

        @Test
        @DisplayName("amount=0 → 0 (no coins needed)")
        void testZeroAmount() {
            assertEquals(0, coinChange.bruteForceSolution(new int[]{1, 2, 5}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 1: coins=[1,2,5], amount=11 → 3 (5+5+1)")
        void testLeetCodeExample1() {
            assertEquals(3, coinChange.bruteForceSolution(new int[]{1, 2, 5}, 11));
        }

        @Test
        @DisplayName("LeetCode Example 2: coins=[2], amount=3 → -1 (impossible)")
        void testLeetCodeExample2Impossible() {
            assertEquals(-1, coinChange.bruteForceSolution(new int[]{2}, 3));
        }

        @Test
        @DisplayName("Single coin exact match: coins=[5], amount=5 → 1")
        void testSingleCoinExactMatch() {
            assertEquals(1, coinChange.bruteForceSolution(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Single coin multiple uses: coins=[5], amount=10 → 2")
        void testSingleCoinMultipleUses() {
            assertEquals(2, coinChange.bruteForceSolution(new int[]{5}, 10));
        }

        @Test
        @DisplayName("All coins larger than amount: coins=[5,10], amount=3 → -1")
        void testAllCoinsLargerThanAmount() {
            assertEquals(-1, coinChange.bruteForceSolution(new int[]{5, 10}, 3));
        }

        @Test
        @DisplayName("DP vs greedy divergence: coins=[1,3,4], amount=6 → 2 (3+3, not 4+1+1)")
        void testDpVsGreedyDivergence() {
            assertEquals(2, coinChange.bruteForceSolution(new int[]{1, 3, 4}, 6));
        }

        @Test
        @DisplayName("Multiple coin types, minimum path: coins=[1,5,6], amount=11 → 2 (5+6)")
        void testMultipleCoinTypesMinimumPath() {
            assertEquals(2, coinChange.bruteForceSolution(new int[]{1, 5, 6}, 11));
        }

        @Test
        @DisplayName("Amount equals one coin: coins=[2,5,10], amount=2 → 1")
        void testAmountEqualsOneCoin() {
            assertEquals(1, coinChange.bruteForceSolution(new int[]{2, 5, 10}, 2));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  topDownWithMemoizationSolution()  — top-down DP, O(n*amount) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("topDownWithMemoizationSolution() — top-down DP with memoization")
    class TopDownWithMemoizationSolutionTests {

        @Test
        @DisplayName("amount=0 → 0 (no coins needed)")
        void testZeroAmount() {
            assertEquals(0, coinChange.topDownWithMemoizationSolution(new int[]{1, 2, 5}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 1: coins=[1,2,5], amount=11 → 3 (5+5+1)")
        void testLeetCodeExample1() {
            assertEquals(3, coinChange.topDownWithMemoizationSolution(new int[]{1, 2, 5}, 11));
        }

        @Test
        @DisplayName("LeetCode Example 2: coins=[2], amount=3 → -1 (impossible)")
        void testLeetCodeExample2Impossible() {
            assertEquals(-1, coinChange.topDownWithMemoizationSolution(new int[]{2}, 3));
        }

        @Test
        @DisplayName("Single coin exact match: coins=[5], amount=5 → 1")
        void testSingleCoinExactMatch() {
            assertEquals(1, coinChange.topDownWithMemoizationSolution(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Single coin multiple uses: coins=[5], amount=10 → 2")
        void testSingleCoinMultipleUses() {
            assertEquals(2, coinChange.topDownWithMemoizationSolution(new int[]{5}, 10));
        }

        @Test
        @DisplayName("All coins larger than amount: coins=[5,10], amount=3 → -1")
        void testAllCoinsLargerThanAmount() {
            assertEquals(-1, coinChange.topDownWithMemoizationSolution(new int[]{5, 10}, 3));
        }

        @Test
        @DisplayName("DP vs greedy divergence: coins=[1,3,4], amount=6 → 2 (3+3, not 4+1+1)")
        void testDpVsGreedyDivergence() {
            assertEquals(2, coinChange.topDownWithMemoizationSolution(new int[]{1, 3, 4}, 6));
        }

        @Test
        @DisplayName("Multiple coin types, minimum path: coins=[1,5,6], amount=11 → 2 (5+6)")
        void testMultipleCoinTypesMinimumPath() {
            assertEquals(2, coinChange.topDownWithMemoizationSolution(new int[]{1, 5, 6}, 11));
        }

        @Test
        @DisplayName("Amount equals one coin: coins=[2,5,10], amount=2 → 1")
        void testAmountEqualsOneCoin() {
            assertEquals(1, coinChange.topDownWithMemoizationSolution(new int[]{2, 5, 10}, 2));
        }

        @Test
        @DisplayName("Large amount: coins=[1,2,5], amount=100 → 20 (5×20)")
        void testLargeAmount() {
            assertEquals(20, coinChange.topDownWithMemoizationSolution(new int[]{1, 2, 5}, 100));
        }

        @Test
        @DisplayName("Larger coins: coins=[186,419,83,408], amount=6249 → 20")
        void testLargeCoinsAndAmount() {
            assertEquals(20, coinChange.topDownWithMemoizationSolution(new int[]{186, 419, 83, 408}, 6249));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpSolution()  — bottom-up DP (tabulation), O(n*amount) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpSolution() — bottom-up DP with tabulation")
    class BottomUpSolutionTests {

        @Test
        @DisplayName("amount=0 → 0 (no coins needed)")
        void testZeroAmount() {
            assertEquals(0, coinChange.bottomUpSolution(new int[]{1, 2, 5}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 1: coins=[1,2,5], amount=11 → 3 (5+5+1)")
        void testLeetCodeExample1() {
            assertEquals(3, coinChange.bottomUpSolution(new int[]{1, 2, 5}, 11));
        }

        @Test
        @DisplayName("LeetCode Example 2: coins=[2], amount=3 → -1 (impossible)")
        void testLeetCodeExample2Impossible() {
            assertEquals(-1, coinChange.bottomUpSolution(new int[]{2}, 3));
        }

        @Test
        @DisplayName("Single coin exact match: coins=[5], amount=5 → 1")
        void testSingleCoinExactMatch() {
            assertEquals(1, coinChange.bottomUpSolution(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Single coin multiple uses: coins=[5], amount=10 → 2")
        void testSingleCoinMultipleUses() {
            assertEquals(2, coinChange.bottomUpSolution(new int[]{5}, 10));
        }

        @Test
        @DisplayName("All coins larger than amount: coins=[5,10], amount=3 → -1")
        void testAllCoinsLargerThanAmount() {
            assertEquals(-1, coinChange.bottomUpSolution(new int[]{5, 10}, 3));
        }

        @Test
        @DisplayName("DP vs greedy divergence: coins=[1,3,4], amount=6 → 2 (3+3, not 4+1+1)")
        void testDpVsGreedyDivergence() {
            assertEquals(2, coinChange.bottomUpSolution(new int[]{1, 3, 4}, 6));
        }

        @Test
        @DisplayName("Multiple coin types, minimum path: coins=[1,5,6], amount=11 → 2 (5+6)")
        void testMultipleCoinTypesMinimumPath() {
            assertEquals(2, coinChange.bottomUpSolution(new int[]{1, 5, 6}, 11));
        }

        @Test
        @DisplayName("Amount equals one coin: coins=[2,5,10], amount=2 → 1")
        void testAmountEqualsOneCoin() {
            assertEquals(1, coinChange.bottomUpSolution(new int[]{2, 5, 10}, 2));
        }

        @Test
        @DisplayName("Large amount: coins=[1,2,5], amount=100 → 20 (5×20)")
        void testLargeAmount() {
            assertEquals(20, coinChange.bottomUpSolution(new int[]{1, 2, 5}, 100));
        }

        @Test
        @DisplayName("Larger coins: coins=[186,419,83,408], amount=6249 → 20")
        void testLargeCoinsAndAmount() {
            assertEquals(20, coinChange.bottomUpSolution(new int[]{186, 419, 83, 408}, 6249));
        }
    }
}
