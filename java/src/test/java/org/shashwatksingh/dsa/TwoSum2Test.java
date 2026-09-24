package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("TwoSum2 Tests")
class TwoSum2Test {

    private TwoSum2 instance;

    @BeforeEach
    void setUp() {
        instance = new TwoSum2();
    }

    // ═══════════════════════════════════════════════════════════
    //  twoSum()  — brute force O(n²) nested loop
    // ═══════════════════════════════════════════════════════════
    @Nested
    // BUG: the inner loop starts at j=0 instead of i+1, so numbers[i]+numbers[i]
    // (the same element added to itself) is checked before any distinct pair.
    // Whenever target == 2*numbers[i] for some i reachable before the real answer,
    // the method returns {i, i} — reusing the same element twice, which the
    // problem explicitly forbids.
    @DisplayName("twoSum() — brute force ⚠️ BUGGY — reuses the same index when 2×value == target")
    class TwoSumTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,7,11,15], target=9 → [0,1]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSum(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,3,4], target=6 → [0,2]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 2}, instance.twoSum(new int[]{2, 3, 4}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: [-1,0], target=-1 → [0,1]")
        void testLeetCodeExample3() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSum(new int[]{-1, 0}, -1));
        }

        @Test
        @DisplayName("No valid answer: [1,2,3], target=100 → [-1,-1]")
        void testNoValidAnswer() {
            assertArrayEquals(new int[]{-1, -1}, instance.twoSum(new int[]{1, 2, 3}, 100));
        }

        @Test
        @DisplayName("Max constraint boundary values: [-1000,-1,1000], target=0 → [0,2]")
        void testBoundaryValues() {
            assertArrayEquals(new int[]{0, 2}, instance.twoSum(new int[]{-1000, -1, 1000}, 0));
        }

        @Test
        @DisplayName("BUG: [4,4,4], target=8 → expected [0,1] but self-doubles at i=0,j=0 first")
        void testSelfReuseAtFirstElement() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSum(new int[]{4, 4, 4}, 8));
        }

        @Test
        @DisplayName("BUG: [1,4,4], target=8 → expected [1,2] but self-doubles at i=1,j=1 first")
        void testSelfReuseAtLaterElement() {
            assertArrayEquals(new int[]{1, 2}, instance.twoSum(new int[]{1, 4, 4}, 8));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  twoSumHashMap()  — complement-lookup with HashMap
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoSumHashMap()")
    class TwoSumHashMapTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,7,11,15], target=9 → [0,1]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumHashMap(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,3,4], target=6 → [0,2]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 2}, instance.twoSumHashMap(new int[]{2, 3, 4}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: [-1,0], target=-1 → [0,1]")
        void testLeetCodeExample3() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumHashMap(new int[]{-1, 0}, -1));
        }

        @Test
        @DisplayName("BUG: [3,3], target=6 → expected [0,1] but returns indices reversed as [1,0]")
        void testAccidentalMatchReturnsReversedOrder() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumHashMap(new int[]{3, 3}, 6));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  twoSumBinarySearch()  — complement lookup via Arrays.binarySearch
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoSumBinarySearch() — complement lookup via binary search")
    class TwoSumBinarySearchTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,7,11,15], target=9 → [0,1]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumBinarySearch(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,3,4], target=6 → [0,2]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 2}, instance.twoSumBinarySearch(new int[]{2, 3, 4}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: [-1,0], target=-1 → [0,1]")
        void testLeetCodeExample3() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumBinarySearch(new int[]{-1, 0}, -1));
        }

        // BUG: `if (index > 0)` should be `if (index >= 0)`. Arrays.binarySearch
        // legitimately returns 0 when the complement lives at index 0, and that
        // valid match is silently discarded.
        @Test
        @DisplayName("BUG: [5,5], target=10 → expected [0,1] but binarySearch lands on index 0 and `index > 0` rejects it")
        void testValidMatchAtIndexZeroIsRejected() {
            assertArrayEquals(new int[]{0, 1}, instance.twoSumBinarySearch(new int[]{5, 5}, 10));
        }

        // BUG: there is no check that the found `index` differs from the current `i`,
        // so a duplicate value can match against itself and reuse the same element twice.
        @Test
        @DisplayName("BUG: [0,3,3,8], target=6 → expected [1,2] but matches itself and returns [1,1]")
        void testSelfMatchReusesSameIndex() {
            assertArrayEquals(new int[]{1, 2}, instance.twoSumBinarySearch(new int[]{0, 3, 3, 8}, 6));
        }

        @Test
        @DisplayName("Max constraint boundary values: [-1000,-1,1000], target=0 → [0,2]")
        void testBoundaryValues() {
            assertArrayEquals(new int[]{0, 2}, instance.twoSumBinarySearch(new int[]{-1000, -1, 1000}, 0));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  twoPointers()  — converging two-pointer scan
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoPointers() — ⚠️ BUGGY — right pointer never initialized, loop body never runs")
    class TwoPointersTests {

        @Test
        @DisplayName("LeetCode Example 1: [2,7,11,15], target=9 → [0,1]")
        void testLeetCodeExample1() {
            assertArrayEquals(new int[]{0, 1}, instance.twoPointers(new int[]{2, 7, 11, 15}, 9));
        }

        @Test
        @DisplayName("LeetCode Example 2: [2,3,4], target=6 → [0,2]")
        void testLeetCodeExample2() {
            assertArrayEquals(new int[]{0, 2}, instance.twoPointers(new int[]{2, 3, 4}, 6));
        }

        @Test
        @DisplayName("LeetCode Example 3: [-1,0], target=-1 → [0,1]")
        void testLeetCodeExample3() {
            assertArrayEquals(new int[]{0, 1}, instance.twoPointers(new int[]{-1, 0}, -1));
        }

        @Test
        @DisplayName("Minimum-size input: [1,2], target=3 → [0,1]")
        void testMinimumSizeInput() {
            assertArrayEquals(new int[]{0, 1}, instance.twoPointers(new int[]{1, 2}, 3));
        }
    }
}
