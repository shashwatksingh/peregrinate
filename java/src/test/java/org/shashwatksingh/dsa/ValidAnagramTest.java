package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ValidAnagram Tests")
class ValidAnagramTest {

    private ValidAnagram validAnagram;

    @BeforeEach
    void setUp() {
        validAnagram = new ValidAnagram();
    }

    // -------------------------------------------------------------------------
    // sortAndCompare — sorts both char arrays and compares them
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("sortAndCompare (Sort + Arrays.equals approach)")
    class SortAndCompareTests {

        @Test
        @DisplayName("Example 1: \"anagram\" / \"nagaram\" → true")
        void testAnagramExample1() {
            assertTrue(validAnagram.sortAndCompare("anagram", "nagaram"));
        }

        @Test
        @DisplayName("Example 2: \"rat\" / \"car\" → false")
        void testNonAnagramExample2() {
            assertFalse(validAnagram.sortAndCompare("rat", "car"));
        }

        @Test
        @DisplayName("Same string \"listen\" / \"listen\" → true")
        void testSameString() {
            assertTrue(validAnagram.sortAndCompare("listen", "listen"));
        }

        @Test
        @DisplayName("Classic anagram: \"listen\" / \"silent\" → true")
        void testClassicAnagram() {
            assertTrue(validAnagram.sortAndCompare("listen", "silent"));
        }

        @Test
        @DisplayName("Different lengths \"ab\" / \"abc\" → false")
        void testDifferentLengths() {
            assertFalse(validAnagram.sortAndCompare("ab", "abc"));
        }

        @Test
        @DisplayName("Single character same \"a\" / \"a\" → true")
        void testSingleCharSame() {
            assertTrue(validAnagram.sortAndCompare("a", "a"));
        }

        @Test
        @DisplayName("Single character different \"a\" / \"b\" → false")
        void testSingleCharDifferent() {
            assertFalse(validAnagram.sortAndCompare("a", "b"));
        }

        @Test
        @DisplayName("All same letters same count \"aaa\" / \"aaa\" → true")
        void testAllSameLetters() {
            assertTrue(validAnagram.sortAndCompare("aaa", "aaa"));
        }

        @Test
        @DisplayName("Same letters different counts \"aab\" / \"abb\" → false")
        void testSameLettersDifferentCounts() {
            assertFalse(validAnagram.sortAndCompare("aab", "abb"));
        }

        @Test
        @DisplayName("Near-anagram off by one char \"anagram\" / \"nagaramx\" → false")
        void testNearAnagramDifferentLength() {
            assertFalse(validAnagram.sortAndCompare("anagram", "nagaramx"));
        }

        @Test
        @DisplayName("Empty strings \"\" / \"\" → true")
        void testEmptyStrings() {
            assertTrue(validAnagram.sortAndCompare("", ""));
        }
    }

    // -------------------------------------------------------------------------
    // characterMap — two int[26] frequency arrays
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("characterMap (Two int[26] frequency arrays)")
    class CharacterMapTests {

        @Test
        @DisplayName("Example 1: \"anagram\" / \"nagaram\" → true")
        void testAnagramExample1() {
            assertTrue(validAnagram.characterMap("anagram", "nagaram"));
        }

        @Test
        @DisplayName("Example 2: \"rat\" / \"car\" → false")
        void testNonAnagramExample2() {
            assertFalse(validAnagram.characterMap("rat", "car"));
        }

        @Test
        @DisplayName("Same string \"listen\" / \"listen\" → true")
        void testSameString() {
            assertTrue(validAnagram.characterMap("listen", "listen"));
        }

        @Test
        @DisplayName("Classic anagram: \"listen\" / \"silent\" → true")
        void testClassicAnagram() {
            assertTrue(validAnagram.characterMap("listen", "silent"));
        }

        @Test
        @DisplayName("Different lengths \"ab\" / \"abc\" → false (early exit)")
        void testDifferentLengths() {
            assertFalse(validAnagram.characterMap("ab", "abc"));
        }

        @Test
        @DisplayName("Single character same \"z\" / \"z\" → true")
        void testSingleCharSame() {
            assertTrue(validAnagram.characterMap("z", "z"));
        }

        @Test
        @DisplayName("Single character different \"a\" / \"b\" → false")
        void testSingleCharDifferent() {
            assertFalse(validAnagram.characterMap("a", "b"));
        }

        @Test
        @DisplayName("All same letters same count \"aaa\" / \"aaa\" → true")
        void testAllSameLetters() {
            assertTrue(validAnagram.characterMap("aaa", "aaa"));
        }

        @Test
        @DisplayName("Same letters different counts \"aab\" / \"abb\" → false")
        void testSameLettersDifferentCounts() {
            assertFalse(validAnagram.characterMap("aab", "abb"));
        }

        @Test
        @DisplayName("All 26 letters: \"abcdefghijklmnopqrstuvwxyz\" reversed → true")
        void testAllAlphabetLetters() {
            assertTrue(validAnagram.characterMap("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba"));
        }

