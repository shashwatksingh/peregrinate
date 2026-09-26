package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Continuous Subarray Sum Tests")
class ContinousSubArraySumTest {

    private ContinousSubArraySum instance;

    @BeforeEach
    void setUp() {
        instance = new ContinousSubArraySum();
    }

    // ═══════════════════════════════════════════════════════════
    //  checkSubarraySumPrefixMethod()  — O(n^2) prefix-sum pair scan
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("checkSubarraySumPrefixMethod()")
    class CheckSubarraySumPrefixMethodTests {

        @Test
        @DisplayName("LeetCode Example 1: nums=[23,2,4,6,7], k=6 → true ([2,4] sums to 6)")
        void testLeetCodeExample1() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{23, 2, 4, 6, 7}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums=[23,2,6,4,7], k=6 → true (whole array sums to 42)")
        void testLeetCodeExample2() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{23, 2, 6, 4, 7}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: nums=[23,2,6,4,7], k=13 → false")
        void testLeetCodeExample3() {
            assertEquals(false, instance.checkSubarraySumPrefixMethod(new int[]{23, 2, 6, 4, 7}, 13));
        }

        @Test
        @DisplayName("Minimum-size input: nums=[7], k=3 → false (no subarray of length >= 2 exists)")
        void testMinimumSizeInput() {
            assertEquals(false, instance.checkSubarraySumPrefixMethod(new int[]{7}, 3));
        }

        @Test
        @DisplayName("All-same values: nums=[1,1,1,1], k=2 → true (any adjacent pair sums to 2)")
        void testAllSameValues() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{1, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Strictly increasing sequence: nums=[1,2,3,4,5,6], k=5 → true ([2,3] sums to 5)")
        void testStrictlyIncreasingSequence() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{1, 2, 3, 4, 5, 6}, 5));
        }

        @Test
        @DisplayName("Strictly decreasing sequence: nums=[10,9,8,7], k=4 → true ([9,8,7] sums to 24)")
        void testStrictlyDecreasingSequence() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{10, 9, 8, 7}, 4));
        }

        @Test
        @DisplayName("No valid answer: nums=[1,2,3,4,5], k=100 → false (every subarray sum is below k)")
        void testNoValidAnswer() {
            assertEquals(false, instance.checkSubarraySumPrefixMethod(new int[]{1, 2, 3, 4, 5}, 100));
        }

        @Test
        @DisplayName("Max constraint boundary values: nums=[1e9,3,1e9], k=1e9 → false")
        void testMaxConstraintBoundaryValues() {
            assertEquals(false, instance.checkSubarraySumPrefixMethod(
                    new int[]{1_000_000_000, 3, 1_000_000_000}, 1_000_000_000));
        }

        @Test
        @DisplayName("nums=[5,5], k=5 → true")
        void testWholeArrayStartingAtIndexZeroIsNeverChecked() {
            assertEquals(true, instance.checkSubarraySumPrefixMethod(new int[]{5, 5}, 5));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  checkSubarraySum()  — O(n) running-mod / HashMap approach
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("checkSubarraySum()")
    class CheckSubarraySumTests {

        @Test
        @DisplayName("LeetCode Example 1: nums=[23,2,4,6,7], k=6 → true ([2,4] sums to 6)")
        void testLeetCodeExample1() {
            assertEquals(true, instance.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums=[23,2,6,4,7], k=6 → true (whole array sums to 42)")
        void testLeetCodeExample2() {
            assertEquals(true, instance.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: nums=[23,2,6,4,7], k=13 → false")
        void testLeetCodeExample3() {
            assertEquals(false, instance.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
        }

        @Test
        @DisplayName("Minimum-size input: nums=[7], k=3 → false (no subarray of length >= 2 exists)")
        void testMinimumSizeInput() {
            assertEquals(false, instance.checkSubarraySum(new int[]{7}, 3));
        }

        @Test
        @DisplayName("All-same values: nums=[1,1,1,1], k=2 → true (any adjacent pair sums to 2)")
        void testAllSameValues() {
            assertEquals(true, instance.checkSubarraySum(new int[]{1, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Strictly increasing sequence: nums=[1,2,3,4,5,6], k=5 → true ([2,3] sums to 5)")
        void testStrictlyIncreasingSequence() {
            assertEquals(true, instance.checkSubarraySum(new int[]{1, 2, 3, 4, 5, 6}, 5));
        }

        @Test
        @DisplayName("Strictly decreasing sequence: nums=[10,9,8,7], k=4 → true ([9,8,7] sums to 24)")
        void testStrictlyDecreasingSequence() {
            assertEquals(true, instance.checkSubarraySum(new int[]{10, 9, 8, 7}, 4));
        }

        @Test
        @DisplayName("No valid answer: nums=[1,2,3,4,5], k=100 → false (every subarray sum is below k)")
        void testNoValidAnswer() {
            assertEquals(false, instance.checkSubarraySum(new int[]{1, 2, 3, 4, 5}, 100));
        }

        @Test
        @DisplayName("Max constraint boundary values: nums=[1e9,3,1e9], k=1e9 → false")
        void testMaxConstraintBoundaryValues() {
            assertEquals(false, instance.checkSubarraySum(
                    new int[]{1_000_000_000, 3, 1_000_000_000}, 1_000_000_000));
        }

        @Test
        @DisplayName("nums=[5,5], k=5 → true")
        void testEarliestModIndexGetsOverwrittenBeforeMatchIsFound() {
            assertEquals(true, instance.checkSubarraySum(new int[]{5, 5}, 5));
        }
    }
}
