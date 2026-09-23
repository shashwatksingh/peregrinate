package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
49. Group Anagrams
Given an array of strings strs, group the anagrams together. 
You can return the answer in any order.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
Explanation:
There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
*/

public class GroupAnagrams {

    public static void main(String[] args) {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        System.out.println(groupAnagrams.bruteForce(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
        System.out.println(groupAnagrams.bruteForceOptimized(new String[] { "eat", "tea", "tan", "ate", "nat", "bat" }));
    }

    // O(n^2) solution if is take all array and comparing.
    // Maintain a boolean seen array to check if the element has been added or not.

    // Maintain another sorted array of values. all string with same value collected
    // in same list.

    // Maintain a hashmap of sorted values and add all string that anagram of the
    // sorted string. O(n)

    public List<List<String>> bruteForce(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0)
            return res;
        if (strs.length == 1) {
            res.add(List.of(strs[0]));
            return res;
        }

        boolean[] seen = new boolean[strs.length];

        for (int j = 0; j < strs.length; j++) {
            if (seen[j])
                continue;
            char[] strCharArr = strs[j].toCharArray();
            Arrays.sort(strCharArr);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < strs.length; i++) {
                char[] currStrCharArr = strs[i].toCharArray();
                Arrays.sort(currStrCharArr);
                if (!seen[i] && Arrays.equals(strCharArr, currStrCharArr)) {
                    list.add(strs[i]);
                    seen[i] = true;
                }
            }
            res.add(list);
        }

        return res;
    }

    public List<List<String>> bruteForceOptimized(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;
        if (strs.length == 1) {
            res.add(List.of(strs[0]));
            return res;
        }

        boolean[] seen = new boolean[strs.length];
        String[] sortedStrings = new String[strs.length];

        for (int i = 0; i < sortedStrings.length; i++) {
            char[] arr = strs[i].toCharArray();
            Arrays.sort(arr);
            sortedStrings[i] = String.valueOf(arr);
        }

        for (int i = 0; i < strs.length; i++) {
            if (seen[i]) continue;
            List<String> list = new ArrayList<>();

            for (int j = 0; j < sortedStrings.length; j++) {
                if(sortedStrings[i].equals(sortedStrings[j])){
                    list.add(strs[i]);
                    seen[j] = true;
                }
            }

            res.add(list);
        }
        return res;
    }

    public List<List<String>> anagramsUsingHashMap(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        if(strs.length == 0) return new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chArr = strs[i].toCharArray();
            Arrays.sort(chArr);
            String sortedString = String.valueOf(chArr);
            if(map.containsKey(sortedString)) {
                map.get(sortedString).add(strs[i]);
            } else {
                map.computeIfAbsent(sortedString, k -> new ArrayList<>()).add(strs[i]);
            }
        }
        return new ArrayList<>(map.values());
    }
}
