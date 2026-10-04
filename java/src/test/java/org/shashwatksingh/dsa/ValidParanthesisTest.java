package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Valid Parentheses Tests")
class ValidParanthesisTest {

    private ValidParanthesis instance;

    @BeforeEach
    void setUp() {
        instance = new ValidParanthesis();
    }

    // ═══════════════════════════════════════════════════════════
    //  isValid()  — stack, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isValid() — stack-based bracket matching, O(n)")
    class IsValidTests {

        @Test
        @DisplayName("LeetCode Example 1: \"()\" → true")
        void testLeetCodeExample1() {
            assertEquals(true, instance.isValid("()"));
        }

        @Test
        @DisplayName("LeetCode Example 2: \"()[]{}\" → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.isValid("()[]{}"));
        }

        @Test
        @DisplayName("LeetCode Example 3: \"(]\" → false")
        void testLeetCodeExample3() {
            assertEquals(false, instance.isValid("(]"));
        }

        @Test
        @DisplayName("LeetCode Example 4: \"([])\" → true")
        void testLeetCodeExample4() {
            assertEquals(true, instance.isValid("([])"));
        }

        @Test
        @DisplayName("LeetCode Example 5: \"([)]\" → false")
        void testLeetCodeExample5() {
            assertEquals(false, instance.isValid("([)]"));
        }

        @Test
        @DisplayName("Scenario: \"(\" → false (single open bracket)")
        void testSingleOpen() {
            assertEquals(false, instance.isValid("("));
        }

        @Test
        @DisplayName("Scenario: \")\" → false (single close bracket)")
        void testSingleClose() {
            assertEquals(false, instance.isValid(")"));
        }

        @Test
        @DisplayName("Scenario: \"]\" → false (single close square bracket)")
        void testSingleCloseSquare() {
            assertEquals(false, instance.isValid("]"));
        }

        @Test
        @DisplayName("Scenario: \"}\" → false (single close curly bracket)")
        void testSingleCloseCurly() {
            assertEquals(false, instance.isValid("}"));
        }

        @Test
        @DisplayName("Scenario: \"((((\" → false (all open, never closed)")
        void testAllOpen() {
            assertEquals(false, instance.isValid("(((("));
        }

        @Test
        @DisplayName("Scenario: \"))))\" → false (all close, never opened)")
        void testAllClose() {
            assertEquals(false, instance.isValid("))))"));
        }

        @Test
        @DisplayName("Scenario: \"()()()\" → true (repeated pairs of same type)")
        void testRepeatedPairs() {
            assertEquals(true, instance.isValid("()()()"));
        }

        @Test
        @DisplayName("Scenario: \"((()))\" → true (deeply nested, same type)")
        void testNestedSameType() {
            assertEquals(true, instance.isValid("((()))"));
        }

        @Test
        @DisplayName("Scenario: \"{[()]}\" → true (nested, all three types)")
        void testNestedAllTypes() {
            assertEquals(true, instance.isValid("{[()]}"));
        }

        @Test
        @DisplayName("Scenario: \"([{}])\" → true (nested, reverse type order)")
        void testNestedReverseTypeOrder() {
            assertEquals(true, instance.isValid("([{}])"));
        }

        @Test
        @DisplayName("Scenario: \"(){}[]\" → true (one pair of each type in sequence)")
        void testSequentialAllTypes() {
            assertEquals(true, instance.isValid("(){}[]"));
        }

        @Test
        @DisplayName("Scenario: \"(()\" → false (unclosed open bracket)")
        void testUnclosedOpen() {
            assertEquals(false, instance.isValid("(()"));
        }

        @Test
        @DisplayName("Scenario: \"())\" → false (extra close bracket)")
        void testExtraClose() {
            assertEquals(false, instance.isValid("())"));
        }

        @Test
        @DisplayName("Scenario: \")(\" → false (close before open)")
        void testCloseBeforeOpen() {
            assertEquals(false, instance.isValid(")("));
        }

        @Test
        @DisplayName("Scenario: \"}{\" → false (curly close before open)")
        void testCurlyCloseBeforeOpen() {
            assertEquals(false, instance.isValid("}{"));
        }

        @Test
        @DisplayName("Scenario: \"){}\" → false (leading close followed by valid pair)")
        void testLeadingCloseThenValidPair() {
            assertEquals(false, instance.isValid("){}"));
        }

        @Test
        @DisplayName("Scenario: \"{[}]\" → false (interleaved curly and square)")
        void testInterleavedCurlySquare() {
            assertEquals(false, instance.isValid("{[}]"));
        }

        @Test
        @DisplayName("Scenario: \"{[(])}\" → false (mismatch deep inside nesting)")
        void testMismatchInsideNesting() {
            assertEquals(false, instance.isValid("{[(])}"));
        }

        @Test
        @DisplayName("Scenario: \"(}\" → false (wrong close type, curly)")
        void testWrongCloseCurly() {
            assertEquals(false, instance.isValid("(}"));
        }

        @Test
        @DisplayName("Scenario: \"{)\" → false (wrong close type, round)")
        void testWrongCloseRound() {
            assertEquals(false, instance.isValid("{)"));
        }

        @Test
        @DisplayName("Scenario: \"()]\" → false (valid pair followed by stray close)")
        void testValidPairThenStrayClose() {
            assertEquals(false, instance.isValid("()]"));
        }

        @Test
        @DisplayName("Scenario: \"(\" × 10,000 → false (max length, all open)")
        void testMaxLengthAllOpen() {
            assertEquals(false, instance.isValid("(".repeat(10_000)));
        }

        @Test
        @DisplayName("Scenario: \")\" × 10,000 → false (max length, all close)")
        void testMaxLengthAllClose() {
            assertEquals(false, instance.isValid(")".repeat(10_000)));
        }

        @Test
        @DisplayName("Scenario: \"()\" × 5,000 → true (max length, repeated pairs)")
        void testMaxLengthRepeatedPairs() {
            assertEquals(true, instance.isValid("()".repeat(5_000)));
        }

        @Test
        @DisplayName("Scenario: \"[\" × 5,000 + \"]\" × 5,000 → true (max length, fully nested)")
        void testMaxLengthFullyNested() {
            assertEquals(true, instance.isValid("[".repeat(5_000) + "]".repeat(5_000)));
        }

        @Test
        @DisplayName("Scenario: \"{\" × 5,000 + \"]\" × 5,000 → false (max length, wrong close type)")
        void testMaxLengthWrongCloseType() {
            assertEquals(false, instance.isValid("{".repeat(5_000) + "]".repeat(5_000)));
        }
    }
}
