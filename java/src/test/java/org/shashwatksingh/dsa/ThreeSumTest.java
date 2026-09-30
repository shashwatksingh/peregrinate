package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ThreeSum Tests")
class ThreeSumTest {

    private ThreeSum instance;

    @BeforeEach
    void setUp() {
        instance = new ThreeSum();
    }

    // ─── Helper: normalize for order-independent comparison ──────────────────────
    // Sorts each triplet's elements, then sorts the outer list so two triplet sets
    // with the same triplets in any order compare as equal.
    private List<List<Integer>> normalize(List<List<Integer>> triplets) {
        return triplets.stream()
                .map(t -> t.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(t -> t.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(","))))
                .collect(Collectors.toList());
    }

    private void assertSameTriplets(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(normalize(expected), normalize(actual));
    }

    private static int[] boundaryValueArray(int copiesEach) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < copiesEach; i++) values.add(-100000);
        for (int i = 0; i < copiesEach; i++) values.add(0);
        for (int i = 0; i < copiesEach; i++) values.add(100000);
        return values.stream().mapToInt(Integer::intValue).toArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  threeSumBruteForce()  — triple nested loop, O(n^3) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("threeSumBruteForce() — triple nested loop")
    class ThreeSumBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]")
        void testLeetCodeExample1() {
            List<List<Integer>> expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
            assertSameTriplets(expected, instance.threeSumBruteForce(new int[]{-1, 0, 1, 2, -1, -4}));
        }

        @Test
        @DisplayName("LeetCode Example 2, no valid answer: [0,1,1] → []")
        void testLeetCodeExample2NoValidAnswer() {
            assertSameTriplets(List.of(), instance.threeSumBruteForce(new int[]{0, 1, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3, all-same values: [0,0,0] → [[0,0,0]]")
        void testLeetCodeExample3AllSameValues() {
            assertSameTriplets(List.of(List.of(0, 0, 0)), instance.threeSumBruteForce(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Minimum-size input, valid triplet: [-1,-1,2] → [[-1,-1,2]]")
        void testMinimumSizeValidTriplet() {
            assertSameTriplets(List.of(List.of(-1, -1, 2)), instance.threeSumBruteForce(new int[]{-1, -1, 2}));
        }

        @Test
        @DisplayName("Minimum-size input, no valid answer: [1,2,3] → []")
        void testMinimumSizeNoValidAnswer() {
            assertSameTriplets(List.of(), instance.threeSumBruteForce(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Duplicates yielding a single distinct triplet: [-2,0,0,2,2] → [[-2,0,2]]")
        void testDuplicatesYieldingSingleTriplet() {
            assertSameTriplets(List.of(List.of(-2, 0, 2)), instance.threeSumBruteForce(new int[]{-2, 0, 0, 2, 2}));
        }

        @Test
        @DisplayName("Consecutive distinct values yielding multiple triplets: [-2,-1,0,1,2] → [[-2,0,2],[-1,0,1]]")
        void testConsecutiveDistinctValuesMultipleTriplets() {
            List<List<Integer>> expected = List.of(List.of(-2, 0, 2), List.of(-1, 0, 1));
            assertSameTriplets(expected, instance.threeSumBruteForce(new int[]{-2, -1, 0, 1, 2}));
        }

        @Test
        @DisplayName("Boundary values at min/max constraint (scaled down from the 3000-length constraint to keep O(n^3) runnable): 50 copies each of -100000, 0, 100000 → [[-100000,0,100000],[0,0,0]]")
        void testBoundaryValuesScaledDown() {
            List<List<Integer>> expected = List.of(List.of(-100000, 0, 100000), List.of(0, 0, 0));
            assertSameTriplets(expected, instance.threeSumBruteForce(boundaryValueArray(50)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  threeSumOptimised()  — sort + two-pointer, O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("threeSumOptimised() — sort + two-pointer")
    class ThreeSumOptimisedTests {

        @Test
        @DisplayName("LeetCode Example 1: [-1,0,1,2,-1,-4] → [[-1,-1,2],[-1,0,1]]")
        void testLeetCodeExample1() {
            List<List<Integer>> expected = List.of(List.of(-1, -1, 2), List.of(-1, 0, 1));
            assertSameTriplets(expected, instance.threeSumOptimised(new int[]{-1, 0, 1, 2, -1, -4}));
        }

        @Test
        @DisplayName("LeetCode Example 2, no valid answer: [0,1,1] → []")
        void testLeetCodeExample2NoValidAnswer() {
            assertSameTriplets(List.of(), instance.threeSumOptimised(new int[]{0, 1, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3, all-same values: [0,0,0] → [[0,0,0]]")
        void testLeetCodeExample3AllSameValues() {
            assertSameTriplets(List.of(List.of(0, 0, 0)), instance.threeSumOptimised(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Minimum-size input, valid triplet: [-1,-1,2] → [[-1,-1,2]]")
        void testMinimumSizeValidTriplet() {
            assertSameTriplets(List.of(List.of(-1, -1, 2)), instance.threeSumOptimised(new int[]{-1, -1, 2}));
        }

        @Test
        @DisplayName("Minimum-size input, no valid answer: [1,2,3] → []")
        void testMinimumSizeNoValidAnswer() {
            assertSameTriplets(List.of(), instance.threeSumOptimised(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Duplicates yielding a single distinct triplet: [-2,0,0,2,2] → [[-2,0,2]]")
        void testDuplicatesYieldingSingleTriplet() {
            assertSameTriplets(List.of(List.of(-2, 0, 2)), instance.threeSumOptimised(new int[]{-2, 0, 0, 2, 2}));
        }

        @Test
        @DisplayName("Consecutive distinct values yielding multiple triplets: [-2,-1,0,1,2] → [[-2,0,2],[-1,0,1]]")
        void testConsecutiveDistinctValuesMultipleTriplets() {
            List<List<Integer>> expected = List.of(List.of(-2, 0, 2), List.of(-1, 0, 1));
            assertSameTriplets(expected, instance.threeSumOptimised(new int[]{-2, -1, 0, 1, 2}));
        }

        @Test
        @DisplayName("Max constraint boundary: 1000 copies each of -100000, 0, 100000 (length=3000) → [[-100000,0,100000],[0,0,0]]")
        void testMaxConstraintBoundary() {
            List<List<Integer>> expected = List.of(List.of(-100000, 0, 100000), List.of(0, 0, 0));
            assertSameTriplets(expected, instance.threeSumOptimised(boundaryValueArray(1000)));
        }
    }
}
