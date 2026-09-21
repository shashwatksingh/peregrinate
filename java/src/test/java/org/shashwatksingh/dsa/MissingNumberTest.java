package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MissingNumber Tests")
class MissingNumberTest {

    private MissingNumber missingNumber;

    @BeforeEach
    void setUp() {
        missingNumber = new MissingNumber();
    }

    @Nested
    @DisplayName("bruteForce (O(n²) nested loop)")
    class BruteForceTests {

        @Test
        @DisplayName("Example 1: [3,0,1] → 2 (missing in the middle)")
        void testMissingInMiddle() {
            assertEquals(2, missingNumber.bruteForce(new int[]{3, 0, 1}));
        }

        @Test
        @DisplayName("Example 2: [0,1] → 2 (missing at end, n=2)")
        void testMissingAtEnd() {
            assertEquals(2, missingNumber.bruteForce(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Example 3: [9,6,4,2,3,5,7,0,1] → 8 (large array)")
        void testLargeArray() {
            assertEquals(8, missingNumber.bruteForce(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        }

        @Test
        @DisplayName("Missing 0: [1,2,3] → 0 (missing at start)")
        void testMissingZero() {
            assertEquals(0, missingNumber.bruteForce(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Missing n: [0,1,2] → 3 (missing last element)")
        void testMissingN() {
            assertEquals(3, missingNumber.bruteForce(new int[]{0, 1, 2}));
        }

        @Test
        @DisplayName("Single element [1] → 0 (only element is 1, so 0 is missing)")
        void testSingleElementOneMissing() {
            assertEquals(0, missingNumber.bruteForce(new int[]{1}));
        }

        @Test
        @DisplayName("Single element [0] → 1 (only element is 0, so 1 is missing)")
        void testSingleElementZeroPresent() {
            assertEquals(1, missingNumber.bruteForce(new int[]{0}));
        }

        @Test
        @DisplayName("Already sorted input: [0,1,3,4] → 2 (gap in the middle)")
        void testAlreadySortedWithGap() {
            assertEquals(2, missingNumber.bruteForce(new int[]{0, 1, 3, 4}));
        }
    }

    @Nested
    @DisplayName("sortSolution (Sort + linear scan)")
    class SortSolutionTests {

        @Test
        @DisplayName("Example 1: [3,0,1] → 2 (missing in the middle)")
        void testMissingInMiddle() {
            assertEquals(2, missingNumber.sortSolution(new int[]{3, 0, 1}));
        }

        @Test
        @DisplayName("Example 2: [0,1] → 2 (missing at end, n=2)")
        void testMissingAtEnd() {
            assertEquals(2, missingNumber.sortSolution(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Example 3: [9,6,4,2,3,5,7,0,1] → 8 (large array)")
        void testLargeArray() {
            assertEquals(8, missingNumber.sortSolution(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        }

        @Test
        @DisplayName("Missing 0: [1,2,3] → 0 (missing at start)")
        void testMissingZero() {
            assertEquals(0, missingNumber.sortSolution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Missing n: [0,1,2] → 3 (missing last element)")
        void testMissingN() {
            assertEquals(3, missingNumber.sortSolution(new int[]{0, 1, 2}));
        }

        @Test
        @DisplayName("Single element [1] → 0 (only element is 1, so 0 is missing)")
        void testSingleElementOneMissing() {
            assertEquals(0, missingNumber.sortSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Single element [0] → 1 (only element is 0, so 1 is missing)")
        void testSingleElementZeroPresent() {
            assertEquals(1, missingNumber.sortSolution(new int[]{0}));
        }

        @Test
        @DisplayName("Already sorted input: [0,1,3,4] → 2 (gap in the middle)")
        void testAlreadySortedWithGap() {
            assertEquals(2, missingNumber.sortSolution(new int[]{0, 1, 3, 4}));
        }
    }

    @Nested
    @DisplayName("xorSolution (XOR bit-manipulation)")
    class XorSolutionTests {

        @Test
        @DisplayName("Example 1: [3,0,1] → 2 (missing in the middle)")
        void testMissingInMiddle() {
            assertEquals(2, missingNumber.xorSolution(new int[]{3, 0, 1}));
        }

        @Test
        @DisplayName("Example 2: [0,1] → 2 (missing at end, n=2)")
        void testMissingAtEnd() {
            assertEquals(2, missingNumber.xorSolution(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Example 3: [9,6,4,2,3,5,7,0,1] → 8 (large array)")
        void testLargeArray() {
            assertEquals(8, missingNumber.xorSolution(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        }

        @Test
        @DisplayName("Missing 0: [1,2,3] → 0 (missing at start)")
        void testMissingZero() {
            assertEquals(0, missingNumber.xorSolution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Missing n: [0,1,2] → 3 (missing last element)")
        void testMissingN() {
            assertEquals(3, missingNumber.xorSolution(new int[]{0, 1, 2}));
        }

        @Test
        @DisplayName("Single element [1] → 0 (only element is 1, so 0 is missing)")
        void testSingleElementOneMissing() {
            assertEquals(0, missingNumber.xorSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Single element [0] → 1 (only element is 0, so 1 is missing)")
        void testSingleElementZeroPresent() {
            assertEquals(1, missingNumber.xorSolution(new int[]{0}));
        }

        @Test
        @DisplayName("Already sorted input: [0,1,3,4] → 2 (gap in the middle)")
        void testAlreadySortedWithGap() {
            assertEquals(2, missingNumber.xorSolution(new int[]{0, 1, 3, 4}));
        }
    }

    @Nested
    @DisplayName("gaussSolution (Gauss formula: n*(n+1)/2 - actualSum)")
    class GaussSolutionTests {

        @Test
        @DisplayName("Example 1: [3,0,1] → 2 (missing in the middle)")
        void testMissingInMiddle() {
            assertEquals(2, missingNumber.gaussSolution(new int[]{3, 0, 1}));
        }

        @Test
        @DisplayName("Example 2: [0,1] → 2 (missing at end, n=2)")
        void testMissingAtEnd() {
            assertEquals(2, missingNumber.gaussSolution(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Example 3: [9,6,4,2,3,5,7,0,1] → 8 (large array)")
        void testLargeArray() {
            assertEquals(8, missingNumber.gaussSolution(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        }

        @Test
        @DisplayName("Missing 0: [1,2,3] → 0 (missing at start)")
        void testMissingZero() {
            assertEquals(0, missingNumber.gaussSolution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Missing n: [0,1,2] → 3 (missing last element)")
        void testMissingN() {
            assertEquals(3, missingNumber.gaussSolution(new int[]{0, 1, 2}));
        }

        @Test
        @DisplayName("Single element [1] → 0 (only element is 1, so 0 is missing)")
        void testSingleElementOneMissing() {
            assertEquals(0, missingNumber.gaussSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Single element [0] → 1 (only element is 0, so 1 is missing)")
        void testSingleElementZeroPresent() {
            assertEquals(1, missingNumber.gaussSolution(new int[]{0}));
        }

        @Test
        @DisplayName("Already sorted input: [0,1,3,4] → 2 (gap in the middle)")
        void testAlreadySortedWithGap() {
            assertEquals(2, missingNumber.gaussSolution(new int[]{0, 1, 3, 4}));
        }
    }

    @Nested
    // BUG: setSolution pre-populates the HashSet with {0..n} before iterating over nums.
    // Since every element in nums is already in the set, set.add(ele) always returns false,
    // so !set.add(ele) is always true and the method unconditionally returns nums[0]
    // instead of the actual missing number. All tests below will FAIL until the bug is fixed.
    // Fix: either (a) build the set only from nums, then scan 0..n for the absent value,
    //      or (b) pre-populate with 0..n then *remove* each nums element and return what remains.
    @DisplayName("setSolution (HashSet lookup) ⚠️ BUGGY — always returns nums[0]")
    class SetSolutionTests {

        @Test
        @DisplayName("Example 1: [3,0,1] → 2 (missing in the middle)")
        void testMissingInMiddle() {
            assertEquals(2, missingNumber.setSolution(new int[]{3, 0, 1}));
        }

        @Test
        @DisplayName("Example 2: [0,1] → 2 (missing at end, n=2)")
        void testMissingAtEnd() {
            assertEquals(2, missingNumber.setSolution(new int[]{0, 1}));
        }

        @Test
        @DisplayName("Example 3: [9,6,4,2,3,5,7,0,1] → 8 (large array)")
        void testLargeArray() {
            assertEquals(8, missingNumber.setSolution(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
        }

        @Test
        @DisplayName("Missing 0: [1,2,3] → 0 (missing at start)")
        void testMissingZero() {
            assertEquals(0, missingNumber.setSolution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Missing n: [0,1,2] → 3 (missing last element)")
        void testMissingN() {
            assertEquals(3, missingNumber.setSolution(new int[]{0, 1, 2}));
        }

        @Test
        @DisplayName("Single element [1] → 0 (only element is 1, so 0 is missing)")
        void testSingleElementOneMissing() {
            assertEquals(0, missingNumber.setSolution(new int[]{1}));
        }

        @Test
        @DisplayName("Single element [0] → 1 (only element is 0, so 1 is missing)")
        void testSingleElementZeroPresent() {
            assertEquals(1, missingNumber.setSolution(new int[]{0}));
        }

        @Test
        @DisplayName("Already sorted input: [0,1,3,4] → 2 (gap in the middle)")
        void testAlreadySortedWithGap() {
            assertEquals(2, missingNumber.setSolution(new int[]{0, 1, 3, 4}));
        }
    }
}
