package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Find First and Last Position of Element in Sorted Array Tests")
class FirstAndLastPositionOfElementInSortedArrayTest {

    private FirstAndLastPositionOfElementInSortedArray instance;

    @BeforeEach
    void setUp() {
        instance = new FirstAndLastPositionOfElementInSortedArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  searchRange()  — two binary searches, O(log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchRange() — first and last index of target")
    class SearchRangeTests {

        @Test
        @DisplayName("LeetCode Example 1: [5,7,7,8,8,10], target=8 → [3,4]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[]{3, 4}),
                    Arrays.toString(instance.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        }

        @Test
        @DisplayName("LeetCode Example 2: [5,7,7,8,8,10], target=6 → [-1,-1] (absent, within range)")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        }

        @Test
        @DisplayName("LeetCode Example 3: [], target=0 → [-1,-1] (empty array)")
        void testLeetCodeExample3() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{}, 0)));
        }

        @Test
        @DisplayName("Scenario: [1], target=1 → [0,0] (single element, found)")
        void testSingleElementFound() {
            assertEquals(Arrays.toString(new int[]{0, 0}),
                    Arrays.toString(instance.searchRange(new int[]{1}, 1)));
        }

        @Test
        @DisplayName("Scenario: [1], target=2 → [-1,-1] (single element, not found)")
        void testSingleElementNotFound() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{1}, 2)));
        }

        @Test
        @DisplayName("Scenario: [2,2,2,2,2], target=2 → [0,4] (all same, target matches)")
        void testAllSameFound() {
            assertEquals(Arrays.toString(new int[]{0, 4}),
                    Arrays.toString(instance.searchRange(new int[]{2, 2, 2, 2, 2}, 2)));
        }

        @Test
        @DisplayName("Scenario: [2,2,2,2,2], target=3 → [-1,-1] (all same, target absent)")
        void testAllSameNotFound() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{2, 2, 2, 2, 2}, 3)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=3 → [2,2] (strictly increasing, unique)")
        void testStrictlyIncreasingUnique() {
            assertEquals(Arrays.toString(new int[]{2, 2}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3, 4, 5}, 3)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=1 → [0,0] (target at first index)")
        void testTargetAtFirstIndex() {
            assertEquals(Arrays.toString(new int[]{0, 0}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3, 4, 5}, 1)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=5 → [4,4] (target at last index)")
        void testTargetAtLastIndex() {
            assertEquals(Arrays.toString(new int[]{4, 4}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3, 4, 5}, 5)));
        }

        @Test
        @DisplayName("Scenario: [1,1,1,2,3], target=1 → [0,2] (duplicates at start)")
        void testDuplicatesAtStart() {
            assertEquals(Arrays.toString(new int[]{0, 2}),
                    Arrays.toString(instance.searchRange(new int[]{1, 1, 1, 2, 3}, 1)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,3,3], target=3 → [2,4] (duplicates at end)")
        void testDuplicatesAtEnd() {
            assertEquals(Arrays.toString(new int[]{2, 4}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3, 3, 3}, 3)));
        }

        @Test
        @DisplayName("Scenario: [5,7,7,8,8,8,8,10], target=8 → [3,6] (long run of duplicates)")
        void testLongRunOfDuplicates() {
            assertEquals(Arrays.toString(new int[]{3, 6}),
                    Arrays.toString(instance.searchRange(new int[]{5, 7, 7, 8, 8, 8, 8, 10}, 8)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], target=0 → [-1,-1] (target below all elements)")
        void testTargetBelowAll() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3}, 0)));
        }

        @Test
        @DisplayName("Scenario: [1,2,3], target=4 → [-1,-1] (target above all elements)")
        void testTargetAboveAll() {
            assertEquals(Arrays.toString(new int[]{-1, -1}),
                    Arrays.toString(instance.searchRange(new int[]{1, 2, 3}, 4)));
        }

        @Test
        @DisplayName("Scenario: [-3,-3,-1,0,0,4], target=-3 → [0,1] (negative values)")
        void testNegativeValues() {
            assertEquals(Arrays.toString(new int[]{0, 1}),
                    Arrays.toString(instance.searchRange(new int[]{-3, -3, -1, 0, 0, 4}, -3)));
        }

        @Test
        @DisplayName("Scenario: [-1e9,-1e9,0,1e9,1e9], target=1e9 → [3,4] (value boundaries)")
        void testValueBoundaries() {
            assertEquals(Arrays.toString(new int[]{3, 4}),
                    Arrays.toString(instance.searchRange(
                            new int[]{-1_000_000_000, -1_000_000_000, 0, 1_000_000_000, 1_000_000_000},
                            1_000_000_000)));
        }

        @Test
        @DisplayName("Scenario: 100000 copies of 7, target=7 → [0,99999] (max length, uniform)")
        void testMaxLengthUniform() {
            int[] nums = new int[100_000];
            Arrays.fill(nums, 7);
            assertEquals(Arrays.toString(new int[]{0, 99_999}),
                    Arrays.toString(instance.searchRange(nums, 7)));
        }

        @Test
        @DisplayName("Scenario: 100000 elements, 50000 zeros then 50000 ones, target=1 → [50000,99999] (max length, split)")
        void testMaxLengthSplit() {
            int[] nums = new int[100_000];
            Arrays.fill(nums, 50_000, 100_000, 1);
            assertEquals(Arrays.toString(new int[]{50_000, 99_999}),
                    Arrays.toString(instance.searchRange(nums, 1)));
        }
    }
}
