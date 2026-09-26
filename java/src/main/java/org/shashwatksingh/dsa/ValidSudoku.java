package org.shashwatksingh.dsa;

import java.util.HashSet;

/*
36. Valid Sudoku
Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.

Note:
A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
 

Example 1:
Input: board = 
[["5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: true

Example 2:
Input: board = 
[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
 

Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.
*/

public class ValidSudoku {

    public boolean isValidSudokuThreePass(char[][] board) {
        // check if the row is valid
        // check if the column is valid
        // check if the 3X3 current square is valid
        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            if(!isRowValid(board, i, m, n)) return false;
        }

        for (int i = 0; i < m; i++) {
            if(!isColValid(board, i, m, n));
        }

        for (int i = 0; i < m; i +=3) {
            for (int j = 0; j < n; j+=3) {
                if(!isSubSqquareValid(board, i, j, m, n)) return false;
            }
        }
        return true;
    }

    private boolean isRowValid(char[][] board, int row, int m, int n) {
        int[] freqMap = new int[m];
        for (int i = 0; i < m; i++) {
            if (board[row][i] != '.') {
                freqMap[board[row][i] - '0' - 1]++;
                if (freqMap[board[row][i] - '0' - 1] > 1)
                    return false;
            }
        }
        return true;
    }

    private boolean isColValid(char[][] board, int col, int m, int n) {
        int[] freqMap = new int[n];
        for (int i = 0; i < n; i++) {
            if (board[i][col] != '.') {
                freqMap[board[i][col] - '0'-1]++;
                if (freqMap[board[i][col] - '0'-1] > 1)
                    return false;
            }
        }
        return true;
    }

    private boolean isSubSqquareValid(char[][] board, int startRow, int startCol, int m, int n) {
        int[] freqMap = new int[n];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow+i][startCol+j] == '.') continue;
                int num = board[startRow+i][startCol+j]-'0';
                freqMap[num-1]++;
                if (freqMap[num-1] > 1)
                    return false;
            }
        }
        return true;
    }

    public boolean isValidSudokuHashSet(char[][] board) {
        int N = 9;
        HashSet<Character>[] setRow = new HashSet[N];
        HashSet<Character>[] setCol = new HashSet[N];
        HashSet<Character>[] setSubSquares = new HashSet[N];

        for (int i = 0; i < N; i++) {
            setRow[i] = new HashSet<>();
            setCol[i] = new HashSet<>();
            setSubSquares[i] = new HashSet<>();
        }

        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                char ch = board[i][j];
                if(ch == '.') continue;
                if(!setRow[i].add(ch)) return false;
                if(!setCol[j].add(ch)) return false;
                int idx = (i / 3) * 3 + j / 3;
                if(!setSubSquares[idx].add(ch)) return false;
            }
        }

        return true;
    }

    public boolean isValidSudokuArrayMap(char[][] board) {
        int N = 9;
        int[][] row = new int[N][N];
        int[][] col = new int[N][N];
        int[][] subSquares = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == '.') continue;
                int pos = board[i][j] - '1';
                if(++row[i][pos]>1) return false;
                if(++col[pos][j]>1) return false;
                int idx = (i / 3) * 3 + j / 3;
                if(++subSquares[idx][pos]>1) return false;
            }
        }

        return true;
    }

    public boolean isValidSudokuBitmask(char[][] board) {
        int N = board.length;
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] subSquares = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(board[i][j] == '.') continue;
                int val = board[i][j] - '0';
                int pos = 1 << val-1;

                if((rows[i]&pos)>0) return false;
                rows[i] |= pos;

                if((cols[j]&pos)>0) return false;
                cols[j] |= pos;

                int idx = (i / 3) * 3 + j / 3;
                if((subSquares[idx] & pos) > 0) return false;
                subSquares[idx] |= pos;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidSudoku solution = new ValidSudoku();

        char[][] validBoard = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        char[][] invalidBoard = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        System.out.println("=== Example 1 (expected true) ===");
        System.out.println("ThreePass: " + solution.isValidSudokuThreePass(validBoard));
        System.out.println("HashSet:   " + solution.isValidSudokuHashSet(validBoard));
        System.out.println("ArrayMap:  " + solution.isValidSudokuArrayMap(validBoard));
        System.out.println("Bitmask:   " + solution.isValidSudokuBitmask(validBoard));

        System.out.println("=== Example 2 (expected false) ===");
        System.out.println("ThreePass: " + solution.isValidSudokuThreePass(invalidBoard));
        System.out.println("HashSet:   " + solution.isValidSudokuHashSet(invalidBoard));
        System.out.println("ArrayMap:  " + solution.isValidSudokuArrayMap(invalidBoard));
        System.out.println("Bitmask:   " + solution.isValidSudokuBitmask(invalidBoard));
    }
}
