package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Decode Ways Tests")
class DecodeWaysTest {

    private DecodeWays decodeWays;

    @BeforeEach
    void setUp() {
        decodeWays = new DecodeWays();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — plain recursion, O(2^n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — plain recursion O(2^n)")
    class BruteForceTests {

        @Test
        @DisplayName("Single non-zero digit: \"1\" → 1")
        void testSingleNonZeroDigit() {
            assertEquals(1, decodeWays.bruteForce("1"));
        }

        @Test
        @DisplayName("Single non-zero digit at boundary: \"9\" → 1")
        void testSingleNonZeroDigitBoundary() {
            assertEquals(1, decodeWays.bruteForce("9"));
        }

        @Test
        @DisplayName("Single zero: \"0\" → 0 (cannot be decoded)")
        void testSingleZero() {
            assertEquals(0, decodeWays.bruteForce("0"));
        }

        @Test
        @DisplayName("LeetCode Example 1: \"12\" → 2 (\"AB\" or \"L\")")
        void testExample1() {
            assertEquals(2, decodeWays.bruteForce("12"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"226\" → 3 (\"BZ\", \"VF\", or \"BBF\")")
        void testExample2() {
            assertEquals(3, decodeWays.bruteForce("226"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"06\" → 0 (leading zero, invalid)")
        void testExample3LeadingZero() {
            assertEquals(0, decodeWays.bruteForce("06"));
        }

        @Test
        @DisplayName("From main: \"11106\" → 2 (\"AAJF\" or \"KJF\")")
        void testFromMain() {
            assertEquals(2, decodeWays.bruteForce("11106"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"10\" → 1 (only \"J\")")
        void testValidTwoDigitEndingZero10() {
            assertEquals(1, decodeWays.bruteForce("10"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"20\" → 1 (only \"T\")")
        void testValidTwoDigitEndingZero20() {
            assertEquals(1, decodeWays.bruteForce("20"));
        }

        @Test
        @DisplayName("Invalid two-digit > 26 ending in zero: \"30\" → 0")
        void testInvalidTwoDigitEndingZero30() {
            assertEquals(0, decodeWays.bruteForce("30"));
        }

        @Test
        @DisplayName("Cascading zeros: \"100\" → 0 (no valid decoding)")
        void testCascadingZeros() {
            assertEquals(0, decodeWays.bruteForce("100"));
        }

        @Test
        @DisplayName("Two-digit > 26 — only single path: \"27\" → 1 (\"BG\")")
        void testTwoDigitGreaterThan26() {
            assertEquals(1, decodeWays.bruteForce("27"));
        }

        @Test
        @DisplayName("Triple ones: \"111\" → 3 (1+1+1, 11+1, 1+11)")
        void testTripleOnes() {
            assertEquals(3, decodeWays.bruteForce("111"));
        }

        @Test
        @DisplayName("Ten ones: \"1111111111\" → 89 (Fibonacci-like growth)")
        void testTenOnes() {
            assertEquals(89, decodeWays.bruteForce("1111111111"));
        }

        @Test
        @DisplayName("Ten nines: \"9999999999\" → 1 (99 > 26, only single-digit path)")
        void testTenNines() {
            assertEquals(1, decodeWays.bruteForce("9999999999"));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  topDownWithMemoization()  — top-down DP, O(n) time & space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("topDownWithMemoization() — top-down DP O(n)")
    class TopDownWithMemoizationTests {

        @Test
        @DisplayName("Single non-zero digit: \"1\" → 1")
        void testSingleNonZeroDigit() {
            assertEquals(1, decodeWays.topDownWithMemoization("1"));
        }

        @Test
        @DisplayName("Single non-zero digit at boundary: \"9\" → 1")
        void testSingleNonZeroDigitBoundary() {
            assertEquals(1, decodeWays.topDownWithMemoization("9"));
        }

        @Test
        @DisplayName("Single zero: \"0\" → 0 (cannot be decoded)")
        void testSingleZero() {
            assertEquals(0, decodeWays.topDownWithMemoization("0"));
        }

        @Test
        @DisplayName("LeetCode Example 1: \"12\" → 2 (\"AB\" or \"L\")")
        void testExample1() {
            assertEquals(2, decodeWays.topDownWithMemoization("12"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"226\" → 3 (\"BZ\", \"VF\", or \"BBF\")")
        void testExample2() {
            assertEquals(3, decodeWays.topDownWithMemoization("226"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"06\" → 0 (leading zero, invalid)")
        void testExample3LeadingZero() {
            assertEquals(0, decodeWays.topDownWithMemoization("06"));
        }

        @Test
        @DisplayName("From main: \"11106\" → 2 (\"AAJF\" or \"KJF\")")
        void testFromMain() {
            assertEquals(2, decodeWays.topDownWithMemoization("11106"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"10\" → 1 (only \"J\")")
        void testValidTwoDigitEndingZero10() {
            assertEquals(1, decodeWays.topDownWithMemoization("10"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"20\" → 1 (only \"T\")")
        void testValidTwoDigitEndingZero20() {
            assertEquals(1, decodeWays.topDownWithMemoization("20"));
        }

        @Test
        @DisplayName("Invalid two-digit > 26 ending in zero: \"30\" → 0")
        void testInvalidTwoDigitEndingZero30() {
            assertEquals(0, decodeWays.topDownWithMemoization("30"));
        }

        @Test
        @DisplayName("Cascading zeros: \"100\" → 0 (no valid decoding)")
        void testCascadingZeros() {
            assertEquals(0, decodeWays.topDownWithMemoization("100"));
        }

        @Test
        @DisplayName("Two-digit > 26 — only single path: \"27\" → 1 (\"BG\")")
        void testTwoDigitGreaterThan26() {
            assertEquals(1, decodeWays.topDownWithMemoization("27"));
        }

        @Test
        @DisplayName("Triple ones: \"111\" → 3 (1+1+1, 11+1, 1+11)")
        void testTripleOnes() {
            assertEquals(3, decodeWays.topDownWithMemoization("111"));
        }

        @Test
        @DisplayName("Ten ones: \"1111111111\" → 89 (Fibonacci-like growth)")
        void testTenOnes() {
            assertEquals(89, decodeWays.topDownWithMemoization("1111111111"));
        }

        @Test
        @DisplayName("Ten nines: \"9999999999\" → 1 (99 > 26, only single-digit path)")
        void testTenNines() {
            assertEquals(1, decodeWays.topDownWithMemoization("9999999999"));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpTabulation()  — bottom-up DP, O(n) time, O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpTabulation() — bottom-up DP O(n) time, O(n) space")
    class BottomUpTabulationTests {

        @Test
        @DisplayName("Single non-zero digit: \"1\" → 1")
        void testSingleNonZeroDigit() {
            assertEquals(1, decodeWays.bottomUpTabulation("1"));
        }

        @Test
        @DisplayName("Single non-zero digit at boundary: \"9\" → 1")
        void testSingleNonZeroDigitBoundary() {
            assertEquals(1, decodeWays.bottomUpTabulation("9"));
        }

        @Test
        @DisplayName("Single zero: \"0\" → 0 (cannot be decoded)")
        void testSingleZero() {
            assertEquals(0, decodeWays.bottomUpTabulation("0"));
        }

        @Test
        @DisplayName("LeetCode Example 1: \"12\" → 2 (\"AB\" or \"L\")")
        void testExample1() {
            assertEquals(2, decodeWays.bottomUpTabulation("12"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"226\" → 3 (\"BZ\", \"VF\", or \"BBF\")")
        void testExample2() {
            assertEquals(3, decodeWays.bottomUpTabulation("226"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"06\" → 0 (leading zero, invalid)")
        void testExample3LeadingZero() {
            assertEquals(0, decodeWays.bottomUpTabulation("06"));
        }

        @Test
        @DisplayName("From main: \"11106\" → 2 (\"AAJF\" or \"KJF\")")
        void testFromMain() {
            assertEquals(2, decodeWays.bottomUpTabulation("11106"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"10\" → 1 (only \"J\")")
        void testValidTwoDigitEndingZero10() {
            assertEquals(1, decodeWays.bottomUpTabulation("10"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"20\" → 1 (only \"T\")")
        void testValidTwoDigitEndingZero20() {
            assertEquals(1, decodeWays.bottomUpTabulation("20"));
        }

        @Test
        @DisplayName("Invalid two-digit > 26 ending in zero: \"30\" → 0")
        void testInvalidTwoDigitEndingZero30() {
            assertEquals(0, decodeWays.bottomUpTabulation("30"));
        }

        @Test
        @DisplayName("Cascading zeros: \"100\" → 0 (no valid decoding)")
        void testCascadingZeros() {
            assertEquals(0, decodeWays.bottomUpTabulation("100"));
        }

        @Test
        @DisplayName("Two-digit > 26 — only single path: \"27\" → 1 (\"BG\")")
        void testTwoDigitGreaterThan26() {
            assertEquals(1, decodeWays.bottomUpTabulation("27"));
        }

        @Test
        @DisplayName("Triple ones: \"111\" → 3 (1+1+1, 11+1, 1+11)")
        void testTripleOnes() {
            assertEquals(3, decodeWays.bottomUpTabulation("111"));
        }

        @Test
        @DisplayName("Ten ones: \"1111111111\" → 89 (Fibonacci-like growth)")
        void testTenOnes() {
            assertEquals(89, decodeWays.bottomUpTabulation("1111111111"));
        }

        @Test
        @DisplayName("Ten nines: \"9999999999\" → 1 (99 > 26, only single-digit path)")
        void testTenNines() {
            assertEquals(1, decodeWays.bottomUpTabulation("9999999999"));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  bottomUpSpaceOptimization()  — O(n) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bottomUpSpaceOptimization() — O(n) time, O(1) space")
    class BottomUpSpaceOptimizationTests {

        @Test
        @DisplayName("Single non-zero digit: \"1\" → 1")
        void testSingleNonZeroDigit() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("1"));
        }

        @Test
        @DisplayName("Single non-zero digit at boundary: \"9\" → 1")
        void testSingleNonZeroDigitBoundary() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("9"));
        }

        @Test
        @DisplayName("Single zero: \"0\" → 0 (cannot be decoded)")
        void testSingleZero() {
            assertEquals(0, decodeWays.bottomUpSpaceOptimization("0"));
        }

        @Test
        @DisplayName("LeetCode Example 1: \"12\" → 2 (\"AB\" or \"L\")")
        void testExample1() {
            assertEquals(2, decodeWays.bottomUpSpaceOptimization("12"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"226\" → 3 (\"BZ\", \"VF\", or \"BBF\")")
        void testExample2() {
            assertEquals(3, decodeWays.bottomUpSpaceOptimization("226"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"06\" → 0 (leading zero, invalid)")
        void testExample3LeadingZero() {
            assertEquals(0, decodeWays.bottomUpSpaceOptimization("06"));
        }

        @Test
        @DisplayName("From main: \"11106\" → 2 (\"AAJF\" or \"KJF\")")
        void testFromMain() {
            assertEquals(2, decodeWays.bottomUpSpaceOptimization("11106"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"10\" → 1 (only \"J\")")
        void testValidTwoDigitEndingZero10() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("10"));
        }

        @Test
        @DisplayName("Valid two-digit ending in zero: \"20\" → 1 (only \"T\")")
        void testValidTwoDigitEndingZero20() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("20"));
        }

        @Test
        @DisplayName("Invalid two-digit > 26 ending in zero: \"30\" → 0")
        void testInvalidTwoDigitEndingZero30() {
            assertEquals(0, decodeWays.bottomUpSpaceOptimization("30"));
        }

        @Test
        @DisplayName("Cascading zeros: \"100\" → 0 (no valid decoding)")
        void testCascadingZeros() {
            assertEquals(0, decodeWays.bottomUpSpaceOptimization("100"));
        }

        @Test
        @DisplayName("Two-digit > 26 — only single path: \"27\" → 1 (\"BG\")")
        void testTwoDigitGreaterThan26() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("27"));
        }

        @Test
        @DisplayName("Triple ones: \"111\" → 3 (1+1+1, 11+1, 1+11)")
        void testTripleOnes() {
            assertEquals(3, decodeWays.bottomUpSpaceOptimization("111"));
        }

        @Test
        @DisplayName("Ten ones: \"1111111111\" → 89 (Fibonacci-like growth)")
        void testTenOnes() {
            assertEquals(89, decodeWays.bottomUpSpaceOptimization("1111111111"));
        }

        @Test
        @DisplayName("Ten nines: \"9999999999\" → 1 (99 > 26, only single-digit path)")
        void testTenNines() {
            assertEquals(1, decodeWays.bottomUpSpaceOptimization("9999999999"));
        }
    }
}
