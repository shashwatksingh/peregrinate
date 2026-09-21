package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ContainsDuplicate Tests")
class ContainsDuplicateTest {

    private ContainsDuplicate containsDuplicate;

    @BeforeEach
    void setUp() {
        containsDuplicate = new ContainsDuplicate();
    }

    @Nested
    @DisplayName("setSolution (HashSet approach)")
    class SetSolutionTests {

        @Test
        @DisplayName("Example 1: [1,2,3,1] → true (duplicate at index 0 and 3)")
        void testDuplicateAtStartAndEnd() {
            assertTrue(containsDuplicate.setSolution(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("Example 2: [1,2,3,4] → false (all distinct)")
        void testAllDistinct() {
            assertFalse(containsDuplicate.setSolution(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Example 3: [1,1,1,3,3,4,3,2,4,2] → true (many duplicates)")
        void testManyDuplicates() {
            assertTrue(containsDuplicate.setSolution(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        }

        @Test
        @DisplayName("Single element [42] → false (cannot have a duplicate)")
        void testSingleElement() {
            assertFalse(containsDuplicate.setSolution(new int[]{42}));
        }

        @Test
        @DisplayName("Two identical elements [7,7] → true")
        void testTwoIdenticalElements() {
            assertTrue(containsDuplicate.setSolution(new int[]{7, 7}));
        }

        @Test
        @DisplayName("Two distinct elements [1,2] → false")
        void testTwoDistinctElements() {
            assertFalse(containsDuplicate.setSolution(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Negative numbers with duplicate [-3,-1,-3] → true")
        void testNegativeNumbersWithDuplicate() {
            assertTrue(containsDuplicate.setSolution(new int[]{-3, -1, -3}));
        }

        @Test
        @DisplayName("Negative numbers all distinct [-1,-2,-3] → false")
        void testNegativeNumbersAllDistinct() {
            assertFalse(containsDuplicate.setSolution(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("Boundary values: contains duplicate [-10^9, 10^9, -10^9] → true")
        void testBoundaryValuesWithDuplicate() {
            assertTrue(containsDuplicate.setSolution(new int[]{-1_000_000_000, 1_000_000_000, -1_000_000_000}));
        }

        @Test
        @DisplayName("Boundary values: all distinct [-10^9, 0, 10^9] → false")
        void testBoundaryValuesAllDistinct() {
            assertFalse(containsDuplicate.setSolution(new int[]{-1_000_000_000, 0, 1_000_000_000}));
        }
    }

    @Nested
    @DisplayName("twoPointer (Sort + adjacent comparison)")
    class TwoPointerTests {

        @Test
        @DisplayName("Example 1: [1,2,3,1] → true (duplicate at index 0 and 3)")
        void testDuplicateAtStartAndEnd() {
            assertTrue(containsDuplicate.twoPointer(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("Example 2: [1,2,3,4] → false (all distinct)")
        void testAllDistinct() {
            assertFalse(containsDuplicate.twoPointer(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Example 3: [1,1,1,3,3,4,3,2,4,2] → true (many duplicates)")
        void testManyDuplicates() {
            assertTrue(containsDuplicate.twoPointer(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        }

        @Test
        @DisplayName("Single element [42] → false (cannot have a duplicate)")
        void testSingleElement() {
            assertFalse(containsDuplicate.twoPointer(new int[]{42}));
        }

        @Test
        @DisplayName("Two identical elements [7,7] → true")
        void testTwoIdenticalElements() {
            assertTrue(containsDuplicate.twoPointer(new int[]{7, 7}));
        }

        @Test
        @DisplayName("Two distinct elements [1,2] → false")
        void testTwoDistinctElements() {
            assertFalse(containsDuplicate.twoPointer(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Negative numbers with duplicate [-3,-1,-3] → true")
        void testNegativeNumbersWithDuplicate() {
            assertTrue(containsDuplicate.twoPointer(new int[]{-3, -1, -3}));
        }

        @Test
        @DisplayName("Negative numbers all distinct [-1,-2,-3] → false")
        void testNegativeNumbersAllDistinct() {
            assertFalse(containsDuplicate.twoPointer(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("Boundary values: contains duplicate [-10^9, 10^9, -10^9] → true")
        void testBoundaryValuesWithDuplicate() {
            assertTrue(containsDuplicate.twoPointer(new int[]{-1_000_000_000, 1_000_000_000, -1_000_000_000}));
        }

        @Test
        @DisplayName("Boundary values: all distinct [-10^9, 0, 10^9] → false")
        void testBoundaryValuesAllDistinct() {
            assertFalse(containsDuplicate.twoPointer(new int[]{-1_000_000_000, 0, 1_000_000_000}));
        }
    }

    @Nested
    @DisplayName("bruteForceSolution (O(n²) nested loop)")
    class BruteForceSolutionTests {

        @Test
        @DisplayName("Example 1: [1,2,3,1] → true (duplicate at index 0 and 3)")
        void testDuplicateAtStartAndEnd() {
            assertTrue(containsDuplicate.bruteForceSolution(new int[]{1, 2, 3, 1}));
        }

        @Test
        @DisplayName("Example 2: [1,2,3,4] → false (all distinct)")
        void testAllDistinct() {
            assertFalse(containsDuplicate.bruteForceSolution(new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Example 3: [1,1,1,3,3,4,3,2,4,2] → true (many duplicates)")
        void testManyDuplicates() {
            assertTrue(containsDuplicate.bruteForceSolution(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        }

        @Test
        @DisplayName("Single element [42] → false (cannot have a duplicate)")
        void testSingleElement() {
            assertFalse(containsDuplicate.bruteForceSolution(new int[]{42}));
        }

        @Test
        @DisplayName("Two identical elements [7,7] → true")
        void testTwoIdenticalElements() {
            assertTrue(containsDuplicate.bruteForceSolution(new int[]{7, 7}));
        }

        @Test
        @DisplayName("Two distinct elements [1,2] → false")
        void testTwoDistinctElements() {
            assertFalse(containsDuplicate.bruteForceSolution(new int[]{1, 2}));
        }

        @Test
        @DisplayName("Negative numbers with duplicate [-3,-1,-3] → true")
        void testNegativeNumbersWithDuplicate() {
            assertTrue(containsDuplicate.bruteForceSolution(new int[]{-3, -1, -3}));
        }

        @Test
        @DisplayName("Negative numbers all distinct [-1,-2,-3] → false")
        void testNegativeNumbersAllDistinct() {
            assertFalse(containsDuplicate.bruteForceSolution(new int[]{-1, -2, -3}));
        }

        @Test
        @DisplayName("Boundary values: contains duplicate [-10^9, 10^9, -10^9] → true")
        void testBoundaryValuesWithDuplicate() {
            assertTrue(containsDuplicate.bruteForceSolution(new int[]{-1_000_000_000, 1_000_000_000, -1_000_000_000}));
        }

        @Test
        @DisplayName("Boundary values: all distinct [-10^9, 0, 10^9] → false")
        void testBoundaryValuesAllDistinct() {
            assertFalse(containsDuplicate.bruteForceSolution(new int[]{-1_000_000_000, 0, 1_000_000_000}));
        }
    }
}
