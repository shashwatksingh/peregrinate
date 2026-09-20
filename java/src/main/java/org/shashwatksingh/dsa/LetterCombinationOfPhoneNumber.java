package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.List;

/*
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]


Example 2:
Input: digits = "2"
Output: ["a","b","c"]
 
Constraints:
1 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].

*/

public class LetterCombinationOfPhoneNumber {
    public List<String> solution(String digits) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        backtrack(res, sb, map, digits, 0);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder sb, String[] map, String digits, int start) {
        if(sb.length() == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String possibleLetters = map[digits.charAt(start) - '0'];

        for (char ch: possibleLetters.toCharArray()) {
            sb.append(ch);
            backtrack(res, sb, map, digits, start+1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
