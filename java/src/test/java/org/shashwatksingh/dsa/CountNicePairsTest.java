package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("CountNicePairs Tests")
class CountNicePairsTest {

    private CountNicePairs countNicePairs;

    @BeforeEach
    void setUp() {
        countNicePairs = new CountNicePairs();
    }

    @Test
    @DisplayName("Example 1: [42,11,1,97] → 2")
    void testExample1() {
        assertEquals(2, countNicePairs.solution(new int[]{42, 11, 1, 97}));
    }

    @Test
    @DisplayName("Example 2: [13,10,35,24,76] → 4")
    void testExample2() {
        assertEquals(4, countNicePairs.solution(new int[]{13, 10, 35, 24, 76}));
    }

    @Test
    @DisplayName("Single element array → 0 (no pairs possible)")
    void testSingleElement() {
        assertEquals(0, countNicePairs.solution(new int[]{5}));
    }

    @Test
    @DisplayName("Two elements that form a nice pair: [12, 21] → 1")
    void testTwoNiceElements() {
        // 12 - rev(12)=21 → diff = -9; 21 - rev(21)=12 → diff = 9; not equal → 0
        // Actually: nums[i]+rev(nums[j]) == nums[j]+rev(nums[i])
        // 12 + rev(21) = 12 + 12 = 24; 21 + rev(12) = 21 + 21 = 42 → NOT nice
        // Use [11, 11]: 11+rev(11)=11+11=22 == 11+rev(11)=22 → nice
        assertEquals(1, countNicePairs.solution(new int[]{11, 11}));
    }

    @Test
    @DisplayName("Two elements that do NOT form a nice pair: [12, 21] → 0")
    void testTwoNonNiceElements() {
        assertEquals(0, countNicePairs.solution(new int[]{12, 21}));
    }

    @Test
    @DisplayName("All zeros → n*(n-1)/2 pairs")
    void testAllZeros() {
        // [0,0,0,0]: 4 elements, all diffs = 0, C(4,2) = 6
        assertEquals(6, countNicePairs.solution(new int[]{0, 0, 0, 0}));
    }

    @Test
    @DisplayName("Palindrome numbers form nice pairs with each other: [121, 131, 11] → 3")
    void testPalindromes() {
        // All palindromes have nums[i] - rev(nums[i]) = 0, so all pairs are nice
        // C(3,2) = 3
        assertEquals(3, countNicePairs.solution(new int[]{121, 131, 11}));
    }

    @Test
    @DisplayName("Single digit numbers: all have rev == themselves, all pairs nice")
    void testSingleDigitNumbers() {
        // [1,2,3]: all single-digit so rev(x)=x, diff=0 for all → C(3,2)=3
        assertEquals(3, countNicePairs.solution(new int[]{1, 2, 3}));
    }

    @Test
    @DisplayName("No nice pairs when all diffs are distinct: [10, 20, 30] → 0")
    void testNoPairs() {
        // 10 - rev(10)=01=1 → diff=9
        // 20 - rev(20)=02=2 → diff=18
        // 30 - rev(30)=03=3 → diff=27
        // All different → 0 nice pairs
        assertEquals(0, countNicePairs.solution(new int[]{10, 20, 30}));
    }

    @Test
    @DisplayName("Large values near constraint boundary: [1000000000, 1000000000] → 1")
    void testLargeValues() {
        assertEquals(1, countNicePairs.solution(new int[]{1000000000, 1000000000}));
    }

    @Test
    @DisplayName("Modulo check: large array of identical elements produces result mod 1e9+7")
    void testModulo() {
        // n identical elements → C(n,2) = n*(n-1)/2 pairs, must be mod 1e9+7
        // Use n=100000, C(100000,2) = 4999950000 mod (1e9+7) = 4999950000 - 4*(1e9+7) = 4999950000 - 4000000028 = 999949972
        int n = 100000;
        int[] nums = new int[n];
        java.util.Arrays.fill(nums, 7);
        assertEquals(999949972, countNicePairs.solution(nums));
    }

    // ── Trailing-zero edge cases ──────────────────────────────────────────────

    @Test
    @DisplayName("Trailing zero strips on reverse: [10, 1] → 0 (10 has diff=9, 1 has diff=0)")
    void testTrailingZeroVsSingleDigit() {
        // rev(10) = 1  → diff = 10 - 1  = 9
        // rev(1)  = 1  → diff = 1  - 1  = 0
        // Different groups → 0 nice pairs
        assertEquals(0, countNicePairs.solution(new int[]{10, 1}));
    }

    @Test
    @DisplayName("Trailing zero: [120, 110] share the same diff=99 → 1 nice pair")
    void testTrailingZeroSameDiff() {
        // rev(120) = 21  → diff = 120 - 21  = 99
        // rev(110) = 11  → diff = 110 - 11  = 99
        // Same group → 1 nice pair
        assertEquals(1, countNicePairs.solution(new int[]{120, 110}));
    }

    @Test
    @DisplayName("Trailing zero: [10, 100] have different diffs → 0 nice pairs")
    void testTrailingZeroDifferentDiffs() {
        // rev(10)  = 1  → diff = 9
        // rev(100) = 1  → diff = 99
        // Different groups → 0 nice pairs
        assertEquals(0, countNicePairs.solution(new int[]{10, 100}));
    }

