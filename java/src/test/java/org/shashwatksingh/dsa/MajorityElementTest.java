package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Majority Element Tests")
class MajorityElementTest {

    private MajorityElement majorityElement;

    @BeforeEach
    void setUp() {
        majorityElement = new MajorityElement();
    }

    // ─── Shared helper ────────────────────────────────────────────────────────

    private void assertMajority(int expected, int actual, String methodName) {
        assertEquals(expected, actual,
                methodName + " should return the majority element " + expected);
    }

    // ─── bruteForceSolution (O(n²) nested loop) ───────────────────────────────

    @Nested
    @DisplayName("bruteForceSolution (O(n²) nested loop)")
    class BruteForceSolutionTests {

        @Test
        @DisplayName("Example 1: [3,2,3] → 3")
        void testExample1() {
            assertMajority(3, majorityElement.bruteForceSolution(new int[]{3, 2, 3}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Example 2: [2,2,1,1,1,2,2] → 2")
        void testExample2() {
            assertMajority(2, majorityElement.bruteForceSolution(new int[]{2, 2, 1, 1, 1, 2, 2}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Single element: [1] → 1")
        void testSingleElement() {
            assertMajority(1, majorityElement.bruteForceSolution(new int[]{1}), "bruteForceSolution");
        }

        @Test
        @DisplayName("All same elements: [5,5,5,5] → 5")
        void testAllSameElements() {
            assertMajority(5, majorityElement.bruteForceSolution(new int[]{5, 5, 5, 5}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Majority clustered at start: [3,3,3,1,2] → 3")
        void testMajorityAtStart() {
            assertMajority(3, majorityElement.bruteForceSolution(new int[]{3, 3, 3, 1, 2}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Majority clustered at end: [1,2,3,3,3] → 3")
        void testMajorityAtEnd() {
            assertMajority(3, majorityElement.bruteForceSolution(new int[]{1, 2, 3, 3, 3}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Negative majority: [-1,-1,-1,2,3] → -1")
        void testNegativeMajority() {
            assertMajority(-1, majorityElement.bruteForceSolution(new int[]{-1, -1, -1, 2, 3}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Boundary values: [-10^9,-10^9,10^9] → -10^9")
        void testBoundaryValues() {
            assertMajority(-1_000_000_000, majorityElement.bruteForceSolution(
                    new int[]{-1_000_000_000, -1_000_000_000, 1_000_000_000}), "bruteForceSolution");
        }

        @Test
        @DisplayName("Two identical elements: [7,7] → 7")
        void testTwoIdenticalElements() {
            assertMajority(7, majorityElement.bruteForceSolution(new int[]{7, 7}), "bruteForceSolution");
        }
    }

    // ─── hashMapSolution (HashMap frequency count) ────────────────────────────

    @Nested
    @DisplayName("hashMapSolution (HashMap frequency count)")
    class HashMapSolutionTests {

        @Test
        @DisplayName("Example 1: [3,2,3] → 3")
        void testExample1() {
            assertMajority(3, majorityElement.hashMapSolution(new int[]{3, 2, 3}), "hashMapSolution");
        }

        @Test
        @DisplayName("Example 2: [2,2,1,1,1,2,2] → 2")
        void testExample2() {
            assertMajority(2, majorityElement.hashMapSolution(new int[]{2, 2, 1, 1, 1, 2, 2}), "hashMapSolution");
        }

        @Test
        @DisplayName("Single element: [1] → 1")
        void testSingleElement() {
            assertMajority(1, majorityElement.hashMapSolution(new int[]{1}), "hashMapSolution");
        }

        @Test
        @DisplayName("All same elements: [5,5,5,5] → 5")
        void testAllSameElements() {
            assertMajority(5, majorityElement.hashMapSolution(new int[]{5, 5, 5, 5}), "hashMapSolution");
        }

        @Test
        @DisplayName("Majority clustered at start: [3,3,3,1,2] → 3")
        void testMajorityAtStart() {
            assertMajority(3, majorityElement.hashMapSolution(new int[]{3, 3, 3, 1, 2}), "hashMapSolution");
        }

        @Test
        @DisplayName("Majority clustered at end: [1,2,3,3,3] → 3")
        void testMajorityAtEnd() {
            assertMajority(3, majorityElement.hashMapSolution(new int[]{1, 2, 3, 3, 3}), "hashMapSolution");
        }

        @Test
        @DisplayName("Negative majority: [-1,-1,-1,2,3] → -1")
        void testNegativeMajority() {
            assertMajority(-1, majorityElement.hashMapSolution(new int[]{-1, -1, -1, 2, 3}), "hashMapSolution");
        }

        @Test
        @DisplayName("Boundary values: [-10^9,-10^9,10^9] → -10^9")
        void testBoundaryValues() {
            assertMajority(-1_000_000_000, majorityElement.hashMapSolution(
                    new int[]{-1_000_000_000, -1_000_000_000, 1_000_000_000}), "hashMapSolution");
        }

        @Test
        @DisplayName("Two identical elements: [7,7] → 7")
        void testTwoIdenticalElements() {
            assertMajority(7, majorityElement.hashMapSolution(new int[]{7, 7}), "hashMapSolution");
        }
    }

    // ─── sortSolution (Sort + linear scan) ───────────────────────────────────

    @Nested
    @DisplayName("sortSolution (Sort + linear scan)")
    class SortSolutionTests {

        @Test
        @DisplayName("Example 1: [3,2,3] → 3")
        void testExample1() {
            assertMajority(3, majorityElement.sortSolution(new int[]{3, 2, 3}), "sortSolution");
        }

        @Test
        @DisplayName("Example 2: [2,2,1,1,1,2,2] → 2")
        void testExample2() {
            assertMajority(2, majorityElement.sortSolution(new int[]{2, 2, 1, 1, 1, 2, 2}), "sortSolution");
        }

        @Test
        @DisplayName("Single element: [1] → 1")
        void testSingleElement() {
            assertMajority(1, majorityElement.sortSolution(new int[]{1}), "sortSolution");
        }

        @Test
        @DisplayName("All same elements: [5,5,5,5] → 5")
        void testAllSameElements() {
            assertMajority(5, majorityElement.sortSolution(new int[]{5, 5, 5, 5}), "sortSolution");
        }

        @Test
        @DisplayName("Majority clustered at start: [3,3,3,1,2] → 3")
        void testMajorityAtStart() {
            assertMajority(3, majorityElement.sortSolution(new int[]{3, 3, 3, 1, 2}), "sortSolution");
        }

        @Test
        @DisplayName("Majority clustered at end: [1,2,3,3,3] → 3")
        void testMajorityAtEnd() {
            assertMajority(3, majorityElement.sortSolution(new int[]{1, 2, 3, 3, 3}), "sortSolution");
        }

        @Test
        @DisplayName("Negative majority: [-1,-1,-1,2,3] → -1")
        void testNegativeMajority() {
            assertMajority(-1, majorityElement.sortSolution(new int[]{-1, -1, -1, 2, 3}), "sortSolution");
        }

        @Test
        @DisplayName("Boundary values: [-10^9,-10^9,10^9] → -10^9")
        void testBoundaryValues() {
            assertMajority(-1_000_000_000, majorityElement.sortSolution(
                    new int[]{-1_000_000_000, -1_000_000_000, 1_000_000_000}), "sortSolution");
        }

        @Test
        @DisplayName("Two identical elements: [7,7] → 7")
        void testTwoIdenticalElements() {
            assertMajority(7, majorityElement.sortSolution(new int[]{7, 7}), "sortSolution");
        }
    }

    // ─── sortSolutionOptimised (Sort + return middle index) ───────────────────

    @Nested
    @DisplayName("sortSolutionOptimised (Sort + return middle index)")
    class SortSolutionOptimisedTests {

        @Test
        @DisplayName("Example 1: [3,2,3] → 3")
        void testExample1() {
            assertMajority(3, majorityElement.sortSolutionOptimised(new int[]{3, 2, 3}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Example 2: [2,2,1,1,1,2,2] → 2")
        void testExample2() {
            assertMajority(2, majorityElement.sortSolutionOptimised(new int[]{2, 2, 1, 1, 1, 2, 2}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Single element: [1] → 1")
        void testSingleElement() {
            assertMajority(1, majorityElement.sortSolutionOptimised(new int[]{1}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("All same elements: [5,5,5,5] → 5")
        void testAllSameElements() {
            assertMajority(5, majorityElement.sortSolutionOptimised(new int[]{5, 5, 5, 5}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Majority clustered at start: [3,3,3,1,2] → 3")
        void testMajorityAtStart() {
            assertMajority(3, majorityElement.sortSolutionOptimised(new int[]{3, 3, 3, 1, 2}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Majority clustered at end: [1,2,3,3,3] → 3")
        void testMajorityAtEnd() {
            assertMajority(3, majorityElement.sortSolutionOptimised(new int[]{1, 2, 3, 3, 3}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Negative majority: [-1,-1,-1,2,3] → -1")
        void testNegativeMajority() {
            assertMajority(-1, majorityElement.sortSolutionOptimised(new int[]{-1, -1, -1, 2, 3}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Boundary values: [-10^9,-10^9,10^9] → -10^9")
        void testBoundaryValues() {
            assertMajority(-1_000_000_000, majorityElement.sortSolutionOptimised(
                    new int[]{-1_000_000_000, -1_000_000_000, 1_000_000_000}), "sortSolutionOptimised");
        }

        @Test
        @DisplayName("Two identical elements: [7,7] → 7")
        void testTwoIdenticalElements() {
            assertMajority(7, majorityElement.sortSolutionOptimised(new int[]{7, 7}), "sortSolutionOptimised");
        }
    }
}
