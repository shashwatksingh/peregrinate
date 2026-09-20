package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Longest Consecutive Sequence Tests")
class LongestConsecutiveSequenceTest {

    private LongestConsecutiveSequence lcs;

    @BeforeEach
    void setUp() {
        lcs = new LongestConsecutiveSequence();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // bruteForce
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("bruteForce()")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [100,4,200,1,3,2] → 4")
        void testLeetCodeExample1() {
            assertEquals(4, lcs.bruteForce(new int[]{100, 4, 200, 1, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [0,3,7,2,5,8,4,6,0,1] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, lcs.bruteForce(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        }

        @Test
        @DisplayName("Duplicates in input: [1,0,1,2] → 3")
        void testDuplicatesInInput() {
            assertEquals(3, lcs.bruteForce(new int[]{1, 0, 1, 2}));
        }

        @Test
        @DisplayName("Single element: [42] → 1")
        void testSingleElement() {
            assertEquals(1, lcs.bruteForce(new int[]{42}));
        }

        @Test
        @DisplayName("All same elements: [3,3,3] → 1")
        void testAllDuplicates() {
            assertEquals(1, lcs.bruteForce(new int[]{3, 3, 3}));
        }

        @Test
        @DisplayName("Already consecutive: [1,2,3,4,5] → 5")
        void testAlreadyConsecutive() {
            assertEquals(5, lcs.bruteForce(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("No consecutive pairs: [1,10,100] → 1")
        void testNoConsecutivePairs() {
            assertEquals(1, lcs.bruteForce(new int[]{1, 10, 100}));
        }

        @Test
        @DisplayName("Negative numbers: [-3,-2,-1,0,1] → 5")
        void testNegativeNumbers() {
            assertEquals(5, lcs.bruteForce(new int[]{-3, -2, -1, 0, 1}));
        }

        @Test
        @DisplayName("Mixed negative and positive: [-1,0,1] → 3")
        void testMixedNegativeAndPositive() {
            assertEquals(3, lcs.bruteForce(new int[]{-1, 0, 1}));
        }

        @Test
        @DisplayName("Two separate sequences, longer wins: [1,2,3,10,11,12,13] → 4")
        void testTwoSeparateSequences() {
            assertEquals(4, lcs.bruteForce(new int[]{1, 2, 3, 10, 11, 12, 13}));
        }

        @Test
        @DisplayName("Empty array → 0")
        void testEmptyArray() {
            assertEquals(0, lcs.bruteForce(new int[]{}));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // solutionSorting
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("solutionSorting()")
    class SolutionSortingTests {

        @Test
        @DisplayName("LeetCode Example 1: [100,4,200,1,3,2] → 4")
        void testLeetCodeExample1() {
            assertEquals(4, lcs.solutionSorting(new int[]{100, 4, 200, 1, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [0,3,7,2,5,8,4,6,0,1] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, lcs.solutionSorting(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        }

        @Test
        @DisplayName("Duplicates in input: [1,0,1,2] → 3")
        void testDuplicatesInInput() {
            assertEquals(3, lcs.solutionSorting(new int[]{1, 0, 1, 2}));
        }

        @Test
        @DisplayName("Single element: [42] → 1")
        void testSingleElement() {
            assertEquals(1, lcs.solutionSorting(new int[]{42}));
        }

        @Test
        @DisplayName("All same elements: [3,3,3] → 1")
        void testAllDuplicates() {
            assertEquals(1, lcs.solutionSorting(new int[]{3, 3, 3}));
        }

        @Test
        @DisplayName("Already consecutive: [1,2,3,4,5] → 5")
        void testAlreadyConsecutive() {
            assertEquals(5, lcs.solutionSorting(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("No consecutive pairs: [1,10,100] → 1")
        void testNoConsecutivePairs() {
            assertEquals(1, lcs.solutionSorting(new int[]{1, 10, 100}));
        }

        @Test
        @DisplayName("Negative numbers: [-3,-2,-1,0,1] → 5")
        void testNegativeNumbers() {
            assertEquals(5, lcs.solutionSorting(new int[]{-3, -2, -1, 0, 1}));
        }

        @Test
        @DisplayName("Mixed negative and positive: [-1,0,1] → 3")
        void testMixedNegativeAndPositive() {
            assertEquals(3, lcs.solutionSorting(new int[]{-1, 0, 1}));
        }

        @Test
        @DisplayName("Two separate sequences, longer wins: [1,2,3,10,11,12,13] → 4")
        void testTwoSeparateSequences() {
            assertEquals(4, lcs.solutionSorting(new int[]{1, 2, 3, 10, 11, 12, 13}));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // solutionSets
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("solutionSets()")
    class SolutionSetsTests {

        @Test
        @DisplayName("LeetCode Example 1: [100,4,200,1,3,2] → 4")
        void testLeetCodeExample1() {
            assertEquals(4, lcs.solutionSets(new int[]{100, 4, 200, 1, 3, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [0,3,7,2,5,8,4,6,0,1] → 9")
        void testLeetCodeExample2() {
            assertEquals(9, lcs.solutionSets(new int[]{0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        }

        @Test
        @DisplayName("Duplicates in input: [1,0,1,2] → 3")
        void testDuplicatesInInput() {
            assertEquals(3, lcs.solutionSets(new int[]{1, 0, 1, 2}));
        }

        @Test
        @DisplayName("Single element: [42] → 1")
        void testSingleElement() {
            assertEquals(1, lcs.solutionSets(new int[]{42}));
        }

        @Test
        @DisplayName("All same elements: [3,3,3] → 1")
        void testAllDuplicates() {
            assertEquals(1, lcs.solutionSets(new int[]{3, 3, 3}));
        }

        @Test
        @DisplayName("Already consecutive: [1,2,3,4,5] → 5")
        void testAlreadyConsecutive() {
            assertEquals(5, lcs.solutionSets(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("No consecutive pairs: [1,10,100] → 1")
        void testNoConsecutivePairs() {
            assertEquals(1, lcs.solutionSets(new int[]{1, 10, 100}));
        }

        @Test
        @DisplayName("Negative numbers: [-3,-2,-1,0,1] → 5")
        void testNegativeNumbers() {
            assertEquals(5, lcs.solutionSets(new int[]{-3, -2, -1, 0, 1}));
        }

        @Test
        @DisplayName("Mixed negative and positive: [-1,0,1] → 3")
        void testMixedNegativeAndPositive() {
            assertEquals(3, lcs.solutionSets(new int[]{-1, 0, 1}));
        }

        @Test
        @DisplayName("Two separate sequences, longer wins: [1,2,3,10,11,12,13] → 4")
        void testTwoSeparateSequences() {
            assertEquals(4, lcs.solutionSets(new int[]{1, 2, 3, 10, 11, 12, 13}));
        }

        @Test
        @DisplayName("Empty array → 0")
        void testEmptyArray() {
            assertEquals(0, lcs.solutionSets(new int[]{}));
        }
    }
}