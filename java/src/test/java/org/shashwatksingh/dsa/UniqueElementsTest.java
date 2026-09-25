package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("UniqueElements Tests")
class UniqueElementsTest {

    private UniqueElements instance;

    @BeforeEach
    void setUp() {
        instance = new UniqueElements();
    }

    private static int[] sorted(int[] values) {
        int[] copy = values.clone();
        Arrays.sort(copy);
        return copy;
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionSets()  — toggles membership in a HashSet, keeps elements seen exactly once
    // ═══════════════════════════════════════════════════════════
    @Nested
    // NOTE: HashSet iteration order is unspecified, so results are compared
    // after sorting both sides rather than via direct array equality.
    @DisplayName("solutionSets() — elements appearing exactly once (order-independent)")
    class SolutionSetsTests {

        @Test
        @DisplayName("Mixed duplicates and uniques: [1,2,3,4,5,1,2,3] → {4,5}")
        void testMixedDuplicatesAndUniques() {
            int[] result = instance.solutionSets(new int[]{1, 2, 3, 4, 5, 1, 2, 3});
            assertArrayEquals(new int[]{4, 5}, sorted(result));
        }

        @Test
        @DisplayName("Single-element array: [5] → {5}")
        void testSingleElementArray() {
            int[] result = instance.solutionSets(new int[]{5});
            assertArrayEquals(new int[]{5}, sorted(result));
        }

        @Test
        @DisplayName("All-same values: [3,3,3] → {} (no unique elements)")
        void testAllSameValues() {
            int[] result = instance.solutionSets(new int[]{3, 3, 3});
            assertArrayEquals(new int[]{}, sorted(result));
        }

        @Test
        @DisplayName("No unique elements, all duplicated: [1,1,2,2] → {}")
        void testNoUniqueElements() {
            int[] result = instance.solutionSets(new int[]{1, 1, 2, 2});
            assertArrayEquals(new int[]{}, sorted(result));
        }

        @Test
        @DisplayName("No duplicates at all: [1,2,3] → {1,2,3}")
        void testAllElementsUnique() {
            int[] result = instance.solutionSets(new int[]{1, 2, 3});
            assertArrayEquals(new int[]{1, 2, 3}, sorted(result));
        }

        @Test
        @DisplayName("Element appearing three times is excluded: [1,1,1,2] → {2}")
        void testTripleOccurrenceIsExcluded() {
            int[] result = instance.solutionSets(new int[]{1, 1, 1, 2});
            assertArrayEquals(new int[]{2}, sorted(result));
        }

        @Test
        @DisplayName("Negative and boundary values: [-5,-5,0,7] → {0,7}")
        void testNegativeAndBoundaryValues() {
            int[] result = instance.solutionSets(new int[]{-5, -5, 0, 7});
            assertArrayEquals(new int[]{0, 7}, sorted(result));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionHashMap()  — toggles membership in a List, preserves first-seen order
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionHashMap() — elements appearing exactly once, first-seen order preserved")
    class SolutionHashMapTests {

        @Test
        @DisplayName("Mixed duplicates and uniques: [1,2,3,4,5,1,2,3] → [4,5]")
        void testMixedDuplicatesAndUniques() {
            assertArrayEquals(new int[]{4, 5}, instance.solutionHashMap(new int[]{1, 2, 3, 4, 5, 1, 2, 3}));
        }

        @Test
        @DisplayName("Single-element array: [5] → [5]")
        void testSingleElementArray() {
            assertArrayEquals(new int[]{5}, instance.solutionHashMap(new int[]{5}));
        }

        @Test
        @DisplayName("All-same values: [3,3,3] → [] (no unique elements)")
        void testAllSameValues() {
            assertArrayEquals(new int[]{}, instance.solutionHashMap(new int[]{3, 3, 3}));
        }

        @Test
        @DisplayName("No unique elements, all duplicated: [1,1,2,2] → []")
        void testNoUniqueElements() {
            assertArrayEquals(new int[]{}, instance.solutionHashMap(new int[]{1, 1, 2, 2}));
        }

        @Test
        @DisplayName("No duplicates at all, order preserved: [3,1,2] → [3,1,2]")
        void testAllElementsUniqueOrderPreserved() {
            assertArrayEquals(new int[]{3, 1, 2}, instance.solutionHashMap(new int[]{3, 1, 2}));
        }

        @Test
        @DisplayName("Element appearing three times is excluded: [1,1,1,2] → [2]")
        void testTripleOccurrenceIsExcluded() {
            assertArrayEquals(new int[]{2}, instance.solutionHashMap(new int[]{1, 1, 1, 2}));
        }

        @Test
        @DisplayName("Negative and boundary values: [-5,-5,0,7] → [0,7]")
        void testNegativeAndBoundaryValues() {
            assertArrayEquals(new int[]{0, 7}, instance.solutionHashMap(new int[]{-5, -5, 0, 7}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solutionSorting()  — sort then single scan grouping consecutive equal runs
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionSorting()")
    class SolutionSortingTests {

        @Test
        @DisplayName("Mixed duplicates and uniques: [1,2,3,4,5,1,2,3] → [4,5]")
        void testMixedDuplicatesAndUniquesHappensToWork() {
            int[] result = instance.solutionSorting(new int[]{1, 2, 3, 4, 5, 1, 2, 3});
            assertArrayEquals(new int[]{4, 5}, sorted(result));
        }

        @Test
        @DisplayName("trailing singleton is dropped entirely: [1,1,2] → [2] ")
        void testTrailingSingletonIsDropped() {
            int[] result = instance.solutionSorting(new int[]{1, 1, 2});
            assertArrayEquals(new int[]{2}, sorted(result));
        }

        @Test
        @DisplayName("a duplicated value gets wrongly included and the true trailing singleton is missed: [1,2,2,3] → [1,3]")
        void testDuplicateWronglyIncludedAndSingletonMissed() {
            int[] result = instance.solutionSorting(new int[]{1, 2, 2, 3});
            assertArrayEquals(new int[]{1, 3}, sorted(result));
        }

        @Test
        @DisplayName("single-element array, no transition ever happens: [5] → [5]")
        void testSingleElementArrayNeverTriggersAdd() {
            int[] result = instance.solutionSorting(new int[]{5});
            assertArrayEquals(new int[]{5}, sorted(result));
        }

        @Test
        @DisplayName("No unique elements, all duplicated: [1,1,2,2] → []")
        void testNoUniqueElements() {
            int[] result = instance.solutionSorting(new int[]{1, 1, 2, 2});
            assertArrayEquals(new int[]{}, sorted(result));
        }
    }
}
