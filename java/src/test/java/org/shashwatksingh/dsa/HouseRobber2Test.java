package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("House Robber II Tests")
class HouseRobber2Test {

    private HouseRobber2 houseRobber2;

    @BeforeEach
    void setUp() {
        houseRobber2 = new HouseRobber2();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — plain recursion, O(2^n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — plain recursion O(2^n)")
    class BruteForceTests {

        @Test
        @DisplayName("Single house: [5] → 5 (min constraint)")
        void testSingleHouse() {
            assertEquals(5, houseRobber2.bruteForce(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber2.bruteForce(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [2,3,2] → 3 (first & last adjacent — can't rob both)")
        void testExample1() {
            assertEquals(3, houseRobber2.bruteForce(new int[]{2, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,1] → 4 (rob house 1 + house 3)")
        void testExample2() {
            assertEquals(4, houseRobber2.bruteForce(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,3] → 3 (rob house 3 only)")
        void testExample3() {
            assertEquals(3, houseRobber2.bruteForce(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber2.bruteForce(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6 (pick two non-adjacent)")
        void testAllSameValues() {
            assertEquals(6, houseRobber2.bruteForce(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 8 (rob house 3 + house 5, exclude first)")
        void testStrictlyIncreasing() {
            assertEquals(8, houseRobber2.bruteForce(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Alternating high-low: [5,1,5,1,5] → 10 (rob two 5s from either range)")
        void testAlternatingHighLow() {
            assertEquals(10, houseRobber2.bruteForce(new int[]{5, 1, 5, 1, 5}));
        }

        @Test
        @DisplayName("Max constraint: [1000,1,1000] → 1000 (first & last adjacent in circle)")
        void testMaxConstraint() {
            assertEquals(1000, houseRobber2.bruteForce(new int[]{1000, 1, 1000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpSolution()  — bottom-up DP, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpSolution() — bottom-up DP O(n) time O(n) space")
    class BottomUpSolutionTests {

        @Test
        @DisplayName("Single house: [5] → 5 (min constraint)")
        void testSingleHouse() {
            assertEquals(5, houseRobber2.bottomUpSolution(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber2.bottomUpSolution(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [2,3,2] → 3 (first & last adjacent — can't rob both)")
        void testExample1() {
            assertEquals(3, houseRobber2.bottomUpSolution(new int[]{2, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,1] → 4 (rob house 1 + house 3)")
        void testExample2() {
            assertEquals(4, houseRobber2.bottomUpSolution(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,3] → 3 (rob house 3 only)")
        void testExample3() {
            assertEquals(3, houseRobber2.bottomUpSolution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber2.bottomUpSolution(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6 (pick two non-adjacent)")
        void testAllSameValues() {
            assertEquals(6, houseRobber2.bottomUpSolution(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 8 (rob house 3 + house 5, exclude first)")
        void testStrictlyIncreasing() {
            assertEquals(8, houseRobber2.bottomUpSolution(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Alternating high-low: [5,1,5,1,5] → 10 (rob two 5s from either range)")
        void testAlternatingHighLow() {
            assertEquals(10, houseRobber2.bottomUpSolution(new int[]{5, 1, 5, 1, 5}));
        }

        @Test
        @DisplayName("Max constraint: [1000,1,1000] → 1000 (first & last adjacent in circle)")
        void testMaxConstraint() {
            assertEquals(1000, houseRobber2.bottomUpSolution(new int[]{1000, 1, 1000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpSolutionSpaceOptimization()  — O(n) time, O(1) space
    //  NOTE: Three known bugs in bottomUpSpaceOptimizedCalculation():
    //    1. n=1  — no n==1 guard; second call reads nums[1] → ArrayIndexOutOfBoundsException.
    //    2. n=2  — size=1, loop never runs; returns prev2=0 instead of max element.
    //    3. n>=4 — variable swap is wrong: code does prev1=prev2; prev2=third
    //              but it should be prev2=prev1; prev1=third, corrupting the recurrence.
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpSolutionSpaceOptimization() — O(n) time, O(1) space")
    class BottomUpSolutionSpaceOptimizationTests {

        @Test
        @DisplayName("Single house: [5] → 5 [BUG: throws ArrayIndexOutOfBoundsException — missing n==1 guard]")
        void testSingleHouse() {
            assertEquals(5, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses: [2,7] → 7 [BUG: returns 0 — loop skipped for size=1, prev2 never updated]")
        void testTwoHouses() {
            assertEquals(7, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [2,3,2] → 3 (n=3, single loop iteration — swap bug does not manifest)")
        void testExample1() {
            assertEquals(3, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{2, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,1] → 4 [BUG: wrong variable swap gives 5]")
        void testExample2() {
            assertEquals(4, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,3] → 3 (n=3, single loop iteration — swap bug does not manifest)")
        void testExample3() {
            assertEquals(3, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0 (n=3, single loop iteration — swap bug does not manifest)")
        void testAllZeros() {
            assertEquals(0, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6 [BUG: wrong swap — coincidentally correct here]")
        void testAllSameValues() {
            assertEquals(6, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 8 [BUG: wrong variable swap gives 12]")
        void testStrictlyIncreasing() {
            assertEquals(8, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Alternating high-low: [5,1,5,1,5] → 10 [BUG: wrong variable swap gives 11]")
        void testAlternatingHighLow() {
            assertEquals(10, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{5, 1, 5, 1, 5}));
        }

        @Test
        @DisplayName("Max constraint: [1000,1,1000] → 1000 (n=3, single loop iteration — swap bug does not manifest)")
        void testMaxConstraint() {
            assertEquals(1000, houseRobber2.bottomUpSolutionSpaceOptimization(new int[]{1000, 1, 1000}));
        }
    }
}
