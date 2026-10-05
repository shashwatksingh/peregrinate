package org.shashwatksingh.dsa;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Next Greater Element II Tests")
class NextGreaterElement2Test {

    private NextGreaterElement2 instance;

    @BeforeEach
    void setUp() {
        instance = new NextGreaterElement2();
    }

    private static int[] filled(int length, int value) {
        int[] arr = new int[length];
        Arrays.fill(arr, value);
        return arr;
    }

    /** {start, start + step, start + 2 * step, ...} with the given length. */
    private static int[] sequence(int length, int start, int step) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = start + i * step;
        }
        return arr;
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — brute force over a doubled array, O(n^2)")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,1] → [2,-1,2] (second 1 wraps around to 2)")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 2, -1, 2 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1, 2, 1 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,4,3] → [2,3,4,-1,4] (last 3 wraps around to 4)")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1, 4 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1, 2, 3, 4, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5] → [-1] (minimum size, nothing else in the circle)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 5 })));
        }

        @Test
        @DisplayName("Scenario: [1,2] → [2,-1] (minimum increasing pair)")
        void testTwoElementsIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: [2,1] → [-1,2] (smaller element wraps to the larger)")
        void testTwoElementsDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 2 }),
                    Arrays.toString(instance.bruteForce(new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [3,3,3] → [-1,-1,-1] (equal is not greater, even after wrapping)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 3, 3, 3 })));
        }

        @Test
        @DisplayName("Scenario: [0,0] → [-1,-1] (zeros)")
        void testAllZeros() {
            assertEquals(Arrays.toString(new int[] { -1, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 0, 0 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4] → [2,3,4,-1] (only the maximum has no answer)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: [4,3,2,1] → [-1,4,4,4] (every smaller element wraps to the maximum)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 4, 4 }),
                    Arrays.toString(instance.bruteForce(new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,2,1] → [2,3,-1,3,2] (peak in the middle, descending half wraps)")
        void testMirroredMountain() {
            assertEquals(Arrays.toString(new int[] { 2, 3, -1, 3, 2 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1, 2, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3] → [-1,2,3,5] (last element wraps to the front maximum)")
        void testMaxAtStart() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 5 }),
                    Arrays.toString(instance.bruteForce(new int[] { 5, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [2,5,1,3] → [5,-1,3,5] (last element wraps past the first to the maximum)")
        void testMaxInMiddle() {
            assertEquals(Arrays.toString(new int[] { 5, -1, 3, 5 }),
                    Arrays.toString(instance.bruteForce(new int[] { 2, 5, 1, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5,5,1] → [-1,-1,5] (duplicate maximums have no answer)")
        void testDuplicateMax() {
            assertEquals(Arrays.toString(new int[] { -1, -1, 5 }),
                    Arrays.toString(instance.bruteForce(new int[] { 5, 5, 1 })));
        }

        @Test
        @DisplayName("Scenario: [2,2,3] → [3,3,-1] (equal neighbours both resolve to the next larger value)")
        void testDuplicateNonMaxAscending() {
            assertEquals(Arrays.toString(new int[] { 3, 3, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 2, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [3,2,2] → [-1,3,3] (equal neighbours both wrap to the front maximum)")
        void testDuplicateNonMaxWrapping() {
            assertEquals(Arrays.toString(new int[] { -1, 3, 3 }),
                    Arrays.toString(instance.bruteForce(new int[] { 3, 2, 2 })));
        }

        @Test
        @DisplayName("Scenario: [9,1,2,3,4,5,6,7,8] → [-1,2,3,4,5,6,7,8,9] (last element wraps across the whole array)")
        void testLongWrap() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 4, 5, 6, 7, 8, 9 }),
                    Arrays.toString(instance.bruteForce(new int[] { 9, 1, 2, 3, 4, 5, 6, 7, 8 })));
        }

        @Test
        @DisplayName("Scenario: [3,1,2,3,1] → [-1,2,3,-1,3] (repeated maximum, trailing element wraps)")
        void testValleyWithRepeats() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, -1, 3 }),
                    Arrays.toString(instance.bruteForce(new int[] { 3, 1, 2, 3, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,5,1,5] → [-1,5,-1,5,-1] (alternating high and low values)")
        void testZigZag() {
            assertEquals(Arrays.toString(new int[] { -1, 5, -1, 5, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { 5, 1, 5, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: [-5,-3,-4] → [-3,-1,-3] (negative values, -1 sentinel for the maximum)")
        void testAllNegative() {
            assertEquals(Arrays.toString(new int[] { -3, -1, -3 }),
                    Arrays.toString(instance.bruteForce(new int[] { -5, -3, -4 })));
        }

        @Test
        @DisplayName("Scenario: [-1,0,1] → [0,1,-1] (negative, zero and positive)")
        void testMixedSigns() {
            assertEquals(Arrays.toString(new int[] { 0, 1, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { -1, 0, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1_000_000_000,-1_000_000_000] → [-1,1_000_000_000] (constraint bounds, maximum first)")
        void testValueBoundsMaxFirst() {
            assertEquals(Arrays.toString(new int[] { -1, 1_000_000_000 }),
                    Arrays.toString(instance.bruteForce(new int[] { 1_000_000_000, -1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: [-1_000_000_000,1_000_000_000] → [1_000_000_000,-1] (constraint bounds, minimum first)")
        void testValueBoundsMinFirst() {
            assertEquals(Arrays.toString(new int[] { 1_000_000_000, -1 }),
                    Arrays.toString(instance.bruteForce(new int[] { -1_000_000_000, 1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: 10,000 × 7 → 10,000 × -1 (max length, all same)")
        void testMaxLengthAllSame() {
            assertEquals(Arrays.toString(filled(10_000, -1)),
                    Arrays.toString(instance.bruteForce(filled(10_000, 7))));
        }

        @Test
        @DisplayName("Scenario: [1..10,000] → [2,3,...,10,000,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] expected = sequence(10_000, 2, 1);
            expected[9_999] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.bruteForce(sequence(10_000, 1, 1))));
        }

        @Test
        @DisplayName("Scenario: [10,000..1] → [-1, 10,000 × 9,999] (max length, strictly decreasing, every element wraps)")
        void testMaxLengthDecreasing() {
            int[] expected = filled(10_000, 10_000);
            expected[0] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.bruteForce(sequence(10_000, 10_000, -1))));
        }

        @Test
        @DisplayName("Scenario: 5,000 × (10^9, -10^9) → even indices -1, odd indices 10^9 (max length, constraint bounds)")
        void testMaxLengthAlternatingBounds() {
            int[] nums = new int[10_000];
            int[] expected = new int[10_000];
            for (int i = 0; i < 10_000; i++) {
                nums[i] = i % 2 == 0 ? 1_000_000_000 : -1_000_000_000;
                expected[i] = i % 2 == 0 ? -1 : 1_000_000_000;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.bruteForce(nums)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  optimizedBruteForce()  — O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("optimizedBruteForce() — brute force with modulo indexing, O(n^2)")
    class OptimizedBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,1] → [2,-1,2] (second 1 wraps around to 2)")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 2, -1, 2 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1, 2, 1 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,4,3] → [2,3,4,-1,4] (last 3 wraps around to 4)")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1, 4 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1, 2, 3, 4, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5] → [-1] (minimum size, nothing else in the circle)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 5 })));
        }

        @Test
        @DisplayName("Scenario: [1,2] → [2,-1] (minimum increasing pair)")
        void testTwoElementsIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: [2,1] → [-1,2] (smaller element wraps to the larger)")
        void testTwoElementsDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 2 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [3,3,3] → [-1,-1,-1] (equal is not greater, even after wrapping)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 3, 3, 3 })));
        }

        @Test
        @DisplayName("Scenario: [0,0] → [-1,-1] (zeros)")
        void testAllZeros() {
            assertEquals(Arrays.toString(new int[] { -1, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 0, 0 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4] → [2,3,4,-1] (only the maximum has no answer)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: [4,3,2,1] → [-1,4,4,4] (every smaller element wraps to the maximum)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 4, 4 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,2,1] → [2,3,-1,3,2] (peak in the middle, descending half wraps)")
        void testMirroredMountain() {
            assertEquals(Arrays.toString(new int[] { 2, 3, -1, 3, 2 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1, 2, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3] → [-1,2,3,5] (last element wraps to the front maximum)")
        void testMaxAtStart() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 5 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 5, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [2,5,1,3] → [5,-1,3,5] (last element wraps past the first to the maximum)")
        void testMaxInMiddle() {
            assertEquals(Arrays.toString(new int[] { 5, -1, 3, 5 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 2, 5, 1, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5,5,1] → [-1,-1,5] (duplicate maximums have no answer)")
        void testDuplicateMax() {
            assertEquals(Arrays.toString(new int[] { -1, -1, 5 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 5, 5, 1 })));
        }

        @Test
        @DisplayName("Scenario: [2,2,3] → [3,3,-1] (equal neighbours both resolve to the next larger value)")
        void testDuplicateNonMaxAscending() {
            assertEquals(Arrays.toString(new int[] { 3, 3, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 2, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [3,2,2] → [-1,3,3] (equal neighbours both wrap to the front maximum)")
        void testDuplicateNonMaxWrapping() {
            assertEquals(Arrays.toString(new int[] { -1, 3, 3 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 3, 2, 2 })));
        }

        @Test
        @DisplayName("Scenario: [9,1,2,3,4,5,6,7,8] → [-1,2,3,4,5,6,7,8,9] (last element wraps across the whole array)")
        void testLongWrap() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 4, 5, 6, 7, 8, 9 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 9, 1, 2, 3, 4, 5, 6, 7, 8 })));
        }

        @Test
        @DisplayName("Scenario: [3,1,2,3,1] → [-1,2,3,-1,3] (repeated maximum, trailing element wraps)")
        void testValleyWithRepeats() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, -1, 3 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 3, 1, 2, 3, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,5,1,5] → [-1,5,-1,5,-1] (alternating high and low values)")
        void testZigZag() {
            assertEquals(Arrays.toString(new int[] { -1, 5, -1, 5, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 5, 1, 5, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: [-5,-3,-4] → [-3,-1,-3] (negative values, -1 sentinel for the maximum)")
        void testAllNegative() {
            assertEquals(Arrays.toString(new int[] { -3, -1, -3 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { -5, -3, -4 })));
        }

        @Test
        @DisplayName("Scenario: [-1,0,1] → [0,1,-1] (negative, zero and positive)")
        void testMixedSigns() {
            assertEquals(Arrays.toString(new int[] { 0, 1, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { -1, 0, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1_000_000_000,-1_000_000_000] → [-1,1_000_000_000] (constraint bounds, maximum first)")
        void testValueBoundsMaxFirst() {
            assertEquals(Arrays.toString(new int[] { -1, 1_000_000_000 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { 1_000_000_000, -1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: [-1_000_000_000,1_000_000_000] → [1_000_000_000,-1] (constraint bounds, minimum first)")
        void testValueBoundsMinFirst() {
            assertEquals(Arrays.toString(new int[] { 1_000_000_000, -1 }),
                    Arrays.toString(instance.optimizedBruteForce(new int[] { -1_000_000_000, 1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: 10,000 × 7 → 10,000 × -1 (max length, all same)")
        void testMaxLengthAllSame() {
            assertEquals(Arrays.toString(filled(10_000, -1)),
                    Arrays.toString(instance.optimizedBruteForce(filled(10_000, 7))));
        }

        @Test
        @DisplayName("Scenario: [1..10,000] → [2,3,...,10,000,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] expected = sequence(10_000, 2, 1);
            expected[9_999] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.optimizedBruteForce(sequence(10_000, 1, 1))));
        }

        @Test
        @DisplayName("Scenario: [10,000..1] → [-1, 10,000 × 9,999] (max length, strictly decreasing, every element wraps)")
        void testMaxLengthDecreasing() {
            int[] expected = filled(10_000, 10_000);
            expected[0] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.optimizedBruteForce(sequence(10_000, 10_000, -1))));
        }

        @Test
        @DisplayName("Scenario: 5,000 × (10^9, -10^9) → even indices -1, odd indices 10^9 (max length, constraint bounds)")
        void testMaxLengthAlternatingBounds() {
            int[] nums = new int[10_000];
            int[] expected = new int[10_000];
            for (int i = 0; i < 10_000; i++) {
                nums[i] = i % 2 == 0 ? 1_000_000_000 : -1_000_000_000;
                expected[i] = i % 2 == 0 ? -1 : 1_000_000_000;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.optimizedBruteForce(nums)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  nextGreaterElementsStack()  — O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("nextGreaterElementsStack() — monotonic stack with a second pass, O(n)")
    class StackTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,1] → [2,-1,2] (second 1 wraps around to 2)")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 2, -1, 2 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1, 2, 1 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,4,3] → [2,3,4,-1,4] (last 3 wraps around to 4)")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1, 4 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1, 2, 3, 4, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5] → [-1] (minimum size, nothing else in the circle)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 5 })));
        }

        @Test
        @DisplayName("Scenario: [1,2] → [2,-1] (minimum increasing pair)")
        void testTwoElementsIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: [2,1] → [-1,2] (smaller element wraps to the larger)")
        void testTwoElementsDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 2 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [3,3,3] → [-1,-1,-1] (equal is not greater, even after wrapping)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 3, 3, 3 })));
        }

        @Test
        @DisplayName("Scenario: [0,0] → [-1,-1] (zeros)")
        void testAllZeros() {
            assertEquals(Arrays.toString(new int[] { -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 0, 0 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4] → [2,3,4,-1] (only the maximum has no answer)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: [4,3,2,1] → [-1,4,4,4] (every smaller element wraps to the maximum)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 4, 4 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,2,1] → [2,3,-1,3,2] (peak in the middle, descending half wraps)")
        void testMirroredMountain() {
            assertEquals(Arrays.toString(new int[] { 2, 3, -1, 3, 2 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1, 2, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3] → [-1,2,3,5] (last element wraps to the front maximum)")
        void testMaxAtStart() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 5 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 5, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [2,5,1,3] → [5,-1,3,5] (last element wraps past the first to the maximum)")
        void testMaxInMiddle() {
            assertEquals(Arrays.toString(new int[] { 5, -1, 3, 5 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 2, 5, 1, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5,5,1] → [-1,-1,5] (duplicate maximums have no answer)")
        void testDuplicateMax() {
            assertEquals(Arrays.toString(new int[] { -1, -1, 5 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 5, 5, 1 })));
        }

        @Test
        @DisplayName("Scenario: [2,2,3] → [3,3,-1] (equal neighbours both resolve to the next larger value)")
        void testDuplicateNonMaxAscending() {
            assertEquals(Arrays.toString(new int[] { 3, 3, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 2, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [3,2,2] → [-1,3,3] (equal neighbours both wrap to the front maximum)")
        void testDuplicateNonMaxWrapping() {
            assertEquals(Arrays.toString(new int[] { -1, 3, 3 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 3, 2, 2 })));
        }

        @Test
        @DisplayName("Scenario: [9,1,2,3,4,5,6,7,8] → [-1,2,3,4,5,6,7,8,9] (last element wraps across the whole array)")
        void testLongWrap() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 4, 5, 6, 7, 8, 9 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 9, 1, 2, 3, 4, 5, 6, 7, 8 })));
        }

        @Test
        @DisplayName("Scenario: [3,1,2,3,1] → [-1,2,3,-1,3] (repeated maximum, trailing element wraps)")
        void testValleyWithRepeats() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, -1, 3 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 3, 1, 2, 3, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,5,1,5] → [-1,5,-1,5,-1] (alternating high and low values)")
        void testZigZag() {
            assertEquals(Arrays.toString(new int[] { -1, 5, -1, 5, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 5, 1, 5, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: [-5,-3,-4] → [-3,-1,-3] (negative values, -1 sentinel for the maximum)")
        void testAllNegative() {
            assertEquals(Arrays.toString(new int[] { -3, -1, -3 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { -5, -3, -4 })));
        }

        @Test
        @DisplayName("Scenario: [-1,0,1] → [0,1,-1] (negative, zero and positive)")
        void testMixedSigns() {
            assertEquals(Arrays.toString(new int[] { 0, 1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { -1, 0, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1_000_000_000,-1_000_000_000] → [-1,1_000_000_000] (constraint bounds, maximum first)")
        void testValueBoundsMaxFirst() {
            assertEquals(Arrays.toString(new int[] { -1, 1_000_000_000 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { 1_000_000_000, -1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: [-1_000_000_000,1_000_000_000] → [1_000_000_000,-1] (constraint bounds, minimum first)")
        void testValueBoundsMinFirst() {
            assertEquals(Arrays.toString(new int[] { 1_000_000_000, -1 }),
                    Arrays.toString(instance.nextGreaterElementsStack(new int[] { -1_000_000_000, 1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: 10,000 × 7 → 10,000 × -1 (max length, all same)")
        void testMaxLengthAllSame() {
            assertEquals(Arrays.toString(filled(10_000, -1)),
                    Arrays.toString(instance.nextGreaterElementsStack(filled(10_000, 7))));
        }

        @Test
        @DisplayName("Scenario: [1..10,000] → [2,3,...,10,000,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] expected = sequence(10_000, 2, 1);
            expected[9_999] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsStack(sequence(10_000, 1, 1))));
        }

        @Test
        @DisplayName("Scenario: [10,000..1] → [-1, 10,000 × 9,999] (max length, strictly decreasing, every element wraps)")
        void testMaxLengthDecreasing() {
            int[] expected = filled(10_000, 10_000);
            expected[0] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsStack(sequence(10_000, 10_000, -1))));
        }

        @Test
        @DisplayName("Scenario: 5,000 × (10^9, -10^9) → even indices -1, odd indices 10^9 (max length, constraint bounds)")
        void testMaxLengthAlternatingBounds() {
            int[] nums = new int[10_000];
            int[] expected = new int[10_000];
            for (int i = 0; i < 10_000; i++) {
                nums[i] = i % 2 == 0 ? 1_000_000_000 : -1_000_000_000;
                expected[i] = i % 2 == 0 ? -1 : 1_000_000_000;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsStack(nums)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  nextGreaterElementsOptimisedStack()  — O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("nextGreaterElementsOptimisedStack() — monotonic stack scanning 2n indices backwards, O(n)")
    class OptimisedStackTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,1] → [2,-1,2] (second 1 wraps around to 2)")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 2, -1, 2 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1, 2, 1 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,3,4,3] → [2,3,4,-1,4] (last 3 wraps around to 4)")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1, 4 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1, 2, 3, 4, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5] → [-1] (minimum size, nothing else in the circle)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 5 })));
        }

        @Test
        @DisplayName("Scenario: [1,2] → [2,-1] (minimum increasing pair)")
        void testTwoElementsIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1, 2 })));
        }

        @Test
        @DisplayName("Scenario: [2,1] → [-1,2] (smaller element wraps to the larger)")
        void testTwoElementsDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 2 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [3,3,3] → [-1,-1,-1] (equal is not greater, even after wrapping)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { -1, -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 3, 3, 3 })));
        }

        @Test
        @DisplayName("Scenario: [0,0] → [-1,-1] (zeros)")
        void testAllZeros() {
            assertEquals(Arrays.toString(new int[] { -1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 0, 0 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4] → [2,3,4,-1] (only the maximum has no answer)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 2, 3, 4, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1, 2, 3, 4 })));
        }

        @Test
        @DisplayName("Scenario: [4,3,2,1] → [-1,4,4,4] (every smaller element wraps to the maximum)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { -1, 4, 4, 4 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 4, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,2,1] → [2,3,-1,3,2] (peak in the middle, descending half wraps)")
        void testMirroredMountain() {
            assertEquals(Arrays.toString(new int[] { 2, 3, -1, 3, 2 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1, 2, 3, 2, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,2,3] → [-1,2,3,5] (last element wraps to the front maximum)")
        void testMaxAtStart() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 5 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 5, 1, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [2,5,1,3] → [5,-1,3,5] (last element wraps past the first to the maximum)")
        void testMaxInMiddle() {
            assertEquals(Arrays.toString(new int[] { 5, -1, 3, 5 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 2, 5, 1, 3 })));
        }

        @Test
        @DisplayName("Scenario: [5,5,1] → [-1,-1,5] (duplicate maximums have no answer)")
        void testDuplicateMax() {
            assertEquals(Arrays.toString(new int[] { -1, -1, 5 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 5, 5, 1 })));
        }

        @Test
        @DisplayName("Scenario: [2,2,3] → [3,3,-1] (equal neighbours both resolve to the next larger value)")
        void testDuplicateNonMaxAscending() {
            assertEquals(Arrays.toString(new int[] { 3, 3, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 2, 2, 3 })));
        }

        @Test
        @DisplayName("Scenario: [3,2,2] → [-1,3,3] (equal neighbours both wrap to the front maximum)")
        void testDuplicateNonMaxWrapping() {
            assertEquals(Arrays.toString(new int[] { -1, 3, 3 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 3, 2, 2 })));
        }

        @Test
        @DisplayName("Scenario: [9,1,2,3,4,5,6,7,8] → [-1,2,3,4,5,6,7,8,9] (last element wraps across the whole array)")
        void testLongWrap() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, 4, 5, 6, 7, 8, 9 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 9, 1, 2, 3, 4, 5, 6, 7, 8 })));
        }

        @Test
        @DisplayName("Scenario: [3,1,2,3,1] → [-1,2,3,-1,3] (repeated maximum, trailing element wraps)")
        void testValleyWithRepeats() {
            assertEquals(Arrays.toString(new int[] { -1, 2, 3, -1, 3 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 3, 1, 2, 3, 1 })));
        }

        @Test
        @DisplayName("Scenario: [5,1,5,1,5] → [-1,5,-1,5,-1] (alternating high and low values)")
        void testZigZag() {
            assertEquals(Arrays.toString(new int[] { -1, 5, -1, 5, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 5, 1, 5, 1, 5 })));
        }

        @Test
        @DisplayName("Scenario: [-5,-3,-4] → [-3,-1,-3] (negative values, -1 sentinel for the maximum)")
        void testAllNegative() {
            assertEquals(Arrays.toString(new int[] { -3, -1, -3 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { -5, -3, -4 })));
        }

        @Test
        @DisplayName("Scenario: [-1,0,1] → [0,1,-1] (negative, zero and positive)")
        void testMixedSigns() {
            assertEquals(Arrays.toString(new int[] { 0, 1, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { -1, 0, 1 })));
        }

        @Test
        @DisplayName("Scenario: [1_000_000_000,-1_000_000_000] → [-1,1_000_000_000] (constraint bounds, maximum first)")
        void testValueBoundsMaxFirst() {
            assertEquals(Arrays.toString(new int[] { -1, 1_000_000_000 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { 1_000_000_000, -1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: [-1_000_000_000,1_000_000_000] → [1_000_000_000,-1] (constraint bounds, minimum first)")
        void testValueBoundsMinFirst() {
            assertEquals(Arrays.toString(new int[] { 1_000_000_000, -1 }),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(new int[] { -1_000_000_000, 1_000_000_000 })));
        }

        @Test
        @DisplayName("Scenario: 10,000 × 7 → 10,000 × -1 (max length, all same)")
        void testMaxLengthAllSame() {
            assertEquals(Arrays.toString(filled(10_000, -1)),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(filled(10_000, 7))));
        }

        @Test
        @DisplayName("Scenario: [1..10,000] → [2,3,...,10,000,-1] (max length, strictly increasing)")
        void testMaxLengthIncreasing() {
            int[] expected = sequence(10_000, 2, 1);
            expected[9_999] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(sequence(10_000, 1, 1))));
        }

        @Test
        @DisplayName("Scenario: [10,000..1] → [-1, 10,000 × 9,999] (max length, strictly decreasing, every element wraps)")
        void testMaxLengthDecreasing() {
            int[] expected = filled(10_000, 10_000);
            expected[0] = -1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(sequence(10_000, 10_000, -1))));
        }

        @Test
        @DisplayName("Scenario: 5,000 × (10^9, -10^9) → even indices -1, odd indices 10^9 (max length, constraint bounds)")
        void testMaxLengthAlternatingBounds() {
            int[] nums = new int[10_000];
            int[] expected = new int[10_000];
            for (int i = 0; i < 10_000; i++) {
                nums[i] = i % 2 == 0 ? 1_000_000_000 : -1_000_000_000;
                expected[i] = i % 2 == 0 ? -1 : 1_000_000_000;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.nextGreaterElementsOptimisedStack(nums)));
        }
    }
}
