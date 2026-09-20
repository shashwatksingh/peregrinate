package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("House Robber Tests")
class HouseRobberTest {

    private HouseRobber houseRobber;

    @BeforeEach
    void setUp() {
        houseRobber = new HouseRobber();
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
            assertEquals(5, houseRobber.bruteForce(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber.bruteForce(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 4")
        void testExample1() {
            assertEquals(4, houseRobber.bruteForce(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,7,9,3,1] → 12")
        void testExample2() {
            assertEquals(12, houseRobber.bruteForce(new int[]{2, 7, 9, 3, 1}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber.bruteForce(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6")
        void testAllSameValues() {
            assertEquals(6, houseRobber.bruteForce(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 9")
        void testStrictlyIncreasing() {
            assertEquals(9, houseRobber.bruteForce(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Max constraint values: [400,400,400,400,400] → 1200")
        void testMaxConstraintValues() {
            assertEquals(1200, houseRobber.bruteForce(new int[]{400, 400, 400, 400, 400}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  topDownWithTabulation()  — top-down DP, O(n) time & space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("topDownWithTabulation() — top-down DP O(n)")
    class TopDownWithTabulationTests {

        @Test
        @DisplayName("Single house: [5] → 5 (min constraint)")
        void testSingleHouse() {
            assertEquals(5, houseRobber.topDownWithTabulation(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber.topDownWithTabulation(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 4")
        void testExample1() {
            assertEquals(4, houseRobber.topDownWithTabulation(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,7,9,3,1] → 12")
        void testExample2() {
            assertEquals(12, houseRobber.topDownWithTabulation(new int[]{2, 7, 9, 3, 1}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber.topDownWithTabulation(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6")
        void testAllSameValues() {
            assertEquals(6, houseRobber.topDownWithTabulation(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 9")
        void testStrictlyIncreasing() {
            assertEquals(9, houseRobber.topDownWithTabulation(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Max constraint values: [400,400,400,400,400] → 1200")
        void testMaxConstraintValues() {
            assertEquals(1200, houseRobber.topDownWithTabulation(new int[]{400, 400, 400, 400, 400}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpWithTabulation()  — bottom-up DP, O(n) time & space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpWithTabulation() — bottom-up DP O(n)")
    class BottomUpWithTabulationTests {

        @Test
        @DisplayName("Single house: [5] → 5 (min constraint)")
        void testSingleHouse() {
            assertEquals(5, houseRobber.bottomUpWithTabulation(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber.bottomUpWithTabulation(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 4")
        void testExample1() {
            assertEquals(4, houseRobber.bottomUpWithTabulation(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,7,9,3,1] → 12")
        void testExample2() {
            assertEquals(12, houseRobber.bottomUpWithTabulation(new int[]{2, 7, 9, 3, 1}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber.bottomUpWithTabulation(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6")
        void testAllSameValues() {
            assertEquals(6, houseRobber.bottomUpWithTabulation(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 9")
        void testStrictlyIncreasing() {
            assertEquals(9, houseRobber.bottomUpWithTabulation(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Max constraint values: [400,400,400,400,400] → 1200")
        void testMaxConstraintValues() {
            assertEquals(1200, houseRobber.bottomUpWithTabulation(new int[]{400, 400, 400, 400, 400}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpWithSpaceOptimisation()  — O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpWithSpaceOptimisation() — O(n) time, O(1) space")
    class BottomUpWithSpaceOptimisationTests {

        @Test
        @DisplayName("Single house: [5] → 5 (min constraint)")
        void testSingleHouse() {
            assertEquals(5, houseRobber.bottomUpWithSpaceOptimisation(new int[]{5}));
        }

        @Test
        @DisplayName("Two houses — pick richer: [2, 7] → 7")
        void testTwoHouses() {
            assertEquals(7, houseRobber.bottomUpWithSpaceOptimisation(new int[]{2, 7}));
        }

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,1] → 4")
        void testExample1() {
            assertEquals(4, houseRobber.bottomUpWithSpaceOptimisation(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,7,9,3,1] → 12")
        void testExample2() {
            assertEquals(12, houseRobber.bottomUpWithSpaceOptimisation(new int[]{2, 7, 9, 3, 1}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0, houseRobber.bottomUpWithSpaceOptimisation(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All same values: [3,3,3,3] → 6")
        void testAllSameValues() {
            assertEquals(6, houseRobber.bottomUpWithSpaceOptimisation(new int[]{3, 3, 3, 3}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 9")
        void testStrictlyIncreasing() {
            assertEquals(9, houseRobber.bottomUpWithSpaceOptimisation(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Max constraint values: [400,400,400,400,400] → 1200")
        void testMaxConstraintValues() {
            assertEquals(1200, houseRobber.bottomUpWithSpaceOptimisation(new int[]{400, 400, 400, 400, 400}));
        }
    }
}
