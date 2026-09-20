package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
78. Subsets
Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.


Example 1:
Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

Example 2:
Input: nums = [0]
Output: [[],[0]]
 

Constraints:
1 <= nums.length <= 10
-10 <= nums[i] <= 10
All the numbers of nums are unique.

*/

public class Subsets {
    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        subsets.solution(new int[]{1,2,3}).stream().forEach(System.out::println);
    }

    public List<List<Integer>> solution(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new LinkedList<>(), nums, 0);
        return res;

    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> comb, int[] nums, int start) {
        res.add(new ArrayList<>(comb));
        for (int i = start; i < nums.length; i++) {
            comb.add(nums[i]);
            backtrack(res, comb, nums, i+1);
            comb.removeLast();
        }
    }
}
