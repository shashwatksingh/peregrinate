package org.shashwatksingh.dsa;

/*
125. Valid Palindrome

A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. 
Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

 

Example 1:
Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.

Example 2:
Input: s = "race a car"
Output: false
Explanation: "raceacar" is not a palindrome.

Example 3:
Input: s = " "
Output: true
Explanation: s is an empty string "" after removing non-alphanumeric characters.
Since an empty string reads the same forward and backward, it is a palindrome.
 

Constraints:
1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.

*/

public class ValidPalindrome {

    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        System.out.println(vp.bruteForce("A man, a plan, a canal: Panama"));
        System.out.println(vp.bruteForce("race a car"));
        System.out.println(vp.twoPointerSolution("A man, a plan, a canal: Panama"));
        System.out.println(vp.twoPointerSolution("race a car"));
    }

    public boolean bruteForce(String s) {
        s = s.trim();
        if(s.length() == 0) return true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isLetter(ch) || Character.isDigit(ch)) sb.append(Character.toLowerCase(ch));
        }
        String orig = sb.toString();
        String reverseString = sb.reverse().toString();
        return orig.equals(reverseString);
    }

    public boolean twoPointerSolution(String s) {
        s = s.trim();
        int left = 0, right = s.length()-1;

        while(left<right) {
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))) left++;
            while(left<right && !Character.isLetterOrDigit(s.charAt(right))) right--;
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            left++;
            right--;
        }

        return true;
    }
    
}
