package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Quick Sort Tests")
class QuickSortTest extends SortingAlgorithmTestBase {

    private QuickSort instance;

    @BeforeEach
    void setUp() {
        instance = new QuickSort();
    }

    @Override
    protected void sort(int[] arr) {
        instance.sort(arr, 0, arr.length - 1);
    }

    // ═══════════════════════════════════════════════════════════
    //  sort(arr, low, high)  — sorts only the inclusive range [low, high], O(n log n) average time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("sort(arr, low, high) — sorts only the inclusive sub-range")
    class SubRangeTests {

        @Test
        @DisplayName("Middle sub-range: [9,5,4,3,2,1,0], low=1, high=4 → [9,2,3,4,5,1,0] (outside the range untouched)")
        void testMiddleSubRange() {
            int[] arr = {9, 5, 4, 3, 2, 1, 0};
            int[] expected = {9, 2, 3, 4, 5, 1, 0};
            instance.sort(arr, 1, 4);
            assertEquals(Arrays.toString(expected), Arrays.toString(arr));
        }

        @Test
        @DisplayName("Prefix sub-range: [5,4,3,2,1], low=0, high=2 → [3,4,5,2,1]")
        void testPrefixSubRange() {
            int[] arr = {5, 4, 3, 2, 1};
            int[] expected = {3, 4, 5, 2, 1};
            instance.sort(arr, 0, 2);
            assertEquals(Arrays.toString(expected), Arrays.toString(arr));
        }

        @Test
        @DisplayName("Suffix sub-range: [5,4,3,2,1], low=2, high=4 → [5,4,1,2,3]")
        void testSuffixSubRange() {
            int[] arr = {5, 4, 3, 2, 1};
            int[] expected = {5, 4, 1, 2, 3};
            instance.sort(arr, 2, 4);
            assertEquals(Arrays.toString(expected), Arrays.toString(arr));
        }

        @Test
        @DisplayName("Single-element range: [3,1,2], low=1, high=1 → [3,1,2] (unchanged)")
        void testSingleElementRange() {
            int[] arr = {3, 1, 2};
            int[] expected = {3, 1, 2};
            instance.sort(arr, 1, 1);
            assertEquals(Arrays.toString(expected), Arrays.toString(arr));
        }

        @Test
        @DisplayName("Empty range (low > high): [3,2,1], low=2, high=0 → [3,2,1] (unchanged)")
        void testEmptyRange() {
            int[] arr = {3, 2, 1};
            int[] expected = {3, 2, 1};
            instance.sort(arr, 2, 0);
            assertEquals(Arrays.toString(expected), Arrays.toString(arr));
        }
    }
}
