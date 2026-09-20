package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Subarray Sum Equals K Tests")
public class SubarraySumEqualsKTest {

    private SubarraySumEqualsK ss;

    @BeforeEach
    void setUp() {
        ss = new SubarraySumEqualsK();
    }

    // ─── shared test logic so every solution is held to the same bar ───────
    interface Solver {
        int solve(int[] nums, int k);
    }

    private void runAll(Solver solver) {
        // LeetCode examples
        assertEquals(2, solver.solve(new int[]{1, 1, 1}, 2),           "Example 1");
        assertEquals(2, solver.solve(new int[]{1, 2, 3}, 3),           "Example 2");

        // Single element equals k
        assertEquals(1, solver.solve(new int[]{5}, 5),                 "Single element = k");

        // Single element not equal to k
        assertEquals(0, solver.solve(new int[]{5}, 3),                 "Single element ≠ k");

        // Subarray starting at index 0
        assertEquals(2, solver.solve(new int[]{3, 1, 2}, 3),           "Subarray at start");

        // Subarray ending at last index
        assertEquals(1, solver.solve(new int[]{1, 2, 3}, 6),           "Subarray = whole array");

        // Negative numbers
        assertEquals(6, solver.solve(new int[]{1, -1, 1, -1, 1}, 0),  "Negative numbers, k=0");

        // Negative k
        assertEquals(1, solver.solve(new int[]{-3, 1, 2}, -3),         "Negative k");

        // All zeros, k = 0 → every subarray qualifies: n*(n+1)/2 = 6
        assertEquals(6, solver.solve(new int[]{0, 0, 0}, 0),           "All zeros");

        // No matching subarray
        assertEquals(0, solver.solve(new int[]{1, 2, 3}, 10),          "No match");

        // k larger than any element but equal to full sum
        assertEquals(1, solver.solve(new int[]{2, 4, 6}, 12),          "Full array sum");

        // Multiple overlapping subarrays
        assertEquals(4, solver.solve(new int[]{1, 2, 1, 2, 1}, 3),    "Multiple overlapping");
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solution() — HashMap O(n)
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution() — HashMap O(n)")
    class Solution0Tests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll((nums, k) -> ss.solution(nums, k));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solution2() — prefix sum array O(n²)
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution2() — prefix sum array O(n²)")
    class Solution2Tests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll((nums, k) -> ss.solution2(nums, k));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solution1() — brute force O(n³)
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution1() — brute force O(n³)")
    class Solution1Tests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll((nums, k) -> ss.solution1(nums, k));
        }
    }
}
