package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Missing Elements In Array Tests")
class MissingElementsInArrayTest {

    private MissingElementsInArray missingElementsInArray;

    @BeforeEach
    void setUp() {
        missingElementsInArray = new MissingElementsInArray();
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  bruteForceSolution()  — O(n²) time, O(1) auxiliary space
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForceSolution() — O(n²) nested scan")
    class BruteForceSolutionTests {

        // ─── LeetCode examples ───────────────────────────────────────────────

        @Test
        @DisplayName("LeetCode Example 1: [4,3,2,7,8,2,3,1] → [5,6] (two gaps in middle)")
        void testLeetCodeExample1() {
            assertArrayEquals(
                    new int[]{5, 6},
                    missingElementsInArray.bruteForceSolution(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,1] → [2] (n itself is missing)")
        void testLeetCodeExample2() {
            assertArrayEquals(
                    new int[]{2},
                    missingElementsInArray.bruteForceSolution(new int[]{1, 1}));
        }

        // ─── Single-element / minimum-size input ─────────────────────────────

        @Test
        @DisplayName("Single element [1] → [] (only possible value 1 is present)")
        void testSingleElementPresent() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.bruteForceSolution(new int[]{1}));
        }

        // ─── No missing — complete 1..n range ────────────────────────────────

        @Test
        @DisplayName("Full range reversed [3,2,1] → [] (all of 1..n present)")
        void testFullRangeReversed() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.bruteForceSolution(new int[]{3, 2, 1}));
        }

        // ─── All-same / uniform values ────────────────────────────────────────

