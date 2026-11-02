package org.shashwatksingh.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("Prime Factorisation Tests")
class PrimeFactorOfNumberTest {

    private PrimeFactorOfNumber pf;

    @BeforeEach
    void setUp() {
        pf = new PrimeFactorOfNumber();
    }

    // ═══════════════════════════════════════════════════════════
    //  primeFactor()  — map of prime → exponent, O(sqrt(x))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("primeFactor() — prime → exponent map via trial division")
    class PrimeFactorTests {

        @Test
        @DisplayName("Scenario: 15 → {3=1, 5=1} (example from main)")
        void testExampleFifteen() {
            assertEquals(Map.of(3, 1, 5, 1), pf.primeFactor(15));
        }

        @Test
        @DisplayName("Scenario: 40 → {2=3, 5=1} (even number with repeated 2)")
        void testFortyRepeatedTwo() {
            assertEquals(Map.of(2, 3, 5, 1), pf.primeFactor(40));
        }

        @Test
        @DisplayName("Scenario: 1 → {} (minimum input, no prime factors)")
        void testOne() {
            assertEquals(Map.of(), pf.primeFactor(1));
        }

        @Test
        @DisplayName("Scenario: 2 → {2=1} (smallest prime)")
        void testTwo() {
            assertEquals(Map.of(2, 1), pf.primeFactor(2));
        }

        @Test
        @DisplayName("Scenario: 3 → {3=1} (smallest odd prime)")
        void testThree() {
            assertEquals(Map.of(3, 1), pf.primeFactor(3));
        }

        @Test
        @DisplayName("Scenario: 4 → {2=2} (square of 2)")
        void testFour() {
            assertEquals(Map.of(2, 2), pf.primeFactor(4));
        }

        @Test
        @DisplayName("Scenario: 7 → {7=1} (odd prime)")
        void testSeven() {
            assertEquals(Map.of(7, 1), pf.primeFactor(7));
        }

        @Test
        @DisplayName("Scenario: 64 → {2=6} (pure power of two)")
        void testPowerOfTwo() {
            assertEquals(Map.of(2, 6), pf.primeFactor(64));
        }

        @Test
        @DisplayName("Scenario: 81 → {3=4} (pure power of an odd prime)")
        void testPowerOfThree() {
            assertEquals(Map.of(3, 4), pf.primeFactor(81));
        }

        @Test
        @DisplayName("Scenario: 49 → {7=2} (square of an odd prime)")
        void testSquareOfSeven() {
            assertEquals(Map.of(7, 2), pf.primeFactor(49));
        }

        @Test
        @DisplayName("Scenario: 12 → {2=2, 3=1} (more 2s than 3s)")
        void testTwelve() {
            assertEquals(Map.of(2, 2, 3, 1), pf.primeFactor(12));
        }

        @Test
        @DisplayName("Scenario: 18 → {2=1, 3=2} (more 3s than 2s, asymmetric)")
        void testEighteen() {
            assertEquals(Map.of(2, 1, 3, 2), pf.primeFactor(18));
        }

        @Test
        @DisplayName("Scenario: 100 → {2=2, 5=2} (two squared primes)")
        void testHundred() {
            assertEquals(Map.of(2, 2, 5, 2), pf.primeFactor(100));
        }

        @Test
        @DisplayName("Scenario: 360 → {2=3, 3=2, 5=1} (three distinct primes, mixed exponents)")
        void testThreeSixty() {
            assertEquals(Map.of(2, 3, 3, 2, 5, 1), pf.primeFactor(360));
        }

        @Test
        @DisplayName("Scenario: 1001 → {7=1, 11=1, 13=1} (product of three odd primes)")
        void testThousandOne() {
            assertEquals(Map.of(7, 1, 11, 1, 13, 1), pf.primeFactor(1001));
        }

        @Test
        @DisplayName("Scenario: 9973 → {9973=1} (largest prime below 10000)")
        void testLargePrimeBelowTenThousand() {
            assertEquals(Map.of(9973, 1), pf.primeFactor(9973));
        }

        @Test
        @DisplayName("Scenario: 1000000 → {2=6, 5=6} (large round number)")
        void testMillion() {
            assertEquals(Map.of(2, 6, 5, 6), pf.primeFactor(1_000_000));
        }

        @Test
        @DisplayName("Scenario: 999999 → {3=3, 7=1, 11=1, 13=1, 37=1} (large odd composite)")
        void testNineNineNine() {
            assertEquals(Map.of(3, 3, 7, 1, 11, 1, 13, 1, 37, 1), pf.primeFactor(999_999));
        }

        @Test
        @DisplayName("Scenario: 1073741824 (2^30) → {2=30} (largest power of two in int)")
        void testTwoToThirty() {
            assertEquals(Map.of(2, 30), pf.primeFactor(1_073_741_824));
        }

        @Test
        @DisplayName("Scenario: 1162261467 (3^19) → {3=19} (largest power of three in int)")
        void testThreeToNineteen() {
            assertEquals(Map.of(3, 19), pf.primeFactor(1_162_261_467));
        }

        @Test
        @DisplayName("Scenario: 2147395600 (46340^2) → {2=4, 5=2, 7=2, 331=2} (large perfect square)")
        void testLargePerfectSquare() {
            assertEquals(Map.of(2, 4, 5, 2, 7, 2, 331, 2), pf.primeFactor(2_147_395_600));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: 0 → {} (zero has no prime factorisation)")
        void testZero() {
            assertEquals(Map.of(), pf.primeFactor(0));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: -1 → {} (negative, no prime factorisation)")
        void testMinusOne() {
            assertEquals(Map.of(), pf.primeFactor(-1));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: -2 → {} (negative of a prime)")
        void testMinusTwo() {
            assertEquals(Map.of(), pf.primeFactor(-2));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: -15 → {} (negative odd composite)")
        void testMinusFifteen() {
            assertEquals(Map.of(), pf.primeFactor(-15));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: -40 → {} (negative even composite)")
        void testMinusForty() {
            assertEquals(Map.of(), pf.primeFactor(-40));
        }

        @Test
        @Timeout(5)
        @DisplayName("Scenario: Integer.MIN_VALUE → {} (min boundary)")
        void testMinValue() {
            assertEquals(Map.of(), pf.primeFactor(Integer.MIN_VALUE));
        }

        @Test
        @DisplayName("Scenario: Integer.MAX_VALUE → {2147483647=1} (max boundary, Mersenne prime)")
        void testMaxValuePrime() {
            assertEquals(Map.of(Integer.MAX_VALUE, 1), pf.primeFactor(Integer.MAX_VALUE));
        }
    }
}
