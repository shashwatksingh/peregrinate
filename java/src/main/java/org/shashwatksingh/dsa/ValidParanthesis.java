package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/*
20. Valid Parentheses

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.
 

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Example 5:
Input: s = "([)]"
Output: false

 

Constraints:
1 <= s.length <= 104
s consists of parentheses only '()[]{}'.

*/

public class ValidParanthesis {


    public static void main(String[] args) {
        ValidParanthesis validParanthesis = new ValidParanthesis();
        System.out.println(validParanthesis.isValid("()[]{}"));
        System.out.println(validParanthesis.isValid("]"));
        
    }

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        for (Character ch : s.toCharArray()) {
            if(ch == '(' || ch == '{' || ch == '[') stack.push(ch);
            else if(!stack.isEmpty() && ((ch == ')' && stack.peek() =='(') || (ch == '}' && stack.peek() =='{') || (ch == ']' && stack.peek() =='['))) stack.pop();
            else return false;
        }

        return stack.isEmpty();
    }

}
