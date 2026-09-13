package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Unique 3-Digit Even Number Tests")
public class Unique3DigitNumberTest {

    private Unique3DigitNumber u3d;

    @BeforeEach
    void setUp() {
        u3d = new Unique3DigitNumber();
    }

    // ── Shared test logic so both solutions are held to the same bar ──────────

    /**
     * Functional interface for a solver that counts distinct 3-digit even numbers.
     */
    interface Solver {
        int solve(int[] digits);
    }

    private void runAll(Solver solver) {

        // ── LeetCode provided examples ────────────────────────────────────────

        assertEquals(12, solver.solve(new int[]{1, 2, 3, 4}),
                "Example 1: [1,2,3,4] → 12 distinct 3-digit even numbers");

        assertEquals(2, solver.solve(new int[]{0, 2, 2}),
                "Example 2: [0,2,2] → 2 (202 and 220)");

        assertEquals(1, solver.solve(new int[]{6, 6, 6}),
                "Example 3: [6,6,6] → 1 (only 666)");

        assertEquals(0, solver.solve(new int[]{1, 3, 5}),
                "Example 4: [1,3,5] → 0 (no even digit for units place)");

        // ── Edge cases: no valid number can be formed ─────────────────────────

        assertEquals(0, solver.solve(new int[]{0, 0, 0}),
                "All zeros → 0 (leading zeros not allowed, so no valid hundreds digit)");

        assertEquals(2, solver.solve(new int[]{0, 1, 3}),
                "[0,1,3] → 2 (130 and 310; 0 is even so it fills the units place)");

        assertEquals(0, solver.solve(new int[]{1, 1, 3, 3, 5}),
                "Only odd digits (with repetition) → 0");

        // ── Single even digit available ───────────────────────────────────────

        assertEquals(2, solver.solve(new int[]{1, 2, 3}),
                "[1,2,3] → 2 (132 and 312; the only even digit is 2)");

        // ── Zero mixed in with other digits ───────────────────────────────────

        assertEquals(3, solver.solve(new int[]{0, 1, 2}),
                "[0,1,2] → 3 (102, 120, 210)");

        assertEquals(1, solver.solve(new int[]{0, 0, 2}),
                "[0,0,2] → 1 (only 200; leading zero disallowed)");

        // ── Repeated even digits ──────────────────────────────────────────────

        assertEquals(1, solver.solve(new int[]{2, 2, 2}),
                "[2,2,2] → 1 (only 222)");

        assertEquals(3, solver.solve(new int[]{4, 4, 2}),
                "[4,4,2] → 3 (244, 424, 442)");

        // ── All same even ─────────────────────────────────────────────────────

        assertEquals(1, solver.solve(new int[]{8, 8, 8}),
                "[8,8,8] → 1 (only 888)");

        // ── All distinct even digits ──────────────────────────────────────────

        assertEquals(6, solver.solve(new int[]{2, 4, 6}),
                "[2,4,6] → 6 (all 3! = 6 permutations are even: 246,264,426,462,624,642)");

        // ── Larger input: all ten digits 0–9 ─────────────────────────────────
        // Derivation:
        //   units=0 (1 choice): hundreds in 1..9 (9 choices), tens=any remaining 8  →  1×9×8 = 72
        //   units=2,4,6,8 (4 choices each): hundreds in 1..9 minus units (8), tens=remaining 8  →  4×8×8 = 256
        //   Total = 72 + 256 = 328

        assertEquals(328, solver.solve(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}),
                "[0-9] → 328 distinct 3-digit even numbers");
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  solutionRecursive() — Backtracking / DFS
    //
    //  NOTE: `res` is a stateful instance field, so a fresh Unique3DigitNumber
    //  instance is created for every call to prevent count accumulation across
    //  multiple assertions within the same test.
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionRecursive() — Backtracking / DFS")
    class RecursiveTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            // A new instance per call is mandatory because `res` is an instance field.
            runAll(digits -> new Unique3DigitNumber().solutionRecursive(digits));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  solutionIterative() — Triple nested loop with a boolean seen[] array
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionIterative() — Triple nested loop")
    class IterativeTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(digits -> u3d.solutionIterative(digits));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  Cross-solution consistency — both methods must agree on every result
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Cross-solution consistency")
    class ConsistencyTests {

        private void assertBothAgree(int[] digits, String label) {
            int recursive = new Unique3DigitNumber().solutionRecursive(digits);
            int iterative = u3d.solutionIterative(digits);
            assertEquals(recursive, iterative,
                    "solutionRecursive and solutionIterative must return the same result for: " + label);
        }

        @Test
        @DisplayName("Both solutions agree on the four LeetCode examples")
        void testLeetCodeExamples() {
            assertBothAgree(new int[]{1, 2, 3, 4}, "[1,2,3,4]");
            assertBothAgree(new int[]{0, 2, 2},    "[0,2,2]");
            assertBothAgree(new int[]{6, 6, 6},    "[6,6,6]");
            assertBothAgree(new int[]{1, 3, 5},    "[1,3,5]");
        }

        @Test
        @DisplayName("Both solutions agree on edge-case inputs")
        void testEdgeCases() {
            assertBothAgree(new int[]{0, 0, 0},             "[0,0,0]");
            assertBothAgree(new int[]{0, 1, 3}, "[0,1,3]");
            assertBothAgree(new int[]{1, 2, 3},             "[1,2,3]");
            assertBothAgree(new int[]{0, 1, 2},             "[0,1,2]");
            assertBothAgree(new int[]{0, 0, 2},             "[0,0,2]");
            assertBothAgree(new int[]{2, 2, 2},             "[2,2,2]");
            assertBothAgree(new int[]{4, 4, 2},             "[4,4,2]");
            assertBothAgree(new int[]{2, 4, 6},             "[2,4,6]");
            assertBothAgree(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, "[0-9]");
        }
    }
}