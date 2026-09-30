package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/*
844. Backspace String Compare

Given two strings s and t, return true if they are equal when both are typed into empty text editors. 
'#' means a backspace character.
Note that after backspacing an empty text, the text will continue empty.
 

Example 1:
Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".

Example 3:
Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(n) time and O(1) space?

*/

public class BackSpaceStringCompare {

    public static void main(String[] args) {
        BackSpaceStringCompare bssc = new BackSpaceStringCompare();
        System.out.println(bssc.backspaceCompareBruteSolution("a#b#c#", "#"));
        System.out.println(bssc.backspaceCompareBruteSolution("ab##", "c#d#"));
        System.out.println(bssc.backspaceCompareBruteSolution("a#c", "b"));
        System.out.println(bssc.backspaceCompare("ab#c", "ad#c"));
        System.out.println(bssc.backspaceCompare("ab##", "c#d#"));
        System.out.println(bssc.backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompareBruteSolution(String s, String t) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();

        for (char ch: s.toCharArray()) {
            if(sb.length()>0 && ch=='#') sb.deleteCharAt(sb.length()-1);
            else if(ch!='#') sb.append(ch);
        }

        for (char ch: t.toCharArray()) {
            if(sb1.length()>0 && ch=='#') sb1.deleteCharAt(sb1.length()-1);
            else if(ch!='#') sb1.append(ch);
        }

        return sb.toString().equals(sb1.toString());
    }

    public boolean backSpaceStack(String s, String t) {
        return build(s).equals(build(t));
    }

    public String build(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for(char ch: s.toCharArray()) {
            if(ch=='#' && stack.size()>0) stack.pop();
            else stack.push(ch);
        }

        return String.valueOf(stack);
    }

    public boolean backspaceCompare(String s, String t) {
        int i = s.length()-1, j = t.length()-1;
        int skipS = 0, skipT = 0;

        while(i>=0 || j>=0) {
            while (i>=0) {
                if(s.charAt(i)=='#') {skipS++; i--;}
                else if(skipS>0) {skipS--; i--;}
                else break;
            }

            while (j>=0) {
                if(t.charAt(j)=='#') {skipT++; j--;}
                else if(skipT>0) {skipT--; j--;}
                else break;
            }

            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) return false;
            if ((i >= 0) != (j >= 0))return false;
            i--;
            j--;
        }

        return true;
    }
}
