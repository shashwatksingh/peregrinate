package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Shared test cases for every in-place ascending int[] sort in this package.
 * To cover a new algorithm, extend this class and implement {@link #sort(int[])}.
 */
public abstract class SortingAlgorithmTestBase {

    /** Sorts {@code arr} in place, ascending, using the algorithm under test. */
    protected abstract void sort(int[] arr);

    // ═══════════════════════════════════════════════════════════
    //  sort()  — in-place ascending sort (shared across all sorting algorithms)
    // ═══════════════════════════════════════════════════════════

    @Test
    @DisplayName("Typical unsorted input: [64,34,25,12,22,11,90] → [11,12,22,25,34,64,90]")
    void testTypical() {
        int[] arr = new int[]{64, 34, 25, 12, 22, 11, 90};
        int[] expected = new int[]{11, 12, 22, 25, 34, 64, 90};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Empty array: [] → []")
    void testEmptyArray() {
        int[] arr = new int[]{};
        int[] expected = new int[]{};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Single element: [1] → [1]")
    void testSingleElement() {
        int[] arr = new int[]{1};
        int[] expected = new int[]{1};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Two elements, out of order: [2,1] → [1,2]")
    void testTwoElementsUnsorted() {
        int[] arr = new int[]{2, 1};
        int[] expected = new int[]{1, 2};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Two elements, already sorted: [1,2] → [1,2]")
    void testTwoElementsSorted() {
        int[] arr = new int[]{1, 2};
        int[] expected = new int[]{1, 2};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Already sorted: [1,2,3,4,5] → [1,2,3,4,5]")
    void testAlreadySorted() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Reverse sorted: [5,4,3,2,1] → [1,2,3,4,5]")
    void testReverseSorted() {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("All-same values: [7,7,7,7] → [7,7,7,7]")
    void testAllSame() {
        int[] arr = new int[]{7, 7, 7, 7};
        int[] expected = new int[]{7, 7, 7, 7};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Duplicates: [3,1,2,3,1,2] → [1,1,2,2,3,3]")
    void testDuplicates() {
        int[] arr = new int[]{3, 1, 2, 3, 1, 2};
        int[] expected = new int[]{1, 1, 2, 2, 3, 3};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Negative values: [-1,-5,3,0,-2] → [-5,-2,-1,0,3]")
    void testNegativeValues() {
        int[] arr = new int[]{-1, -5, 3, 0, -2};
        int[] expected = new int[]{-5, -2, -1, 0, 3};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Int boundary values: [Integer.MAX_VALUE,Integer.MIN_VALUE,0] → [Integer.MIN_VALUE,0,Integer.MAX_VALUE]")
    void testIntBoundaryValues() {
        int[] arr = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        int[] expected = new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Smallest element at the end: [2,3,4,5,1] → [1,2,3,4,5]")
    void testSmallestAtEnd() {
        int[] arr = new int[]{2, 3, 4, 5, 1};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Largest element at the start: [5,1,2,3,4] → [1,2,3,4,5]")
    void testLargestAtStart() {
        int[] arr = new int[]{5, 1, 2, 3, 4};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Odd length: [3,1,2] → [1,2,3]")
    void testOddLength() {
        int[] arr = new int[]{3, 1, 2};
        int[] expected = new int[]{1, 2, 3};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Even length: [4,3,2,1] → [1,2,3,4]")
    void testEvenLength() {
        int[] arr = new int[]{4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Alternating high and low values: [1,9,2,8,3,7] → [1,2,3,7,8,9]")
    void testAlternatingHighLow() {
        int[] arr = new int[]{1, 9, 2, 8, 3, 7};
        int[] expected = new int[]{1, 2, 3, 7, 8, 9};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Duplicates at both ends: [5,2,9,1,5] → [1,2,5,5,9]")
    void testDuplicatesAtBothEnds() {
        int[] arr = new int[]{5, 2, 9, 1, 5};
        int[] expected = new int[]{1, 2, 5, 5, 9};
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Large reverse-sorted input (n=10000) → ascending 0..9999")
    void testLargeReverseSorted() {
        int n = 10000;
        int[] arr = new int[n];
        int[] expected = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - 1 - i;
            expected[i] = i;
        }
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }

    @Test
    @DisplayName("Large pseudo-random input with duplicates (n=2000, values in [-500,500]) → ascending")
    void testLargeRandom() {
        Random random = new Random(42);
        int[] arr = new int[2000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1001) - 500;
        }
        int[] expected = arr.clone();
        Arrays.sort(expected);
        sort(arr);
        assertEquals(Arrays.toString(expected), Arrays.toString(arr));
    }
}
