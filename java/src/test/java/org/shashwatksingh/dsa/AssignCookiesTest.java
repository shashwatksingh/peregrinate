package org.shashwatksingh.dsa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Assign Cookies Tests")
class AssignCookiesTest {

    private AssignCookies instance;

    @BeforeEach
    void setUp() {
        instance = new AssignCookies();
    }

    private static int[] filled(int n, int v) {
        int[] arr = new int[n];
        Arrays.fill(arr, v);
        return arr;
    }

    private static int[] range(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    // ═══════════════════════════════════════════════════════════
    //  findContentChildrenBrute()  — brute force, O(n·m)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findContentChildrenBrute() — brute force")
    class FindContentChildrenBruteTests {

        @Test
        @DisplayName("Scenario: LeetCode Example 1: g=[1,2,3], s=[1,1] → 1 (only the child with greed 1 is satisfied)")
        void testLeetCodeExample1() {
            assertEquals(1, instance.findContentChildrenBrute(new int[]{1, 2, 3}, new int[]{1, 1}));
        }

        @Test
        @DisplayName("Scenario: LeetCode Example 2: g=[1,2], s=[1,2,3] → 2 (all children satisfied)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.findContentChildrenBrute(new int[]{1, 2}, new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Scenario: Single child, exact-size cookie: g=[1], s=[1] → 1")
        void testSingleChildExactCookie() {
            assertEquals(1, instance.findContentChildrenBrute(new int[]{1}, new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: Single child, cookie too small: g=[2], s=[1] → 0")
        void testSingleChildCookieTooSmall() {
            assertEquals(0, instance.findContentChildrenBrute(new int[]{2}, new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: No cookies: g=[1,2,3], s=[] → 0")
        void testNoCookies() {
            assertEquals(0, instance.findContentChildrenBrute(new int[]{1, 2, 3}, new int[]{}));
        }

        @Test
        @DisplayName("Scenario: All-same greed and cookie sizes: g=[2,2,2], s=[2,2] → 2 (limited by cookies)")
        void testUniformValuesFewerCookies() {
            assertEquals(2, instance.findContentChildrenBrute(new int[]{2, 2, 2}, new int[]{2, 2}));
        }

        @Test
        @DisplayName("Scenario: All cookies smaller than uniform greed: g=[3,3], s=[2,2,2] → 0")
        void testUniformCookiesTooSmall() {
            assertEquals(0, instance.findContentChildrenBrute(new int[]{3, 3}, new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Scenario: Strictly increasing: g=[1,2,3,4], s=[1,2,3,4] → 4")
        void testStrictlyIncreasing() {
            assertEquals(4, instance.findContentChildrenBrute(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Scenario: Strictly decreasing: g=[4,3,2,1], s=[4,3,2,1] → 4")
        void testStrictlyDecreasing() {
            assertEquals(4, instance.findContentChildrenBrute(new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Scenario: Unsorted inputs, only large cookies match: g=[10,9,8,7], s=[5,6,7,8] → 2")
        void testUnsortedPartialMatch() {
            assertEquals(2, instance.findContentChildrenBrute(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8}));
        }

        @Test
        @DisplayName("Scenario: One big cookie, many children: g=[1,2,3], s=[3] → 1 (one cookie per child)")
        void testSingleBigCookie() {
            assertEquals(1, instance.findContentChildrenBrute(new int[]{1, 2, 3}, new int[]{3}));
        }

        @Test
        @DisplayName("Scenario: Oversized cookies: g=[1,1,1], s=[100,100,100] → 3")
        void testOversizedCookies() {
            assertEquals(3, instance.findContentChildrenBrute(new int[]{1, 1, 1}, new int[]{100, 100, 100}));
        }

        @Test
        @DisplayName("Scenario: More cookies than children, only last fits: g=[5], s=[1,2,3,4,5] → 1")
        void testMoreCookiesThanChildren() {
            assertEquals(1, instance.findContentChildrenBrute(new int[]{5}, new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Scenario: Large cookie wasted on small child ordering: g=[2,3], s=[3,2] → 2")
        void testCookiesGivenOutOfOrder() {
            assertEquals(2, instance.findContentChildrenBrute(new int[]{2, 3}, new int[]{3, 2}));
        }

        @Test
        @DisplayName("Scenario: Int max values: g=[MAX], s=[MAX] → 1")
        void testIntMaxValues() {
            assertEquals(1, instance.findContentChildrenBrute(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE}));
        }

        @Test
        @DisplayName("Scenario: Int max greed, smaller cookie: g=[MAX], s=[MAX-1] → 0")
        void testIntMaxGreedSmallerCookie() {
            assertEquals(0, instance.findContentChildrenBrute(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE - 1}));
        }

        @Test
        @DisplayName("Scenario: 30000 children and 30000 cookies, all size 1 → 30000 (max constraint)")
        void testMaxConstraintAllSame() {
            assertEquals(30000, instance.findContentChildrenBrute(filled(30000, 1), filled(30000, 1)));
        }

        @Test
        @DisplayName("Scenario: 30000 children and 30000 cookies, sizes 1..30000 → 30000 (max constraint)")
        void testMaxConstraintIncreasing() {
            assertEquals(30000, instance.findContentChildrenBrute(range(30000), range(30000)));
        }

        @Test
        @DisplayName("Scenario: 30000 children with greed 2, 30000 cookies of size 1 → 0 (max constraint)")
        void testMaxConstraintNoMatch() {
            assertEquals(0, instance.findContentChildrenBrute(filled(30000, 2), filled(30000, 1)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  findContentChildren()  — sort + greedy two pointers, O(n log n + m log m)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("findContentChildren() — greedy two-pointer")
    class FindContentChildrenTests {

        @Test
        @DisplayName("Scenario: LeetCode Example 1: g=[1,2,3], s=[1,1] → 1 (only the child with greed 1 is satisfied)")
        void testLeetCodeExample1() {
            assertEquals(1, instance.findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
        }

        @Test
        @DisplayName("Scenario: LeetCode Example 2: g=[1,2], s=[1,2,3] → 2 (all children satisfied)")
        void testLeetCodeExample2() {
            assertEquals(2, instance.findContentChildren(new int[]{1, 2}, new int[]{1, 2, 3}));
        }

        @Test
        @DisplayName("Scenario: Single child, exact-size cookie: g=[1], s=[1] → 1")
        void testSingleChildExactCookie() {
            assertEquals(1, instance.findContentChildren(new int[]{1}, new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: Single child, cookie too small: g=[2], s=[1] → 0")
        void testSingleChildCookieTooSmall() {
            assertEquals(0, instance.findContentChildren(new int[]{2}, new int[]{1}));
        }

        @Test
        @DisplayName("Scenario: No cookies: g=[1,2,3], s=[] → 0")
        void testNoCookies() {
            assertEquals(0, instance.findContentChildren(new int[]{1, 2, 3}, new int[]{}));
        }

        @Test
        @DisplayName("Scenario: All-same greed and cookie sizes: g=[2,2,2], s=[2,2] → 2 (limited by cookies)")
        void testUniformValuesFewerCookies() {
            assertEquals(2, instance.findContentChildren(new int[]{2, 2, 2}, new int[]{2, 2}));
        }

        @Test
        @DisplayName("Scenario: All cookies smaller than uniform greed: g=[3,3], s=[2,2,2] → 0")
        void testUniformCookiesTooSmall() {
            assertEquals(0, instance.findContentChildren(new int[]{3, 3}, new int[]{2, 2, 2}));
        }

        @Test
        @DisplayName("Scenario: Strictly increasing: g=[1,2,3,4], s=[1,2,3,4] → 4")
        void testStrictlyIncreasing() {
            assertEquals(4, instance.findContentChildren(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}));
        }

        @Test
        @DisplayName("Scenario: Strictly decreasing: g=[4,3,2,1], s=[4,3,2,1] → 4")
        void testStrictlyDecreasing() {
            assertEquals(4, instance.findContentChildren(new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 1}));
        }

        @Test
        @DisplayName("Scenario: Unsorted inputs, only large cookies match: g=[10,9,8,7], s=[5,6,7,8] → 2")
        void testUnsortedPartialMatch() {
            assertEquals(2, instance.findContentChildren(new int[]{10, 9, 8, 7}, new int[]{5, 6, 7, 8}));
        }

        @Test
        @DisplayName("Scenario: One big cookie, many children: g=[1,2,3], s=[3] → 1 (one cookie per child)")
        void testSingleBigCookie() {
            assertEquals(1, instance.findContentChildren(new int[]{1, 2, 3}, new int[]{3}));
        }

        @Test
        @DisplayName("Scenario: Oversized cookies: g=[1,1,1], s=[100,100,100] → 3")
        void testOversizedCookies() {
            assertEquals(3, instance.findContentChildren(new int[]{1, 1, 1}, new int[]{100, 100, 100}));
        }

        @Test
        @DisplayName("Scenario: More cookies than children, only last fits: g=[5], s=[1,2,3,4,5] → 1")
        void testMoreCookiesThanChildren() {
            assertEquals(1, instance.findContentChildren(new int[]{5}, new int[]{1, 2, 3, 4, 5}));
        }

        @Test
        @DisplayName("Scenario: Large cookie wasted on small child ordering: g=[2,3], s=[3,2] → 2")
        void testCookiesGivenOutOfOrder() {
            assertEquals(2, instance.findContentChildren(new int[]{2, 3}, new int[]{3, 2}));
        }

        @Test
        @DisplayName("Scenario: Int max values: g=[MAX], s=[MAX] → 1")
        void testIntMaxValues() {
            assertEquals(1, instance.findContentChildren(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE}));
        }

        @Test
        @DisplayName("Scenario: Int max greed, smaller cookie: g=[MAX], s=[MAX-1] → 0")
        void testIntMaxGreedSmallerCookie() {
            assertEquals(0, instance.findContentChildren(new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE - 1}));
        }

        @Test
        @DisplayName("Scenario: 30000 children and 30000 cookies, all size 1 → 30000 (max constraint)")
        void testMaxConstraintAllSame() {
            assertEquals(30000, instance.findContentChildren(filled(30000, 1), filled(30000, 1)));
        }

        @Test
        @DisplayName("Scenario: 30000 children and 30000 cookies, sizes 1..30000 → 30000 (max constraint)")
        void testMaxConstraintIncreasing() {
            assertEquals(30000, instance.findContentChildren(range(30000), range(30000)));
        }

        @Test
        @DisplayName("Scenario: 30000 children with greed 2, 30000 cookies of size 1 → 0 (max constraint)")
        void testMaxConstraintNoMatch() {
            assertEquals(0, instance.findContentChildren(filled(30000, 2), filled(30000, 1)));
        }
    }
}
