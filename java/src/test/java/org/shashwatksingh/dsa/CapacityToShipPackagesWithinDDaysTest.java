package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Capacity To Ship Packages Within D Days Tests")
class CapacityToShipPackagesWithinDDaysTest {

    private CapacityToShipPackagesWithinDDays instance;

    @BeforeEach
    void setUp() {
        instance = new CapacityToShipPackagesWithinDDays();
    }

    // ═══════════════════════════════════════════════════════════
    //  shipWithinDays()  — binary search on capacity, O(n log(sum))
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("shipWithinDays() — least ship capacity to ship all packages within days")
    class ShipWithinDaysTests {

        @Test
        @DisplayName("LeetCode Example 1: [1..10], days=5 → 15")
        void testLeetCodeExample1() {
            assertEquals(15, instance.shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
        }

        @Test
        @DisplayName("LeetCode Example 2: [3,2,2,4,1,4], days=3 → 6")
        void testLeetCodeExample2() {
            assertEquals(6, instance.shipWithinDays(new int[]{3, 2, 2, 4, 1, 4}, 3));
        }

        @Test
        @DisplayName("LeetCode Example 3: [1,2,3,1,1], days=4 → 3")
        void testLeetCodeExample3() {
            assertEquals(3, instance.shipWithinDays(new int[]{1, 2, 3, 1, 1}, 4));
        }

        @Test
        @DisplayName("Single package: [5], days=1 → 5")
        void testSinglePackage() {
            assertEquals(5, instance.shipWithinDays(new int[]{5}, 1));
        }

        @Test
        @DisplayName("Single day: [1,2,3], days=1 → 6 (sum of all weights)")
        void testSingleDay() {
            assertEquals(6, instance.shipWithinDays(new int[]{1, 2, 3}, 1));
        }

        @Test
        @DisplayName("One package per day: [10,50,5,1], days=4 → 50 (max weight)")
        void testDaysEqualsLength() {
            assertEquals(50, instance.shipWithinDays(new int[]{10, 50, 5, 1}, 4));
        }

        @Test
        @DisplayName("Uniform weights: [4,4,4,4], days=2 → 8")
        void testUniformTwoDays() {
            assertEquals(8, instance.shipWithinDays(new int[]{4, 4, 4, 4}, 2));
        }

        @Test
        @DisplayName("Uniform weights: [4,4,4,4], days=3 → 8 (one day must carry two packages)")
        void testUniformThreeDays() {
            assertEquals(8, instance.shipWithinDays(new int[]{4, 4, 4, 4}, 3));
        }

        @Test
        @DisplayName("Uniform weights: [4,4,4,4], days=4 → 4")
        void testUniformFourDays() {
            assertEquals(4, instance.shipWithinDays(new int[]{4, 4, 4, 4}, 4));
        }

        @Test
        @DisplayName("Heaviest package first: [10,1,1,1], days=2 → 10")
        void testHeaviestAtStart() {
            assertEquals(10, instance.shipWithinDays(new int[]{10, 1, 1, 1}, 2));
        }

        @Test
        @DisplayName("Heaviest package last: [1,1,1,10], days=2 → 10")
        void testHeaviestAtEnd() {
            assertEquals(10, instance.shipWithinDays(new int[]{1, 1, 1, 10}, 2));
        }

        @Test
        @DisplayName("Order matters (no reordering): [7,2,5,10,8], days=2 → 18")
        void testOrderMatters() {
            assertEquals(18, instance.shipWithinDays(new int[]{7, 2, 5, 10, 8}, 2));
        }

        @Test
        @DisplayName("Increasing weights: [1,2,3,4,5], days=2 → 9")
        void testIncreasingTwoDays() {
            assertEquals(9, instance.shipWithinDays(new int[]{1, 2, 3, 4, 5}, 2));
        }

        @Test
        @DisplayName("Increasing weights: [1,2,3,4,5], days=3 → 6")
        void testIncreasingThreeDays() {
            assertEquals(6, instance.shipWithinDays(new int[]{1, 2, 3, 4, 5}, 3));
        }

        @Test
        @DisplayName("Alternating light/heavy: [1,9,1,9,1,9], days=3 → 10")
        void testAlternatingWeights() {
            assertEquals(10, instance.shipWithinDays(new int[]{1, 9, 1, 9, 1, 9}, 3));
        }

        @Test
        @DisplayName("Max constraint boundary: n=50000 all 500, days=1 → 25000000")
        void testMaxBoundarySingleDay() {
            int[] weights = new int[50000];
            Arrays.fill(weights, 500);
            assertEquals(25000000, instance.shipWithinDays(weights, 1));
        }

        @Test
        @DisplayName("Max constraint boundary: n=50000 all 500, days=25000 → 1000")
        void testMaxBoundaryHalfDays() {
            int[] weights = new int[50000];
            Arrays.fill(weights, 500);
            assertEquals(1000, instance.shipWithinDays(weights, 25000));
        }

        @Test
        @DisplayName("Max constraint boundary: n=50000 all 500, days=50000 → 500")
        void testMaxBoundaryDaysEqualsLength() {
            int[] weights = new int[50000];
            Arrays.fill(weights, 500);
            assertEquals(500, instance.shipWithinDays(weights, 50000));
        }
    }
}
