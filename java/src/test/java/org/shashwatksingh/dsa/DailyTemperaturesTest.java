package org.shashwatksingh.dsa;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Daily Temperatures Tests")
class DailyTemperaturesTest {

    private DailyTemperatures instance;

    @BeforeEach
    void setUp() {
        instance = new DailyTemperatures();
    }

    private static int[] filled(int length, int value) {
        int[] arr = new int[length];
        Arrays.fill(arr, value);
        return arr;
    }

    /** Deterministic pseudo-random temperatures in [30, 100] (linear congruential generator). */
    private static int[] pseudoRandomTemperatures(int length) {
        int[] arr = new int[length];
        long seed = 12345;
        for (int i = 0; i < length; i++) {
            seed = (seed * 1103515245L + 12345L) & 0x7fffffffL;
            arr[i] = 30 + (int) (seed % 71);
        }
        return arr;
    }

    /** Independent oracle: scan forward from each day for the first strictly warmer day. */
    private static int[] expectedWaits(int[] temperatures) {
        int[] expected = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    expected[i] = j - i;
                    break;
                }
            }
        }
        return expected;
    }

    /** Waits for a day that is n - 1 - i days away, for every i except the last (which is 0). */
    private static int[] countdownToLastDay(int length) {
        int[] expected = new int[length];
        for (int i = 0; i < length - 1; i++) {
            expected[i] = length - 1 - i;
        }
        return expected;
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — O(n^2) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — nested scan for next warmer day, O(n^2)")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [73,74,75,71,69,72,76,73] → [1,1,4,2,1,1,0,0]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 4, 2, 1, 1, 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [30,40,50,60] → [1,1,1,0]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 40, 50, 60 })));
        }

        @Test
        @DisplayName("LeetCode Example 3: [30,60,90] → [1,1,0]")
        void testLeetCodeExample3() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 60, 90 })));
        }

        @Test
        @DisplayName("Scenario: [50] → [0] (single day, nothing to wait for)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 50 })));
        }

        @Test
        @DisplayName("Scenario: [50,60] → [1,0] (minimum warmer pair)")
        void testTwoElementsWarmer() {
            assertEquals(Arrays.toString(new int[] { 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 50, 60 })));
        }

        @Test
        @DisplayName("Scenario: [60,50] → [0,0] (minimum cooler pair)")
        void testTwoElementsCooler() {
            assertEquals(Arrays.toString(new int[] { 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 60, 50 })));
        }

        @Test
        @DisplayName("Scenario: [70,70,70,70] → [0,0,0,0] (equal is not warmer)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { 0, 0, 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 70, 70, 70, 70 })));
        }

        @Test
        @DisplayName("Scenario: [30,31,32,33,34] → [1,1,1,1,0] (every day waits one day)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 31, 32, 33, 34 })));
        }

        @Test
        @DisplayName("Scenario: [100,90,80,70,60] → [0,0,0,0,0] (no warmer day ever)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { 0, 0, 0, 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 100, 90, 80, 70, 60 })));
        }

        @Test
        @DisplayName("Scenario: [60,50,40,30,70] → [4,3,2,1,0] (one warm day resolves every earlier day)")
        void testDecreasingThenSpike() {
            assertEquals(Arrays.toString(new int[] { 4, 3, 2, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 60, 50, 40, 30, 70 })));
        }

        @Test
        @DisplayName("Scenario: [70,70,80] → [2,1,0] (equal days wait for the first strictly warmer day)")
        void testEqualThenWarmer() {
            assertEquals(Arrays.toString(new int[] { 2, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 70, 70, 80 })));
        }

        @Test
        @DisplayName("Scenario: [70,80,70,80] → [1,0,1,0] (repeating warm-up resets)")
        void testAlternatingUpDown() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 70, 80, 70, 80 })));
        }

        @Test
        @DisplayName("Scenario: [30,30,30,31] → [3,2,1,0] (plateau resolved by one later day)")
        void testPlateauThenRise() {
            assertEquals(Arrays.toString(new int[] { 3, 2, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 30, 30, 31 })));
        }

        @Test
        @DisplayName("Scenario: [40,60,50,45,55,65] → [1,4,2,1,1,0] (peak waits for a later, higher day)")
        void testPeakInMiddle() {
            assertEquals(Arrays.toString(new int[] { 1, 4, 2, 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 40, 60, 50, 45, 55, 65 })));
        }

        @Test
        @DisplayName("Scenario: [89,62,70,58,47,47,46,76,100,70] → [8,1,5,4,3,2,1,1,0,0] (nested valleys with repeated values)")
        void testValleyAndTwoPeaks() {
            assertEquals(Arrays.toString(new int[] { 8, 1, 5, 4, 3, 2, 1, 1, 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 89, 62, 70, 58, 47, 47, 46, 76, 100, 70 })));
        }

        @Test
        @DisplayName("Scenario: [30,100] → [1,0] (constraint bounds, warmer)")
        void testMinThenMaxBound() {
            assertEquals(Arrays.toString(new int[] { 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 100 })));
        }

        @Test
        @DisplayName("Scenario: [100,30] → [0,0] (constraint bounds, cooler)")
        void testMaxThenMinBound() {
            assertEquals(Arrays.toString(new int[] { 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 100, 30 })));
        }

        @Test
        @DisplayName("Scenario: [30,100,30,100] → [1,0,1,0] (constraint bounds, repeated)")
        void testAlternatingBounds() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 30, 100, 30, 100 })));
        }

        @Test
        @DisplayName("Scenario: [70,71,71,70] → [1,0,0,0] (equal peak is never beaten)")
        void testEqualPeak() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 0, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 70, 71, 71, 70 })));
        }

        @Test
        @DisplayName("Scenario: [75,71,72,73,76] → [4,1,1,1,0] (one late day resolves the first, others resolve earlier)")
        void testLateDayResolvesFirstOnly() {
            assertEquals(Arrays.toString(new int[] { 4, 1, 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 75, 71, 72, 73, 76 })));
        }

        @Test
        @DisplayName("Scenario: [80,60,70,65,75,90] → [5,1,2,1,1,0] (partial pops from the pending stack)")
        void testPartialPops() {
            assertEquals(Arrays.toString(new int[] { 5, 1, 2, 1, 1, 0 }),
                    Arrays.toString(instance.bruteForce(new int[] { 80, 60, 70, 65, 75, 90 })));
        }

        @Test
        @DisplayName("Scenario: 2,000 pseudo-random temperatures in [30,100] → matches independent oracle")
        void testPseudoRandomMidSize() {
            int[] temperatures = pseudoRandomTemperatures(2_000);
            assertEquals(Arrays.toString(expectedWaits(temperatures)),
                    Arrays.toString(instance.bruteForce(temperatures)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  dailyTemperatures()  — O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("dailyTemperatures() — monotonic stack of pending days, O(n)")
    class MonotonicStackTests {

        @Test
        @DisplayName("LeetCode Example 1: [73,74,75,71,69,72,76,73] → [1,1,4,2,1,1,0,0]")
        void testLeetCodeExample1() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 4, 2, 1, 1, 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 73, 74, 75, 71, 69, 72, 76, 73 })));
        }

        @Test
        @DisplayName("LeetCode Example 2: [30,40,50,60] → [1,1,1,0]")
        void testLeetCodeExample2() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 40, 50, 60 })));
        }

        @Test
        @DisplayName("LeetCode Example 3: [30,60,90] → [1,1,0]")
        void testLeetCodeExample3() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 60, 90 })));
        }

        @Test
        @DisplayName("Scenario: [50] → [0] (single day, nothing to wait for)")
        void testSingleElement() {
            assertEquals(Arrays.toString(new int[] { 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 50 })));
        }

        @Test
        @DisplayName("Scenario: [50,60] → [1,0] (minimum warmer pair)")
        void testTwoElementsWarmer() {
            assertEquals(Arrays.toString(new int[] { 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 50, 60 })));
        }

        @Test
        @DisplayName("Scenario: [60,50] → [0,0] (minimum cooler pair)")
        void testTwoElementsCooler() {
            assertEquals(Arrays.toString(new int[] { 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 60, 50 })));
        }

        @Test
        @DisplayName("Scenario: [70,70,70,70] → [0,0,0,0] (equal is not warmer)")
        void testAllSame() {
            assertEquals(Arrays.toString(new int[] { 0, 0, 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 70, 70, 70, 70 })));
        }

        @Test
        @DisplayName("Scenario: [30,31,32,33,34] → [1,1,1,1,0] (every day waits one day)")
        void testStrictlyIncreasing() {
            assertEquals(Arrays.toString(new int[] { 1, 1, 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 31, 32, 33, 34 })));
        }

        @Test
        @DisplayName("Scenario: [100,90,80,70,60] → [0,0,0,0,0] (no warmer day ever)")
        void testStrictlyDecreasing() {
            assertEquals(Arrays.toString(new int[] { 0, 0, 0, 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 100, 90, 80, 70, 60 })));
        }

        @Test
        @DisplayName("Scenario: [60,50,40,30,70] → [4,3,2,1,0] (one warm day resolves every earlier day)")
        void testDecreasingThenSpike() {
            assertEquals(Arrays.toString(new int[] { 4, 3, 2, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 60, 50, 40, 30, 70 })));
        }

        @Test
        @DisplayName("Scenario: [70,70,80] → [2,1,0] (equal days wait for the first strictly warmer day)")
        void testEqualThenWarmer() {
            assertEquals(Arrays.toString(new int[] { 2, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 70, 70, 80 })));
        }

        @Test
        @DisplayName("Scenario: [70,80,70,80] → [1,0,1,0] (repeating warm-up resets)")
        void testAlternatingUpDown() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 70, 80, 70, 80 })));
        }

        @Test
        @DisplayName("Scenario: [30,30,30,31] → [3,2,1,0] (plateau resolved by one later day)")
        void testPlateauThenRise() {
            assertEquals(Arrays.toString(new int[] { 3, 2, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 30, 30, 31 })));
        }

        @Test
        @DisplayName("Scenario: [40,60,50,45,55,65] → [1,4,2,1,1,0] (peak waits for a later, higher day)")
        void testPeakInMiddle() {
            assertEquals(Arrays.toString(new int[] { 1, 4, 2, 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 40, 60, 50, 45, 55, 65 })));
        }

        @Test
        @DisplayName("Scenario: [89,62,70,58,47,47,46,76,100,70] → [8,1,5,4,3,2,1,1,0,0] (nested valleys with repeated values)")
        void testValleyAndTwoPeaks() {
            assertEquals(Arrays.toString(new int[] { 8, 1, 5, 4, 3, 2, 1, 1, 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 89, 62, 70, 58, 47, 47, 46, 76, 100, 70 })));
        }

        @Test
        @DisplayName("Scenario: [30,100] → [1,0] (constraint bounds, warmer)")
        void testMinThenMaxBound() {
            assertEquals(Arrays.toString(new int[] { 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 100 })));
        }

        @Test
        @DisplayName("Scenario: [100,30] → [0,0] (constraint bounds, cooler)")
        void testMaxThenMinBound() {
            assertEquals(Arrays.toString(new int[] { 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 100, 30 })));
        }

        @Test
        @DisplayName("Scenario: [30,100,30,100] → [1,0,1,0] (constraint bounds, repeated)")
        void testAlternatingBounds() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 30, 100, 30, 100 })));
        }

        @Test
        @DisplayName("Scenario: 100,000 × 70 → 100,000 zeros (max length, all same)")
        void testMaxLengthAllSame() {
            assertEquals(Arrays.toString(new int[100_000]),
                    Arrays.toString(instance.dailyTemperatures(filled(100_000, 70))));
        }

        @Test
        @DisplayName("Scenario: 100,000 × 100 → 100,000 zeros (max length, all at upper bound)")
        void testMaxLengthAllMax() {
            assertEquals(Arrays.toString(new int[100_000]),
                    Arrays.toString(instance.dailyTemperatures(filled(100_000, 100))));
        }

        @Test
        @DisplayName("Scenario: 99,999 × 30 then 100 → [99,999, 99,998, ..., 1, 0] (max length, one warm day at the end)")
        void testMaxLengthWarmDayAtEnd() {
            int[] temperatures = filled(100_000, 30);
            temperatures[99_999] = 100;
            assertEquals(Arrays.toString(countdownToLastDay(100_000)),
                    Arrays.toString(instance.dailyTemperatures(temperatures)));
        }

        @Test
        @DisplayName("Scenario: 30 then 99,999 × 100 → [1, 0, 0, ..., 0] (max length, warm day right after)")
        void testMaxLengthWarmDayRightAfter() {
            int[] temperatures = filled(100_000, 100);
            temperatures[0] = 30;
            int[] expected = new int[100_000];
            expected[0] = 1;
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.dailyTemperatures(temperatures)));
        }

        @Test
        @DisplayName("Scenario: [70,71,71,70] → [1,0,0,0] (equal peak is never beaten)")
        void testEqualPeak() {
            assertEquals(Arrays.toString(new int[] { 1, 0, 0, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 70, 71, 71, 70 })));
        }

        @Test
        @DisplayName("Scenario: [75,71,72,73,76] → [4,1,1,1,0] (one late day resolves the first, others resolve earlier)")
        void testLateDayResolvesFirstOnly() {
            assertEquals(Arrays.toString(new int[] { 4, 1, 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 75, 71, 72, 73, 76 })));
        }

        @Test
        @DisplayName("Scenario: [80,60,70,65,75,90] → [5,1,2,1,1,0] (partial pops from the pending stack)")
        void testPartialPops() {
            assertEquals(Arrays.toString(new int[] { 5, 1, 2, 1, 1, 0 }),
                    Arrays.toString(instance.dailyTemperatures(new int[] { 80, 60, 70, 65, 75, 90 })));
        }

        @Test
        @DisplayName("Scenario: 2,000 pseudo-random temperatures in [30,100] → matches independent oracle")
        void testPseudoRandomMidSize() {
            int[] temperatures = pseudoRandomTemperatures(2_000);
            assertEquals(Arrays.toString(expectedWaits(temperatures)),
                    Arrays.toString(instance.dailyTemperatures(temperatures)));
        }

        @Test
        @DisplayName("Scenario: 100,000 pseudo-random temperatures in [30,100] → matches independent oracle (max length)")
        void testPseudoRandomMaxLength() {
            int[] temperatures = pseudoRandomTemperatures(100_000);
            assertEquals(Arrays.toString(expectedWaits(temperatures)),
                    Arrays.toString(instance.dailyTemperatures(temperatures)));
        }

        @Test
        @DisplayName("Scenario: 50,000 × (70, 71) → even days wait 1, odd days 0 (max length, alternating)")
        void testMaxLengthAlternating() {
            int[] temperatures = new int[100_000];
            int[] expected = new int[100_000];
            for (int i = 0; i < 100_000; i++) {
                temperatures[i] = i % 2 == 0 ? 70 : 71;
                expected[i] = i % 2 == 0 ? 1 : 0;
            }
            assertEquals(Arrays.toString(expected),
                    Arrays.toString(instance.dailyTemperatures(temperatures)));
        }
    }
}
