package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Top K Frequent Element Tests")
class TopKFrequentElementTest {

    private TopKFrequentElement instance;

    @BeforeEach
    void setUp() {
        instance = new TopKFrequentElement();
    }

    // ─── Helper: order-independent array comparison via assertEquals ─────────────
    private void assertTopK(int[] expected, int[] actual) {
        int[] sortedExpected = Arrays.copyOf(expected, expected.length);
        int[] sortedActual = Arrays.copyOf(actual, actual.length);
        Arrays.sort(sortedExpected);
        Arrays.sort(sortedActual);
        assertEquals(Arrays.toString(sortedExpected), Arrays.toString(sortedActual));
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionI()  — bounded min-heap of size k, O(n log k) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionI() — bounded min-heap of size k")
    class SolutionITests {

        @Test
        @DisplayName("LeetCode Example 1: [1,1,1,2,2,3], k=2 → [1,2] (in any order)")
        void testLeetCodeExample1() {
            assertTopK(new int[]{1, 2}, instance.solutionI(new int[]{1, 1, 1, 2, 2, 3}, 2));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1], k=1 → [1] (single-element input)")
        void testLeetCodeExample2() {
            assertTopK(new int[]{1}, instance.solutionI(new int[]{1}, 1));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,1,2,1,2,3,1,3,2], k=2 → [1,2] (in any order)")
        void testLeetCodeExample3() {
            assertTopK(new int[]{1, 2}, instance.solutionI(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2));
        }

        @Test
        @DisplayName("All-same values [5,5,5,5] with k=1 → [5] (single distinct value)")
        void testAllSameValues() {
            assertTopK(new int[]{5}, instance.solutionI(new int[]{5, 5, 5, 5}, 1));
        }

        @Test
        @DisplayName("All-unique values [1,2,3,4,5] with k=3 → any 3 elements (tied frequencies)")
        void testAllUniqueFrequenciesTied() {
            int[] result = instance.solutionI(new int[]{1, 2, 3, 4, 5}, 3);
            assertEquals(3, result.length);
        }

        @Test
        @DisplayName("k equals number of unique elements: [1,1,2,3,3] k=3 → [1,2,3]")
        void testKEqualsUniqueCount() {
            assertTopK(new int[]{1, 2, 3}, instance.solutionI(new int[]{1, 1, 2, 3, 3}, 3));
        }

        @Test
        @DisplayName("Negative numbers: [-1,-1,-2,-2,-2,3], k=1 → [-2] (most frequent)")
        void testNegativeNumbers() {
            assertTopK(new int[]{-2}, instance.solutionI(new int[]{-1, -1, -2, -2, -2, 3}, 1));
        }

        @Test
        @DisplayName("Clear frequency ranking: [4,4,4,4,1,1,1,2,2,3], k=2 → [4,1] (top two frequencies)")
        void testClearFrequencyRanking() {
            assertTopK(new int[]{4, 1}, instance.solutionI(new int[]{4, 4, 4, 4, 1, 1, 1, 2, 2, 3}, 2));
        }

        // ─── Boundary values of k ──────────────────────────────────────────────

        @Test
        @DisplayName("k=0: heap never exceeds capacity 0, so every insert is immediately evicted → []")
        void testKEqualsZero() {
            int[] result = instance.solutionI(new int[]{1, 1, 2, 2, 3, 3, 3}, 0);
            assertEquals(0, result.length);
        }

        @Test
        @DisplayName("Negative k: size() > k is always true, so every insert is immediately evicted → []")
        void testNegativeK() {
            int[] result = instance.solutionI(new int[]{1, 1, 2, 2, 3}, -1);
            assertEquals(0, result.length);
        }

        @Test
        @DisplayName("k greater than unique element count: [1,2,3] k=5 → only the 3 unique elements, not padded")
        void testKGreaterThanUniqueCount() {
            int[] result = instance.solutionI(new int[]{1, 2, 3}, 5);
            assertTopK(new int[]{1, 2, 3}, result);
        }

        // ─── Tied frequencies at the cutoff boundary ────────────────────────────

        @Test
        @DisplayName("Three-way tie for two spots: [1,1,2,2,3,3,4], k=2 → exactly 2 of the tied {1,2,3} candidates")
        void testTiedFrequenciesAtCutoff() {
            // 1, 2, and 3 all occur twice; 4 occurs once. Only 2 of the 3 tied
            // values can survive, and which one is evicted depends on HashMap
            // iteration order rather than any defined tie-break rule, so we
            // assert the size/membership invariant instead of one exact answer.
            int[] result = instance.solutionI(new int[]{1, 1, 2, 2, 3, 3, 4}, 2);
            assertEquals(2, result.length);
            Set<Integer> tiedCandidates = Set.of(1, 2, 3);
            for (int value : result) {
                assertTrue(tiedCandidates.contains(value));
            }
        }

        // ─── Invalid input ───────────────────────────────────────────────────────

        @Test
        @DisplayName("null input array throws NullPointerException")
        void testNullInputThrows() {
            assertThrows(NullPointerException.class, () -> instance.solutionI(null, 1));
        }
    }
}
