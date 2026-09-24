package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SingleNumber Tests")
class SingleNumberTest {

    private SingleNumber instance;

    @BeforeEach
    void setUp() {
        instance = new SingleNumber();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — nested loop excluding self-comparison, O(n²) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — nested loop, skips comparing nums[i] to itself")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.bruteForce(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.bruteForce(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.bruteForce(new int[]{1}));
        }

        @Test
        @DisplayName("Singleton in the middle: [3,5,3] → 5")
        void testSingletonInMiddle() {
            assertEquals(5, instance.bruteForce(new int[]{3, 5, 3}));
        }

        @Test
        @DisplayName("Negative values: [-5,-5,-3] → -3")
        void testNegativeValues() {
            assertEquals(-3, instance.bruteForce(new int[]{-5, -5, -3}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.bruteForce(new int[]{30000, -30000, 30000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  twoPointerSolution()  — sorts then scans adjacent pairs, O(n log n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoPointerSolution() — sorts input, then finds the broken adjacent pair")
    class TwoPointerSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.twoPointerSolution(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.twoPointerSolution(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.twoPointerSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Scrambled, non-adjacent duplicates: [1,4,2,1,2] → 4 (sort fixes prior adjacency bug)")
        void testScrambledDuplicates() {
            assertEquals(4, instance.twoPointerSolution(new int[]{1, 4, 2, 1, 2}));
        }

        @Test
        @DisplayName("Singleton is the smallest value after sort: [3,1,3,2,2] → 1")
        void testSingletonBecomesSmallestAfterSort() {
            assertEquals(1, instance.twoPointerSolution(new int[]{3, 1, 3, 2, 2}));
        }

        @Test
        @DisplayName("Singleton is the largest value after sort: [2,1,2,4,1] → 4")
        void testSingletonBecomesLargestAfterSort() {
            assertEquals(4, instance.twoPointerSolution(new int[]{2, 1, 2, 4, 1}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.twoPointerSolution(new int[]{30000, -30000, 30000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  mathSolution()  — 2×(sum of unique values) − total sum, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("mathSolution() — 2×(sum of unique values) − total sum")
    class MathSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.mathSolution(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.mathSolution(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.mathSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Scrambled duplicates, non-adjacent: [1,4,2,1,2] → 4")
        void testScrambledDuplicates() {
            assertEquals(4, instance.mathSolution(new int[]{1, 4, 2, 1, 2}));
        }

        @Test
        @DisplayName("Negative values: [-5,-5,-3] → -3")
        void testNegativeValues() {
            assertEquals(-3, instance.mathSolution(new int[]{-5, -5, -3}));
        }

        @Test
        @DisplayName("Singleton is zero: [0,7,7] → 0")
        void testSingletonIsZero() {
            assertEquals(0, instance.mathSolution(new int[]{0, 7, 7}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.mathSolution(new int[]{30000, -30000, 30000}));
        }

        @Test
        @DisplayName("Larger array with many pairs: [5,3,9,3,5,7,9] → 7")
        void testLargerArrayManyPairs() {
            assertEquals(7, instance.mathSolution(new int[]{5, 3, 9, 3, 5, 7, 9}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  optimisedBinarySearchMethod()  — binary search over sorted pair boundaries, O(log n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("optimisedBinarySearchMethod() — binary search over sorted pair boundaries")
    class OptimisedBinarySearchMethodTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.optimisedBinarySearchMethod(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.optimisedBinarySearchMethod(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.optimisedBinarySearchMethod(new int[]{1}));
        }

        @Test
        @DisplayName("Singleton is the smallest value after sort: [0,1,1,2,2,3,3] → 0")
        void testSingletonAtStart() {
            assertEquals(0, instance.optimisedBinarySearchMethod(new int[]{0, 1, 1, 2, 2, 3, 3}));
        }

        @Test
        @DisplayName("Singleton is the largest value after sort: [1,1,2,2,3,3,4] → 4")
        void testSingletonAtEnd() {
            assertEquals(4, instance.optimisedBinarySearchMethod(new int[]{1, 1, 2, 2, 3, 3, 4}));
        }

        @Test
        @DisplayName("Singleton exactly in the middle pair position: [1,1,2,3,3] → 2")
        void testSingletonInMiddle() {
            assertEquals(2, instance.optimisedBinarySearchMethod(new int[]{1, 1, 2, 3, 3}));
        }

        @Test
        @DisplayName("Negative values: [-5,-5,-3] → -3")
        void testNegativeValues() {
            assertEquals(-3, instance.optimisedBinarySearchMethod(new int[]{-5, -5, -3}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.optimisedBinarySearchMethod(new int[]{30000, -30000, 30000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  mapSolution()  — frequency count via HashMap, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("mapSolution() — frequency count via HashMap")
    class MapSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.mapSolution(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.mapSolution(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.mapSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Scrambled duplicates, non-adjacent: [1,4,2,1,2] → 4")
        void testScrambledDuplicates() {
            assertEquals(4, instance.mapSolution(new int[]{1, 4, 2, 1, 2}));
        }

        @Test
        @DisplayName("Negative values: [-5,-5,-3] → -3")
        void testNegativeValues() {
            assertEquals(-3, instance.mapSolution(new int[]{-5, -5, -3}));
        }

        @Test
        @DisplayName("Singleton is zero: [0,7,7] → 0")
        void testSingletonIsZero() {
            assertEquals(0, instance.mapSolution(new int[]{0, 7, 7}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.mapSolution(new int[]{30000, -30000, 30000}));
        }

        @Test
        @DisplayName("Larger array with many pairs: [5,3,9,3,5,7,9] → 7")
        void testLargerArrayManyPairs() {
            assertEquals(7, instance.mapSolution(new int[]{5, 3, 9, 3, 5, 7, 9}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  xorOperatorSolution()  — bitwise XOR cancellation, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("xorOperatorSolution() — bitwise XOR cancellation")
    class XorOperatorSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,2,1] → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.xorOperatorSolution(new int[]{2, 2, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,1,2,1,2] → 4")
        void testLeetCodeExample2() {
            assertEquals(4, instance.xorOperatorSolution(new int[]{4, 1, 2, 1, 2}));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1] → 1")
        void testLeetCodeExample3() {
            assertEquals(1, instance.xorOperatorSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Scrambled duplicates, non-adjacent: [1,4,2,1,2] → 4")
        void testScrambledDuplicates() {
            assertEquals(4, instance.xorOperatorSolution(new int[]{1, 4, 2, 1, 2}));
        }

        @Test
        @DisplayName("Negative values: [-5,-5,-3] → -3")
        void testNegativeValues() {
            assertEquals(-3, instance.xorOperatorSolution(new int[]{-5, -5, -3}));
        }

        @Test
        @DisplayName("Singleton is zero: [0,7,7] → 0")
        void testSingletonIsZero() {
            assertEquals(0, instance.xorOperatorSolution(new int[]{0, 7, 7}));
        }

        @Test
        @DisplayName("Max constraint boundary values: [30000,-30000,30000] → -30000")
        void testBoundaryValues() {
            assertEquals(-30000, instance.xorOperatorSolution(new int[]{30000, -30000, 30000}));
        }

        @Test
        @DisplayName("Larger array with many pairs: [5,3,9,3,5,7,9] → 7")
        void testLargerArrayManyPairs() {
            assertEquals(7, instance.xorOperatorSolution(new int[]{5, 3, 9, 3, 5, 7, 9}));
        }
    }
}
