package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Search in Rotated Sorted Array Tests")
class SearchInaRotatedSortedArrayTest {

    private SearchInaRotatedSortedArray instance;

    @BeforeEach
    void setUp() {
        instance = new SearchInaRotatedSortedArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  searchAfterRemovingRotation()  — doubled array + binary search
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchAfterRemovingRotation() — doubled array + binary search")
    class RemovingTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,6,7,0,1,2], target=0 → 4 (target in rotated part)")
        void testCase1() {
            assertEquals(4, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,5,6,7,0,1,2], target=3 → -1 (absent, within range)")
        void testCase2() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1], target=0 → -1 (single element, absent)")
        void testCase3() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Scenario: [1], target=1 → 0 (single element, found)")
        void testCase4() {
            assertEquals(0, instance.searchAfterRemovingRotation(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=1 → 1 (two elements rotated, target is second)")
        void testCase5() {
            assertEquals(1, instance.searchAfterRemovingRotation(new int[]{3,1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=3 → 0 (two elements rotated, target is first)")
        void testCase6() {
            assertEquals(0, instance.searchAfterRemovingRotation(new int[]{3,1}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=3 → 1 (two elements not rotated)")
        void testCase7() {
            assertEquals(1, instance.searchAfterRemovingRotation(new int[]{1,3}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=2 → -1 (two elements, absent between)")
        void testCase8() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{1,3}, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=4 → 3 (not rotated, found)")
        void testCase9() {
            assertEquals(3, instance.searchAfterRemovingRotation(new int[]{1,2,3,4,5}, 4));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=6 → -1 (not rotated, above all)")
        void testCase10() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{1,2,3,4,5}, 6));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=4 → 0 (target at first index)")
        void testCase11() {
            assertEquals(0, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 4));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=2 → 6 (target at last index)")
        void testCase12() {
            assertEquals(6, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 2));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=7 → 3 (target is max, just before pivot)")
        void testCase13() {
            assertEquals(3, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 7));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=5 → 1 (target in left sorted part)")
        void testCase14() {
            assertEquals(1, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 5));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=1 → 5 (target in right sorted part)")
        void testCase15() {
            assertEquals(5, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=-1 → -1 (below all elements)")
        void testCase16() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, -1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=8 → -1 (above all elements)")
        void testCase17() {
            assertEquals(-1, instance.searchAfterRemovingRotation(new int[]{4,5,6,7,0,1,2}, 8));
        }

        @Test
        @DisplayName("Scenario: [5,6,7,0,1,2,3,4], target=3 → 6 (rotated near the middle, target in right part)")
        void testCase18() {
            assertEquals(6, instance.searchAfterRemovingRotation(new int[]{5,6,7,0,1,2,3,4}, 3));
        }

        @Test
        @DisplayName("Scenario: [2,3,4,5,6,7,0,1], target=0 → 6 (rotated near the end)")
        void testCase19() {
            assertEquals(6, instance.searchAfterRemovingRotation(new int[]{2,3,4,5,6,7,0,1}, 0));
        }

        @Test
        @DisplayName("Scenario: [7,0,1,2,3,4,5,6], target=7 → 0 (rotated near the start, target at index 0)")
        void testCase20() {
            assertEquals(0, instance.searchAfterRemovingRotation(new int[]{7,0,1,2,3,4,5,6}, 7));
        }

        @Test
        @DisplayName("Scenario: [-2,-1,5,-10,-8,-5,-3], target=-8 → 4 (negative values)")
        void testCase21() {
            assertEquals(4, instance.searchAfterRemovingRotation(new int[]{-2,-1,5,-10,-8,-5,-3}, -8));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000], target=-10000 → 1 (value boundaries)")
        void testCase22() {
            assertEquals(1, instance.searchAfterRemovingRotation(new int[]{10000,-10000}, -10000));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=0 → 2500 (max length, target 0 at rotation point)")
        void testMaxLength0() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2500, instance.searchAfterRemovingRotation(nums, 0));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=4999 → 2499 (max length, target max just before pivot)")
        void testMaxLength4999() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2499, instance.searchAfterRemovingRotation(nums, 4999));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=1234 → 3734 (max length, target in the right part)")
        void testMaxLength1234() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(3734, instance.searchAfterRemovingRotation(nums, 1234));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=-1 → -1 (max length, absent)")
        void testMaxLengthAbsent() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(-1, instance.searchAfterRemovingRotation(nums, -1));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchInRotatedParts()  — find pivot, then binary search each part
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchInRotatedParts() — find pivot, then binary search each part")
    class RotatedPartsTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,6,7,0,1,2], target=0 → 4 (target in rotated part)")
        void testCase1() {
            assertEquals(4, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,5,6,7,0,1,2], target=3 → -1 (absent, within range)")
        void testCase2() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1], target=0 → -1 (single element, absent)")
        void testCase3() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Scenario: [1], target=1 → 0 (single element, found)")
        void testCase4() {
            assertEquals(0, instance.searchInRotatedParts(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=1 → 1 (two elements rotated, target is second)")
        void testCase5() {
            assertEquals(1, instance.searchInRotatedParts(new int[]{3,1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=3 → 0 (two elements rotated, target is first)")
        void testCase6() {
            assertEquals(0, instance.searchInRotatedParts(new int[]{3,1}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=3 → 1 (two elements not rotated)")
        void testCase7() {
            assertEquals(1, instance.searchInRotatedParts(new int[]{1,3}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=2 → -1 (two elements, absent between)")
        void testCase8() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{1,3}, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=4 → 3 (not rotated, found)")
        void testCase9() {
            assertEquals(3, instance.searchInRotatedParts(new int[]{1,2,3,4,5}, 4));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=6 → -1 (not rotated, above all)")
        void testCase10() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{1,2,3,4,5}, 6));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=4 → 0 (target at first index)")
        void testCase11() {
            assertEquals(0, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 4));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=2 → 6 (target at last index)")
        void testCase12() {
            assertEquals(6, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 2));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=7 → 3 (target is max, just before pivot)")
        void testCase13() {
            assertEquals(3, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 7));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=5 → 1 (target in left sorted part)")
        void testCase14() {
            assertEquals(1, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 5));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=1 → 5 (target in right sorted part)")
        void testCase15() {
            assertEquals(5, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=-1 → -1 (below all elements)")
        void testCase16() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, -1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=8 → -1 (above all elements)")
        void testCase17() {
            assertEquals(-1, instance.searchInRotatedParts(new int[]{4,5,6,7,0,1,2}, 8));
        }

        @Test
        @DisplayName("Scenario: [5,6,7,0,1,2,3,4], target=3 → 6 (rotated near the middle, target in right part)")
        void testCase18() {
            assertEquals(6, instance.searchInRotatedParts(new int[]{5,6,7,0,1,2,3,4}, 3));
        }

        @Test
        @DisplayName("Scenario: [2,3,4,5,6,7,0,1], target=0 → 6 (rotated near the end)")
        void testCase19() {
            assertEquals(6, instance.searchInRotatedParts(new int[]{2,3,4,5,6,7,0,1}, 0));
        }

        @Test
        @DisplayName("Scenario: [7,0,1,2,3,4,5,6], target=7 → 0 (rotated near the start, target at index 0)")
        void testCase20() {
            assertEquals(0, instance.searchInRotatedParts(new int[]{7,0,1,2,3,4,5,6}, 7));
        }

        @Test
        @DisplayName("Scenario: [-2,-1,5,-10,-8,-5,-3], target=-8 → 4 (negative values)")
        void testCase21() {
            assertEquals(4, instance.searchInRotatedParts(new int[]{-2,-1,5,-10,-8,-5,-3}, -8));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000], target=-10000 → 1 (value boundaries)")
        void testCase22() {
            assertEquals(1, instance.searchInRotatedParts(new int[]{10000,-10000}, -10000));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=0 → 2500 (max length, target 0 at rotation point)")
        void testMaxLength0() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2500, instance.searchInRotatedParts(nums, 0));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=4999 → 2499 (max length, target max just before pivot)")
        void testMaxLength4999() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2499, instance.searchInRotatedParts(nums, 4999));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=1234 → 3734 (max length, target in the right part)")
        void testMaxLength1234() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(3734, instance.searchInRotatedParts(nums, 1234));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=-1 → -1 (max length, absent)")
        void testMaxLengthAbsent() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(-1, instance.searchInRotatedParts(nums, -1));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  searchBinarySolution()  — single-pass modified binary search
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("searchBinarySolution() — single-pass modified binary search")
    class BinarySolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [4,5,6,7,0,1,2], target=0 → 4 (target in rotated part)")
        void testCase1() {
            assertEquals(4, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 0));
        }

        @Test
        @DisplayName("LeetCode Example 2: [4,5,6,7,0,1,2], target=3 → -1 (absent, within range)")
        void testCase2() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1], target=0 → -1 (single element, absent)")
        void testCase3() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{1}, 0));
        }

        @Test
        @DisplayName("Scenario: [1], target=1 → 0 (single element, found)")
        void testCase4() {
            assertEquals(0, instance.searchBinarySolution(new int[]{1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=1 → 1 (two elements rotated, target is second)")
        void testCase5() {
            assertEquals(1, instance.searchBinarySolution(new int[]{3,1}, 1));
        }

        @Test
        @DisplayName("Scenario: [3,1], target=3 → 0 (two elements rotated, target is first)")
        void testCase6() {
            assertEquals(0, instance.searchBinarySolution(new int[]{3,1}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=3 → 1 (two elements not rotated)")
        void testCase7() {
            assertEquals(1, instance.searchBinarySolution(new int[]{1,3}, 3));
        }

        @Test
        @DisplayName("Scenario: [1,3], target=2 → -1 (two elements, absent between)")
        void testCase8() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{1,3}, 2));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=4 → 3 (not rotated, found)")
        void testCase9() {
            assertEquals(3, instance.searchBinarySolution(new int[]{1,2,3,4,5}, 4));
        }

        @Test
        @DisplayName("Scenario: [1,2,3,4,5], target=6 → -1 (not rotated, above all)")
        void testCase10() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{1,2,3,4,5}, 6));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=4 → 0 (target at first index)")
        void testCase11() {
            assertEquals(0, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 4));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=2 → 6 (target at last index)")
        void testCase12() {
            assertEquals(6, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 2));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=7 → 3 (target is max, just before pivot)")
        void testCase13() {
            assertEquals(3, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 7));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=5 → 1 (target in left sorted part)")
        void testCase14() {
            assertEquals(1, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 5));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=1 → 5 (target in right sorted part)")
        void testCase15() {
            assertEquals(5, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=-1 → -1 (below all elements)")
        void testCase16() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, -1));
        }

        @Test
        @DisplayName("Scenario: [4,5,6,7,0,1,2], target=8 → -1 (above all elements)")
        void testCase17() {
            assertEquals(-1, instance.searchBinarySolution(new int[]{4,5,6,7,0,1,2}, 8));
        }

        @Test
        @DisplayName("Scenario: [5,6,7,0,1,2,3,4], target=3 → 6 (rotated near the middle, target in right part)")
        void testCase18() {
            assertEquals(6, instance.searchBinarySolution(new int[]{5,6,7,0,1,2,3,4}, 3));
        }

        @Test
        @DisplayName("Scenario: [2,3,4,5,6,7,0,1], target=0 → 6 (rotated near the end)")
        void testCase19() {
            assertEquals(6, instance.searchBinarySolution(new int[]{2,3,4,5,6,7,0,1}, 0));
        }

        @Test
        @DisplayName("Scenario: [7,0,1,2,3,4,5,6], target=7 → 0 (rotated near the start, target at index 0)")
        void testCase20() {
            assertEquals(0, instance.searchBinarySolution(new int[]{7,0,1,2,3,4,5,6}, 7));
        }

        @Test
        @DisplayName("Scenario: [-2,-1,5,-10,-8,-5,-3], target=-8 → 4 (negative values)")
        void testCase21() {
            assertEquals(4, instance.searchBinarySolution(new int[]{-2,-1,5,-10,-8,-5,-3}, -8));
        }

        @Test
        @DisplayName("Scenario: [10000,-10000], target=-10000 → 1 (value boundaries)")
        void testCase22() {
            assertEquals(1, instance.searchBinarySolution(new int[]{10000,-10000}, -10000));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=0 → 2500 (max length, target 0 at rotation point)")
        void testMaxLength0() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2500, instance.searchBinarySolution(nums, 0));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=4999 → 2499 (max length, target max just before pivot)")
        void testMaxLength4999() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(2499, instance.searchBinarySolution(nums, 4999));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=1234 → 3734 (max length, target in the right part)")
        void testMaxLength1234() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(3734, instance.searchBinarySolution(nums, 1234));
        }

        @Test
        @DisplayName("Scenario: 5000 elements rotated by 2500, target=-1 → -1 (max length, absent)")
        void testMaxLengthAbsent() {
            int[] nums = new int[5000];
            for (int i = 0; i < nums.length; i++) nums[i] = (i + 2500) % 5000;
            assertEquals(-1, instance.searchBinarySolution(nums, -1));
        }
    }
}
