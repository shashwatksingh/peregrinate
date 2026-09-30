package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Backspace String Compare Tests")
class BackSpaceStringCompareTest {

    private BackSpaceStringCompare instance;

    @BeforeEach
    void setUp() {
        instance = new BackSpaceStringCompare();
    }

    // ═══════════════════════════════════════════════════════════
    //  backspaceCompareBruteSolution()  — stack-based simulation, O(n) time / O(n) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("backspaceCompareBruteSolution() — stack-based simulation, O(n) time / O(n) space")
    class BackspaceCompareBruteSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: s=\"ab#c\", t=\"ad#c\" → true (both become \"ac\")")
        void testLeetCodeExample1() {
            assertEquals(true, instance.backspaceCompareBruteSolution("ab#c", "ad#c"));
        }

        @Test
        @DisplayName("LeetCode Example 2: s=\"ab##\", t=\"c#d#\" → true (both become empty)")
        void testLeetCodeExample2() {
            assertEquals(true, instance.backspaceCompareBruteSolution("ab##", "c#d#"));
        }

        @Test
        @DisplayName("LeetCode Example 3: s=\"a#c\", t=\"b\" → false (\"c\" vs \"b\")")
        void testLeetCodeExample3() {
            assertEquals(false, instance.backspaceCompareBruteSolution("a#c", "b"));
        }

        @Test
        @DisplayName("Single chars: s=\"a\", t=\"a\" → true")
        void testSingleCharSame() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a", "a"));
        }

        @Test
        @DisplayName("Single chars: s=\"a\", t=\"b\" → false")
        void testSingleCharDifferent() {
            assertEquals(false, instance.backspaceCompareBruteSolution("a", "b"));
        }

        @Test
        @DisplayName("Only backspace: s=\"#\", t=\"#\" → true (both empty)")
        void testOnlyBackspaceBoth() {
            assertEquals(true, instance.backspaceCompareBruteSolution("#", "#"));
        }

        @Test
        @DisplayName("Char erased vs only backspace: s=\"a#\", t=\"#\" → true (both empty)")
        void testErasedVersusBackspace() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a#", "#"));
        }

        @Test
        @DisplayName("Backspace on empty text: s=\"#a\", t=\"a\" → true")
        void testLeadingBackspace() {
            assertEquals(true, instance.backspaceCompareBruteSolution("#a", "a"));
        }

        @Test
        @DisplayName("Multiple leading backspaces: s=\"##a\", t=\"a\" → true")
        void testMultipleLeadingBackspaces() {
            assertEquals(true, instance.backspaceCompareBruteSolution("##a", "a"));
        }

        @Test
        @DisplayName("Uniform strings: s=\"aaa\", t=\"aaa\" → true")
        void testUniformEqual() {
            assertEquals(true, instance.backspaceCompareBruteSolution("aaa", "aaa"));
        }

        @Test
        @DisplayName("Many backspaces vs one: s=\"###\", t=\"#\" → true (both empty)")
        void testBackspacesOnlyDifferentCounts() {
            assertEquals(true, instance.backspaceCompareBruteSolution("###", "#"));
        }

        @Test
        @DisplayName("Prefix only: s=\"abc\", t=\"ab\" → false")
        void testPrefixDifferentLength() {
            assertEquals(false, instance.backspaceCompareBruteSolution("abc", "ab"));
        }

        @Test
        @DisplayName("Same letters reordered: s=\"ab\", t=\"ba\" → false")
        void testReorderedLetters() {
            assertEquals(false, instance.backspaceCompareBruteSolution("ab", "ba"));
        }

        @Test
        @DisplayName("Trailing backspace: s=\"ab#\", t=\"a\" → true")
        void testTrailingBackspace() {
            assertEquals(true, instance.backspaceCompareBruteSolution("ab#", "a"));
        }

        @Test
        @DisplayName("Backspace in middle: s=\"a#b\", t=\"b\" → true")
        void testMiddleBackspace() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a#b", "b"));
        }

        @Test
        @DisplayName("Backspaces around char: s=\"a##b\", t=\"b\" → true")
        void testExtraBackspaceBeforeChar() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a##b", "b"));
        }

        @Test
        @DisplayName("Backspace run crosses multiple chars: s=\"abc###d\", t=\"d\" → true")
        void testRunErasesAllThenChar() {
            assertEquals(true, instance.backspaceCompareBruteSolution("abc###d", "d"));
        }

        @Test
        @DisplayName("Excess backspaces: s=\"a###b\", t=\"b\" → true")
        void testExcessBackspaces() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a###b", "b"));
        }

        @Test
        @DisplayName("Alternating: s=\"a#b#c#\", t=\"#\" → true (both empty)")
        void testAlternatingAllErased() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a#b#c#", "#"));
        }

        @Test
        @DisplayName("Erased vs survivor: s=\"ab#\", t=\"c#\" → false (\"a\" vs empty)")
        void testSurvivorVersusEmpty() {
            assertEquals(false, instance.backspaceCompareBruteSolution("ab#", "c#"));
        }

        @Test
        @DisplayName("Nested erase: s=\"bxj##tw\", t=\"bxo#j##tw\" → true (both \"btw\")")
        void testNestedEraseEqual() {
            assertEquals(true, instance.backspaceCompareBruteSolution("bxj##tw", "bxo#j##tw"));
        }

        @Test
        @DisplayName("Differing backspace count: s=\"bxj##tw\", t=\"bxj###tw\" → false (\"btw\" vs \"tw\")")
        void testDifferingBackspaceCount() {
            assertEquals(false, instance.backspaceCompareBruteSolution("bxj##tw", "bxj###tw"));
        }

        @Test
        @DisplayName("Empty vs non-empty: s=\"a#\", t=\"b\" → false")
        void testEmptyVersusNonEmpty() {
            assertEquals(false, instance.backspaceCompareBruteSolution("a#", "b"));
        }

        @Test
        @DisplayName("Max length: 200 × 'a' vs 200 × 'a' → true")
        void testMax200Equal() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a".repeat(200), "a".repeat(200)));
        }

        @Test
        @DisplayName("Max length: 100 × 'a' + 100 × '#' vs 100 × 'b' + 100 × '#' → true (both empty)")
        void testMax200AllErased() {
            assertEquals(true, instance.backspaceCompareBruteSolution("a".repeat(100) + "#".repeat(100), "b".repeat(100) + "#".repeat(100)));
        }

        @Test
        @DisplayName("Max length: 200 × '#' vs \"#\" → true")
        void testMax200Backspaces() {
            assertEquals(true, instance.backspaceCompareBruteSolution("#".repeat(200), "#"));
        }

        @Test
        @DisplayName("Max length: 199 × 'a' + 'b' vs 199 × 'a' + 'c' → false")
        void testMax200LastCharDiffers() {
            assertEquals(false, instance.backspaceCompareBruteSolution("a".repeat(199) + "b", "a".repeat(199) + "c"));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  backspaceCompare()  — two pointers from the end, O(n) time / O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("backspaceCompare() — two pointers from the end, O(n) time / O(1) space")
    class BackspaceCompareTests {

        @Test
        @DisplayName("LeetCode Example 1: s=\"ab#c\", t=\"ad#c\" → true (both become \"ac\")")
        void testLeetCodeExample1() {
            assertEquals(true, instance.backspaceCompare("ab#c", "ad#c"));
        }

        @Test
        @DisplayName("LeetCode Example 2: s=\"ab##\", t=\"c#d#\" → true (both become empty)")
        void testLeetCodeExample2() {
            assertEquals(true, instance.backspaceCompare("ab##", "c#d#"));
        }

        @Test
        @DisplayName("LeetCode Example 3: s=\"a#c\", t=\"b\" → false (\"c\" vs \"b\")")
        void testLeetCodeExample3() {
            assertEquals(false, instance.backspaceCompare("a#c", "b"));
        }

        @Test
        @DisplayName("Single chars: s=\"a\", t=\"a\" → true")
        void testSingleCharSame() {
            assertEquals(true, instance.backspaceCompare("a", "a"));
        }

        @Test
        @DisplayName("Single chars: s=\"a\", t=\"b\" → false")
        void testSingleCharDifferent() {
            assertEquals(false, instance.backspaceCompare("a", "b"));
        }

        @Test
        @DisplayName("Only backspace: s=\"#\", t=\"#\" → true (both empty)")
        void testOnlyBackspaceBoth() {
            assertEquals(true, instance.backspaceCompare("#", "#"));
        }

        @Test
        @DisplayName("Char erased vs only backspace: s=\"a#\", t=\"#\" → true (both empty)")
        void testErasedVersusBackspace() {
            assertEquals(true, instance.backspaceCompare("a#", "#"));
        }

        @Test
        @DisplayName("Backspace on empty text: s=\"#a\", t=\"a\" → true")
        void testLeadingBackspace() {
            assertEquals(true, instance.backspaceCompare("#a", "a"));
        }

        @Test
        @DisplayName("Multiple leading backspaces: s=\"##a\", t=\"a\" → true")
        void testMultipleLeadingBackspaces() {
            assertEquals(true, instance.backspaceCompare("##a", "a"));
        }

        @Test
        @DisplayName("Uniform strings: s=\"aaa\", t=\"aaa\" → true")
        void testUniformEqual() {
            assertEquals(true, instance.backspaceCompare("aaa", "aaa"));
        }

        @Test
        @DisplayName("Many backspaces vs one: s=\"###\", t=\"#\" → true (both empty)")
        void testBackspacesOnlyDifferentCounts() {
            assertEquals(true, instance.backspaceCompare("###", "#"));
        }

        @Test
        @DisplayName("Prefix only: s=\"abc\", t=\"ab\" → false")
        void testPrefixDifferentLength() {
            assertEquals(false, instance.backspaceCompare("abc", "ab"));
        }

        @Test
        @DisplayName("Same letters reordered: s=\"ab\", t=\"ba\" → false")
        void testReorderedLetters() {
            assertEquals(false, instance.backspaceCompare("ab", "ba"));
        }

        @Test
        @DisplayName("Trailing backspace: s=\"ab#\", t=\"a\" → true")
        void testTrailingBackspace() {
            assertEquals(true, instance.backspaceCompare("ab#", "a"));
        }

        @Test
        @DisplayName("Backspace in middle: s=\"a#b\", t=\"b\" → true")
        void testMiddleBackspace() {
            assertEquals(true, instance.backspaceCompare("a#b", "b"));
        }

        @Test
        @DisplayName("Backspaces around char: s=\"a##b\", t=\"b\" → true")
        void testExtraBackspaceBeforeChar() {
            assertEquals(true, instance.backspaceCompare("a##b", "b"));
        }

        @Test
        @DisplayName("Backspace run crosses multiple chars: s=\"abc###d\", t=\"d\" → true")
        void testRunErasesAllThenChar() {
            assertEquals(true, instance.backspaceCompare("abc###d", "d"));
        }

        @Test
        @DisplayName("Excess backspaces: s=\"a###b\", t=\"b\" → true")
        void testExcessBackspaces() {
            assertEquals(true, instance.backspaceCompare("a###b", "b"));
        }

        @Test
        @DisplayName("Alternating: s=\"a#b#c#\", t=\"#\" → true (both empty)")
        void testAlternatingAllErased() {
            assertEquals(true, instance.backspaceCompare("a#b#c#", "#"));
        }

        @Test
        @DisplayName("Erased vs survivor: s=\"ab#\", t=\"c#\" → false (\"a\" vs empty)")
        void testSurvivorVersusEmpty() {
            assertEquals(false, instance.backspaceCompare("ab#", "c#"));
        }

        @Test
        @DisplayName("Nested erase: s=\"bxj##tw\", t=\"bxo#j##tw\" → true (both \"btw\")")
        void testNestedEraseEqual() {
            assertEquals(true, instance.backspaceCompare("bxj##tw", "bxo#j##tw"));
        }

        @Test
        @DisplayName("Differing backspace count: s=\"bxj##tw\", t=\"bxj###tw\" → false (\"btw\" vs \"tw\")")
        void testDifferingBackspaceCount() {
            assertEquals(false, instance.backspaceCompare("bxj##tw", "bxj###tw"));
        }

        @Test
        @DisplayName("Empty vs non-empty: s=\"a#\", t=\"b\" → false")
        void testEmptyVersusNonEmpty() {
            assertEquals(false, instance.backspaceCompare("a#", "b"));
        }

        @Test
        @DisplayName("Max length: 200 × 'a' vs 200 × 'a' → true")
        void testMax200Equal() {
            assertEquals(true, instance.backspaceCompare("a".repeat(200), "a".repeat(200)));
        }

        @Test
        @DisplayName("Max length: 100 × 'a' + 100 × '#' vs 100 × 'b' + 100 × '#' → true (both empty)")
        void testMax200AllErased() {
            assertEquals(true, instance.backspaceCompare("a".repeat(100) + "#".repeat(100), "b".repeat(100) + "#".repeat(100)));
        }

        @Test
        @DisplayName("Max length: 200 × '#' vs \"#\" → true")
        void testMax200Backspaces() {
            assertEquals(true, instance.backspaceCompare("#".repeat(200), "#"));
        }

        @Test
        @DisplayName("Max length: 199 × 'a' + 'b' vs 199 × 'a' + 'c' → false")
        void testMax200LastCharDiffers() {
            assertEquals(false, instance.backspaceCompare("a".repeat(199) + "b", "a".repeat(199) + "c"));
        }
    }
}
