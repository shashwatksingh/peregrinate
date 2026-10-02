package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Longest Substring Without Repeating Characters Tests (LongestRepeatingCharacterReplacement)")
class LongestRepeatingCharacterReplacementTest {

    private LongestRepeatingCharacterReplacement instance;

    @BeforeEach
    void setUp() {
        instance = new LongestRepeatingCharacterReplacement();
    }

    private static String printableAsciiCycle(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) (' ' + (i % 95)));
        }
        return sb.toString();
    }

    // ═══════════════════════════════════════════════════════════
    //  lengthOfLongestSubstringBruteForce()  — HashMap per substring, O(n^3)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("lengthOfLongestSubstringBruteForce() — brute force with HashMap, O(n^3)")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: \"abcabcbb\" → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForce("abcabcbb"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"bbbbb\" → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForce("bbbbb"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"pwwkew\" → 3")
        void testLeetCodeExample3() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForce("pwwkew"));
        }

        @Test
        @DisplayName("Scenario: \"\" → 0 (empty string)")
        void testEmptyString() {
            assertEquals(0, instance.lengthOfLongestSubstringBruteForce(""));
        }

        @Test
        @DisplayName("Scenario: \"a\" → 1 (single character)")
        void testSingleCharacter() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForce("a"));
        }

        @Test
        @DisplayName("Scenario: \" \" → 1 (single space)")
        void testSingleSpace() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForce(" "));
        }

        @Test
        @DisplayName("Scenario: \"aaaa\" → 1 (all same)")
        void testAllSame() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForce("aaaa"));
        }

        @Test
        @DisplayName("Scenario: \"abcdef\" → 6 (all distinct, whole string)")
        void testAllDistinct() {
            assertEquals(6, instance.lengthOfLongestSubstringBruteForce("abcdef"));
        }

        @Test
        @DisplayName("Scenario: \"abba\" → 2 (repeat of char already left of window)")
        void testAbba() {
            assertEquals(2, instance.lengthOfLongestSubstringBruteForce("abba"));
        }

        @Test
        @DisplayName("Scenario: \"dvdf\" → 3 (window must jump past first duplicate)")
        void testDvdf() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForce("dvdf"));
        }

        @Test
        @DisplayName("Scenario: \"tmmzuxt\" → 5 (stale earlier index of repeated char)")
        void testTmmzuxt() {
            assertEquals(5, instance.lengthOfLongestSubstringBruteForce("tmmzuxt"));
        }

        @Test
        @DisplayName("Scenario: \"bbcbacba\" → 3 (reversed Example 1)")
        void testReversedExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForce("bbcbacba"));
        }

        @Test
        @DisplayName("Scenario: \"aAbB\" → 4 (uppercase and lowercase are distinct)")
        void testUpperAndLowerCase() {
            assertEquals(4, instance.lengthOfLongestSubstringBruteForce("aAbB"));
        }

        @Test
        @DisplayName("Scenario: \"a1b2 c3!\" → 8 (digits, space and symbols)")
        void testDigitsSpacesSymbols() {
            assertEquals(8, instance.lengthOfLongestSubstringBruteForce("a1b2 c3!"));
        }

        @Test
        @DisplayName("Scenario: \"a b a\" → 3 (space as a repeated character)")
        void testRepeatedSpace() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForce("a b a"));
        }

        @Test
        @DisplayName("Scenario: all 95 printable ASCII chars → 95 (max distinct charset)")
        void testAllPrintableAscii() {
            assertEquals(95, instance.lengthOfLongestSubstringBruteForce(printableAsciiCycle(95)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  lengthOfLongestSubstringBruteForceSet()  — HashSet per substring, O(n^3)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("lengthOfLongestSubstringBruteForceSet() — brute force with HashSet, O(n^3)")
    class BruteForceSetTests {

        @Test
        @DisplayName("LeetCode Example 1: \"abcabcbb\" → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForceSet("abcabcbb"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"bbbbb\" → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForceSet("bbbbb"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"pwwkew\" → 3")
        void testLeetCodeExample3() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForceSet("pwwkew"));
        }

        @Test
        @DisplayName("Scenario: \"\" → 0 (empty string)")
        void testEmptyString() {
            assertEquals(0, instance.lengthOfLongestSubstringBruteForceSet(""));
        }

        @Test
        @DisplayName("Scenario: \"a\" → 1 (single character)")
        void testSingleCharacter() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForceSet("a"));
        }

        @Test
        @DisplayName("Scenario: \" \" → 1 (single space)")
        void testSingleSpace() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForceSet(" "));
        }

        @Test
        @DisplayName("Scenario: \"aaaa\" → 1 (all same)")
        void testAllSame() {
            assertEquals(1, instance.lengthOfLongestSubstringBruteForceSet("aaaa"));
        }

        @Test
        @DisplayName("Scenario: \"abcdef\" → 6 (all distinct, whole string)")
        void testAllDistinct() {
            assertEquals(6, instance.lengthOfLongestSubstringBruteForceSet("abcdef"));
        }

        @Test
        @DisplayName("Scenario: \"abba\" → 2 (repeat of char already left of window)")
        void testAbba() {
            assertEquals(2, instance.lengthOfLongestSubstringBruteForceSet("abba"));
        }

        @Test
        @DisplayName("Scenario: \"dvdf\" → 3 (window must jump past first duplicate)")
        void testDvdf() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForceSet("dvdf"));
        }

        @Test
        @DisplayName("Scenario: \"tmmzuxt\" → 5 (stale earlier index of repeated char)")
        void testTmmzuxt() {
            assertEquals(5, instance.lengthOfLongestSubstringBruteForceSet("tmmzuxt"));
        }

        @Test
        @DisplayName("Scenario: \"bbcbacba\" → 3 (reversed Example 1)")
        void testReversedExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForceSet("bbcbacba"));
        }

        @Test
        @DisplayName("Scenario: \"aAbB\" → 4 (uppercase and lowercase are distinct)")
        void testUpperAndLowerCase() {
            assertEquals(4, instance.lengthOfLongestSubstringBruteForceSet("aAbB"));
        }

        @Test
        @DisplayName("Scenario: \"a1b2 c3!\" → 8 (digits, space and symbols)")
        void testDigitsSpacesSymbols() {
            assertEquals(8, instance.lengthOfLongestSubstringBruteForceSet("a1b2 c3!"));
        }

        @Test
        @DisplayName("Scenario: \"a b a\" → 3 (space as a repeated character)")
        void testRepeatedSpace() {
            assertEquals(3, instance.lengthOfLongestSubstringBruteForceSet("a b a"));
        }

        @Test
        @DisplayName("Scenario: all 95 printable ASCII chars → 95 (max distinct charset)")
        void testAllPrintableAscii() {
            assertEquals(95, instance.lengthOfLongestSubstringBruteForceSet(printableAsciiCycle(95)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  lengthOfLongestSubstring()  — sliding window + HashMap, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("lengthOfLongestSubstring() — sliding window with HashMap, O(n)")
    class SlidingWindowTests {

        @Test
        @DisplayName("LeetCode Example 1: \"abcabcbb\" → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.lengthOfLongestSubstring("abcabcbb"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"bbbbb\" → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.lengthOfLongestSubstring("bbbbb"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"pwwkew\" → 3")
        void testLeetCodeExample3() {
            assertEquals(3, instance.lengthOfLongestSubstring("pwwkew"));
        }

        @Test
        @DisplayName("Scenario: \"\" → 0 (empty string)")
        void testEmptyString() {
            assertEquals(0, instance.lengthOfLongestSubstring(""));
        }

        @Test
        @DisplayName("Scenario: \"a\" → 1 (single character)")
        void testSingleCharacter() {
            assertEquals(1, instance.lengthOfLongestSubstring("a"));
        }

        @Test
        @DisplayName("Scenario: \" \" → 1 (single space)")
        void testSingleSpace() {
            assertEquals(1, instance.lengthOfLongestSubstring(" "));
        }

        @Test
        @DisplayName("Scenario: \"aaaa\" → 1 (all same)")
        void testAllSame() {
            assertEquals(1, instance.lengthOfLongestSubstring("aaaa"));
        }

        @Test
        @DisplayName("Scenario: \"abcdef\" → 6 (all distinct, whole string)")
        void testAllDistinct() {
            assertEquals(6, instance.lengthOfLongestSubstring("abcdef"));
        }

        @Test
        @DisplayName("Scenario: \"abba\" → 2 (repeat of char already left of window)")
        void testAbba() {
            assertEquals(2, instance.lengthOfLongestSubstring("abba"));
        }

        @Test
        @DisplayName("Scenario: \"dvdf\" → 3 (window must jump past first duplicate)")
        void testDvdf() {
            assertEquals(3, instance.lengthOfLongestSubstring("dvdf"));
        }

        @Test
        @DisplayName("Scenario: \"tmmzuxt\" → 5 (stale earlier index of repeated char)")
        void testTmmzuxt() {
            assertEquals(5, instance.lengthOfLongestSubstring("tmmzuxt"));
        }

        @Test
        @DisplayName("Scenario: \"bbcbacba\" → 3 (reversed Example 1)")
        void testReversedExample1() {
            assertEquals(3, instance.lengthOfLongestSubstring("bbcbacba"));
        }

        @Test
        @DisplayName("Scenario: \"aAbB\" → 4 (uppercase and lowercase are distinct)")
        void testUpperAndLowerCase() {
            assertEquals(4, instance.lengthOfLongestSubstring("aAbB"));
        }

        @Test
        @DisplayName("Scenario: \"a1b2 c3!\" → 8 (digits, space and symbols)")
        void testDigitsSpacesSymbols() {
            assertEquals(8, instance.lengthOfLongestSubstring("a1b2 c3!"));
        }

        @Test
        @DisplayName("Scenario: \"a b a\" → 3 (space as a repeated character)")
        void testRepeatedSpace() {
            assertEquals(3, instance.lengthOfLongestSubstring("a b a"));
        }

        @Test
        @DisplayName("Scenario: all 95 printable ASCII chars → 95 (max distinct charset)")
        void testAllPrintableAscii() {
            assertEquals(95, instance.lengthOfLongestSubstring(printableAsciiCycle(95)));
        }

        @Test
        @DisplayName("Scenario: 50,000 × 'a' → 1 (max length, uniform)")
        void testMaxLengthUniform() {
            assertEquals(1, instance.lengthOfLongestSubstring("a".repeat(50_000)));
        }

        @Test
        @DisplayName("Scenario: 50,000-char cycle of 95 printable ASCII chars → 95 (max length)")
        void testMaxLengthCycle() {
            assertEquals(95, instance.lengthOfLongestSubstring(printableAsciiCycle(50_000)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  lengthOfLongestSubstringOptimised()  — last-seen index array, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("lengthOfLongestSubstringOptimised() — last-seen index array, O(n)")
    class OptimisedTests {

        @Test
        @DisplayName("LeetCode Example 1: \"abcabcbb\" → 3")
        void testLeetCodeExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringOptimised("abcabcbb"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"bbbbb\" → 1")
        void testLeetCodeExample2() {
            assertEquals(1, instance.lengthOfLongestSubstringOptimised("bbbbb"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"pwwkew\" → 3")
        void testLeetCodeExample3() {
            assertEquals(3, instance.lengthOfLongestSubstringOptimised("pwwkew"));
        }

        @Test
        @DisplayName("Scenario: \"\" → 0 (empty string)")
        void testEmptyString() {
            assertEquals(0, instance.lengthOfLongestSubstringOptimised(""));
        }

        @Test
        @DisplayName("Scenario: \"a\" → 1 (single character)")
        void testSingleCharacter() {
            assertEquals(1, instance.lengthOfLongestSubstringOptimised("a"));
        }

        @Test
        @DisplayName("Scenario: \" \" → 1 (single space)")
        void testSingleSpace() {
            assertEquals(1, instance.lengthOfLongestSubstringOptimised(" "));
        }

        @Test
        @DisplayName("Scenario: \"aaaa\" → 1 (all same)")
        void testAllSame() {
            assertEquals(1, instance.lengthOfLongestSubstringOptimised("aaaa"));
        }

        @Test
        @DisplayName("Scenario: \"abcdef\" → 6 (all distinct, whole string)")
        void testAllDistinct() {
            assertEquals(6, instance.lengthOfLongestSubstringOptimised("abcdef"));
        }

        @Test
        @DisplayName("Scenario: \"abba\" → 2 (repeat of char already left of window)")
        void testAbba() {
            assertEquals(2, instance.lengthOfLongestSubstringOptimised("abba"));
        }

        @Test
        @DisplayName("Scenario: \"dvdf\" → 3 (window must jump past first duplicate)")
        void testDvdf() {
            assertEquals(3, instance.lengthOfLongestSubstringOptimised("dvdf"));
        }

        @Test
        @DisplayName("Scenario: \"tmmzuxt\" → 5 (stale earlier index of repeated char)")
        void testTmmzuxt() {
            assertEquals(5, instance.lengthOfLongestSubstringOptimised("tmmzuxt"));
        }

        @Test
        @DisplayName("Scenario: \"bbcbacba\" → 3 (reversed Example 1)")
        void testReversedExample1() {
            assertEquals(3, instance.lengthOfLongestSubstringOptimised("bbcbacba"));
        }

        @Test
        @DisplayName("Scenario: \"aAbB\" → 4 (uppercase and lowercase are distinct)")
        void testUpperAndLowerCase() {
            assertEquals(4, instance.lengthOfLongestSubstringOptimised("aAbB"));
        }

        @Test
        @DisplayName("Scenario: \"a1b2 c3!\" → 8 (digits, space and symbols)")
        void testDigitsSpacesSymbols() {
            assertEquals(8, instance.lengthOfLongestSubstringOptimised("a1b2 c3!"));
        }

        @Test
        @DisplayName("Scenario: \"a b a\" → 3 (space as a repeated character)")
        void testRepeatedSpace() {
            assertEquals(3, instance.lengthOfLongestSubstringOptimised("a b a"));
        }

        @Test
        @DisplayName("Scenario: all 95 printable ASCII chars → 95 (max distinct charset)")
        void testAllPrintableAscii() {
            assertEquals(95, instance.lengthOfLongestSubstringOptimised(printableAsciiCycle(95)));
        }

        @Test
        @DisplayName("Scenario: 50,000 × 'a' → 1 (max length, uniform)")
        void testMaxLengthUniform() {
            assertEquals(1, instance.lengthOfLongestSubstringOptimised("a".repeat(50_000)));
        }

        @Test
        @DisplayName("Scenario: 50,000-char cycle of 95 printable ASCII chars → 95 (max length)")
        void testMaxLengthCycle() {
            assertEquals(95, instance.lengthOfLongestSubstringOptimised(printableAsciiCycle(50_000)));
        }
    }
}
