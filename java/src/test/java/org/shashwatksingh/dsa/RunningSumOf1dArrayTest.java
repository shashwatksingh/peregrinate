package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Running Sum of 1D Array Tests")
public class RunningSumOf1dArrayTest {

    private RunningSumOf1dArray rs;

    @BeforeEach
    void setUp() {
        rs = new RunningSumOf1dArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  solution()  — in-place, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution() — in-place O(1) space")
    class Solution1Tests {

        @Test
        @DisplayName("Example 1: [1,2,3,4] → [1,3,6,10]")
        void testExample1() {
            assertArrayEquals(new int[]{1, 3, 6, 10}, rs.solution(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Example 2: [1,1,1,1,1] → [1,2,3,4,5]")
        void testExample2() {
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, rs.solution(new int[]{1, 1, 1, 1, 1}));
        }

        @Test
        @DisplayName("Example 3: [3,1,2,10,1] → [3,4,6,16,17]")
        void testExample3() {
            assertArrayEquals(new int[]{3, 4, 6, 16, 17}, rs.solution(new int[]{3, 1, 2, 10, 1}));
        }

        @Test
        @DisplayName("Single element: [5] → [5]")
        void testSingleElement() {
            assertArrayEquals(new int[]{5}, rs.solution(new int[]{5}));
        }

        @Test
        @DisplayName("All zeros: [0,0,0] → [0,0,0]")
        void testAllZeros() {
            assertArrayEquals(new int[]{0, 0, 0}, rs.solution(new int[]{0, 0, 0}));
        }

        @Test
        @DisplayName("Negative numbers: [-1,-2,-3] → [-1,-3,-6]")
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-1, -3, -6}, rs.solution(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("Mixed positive and negative: [-3,2,-1,4] → [-3,-1,-2,2]")
        void testMixedNumbers() {
            assertArrayEquals(new int[]{-3, -1, -2, 2}, rs.solution(new int[]{-3, 2, -1, 4}));
        }

        @Test
        @DisplayName("Constraint boundary values: [1000000, -1000000, 1000000] → [1000000, 0, 1000000]")
        void testBoundaryValues() {
            assertArrayEquals(new int[]{1_000_000, 0, 1_000_000},
                    rs.solution(new int[]{1_000_000, -1_000_000, 1_000_000}));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  solution2()  — extra array, O(n) space
    //  NOTE: solution2 has a bug — at i=0 it accesses nums[-1].
    //        The tests below document the correct expected behaviour
    //        and will fail until the bug is fixed.
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution2() — extra-space O(n) space")
    class Solution2Tests {

        @Test
        @DisplayName("Example 1: [1,2,3,4] → [1,3,6,10]")
        void testExample1() {
            assertArrayEquals(new int[]{1, 3, 6, 10}, rs.solution2(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Example 2: [1,1,1,1,1] → [1,2,3,4,5]")
        void testExample2() {
            assertArrayEquals(new int[]{1, 2, 3, 4, 5}, rs.solution2(new int[]{1, 1, 1, 1, 1}));
        }

        @Test
        @DisplayName("Example 3: [3,1,2,10,1] → [3,4,6,16,17]")
        void testExample3() {
            assertArrayEquals(new int[]{3, 4, 6, 16, 17}, rs.solution2(new int[]{3, 1, 2, 10, 1}));
        }

        @Test
        @DisplayName("Single element: [5] → [5]")
        void testSingleElement() {
            assertArrayEquals(new int[]{5}, rs.solution2(new int[]{5}));
        }

        @Test
        @DisplayName("Negative numbers: [-1,-2,-3] → [-1,-3,-6]")
        void testNegativeNumbers() {
            assertArrayEquals(new int[]{-1, -3, -6}, rs.solution2(new int[]{-1, -2, -3}));
        }
    }
}