    @Test
    @DisplayName("Large number with trailing zero [1000000000, 100] → 0 (diffs differ)")
    void testLargeTrailingZero() {
        // rev(1000000000) = 1  → diff = 999999999
        // rev(100)        = 1  → diff = 99
        // Different groups → 0 nice pairs
        assertEquals(0, countNicePairs.solution(new int[]{1000000000, 100}));
    }

    // ── Zero element edge cases ───────────────────────────────────────────────

    @Test
    @DisplayName("Zero pairs with single-digit numbers (all have diff=0): [0, 3, 7] → 3")
    void testZeroWithSingleDigits() {
        // rev(0) = 0 → diff = 0
        // rev(3) = 3 → diff = 0
        // rev(7) = 7 → diff = 0
        // All in the same group → C(3,2) = 3
        assertEquals(3, countNicePairs.solution(new int[]{0, 3, 7}));
    }

    @Test
    @DisplayName("Zero does NOT pair with multi-digit non-palindrome: [0, 10] → 0")
    void testZeroVsMultiDigit() {
        // rev(0)  = 0 → diff = 0
        // rev(10) = 1 → diff = 9
        // Different groups → 0 nice pairs
        assertEquals(0, countNicePairs.solution(new int[]{0, 10}));
    }

    @Test
    @DisplayName("Array containing only zeros: [0, 0, 0] → 3")
    void testAllExplicitZeros() {
        // All diffs = 0 → C(3,2) = 3
        assertEquals(3, countNicePairs.solution(new int[]{0, 0, 0}));
    }

    // ── Multiple distinct groups in one array ─────────────────────────────────

    @Test
    @DisplayName("Two independent groups: [1, 2, 10, 20] → 1 (only group diff=0 yields a pair)")
    void testTwoDistinctGroups() {
        // 1  → diff=0, 2  → diff=0  (group A, C(2,2)=1 pair)
        // 10 → diff=9, 20 → diff=18 (groups B & C, no pairs within each)
        // Total = 1
        assertEquals(1, countNicePairs.solution(new int[]{1, 2, 10, 20}));
    }

    @Test
    @DisplayName("Two balanced groups: [10, 10, 20, 20] → 2")
    void testTwoBalancedGroups() {
        // 10 → diff=9  (×2 → C(2,2)=1 pair)
        // 20 → diff=18 (×2 → C(2,2)=1 pair)
        // Total = 2
        assertEquals(2, countNicePairs.solution(new int[]{10, 10, 20, 20}));
    }

    @Test
    @DisplayName("Mixed group sizes: [1, 2, 3, 10, 10] → 4")
    void testMixedGroupSizes() {
        // 1,2,3 → diff=0 (×3 → C(3,2)=3 pairs)
        // 10,10 → diff=9 (×2 → C(2,2)=1 pair)
        // Total = 4
        assertEquals(4, countNicePairs.solution(new int[]{1, 2, 3, 10, 10}));
    }

    // ── Repeated same-diff numbers (non-palindrome) ───────────────────────────

    @Test
    @DisplayName("Three identical non-palindromes: [12, 12, 12] → 3")
    void testThreeIdenticalNonPalindromes() {
        // rev(12)=21 → diff = -9, all same → C(3,2) = 3
        assertEquals(3, countNicePairs.solution(new int[]{12, 12, 12}));
    }

    @Test
    @DisplayName("Same diff, different numbers: [12, 23, 34] → 3")
    void testSameDiffDifferentNumbers() {
        // rev(12)=21 → diff = 12-21 = -9
        // rev(23)=32 → diff = 23-32 = -9
        // rev(34)=43 → diff = 34-43 = -9
        // All same diff → C(3,2) = 3
        assertEquals(3, countNicePairs.solution(new int[]{12, 23, 34}));
    }

    // ── Repunit numbers (all 1s) ──────────────────────────────────────────────

    @Test
    @DisplayName("Repunit numbers all have diff=0: [1, 11, 111, 1111] → 6")
    void testRepunitNumbers() {
        // rev(1)    = 1    → diff = 0
        // rev(11)   = 11   → diff = 0
        // rev(111)  = 111  → diff = 0
        // rev(1111) = 1111 → diff = 0
        // All same diff → C(4,2) = 6
        assertEquals(6, countNicePairs.solution(new int[]{1, 11, 111, 1111}));
    }

    // ── Boundary: minimum and maximum constraint values ───────────────────────

    @Test
    @DisplayName("Minimum constraint: single element [0] → 0")
    void testMinConstraint() {
        assertEquals(0, countNicePairs.solution(new int[]{0}));
    }

    @Test
    @DisplayName("Maximum constraint value alone: [1000000000] → 0")
    void testMaxConstraintSingleElement() {
        assertEquals(0, countNicePairs.solution(new int[]{1000000000}));
    }

    @Test
    @DisplayName("Two max-constraint values form a nice pair: [1000000000, 1000000000] → 1")
    void testTwoMaxConstraintValues() {
        // Both have identical diff → 1 nice pair
        assertEquals(1, countNicePairs.solution(new int[]{1000000000, 1000000000}));
    }
}
