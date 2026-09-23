package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Of Array Excepts Self Tests")
class ProductOfArrayExceptsSelfTest {

    private ProductOfArrayExceptsSelf productOfArrayExceptsSelf;

    @BeforeEach
    void setUp() {
        productOfArrayExceptsSelf = new ProductOfArrayExceptsSelf();
    }

    // ═══════════════════════════════════════════════════════════
    //  solution1()  — left + right prefix arrays, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution1() — left + right prefix arrays O(n) time, O(n) space")
    class Solution1Tests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4] → [24,12,8,6]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{24, 12, 8, 6},
                    productOfArrayExceptsSelf.solution1(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [-1,1,0,-3,3] → [0,0,9,0,0]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 0, 9, 0, 0},
                    productOfArrayExceptsSelf.solution1(new int[]{-1, 1, 0, -3, 3}));
        }

        @Test
        @DisplayName("Minimum size: [3,4] → [4,3]")
        void testMinimumSize() {
            assertArrayEquals(new int[]{4, 3},
                    productOfArrayExceptsSelf.solution1(new int[]{3, 4}));
        }

        @Test
        @DisplayName("All same values: [2,2,2,2] → [8,8,8,8]")
        void testAllSameValues() {
            assertArrayEquals(new int[]{8, 8, 8, 8},
                    productOfArrayExceptsSelf.solution1(new int[]{2, 2, 2, 2}));
        }

        @Test
        @DisplayName("Contains one zero: [1,0,3,4] → [0,12,0,0]")
        void testContainsOneZero() {
            assertArrayEquals(new int[]{0, 12, 0, 0},
                    productOfArrayExceptsSelf.solution1(new int[]{1, 0, 3, 4}));
        }

        @Test
        @DisplayName("Contains two zeros: [0,0,3,4] → [0,0,0,0]")
        void testContainsTwoZeros() {
            assertArrayEquals(new int[]{0, 0, 0, 0},
                    productOfArrayExceptsSelf.solution1(new int[]{0, 0, 3, 4}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → [120,60,40,30,24]")
        void testStrictlyIncreasing() {
            assertArrayEquals(new int[]{120, 60, 40, 30, 24},
                    productOfArrayExceptsSelf.solution1(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("All negatives: [-1,-2,-3,-4] → [-24,-12,-8,-6]")
        void testAllNegatives() {
            assertArrayEquals(new int[]{-24, -12, -8, -6},
                    productOfArrayExceptsSelf.solution1(new int[]{-1, -2, -3, -4}));
        }

        @Test
        @DisplayName("Mixed sign no zeros: [-1,2,-3,4] → [-24,12,-8,6]")
        void testMixedSignNoZeros() {
            assertArrayEquals(new int[]{-24, 12, -8, 6},
                    productOfArrayExceptsSelf.solution1(new int[]{-1, 2, -3, 4}));
        }

        @Test
        @DisplayName("Constraint boundary: [30,-30,30,-30] → [27000,-27000,27000,-27000]")
        void testConstraintBoundary() {
            assertArrayEquals(new int[]{27000,-27000,27000,-27000},
                    productOfArrayExceptsSelf.solution1(new int[]{30, -30, 30, -30}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solution2()  — reuse result array for right product, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution2() — reuse result array for right product O(n) time, O(n) space")
    class Solution2Tests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4] → [24,12,8,6]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{24, 12, 8, 6},
                    productOfArrayExceptsSelf.solution2(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [-1,1,0,-3,3] → [0,0,9,0,0]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 0, 9, 0, 0},
                    productOfArrayExceptsSelf.solution2(new int[]{-1, 1, 0, -3, 3}));
        }

        @Test
        @DisplayName("Minimum size: [3,4] → [4,3]")
        void testMinimumSize() {
            assertArrayEquals(new int[]{4, 3},
                    productOfArrayExceptsSelf.solution2(new int[]{3, 4}));
        }

        @Test
        @DisplayName("All same values: [2,2,2,2] → [8,8,8,8]")
        void testAllSameValues() {
            assertArrayEquals(new int[]{8, 8, 8, 8},
                    productOfArrayExceptsSelf.solution2(new int[]{2, 2, 2, 2}));
        }

        @Test
        @DisplayName("Contains one zero: [1,0,3,4] → [0,12,0,0]")
        void testContainsOneZero() {
            assertArrayEquals(new int[]{0, 12, 0, 0},
                    productOfArrayExceptsSelf.solution2(new int[]{1, 0, 3, 4}));
        }

        @Test
        @DisplayName("Contains two zeros: [0,0,3,4] → [0,0,0,0]")
        void testContainsTwoZeros() {
            assertArrayEquals(new int[]{0, 0, 0, 0},
                    productOfArrayExceptsSelf.solution2(new int[]{0, 0, 3, 4}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → [120,60,40,30,24]")
        void testStrictlyIncreasing() {
            assertArrayEquals(new int[]{120, 60, 40, 30, 24},
                    productOfArrayExceptsSelf.solution2(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("All negatives: [-1,-2,-3,-4] → [-24,-12,-8,-6]")
        void testAllNegatives() {
            assertArrayEquals(new int[]{-24, -12, -8, -6},
                    productOfArrayExceptsSelf.solution2(new int[]{-1, -2, -3, -4}));
        }

        @Test
        @DisplayName("Mixed sign no zeros: [-1,2,-3,4] → [-24,12,-8,6]")
        void testMixedSignNoZeros() {
            assertArrayEquals(new int[]{-24, 12, -8, 6},
                    productOfArrayExceptsSelf.solution2(new int[]{-1, 2, -3, 4}));
        }

        @Test
        @DisplayName("Constraint boundary: [30,-30,30,-30] → [27000,-27000,27000,-27000]")
        void testSolution2ConstraintBoundary() {
            assertArrayEquals(new int[]{27000,-27000,27000,-27000},
                    productOfArrayExceptsSelf.solution2(new int[]{30, -30, 30, -30}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solution3()  — fully in-place running product, O(n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution3() — fully in-place running product O(n) time, O(1) extra space")
    class Solution3Tests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3,4] → [24,12,8,6]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{24, 12, 8, 6},
                    productOfArrayExceptsSelf.solution3(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [-1,1,0,-3,3] → [0,0,9,0,0]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 0, 9, 0, 0},
                    productOfArrayExceptsSelf.solution3(new int[]{-1, 1, 0, -3, 3}));
        }

        @Test
        @DisplayName("Minimum size: [3,4] → [4,3]")
        void testMinimumSize() {
            assertArrayEquals(new int[]{4, 3},
                    productOfArrayExceptsSelf.solution3(new int[]{3, 4}));
        }

        @Test
        @DisplayName("All same values: [2,2,2,2] → [8,8,8,8]")
        void testAllSameValues() {
            assertArrayEquals(new int[]{8, 8, 8, 8},
                    productOfArrayExceptsSelf.solution3(new int[]{2, 2, 2, 2}));
        }

        @Test
        @DisplayName("Contains one zero: [1,0,3,4] → [0,12,0,0]")
        void testContainsOneZero() {
            assertArrayEquals(new int[]{0, 12, 0, 0},
                    productOfArrayExceptsSelf.solution3(new int[]{1, 0, 3, 4}));
        }

        @Test
        @DisplayName("Contains two zeros: [0,0,3,4] → [0,0,0,0]")
        void testContainsTwoZeros() {
            assertArrayEquals(new int[]{0, 0, 0, 0},
                    productOfArrayExceptsSelf.solution3(new int[]{0, 0, 3, 4}));
        }

        @Test
        @DisplayName("Strictly increasing: [1,2,3,4,5] → [120,60,40,30,24]")
        void testStrictlyIncreasing() {
            assertArrayEquals(new int[]{120, 60, 40, 30, 24},
                    productOfArrayExceptsSelf.solution3(new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("All negatives: [-1,-2,-3,-4] → [-24,-12,-8,-6]")
        void testAllNegatives() {
            assertArrayEquals(new int[]{-24, -12, -8, -6},
                    productOfArrayExceptsSelf.solution3(new int[]{-1, -2, -3, -4}));
        }

        @Test
        @DisplayName("Mixed sign no zeros: [-1,2,-3,4] → [-24,12,-8,6]")
        void testMixedSignNoZeros() {
            assertArrayEquals(new int[]{-24, 12, -8, 6},
                    productOfArrayExceptsSelf.solution3(new int[]{-1, 2, -3, 4}));
        }

        @Test
        @DisplayName("Constraint boundary: [30,-30,30,-30] → [27000,-27000,27000,-27000]")
        void testConstraintBoundary() {
            assertArrayEquals(new int[]{27000,-27000,27000,-27000},
                    productOfArrayExceptsSelf.solution3(new int[]{30, -30, 30, -30}));
        }
    }
}
