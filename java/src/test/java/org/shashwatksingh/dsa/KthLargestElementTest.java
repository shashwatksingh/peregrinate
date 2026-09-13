package org.shashwatksingh.dsa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Kth Largest Element Tests")
public class KthLargestElementTest {

    private final KthLargestElement kthLargestElement = new KthLargestElement();

    // ─── shared test logic so every solution is held to the same bar ───────
    interface Solver {
        int solve(int[] nums, int k);
    }

    private void runAll(Solver solver) {
        // LeetCode examples
        assertEquals(5, solver.solve(new int[]{3, 2, 1, 5, 6, 4}, 2),             "Example 1");
        assertEquals(4, solver.solve(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4),    "Example 2 - with duplicates");

        // Single element
        assertEquals(1, solver.solve(new int[]{1}, 1),                           "Single element, k=1");

        // k = 1 -> the maximum
        assertEquals(6, solver.solve(new int[]{3, 2, 1, 5, 6, 4}, 1),            "k=1 returns max");

        // k = length -> the minimum
        assertEquals(1, solver.solve(new int[]{3, 2, 1, 5, 6, 4}, 6),            "k=length returns min");

        // All elements equal
        assertEquals(7, solver.solve(new int[]{7, 7, 7, 7}, 2),                  "All duplicates");

        // Negative numbers
        assertEquals(-1, solver.solve(new int[]{-1, -2, -3, -4}, 1),            "Negative numbers, k=1");
        assertEquals(-4, solver.solve(new int[]{-1, -2, -3, -4}, 4),            "Negative numbers, k=length");

        // Mixed positive and negative
        assertEquals(2, solver.solve(new int[]{-5, 3, 2, -1, 0}, 2),            "Mixed sign numbers");

        // Already sorted descending
        assertEquals(3, solver.solve(new int[]{5, 4, 3, 2, 1}, 3),              "Sorted descending");

        // Already sorted ascending
        assertEquals(3, solver.solve(new int[]{1, 2, 3, 4, 5}, 3),              "Sorted ascending");
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionSorting() — full sort
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionSorting() — full sort")
    class SolutionSortingTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(kthLargestElement::solutionSorting);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionHeap() — min heap of size k
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionHeap() — min heap of size k")
    class SolutionHeapTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(kthLargestElement::solutionHeap);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionQuickSelect() — Hoare's quickselect
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionQuickSelect() — Hoare's quickselect")
    class SolutionQuickSelectTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(kthLargestElement::solutionQuickSelect);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  solutionCountSort() — counting sort
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("solutionCountSort() — counting sort")
    class SolutionCountSortTests {

        @Test
        @DisplayName("All shared test cases pass")
        void testAll() {
            runAll(kthLargestElement::solutionCountSort);
        }
    }
}
