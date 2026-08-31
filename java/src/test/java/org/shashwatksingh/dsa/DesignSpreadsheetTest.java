package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DesignSpreadsheet Tests")
class DesignSpreadsheetTest {

    private DesignSpreadsheet spreadsheet;

    @BeforeEach
    void setUp() {
        spreadsheet = new DesignSpreadsheet(3);
    }

    // ── getValue ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: getValue(\"=5+7\") → 12 (two integer literals)")
    void testGetValueTwoIntegers() {
        assertEquals(12, spreadsheet.getValue("=5+7"));
    }

    @Test
    @DisplayName("getValue with both operands being 0 → 0")
    void testGetValueBothZero() {
        assertEquals(0, spreadsheet.getValue("=0+0"));
    }

    @Test
    @DisplayName("getValue with large integers at constraint boundary → 200000")
    void testGetValueLargeIntegers() {
        assertEquals(200000, spreadsheet.getValue("=100000+100000"));
    }

    // ── setCell + getValue ───────────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: setCell(A1,10) then getValue(\"=A1+6\") → 16")
    void testGetValueWithCellReferenceAndIntegerOperand() {
        spreadsheet.setCell("A1", 10);
        assertEquals(16, spreadsheet.getValue("=A1+6"));
    }

    @Test
    @DisplayName("Example 1: setCell(A1,10) + setCell(B2,15) then getValue(\"=A1+B2\") → 25")
    void testGetValueTwoCells() {
        spreadsheet.setCell("A1", 10);
        spreadsheet.setCell("B2", 15);
        assertEquals(25, spreadsheet.getValue("=A1+B2"));
    }

    @Test
    @DisplayName("setCell overwrites previous value: setCell(A1,10) then setCell(A1,20) → getValue uses 20")
    void testSetCellOverwrite() {
        spreadsheet.setCell("A1", 10);
        spreadsheet.setCell("A1", 20);
        assertEquals(20, spreadsheet.getValue("=A1+0"));
    }

    @Test
    @DisplayName("Cell that was never set defaults to 0")
    void testUnsetCellDefaultsToZero() {
        assertEquals(0, spreadsheet.getValue("=Z3+0"));
    }

    @Test
    @DisplayName("setCell on last column Z works correctly")
    void testSetCellLastColumn() {
        spreadsheet.setCell("Z3", 50);
        assertEquals(50, spreadsheet.getValue("=Z3+0"));
    }

    @Test
    @DisplayName("setCell on last row works correctly")
    void testSetCellLastRow() {
        DesignSpreadsheet big = new DesignSpreadsheet(1000);
        big.setCell("A1000", 99);
        assertEquals(99, big.getValue("=A1000+0"));
    }

    // ── resetCell ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("Example 1: resetCell(A1) after setCell(A1,10) → getValue(\"=A1+B2\") uses 0 for A1")
    void testGetValueAfterResetCellReturnsZeroForResetCell() {
        spreadsheet.setCell("A1", 10);
        spreadsheet.setCell("B2", 15);
        spreadsheet.resetCell("A1");
        assertEquals(15, spreadsheet.getValue("=A1+B2"));
    }

    @Test
    @DisplayName("resetCell on a cell that was never set keeps it at 0")
    void testResetCellNeverSet() {
        spreadsheet.resetCell("C3");
        assertEquals(0, spreadsheet.getValue("=C3+0"));
    }

    @Test
    @DisplayName("resetCell does not affect other cells")
    void testResetCellDoesNotAffectOthers() {
        spreadsheet.setCell("A1", 10);
        spreadsheet.setCell("B2", 15);
        spreadsheet.resetCell("A1");
        assertEquals(15, spreadsheet.getValue("=B2+0"));
    }

    // ── Full example from problem statement ─────────────────────────────────

    @Test
    @DisplayName("Full Example 1 sequence matches expected outputs")
    void testFullExample() {
        DesignSpreadsheet s = new DesignSpreadsheet(3);
        assertEquals(12, s.getValue("=5+7"));
        s.setCell("A1", 10);
        assertEquals(16, s.getValue("=A1+6"));
        s.setCell("B2", 15);
        assertEquals(25, s.getValue("=A1+B2"));
        s.resetCell("A1");
        assertEquals(15, s.getValue("=A1+B2"));
    }

    // ── Edge cases: multi-digit rows in formula ──────────────────────────────

    @Test
    @DisplayName("Multi-digit row (2-digit): =A10+B10 correctly splits on the right '+'")
    void testGetValueWithTwoDigitRowCellReferences() {
        DesignSpreadsheet s = new DesignSpreadsheet(10);
        s.setCell("A10", 30);
        s.setCell("B10", 20);
        // "=A10+B10": indexOf("+") must be 4, not somewhere inside the digits
        assertEquals(50, s.getValue("=A10+B10"));
    }

    @Test
    @DisplayName("Three-digit row LEFT operand: =A100+0 correctly parses A100")
    void testGetValueWithThreeDigitRowCellReferenceAsLeftOperand() {
        DesignSpreadsheet s = new DesignSpreadsheet(1000);
        s.setCell("A100", 77);
        // indexOf("+") = 5 → left = "A100", right = "0"
        assertEquals(77, s.getValue("=A100+0"));
    }

