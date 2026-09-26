package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Boats to Save People Tests")
class BoatsToSavePeopleTest {

    private BoatsToSavePeople instance;

    @BeforeEach
    void setUp() {
        instance = new BoatsToSavePeople();
    }

    // ═══════════════════════════════════════════════════════════
    //  numRescueBoats()  — sort + two-pointer greedy, O(n log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numRescueBoats() — greedy two-pointer, pair heaviest with lightest")
    class NumRescueBoatsTests {

        @Test
        @DisplayName("LeetCode Example 1: people=[1,2], limit=3 → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.numRescueBoats(new int[]{1, 2}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 2: people=[3,2,2,1], limit=3 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.numRescueBoats(new int[]{3, 2, 2, 1}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: people=[3,5,3,4], limit=5 → 4")
        void testLeetCodeExample3() {
            assertEquals(4, instance.numRescueBoats(new int[]{3, 5, 3, 4}, 5));
        }

        @Test
        @DisplayName("Minimum-size input: people=[7], limit=7 → 1 (lone person exactly at the limit)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numRescueBoats(new int[]{7}, 7));
        }

        @Test
        @DisplayName("All-same values, pairable: people=[2,2,2,2], limit=4 → 2 (every pair sums to exactly the limit)")
        void testAllSameValuesPairable() {
            assertEquals(2, instance.numRescueBoats(new int[]{2, 2, 2, 2}, 4));
        }

        @Test
        @DisplayName("All-same values, not pairable: people=[5,5,5], limit=5 → 3 (any two together exceed the limit)")
        void testAllSameValuesNotPairable() {
            assertEquals(3, instance.numRescueBoats(new int[]{5, 5, 5}, 5));
        }

        @Test
        @DisplayName("Exact boundary equality: people=[1,4,2,3], limit=5 → 2 (each pair sums to exactly the limit)")
        void testExactBoundaryEquality() {
            assertEquals(2, instance.numRescueBoats(new int[]{1, 4, 2, 3}, 5));
        }

        @Test
        @DisplayName("Max constraint boundary: 50,000 people all at max weight == max limit → 1 boat per person")
        void testMaxConstraintBoundaryValues() {
            int[] people = new int[50_000];
            Arrays.fill(people, 30_000);
            assertEquals(50_000, instance.numRescueBoats(people, 30_000));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  numRescueBoatsBruteForce()  — exhaustive backtracking, exponential
    //  (only exercised with small inputs — it is not meant to scale)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("numRescueBoatsBruteForce() — exhaustive pairing search, small inputs only")
    class NumRescueBoatsBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: people=[1,2], limit=3 → 1")
        void testLeetCodeExample1() {
            assertEquals(1, instance.numRescueBoatsBruteForce(new int[]{1, 2}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 2: people=[3,2,2,1], limit=3 → 3")
        void testLeetCodeExample2() {
            assertEquals(3, instance.numRescueBoatsBruteForce(new int[]{3, 2, 2, 1}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: people=[3,5,3,4], limit=5 → 4")
        void testLeetCodeExample3() {
            assertEquals(4, instance.numRescueBoatsBruteForce(new int[]{3, 5, 3, 4}, 5));
        }

        @Test
        @DisplayName("Minimum-size input: people=[7], limit=7 → 1 (lone person exactly at the limit)")
        void testMinimumSizeInput() {
            assertEquals(1, instance.numRescueBoatsBruteForce(new int[]{7}, 7));
        }

        @Test
        @DisplayName("All-same values, pairable: people=[2,2,2,2], limit=4 → 2 (every pair sums to exactly the limit)")
        void testAllSameValuesPairable() {
            assertEquals(2, instance.numRescueBoatsBruteForce(new int[]{2, 2, 2, 2}, 4));
        }

        @Test
        @DisplayName("All-same values, not pairable: people=[5,5,5], limit=5 → 3 (any two together exceed the limit)")
        void testAllSameValuesNotPairable() {
            assertEquals(3, instance.numRescueBoatsBruteForce(new int[]{5, 5, 5}, 5));
        }

        @Test
        @DisplayName("Exact boundary equality: people=[1,4,2,3], limit=5 → 2 (each pair sums to exactly the limit)")
        void testExactBoundaryEquality() {
            assertEquals(2, instance.numRescueBoatsBruteForce(new int[]{1, 4, 2, 3}, 5));
        }
    }
}
