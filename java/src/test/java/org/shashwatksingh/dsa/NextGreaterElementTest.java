package org.shashwatksingh.dsa;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Next Greater Element I Tests")
class NextGreaterElementTest {

    private NextGreaterElement instance;

    @BeforeEach
    void setUp() {
        instance = new NextGreaterElement();
    }

    /** {start, start + step, start + 2 * step, ...} with the given length. */
    private static int[] sequence(int length, int start, int step) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = start + i * step;
        }
        return arr;
    }

    /** Expected answers for a strictly increasing sequence queried in full: next value, then -1. */
    private static int[] increasingAnswers(int[] increasing) {
        int[] expected = new int[increasing.length];
        for (int i = 0; i < increasing.length - 1; i++) {
            expected[i] = increasing[i + 1];
        }
        expected[increasing.length - 1] = -1;
        return expected;
    }

    // ═══════════════════════════════════════════════════════════
    //  nextGreaterElementBruteForce()  — O(n * m) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("nextGreaterElementBruteForce() — nested scan from each query's position, O(n * m)")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: nums1=[4,1,2], nums2=[1,3,4,2] → [-1,3,-1]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { -1, 3, -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums1=[2,4], nums2=[1,2,3,4] → [3,-1]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 3, -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1], nums2=[1] → [-1] (minimum size, nothing to the right)")
        void testSingleElementBoth() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 1 }, new int[] { 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1], nums2=[1,2] → [2] (single query with a greater neighbour)")
        void testSingleQueryHasGreater() {
            assertEquals(Arrays.toString(new int[] { 2 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 1 }, new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,1] → [-1] (single query, only a smaller element to the right)")
        void testSingleQueryNoGreater() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 2 }, new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[0], nums2=[0,5] → [5] (zero at lower bound)")
        void testZeroHasGreater() {
            assertEquals(Arrays.toString(new int[] { 5 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 0 }, new int[] { 0, 5 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[0], nums2=[5,0] → [-1] (zero at the end)")
        void testZeroAtEnd() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 0 }, new int[] { 5, 0 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1,2,3,4], nums2=[1,2,3,4] → [2,3,4,-1] (strictly increasing, nums1 == nums2)")
        void testNumsEqualIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[4,3,2,1], nums2=[4,3,2,1] → [-1,-1,-1,-1] (strictly decreasing, no answers)")
        void testNumsEqualDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 4, 3, 2, 1 }, new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1], nums2=[1,2,3,4] → [4,2] (nums1 order differs from nums2 order)")
        void testQueriesInDifferentOrder() {
            assertEquals(Arrays.toString(new int[] { 4, 2 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 3, 1 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[4,3,2,1], nums2=[1,2,3,4] → [-1,4,3,2] (queries reversed relative to nums2)")
        void testQueriesInReverseOrder() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 3, 2 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 4, 3, 2, 1 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,1,0,5,3] → [5] (smaller elements skipped before the greater one)")
        void testGreaterNotAdjacent() {
            assertEquals(Arrays.toString(new int[] { 5 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 2 }, new int[] { 2, 1, 0, 5, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3], nums2=[5,3] → [-1] (greater element exists only to the left)")
        void testGreaterOnlyOnTheLeft() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 3 }, new int[] { 5, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,4,9] → [4] (first greater, not the maximum to the right)")
        void testFirstGreaterNotLargest() {
            assertEquals(Arrays.toString(new int[] { 4 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 2 }, new int[] { 2, 4, 9 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1], nums2=[3,1,5] → [5,5] (two queries share the same next greater element)")
        void testSharedNextGreater() {
            assertEquals(Arrays.toString(new int[] { 5, 5 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 3, 1 }, new int[] { 3, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2,7,3], nums2=[5,2,7,3,9,1] → [7,9,9] (queries across peaks and valleys)")
        void testPeaksAndValleys() {
            assertEquals(Arrays.toString(new int[] { 7, 9, 9 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 2, 7, 3 }, new int[] { 5, 2, 7, 3, 9, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[9], nums2=[9,1,2,3] → [-1] (largest element at the front)")
        void testMaxElementFirst() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 9 }, new int[] { 9, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1,2], nums2=[3,1,2,4] → [4,2,4] (pending elements resolved at different times)")
        void testLateResolution() {
            assertEquals(Arrays.toString(new int[] { 4, 2, 4 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 3, 1, 2 }, new int[] { 3, 1, 2, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[6,2,8,1,9,4], nums2=[6,2,8,1,9,4] → [8,8,9,9,-1,-1] (zig-zag sequence, nums1 == nums2)")
        void testZigZagAllQueried() {
            assertEquals(Arrays.toString(new int[] { 8, 8, 9, 9, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 6, 2, 8, 1, 9, 4 }, new int[] { 6, 2, 8, 1, 9, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[9999], nums2=[9999,10000] → [10000] (value at upper bound as answer)")
        void testUpperBoundValue() {
            assertEquals(Arrays.toString(new int[] { 10000 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 9999 }, new int[] { 9999, 10000 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[10000], nums2=[0,10000] → [-1] (upper bound value has no greater element)")
        void testUpperBoundQuery() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 10000 }, new int[] { 0, 10000 })));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [0..999] → [1,2,...,999,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] nums = sequence(1_000, 0, 1);
            assertEquals(Arrays.toString(increasingAnswers(nums)),
                    Arrays.toString(instance.nextGreaterElementBruteForce(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [0,10,...,9990] → [10,20,...,9990,-1] (max length, large values)")
        void testMaxLengthLargeValuesIncreasing() {
            int[] nums = sequence(1_000, 0, 10);
            assertEquals(Arrays.toString(increasingAnswers(nums)),
                    Arrays.toString(instance.nextGreaterElementBruteForce(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [999..0] → 1,000 × -1 (max length, strictly decreasing)")
        void testMaxLengthDecreasing() {
            int[] nums = sequence(1_000, 999, -1);
            int[] expected = new int[1_000];
            Arrays.fill(expected, -1);
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementBruteForce(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = [0], nums2 = [0..999] → [1] (max-length nums2, single query at the front)")
        void testMaxLengthSingleQueryFront() {
            assertEquals(Arrays.toString(new int[] { 1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 0 }, sequence(1_000, 0, 1))));
        }

        @Test
        @DisplayName("Scenario: nums1 = [999], nums2 = [0..999] → [-1] (max-length nums2, single query at the end)")
        void testMaxLengthSingleQueryEnd() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementBruteForce(new int[] { 999 }, sequence(1_000, 0, 1))));
        }

        @Test
        @DisplayName("Scenario: nums1 = even values of [0..999], nums2 = [0..999] → each value + 1 (max length, half queried)")
        void testMaxLengthHalfQueried() {
            int[] nums1 = sequence(500, 0, 2);
            int[] expected = new int[500];
            for (int i = 0; i < 500; i++) {
                expected[i] = nums1[i] + 1;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementBruteForce(nums1, sequence(1_000, 0, 1))));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  nextGreaterElement()  — O(n + m) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("nextGreaterElement() — monotonic stack and hash map, O(n + m)")
    class MonotonicStackTests {

        @Test
        @DisplayName("LeetCode Example 1: nums1=[4,1,2], nums2=[1,3,4,2] → [-1,3,-1]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { -1, 3, -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 4, 1, 2 }, new int[] { 1, 3, 4, 2 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: nums1=[2,4], nums2=[1,2,3,4] → [3,-1]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 3, -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 2, 4 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1], nums2=[1] → [-1] (minimum size, nothing to the right)")
        void testSingleElementBoth() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 1 }, new int[] { 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1], nums2=[1,2] → [2] (single query with a greater neighbour)")
        void testSingleQueryHasGreater() {
            assertEquals(Arrays.toString(new int[] { 2 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 1 }, new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,1] → [-1] (single query, only a smaller element to the right)")
        void testSingleQueryNoGreater() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 2 }, new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[0], nums2=[0,5] → [5] (zero at lower bound)")
        void testZeroHasGreater() {
            assertEquals(Arrays.toString(new int[] { 5 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 0 }, new int[] { 0, 5 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[0], nums2=[5,0] → [-1] (zero at the end)")
        void testZeroAtEnd() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 0 }, new int[] { 5, 0 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[1,2,3,4], nums2=[1,2,3,4] → [2,3,4,-1] (strictly increasing, nums1 == nums2)")
        void testNumsEqualIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 1, 2, 3, 4 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[4,3,2,1], nums2=[4,3,2,1] → [-1,-1,-1,-1] (strictly decreasing, no answers)")
        void testNumsEqualDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 4, 3, 2, 1 }, new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1], nums2=[1,2,3,4] → [4,2] (nums1 order differs from nums2 order)")
        void testQueriesInDifferentOrder() {
            assertEquals(Arrays.toString(new int[] { 4, 2 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 3, 1 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[4,3,2,1], nums2=[1,2,3,4] → [-1,4,3,2] (queries reversed relative to nums2)")
        void testQueriesInReverseOrder() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 3, 2 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 4, 3, 2, 1 }, new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,1,0,5,3] → [5] (smaller elements skipped before the greater one)")
        void testGreaterNotAdjacent() {
            assertEquals(Arrays.toString(new int[] { 5 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 2 }, new int[] { 2, 1, 0, 5, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3], nums2=[5,3] → [-1] (greater element exists only to the left)")
        void testGreaterOnlyOnTheLeft() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 3 }, new int[] { 5, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2], nums2=[2,4,9] → [4] (first greater, not the maximum to the right)")
        void testFirstGreaterNotLargest() {
            assertEquals(Arrays.toString(new int[] { 4 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 2 }, new int[] { 2, 4, 9 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1], nums2=[3,1,5] → [5,5] (two queries share the same next greater element)")
        void testSharedNextGreater() {
            assertEquals(Arrays.toString(new int[] { 5, 5 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 3, 1 }, new int[] { 3, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[2,7,3], nums2=[5,2,7,3,9,1] → [7,9,9] (queries across peaks and valleys)")
        void testPeaksAndValleys() {
            assertEquals(Arrays.toString(new int[] { 7, 9, 9 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 2, 7, 3 }, new int[] { 5, 2, 7, 3, 9, 1 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[9], nums2=[9,1,2,3] → [-1] (largest element at the front)")
        void testMaxElementFirst() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 9 }, new int[] { 9, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[3,1,2], nums2=[3,1,2,4] → [4,2,4] (pending elements resolved at different times)")
        void testLateResolution() {
            assertEquals(Arrays.toString(new int[] { 4, 2, 4 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 3, 1, 2 }, new int[] { 3, 1, 2, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[6,2,8,1,9,4], nums2=[6,2,8,1,9,4] → [8,8,9,9,-1,-1] (zig-zag sequence, nums1 == nums2)")
        void testZigZagAllQueried() {
            assertEquals(Arrays.toString(new int[] { 8, 8, 9, 9, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 6, 2, 8, 1, 9, 4 }, new int[] { 6, 2, 8, 1, 9, 4 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[9999], nums2=[9999,10000] → [10000] (value at upper bound as answer)")
        void testUpperBoundValue() {
            assertEquals(Arrays.toString(new int[] { 10000 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 9999 }, new int[] { 9999, 10000 })));
        }

        @Test
        @DisplayName("Scenario: nums1=[10000], nums2=[0,10000] → [-1] (upper bound value has no greater element)")
        void testUpperBoundQuery() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 10000 }, new int[] { 0, 10000 })));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [0..999] → [1,2,...,999,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] nums = sequence(1_000, 0, 1);
            assertEquals(Arrays.toString(increasingAnswers(nums)),
                    Arrays.toString(instance.nextGreaterElement(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [0,10,...,9990] → [10,20,...,9990,-1] (max length, large values)")
        void testMaxLengthLargeValuesIncreasing() {
            int[] nums = sequence(1_000, 0, 10);
            assertEquals(Arrays.toString(increasingAnswers(nums)),
                    Arrays.toString(instance.nextGreaterElement(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = nums2 = [999..0] → 1,000 × -1 (max length, strictly decreasing)")
        void testMaxLengthDecreasing() {
            int[] nums = sequence(1_000, 999, -1);
            int[] expected = new int[1_000];
            Arrays.fill(expected, -1);
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElement(nums, nums)));
        }

        @Test
        @DisplayName("Scenario: nums1 = [0], nums2 = [0..999] → [1] (max-length nums2, single query at the front)")
        void testMaxLengthSingleQueryFront() {
            assertEquals(Arrays.toString(new int[] { 1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 0 }, sequence(1_000, 0, 1))));
        }

        @Test
        @DisplayName("Scenario: nums1 = [999], nums2 = [0..999] → [-1] (max-length nums2, single query at the end)")
        void testMaxLengthSingleQueryEnd() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElement(new int[] { 999 }, sequence(1_000, 0, 1))));
        }

        @Test
        @DisplayName("Scenario: nums1 = even values of [0..999], nums2 = [0..999] → each value + 1 (max length, half queried)")
        void testMaxLengthHalfQueried() {
            int[] nums1 = sequence(500, 0, 2);
            int[] expected = new int[500];
            for (int i = 0; i < 500; i++) {
                expected[i] = nums1[i] + 1;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElement(nums1, sequence(1_000, 0, 1))));
        }
    }
}