        @Test
        @DisplayName("All same non-1 value [2,2,2] → [1,3] (1 and n=3 both absent)")
        void testAllSameNonOne() {
            assertArrayEquals(
                    new int[]{1, 3},
                    missingElementsInArray.bruteForceSolution(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("All same value = 1 [1,1,1,1] → [2,3,4] (2 through n missing)")
        void testAllSameOne() {
            assertArrayEquals(
                    new int[]{2, 3, 4},
                    missingElementsInArray.bruteForceSolution(new int[]{1, 1, 1, 1}));
        }

        @Test
        @DisplayName("All same value = n [3,3,3] → [1,2] (1 and 2 absent, n present)")
        void testAllSameN() {
            assertArrayEquals(
                    new int[]{1, 2},
                    missingElementsInArray.bruteForceSolution(new int[]{3, 3, 3}));
        }

        // ─── Multiple gaps including n ────────────────────────────────────────

        @Test
        @DisplayName("Gaps at even positions [1,3,3,3,5,5] → [2,4,6] (includes n=6)")
        void testGapsIncludingN() {
            assertArrayEquals(
                    new int[]{2, 4, 6},
                    missingElementsInArray.bruteForceSolution(new int[]{1, 3, 3, 3, 5, 5}));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  setSolution()  — O(n) time, O(n) space — HashSet membership
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("setSolution() — O(n) HashSet lookup")
    class SetSolutionTests {

        // ─── LeetCode examples ───────────────────────────────────────────────

        @Test
        @DisplayName("LeetCode Example 1: [4,3,2,7,8,2,3,1] → [5,6] (two gaps in middle)")
        void testLeetCodeExample1() {
            assertArrayEquals(
                    new int[]{5, 6},
                    missingElementsInArray.setSolution(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [1,1] → [2] (n itself is missing)")
        void testLeetCodeExample2() {
            assertArrayEquals(
                    new int[]{2},
                    missingElementsInArray.setSolution(new int[]{1, 1}));
        }

        // ─── Single-element / minimum-size input ─────────────────────────────

        @Test
        @DisplayName("Single element [1] → [] (only possible value 1 is present)")
        void testSingleElementPresent() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.setSolution(new int[]{1}));
        }

        // ─── No missing — complete 1..n range ────────────────────────────────

        @Test
        @DisplayName("Full range reversed [3,2,1] → [] (all of 1..n present)")
        void testFullRangeReversed() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.setSolution(new int[]{3, 2, 1}));
        }

        // ─── All-same / uniform values ────────────────────────────────────────

        @Test
        @DisplayName("All same non-1 value [2,2,2] → [1,3] (1 and n=3 both absent)")
        void testAllSameNonOne() {
            assertArrayEquals(
                    new int[]{1, 3},
                    missingElementsInArray.setSolution(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("All same value = 1 [1,1,1,1] → [2,3,4] (2 through n missing)")
        void testAllSameOne() {
            assertArrayEquals(
                    new int[]{2, 3, 4},
                    missingElementsInArray.setSolution(new int[]{1, 1, 1, 1}));
        }

        @Test
        @DisplayName("All same value = n [3,3,3] → [1,2] (1 and 2 absent, n present)")
        void testAllSameN() {
            assertArrayEquals(
                    new int[]{1, 2},
                    missingElementsInArray.setSolution(new int[]{3, 3, 3}));
        }

        // ─── Multiple gaps including n ────────────────────────────────────────

        @Test
        @DisplayName("Gaps at even positions [1,3,3,3,5,5] → [2,4,6] (includes n=6)")
        void testGapsIncludingN() {
            assertArrayEquals(
                    new int[]{2, 4, 6},
                    missingElementsInArray.setSolution(new int[]{1, 3, 3, 3, 5, 5}));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  optimisedSolution()  — O(n) time, O(1) space — in-place sign marking
    //
    //  ⚠️  BUG: The second loop is `for (int i = 1; i < n; i++)` which only
    //  checks nums[i-1] for i ∈ [1, n-1], i.e. indices 0..n-2, and adds
    //  values 1..n-1.  It NEVER checks nums[n-1], so value `n` is never
    //  added to the result even when it is absent from the input.
    //
    //  Fix: change `i < n` → `i <= n` so the loop covers all n positions.
    //  Tests that expose this bug are annotated with ⚠️ BUG below.
    // ═══════════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("optimisedSolution() — O(n) in-place sign marking ⚠️ BUG: never emits n when absent")
    class OptimisedSolutionTests {

        // ─── LeetCode examples ───────────────────────────────────────────────

        @Test
        @DisplayName("LeetCode Example 1: [4,3,2,7,8,2,3,1] → [5,6] (missing values are not n)")
        void testLeetCodeExample1() {
            assertArrayEquals(
                    new int[]{5, 6},
                    missingElementsInArray.optimisedSolution(new int[]{4, 3, 2, 7, 8, 2, 3, 1}));
        }

        @Test
        @DisplayName("⚠️ BUG — LeetCode Example 2: [1,1] → [2] expected, but returns [] (n=2 is missing)")
        void testLeetCodeExample2_exposedBug() {
            // The second loop only runs i in [1, n-1]; for n=2 that is only i=1,
            // checking nums[0]. It never checks nums[1], so value 2 (=n) is never added.
            // This test FAILS until the loop bound is fixed: `i < n` → `i <= n`.
            assertArrayEquals(
                    new int[]{2},
                    missingElementsInArray.optimisedSolution(new int[]{1, 1}));
        }

        // ─── Single-element / minimum-size input ─────────────────────────────

        @Test
        @DisplayName("Single element [1] → [] (only possible value 1 is present; n not missing)")
        void testSingleElementPresent() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.optimisedSolution(new int[]{1}));
        }

        // ─── No missing — complete 1..n range ────────────────────────────────

        @Test
        @DisplayName("Full range reversed [3,2,1] → [] (all of 1..n present; n not missing)")
        void testFullRangeReversed() {
            assertArrayEquals(
                    new int[]{},
                    missingElementsInArray.optimisedSolution(new int[]{3, 2, 1}));
        }

        // ─── All-same / uniform values ────────────────────────────────────────

        @Test
        @DisplayName("⚠️ BUG — All same non-1 value [2,2,2] → [1,3] expected, returns [1] (n=3 absent)")
        void testAllSameNonOne_exposedBug() {
            // Value n=3 is missing but the loop only checks up to index n-2=1.
            // This test FAILS until the loop bound is fixed.
            assertArrayEquals(
                    new int[]{1, 3},
                    missingElementsInArray.optimisedSolution(new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("⚠️ BUG — All same value = 1 [1,1,1,1] → [2,3,4] expected, returns [2,3] (n=4 absent)")
        void testAllSameOne_exposedBug() {
            // Values 2, 3, and 4 are all missing. 4=n is never found due to the loop bug.
            // This test FAILS until the loop bound is fixed.
            assertArrayEquals(
                    new int[]{2, 3, 4},
                    missingElementsInArray.optimisedSolution(new int[]{1, 1, 1, 1}));
        }

        @Test
        @DisplayName("All same value = n [3,3,3] → [1,2] (n=3 is present; bug does not affect this case)")
        void testAllSameN() {
            // n itself is present, so the off-by-one does not matter here.
            assertArrayEquals(
                    new int[]{1, 2},
                    missingElementsInArray.optimisedSolution(new int[]{3, 3, 3}));
        }

        // ─── Multiple gaps including n ────────────────────────────────────────

        @Test
        @DisplayName("⚠️ BUG — Gaps including n=6: [1,3,3,3,5,5] → [2,4,6] expected, returns [2,4]")
        void testGapsIncludingN_exposedBug() {
            // Value 6=n is missing but will never be added because the loop stops at i < n.
            // This test FAILS until the loop bound is fixed.
            assertArrayEquals(
                    new int[]{2, 4, 6},
                    missingElementsInArray.optimisedSolution(new int[]{1, 3, 3, 3, 5, 5}));
        }
    }
}