package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Valid Palindrome Tests")
class ValidPalindromeTest {

    private ValidPalindrome instance;

    @BeforeEach
    void setUp() {
        instance = new ValidPalindrome();
    }

    // ═══════════════════════════════════════════════════════════
    //  bruteForce()  — build filtered/lowercased string, compare to its reverse, O(n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("bruteForce() — filter + reverse comparison")
    class BruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [\"A man, a plan, a canal: Panama\"] → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.bruteForce("A man, a plan, a canal: Panama"));
        }

        @Test
        @DisplayName("LeetCode Example 2: [\"race a car\"] → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.bruteForce("race a car"));
        }

        @Test
        @DisplayName("LeetCode Example 3: [\" \"] → true (empty after filtering)")
        void testLeetCodeExample3() {
            assertEquals(true, instance.bruteForce(" "));
        }

        @Test
        @DisplayName("Single-character input: [\"a\"] → true")
        void testSingleCharacter() {
            assertEquals(true, instance.bruteForce("a"));
        }

        @Test
        @DisplayName("Empty string: [\"\"] → true")
        void testEmptyString() {
            assertEquals(true, instance.bruteForce(""));
        }

        @Test
        @DisplayName("All-same characters: [\"aaaa\"] → true")
        void testAllSameCharacters() {
            assertEquals(true, instance.bruteForce("aaaa"));
        }

        @Test
        @DisplayName("Case-insensitive match: [\"Aa\"] → true")
        void testCaseInsensitiveMatch() {
            assertEquals(true, instance.bruteForce("Aa"));
        }

        @Test
        @DisplayName("Only non-alphanumeric characters: [\".,!?\"] → true (empty after filtering)")
        void testOnlyNonAlphanumericCharacters() {
            assertEquals(true, instance.bruteForce(".,!?"));
        }

        @Test
        @DisplayName("Strictly non-palindromic sequence: [\"abcdefg\"] → false")
        void testStrictlyNonPalindromicSequence() {
            assertEquals(false, instance.bruteForce("abcdefg"));
        }

        @Test
        @DisplayName("Numeric palindrome: [\"12321\"] → true")
        void testNumericPalindrome() {
            assertEquals(true, instance.bruteForce("12321"));
        }

        @Test
        @DisplayName("Digit/letter mismatch: [\"0P\"] → false (no valid answer despite same length)")
        void testDigitLetterMismatch() {
            assertEquals(false, instance.bruteForce("0P"));
        }

        @Test
        @DisplayName("Palindrome with punctuation and mixed case: [\"Was it a car or a cat I saw?\"] → true")
        void testPalindromeWithPunctuationAndMixedCase() {
            assertEquals(true, instance.bruteForce("Was it a car or a cat I saw?"));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  twoPointerSolution()  — two pointers skipping non-alphanumeric chars, O(n) / O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("twoPointerSolution() — two-pointer in-place comparison")
    class TwoPointerSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [\"A man, a plan, a canal: Panama\"] → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.twoPointerSolution("A man, a plan, a canal: Panama"));
        }

        @Test
        @DisplayName("LeetCode Example 2: [\"race a car\"] → false")
        void testLeetCodeExample2() {
            assertEquals(false, instance.twoPointerSolution("race a car"));
        }

        @Test
        @DisplayName("LeetCode Example 3: [\" \"] → true (empty after filtering)")
        void testLeetCodeExample3() {
            assertEquals(true, instance.twoPointerSolution(" "));
        }

        @Test
        @DisplayName("Single-character input: [\"a\"] → true")
        void testSingleCharacter() {
            assertEquals(true, instance.twoPointerSolution("a"));
        }

        @Test
        @DisplayName("Empty string: [\"\"] → true")
        void testEmptyString() {
            assertEquals(true, instance.twoPointerSolution(""));
        }

        @Test
        @DisplayName("All-same characters: [\"aaaa\"] → true")
        void testAllSameCharacters() {
            assertEquals(true, instance.twoPointerSolution("aaaa"));
        }

        @Test
        @DisplayName("Case-insensitive match: [\"Aa\"] → true")
        void testCaseInsensitiveMatch() {
            assertEquals(true, instance.twoPointerSolution("Aa"));
        }

        @Test
        @DisplayName("Only non-alphanumeric characters: [\".,!?\"] → true (empty after filtering)")
        void testOnlyNonAlphanumericCharacters() {
            assertEquals(true, instance.twoPointerSolution(".,!?"));
        }

        @Test
        @DisplayName("Strictly non-palindromic sequence: [\"abcdefg\"] → false")
        void testStrictlyNonPalindromicSequence() {
            assertEquals(false, instance.twoPointerSolution("abcdefg"));
        }

        @Test
        @DisplayName("Numeric palindrome: [\"12321\"] → true")
        void testNumericPalindrome() {
            assertEquals(true, instance.twoPointerSolution("12321"));
        }

        @Test
        @DisplayName("Digit/letter mismatch: [\"0P\"] → false (no valid answer despite same length)")
        void testDigitLetterMismatch() {
            assertEquals(false, instance.twoPointerSolution("0P"));
        }

        @Test
        @DisplayName("Palindrome with punctuation and mixed case: [\"Was it a car or a cat I saw?\"] → true")
        void testPalindromeWithPunctuationAndMixedCase() {
            assertEquals(true, instance.twoPointerSolution("Was it a car or a cat I saw?"));
        }
    }
}
