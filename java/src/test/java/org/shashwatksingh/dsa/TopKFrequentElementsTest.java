package org.shashwatksingh.dsa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Top K Frequent Elements Tests")
public class TopKFrequentElementsTest {

    private final TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

    // ─── Helper: sort both arrays before comparing (result may be in any order) ──
    private void assertTopK(int[] expected, int[] actual) {
        assertEquals(expected.length, actual.length, "Result length should equal k");
        int[] sortedExpected = Arrays.copyOf(expected, expected.length);
        int[] sortedActual   = Arrays.copyOf(actual, actual.length);
        Arrays.sort(sortedExpected);
        Arrays.sort(sortedActual);
        assertArrayEquals(sortedExpected, sortedActual, "Top-K elements should match (order-independent)");
    }

    // ─── LeetCode examples ───────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: [1,1,1,2,2,3], k=2 → [1,2]")
    void testExample1() {
        int[] result = topKFrequentElements.solution(new int[]{1, 1, 1, 2, 2, 3}, 2);
        assertTopK(new int[]{1, 2}, result);
    }

    @Test
    @DisplayName("Example 2: [1], k=1 → [1] (single element)")
    void testExample2() {
        int[] result = topKFrequentElements.solution(new int[]{1}, 1);
        assertTopK(new int[]{1}, result);
    }

    @Test
    @DisplayName("Example 3: [1,2,1,2,1,2,3,1,3,2], k=2 → [1,2]")
    void testExample3() {
        int[] result = topKFrequentElements.solution(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2);
        assertTopK(new int[]{1, 2}, result);
    }

    // ─── Short-circuit: k == nums.length ─────────────────────────────────────────

    @Test
    @DisplayName("k equals array length: short-circuit returns all elements")
    void testKEqualsArrayLength() {
        // Triggers the early return path (k == nums.length)
        int[] result = topKFrequentElements.solution(new int[]{1, 2, 3}, 3);
        assertTopK(new int[]{1, 2, 3}, result);
    }

    // ─── k = 1 ───────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("k=1: only the single most frequent element is returned")
    void testKEquals1() {
        int[] result = topKFrequentElements.solution(new int[]{4, 1, 4, 4, 2, 2, 3}, 1);
        assertTopK(new int[]{4}, result);
    }

    // ─── Negative numbers ────────────────────────────────────────────────────────

    @Test
    @DisplayName("Negative numbers: [-1,-1,-2,-2,-2], k=1 → [-2]")
    void testNegativeNumbers() {
        int[] result = topKFrequentElements.solution(new int[]{-1, -1, -2, -2, -2}, 1);
        assertTopK(new int[]{-2}, result);
    }

    // ─── Mixed positive and negative ─────────────────────────────────────────────

    @Test
    @DisplayName("Mixed numbers: [-1,-1,2,2,2,-3], k=2 → [-1,2]")
    void testMixedNumbers() {
        int[] result = topKFrequentElements.solution(new int[]{-1, -1, 2, 2, 2, -3}, 2);
        assertTopK(new int[]{-1, 2}, result);
    }

    // ─── All elements have equal frequency ───────────────────────────────────────

    @Test
    @DisplayName("All equal frequency: result has exactly k elements")
    void testAllEqualFrequency() {
        // Each of 1,2,3,4 appears exactly once; any k=2 of them is a valid answer
        int[] result = topKFrequentElements.solution(new int[]{1, 2, 3, 4}, 2);
        assertEquals(2, result.length, "Result should have exactly k=2 elements");
    }

    // ─── Result length sanity check ──────────────────────────────────────────────

    @Test
    @DisplayName("Result array length always equals k")
    void testResultLengthEqualsK() {
        int[] result = topKFrequentElements.solution(new int[]{1, 1, 1, 2, 2, 3}, 2);
        assertEquals(2, result.length, "Returned array length must equal k");
    }

    // ─── Larger input (from main) ─────────────────────────────────────────────────

    @Test
    @DisplayName("Larger input: k=10 returns all 10 unique elements")
    void testLargerInput() {
        // Frequencies: 5→4, 1→4, 2→4, 6→3, 3→3, 7→3, 4→2, 8→2, 10→1, 11→1
        // k=10 == total unique elements, so all must be in the result
        int[] result = topKFrequentElements.solution(
                new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6}, 10);
        assertTopK(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10, 11}, result);
    }
}
