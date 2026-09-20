package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("First Missing Positive Tests")
class FirstMissingPositiveTest {

    private FirstMissingPositive firstMissingPositive;

    @BeforeEach
    void setUp() {
        firstMissingPositive = new FirstMissingPositive();
    }

    // ─────────────────────────────────────────────────────────────────────────
    // firstMissingPositiveIndex  (O(n) time, O(1) space — in-place index marking)
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("firstMissingPositiveIndex")
    class FirstMissingPositiveIndexTests {

        // ─── LeetCode examples ───────────────────────────────────────────────

        @Test
        @DisplayName("Example 1: [1,2,0] → 3 (numbers 1 and 2 are present)")
        void testExample1() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveIndex(new int[]{1, 2, 0}));
        }

        @Test
        @DisplayName("Example 2: [3,4,-1,1] → 2 (1 is present but 2 is missing)")
        void testExample2() {
            assertEquals(2, firstMissingPositive.firstMissingPositiveIndex(new int[]{3, 4, -1, 1}));
        }

        @Test
        @DisplayName("Example 3: [7,8,9,11,12] → 1 (smallest positive 1 is absent)")
        void testExample3() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveIndex(new int[]{7, 8, 9, 11, 12}));
        }

        // ─── Edge: 1 is missing ──────────────────────────────────────────────

        @Test
        @DisplayName("All negatives: [-1,-2,-3] → 1")
        void testAllNegatives() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveIndex(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 1")
        void testAllZeros() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveIndex(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Single large number: [1000000] → 1 (out-of-range, 1 is missing)")
        void testSingleLargeNumber() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveIndex(new int[]{1000000}));
        }

        // ─── Edge: single element ────────────────────────────────────────────

        @Test
        @DisplayName("Single element = 1: [1] → 2")
        void testSingleElementOne() {
            assertEquals(2, firstMissingPositive.firstMissingPositiveIndex(new int[]{1}));
        }

        @Test
        @DisplayName("Single element != 1: [2] → 1")
        void testSingleElementNotOne() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveIndex(new int[]{2}));
        }

        // ─── Answer is n+1 (all of 1..n are present) ─────────────────────────

        @Test
        @DisplayName("Consecutive 1..n: [1,2,3] → 4 (all present, answer is n+1)")
        void testConsecutiveFullRange() {
            assertEquals(4, firstMissingPositive.firstMissingPositiveIndex(new int[]{1, 2, 3}));
        }

        // ─── Duplicates ──────────────────────────────────────────────────────

        @Test
        @DisplayName("Duplicates: [1,1,2,2] → 3 (duplicates must not mask missing values)")
        void testDuplicates() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveIndex(new int[]{1, 1, 2, 2}));
        }

        // ─── Gap in the middle ───────────────────────────────────────────────

        @Test
        @DisplayName("Gap in the middle: [1,2,4,5] → 3")
        void testGapInMiddle() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveIndex(new int[]{1, 2, 4, 5}));
        }

        // ─── Mixed positive, negative and zero ───────────────────────────────

        @Test
        @DisplayName("Mixed signs: [1,-1,2,-2,3] → 4 (negatives are ignored)")
        void testMixedSigns() {
            assertEquals(4, firstMissingPositive.firstMissingPositiveIndex(new int[]{1, -1, 2, -2, 3}));
        }
    }

    // ─────────────────────────────────────────────────────────────────────────
    // firstMissingPositiveBooleanArray  (O(n) time, O(n) space — boolean array)
    // NOTE: This method has a known bug — it returns -1 instead of n+1 when all
    //       integers 1..n are present in the array. The test for that scenario
    //       asserts the CORRECT value and will intentionally expose the defect.
    // ─────────────────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("firstMissingPositiveBooleanArray")
    class FirstMissingPositiveBooleanArrayTests {

        // ─── LeetCode examples ───────────────────────────────────────────────

        @Test
        @DisplayName("Example 1: [1,2,0] → 3 (numbers 1 and 2 are present)")
        void testExample1() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1, 2, 0}));
        }

        @Test
        @DisplayName("Example 2: [3,4,-1,1] → 2 (1 is present but 2 is missing)")
        void testExample2() {
            assertEquals(2, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{3, 4, -1, 1}));
        }

        @Test
        @DisplayName("Example 3: [7,8,9,11,12] → 1 (smallest positive 1 is absent)")
        void testExample3() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{7, 8, 9, 11, 12}));
        }

        // ─── Edge: 1 is missing ──────────────────────────────────────────────

        @Test
        @DisplayName("All negatives: [-1,-2,-3] → 1")
        void testAllNegatives() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → 1")
        void testAllZeros() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Single large number: [1000000] → 1 (out-of-range, 1 is missing)")
        void testSingleLargeNumber() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1000000}));
        }

        // ─── Edge: single element ────────────────────────────────────────────

        @Test
        @DisplayName("Single element = 1: [1] → 2")
        void testSingleElementOne() {
            assertEquals(2, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1}));
        }

        @Test
        @DisplayName("Single element != 1: [2] → 1")
        void testSingleElementNotOne() {
            assertEquals(1, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{2}));
        }

        // ─── Answer is n+1 — exposes the bug ─────────────────────────────────

        @Test
        @DisplayName("BUG: Consecutive 1..n: [1,2,3] → 4 expected, but method returns -1")
        void testConsecutiveFullRange_exposedBug() {
            // The boolean-array method returns -1 here instead of the correct n+1 = 4.
            // This test will FAIL until the bug is fixed:
            //   change `return -1;` → `return n + 1;`
            assertEquals(4, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1, 2, 3}));
        }

        // ─── Duplicates ──────────────────────────────────────────────────────

        @Test
        @DisplayName("Duplicates: [1,1,2,2] → 3 (duplicates must not mask missing values)")
        void testDuplicates() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1, 1, 2, 2}));
        }

        // ─── Gap in the middle ───────────────────────────────────────────────

        @Test
        @DisplayName("Gap in the middle: [1,2,4,5] → 3")
        void testGapInMiddle() {
            assertEquals(3, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1, 2, 4, 5}));
        }

        // ─── Mixed positive, negative and zero ───────────────────────────────

        @Test
        @DisplayName("Mixed signs: [1,-1,2,-2,3] → 4 (negatives are ignored)")
        void testMixedSigns() {
            assertEquals(4, firstMissingPositive.firstMissingPositiveBooleanArray(new int[]{1, -1, 2, -2, 3}));
        }
    }
}
