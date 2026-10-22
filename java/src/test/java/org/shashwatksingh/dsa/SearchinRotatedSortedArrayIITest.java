package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Search in Rotated Sorted Array II Tests")
class SearchinRotatedSortedArrayIITest {

    private SearchinRotatedSortedArrayII instance;

    @BeforeEach
    void setUp() {
        instance = new SearchinRotatedSortedArrayII();
    }

    // ═══════════════════════════════════════════════════════════
    //  search()  — binary search on rotated array with duplicates, O(log n) average / O(n) worst
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("search() — is target present in rotated sorted array with duplicates")
    class SearchTests {
        @Test
        @DisplayName("LeetCode Example 1: [2,5,6,0,0,1,2], target=0 → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,5,6,0,0,1,2], target=3 → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Single element, present: [1], target=1 → true")
        void testSingleElementPresent() {
            assertEquals(true, instance.search(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Single element, absent: [1], target=0 → false")
        void testSingleElementAbsent() {
            assertEquals(false, instance.search(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=1 → true")
        void testTwoElementsFindSmaller() {
            assertEquals(true, instance.search(new int[]{3, 1}, 1));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=3 → true")
        void testTwoElementsFindLarger() {
            assertEquals(true, instance.search(new int[]{3, 1}, 3));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=2 → false")
        void testTwoElementsAbsent() {
            assertEquals(false, instance.search(new int[]{3, 1}, 2));
        }

        @Test
        @DisplayName("All-same values, present: [1,1,1,1,1], target=1 → true")
        void testAllSamePresent() {
            assertEquals(true, instance.search(new int[]{1, 1, 1, 1, 1}, 1));
        }

        @Test
        @DisplayName("All-same values, absent: [1,1,1,1,1], target=2 → false")
        void testAllSameAbsent() {
            assertEquals(false, instance.search(new int[]{1, 1, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=5 → true")
        void testNotRotatedFindLast() {
            assertEquals(true, instance.search(new int[]{1, 2, 3, 4, 5}, 5));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=1 → true")
        void testNotRotatedFindFirst() {
            assertEquals(true, instance.search(new int[]{1, 2, 3, 4, 5}, 1));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=6 → false (above max)")
        void testNotRotatedAboveMax() {
            assertEquals(false, instance.search(new int[]{1, 2, 3, 4, 5}, 6));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=0 → false (below min)")
        void testNotRotatedBelowMin() {
            assertEquals(false, instance.search(new int[]{1, 2, 3, 4, 5}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=0 → true")
        void testDistinctRotatedFindMin() {
            assertEquals(true, instance.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=7 → true (max just before pivot)")
        void testDistinctRotatedFindMax() {
            assertEquals(true, instance.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=3 → false (falls in the gap)")
        void testDistinctRotatedInGap() {
            assertEquals(false, instance.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=8 → false")
        void testRotatedAboveMax() {
            assertEquals(false, instance.search(new int[]{5, 6, 7, 1, 2, 3}, 8));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=0 → false")
        void testRotatedBelowMin() {
            assertEquals(false, instance.search(new int[]{5, 6, 7, 1, 2, 3}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,0,1,1,1], target=0 → true")
        void testDuplicatesHidePivotLeft() {
            assertEquals(true, instance.search(new int[]{1, 0, 1, 1, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,0,1], target=0 → true")
        void testDuplicatesHidePivotRight() {
            assertEquals(true, instance.search(new int[]{1, 1, 1, 0, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,1,0,1,1], target=0 → true")
        void testDuplicatesHidePivotCenter() {
            assertEquals(true, instance.search(new int[]{1, 1, 1, 1, 0, 1, 1}, 0));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [2,2,2,3,2,2,2], target=3 → true")
        void testSingleLargerAmongDuplicates() {
            assertEquals(true, instance.search(new int[]{2, 2, 2, 3, 2, 2, 2}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,3,1,1,1], target=3 → true")
        void testSingleLargerNearStart() {
            assertEquals(true, instance.search(new int[]{1, 3, 1, 1, 1}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,2,1,1,1], target=2 → true")
        void testSingleLargerNearStartTwo() {
            assertEquals(true, instance.search(new int[]{1, 2, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=1 → true")
        void testDuplicatesAtBothEnds() {
            assertEquals(true, instance.search(new int[]{2, 2, 3, 4, 0, 1, 2}, 1));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=5 → false")
        void testDuplicatesAtBothEndsAbsent() {
            assertEquals(false, instance.search(new int[]{2, 2, 3, 4, 0, 1, 2}, 5));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=-10000 → true")
        void testBoundaryValueMin() {
            assertEquals(true, instance.search(new int[]{10000, -10000}, -10000));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=10000 → true")
        void testBoundaryValueMax() {
            assertEquals(true, instance.search(new int[]{10000, -10000}, 10000));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-5 → true")
        void testNegativeValuesPresent() {
            assertEquals(true, instance.search(new int[]{-3, -2, -1, -5, -4}, -5));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-6 → false")
        void testNegativeValuesAbsent() {
            assertEquals(false, instance.search(new int[]{-3, -2, -1, -5, -4}, -6));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=0 → true")
        void testMaxBoundaryDistinctFindMin() {
            assertEquals(true, instance.search(rotatedDistinct(5000, 2500), 0));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=4999 → true")
        void testMaxBoundaryDistinctFindMax() {
            assertEquals(true, instance.search(rotatedDistinct(5000, 2500), 4999));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=5000 → false")
        void testMaxBoundaryDistinctAbsent() {
            assertEquals(false, instance.search(rotatedDistinct(5000, 2500), 5000));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=1 → true")
        void testMaxBoundaryAllSamePresent() {
            assertEquals(true, instance.search(uniform(5000, 1, -1, 0), 1));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=2 → false")
        void testMaxBoundaryAllSameAbsent() {
            assertEquals(false, instance.search(uniform(5000, 1, -1, 0), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 2 at index 2500, target=2 → true")
        void testMaxBoundarySingleOddValue() {
            assertEquals(true, instance.search(uniform(5000, 1, 2500, 2), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 0 at index 2500, target=0 → true")
        void testMaxBoundarySingleSmallerValue() {
            assertEquals(true, instance.search(uniform(5000, 1, 2500, 0), 0));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchInRotatedParts()  — find pivot, then binary search each sorted part
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchInRotatedParts() — is target present in rotated sorted array with duplicates")
    class SearchInRotatedPartsTests {
        @Test
        @DisplayName("LeetCode Example 1: [2,5,6,0,0,1,2], target=0 → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,5,6,0,0,1,2], target=3 → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Single element, present: [1], target=1 → true")
        void testSingleElementPresent() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Single element, absent: [1], target=0 → false")
        void testSingleElementAbsent() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=1 → true")
        void testTwoElementsFindSmaller() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{3, 1}, 1));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=3 → true")
        void testTwoElementsFindLarger() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{3, 1}, 3));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=2 → false")
        void testTwoElementsAbsent() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{3, 1}, 2));
        }

        @Test
        @DisplayName("All-same values, present: [1,1,1,1,1], target=1 → true")
        void testAllSamePresent() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 1, 1, 1, 1}, 1));
        }

        @Test
        @DisplayName("All-same values, absent: [1,1,1,1,1], target=2 → false")
        void testAllSameAbsent() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{1, 1, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=5 → true")
        void testNotRotatedFindLast() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 2, 3, 4, 5}, 5));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=1 → true")
        void testNotRotatedFindFirst() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 2, 3, 4, 5}, 1));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=6 → false (above max)")
        void testNotRotatedAboveMax() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{1, 2, 3, 4, 5}, 6));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=0 → false (below min)")
        void testNotRotatedBelowMin() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{1, 2, 3, 4, 5}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=0 → true")
        void testDistinctRotatedFindMin() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=7 → true (max just before pivot)")
        void testDistinctRotatedFindMax() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=3 → false (falls in the gap)")
        void testDistinctRotatedInGap() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=8 → false")
        void testRotatedAboveMax() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{5, 6, 7, 1, 2, 3}, 8));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=0 → false")
        void testRotatedBelowMin() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{5, 6, 7, 1, 2, 3}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,0,1,1,1], target=0 → true")
        void testDuplicatesHidePivotLeft() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 0, 1, 1, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,0,1], target=0 → true")
        void testDuplicatesHidePivotRight() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 1, 1, 0, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,1,0,1,1], target=0 → true")
        void testDuplicatesHidePivotCenter() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 1, 1, 1, 0, 1, 1}, 0));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [2,2,2,3,2,2,2], target=3 → true")
        void testSingleLargerAmongDuplicates() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{2, 2, 2, 3, 2, 2, 2}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,3,1,1,1], target=3 → true")
        void testSingleLargerNearStart() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 3, 1, 1, 1}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,2,1,1,1], target=2 → true")
        void testSingleLargerNearStartTwo() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{1, 2, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=1 → true")
        void testDuplicatesAtBothEnds() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{2, 2, 3, 4, 0, 1, 2}, 1));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=5 → false")
        void testDuplicatesAtBothEndsAbsent() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{2, 2, 3, 4, 0, 1, 2}, 5));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=-10000 → true")
        void testBoundaryValueMin() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{10000, -10000}, -10000));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=10000 → true")
        void testBoundaryValueMax() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{10000, -10000}, 10000));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-5 → true")
        void testNegativeValuesPresent() {
            assertEquals(true, instance.searchInRotatedParts(new int[]{-3, -2, -1, -5, -4}, -5));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-6 → false")
        void testNegativeValuesAbsent() {
            assertEquals(false, instance.searchInRotatedParts(new int[]{-3, -2, -1, -5, -4}, -6));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=0 → true")
        void testMaxBoundaryDistinctFindMin() {
            assertEquals(true, instance.searchInRotatedParts(rotatedDistinct(5000, 2500), 0));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=4999 → true")
        void testMaxBoundaryDistinctFindMax() {
            assertEquals(true, instance.searchInRotatedParts(rotatedDistinct(5000, 2500), 4999));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=5000 → false")
        void testMaxBoundaryDistinctAbsent() {
            assertEquals(false, instance.searchInRotatedParts(rotatedDistinct(5000, 2500), 5000));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=1 → true")
        void testMaxBoundaryAllSamePresent() {
            assertEquals(true, instance.searchInRotatedParts(uniform(5000, 1, -1, 0), 1));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=2 → false")
        void testMaxBoundaryAllSameAbsent() {
            assertEquals(false, instance.searchInRotatedParts(uniform(5000, 1, -1, 0), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 2 at index 2500, target=2 → true")
        void testMaxBoundarySingleOddValue() {
            assertEquals(true, instance.searchInRotatedParts(uniform(5000, 1, 2500, 2), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 0 at index 2500, target=0 → true")
        void testMaxBoundarySingleSmallerValue() {
            assertEquals(true, instance.searchInRotatedParts(uniform(5000, 1, 2500, 0), 0));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchAfterRemovingRotation()  — doubles the array, finds the rotation point, binary searches the sorted window
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchAfterRemovingRotation() — is target present in rotated sorted array with duplicates")
    class SearchAfterRemovingRotationTests {
        @Test
        @DisplayName("LeetCode Example 1: [2,5,6,0,0,1,2], target=0 → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,5,6,0,0,1,2], target=3 → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{2, 5, 6, 0, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Single element, present: [1], target=1 → true")
        void testSingleElementPresent() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Single element, absent: [1], target=0 → false")
        void testSingleElementAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=1 → true")
        void testTwoElementsFindSmaller() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{3, 1}, 1));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=3 → true")
        void testTwoElementsFindLarger() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{3, 1}, 3));
        }

        @Test
        @DisplayName("Two elements rotated: [3,1], target=2 → false")
        void testTwoElementsAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{3, 1}, 2));
        }

        @Test
        @DisplayName("All-same values, present: [1,1,1,1,1], target=1 → true")
        void testAllSamePresent() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 1, 1, 1, 1}, 1));
        }

        @Test
        @DisplayName("All-same values, absent: [1,1,1,1,1], target=2 → false")
        void testAllSameAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{1, 1, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=5 → true")
        void testNotRotatedFindLast() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 2, 3, 4, 5}, 5));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=1 → true")
        void testNotRotatedFindFirst() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 2, 3, 4, 5}, 1));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=6 → false (above max)")
        void testNotRotatedAboveMax() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{1, 2, 3, 4, 5}, 6));
        }

        @Test
        @DisplayName("Not rotated (pivot 0): [1,2,3,4,5], target=0 → false (below min)")
        void testNotRotatedBelowMin() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{1, 2, 3, 4, 5}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=0 → true")
        void testDistinctRotatedFindMin() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=7 → true (max just before pivot)")
        void testDistinctRotatedFindMax() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{4, 5, 6, 7, 0, 1, 2}, 7));
        }

        @Test
        @DisplayName("Distinct rotated: [4,5,6,7,0,1,2], target=3 → false (falls in the gap)")
        void testDistinctRotatedInGap() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=8 → false")
        void testRotatedAboveMax() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{5, 6, 7, 1, 2, 3}, 8));
        }

        @Test
        @DisplayName("Target outside range: [5,6,7,1,2,3], target=0 → false")
        void testRotatedBelowMin() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{5, 6, 7, 1, 2, 3}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,0,1,1,1], target=0 → true")
        void testDuplicatesHidePivotLeft() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 0, 1, 1, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,0,1], target=0 → true")
        void testDuplicatesHidePivotRight() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 1, 1, 0, 1}, 0));
        }

        @Test
        @DisplayName("Duplicates hide the pivot: [1,1,1,1,0,1,1], target=0 → true")
        void testDuplicatesHidePivotCenter() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 1, 1, 1, 0, 1, 1}, 0));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [2,2,2,3,2,2,2], target=3 → true")
        void testSingleLargerAmongDuplicates() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{2, 2, 2, 3, 2, 2, 2}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,3,1,1,1], target=3 → true")
        void testSingleLargerNearStart() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 3, 1, 1, 1}, 3));
        }

        @Test
        @DisplayName("Single distinct value among duplicates: [1,2,1,1,1], target=2 → true")
        void testSingleLargerNearStartTwo() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{1, 2, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=1 → true")
        void testDuplicatesAtBothEnds() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{2, 2, 3, 4, 0, 1, 2}, 1));
        }

        @Test
        @DisplayName("Duplicates at both ends of the rotation: [2,2,3,4,0,1,2], target=5 → false")
        void testDuplicatesAtBothEndsAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{2, 2, 3, 4, 0, 1, 2}, 5));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=-10000 → true")
        void testBoundaryValueMin() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{10000, -10000}, -10000));
        }

        @Test
        @DisplayName("Value constraint boundaries: [10000,-10000], target=10000 → true")
        void testBoundaryValueMax() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{10000, -10000}, 10000));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-5 → true")
        void testNegativeValuesPresent() {
            assertEquals(true, instance.searchAfterRemovingRotation(new int[]{-3, -2, -1, -5, -4}, -5));
        }

        @Test
        @DisplayName("Negative values: [-3,-2,-1,-5,-4], target=-6 → false")
        void testNegativeValuesAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(new int[]{-3, -2, -1, -5, -4}, -6));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=0 → true")
        void testMaxBoundaryDistinctFindMin() {
            assertEquals(true, instance.searchAfterRemovingRotation(rotatedDistinct(5000, 2500), 0));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=4999 → true")
        void testMaxBoundaryDistinctFindMax() {
            assertEquals(true, instance.searchAfterRemovingRotation(rotatedDistinct(5000, 2500), 4999));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 distinct rotated at 2500, target=5000 → false")
        void testMaxBoundaryDistinctAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(rotatedDistinct(5000, 2500), 5000));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=1 → true")
        void testMaxBoundaryAllSamePresent() {
            assertEquals(true, instance.searchAfterRemovingRotation(uniform(5000, 1, -1, 0), 1));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s, target=2 → false")
        void testMaxBoundaryAllSameAbsent() {
            assertEquals(false, instance.searchAfterRemovingRotation(uniform(5000, 1, -1, 0), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 2 at index 2500, target=2 → true")
        void testMaxBoundarySingleOddValue() {
            assertEquals(true, instance.searchAfterRemovingRotation(uniform(5000, 1, 2500, 2), 2));
        }

        @Test
        @DisplayName("Max constraint boundary: n=5000 all 1s except a single 0 at index 2500, target=0 → true")
        void testMaxBoundarySingleSmallerValue() {
            assertEquals(true, instance.searchAfterRemovingRotation(uniform(5000, 1, 2500, 0), 0));
        }
    }

    /** Builds [k, k+1, ..., n-1, 0, 1, ..., k-1]. */
    private int[] rotatedDistinct(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (i + k) % n;
        }
        return nums;
    }

    /** Builds an array of n copies of fill; if idx >= 0, nums[idx] is set to special. */
    private int[] uniform(int n, int fill, int idx, int special) {
        int[] nums = new int[n];
        java.util.Arrays.fill(nums, fill);
        if (idx >= 0) nums[idx] = special;
        return nums;
    }
}
