package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Find Target Indices After Sorting Array Tests")
class FindTargetIndicesAfterSortingArrayTest {

    private FindTargetIndicesAfterSortingArray instance;

    @BeforeEach
    void setUp() {
        instance = new FindTargetIndicesAfterSortingArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  targetIndicesSorting()  — sort, then scan for the target, O(n log n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("targetIndicesSorting() — sort")
    class TargetIndicesSortingTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,5,2,3], target=2 → [1,2]")
        void testLeetCodeExample1() {
            List<Integer> expected = List.of(1, 2);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 5, 2, 3}, 2).toString());
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,5,2,3], target=3 → [3]")
        void testLeetCodeExample2() {
            List<Integer> expected = List.of(3);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 5, 2, 3}, 3).toString());
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,5,2,3], target=5 → [4]")
        void testLeetCodeExample3() {
            List<Integer> expected = List.of(4);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 5, 2, 3}, 5).toString());
        }

        @Test
        @DisplayName("Target absent, between existing values: [1,2,5,2,3], target=4 → []")
        void testTargetAbsentBetween() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 5, 2, 3}, 4).toString());
        }

        @Test
        @DisplayName("Target smaller than every element: [2,3,4], target=1 → []")
        void testTargetBelowAll() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{2, 3, 4}, 1).toString());
        }

        @Test
        @DisplayName("Target larger than every element: [1,2,3], target=10 → []")
        void testTargetAboveAll() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 3}, 10).toString());
        }

        @Test
        @DisplayName("Single element, match: [5], target=5 → [0]")
        void testSingleElementMatch() {
            List<Integer> expected = List.of(0);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{5}, 5).toString());
        }

        @Test
        @DisplayName("Single element, no match: [5], target=3 → []")
        void testSingleElementNoMatch() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{5}, 3).toString());
        }

        @Test
        @DisplayName("All-same values, target matches: [7,7,7], target=7 → [0,1,2]")
        void testAllSameMatch() {
            List<Integer> expected = List.of(0, 1, 2);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{7, 7, 7}, 7).toString());
        }

        @Test
        @DisplayName("All-same values, target differs: [7,7,7], target=3 → []")
        void testAllSameNoMatch() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{7, 7, 7}, 3).toString());
        }

        @Test
        @DisplayName("Already sorted, unique target: [1,2,3,4,5], target=3 → [2]")
        void testAlreadySorted() {
            List<Integer> expected = List.of(2);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 2, 3, 4, 5}, 3).toString());
        }

        @Test
        @DisplayName("Reverse sorted, smallest value: [5,4,3,2,1], target=1 → [0]")
        void testReverseSortedSmallest() {
            List<Integer> expected = List.of(0);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{5, 4, 3, 2, 1}, 1).toString());
        }

        @Test
        @DisplayName("Reverse sorted, largest value: [5,4,3,2,1], target=5 → [4]")
        void testReverseSortedLargest() {
            List<Integer> expected = List.of(4);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{5, 4, 3, 2, 1}, 5).toString());
        }

        @Test
        @DisplayName("Duplicates scattered through the array: [2,1,2,3,2], target=2 → [1,2,3]")
        void testScatteredDuplicates() {
            List<Integer> expected = List.of(1, 2, 3);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{2, 1, 2, 3, 2}, 2).toString());
        }

        @Test
        @DisplayName("Target is the minimum with duplicates: [1,3,1,2,1], target=1 → [0,1,2]")
        void testMinimumWithDuplicates() {
            List<Integer> expected = List.of(0, 1, 2);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 3, 1, 2, 1}, 1).toString());
        }

        @Test
        @DisplayName("Target is the maximum with duplicates: [4,9,9,1,9], target=9 → [2,3,4]")
        void testMaximumWithDuplicates() {
            List<Integer> expected = List.of(2, 3, 4);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{4, 9, 9, 1, 9}, 9).toString());
        }

        @Test
        @DisplayName("Upper boundary value 100 with duplicates: [100,1,100,1], target=100 → [2,3]")
        void testUpperBoundaryValue() {
            List<Integer> expected = List.of(2, 3);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{100, 1, 100, 1}, 100).toString());
        }

        @Test
        @DisplayName("Lower boundary value 1 with duplicates: [100,1,100,1], target=1 → [0,1]")
        void testLowerBoundaryValue() {
            List<Integer> expected = List.of(0, 1);
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{100, 1, 100, 1}, 1).toString());
        }

        @Test
        @DisplayName("Target absent between sparse values: [1,3,5], target=2 → []")
        void testTargetAbsentSparse() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndicesSorting(new int[]{1, 3, 5}, 2).toString());
        }

        @Test
        @DisplayName("Max size (n=100): values 1..100 once each, target 100 → [99]")
        void testMaxSizeDistinctLargest() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = (i * 37) % 100 + 1;
            List<Integer> expected = List.of(99);
            assertEquals(expected.toString(), instance.targetIndicesSorting(nums, 100).toString());
        }

        @Test
        @DisplayName("Max size (n=100): all values equal to target → [0..99]")
        void testMaxSizeAllTarget() {
            int[] nums = new int[100];
            Arrays.fill(nums, 100);
            List<Integer> expected = java.util.stream.IntStream.range(0, 100).boxed().toList();
            assertEquals(expected.toString(), instance.targetIndicesSorting(nums, 100).toString());
        }

        @Test
        @DisplayName("Max size (n=100): descending 100..1, target 50 → [49]")
        void testMaxSizeDescending() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = 100 - i;
            List<Integer> expected = List.of(49);
            assertEquals(expected.toString(), instance.targetIndicesSorting(nums, 50).toString());
        }

        @Test
        @DisplayName("Max size (n=100): alternating 1 and 2, target 2 → [50..99]")
        void testMaxSizeAlternating() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = i % 2 == 0 ? 1 : 2;
            List<Integer> expected = java.util.stream.IntStream.range(50, 100).boxed().toList();
            assertEquals(expected.toString(), instance.targetIndicesSorting(nums, 2).toString());
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  targetIndices()  — count smaller and equal elements, O(n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("targetIndices() — count smaller and equal elements")
    class TargetIndicesTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,5,2,3], target=2 → [1,2]")
        void testLeetCodeExample1() {
            List<Integer> expected = List.of(1, 2);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 5, 2, 3}, 2).toString());
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,2,5,2,3], target=3 → [3]")
        void testLeetCodeExample2() {
            List<Integer> expected = List.of(3);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 5, 2, 3}, 3).toString());
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,5,2,3], target=5 → [4]")
        void testLeetCodeExample3() {
            List<Integer> expected = List.of(4);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 5, 2, 3}, 5).toString());
        }

        @Test
        @DisplayName("Target absent, between existing values: [1,2,5,2,3], target=4 → []")
        void testTargetAbsentBetween() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 5, 2, 3}, 4).toString());
        }

        @Test
        @DisplayName("Target smaller than every element: [2,3,4], target=1 → []")
        void testTargetBelowAll() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{2, 3, 4}, 1).toString());
        }

        @Test
        @DisplayName("Target larger than every element: [1,2,3], target=10 → []")
        void testTargetAboveAll() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 3}, 10).toString());
        }

        @Test
        @DisplayName("Single element, match: [5], target=5 → [0]")
        void testSingleElementMatch() {
            List<Integer> expected = List.of(0);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{5}, 5).toString());
        }

        @Test
        @DisplayName("Single element, no match: [5], target=3 → []")
        void testSingleElementNoMatch() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{5}, 3).toString());
        }

        @Test
        @DisplayName("All-same values, target matches: [7,7,7], target=7 → [0,1,2]")
        void testAllSameMatch() {
            List<Integer> expected = List.of(0, 1, 2);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{7, 7, 7}, 7).toString());
        }

        @Test
        @DisplayName("All-same values, target differs: [7,7,7], target=3 → []")
        void testAllSameNoMatch() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{7, 7, 7}, 3).toString());
        }

        @Test
        @DisplayName("Already sorted, unique target: [1,2,3,4,5], target=3 → [2]")
        void testAlreadySorted() {
            List<Integer> expected = List.of(2);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 2, 3, 4, 5}, 3).toString());
        }

        @Test
        @DisplayName("Reverse sorted, smallest value: [5,4,3,2,1], target=1 → [0]")
        void testReverseSortedSmallest() {
            List<Integer> expected = List.of(0);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{5, 4, 3, 2, 1}, 1).toString());
        }

        @Test
        @DisplayName("Reverse sorted, largest value: [5,4,3,2,1], target=5 → [4]")
        void testReverseSortedLargest() {
            List<Integer> expected = List.of(4);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{5, 4, 3, 2, 1}, 5).toString());
        }

        @Test
        @DisplayName("Duplicates scattered through the array: [2,1,2,3,2], target=2 → [1,2,3]")
        void testScatteredDuplicates() {
            List<Integer> expected = List.of(1, 2, 3);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{2, 1, 2, 3, 2}, 2).toString());
        }

        @Test
        @DisplayName("Target is the minimum with duplicates: [1,3,1,2,1], target=1 → [0,1,2]")
        void testMinimumWithDuplicates() {
            List<Integer> expected = List.of(0, 1, 2);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 3, 1, 2, 1}, 1).toString());
        }

        @Test
        @DisplayName("Target is the maximum with duplicates: [4,9,9,1,9], target=9 → [2,3,4]")
        void testMaximumWithDuplicates() {
            List<Integer> expected = List.of(2, 3, 4);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{4, 9, 9, 1, 9}, 9).toString());
        }

        @Test
        @DisplayName("Upper boundary value 100 with duplicates: [100,1,100,1], target=100 → [2,3]")
        void testUpperBoundaryValue() {
            List<Integer> expected = List.of(2, 3);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{100, 1, 100, 1}, 100).toString());
        }

        @Test
        @DisplayName("Lower boundary value 1 with duplicates: [100,1,100,1], target=1 → [0,1]")
        void testLowerBoundaryValue() {
            List<Integer> expected = List.of(0, 1);
            assertEquals(expected.toString(), instance.targetIndices(new int[]{100, 1, 100, 1}, 1).toString());
        }

        @Test
        @DisplayName("Target absent between sparse values: [1,3,5], target=2 → []")
        void testTargetAbsentSparse() {
            List<Integer> expected = List.of();
            assertEquals(expected.toString(), instance.targetIndices(new int[]{1, 3, 5}, 2).toString());
        }

        @Test
        @DisplayName("Max size (n=100): values 1..100 once each, target 100 → [99]")
        void testMaxSizeDistinctLargest() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = (i * 37) % 100 + 1;
            List<Integer> expected = List.of(99);
            assertEquals(expected.toString(), instance.targetIndices(nums, 100).toString());
        }

        @Test
        @DisplayName("Max size (n=100): all values equal to target → [0..99]")
        void testMaxSizeAllTarget() {
            int[] nums = new int[100];
            Arrays.fill(nums, 100);
            List<Integer> expected = java.util.stream.IntStream.range(0, 100).boxed().toList();
            assertEquals(expected.toString(), instance.targetIndices(nums, 100).toString());
        }

        @Test
        @DisplayName("Max size (n=100): descending 100..1, target 50 → [49]")
        void testMaxSizeDescending() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = 100 - i;
            List<Integer> expected = List.of(49);
            assertEquals(expected.toString(), instance.targetIndices(nums, 50).toString());
        }

        @Test
        @DisplayName("Max size (n=100): alternating 1 and 2, target 2 → [50..99]")
        void testMaxSizeAlternating() {
            int[] nums = new int[100];
            for (int i = 0; i < 100; i++) nums[i] = i % 2 == 0 ? 1 : 2;
            List<Integer> expected = java.util.stream.IntStream.range(50, 100).boxed().toList();
            assertEquals(expected.toString(), instance.targetIndices(nums, 2).toString());
        }
    }
}
