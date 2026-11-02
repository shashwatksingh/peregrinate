package org.shashwatksingh.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Least Common Multiple Tests")
class LCMTest {

    private LCM lcm;

    @BeforeEach
    void setUp() {
        lcm = new LCM();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — count up from max(x, y) until both divide, O(lcm(x, y))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — increment from max(x, y) until divisible by both")
    class BruteForceTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 120 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(120, lcm.bruteForce(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 30 (one divides the other)")
        void testOneDividesOther() {
            assertEquals(30, lcm.bruteForce(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, lcm.bruteForce(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal values)")
        void testEqualValues() {
            assertEquals(7, lcm.bruteForce(7, 7));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 221 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(221, lcm.bruteForce(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 18 (smaller first, divides larger)")
        void testSmallerFirst() {
            assertEquals(18, lcm.bruteForce(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 18 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(18, lcm.bruteForce(18, 6));
        }

        @Test
        @DisplayName("Scenario: (12, 18) → 36 (shared factor, neither divides the other)")
        void testSharedFactor() {
            assertEquals(36, lcm.bruteForce(12, 18));
        }

        @Test
        @DisplayName("Scenario: (21, 6) → 42 (larger first, shared factor 3)")
        void testLargerFirstSharedFactor() {
            assertEquals(42, lcm.bruteForce(21, 6));
        }

        @Test
        @DisplayName("Scenario: (8, 32) → 32 (powers of two)")
        void testPowersOfTwo() {
            assertEquals(32, lcm.bruteForce(8, 32));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 99 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(99, lcm.bruteForce(1, 99));
        }

        @Test
        @DisplayName("Scenario: (1000, 999) → 999000 (larger consecutive, coprime)")
        void testLargerConsecutive() {
            assertEquals(999_000, lcm.bruteForce(1000, 999));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE) → Integer.MAX_VALUE (max boundary, equal)")
        void testMaxBoundaryEqual() {
            assertEquals(Integer.MAX_VALUE, lcm.bruteForce(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, 1) → Integer.MAX_VALUE (max boundary, with 1)")
        void testMaxBoundaryWithOne() {
            assertEquals(Integer.MAX_VALUE, lcm.bruteForce(Integer.MAX_VALUE, 1));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  usingGCD()  — x / gcd(x, y) * y, O(log(min(x, y)))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("usingGCD() — LCM via x / gcd(x, y) * y")
    class UsingGCDTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 120 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(120, lcm.usingGCD(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 30 (one divides the other)")
        void testOneDividesOther() {
            assertEquals(30, lcm.usingGCD(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, lcm.usingGCD(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal values)")
        void testEqualValues() {
            assertEquals(7, lcm.usingGCD(7, 7));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 221 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(221, lcm.usingGCD(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 18 (smaller first, divides larger)")
        void testSmallerFirst() {
            assertEquals(18, lcm.usingGCD(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 18 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(18, lcm.usingGCD(18, 6));
        }

        @Test
        @DisplayName("Scenario: (12, 18) → 36 (shared factor, neither divides the other)")
        void testSharedFactor() {
            assertEquals(36, lcm.usingGCD(12, 18));
        }

        @Test
        @DisplayName("Scenario: (21, 6) → 42 (larger first, shared factor 3)")
        void testLargerFirstSharedFactor() {
            assertEquals(42, lcm.usingGCD(21, 6));
        }

        @Test
        @DisplayName("Scenario: (100, 75) → 300 (shared factor 25)")
        void testSharedFactorTwentyFive() {
            assertEquals(300, lcm.usingGCD(100, 75));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 99 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(99, lcm.usingGCD(1, 99));
        }

        @Test
        @DisplayName("Scenario: (0, 5) → 0 (zero operand)")
        void testZeroFirst() {
            assertEquals(0, lcm.usingGCD(0, 5));
        }

        @Test
        @DisplayName("Scenario: (5, 0) → 0 (zero operand)")
        void testZeroSecond() {
            assertEquals(0, lcm.usingGCD(5, 0));
        }

        @Test
        @DisplayName("Scenario: (46340, 46341) → 2147441940 (largest consecutive pair whose product fits in int)")
        void testLargeConsecutiveCoprime() {
            assertEquals(2_147_441_940, lcm.usingGCD(46_340, 46_341));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE) → Integer.MAX_VALUE (max boundary, equal)")
        void testMaxBoundaryEqual() {
            assertEquals(Integer.MAX_VALUE, lcm.usingGCD(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, 1) → Integer.MAX_VALUE (max boundary, with 1)")
        void testMaxBoundaryWithOne() {
            assertEquals(Integer.MAX_VALUE, lcm.usingGCD(Integer.MAX_VALUE, 1));
        }

        @Test
        @DisplayName("Scenario: (1073741823, 2) → 2147483646 (max boundary, coprime odd × 2)")
        void testMaxBoundaryCoprimeProduct() {
            assertEquals(2_147_483_646, lcm.usingGCD(1_073_741_823, 2));
        }
    }
}
