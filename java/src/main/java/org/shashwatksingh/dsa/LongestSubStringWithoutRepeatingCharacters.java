package org.shashwatksingh.dsa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
3. Longest Substring Without Repeating Characters

Given a string s, find the length of the longest substring without duplicate characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 

Constraints:
0 <= s.length <= 105
s consists of English letters, digits, symbols and spaces.
*/

public class LongestSubStringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        LongestSubStringWithoutRepeatingCharacters lrcr = new LongestSubStringWithoutRepeatingCharacters();
        System.out.println(lrcr.lengthOfLongestSubstringOptimised("abca2bcbb"));
        // System.out.println(lrcr.lengthOfLongestSubstring("pwwkew"));
        // System.out.println(lrcr.lengthOfLongestSubstring("bbbbb"));
    }

    // Approach 1
    public int lengthOfLongestSubstringBruteForce(String s) {
        int n = s.length(), max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                String sub = s.substring(i, j);
                boolean isNonRepeating = true;
                Map<Character, Integer> freqMap = new HashMap<>();
                for (char ch : sub.toCharArray()) {
                    if (freqMap.containsKey(ch))
                        isNonRepeating = false;
                    else
                        freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
                }
                if (isNonRepeating)
                    max = Math.max(max, sub.length());
            }
        }
        return max;
    }

    public int lengthOfLongestSubstringBruteForceSet(String s) {
        int n = s.length(), max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                String sub = s.substring(i, j);
                boolean isNonRepeating = true;
                Set<Character> set = new HashSet<>();
                for (char ch : sub.toCharArray()) {
                    if (set.contains(ch))
                        isNonRepeating = false;
                    else
                        set.add(ch);
                }
                if (isNonRepeating)
                    max = Math.max(max, sub.length());
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), left = 0, res = 0;
        Map<Character, Integer> freqMap = new HashMap<>();

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
            while (freqMap.get(ch) > 1) {
                char leftChar = s.charAt(left++);
                freqMap.put(leftChar, freqMap.get(leftChar) - 1);
            }
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    // Map restricted to 128 ASCII characters
    public int lengthOfLongestSubstringOptimised(String s) {
        int n = s.length(), left = 0, res = 0;
        Integer[] chars = new Integer[128];

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            Integer index = chars[ch];

            if (index != null && index >= left && index < right) {
                left = index + 1;
            }
            
            chars[ch] = right;
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

}