    @Test
    @DisplayName("Three-digit row RIGHT operand: =0+A100 correctly parses right operand A100")
    void testGetValueWithThreeDigitRowCellReferenceAsRightOperand() {
        DesignSpreadsheet s = new DesignSpreadsheet(1000);
        s.setCell("A100", 42);
        // indexOf("+") = 2 → left = "0", right = "A100"
        assertEquals(42, s.getValue("=0+A100"));
    }

    // ── Edge cases: operand order ────────────────────────────────────────────

    @Test
    @DisplayName("Integer LEFT, cell RIGHT: =5+A1 — indexOf must not mistake '5' for a cell ref")
    void testGetValueWithIntegerAsLeftOperandAndCellReferenceAsRightOperand() {
        spreadsheet.setCell("A1", 10);
        assertEquals(15, spreadsheet.getValue("=5+A1"));
    }

    @Test
    @DisplayName("Large integer LEFT, cell RIGHT: =100000+A1 splits at index 7")
    void testGetValueWithLargeIntegerAsLeftOperandAndCellReferenceAsRightOperand() {
        spreadsheet.setCell("A1", 5);
        // "=100000+A1": indexOf("+") = 7 → left = "100000", right = "A1"
        assertEquals(100005, spreadsheet.getValue("=100000+A1"));
    }

    @Test
    @DisplayName("Multi-digit cell LEFT, large integer RIGHT: =A10+100000 splits at the one true '+'")
    void testGetValueWithTwoDigitRowCellReferenceAsLeftOperandAndLargeIntegerAsRightOperand() {
        DesignSpreadsheet s = new DesignSpreadsheet(10);
        s.setCell("A10", 1);
        // "=A10+100000": indexOf("+") = 4 → left = "A10", right = "100000"
        assertEquals(100001, s.getValue("=A10+100000"));
    }

    // ── Edge cases: same cell on both sides ──────────────────────────────────

    @Test
    @DisplayName("Same cell on both sides: =A1+A1 after setCell(A1,5) → 10")
    void testGetValueWithSameCellReferencedInBothOperands() {
        spreadsheet.setCell("A1", 5);
        assertEquals(10, spreadsheet.getValue("=A1+A1"));
    }

    @Test
    @DisplayName("Same cell on both sides after resetCell: =A1+A1 → 0")
    void testGetValueWithSameCellReferencedInBothOperandsAfterReset() {
        spreadsheet.setCell("A1", 5);
        spreadsheet.resetCell("A1");
        assertEquals(0, spreadsheet.getValue("=A1+A1"));
    }

    // ── Edge cases: setCell(0) vs resetCell equivalence ──────────────────────

    @Test
    @DisplayName("setCell(cell, 0) is equivalent to resetCell: cell reads as 0 afterwards")
    void testSetCellToZeroReturnsZeroOnGetValue() {
        spreadsheet.setCell("A1", 100);
        spreadsheet.setCell("A1", 0);
        assertEquals(0, spreadsheet.getValue("=A1+0"));
    }

    @Test
    @DisplayName("resetCell then setCell restores a fresh value correctly (no stale state)")
    void testSetCellAfterResetCellReturnsNewValue() {
        spreadsheet.setCell("B1", 50);
        spreadsheet.resetCell("B1");
        spreadsheet.setCell("B1", 99);
        assertEquals(99, spreadsheet.getValue("=B1+0"));
    }

    // ── Edge cases: column / row boundaries ──────────────────────────────────

    @Test
    @DisplayName("Column Z maps to arr index 25 (upper bound of second dim) without AIOOBE")
    void testSetAndGetValueOnLastColumn() {
        spreadsheet.setCell("Z1", 88);
        // 'Z' - 'A' = 25; arr is [rows+1][26] — index 25 is the last valid slot
        assertEquals(88, spreadsheet.getValue("=Z1+0"));
    }

    @Test
    @DisplayName("Single-row spreadsheet (rows=1): arr is [2][26], row index 1 is the only valid row")
    void testSetAndGetValueOnSingleRowSpreadsheet() {
        DesignSpreadsheet s = new DesignSpreadsheet(1);
        s.setCell("A1", 7);
        assertEquals(7, s.getValue("=A1+0"));
    }

    @Test
    @DisplayName("Max cell value (100000) stored and retrieved correctly")
    void testMaxCellValueOnSingleRowSpreadSheet() {
        spreadsheet.setCell("A1", 100000);
        assertEquals(100000, spreadsheet.getValue("=A1+0"));
    }

    @Test
    @DisplayName("Sum of two max-value cells: 100000+100000 = 200000 (int must not overflow)")
    void testSumOfTwoMaxValues() {
        spreadsheet.setCell("A1", 100000);
        spreadsheet.setCell("B1", 100000);
        assertEquals(200000, spreadsheet.getValue("=A1+B1"));
    }

    // ── Edge cases: both operands are unset cells ────────────────────────────

    @Test
    @DisplayName("Both operands are distinct unset cells: both default to 0, sum is 0")
    void testGetValueReturnZeroWhenBothCellOperandsAreUnset() {
        assertEquals(0, spreadsheet.getValue("=C1+D2"));
    }
}
