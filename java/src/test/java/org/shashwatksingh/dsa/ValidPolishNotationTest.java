package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Evaluate Reverse Polish Notation Tests")
class ValidPolishNotationTest {

    private ValidPolishNotation instance;

    @BeforeEach
    void setUp() {
        instance = new ValidPolishNotation();
    }

    /** "1", then ("1", op) repeated pairs times: 1 op 1 op 1 ... (left-leaning). */
    private static String[] leftLeaning(int pairs, String op) {
        List<String> tokens = new ArrayList<>();
        tokens.add("1");
        for (int i = 0; i < pairs; i++) {
            tokens.add("1");
            tokens.add(op);
        }
        return tokens.toArray(new String[0]);
    }

    /** operands "1" × (count + 1) followed by op × count (right-leaning, deepest stack). */
    private static String[] rightLeaning(int count, String op) {
        List<String> tokens = new ArrayList<>();
        for (int i = 0; i <= count; i++) {
            tokens.add("1");
        }
        for (int i = 0; i < count; i++) {
            tokens.add(op);
        }
        return tokens.toArray(new String[0]);
    }

    // ═══════════════════════════════════════════════════════════
    //  evalRPN()  — stack, O(n) time
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("evalRPN() — stack-based reverse polish evaluation, O(n)")
    class EvalRPNTests {

        @Test
        @DisplayName("LeetCode Example 1: [\"2\",\"1\",\"+\",\"3\",\"*\"] → 9 (((2 + 1) * 3))")
        void testLeetCodeExample1() {
            assertEquals(9, instance.evalRPN(new String[] { "2", "1", "+", "3", "*" }));
        }

        @Test
        @DisplayName("LeetCode Example 2: [\"4\",\"13\",\"5\",\"/\",\"+\"] → 6 ((4 + (13 / 5)))")
        void testLeetCodeExample2() {
            assertEquals(6, instance.evalRPN(new String[] { "4", "13", "5", "/", "+" }));
        }

        @Test
        @DisplayName("LeetCode Example 3: [\"10\",\"6\",\"9\",\"3\",\"+\",\"-11\",\"*\",\"/\",\"*\",\"17\",\"+\",\"5\",\"+\"] → 22 (division truncates to 0 mid-expression)")
        void testLeetCodeExample3() {
            assertEquals(22, instance.evalRPN(new String[] { "10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+" }));
        }

        @Test
        @DisplayName("Scenario: [\"5\"] → 5 (single token)")
        void testSingleOperand() {
            assertEquals(5, instance.evalRPN(new String[] { "5" }));
        }

        @Test
        @DisplayName("Scenario: [\"-7\"] → -7 (single negative token)")
        void testSingleNegativeOperand() {
            assertEquals(-7, instance.evalRPN(new String[] { "-7" }));
        }

        @Test
        @DisplayName("Scenario: [\"0\"] → 0 (single zero)")
        void testSingleZero() {
            assertEquals(0, instance.evalRPN(new String[] { "0" }));
        }

        @Test
        @DisplayName("Scenario: [\"3\",\"4\",\"+\"] → 7 (addition)")
        void testAddition() {
            assertEquals(7, instance.evalRPN(new String[] { "3", "4", "+" }));
        }

