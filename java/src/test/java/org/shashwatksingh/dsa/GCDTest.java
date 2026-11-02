package org.shashwatksingh.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Greatest Common Divisor Tests")
class GCDTest {

    private GCD gcd;

    @BeforeEach
    void setUp() {
        gcd = new GCD();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — try every candidate up to min(x, y), O(min(x, y))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — iterate candidates up to min(x, y)")
    class BruteForceTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 5 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(5, gcd.bruteForce(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 15 (one divides the other)")
        void testExampleFifteenThirty() {
            assertEquals(15, gcd.bruteForce(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, gcd.bruteForce(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal values)")
        void testEqualValues() {
            assertEquals(7, gcd.bruteForce(7, 7));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 1 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(1, gcd.bruteForce(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 6 (smaller first, divides larger)")
        void testSmallerDividesLarger() {
            assertEquals(6, gcd.bruteForce(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 6 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(6, gcd.bruteForce(18, 6));
        }

        @Test
        @DisplayName("Scenario: (12, 18) → 6 (asymmetric prime powers)")
        void testAsymmetricPrimePowers() {
            assertEquals(6, gcd.bruteForce(12, 18));
        }

        @Test
        @DisplayName("Scenario: (8, 32) → 8 (powers of two)")
        void testPowersOfTwo() {
            assertEquals(8, gcd.bruteForce(8, 32));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 1 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(1, gcd.bruteForce(1, 99));
        }

        @Test
        @DisplayName("Scenario: (1000000, 999999) → 1 (large consecutive, coprime)")
        void testLargeConsecutive() {
            assertEquals(1, gcd.bruteForce(1_000_000, 999_999));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  primeFactorisation()  — min-exponent of shared primes, ~O(sqrt(x) + sqrt(y))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("primeFactorisation() — product of shared primes at min exponent")
    class PrimeFactorisationTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 5 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(5, gcd.primeFactorisation(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 15 (one divides the other)")
        void testExampleFifteenThirty() {
            assertEquals(15, gcd.primeFactorisation(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, gcd.primeFactorisation(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal primes)")
        void testEqualPrimes() {
            assertEquals(7, gcd.primeFactorisation(7, 7));
        }

        @Test
        @DisplayName("Scenario: (12, 12) → 12 (equal composites with repeated factors)")
        void testEqualComposites() {
            assertEquals(12, gcd.primeFactorisation(12, 12));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 1 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(1, gcd.primeFactorisation(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 6 (smaller first, divides larger)")
        void testSmallerDividesLarger() {
            assertEquals(6, gcd.primeFactorisation(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 6 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(6, gcd.primeFactorisation(18, 6));
        }

        @Test
        @DisplayName("Scenario: (12, 18) → 6 (asymmetric prime powers: 2^2*3 vs 2*3^2)")
        void testAsymmetricPrimePowers() {
            assertEquals(6, gcd.primeFactorisation(12, 18));
        }

        @Test
        @DisplayName("Scenario: (8, 32) → 8 (powers of two)")
        void testPowersOfTwo() {
            assertEquals(8, gcd.primeFactorisation(8, 32));
        }

        @Test
        @DisplayName("Scenario: (100, 75) → 25 (shared squared prime 5^2)")
        void testSharedSquaredPrime() {
            assertEquals(25, gcd.primeFactorisation(100, 75));
        }

        @Test
        @DisplayName("Scenario: (48, 180) → 12 (multiple shared primes with differing exponents)")
        void testMultipleSharedPrimes() {
            assertEquals(12, gcd.primeFactorisation(48, 180));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 1 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(1, gcd.primeFactorisation(1, 99));
        }

        @Test
        @DisplayName("Scenario: (1000000, 999999) → 1 (large consecutive, coprime)")
        void testLargeConsecutive() {
            assertEquals(1, gcd.primeFactorisation(1_000_000, 999_999));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  euclideanMethod()  — iterative remainder reduction, O(log(min(x, y)))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("euclideanMethod() — iterative Euclidean algorithm")
    class EuclideanMethodTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 5 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(5, gcd.euclideanMethod(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 15 (one divides the other)")
        void testExampleFifteenThirty() {
            assertEquals(15, gcd.euclideanMethod(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, gcd.euclideanMethod(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal values)")
        void testEqualValues() {
            assertEquals(7, gcd.euclideanMethod(7, 7));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 1 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(1, gcd.euclideanMethod(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 6 (smaller first, divides larger)")
        void testSmallerDividesLarger() {
            assertEquals(6, gcd.euclideanMethod(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 6 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(6, gcd.euclideanMethod(18, 6));
        }

        @Test
        @DisplayName("Scenario: (48, 18) → 6 (multi-step reduction)")
        void testMultiStepReduction() {
            assertEquals(6, gcd.euclideanMethod(48, 18));
        }

        @Test
        @DisplayName("Scenario: (0, 5) → 5 (zero operand, gcd(0, n) = n)")
        void testZeroFirst() {
            assertEquals(5, gcd.euclideanMethod(0, 5));
        }

        @Test
        @DisplayName("Scenario: (5, 0) → 5 (zero operand, gcd(n, 0) = n)")
        void testZeroSecond() {
            assertEquals(5, gcd.euclideanMethod(5, 0));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 1 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(1, gcd.euclideanMethod(1, 99));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE - 1) → 1 (max boundary, consecutive)")
        void testMaxBoundaryConsecutive() {
            assertEquals(1, gcd.euclideanMethod(Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
        }

        @Test
        @DisplayName("Scenario: (2147483646, 1073741823) → 1073741823 (max boundary, exact multiple)")
        void testMaxBoundaryMultiple() {
            assertEquals(1_073_741_823, gcd.euclideanMethod(2_147_483_646, 1_073_741_823));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE) → Integer.MAX_VALUE (max boundary, equal)")
        void testMaxBoundaryEqual() {
            assertEquals(Integer.MAX_VALUE, gcd.euclideanMethod(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  recursiveEuclidean()  — recursive remainder reduction, O(log(min(x, y)))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("recursiveEuclidean() — recursive Euclidean algorithm")
    class RecursiveEuclideanTests {

        @Test
        @DisplayName("Scenario: (15, 40) → 5 (example from main)")
        void testExampleFifteenForty() {
            assertEquals(5, gcd.recursiveEuclidean(15, 40));
        }

        @Test
        @DisplayName("Scenario: (15, 30) → 15 (one divides the other)")
        void testExampleFifteenThirty() {
            assertEquals(15, gcd.recursiveEuclidean(15, 30));
        }

        @Test
        @DisplayName("Scenario: (1, 1) → 1 (minimum-size input)")
        void testMinimumInput() {
            assertEquals(1, gcd.recursiveEuclidean(1, 1));
        }

        @Test
        @DisplayName("Scenario: (7, 7) → 7 (equal values)")
        void testEqualValues() {
            assertEquals(7, gcd.recursiveEuclidean(7, 7));
        }

        @Test
        @DisplayName("Scenario: (13, 17) → 1 (coprime primes)")
        void testCoprimePrimes() {
            assertEquals(1, gcd.recursiveEuclidean(13, 17));
        }

        @Test
        @DisplayName("Scenario: (6, 18) → 6 (smaller first, divides larger)")
        void testSmallerDividesLarger() {
            assertEquals(6, gcd.recursiveEuclidean(6, 18));
        }

        @Test
        @DisplayName("Scenario: (18, 6) → 6 (reversed argument order)")
        void testReversedOrder() {
            assertEquals(6, gcd.recursiveEuclidean(18, 6));
        }

        @Test
        @DisplayName("Scenario: (48, 18) → 6 (multi-step reduction)")
        void testMultiStepReduction() {
            assertEquals(6, gcd.recursiveEuclidean(48, 18));
        }

        @Test
        @DisplayName("Scenario: (0, 5) → 5 (zero operand, gcd(0, n) = n)")
        void testZeroFirst() {
            assertEquals(5, gcd.recursiveEuclidean(0, 5));
        }

        @Test
        @DisplayName("Scenario: (5, 0) → 5 (zero operand, gcd(n, 0) = n)")
        void testZeroSecond() {
            assertEquals(5, gcd.recursiveEuclidean(5, 0));
        }

        @Test
        @DisplayName("Scenario: (1, 99) → 1 (one operand is 1)")
        void testOneOperandIsOne() {
            assertEquals(1, gcd.recursiveEuclidean(1, 99));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE - 1) → 1 (max boundary, consecutive)")
        void testMaxBoundaryConsecutive() {
            assertEquals(1, gcd.recursiveEuclidean(Integer.MAX_VALUE, Integer.MAX_VALUE - 1));
        }

        @Test
        @DisplayName("Scenario: (2147483646, 1073741823) → 1073741823 (max boundary, exact multiple)")
        void testMaxBoundaryMultiple() {
            assertEquals(1_073_741_823, gcd.recursiveEuclidean(2_147_483_646, 1_073_741_823));
        }

        @Test
        @DisplayName("Scenario: (Integer.MAX_VALUE, Integer.MAX_VALUE) → Integer.MAX_VALUE (max boundary, equal)")
        void testMaxBoundaryEqual() {
            assertEquals(Integer.MAX_VALUE, gcd.recursiveEuclidean(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }
    }
}
