package org.shashwatksingh.dsa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Last Stone Weight Tests")
public class LastStoneWeightTest {

    private final LastStoneWeight lastStoneWeight = new LastStoneWeight();

    // ─── shared test logic so every solution is held to the same bar ───────
    interface Solver {
        int solve(int[] stones);
    }

    private void runAll(Solver solver) {
        // LeetCode examples
        assertEquals(1, solver.solve(new int[]{2, 7, 4, 1, 8, 1}), "Example 1");
        assertEquals(1, solver.solve(new int[]{1}),                "Example 2 - single stone");

        // Two equal stones fully cancel out
        assertEquals(0, solver.solve(new int[]{1, 1}),             "Two equal stones");
        assertEquals(0, solver.solve(new int[]{3, 3}),             "Two equal stones, larger weight");

        // Single stone, no smashing needed
        assertEquals(10, solver.solve(new int[]{10}),              "Single stone");

        // Odd stone survives after pairwise cancellation
        assertEquals(1, solver.solve(new int[]{2, 2, 1}),          "Pair cancels, one remains");

        // Non-trivial 3-stone case
        assertEquals(0, solver.solve(new int[]{1, 3, 2}),          "Three stones fully cancel");

        // Larger input requiring multiple rounds
        assertEquals(9, solver.solve(new int[]{31, 26, 33, 21, 40}), "Multiple rounds");

        // All stones equal, even count -> fully cancels
        assertEquals(0, solver.solve(new int[]{5, 5, 5, 5}),       "All equal, even count");

        // All stones equal, odd count -> one remains
        assertEquals(5, solver.solve(new int[]{5, 5, 5}),          "All equal, odd count");

        // Non-trivial 4-stone case, fully cancels
        assertEquals(0, solver.solve(new int[]{9, 3, 2, 4}),       "Four stones fully cancel");
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solution() — max heap
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solution() — max heap")
    class SolutionTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(lastStoneWeight::solution);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionSortII() — sort and preserve order
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionSortII() — sort and preserve order")
    class SolutionSortIITests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(lastStoneWeight::solutionSortII);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionSortI() — sort and destroy order
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionSortI() — sort and destroy order")
    class SolutionSortITests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(lastStoneWeight::solutionSortI);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionBrute() — brute force array simulation
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionBrute() — brute force array simulation")
    class SolutionBruteTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(lastStoneWeight::solutionBrute);
        }
    }
}