        @Test
        @DisplayName("Scenario: [\"3\",\"4\",\"-\"] → -1 (left operand minus right operand)")
        void testSubtractionOrder() {
            assertEquals(-1, instance.evalRPN(new String[] { "3", "4", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"3\",\"4\",\"*\"] → 12 (multiplication)")
        void testMultiplication() {
            assertEquals(12, instance.evalRPN(new String[] { "3", "4", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"8\",\"2\",\"/\"] → 4 (exact division)")
        void testDivisionExact() {
            assertEquals(4, instance.evalRPN(new String[] { "8", "2", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"2\",\"8\",\"/\"] → 0 (left divided by right, truncated to 0)")
        void testDivisionOrder() {
            assertEquals(0, instance.evalRPN(new String[] { "2", "8", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"13\",\"5\",\"/\"] → 2 (positive quotient truncates down)")
        void testDivisionTruncatesPositive() {
            assertEquals(2, instance.evalRPN(new String[] { "13", "5", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"7\",\"-2\",\"/\"] → -3 (truncates toward zero, not floor)")
        void testDivisionTruncatesNegativeDivisor() {
            assertEquals(-3, instance.evalRPN(new String[] { "7", "-2", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"-7\",\"2\",\"/\"] → -3 (truncates toward zero, not floor)")
        void testDivisionTruncatesNegativeDividend() {
            assertEquals(-3, instance.evalRPN(new String[] { "-7", "2", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"-7\",\"-2\",\"/\"] → 3 (both negative)")
        void testDivisionBothNegative() {
            assertEquals(3, instance.evalRPN(new String[] { "-7", "-2", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"-1\",\"3\",\"/\"] → 0 (-1 / 3 truncates to 0)")
        void testDivisionSmallNegativeToZero() {
            assertEquals(0, instance.evalRPN(new String[] { "-1", "3", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"-10\",\"3\",\"/\"] → -3 (-10 / 3 truncates to -3)")
        void testDivisionNegativeTruncation() {
            assertEquals(-3, instance.evalRPN(new String[] { "-10", "3", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"0\",\"-3\",\"/\"] → 0 (zero divided by negative)")
        void testDivisionZeroNumerator() {
            assertEquals(0, instance.evalRPN(new String[] { "0", "-3", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"0\",\"5\",\"-\"] → -5 (0 - 5)")
        void testSubtractFromZero() {
            assertEquals(-5, instance.evalRPN(new String[] { "0", "5", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"-3\",\"4\",\"*\"] → -12 (negative times positive)")
        void testNegativeTimesPositive() {
            assertEquals(-12, instance.evalRPN(new String[] { "-3", "4", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"-3\",\"-4\",\"*\"] → 12 (negative times negative)")
        void testNegativeTimesNegative() {
            assertEquals(12, instance.evalRPN(new String[] { "-3", "-4", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"-3\",\"-4\",\"+\"] → -7 (adding negatives)")
        void testAddNegatives() {
            assertEquals(-7, instance.evalRPN(new String[] { "-3", "-4", "+" }));
        }

        @Test
        @DisplayName("Scenario: [\"2\",\"2\",\"2\",\"2\",\"+\",\"+\",\"+\"] → 8 (all operands first, then operators)")
        void testUniformAdditionRightHeavy() {
            assertEquals(8, instance.evalRPN(new String[] { "2", "2", "2", "2", "+", "+", "+" }));
        }

        @Test
        @DisplayName("Scenario: [\"2\",\"2\",\"*\",\"2\",\"*\",\"2\",\"*\"] → 16 (operator after every operand)")
        void testUniformMultiplicationLeftHeavy() {
            assertEquals(16, instance.evalRPN(new String[] { "2", "2", "*", "2", "*", "2", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"1\",\"2\",\"+\",\"3\",\"+\",\"4\",\"+\"] → 10 (left-leaning chain)")
        void testIncreasingAdditionChain() {
            assertEquals(10, instance.evalRPN(new String[] { "1", "2", "+", "3", "+", "4", "+" }));
        }

        @Test
        @DisplayName("Scenario: [\"10\",\"2\",\"3\",\"-\",\"-\"] → 11 (10 - (2 - 3))")
        void testRightNestedSubtraction() {
            assertEquals(11, instance.evalRPN(new String[] { "10", "2", "3", "-", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"10\",\"3\",\"-\",\"2\",\"-\"] → 5 ((10 - 3) - 2)")
        void testSubtractionChain() {
            assertEquals(5, instance.evalRPN(new String[] { "10", "3", "-", "2", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"10\",\"2\",\"3\",\"*\",\"-\"] → 4 (10 - (2 * 3))")
        void testMultiplyBeforeSubtract() {
            assertEquals(4, instance.evalRPN(new String[] { "10", "2", "3", "*", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"10\",\"2\",\"-\",\"3\",\"*\"] → 24 ((10 - 2) * 3)")
        void testSubtractBeforeMultiply() {
            assertEquals(24, instance.evalRPN(new String[] { "10", "2", "-", "3", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"100\",\"5\",\"/\",\"2\",\"/\"] → 10 ((100 / 5) / 2)")
        void testDivisionChainLeft() {
            assertEquals(10, instance.evalRPN(new String[] { "100", "5", "/", "2", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"100\",\"5\",\"2\",\"/\",\"/\"] → 50 (100 / (5 / 2) = 100 / 2)")
        void testDivisionChainRight() {
            assertEquals(50, instance.evalRPN(new String[] { "100", "5", "2", "/", "/" }));
        }

        @Test
        @DisplayName("Scenario: [\"2\",\"3\",\"1\",\"*\",\"+\",\"9\",\"-\"] → -4 ((2 + (3 * 1)) - 9)")
        void testMixedAllOperators() {
            assertEquals(-4, instance.evalRPN(new String[] { "2", "3", "1", "*", "+", "9", "-" }));
        }

        @Test
        @DisplayName("Scenario: [\"13\",\"5\",\"/\",\"5\",\"*\"] → 10 ((13 / 5) * 5, not 13)")
        void testTruncatedIntermediateThenMultiply() {
            assertEquals(10, instance.evalRPN(new String[] { "13", "5", "/", "5", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"-200\",\"-200\",\"*\"] → 40_000 (operands at -200)")
        void testOperandBounds() {
            assertEquals(40_000, instance.evalRPN(new String[] { "-200", "-200", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"200\",\"200\",\"*\",\"200\",\"*\",\"200\",\"*\"] → 1_600_000_000 (200^4, near 32-bit limit)")
        void testLargeProductWithinInt() {
            assertEquals(1_600_000_000, instance.evalRPN(new String[] { "200", "200", "*", "200", "*", "200", "*" }));
        }

        @Test
        @DisplayName("Scenario: [\"-200\",\"200\",\"*\",\"200\",\"*\",\"200\",\"*\"] → -1_600_000_000 (-200 * 200^3, near 32-bit limit)")
        void testLargeNegativeProductWithinInt() {
            assertEquals(-1_600_000_000, instance.evalRPN(new String[] { "-200", "200", "*", "200", "*", "200", "*" }));
        }

        @Test
        @DisplayName("Scenario: 9,999 tokens: 1 + 1 + ... + 1 (5,000 ones, left-leaning) → 5,000 (max length)")
        void testMaxLengthLeftLeaning() {
            assertEquals(5_000, instance.evalRPN(leftLeaning(4_999, "+")));
        }

        @Test
        @DisplayName("Scenario: 9,999 tokens: 5,000 ones then 4,999 \"+\" → 5,000 (max length, deepest stack)")
        void testMaxLengthRightLeaning() {
            assertEquals(5_000, instance.evalRPN(rightLeaning(4_999, "+")));
        }

        @Test
        @DisplayName("Scenario: 9,999 tokens: 1 * 1 * ... * 1 (left-leaning) → 1 (max length)")
        void testMaxLengthMultiplyByOne() {
            assertEquals(1, instance.evalRPN(leftLeaning(4_999, "*")));
        }

        @Test
        @DisplayName("Scenario: 9,999 tokens: 1 - 1 - ... - 1 (left-leaning) → -4,998 (max length)")
        void testMaxLengthSubtract() {
            assertEquals(-4_998, instance.evalRPN(leftLeaning(4_999, "-")));
        }

        @Test
        @DisplayName("Scenario: 5,000 ones then 4,999 \"-\" → 0 (max length, 1 - (1 - (1 - ...)))")
        void testMaxLengthRightLeaningAlternatingSubtract() {
            assertEquals(0, instance.evalRPN(rightLeaning(4_999, "-")));
        }
    }
}