        @Test
        @DisplayName("Empty strings \"\" / \"\" → true")
        void testEmptyStrings() {
            assertTrue(validAnagram.characterMap("", ""));
        }
    }


    // -------------------------------------------------------------------------
    // hashmapSolution — HashMap<Character, Integer> frequency map
    // NOTE: Known bug — if t contains a character not in s, map.get(ch)
    // returns null and throws NullPointerException. Tests below document this.
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("hashmapSolution (HashMap frequency map)")
    class HashmapSolutionTests {

        @Test
        @DisplayName("Example 1: \"anagram\" / \"nagaram\" → true")
        void testAnagramExample1() {
            assertTrue(validAnagram.hashmapSolution("anagram", "nagaram"));
        }

        @Test
        @DisplayName("Same letters different counts \"aab\" / \"aaa\" → false")
        void testSameLettersDifferentCounts() {
            assertFalse(validAnagram.hashmapSolution("aab", "aaa"));
        }

        @Test
        @DisplayName("Same string \"listen\" / \"listen\" → true")
        void testSameString() {
            assertTrue(validAnagram.hashmapSolution("listen", "listen"));
        }

        @Test
        @DisplayName("Classic anagram: \"listen\" / \"silent\" → true")
        void testClassicAnagram() {
            assertTrue(validAnagram.hashmapSolution("listen", "silent"));
        }

        @Test
        @DisplayName("Single character same \"a\" / \"a\" → true")
        void testSingleCharSame() {
            assertTrue(validAnagram.hashmapSolution("a", "a"));
        }

        @Test
        @DisplayName("t has more occurrences than s: \"ab\" / \"aa\" → false")
        void testTHasMoreOccurrences() {
            assertFalse(validAnagram.hashmapSolution("ab", "aa"));
        }

        @Test
        @DisplayName("BUG: t has a char not in s → NullPointerException (\"rat\" / \"car\")")
        void testNullPointerWhenTHasCharNotInS() {
            // 'c' is absent from "rat", so map.get('c') returns null → NPE
            assertThrows(NullPointerException.class,
                    () -> validAnagram.hashmapSolution("rat", "car"));
        }

        @Test
        @DisplayName("BUG: t is longer with a new char → NullPointerException (\"ab\" / \"abc\")")
        void testNullPointerOnDifferentLength() {
            // 'c' is absent from "ab", so map.get('c') returns null → NPE
            assertThrows(NullPointerException.class,
                    () -> validAnagram.hashmapSolution("ab", "abc"));
        }
    }


    // -------------------------------------------------------------------------
    // characterMapOptimised — single int[26], increment for s, decrement for t
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("characterMapOptimised (Single int[26] optimised)")
    class CharacterMapOptimisedTests {

        @Test
        @DisplayName("Example 1: \"anagram\" / \"nagaram\" → true")
        void testAnagramExample1() {
            assertTrue(validAnagram.characterMapOptimised("anagram", "nagaram"));
        }

        @Test
        @DisplayName("Example 2: \"rat\" / \"car\" → false")
        void testNonAnagramExample2() {
            assertFalse(validAnagram.characterMapOptimised("rat", "car"));
        }

        @Test
        @DisplayName("Same string \"listen\" / \"listen\" → true")
        void testSameString() {
            assertTrue(validAnagram.characterMapOptimised("listen", "listen"));
        }

        @Test
        @DisplayName("Classic anagram: \"listen\" / \"silent\" → true")
        void testClassicAnagram() {
            assertTrue(validAnagram.characterMapOptimised("listen", "silent"));
        }

        @Test
        @DisplayName("Different lengths \"ab\" / \"abc\" → false (early exit)")
        void testDifferentLengths() {
            assertFalse(validAnagram.characterMapOptimised("ab", "abc"));
        }

        @Test
        @DisplayName("Single character same \"z\" / \"z\" → true")
        void testSingleCharSame() {
            assertTrue(validAnagram.characterMapOptimised("z", "z"));
        }

        @Test
        @DisplayName("Single character different \"a\" / \"b\" → false")
        void testSingleCharDifferent() {
            assertFalse(validAnagram.characterMapOptimised("a", "b"));
        }

        @Test
        @DisplayName("All same letters same count \"aaa\" / \"aaa\" → true")
        void testAllSameLetters() {
            assertTrue(validAnagram.characterMapOptimised("aaa", "aaa"));
        }

        @Test
        @DisplayName("Same letters different counts \"aab\" / \"abb\" → false")
        void testSameLettersDifferentCounts() {
            assertFalse(validAnagram.characterMapOptimised("aab", "abb"));
        }

        @Test
        @DisplayName("All 26 letters: \"abcdefghijklmnopqrstuvwxyz\" reversed → true")
        void testAllAlphabetLetters() {
            assertTrue(validAnagram.characterMapOptimised("abcdefghijklmnopqrstuvwxyz", "zyxwvutsrqponmlkjihgfedcba"));
        }

        @Test
        @DisplayName("Empty strings \"\" / \"\" → true")
        void testEmptyStrings() {
            assertTrue(validAnagram.characterMapOptimised("", ""));
        }
    }
}

