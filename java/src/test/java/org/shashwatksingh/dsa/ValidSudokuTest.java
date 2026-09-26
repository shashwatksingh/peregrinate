package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@DisplayName("ValidSudoku Tests")
class ValidSudokuTest {

    private ValidSudoku instance;

    @BeforeEach
    void setUp() {
        instance = new ValidSudoku();
    }

    private static char[][] emptyBoard() {
        char[][] board = new char[9][9];
        for (char[] row : board) Arrays.fill(row, '.');
        return board;
    }

    // LeetCode Example 1 — a genuinely valid, partially-filled board.
    private static char[][] validExampleBoard() {
        return new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
    }

    // LeetCode Example 2 — same as above but with a duplicate '8' in the top-left sub-box.
    private static char[][] invalidExampleBoard() {
        char[][] board = validExampleBoard();
        board[0][0] = '8';
        return board;
    }

    // Every row is individually valid, and the top-left 3x3 sub-square is fully
    // filled with the distinct digits 1-9 (no '.' cells), so it is both dot-free
    // (avoids the isSubSqquareValid crash) and internally valid (never returns
    // false) — the exact combination that makes the i/=3 / j/=3 loop spin forever.
    private static char[][] fullyFilledValidTopLeftSquareBoard() {
        char[][] board = emptyBoard();
        board[0][0] = '1'; board[0][1] = '2'; board[0][2] = '3';
        board[1][0] = '4'; board[1][1] = '5'; board[1][2] = '6';
        board[2][0] = '7'; board[2][1] = '8'; board[2][2] = '9';
        return board;
    }

    // ═══════════════════════════════════════════════════════════
    //  isValidSudokuThreePass()  — separate row/column/sub-square passes
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isValidSudokuThreePass()")
    class IsValidSudokuThreePassTests {

        @Test
        @DisplayName("Row duplicate is caught before the crash/hang-prone code is ever reached: two 5's in row 0 → false")
        void testRowDuplicateCaughtEarly() {
            char[][] board = emptyBoard();
            board[0][0] = '5';
            board[0][1] = '5';
            assertEquals(false, instance.isValidSudokuThreePass(board));
        }

        @Test
        @DisplayName("LeetCode Example 1 (valid board) → true")
        void testValidBoardCrashesInsteadOfReturningTrue() {
            char[][] board = validExampleBoard();
            boolean result = assertTimeoutPreemptively(Duration.ofSeconds(1),
                    () -> instance.isValidSudokuThreePass(board));
            assertEquals(true, result);
        }

        @Test
        @DisplayName("LeetCode Example 2 (invalid board) → false")
        void testInvalidBoardCrashesInsteadOfReturningFalse() {
            char[][] board = invalidExampleBoard();
            boolean result = assertTimeoutPreemptively(Duration.ofSeconds(1),
                    () -> instance.isValidSudokuThreePass(board));
            assertEquals(false, result);
        }

        @Test
        @DisplayName("fully-filled valid top-left sub-square → true ")
        void testFullyFilledValidTopLeftSquareHangsForever() {
            char[][] board = fullyFilledValidTopLeftSquareBoard();
            boolean result = assertTimeoutPreemptively(Duration.ofSeconds(1),
                    () -> instance.isValidSudokuThreePass(board));
            assertEquals(true, result);
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  isValidSudokuHashSet()  — HashSet per row/column/sub-square
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isValidSudokuHashSet()")
    class IsValidSudokuHashSetTests {

        @Test
        @DisplayName("LeetCode Example 1 (valid board) → true")
        void testValidBoard() {
            assertEquals(true, instance.isValidSudokuHashSet(validExampleBoard()));
        }

        @Test
        @DisplayName("LeetCode Example 2 (duplicate '8' in sub-box) → false")
        void testInvalidBoardDueToSubSquare() {
            assertEquals(false, instance.isValidSudokuHashSet(invalidExampleBoard()));
        }

        @Test
        @DisplayName("duplicate digit in the same column, different rows/sub-squares → false")
        void testColumnDuplicateIsNeverDetected() {
            char[][] board = emptyBoard();
            board[0][0] = '1';
            board[3][0] = '1';
            assertEquals(false, instance.isValidSudokuHashSet(board));
        }

        @Test
        @DisplayName("Empty board (all dots) → true")
        void testEmptyBoard() {
            assertEquals(true, instance.isValidSudokuHashSet(emptyBoard()));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  isValidSudokuArrayMap()  — 2D count arrays per row/column/sub-square
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("isValidSudokuArrayMap()")
    class IsValidSudokuArrayMapTests {

        @Test
        @DisplayName("Empty board (all dots) → true (the one case this method gets right)")
        void testEmptyBoard() {
            assertEquals(true, instance.isValidSudokuArrayMap(emptyBoard()));
        }

        @Test
        @DisplayName("a single filled cell, trivially valid → true")
        void testSingleFilledCellIsTriviallyValid() {
            char[][] board = emptyBoard();
            board[4][4] = '9';
            assertEquals(true, instance.isValidSudokuArrayMap(board));
        }

        @Test
        @DisplayName("LeetCode Example 1 (valid board) → true")
        void testValidBoardRejectedImmediately() {
            assertEquals(true, instance.isValidSudokuArrayMap(validExampleBoard()));
        }

        @Test
        @DisplayName("LeetCode Example 2 (invalid board) → false)")
        void testInvalidBoardHappensToMatch() {
            assertEquals(false, instance.isValidSudokuArrayMap(invalidExampleBoard()));
        }
    }
}
