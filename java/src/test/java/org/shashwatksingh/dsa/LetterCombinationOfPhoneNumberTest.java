package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Letter Combination Of Phone Number Tests")
class LetterCombinationOfPhoneNumberTest {

    private LetterCombinationOfPhoneNumber lc;

    @BeforeEach
    void setUp() {
        lc = new LetterCombinationOfPhoneNumber();
    }

    // helper: sort both lists before comparing (problem allows any order)
    private void assertSameCombinations(List<String> expected, List<String> actual) {
        Collections.sort(expected);
        Collections.sort(actual);
        assertEquals(expected, actual);
    }

    // ═══ LeetCode Examples ═══════════════════════════════════════════════
    @Nested
    @DisplayName("LeetCode examples")
    class LeetCodeExamples {

        @Test
        @DisplayName("Example 1: \"23\" → [\"ad\",\"ae\",\"af\",\"bd\",\"be\",\"bf\",\"cd\",\"ce\",\"cf\"]")
        void testExample1() {
            List<String> expected = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
            assertSameCombinations(expected, lc.solution("23"));
        }

        @Test
        @DisplayName("Example 2: \"2\" → [\"a\",\"b\",\"c\"]")
        void testExample2() {
            assertSameCombinations(Arrays.asList("a", "b", "c"), lc.solution("2"));
        }
    }

    // ═══ Single-digit inputs — verifies each phone key mapping ════════════
    @Nested
    @DisplayName("Single-digit inputs (phone key mappings)")
    class SingleDigitMappings {

        @Test
        @DisplayName("Digit \"2\" maps to [\"a\",\"b\",\"c\"]")
        void testDigit2() {
            assertSameCombinations(Arrays.asList("a", "b", "c"), lc.solution("2"));
        }

        @Test
        @DisplayName("Digit \"3\" maps to [\"d\",\"e\",\"f\"]")
        void testDigit3() {
            assertSameCombinations(Arrays.asList("d", "e", "f"), lc.solution("3"));
        }

        @Test
        @DisplayName("Digit \"4\" maps to [\"g\",\"h\",\"i\"]")
        void testDigit4() {
            assertSameCombinations(Arrays.asList("g", "h", "i"), lc.solution("4"));
        }

        @Test
        @DisplayName("Digit \"5\" maps to [\"j\",\"k\",\"l\"]")
        void testDigit5() {
            assertSameCombinations(Arrays.asList("j", "k", "l"), lc.solution("5"));
        }

        @Test
        @DisplayName("Digit \"6\" maps to [\"m\",\"n\",\"o\"]")
        void testDigit6() {
            assertSameCombinations(Arrays.asList("m", "n", "o"), lc.solution("6"));
        }

        @Test
        @DisplayName("Digit \"7\" maps to [\"p\",\"q\",\"r\",\"s\"] (4 letters)")
        void testDigit7() {
            assertSameCombinations(Arrays.asList("p", "q", "r", "s"), lc.solution("7"));
        }

        @Test
        @DisplayName("Digit \"8\" maps to [\"t\",\"u\",\"v\"]")
        void testDigit8() {
            assertSameCombinations(Arrays.asList("t", "u", "v"), lc.solution("8"));
        }

        @Test
        @DisplayName("Digit \"9\" maps to [\"w\",\"x\",\"y\",\"z\"] (4 letters)")
        void testDigit9() {
            assertSameCombinations(Arrays.asList("w", "x", "y", "z"), lc.solution("9"));
        }
    }

    // ═══ Result size correctness ══════════════════════════════════════════
    @Nested
    @DisplayName("Result size correctness")
    class ResultSizeTests {

        @Test
        @DisplayName("Single digit \"2\" → 3 combinations (3^1)")
        void testSingleDigitSize() {
            assertEquals(3, lc.solution("2").size());
        }

        @Test
        @DisplayName("Two digits \"23\" → 9 combinations (3×3)")
        void testTwoDigitsSize() {
            assertEquals(9, lc.solution("23").size());
        }

        @Test
        @DisplayName("Three digits \"234\" → 27 combinations (3×3×3)")
        void testThreeDigitsSize() {
            assertEquals(27, lc.solution("234").size());
        }

        @Test
        @DisplayName("Four digits \"2345\" → 81 combinations (3×3×3×3)")
        void testFourDigitsSize() {
            assertEquals(81, lc.solution("2345").size());
        }

        @Test
        @DisplayName("Two 4-letter digits \"79\" → 16 combinations (4×4)")
        void testFourLetterDigitsSize() {
            assertEquals(16, lc.solution("79").size());
        }

        @Test
        @DisplayName("Same digit repeated \"77\" → 16 combinations (4×4)")
        void testSameDigitRepeatedSize() {
            assertEquals(16, lc.solution("77").size());
        }
    }

    // ═══ Combinations involving 4-letter keys (7 and 9) ══════════════════
    @Nested
    @DisplayName("Inputs involving 4-letter keys (7 and 9)")
    class FourLetterKeyTests {

        @Test
        @DisplayName("\"72\" → 12 combinations (4×3), all strings verified")
        void testDigit7and2() {
            List<String> expected = Arrays.asList(
                    "pa", "pb", "pc", "qa", "qb", "qc",
                    "ra", "rb", "rc", "sa", "sb", "sc"
            );
            assertSameCombinations(expected, lc.solution("72"));
        }

        @Test
        @DisplayName("\"29\" → 12 combinations (3×4), all strings verified")
        void testDigit2and9() {
            List<String> expected = Arrays.asList(
                    "aw", "ax", "ay", "az",
                    "bw", "bx", "by", "bz",
                    "cw", "cx", "cy", "cz"
            );
            assertSameCombinations(expected, lc.solution("29"));
        }
    }

    // ═══ Combination length correctness ══════════════════════════════════
    @Nested
    @DisplayName("Each combination has the correct length")
    class CombinationLengthTests {

        @Test
        @DisplayName("Each string in result of \"23\" has length 2")
        void testCombinationLengthForTwoDigits() {
            assertTrue(lc.solution("23").stream().allMatch(s -> s.length() == 2),
                    "Every combination should have length equal to the number of digits");
        }

        @Test
        @DisplayName("Each string in result of \"234\" has length 3")
        void testCombinationLengthForThreeDigits() {
            assertTrue(lc.solution("234").stream().allMatch(s -> s.length() == 3),
                    "Every combination should have length equal to the number of digits");
        }

        @Test
        @DisplayName("Each string in result of \"2345\" has length 4")
        void testCombinationLengthForFourDigits() {
            assertTrue(lc.solution("2345").stream().allMatch(s -> s.length() == 4),
                    "Every combination should have length equal to the number of digits");
        }
    }

    // ═══ No duplicate combinations ════════════════════════════════════════
    @Nested
    @DisplayName("No duplicate combinations in result")
    class NoDuplicatesTests {

        @Test
        @DisplayName("\"23\" produces no duplicate combinations")
        void testNoDuplicatesForTwoDigits() {
            List<String> result = lc.solution("23");
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate combinations");
        }

        @Test
        @DisplayName("\"79\" produces no duplicate combinations")
        void testNoDuplicatesForFourLetterKeys() {
            List<String> result = lc.solution("79");
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate combinations");
        }

        @Test
        @DisplayName("\"2345\" produces no duplicate combinations")
        void testNoDuplicatesForFourDigits() {
            List<String> result = lc.solution("2345");
            assertEquals(result.size(), result.stream().distinct().count(),
                    "Result should not contain duplicate combinations");
        }
    }
}