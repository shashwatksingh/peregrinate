package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PermutationsOfArray {
    public static void main(String[] args) {
        PermutationsOfArray permutationsOfArray = new PermutationsOfArray();
        permutationsOfArray.permute(new int[]{1, 2, 3}).stream().forEach(System.out::println);
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new LinkedList<Integer>() , nums);
        return res;
    }

    private void backtrack(List<List<Integer>> res, LinkedList<Integer> curr, int[] nums) {
        if(curr.size()==nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!curr.contains(nums[i])) {
                curr.add(nums[i]);
                backtrack(res, curr, nums);
                curr.removeLast();
            }
        }
    }
}
