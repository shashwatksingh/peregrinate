package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("PermutationsOfArray Tests")
class PermutationsOfArrayTest {

    private PermutationsOfArray instance;

    @BeforeEach
    void setUp() {
        instance = new PermutationsOfArray();
    }

    // ═══════════════════════════════════════════════════════════
    //  permute()  — backtracking with used-check, O(n!) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("permute() — backtracking with used-check")
    class PermuteTests {

        @Test
        @DisplayName("LeetCode Example 1: [1,2,3] → [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]")
        void testLeetCodeExample1() {
            List<List<Integer>> expected = List.of(
                    List.of(1, 2, 3),
                    List.of(1, 3, 2),
                    List.of(2, 1, 3),
                    List.of(2, 3, 1),
                    List.of(3, 1, 2),
                    List.of(3, 2, 1)
            );
            assertEquals(expected, instance.permute(new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [0,1] → [[0,1],[1,0]]")
        void testLeetCodeExample2() {
            List<List<Integer>> expected = List.of(
                    List.of(0, 1),
                    List.of(1, 0)
            );
            assertEquals(expected, instance.permute(new int[]{0, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 3 / minimum-size input: [1] → [[1]]")
        void testMinimumSizeInput() {
            List<List<Integer>> expected = List.of(List.of(1));
            assertEquals(expected, instance.permute(new int[]{1}));
        }

        @Test
        @DisplayName("Descending input: [3,2,1] → [[3,2,1],[3,1,2],[2,3,1],[2,1,3],[1,3,2],[1,2,3]]")
        void testDescendingInput() {
            List<List<Integer>> expected = List.of(
                    List.of(3, 2, 1),
                    List.of(3, 1, 2),
                    List.of(2, 3, 1),
                    List.of(2, 1, 3),
                    List.of(1, 3, 2),
                    List.of(1, 2, 3)
            );
            assertEquals(expected, instance.permute(new int[]{3, 2, 1}));
        }

        @Test
        @DisplayName("Node values at min/max constraint boundaries: [-10,10] → [[-10,10],[10,-10]]")
        void testBoundaryValues() {
            List<List<Integer>> expected = List.of(
                    List.of(-10, 10),
                    List.of(10, -10)
            );
            assertEquals(expected, instance.permute(new int[]{-10, 10}));
        }

        @Test
        @DisplayName("Max constraint boundary: 6 distinct elements → 6! = 720 permutations")
        void testMaxLengthProducesFactorialCount() {
            assertEquals(720, instance.permute(new int[]{1, 2, 3, 4, 5, 6}).size());
        }
    }
}
