package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Maximum Alternating Sum of Squares Tests")
class MaximumAlternateSumofSquaresTest {

    private MaximumAlternateSumofSquares instance;

    @BeforeEach
    void setUp() {
        instance = new MaximumAlternateSumofSquares();
    }

    // ═══════════════════════════════════════════════════════════
    //  maxAlternatingSumStreams()  — sort squares via streams, two pointers, O(n log n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("maxAlternatingSumStreams() — sort squares via streams")
    class MaxAlternatingSumStreamsTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3] → 12")
        void testLeetCodeExample1() {
            assertEquals(12L, instance.maxAlternatingSumStreams(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,-1,2,-2,3,-3] → 16")
        void testLeetCodeExample2() {
            assertEquals(16L, instance.maxAlternatingSumStreams(new int[]{1, -1, 2, -2, 3, -3}));
        }

        @Test
        @DisplayName("Single positive element: [5] → 25")
        void testSinglePositive() {
            assertEquals(25L, instance.maxAlternatingSumStreams(new int[]{5}));
        }

        @Test
        @DisplayName("Single negative element: [-5] → 25")
        void testSingleNegative() {
            assertEquals(25L, instance.maxAlternatingSumStreams(new int[]{-5}));
        }

        @Test
        @DisplayName("Single zero: [0] → 0")
        void testSingleZero() {
            assertEquals(0L, instance.maxAlternatingSumStreams(new int[]{0}));
        }

        @Test
        @DisplayName("Two elements: [3,4] → 7")
        void testTwoElements() {
            assertEquals(7L, instance.maxAlternatingSumStreams(new int[]{3, 4}));
        }

        @Test
        @DisplayName("Two elements, mixed signs: [-3,4] → 7")
        void testTwoElementsMixedSigns() {
            assertEquals(7L, instance.maxAlternatingSumStreams(new int[]{-3, 4}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0L, instance.maxAlternatingSumStreams(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All-same values, even count: [2,2,2,2] → 0")
        void testAllSameEven() {
            assertEquals(0L, instance.maxAlternatingSumStreams(new int[]{2, 2, 2, 2}));
        }

        @Test
        @DisplayName("All-same values, odd count: [2,2,2] → 4")
        void testAllSameOdd() {
            assertEquals(4L, instance.maxAlternatingSumStreams(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 45")
        void testStrictlyIncreasing() {
            assertEquals(45L, instance.maxAlternatingSumStreams(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 45")
        void testStrictlyDecreasing() {
            assertEquals(45L, instance.maxAlternatingSumStreams(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("All negative values: [-1,-2,-3,-4] → 20")
        void testAllNegative() {
            assertEquals(20L, instance.maxAlternatingSumStreams(new int[]{-1, -2, -3, -4}));
        }

        @Test
        @DisplayName("Zeros mixed with a large value: [0,0,5,0] → 25")
        void testZerosWithLargeValue() {
            assertEquals(25L, instance.maxAlternatingSumStreams(new int[]{0, 0, 5, 0}));
        }

        @Test
        @DisplayName("Equal magnitudes with alternating signs: [3,-3,3,-3,3] → 9")
        void testEqualMagnitudeMixedSigns() {
            assertEquals(9L, instance.maxAlternatingSumStreams(new int[]{3, -3, 3, -3, 3}));
        }

        @Test
        @DisplayName("Max magnitude, single element: [40000] → 1600000000")
        void testMaxMagnitudeSingle() {
            assertEquals(1600000000L, instance.maxAlternatingSumStreams(new int[]{40000}));
        }

        @Test
        @DisplayName("Max magnitude, opposite signs: [40000,-40000] → 0")
        void testMaxMagnitudeOppositeSigns() {
            assertEquals(0L, instance.maxAlternatingSumStreams(new int[]{40000, -40000}));
        }

        @Test
        @DisplayName("Max magnitude, three elements: [40000,-40000,40000] → 1600000000")
        void testMaxMagnitudeThree() {
            assertEquals(1600000000L, instance.maxAlternatingSumStreams(new int[]{40000, -40000, 40000}));
        }

        @Test
        @DisplayName("Max size (n=100000), all 40000 → 0 (even count cancels out)")
        void testMaxSizeAllSameEven() {
            int[] nums = new int[100000];
            Arrays.fill(nums, 40000);
            assertEquals(0L, instance.maxAlternatingSumStreams(nums));
        }

        @Test
        @DisplayName("Max size (n=99999), all 40000 → 1600000000")
        void testMaxSizeAllSameOdd() {
            int[] nums = new int[99999];
            Arrays.fill(nums, 40000);
            assertEquals(1_600_000_000L, instance.maxAlternatingSumStreams(nums));
        }

        @Test
        @DisplayName("Max size (n=100000), 50000 values of ±40000 and 50000 zeros → 80000000000000 (exceeds int range)")
        void testMaxSizeSumExceedsIntRange() {
            int[] nums = new int[100000];
            for (int i = 0; i < 50000; i++) {
                nums[i] = i % 2 == 0 ? 40000 : -40000;
            }
            assertEquals(80_000_000_000_000L, instance.maxAlternatingSumStreams(nums));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  maxAlternatingSum()  — square in place, sort, two pointers, O(n log n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("maxAlternatingSum() — square in place")
    class MaxAlternatingSumTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3] → 12")
        void testLeetCodeExample1() {
            assertEquals(12L, instance.maxAlternatingSum(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,-1,2,-2,3,-3] → 16")
        void testLeetCodeExample2() {
            assertEquals(16L, instance.maxAlternatingSum(new int[]{1, -1, 2, -2, 3, -3}));
        }

        @Test
        @DisplayName("Single positive element: [5] → 25")
        void testSinglePositive() {
            assertEquals(25L, instance.maxAlternatingSum(new int[]{5}));
        }

        @Test
        @DisplayName("Single negative element: [-5] → 25")
        void testSingleNegative() {
            assertEquals(25L, instance.maxAlternatingSum(new int[]{-5}));
        }

        @Test
        @DisplayName("Single zero: [0] → 0")
        void testSingleZero() {
            assertEquals(0L, instance.maxAlternatingSum(new int[]{0}));
        }

        @Test
        @DisplayName("Two elements: [3,4] → 7")
        void testTwoElements() {
            assertEquals(7L, instance.maxAlternatingSum(new int[]{3, 4}));
        }

        @Test
        @DisplayName("Two elements, mixed signs: [-3,4] → 7")
        void testTwoElementsMixedSigns() {
            assertEquals(7L, instance.maxAlternatingSum(new int[]{-3, 4}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 0")
        void testAllZeros() {
            assertEquals(0L, instance.maxAlternatingSum(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("All-same values, even count: [2,2,2,2] → 0")
        void testAllSameEven() {
            assertEquals(0L, instance.maxAlternatingSum(new int[]{2, 2, 2, 2}));
        }

        @Test
        @DisplayName("All-same values, odd count: [2,2,2] → 4")
        void testAllSameOdd() {
            assertEquals(4L, instance.maxAlternatingSum(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → 45")
        void testStrictlyIncreasing() {
            assertEquals(45L, instance.maxAlternatingSum(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Strictly decreasing: [5,4,3,2,1] → 45")
        void testStrictlyDecreasing() {
            assertEquals(45L, instance.maxAlternatingSum(new int[]{5, 4, 3, 2, 1}));
        }

        @Test
        @DisplayName("All negative values: [-1,-2,-3,-4] → 20")
        void testAllNegative() {
            assertEquals(20L, instance.maxAlternatingSum(new int[]{-1, -2, -3, -4}));
        }

        @Test
        @DisplayName("Zeros mixed with a large value: [0,0,5,0] → 25")
        void testZerosWithLargeValue() {
            assertEquals(25L, instance.maxAlternatingSum(new int[]{0, 0, 5, 0}));
        }

        @Test
        @DisplayName("Equal magnitudes with alternating signs: [3,-3,3,-3,3] → 9")
        void testEqualMagnitudeMixedSigns() {
            assertEquals(9L, instance.maxAlternatingSum(new int[]{3, -3, 3, -3, 3}));
        }

        @Test
        @DisplayName("Max magnitude, single element: [40000] → 1600000000")
        void testMaxMagnitudeSingle() {
            assertEquals(1600000000L, instance.maxAlternatingSum(new int[]{40000}));
        }

        @Test
        @DisplayName("Max magnitude, opposite signs: [40000,-40000] → 0")
        void testMaxMagnitudeOppositeSigns() {
            assertEquals(0L, instance.maxAlternatingSum(new int[]{40000, -40000}));
        }

        @Test
        @DisplayName("Max magnitude, three elements: [40000,-40000,40000] → 1600000000")
        void testMaxMagnitudeThree() {
            assertEquals(1600000000L, instance.maxAlternatingSum(new int[]{40000, -40000, 40000}));
        }

        @Test
        @DisplayName("Max size (n=100000), all 40000 → 0 (even count cancels out)")
        void testMaxSizeAllSameEven() {
            int[] nums = new int[100000];
            Arrays.fill(nums, 40000);
            assertEquals(0L, instance.maxAlternatingSum(nums));
        }

        @Test
        @DisplayName("Max size (n=99999), all 40000 → 1600000000")
        void testMaxSizeAllSameOdd() {
            int[] nums = new int[99999];
            Arrays.fill(nums, 40000);
            assertEquals(1_600_000_000L, instance.maxAlternatingSum(nums));
        }

        @Test
        @DisplayName("Max size (n=100000), 50000 values of ±40000 and 50000 zeros → 80000000000000 (exceeds int range)")
        void testMaxSizeSumExceedsIntRange() {
            int[] nums = new int[100000];
            for (int i = 0; i < 50000; i++) {
                nums[i] = i % 2 == 0 ? 40000 : -40000;
            }
            assertEquals(80_000_000_000_000L, instance.maxAlternatingSum(nums));
        }
    }
}
