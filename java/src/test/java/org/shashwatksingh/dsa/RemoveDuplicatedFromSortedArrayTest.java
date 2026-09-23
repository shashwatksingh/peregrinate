package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import java.util.Arrays;

@DisplayName("RemoveDuplicatedFromSortedArray Tests")
class RemoveDuplicatedFromSortedArrayTest {

    private RemoveDuplicatedFromSortedArray instance;

    @BeforeEach
    void setUp() {
        instance = new RemoveDuplicatedFromSortedArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  removeDuplicates()  — in-place dedupe of sorted array, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("removeDuplicates() — in-place dedupe of sorted array")
    class RemoveDuplicatesTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,1,2] → k=2, nums=[1,2,_]")
        void testLeetCodeExample1() {
            int[] nums = {1, 1, 2};
            int k = instance.removeDuplicates(nums);
            assertEquals(2, k);
            assertArrayEquals(new int[]{1, 2}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("LeetCode Example 2: [0,0,1,1,1,2,2,3,3,4] → k=5, nums=[0,1,2,3,4,_,_,_,_,_]")
        void testLeetCodeExample2() {
            int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
            int k = instance.removeDuplicates(nums);
            assertEquals(5, k);
            assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("Single-element input: [5] → k=1, nums=[5]")
        void testSingleElement() {
            int[] nums = {5};
            int k = instance.removeDuplicates(nums);
            assertEquals(1, k);
            assertArrayEquals(new int[]{5}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("All-same values: [3,3,3,3] → k=1, nums=[3,_,_,_]")
        void testAllSameValues() {
            int[] nums = {3, 3, 3, 3};
            int k = instance.removeDuplicates(nums);
            assertEquals(1, k);
            assertArrayEquals(new int[]{3}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("Strictly increasing sequence with no duplicates: [1,2,3,4,5] → k=5, nums unchanged")
        void testStrictlyIncreasingNoDuplicates() {
            int[] nums = {1, 2, 3, 4, 5};
            int k = instance.removeDuplicates(nums);
            assertEquals(5, k);
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("Duplicate run resets mid-array on new value: [1,1,2,2,3] → k=3, nums=[1,2,3,_,_]")
        void testDuplicateRunResetsMidArray() {
            int[] nums = {1, 1, 2, 2, 3};
            int k = instance.removeDuplicates(nums);
            assertEquals(3, k);
            assertArrayEquals(new int[]{1, 2, 3}, Arrays.copyOf(nums, k));
        }

        @Test
        @DisplayName("Negative and positive values: [-100,-100,-1,0,0,100] → k=4, nums=[-100,-1,0,100,_,_]")
        void testNegativeAndPositiveValues() {
            int[] nums = {-100, -100, -1, 0, 0, 100};
            int k = instance.removeDuplicates(nums);
            assertEquals(4, k);
            assertArrayEquals(new int[]{-100, -1, 0, 100}, Arrays.copyOf(nums, k));
        }
    }
}
