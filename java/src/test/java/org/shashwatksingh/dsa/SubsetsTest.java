package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Subsets Tests")
class SubsetsTest {

    private Subsets subsets;

    @BeforeEach
    void setUp() {
        subsets = new Subsets();
    }

    // ─── Helper: normalize for order-independent comparison ──────────────────────
    // Sorts each subset's elements, then sorts the outer list so two power-sets
    // with the same subsets in any order compare as equal.
    private List<List<Integer>> normalize(List<List<Integer>> lists) {
        return lists.stream()
                .map(sub -> sub.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator
                        .comparingInt(List<Integer>::size)
                        .thenComparing(l -> l.stream()
                                .map(String::valueOf)
                                .collect(Collectors.joining(","))))
                .collect(Collectors.toList());
    }

    private void assertSameSubsets(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(normalize(expected), normalize(actual));
    }

    // ═══ LeetCode Examples ═══════════════════════════════════════════════════════
    @Nested
    @DisplayName("LeetCode examples")
    class LeetCodeExamples {

        @Test
        @DisplayName("Example 1: [1,2,3] → [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]")
        void testExample1() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(1),
                    Arrays.asList(2),
                    Arrays.asList(1, 2),
                    Arrays.asList(3),
                    Arrays.asList(1, 3),
                    Arrays.asList(2, 3),
                    Arrays.asList(1, 2, 3)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Example 2: [0] → [[],[0]]")
        void testExample2() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(0)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{0}));
        }
    }

    // ═══ Power Set Size (2^n) ═════════════════════════════════════════════════════
    @Nested
    @DisplayName("Power set size is always 2^n")
    class PowerSetSizeTests {

        @Test
        @DisplayName("n=1 → 2^1 = 2 subsets")
        void testSizeForOneElement() {
            assertEquals(2, subsets.solution(new int[]{5}).size());
        }

        @Test
        @DisplayName("n=2 → 2^2 = 4 subsets")
        void testSizeForTwoElements() {
            assertEquals(4, subsets.solution(new int[]{1, 2}).size());
        }

        @Test
        @DisplayName("n=3 → 2^3 = 8 subsets")
        void testSizeForThreeElements() {
            assertEquals(8, subsets.solution(new int[]{1, 2, 3}).size());
        }

        @Test
        @DisplayName("n=4 → 2^4 = 16 subsets")
        void testSizeForFourElements() {
            assertEquals(16, subsets.solution(new int[]{1, 2, 3, 4}).size());
        }

        @Test
        @DisplayName("n=10 → 2^10 = 1024 subsets (maximum constraint)")
        void testSizeForTenElements() {
            assertEquals(1024, subsets.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).size());
        }
    }

    // ═══ Empty Subset Always Present ══════════════════════════════════════════════
    @Nested
    @DisplayName("Empty subset [] is always present in the result")
    class EmptySubsetTests {

        @Test
        @DisplayName("Single element [7]: result contains empty subset")
        void testEmptySubsetSingleElement() {
            assertTrue(subsets.solution(new int[]{7}).contains(Collections.emptyList()),
                    "Power set must always include the empty subset");
        }

        @Test
        @DisplayName("[1,2,3]: result contains empty subset")
        void testEmptySubsetThreeElements() {
            assertTrue(subsets.solution(new int[]{1, 2, 3}).contains(Collections.emptyList()),
                    "Power set must always include the empty subset");
        }

        @Test
        @DisplayName("[1,2,3,4,5]: result contains empty subset")
        void testEmptySubsetFiveElements() {
            assertTrue(subsets.solution(new int[]{1, 2, 3, 4, 5}).contains(Collections.emptyList()),
                    "Power set must always include the empty subset");
        }
    }

    // ═══ Full Array Subset Always Present ════════════════════════════════════════
    @Nested
    @DisplayName("Full-array subset (containing all elements) is always present")
    class FullArraySubsetTests {

        @Test
        @DisplayName("[1,2,3]: result contains [1,2,3]")
        void testFullSubsetThreeElements() {
            assertTrue(subsets.solution(new int[]{1, 2, 3}).contains(Arrays.asList(1, 2, 3)),
                    "Power set must always include the full array as a subset");
        }

        @Test
        @DisplayName("[4,5]: result contains [4,5]")
        void testFullSubsetTwoElements() {
            assertTrue(subsets.solution(new int[]{4, 5}).contains(Arrays.asList(4, 5)),
                    "Power set must always include the full array as a subset");
        }

        @Test
        @DisplayName("[-1,0,1]: result contains [-1,0,1]")
        void testFullSubsetMixed() {
            assertTrue(subsets.solution(new int[]{-1, 0, 1}).contains(Arrays.asList(-1, 0, 1)),
                    "Power set must always include the full array as a subset");
        }
    }

    // ═══ No Duplicate Subsets ════════════════════════════════════════════════════
    @Nested
    @DisplayName("No duplicate subsets in result")
    class NoDuplicatesTests {

        @Test
        @DisplayName("[1,2,3] produces no duplicate subsets")
        void testNoDuplicatesThreeElements() {
            List<List<Integer>> result = subsets.solution(new int[]{1, 2, 3});
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate subsets");
        }

        @Test
        @DisplayName("[1,2,3,4,5] produces no duplicate subsets")
        void testNoDuplicatesFiveElements() {
            List<List<Integer>> result = subsets.solution(new int[]{1, 2, 3, 4, 5});
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate subsets");
        }

        @Test
        @DisplayName("[-3,-2,-1,0] produces no duplicate subsets")
        void testNoDuplicatesNegatives() {
            List<List<Integer>> result = subsets.solution(new int[]{-3, -2, -1, 0});
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate subsets");
        }
    }

    // ═══ Single Element Input ═════════════════════════════════════════════════════
    @Nested
    @DisplayName("Single element inputs")
    class SingleElementTests {

        @Test
        @DisplayName("[5] → [[], [5]]")
        void testSinglePositive() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(5)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{5}));
        }

        @Test
        @DisplayName("[-3] → [[], [-3]]")
        void testSingleNegative() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(-3)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{-3}));
        }

        @Test
        @DisplayName("[0] → [[], [0]]")
        void testSingleZero() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(0)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{0}));
        }
    }

    // ═══ Negative Numbers & Zero ══════════════════════════════════════════════════
    @Nested
    @DisplayName("Inputs containing negative numbers and zero")
    class NegativeNumberTests {

        @Test
        @DisplayName("[-1,-2] → 4 subsets: [[], [-1], [-2], [-1,-2]]")
        void testTwoNegatives() {
            List<List<Integer>> expected = Arrays.asList(
                    Collections.emptyList(),
                    Arrays.asList(-1),
                    Arrays.asList(-2),
                    Arrays.asList(-1, -2)
            );
            assertSameSubsets(expected, subsets.solution(new int[]{-1, -2}));
        }

        @Test
        @DisplayName("[-10,0,10] → 8 subsets mixing negative, zero, and positive")
        void testNegativeZeroPositive() {
            List<List<Integer>> result = subsets.solution(new int[]{-10, 0, 10});
            assertEquals(8, result.size(), "Should produce 2^3 = 8 subsets");
            assertTrue(result.contains(Collections.emptyList()), "Must contain empty subset");
            assertTrue(result.contains(Arrays.asList(-10, 0, 10)), "Must contain the full array subset");
        }

        @Test
        @DisplayName("[-10,-5,0,5,10] → 32 subsets with full range of constraint values")
        void testFullRangeValues() {
            List<List<Integer>> result = subsets.solution(new int[]{-10, -5, 0, 5, 10});
            assertEquals(32, result.size(), "Should produce 2^5 = 32 subsets");
        }
    }

    // ═══ Subset Element Validity ══════════════════════════════════════════════════
    @Nested
    @DisplayName("Every element in every subset must belong to the input array")
    class SubsetValidityTests {

        @Test
        @DisplayName("All elements in subsets of [1,2,3] come from the input")
        void testSubsetElementsFromInput() {
            List<Integer> inputList = Arrays.asList(1, 2, 3);
            List<List<Integer>> result = subsets.solution(new int[]{1, 2, 3});
            assertTrue(result.stream().allMatch(inputList::containsAll),
                    "Every element in every subset must come from the original input array");
        }

        @Test
        @DisplayName("All elements in subsets of [-5,0,5,10] come from the input")
        void testSubsetElementsFromInputMixed() {
            List<Integer> inputList = Arrays.asList(-5, 0, 5, 10);
            List<List<Integer>> result = subsets.solution(new int[]{-5, 0, 5, 10});
            assertTrue(result.stream().allMatch(inputList::containsAll),
                    "Every element in every subset must come from the original input array");
        }
    }
}
