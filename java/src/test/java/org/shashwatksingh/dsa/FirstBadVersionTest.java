package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("First Bad Version Tests")
class FirstBadVersionTest {

    private FirstBadVersion instance;

    @BeforeEach
    void setUp() {
        instance = new FirstBadVersion();
    }

    // ═══════════════════════════════════════════════════════════
    //  firstBadVersion()  — binary search, O(log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("firstBadVersion() — binary search for first bad version")
    class FirstBadVersionMethodTests {

        @Test
        @DisplayName("LeetCode Example 1: n=5, bad=4 → 4")
        void testLeetCodeExample1() {
            instance.bad = 4;
            assertEquals(4, instance.firstBadVersion(5));
        }

        @Test
        @DisplayName("LeetCode Example 2: n=1, bad=1 → 1 (single version)")
        void testLeetCodeExample2() {
            instance.bad = 1;
            assertEquals(1, instance.firstBadVersion(1));
        }

        @Test
        @DisplayName("Scenario: n=2, bad=1 → 1 (all versions bad)")
        void testAllBadTwoVersions() {
            instance.bad = 1;
            assertEquals(1, instance.firstBadVersion(2));
        }

        @Test
        @DisplayName("Scenario: n=2, bad=2 → 2 (only last version bad)")
        void testOnlyLastBadTwoVersions() {
            instance.bad = 2;
            assertEquals(2, instance.firstBadVersion(2));
        }

        @Test
        @DisplayName("Scenario: n=100, bad=1 → 1 (first version is bad)")
        void testFirstVersionBad() {
            instance.bad = 1;
            assertEquals(1, instance.firstBadVersion(100));
        }

        @Test
        @DisplayName("Scenario: n=100, bad=100 → 100 (only last version is bad)")
        void testLastVersionBad() {
            instance.bad = 100;
            assertEquals(100, instance.firstBadVersion(100));
        }

        @Test
        @DisplayName("Scenario: n=100, bad=50 → 50 (middle)")
        void testMiddleBad() {
            instance.bad = 50;
            assertEquals(50, instance.firstBadVersion(100));
        }

        @Test
        @DisplayName("Scenario: n=10, bad=3 → 3 (bad in lower half)")
        void testBadInLowerHalf() {
            instance.bad = 3;
            assertEquals(3, instance.firstBadVersion(10));
        }

        @Test
        @DisplayName("Scenario: n=10, bad=9 → 9 (bad in upper half)")
        void testBadInUpperHalf() {
            instance.bad = 9;
            assertEquals(9, instance.firstBadVersion(10));
        }

        @Test
        @DisplayName("Scenario: n=7, bad=4 → 4 (bad exactly at first midpoint)")
        void testBadAtFirstMidpoint() {
            instance.bad = 4;
            assertEquals(4, instance.firstBadVersion(7));
        }

        @Test
        @DisplayName("Scenario: n=Integer.MAX_VALUE, bad=1 → 1 (max constraint, overflow check)")
        void testMaxNFirstBad() {
            instance.bad = 1;
            assertEquals(1, instance.firstBadVersion(Integer.MAX_VALUE));
        }

        @Test
        @DisplayName("Scenario: n=Integer.MAX_VALUE, bad=Integer.MAX_VALUE → Integer.MAX_VALUE (max constraint, last bad)")
        void testMaxNLastBad() {
            instance.bad = Integer.MAX_VALUE;
            assertEquals(Integer.MAX_VALUE, instance.firstBadVersion(Integer.MAX_VALUE));
        }

        @Test
        @DisplayName("Scenario: n=Integer.MAX_VALUE, bad=1_500_000_000 → 1_500_000_000 (max constraint, upper region)")
        void testMaxNUpperRegionBad() {
            instance.bad = 1_500_000_000;
            assertEquals(1_500_000_000, instance.firstBadVersion(Integer.MAX_VALUE));
        }
    }
}
