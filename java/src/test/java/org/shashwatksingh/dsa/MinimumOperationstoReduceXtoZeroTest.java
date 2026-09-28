package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("MinimumOperationstoReduceXtoZero Tests")
class MinimumOperationstoReduceXtoZeroTest {

    private MinimumOperationstoReduceXtoZero instance;

    @BeforeEach
    void setUp() {
        instance = new MinimumOperationstoReduceXtoZero();
    }

    private static int[] uniformArray(int length, int value) {
        int[] arr = new int[length];
        Arrays.fill(arr, value);
        return arr;
    }

    // ═══════════════════════════════════════════════════════════
    //  minOperationsSolution()  — direct two-pointer, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("minOperationsSolution() — direct two-pointer")
    class MinOperationsSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,1,4,2,3], x=5 → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.minOperationsSolution(new int[]{1, 1, 4, 2, 3}, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5,6,7,8,9], x=4 → -1 (no valid combination)")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.minOperationsSolution(new int[]{5, 6, 7, 8, 9}, 4));
        }

        @Test
        @DisplayName("LeetCode Example 3: [3,2,20,1,1,3], x=10 → 5")
        void testLeetCodeExample3() {
            assertEquals(5, instance.minOperationsSolution(new int[]{3, 2, 20, 1, 1, 3}, 10));
        }

        @Test
        @DisplayName("Minimum-size input, exact match: [5], x=5 → 1")
        void testMinimumSizeExactMatch() {
            assertEquals(1, instance.minOperationsSolution(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Minimum-size input, no valid answer: [5], x=3 → -1")
        void testMinimumSizeNoMatch() {
            assertEquals(-1, instance.minOperationsSolution(new int[]{5}, 3));
        }

        @Test
        @DisplayName("All-same values: [4,4,4,4], x=8 → 2 (remove any two 4's)")
        void testAllSameValues() {
            assertEquals(2, instance.minOperationsSolution(new int[]{4, 4, 4, 4}, 8));
        }

        @Test
        @DisplayName("Full removal required: [1,2,3,4], x=10 (== total sum) → 4")
        void testFullRemovalRequired() {
            assertEquals(4, instance.minOperationsSolution(new int[]{1, 2, 3, 4}, 10));
        }

        @Test
        @DisplayName("Asymmetric one-sided removal: [1,2,3,10,4], x=6 → 3 (remove leftmost three only)")
        void testAsymmetricOneSidedRemoval() {
            assertEquals(3, instance.minOperationsSolution(new int[]{1, 2, 3, 10, 4}, 6));
        }

        @Test
        @DisplayName("Max constraint boundary: 100000 elements at max value 10000, x sized for 40000 removals → 40000")
        void testMaxConstraintBoundary() {
            int n = 100000;
            int k = 40000;
            int[] nums = uniformArray(n, 10000);
            assertEquals(k, instance.minOperationsSolution(nums, 10000 * k));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  minOperationsSolution2()  — total-sum/target sliding window, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("minOperationsSolution2() — total-sum/target sliding window")
    class MinOperationsSolution2Tests {

        @Test
        @DisplayName("LeetCode Example 1: [1,1,4,2,3], x=5 → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.minOperationsSolution2(new int[]{1, 1, 4, 2, 3}, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5,6,7,8,9], x=4 → -1 (no valid combination)")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.minOperationsSolution2(new int[]{5, 6, 7, 8, 9}, 4));
        }

        @Test
        @DisplayName("LeetCode Example 3: [3,2,20,1,1,3], x=10 → 5")
        void testLeetCodeExample3() {
            assertEquals(5, instance.minOperationsSolution2(new int[]{3, 2, 20, 1, 1, 3}, 10));
        }

        @Test
        @DisplayName("Minimum-size input, exact match: [5], x=5 → 1")
        void testMinimumSizeExactMatch() {
            assertEquals(1, instance.minOperationsSolution2(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Minimum-size input, no valid answer: [5], x=3 → -1")
        void testMinimumSizeNoMatch() {
            assertEquals(-1, instance.minOperationsSolution2(new int[]{5}, 3));
        }

        @Test
        @DisplayName("All-same values: [4,4,4,4], x=8 → 2 (remove any two 4's)")
        void testAllSameValues() {
            assertEquals(2, instance.minOperationsSolution2(new int[]{4, 4, 4, 4}, 8));
        }

        @Test
        @DisplayName("Full removal required: [1,2,3,4], x=10 (== total sum) → 4")
        void testFullRemovalRequired() {
            assertEquals(4, instance.minOperationsSolution2(new int[]{1, 2, 3, 4}, 10));
        }

        @Test
        @DisplayName("Asymmetric one-sided removal: [1,2,3,10,4], x=6 → 3 (remove leftmost three only)")
        void testAsymmetricOneSidedRemoval() {
            assertEquals(3, instance.minOperationsSolution2(new int[]{1, 2, 3, 10, 4}, 6));
        }

        @Test
        @DisplayName("Max constraint boundary: 100000 elements at max value 10000, x sized for 40000 removals → 40000")
        void testMaxConstraintBoundary() {
            int n = 100000;
            int k = 40000;
            int[] nums = uniformArray(n, 10000);
            assertEquals(k, instance.minOperationsSolution2(nums, 10000 * k));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  minOperationsSolution1()  — brute-force prefix sums, O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("minOperationsSolution1() — brute-force prefix sums")
    class MinOperationsSolution1Tests {

        @Test
        @DisplayName("LeetCode Example 1: [1,1,4,2,3], x=5 → 2")
        void testLeetCodeExample1() {
            assertEquals(2, instance.minOperationsSolution1(new int[]{1, 1, 4, 2, 3}, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5,6,7,8,9], x=4 → -1 (no valid combination)")
        void testLeetCodeExample2() {
            assertEquals(-1, instance.minOperationsSolution1(new int[]{5, 6, 7, 8, 9}, 4));
        }

        @Test
        @DisplayName("LeetCode Example 3: [3,2,20,1,1,3], x=10 → 5")
        void testLeetCodeExample3() {
            assertEquals(5, instance.minOperationsSolution1(new int[]{3, 2, 20, 1, 1, 3}, 10));
        }

        @Test
        @DisplayName("Minimum-size input, exact match: [5], x=5 → 1")
        void testMinimumSizeExactMatch() {
            assertEquals(1, instance.minOperationsSolution1(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Minimum-size input, no valid answer: [5], x=3 → -1")
        void testMinimumSizeNoMatch() {
            assertEquals(-1, instance.minOperationsSolution1(new int[]{5}, 3));
        }

        @Test
        @DisplayName("All-same values: [4,4,4,4], x=8 → 2 (remove any two 4's)")
        void testAllSameValues() {
            assertEquals(2, instance.minOperationsSolution1(new int[]{4, 4, 4, 4}, 8));
        }

        @Test
        @DisplayName("Full removal required: [1,2,3,4], x=10 (== total sum) → 4")
        void testFullRemovalRequired() {
            assertEquals(4, instance.minOperationsSolution1(new int[]{1, 2, 3, 4}, 10));
        }

        @Test
        @DisplayName("Asymmetric one-sided removal: [1,2,3,10,4], x=6 → 3 (remove leftmost three only)")
        void testAsymmetricOneSidedRemoval() {
            assertEquals(3, instance.minOperationsSolution1(new int[]{1, 2, 3, 10, 4}, 6));
        }

        @Test
        @DisplayName("Large input (2000 elements at max value 10000, x sized for 800 removals → 800) — sized down from the 10^5 constraint for O(n^2) runtime")
        void testLargeInput() {
            int n = 2000;
            int k = 800;
            int[] nums = uniformArray(n, 10000);
            assertEquals(k, instance.minOperationsSolution1(nums, 10000 * k));
        }
    }
}
