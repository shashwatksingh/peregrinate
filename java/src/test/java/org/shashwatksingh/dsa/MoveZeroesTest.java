package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("MoveZeroes Tests")
class MoveZeroesTest {

    private MoveZeroes instance;

    @BeforeEach
    void setUp() {
        instance = new MoveZeroes();
    }

    // ═══════════════════════════════════════════════════════════
    //  twoPassSolution()  — in-place move zeroes to end, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoPassSolution() — compact non-zeroes then fill zeroes")
    class TwoPassSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [0,1,0,3,12] → [1,3,12,0,0]")
        void testLeetCodeExample1() {
            int[] nums = {0, 1, 0, 3, 12};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
        }

        @Test
        @DisplayName("LeetCode Example 2: [0] → [0]")
        void testLeetCodeExample2() {
            int[] nums = {0};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{0}, nums);
        }

        @Test
        @DisplayName("Single non-zero element: [5] → [5]")
        void testSingleNonZeroElement() {
            int[] nums = {5};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{5}, nums);
        }

        @Test
        @DisplayName("All zeroes: [0,0,0,0] → [0,0,0,0]")
        void testAllZeroes() {
            int[] nums = {0, 0, 0, 0};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{0, 0, 0, 0}, nums);
        }

        @Test
        @DisplayName("No zeroes present: [1,2,3,4] → [1,2,3,4] (unchanged)")
        void testNoZeroesPresent() {
            int[] nums = {1, 2, 3, 4};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 3, 4}, nums);
        }

        @Test
        @DisplayName("Leading zeroes only: [0,0,1,2] → [1,2,0,0]")
        void testLeadingZeroesOnly() {
            int[] nums = {0, 0, 1, 2};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 0, 0}, nums);
        }

        @Test
        @DisplayName("Trailing zeroes already in place: [1,2,0,0] → [1,2,0,0] (no-op)")
        void testTrailingZeroesAlreadyInPlace() {
            int[] nums = {1, 2, 0, 0};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 0, 0}, nums);
        }

        @Test
        @DisplayName("Alternating zero/non-zero: [0,1,0,2,0,3] → [1,2,3,0,0,0]")
        void testAlternatingZeroesAndValues() {
            int[] nums = {0, 1, 0, 2, 0, 3};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, nums);
        }

        @Test
        @DisplayName("Negative values mixed with zero: [-1,0,-2,0,-3] → [-1,-2,-3,0,0]")
        void testNegativeValuesWithZero() {
            int[] nums = {-1, 0, -2, 0, -3};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{-1, -2, -3, 0, 0}, nums);
        }

        @Test
        @DisplayName("Max constraint boundary values with zero: [Integer.MAX_VALUE,0,Integer.MIN_VALUE] → [MAX,MIN,0]")
        void testIntegerBoundaryValues() {
            int[] nums = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};
            instance.twoPassSolution(nums);
            assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, nums);
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  onePassSolution()  — in-place swap-based move zeroes to end, O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("onePassSolution() — swap non-zero into left pointer while scanning once")
    class OnePassSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [0,1,0,3,12] → [1,3,12,0,0]")
        void testLeetCodeExample1() {
            int[] nums = {0, 1, 0, 3, 12};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
        }

        @Test
        @DisplayName("LeetCode Example 2: [0] → [0]")
        void testLeetCodeExample2() {
            int[] nums = {0};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{0}, nums);
        }

        @Test
        @DisplayName("Single non-zero element: [5] → [5]")
        void testSingleNonZeroElement() {
            int[] nums = {5};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{5}, nums);
        }

        @Test
        @DisplayName("All zeroes: [0,0,0,0] → [0,0,0,0]")
        void testAllZeroes() {
            int[] nums = {0, 0, 0, 0};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{0, 0, 0, 0}, nums);
        }

        @Test
        @DisplayName("No zeroes present: [1,2,3,4] → [1,2,3,4] (unchanged, left==right every step)")
        void testNoZeroesPresent() {
            int[] nums = {1, 2, 3, 4};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 3, 4}, nums);
        }

        @Test
        @DisplayName("Leading zeroes only: [0,0,1,2] → [1,2,0,0]")
        void testLeadingZeroesOnly() {
            int[] nums = {0, 0, 1, 2};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 0, 0}, nums);
        }

        @Test
        @DisplayName("Trailing zeroes already in place: [1,2,0,0] → [1,2,0,0] (no-op)")
        void testTrailingZeroesAlreadyInPlace() {
            int[] nums = {1, 2, 0, 0};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 0, 0}, nums);
        }

        @Test
        @DisplayName("Alternating zero/non-zero: [0,1,0,2,0,3] → [1,2,3,0,0,0]")
        void testAlternatingZeroesAndValues() {
            int[] nums = {0, 1, 0, 2, 0, 3};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 2, 3, 0, 0, 0}, nums);
        }

        @Test
        @DisplayName("Negative values mixed with zero: [-1,0,-2,0,-3] → [-1,-2,-3,0,0]")
        void testNegativeValuesWithZero() {
            int[] nums = {-1, 0, -2, 0, -3};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{-1, -2, -3, 0, 0}, nums);
        }

        @Test
        @DisplayName("Max constraint boundary values with zero: [Integer.MAX_VALUE,0,Integer.MIN_VALUE] → [MAX,MIN,0]")
        void testIntegerBoundaryValues() {
            int[] nums = {Integer.MAX_VALUE, 0, Integer.MIN_VALUE};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0}, nums);
        }

        @Test
        @DisplayName("Single zero surrounded by identical non-zero swap targets: [1,0,1] → [1,1,0] (self-swap at left==right)")
        void testSelfSwapWhenLeftEqualsRight() {
            int[] nums = {1, 0, 1};
            instance.onePassSolution(nums);
            assertArrayEquals(new int[]{1, 1, 0}, nums);
        }
    }
}
