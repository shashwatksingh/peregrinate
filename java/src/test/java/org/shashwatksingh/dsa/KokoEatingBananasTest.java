package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Koko Eating Bananas Tests")
class KokoEatingBananasTest {

    private KokoEatingBananas instance;

    @BeforeEach
    void setUp() {
        instance = new KokoEatingBananas();
    }

    // ═══════════════════════════════════════════════════════════
    //  minEatingSpeed()  — minimum bananas-per-hour speed that finishes within h hours
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("minEatingSpeed() — minimum speed that finishes within h hours")
    class MinEatingSpeedTests {

        @Test
        @DisplayName("LeetCode Example 1: [3,6,7,11], h=8 → 4")
        void testLeetCodeExample1() {
            assertEquals(4, instance.minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
        }

        @Test
        @DisplayName("LeetCode Example 2: [30,11,23,4,20], h=5 → 30")
        void testLeetCodeExample2() {
            assertEquals(30, instance.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5));
        }

        @Test
        @DisplayName("LeetCode Example 3: [30,11,23,4,20], h=6 → 23")
        void testLeetCodeExample3() {
            assertEquals(23, instance.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6));
        }

        @Test
        @DisplayName("Single pile, one hour: [10], h=1 → 10")
        void testSinglePileOneHour() {
            assertEquals(10, instance.minEatingSpeed(new int[]{10}, 1));
        }

        @Test
        @DisplayName("Single pile, h equals pile size: [10], h=10 → 1")
        void testSinglePileHEqualsSize() {
            assertEquals(1, instance.minEatingSpeed(new int[]{10}, 10));
        }

        @Test
        @DisplayName("Single pile, half the hours: [10], h=5 → 2")
        void testSinglePileHalfHours() {
            assertEquals(2, instance.minEatingSpeed(new int[]{10}, 5));
        }

        @Test
        @DisplayName("Single pile, remainder rounds up: [10], h=3 → 4")
        void testSinglePileRoundsUp() {
            assertEquals(4, instance.minEatingSpeed(new int[]{10}, 3));
        }

        @Test
        @DisplayName("Single pile of one banana: [1], h=1 → 1")
        void testSinglePileOfOne() {
            assertEquals(1, instance.minEatingSpeed(new int[]{1}, 1));
        }

        @Test
        @DisplayName("All-same piles, h equals pile count: [4,4,4], h=3 → 4")
        void testAllSameHEqualsCount() {
            assertEquals(4, instance.minEatingSpeed(new int[]{4, 4, 4}, 3));
        }

        @Test
        @DisplayName("All-same piles, double the hours: [4,4,4], h=6 → 2")
        void testAllSameDoubleHours() {
            assertEquals(2, instance.minEatingSpeed(new int[]{4, 4, 4}, 6));
        }

        @Test
        @DisplayName("All-same piles, plenty of hours: [4,4,4], h=12 → 1")
        void testAllSamePlentyOfHours() {
            assertEquals(1, instance.minEatingSpeed(new int[]{4, 4, 4}, 12));
        }

        @Test
        @DisplayName("All piles of one banana: [1,1,1,1], h=4 → 1")
        void testAllPilesOfOne() {
            assertEquals(1, instance.minEatingSpeed(new int[]{1, 1, 1, 1}, 4));
        }

        @Test
        @DisplayName("Unsorted piles: [11,7,6,3], h=8 → 4")
        void testUnsortedPiles() {
            assertEquals(4, instance.minEatingSpeed(new int[]{11, 7, 6, 3}, 8));
        }

        @Test
        @DisplayName("h exactly equals total bananas, speed 1: [3,6,7,11], h=27 → 1")
        void testHEqualsTotalBananas() {
            assertEquals(1, instance.minEatingSpeed(new int[]{3, 6, 7, 11}, 27));
        }

        @Test
        @DisplayName("h one below total bananas, speed 2: [3,6,7,11], h=26 → 2")
        void testHOneBelowTotal() {
            assertEquals(2, instance.minEatingSpeed(new int[]{3, 6, 7, 11}, 26));
        }

        @Test
        @DisplayName("Piles that do not divide evenly: [5,5], h=3 → 5")
        void testUnevenPiles() {
            assertEquals(5, instance.minEatingSpeed(new int[]{5, 5}, 3));
        }

        @Test
        @DisplayName("Largest pile dominates: [1,1,1,100], h=4 → 100")
        void testLargestPileDominates() {
            assertEquals(100, instance.minEatingSpeed(new int[]{1, 1, 1, 100}, 4));
        }

        @Test
        @DisplayName("Many small piles, few spare hours: [2,2,2,2,2,2], h=7 → 2")
        void testManySmallPiles() {
            assertEquals(2, instance.minEatingSpeed(new int[]{2, 2, 2, 2, 2, 2}, 7));
        }

        @Test
        @DisplayName("Value boundary: one pile of 1e9, one hour: [1000000000], h=1 → 1000000000")
        void testBoundaryOneHour() {
            assertEquals(1000000000, instance.minEatingSpeed(new int[]{1000000000}, 1));
        }

        @Test
        @DisplayName("Value boundary: one pile of 1e9, two hours: [1000000000], h=2 → 500000000")
        void testBoundaryTwoHours() {
            assertEquals(500000000, instance.minEatingSpeed(new int[]{1000000000}, 2));
        }

        @Test
        @DisplayName("Value boundary: one pile of 1e9, h=1e9: [1000000000], h=1000000000 → 1")
        void testBoundaryHugeH() {
            assertEquals(1, instance.minEatingSpeed(new int[]{1000000000}, 1000000000));
        }

        @Test
        @DisplayName("Value boundary: two piles of 1e9, h=3: [1000000000,1000000000], h=3 → 1000000000")
        void testBoundaryTwoPiles() {
            assertEquals(1000000000, instance.minEatingSpeed(new int[]{1000000000, 1000000000}, 3));
        }

        @Test
        @DisplayName("Max size (n=10000): every pile is 1, h=10000 → 1")
        void testMaxSizeAllOnes() {
            int[] piles = new int[10000];
            java.util.Arrays.fill(piles, 1);
            assertEquals(1, instance.minEatingSpeed(piles, 10000));
        }

        @Test
        @DisplayName("Max size (n=10000): piles[i]=i+1, h=10000 (must eat the largest pile in an hour) → 10000")
        void testMaxSizeAscendingTightH() {
            int[] piles = new int[10000];
            for (int i = 0; i < piles.length; i++) piles[i] = i + 1;
            assertEquals(10000, instance.minEatingSpeed(piles, 10000));
        }

        @Test
        @DisplayName("Max size (n=10000): piles[i]=i+1, h=100000 → 527")
        void testMaxSizeAscendingLooseH() {
            int[] piles = new int[10000];
            for (int i = 0; i < piles.length; i++) piles[i] = i + 1;
            assertEquals(527, instance.minEatingSpeed(piles, 100000));
        }
    }
}
