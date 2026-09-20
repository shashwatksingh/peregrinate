package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Find 2 Non-Overlapping Subarrays Tests")
class Find2NonOverlappingSubarraysTest {

    private Find2NonOverlappingSubarrays solver;

    @BeforeEach
    void setUp() {
        solver = new Find2NonOverlappingSubarrays();
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  LeetCode provided examples
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("LeetCode examples")
    class LeetCodeExamples {

        @Test
        @DisplayName("Example 1: [3,2,2,4,3] target=3 → 2 (two single-element subarrays [3] and [3])")
        void testExample1() {
            assertEquals(2, solver.minSumOfLengths(new int[]{3, 2, 2, 4, 3}, 3));
        }

        @Test
        @DisplayName("Example 2: [7,3,4,7] target=7 → 2 (pick [7] at index 0 and [7] at index 3, not [3,4])")
        void testExample2() {
            assertEquals(2, solver.minSumOfLengths(new int[]{7, 3, 4, 7}, 7));
        }

        @Test
        @DisplayName("Example 3: [4,3,2,6,2,3,4] target=6 → -1 (only one subarray [6] exists)")
        void testExample3() {
            assertEquals(-1, solver.minSumOfLengths(new int[]{4, 3, 2, 6, 2, 3, 4}, 6));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Cases that must return -1
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Returns -1 cases")
    class ReturnsMinus1Cases {

        @Test
        @DisplayName("No subarray sums to target → -1")
        void testNoSubarrayMatchesTarget() {
            assertEquals(-1, solver.minSumOfLengths(new int[]{1, 2, 3}, 100));
        }

        @Test
        @DisplayName("Exactly one valid subarray (whole array) → -1")
        void testOnlyWholeArrayMatchesTarget() {
            // Only [1,1,1,1,1] sums to 5; no room for a second non-overlapping subarray
            assertEquals(-1, solver.minSumOfLengths(new int[]{1, 1, 1, 1, 1}, 5));
        }

        @Test
        @DisplayName("Single element equals target, two subarrays impossible → -1")
        void testSingleElementEqualsTarget() {
            assertEquals(-1, solver.minSumOfLengths(new int[]{5}, 5));
        }

        @Test
        @DisplayName("Single element does not equal target → -1")
        void testSingleElementNotTarget() {
            assertEquals(-1, solver.minSumOfLengths(new int[]{3}, 5));
        }

        @Test
        @DisplayName("Only overlapping subarrays exist, no non-overlapping pair → -1")
        void testOverlappingSubarraysOnly() {
            // [1,6]=7 and [6,1]=7 share index 1; no non-overlapping pair possible
            assertEquals(-1, solver.minSumOfLengths(new int[]{1, 6, 1}, 7));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Two length-1 subarrays (minimum possible result = 2)
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Two length-1 subarrays (result = 2)")
    class TwoLengthOneSubarrays {

        @Test
        @DisplayName("Two adjacent single-element subarrays: [5,5] target=5 → 2")
        void testTwoAdjacentSingleElements() {
            assertEquals(2, solver.minSumOfLengths(new int[]{5, 5}, 5));
        }

        @Test
        @DisplayName("Target at both ends with noise in between: [3,1,1,1,3] target=3 → 2")
        void testTargetAtBothEnds() {
            assertEquals(2, solver.minSumOfLengths(new int[]{3, 1, 1, 1, 3}, 3));
        }

        @Test
        @DisplayName("All elements equal target: [3,3,3] target=3 → 2")
        void testAllElementsEqualTarget() {
            assertEquals(2, solver.minSumOfLengths(new int[]{3, 3, 3}, 3));
        }

        @Test
        @DisplayName("Two single-element subarrays separated by gap: [1,2,1] target=1 → 2")
        void testTwoSingleElementsWithGap() {
            assertEquals(2, solver.minSumOfLengths(new int[]{1, 2, 1}, 1));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Optimal pair selection — multiple candidates, algorithm picks minimum
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Optimal pair selection")
    class OptimalPairSelection {

        @Test
        @DisplayName("Multiple overlapping [2,2] subarrays: [2,2,2,2,2] target=4 → 4")
        void testRepeatedPairs() {
            // Best non-overlapping pair of [2,2] subarrays has total length 2+2=4
            assertEquals(4, solver.minSumOfLengths(new int[]{2, 2, 2, 2, 2}, 4));
        }

        @Test
        @DisplayName("Mix of length-1 and length-2 candidates, prefer length-1: [1,2,3,1,2,3] target=3 → 2")
        void testMixedLengthCandidates() {
            // [3] at index 2 and [3] at index 5 are the optimal pair: 1+1=2
            assertEquals(2, solver.minSumOfLengths(new int[]{1, 2, 3, 1, 2, 3}, 3));
        }

        @Test
        @DisplayName("All matching subarrays have length 2, best pair = 4: [1,2,1,2,1] target=3 → 4")
        void testAllLength2Subarrays() {
            // [1,2] and [2,1] subarrays both have length 2; best non-overlapping pair = 2+2=4
            assertEquals(4, solver.minSumOfLengths(new int[]{1, 2, 1, 2, 1}, 3));
        }

        @Test
        @DisplayName("Two subarrays at far ends: [5,1,1,1,1,1,5] target=5 → 2")
        void testTwoSubarraysAtFarEnds() {
            assertEquals(2, solver.minSumOfLengths(new int[]{5, 1, 1, 1, 1, 1, 5}, 5));
        }

        @Test
        @DisplayName("Shorter subarray preferred over longer: [4,1,3,1,4] target=4 → 2")
        void testPreferShorterSubarray() {
            // [4] at idx 0 and [4] at idx 4 → 1+1=2; [1,3] also sums to 4 but is longer
            assertEquals(2, solver.minSumOfLengths(new int[]{4, 1, 3, 1, 4}, 4));
        }
    }

    // ═══════════════════════════════════════════════════════════════════════
    //  Boundary and constraint edge cases
    // ═══════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Boundary and constraint edge cases")
    class BoundaryAndConstraintCases {

        @Test
        @DisplayName("Minimum array length (1 element), no match → -1")
        void testMinArrayLengthNoMatch() {
            assertEquals(-1, solver.minSumOfLengths(new int[]{1}, 1000));
        }

        @Test
        @DisplayName("Two-element array, both equal target → 2")
        void testTwoElementBothEqualTarget() {
            assertEquals(2, solver.minSumOfLengths(new int[]{7, 7}, 7));
        }

        @Test
        @DisplayName("Two-element array, only combined sum matches target → -1")
        void testTwoElementOnlyFullSumMatchesTarget() {
            // Only [3,4] sums to 7; no second non-overlapping subarray possible
            assertEquals(-1, solver.minSumOfLengths(new int[]{3, 4}, 7));
        }

        @Test
        @DisplayName("Large target matched by two single-element subarrays → 2")
        void testLargeTargetValue() {
            // [50000000] at idx 0 and [50000000] at idx 2; 1+1=2
            assertEquals(2, solver.minSumOfLengths(new int[]{50000000, 1, 50000000}, 50000000));
        }

        @Test
        @DisplayName("Target equals sum of entire array, no valid pair → -1")
        void testTargetEqualsFullArraySum() {
            // [2,3,5] sums to 10 only as a whole; no other subarray sums to 10
            assertEquals(-1, solver.minSumOfLengths(new int[]{2, 3, 5}, 10));
        }
    }
}