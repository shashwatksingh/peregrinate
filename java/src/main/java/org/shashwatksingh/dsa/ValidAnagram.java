package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
242. Valid Anagram
Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 

Example 1:
Input: s = "anagram", t = "nagaram"
Output: true

Example 2:
Input: s = "rat", t = "car"
Output: false

 

Constraints:
1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.
 

Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
*/

public class ValidAnagram {

    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        System.out.println(validAnagram.sortAndCompare("anagram", "nagaram"));
    }

    public boolean sortAndCompare(String s, String t) {
        char[] sch = s.toCharArray();
        Arrays.sort(sch);
        char[] tch = t.toCharArray();
        Arrays.sort(tch);
        return Arrays.equals(sch, tch);
    }

    public boolean characterMap(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] sChar = new int[26];
        int[] tChar = new int[26];

        for (Character ch : s.toCharArray()) {
            sChar[ch-'a']++;
        }

        for (Character ch : t.toCharArray()) {
            tChar[ch-'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if(sChar[i] != tChar[i]) return false;
        }
        return true;
    }

    public boolean hashmapSolution(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (Character ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        for (Character ch : t.toCharArray()) {
            int freq = map.get(ch);
            if(freq<=0) return false;
            map.put(ch, map.getOrDefault(ch, 0)-1);
        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            if(entry.getValue()!=0) return false;
        }
        return true;
    }
    
    public boolean characterMapOptimised(String s, String t) {
        if(s.length() != t.length()) return false;
        int[] table = new int[26];

        for (Character ch : s.toCharArray()) {
            table[ch-'a']++;
        }

        for (Character ch : t.toCharArray()) {
            table[ch-'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if(table[i]!=0) return false;
        }
        return true;
    }
}
