package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        LongestConsecutiveSequence longestConsecutiveSequence = new LongestConsecutiveSequence();
        // System.out.println(longestConsecutiveSequence.solutionSorting(new int[]{100,4,200,1,3,2}));
        System.out.println(longestConsecutiveSequence.solutionSets(new int[]{0,3,7,2,5,8,4,6,0,1}));
        System.out.println(longestConsecutiveSequence.solutionSets(new int[]{1,0,1,2}));
        System.out.println(longestConsecutiveSequence.solutionSets(new int[]{100,4,200,1,3,2}));
    }

    public int bruteForce(int[] nums) {
        int res = 0;
        // boolean[] seen = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int currStreak = 1;
            int curr = nums[i] + 1;
            while(containsNum(curr, nums)) {
                currStreak++;
                curr++;
            }
            res = Math.max(currStreak, res);
        }
        return res;
    }

    private boolean containsNum(int n, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if(n == arr[i]) return true;
        }
        return false;
    }

    public int solutionSorting(int[] nums) {
        int res = 0, curr = 1;
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i-1]!=nums[i]) {
                if(nums[i-1] + 1 == nums[i]) {
                    curr++;
                } else {
                    res = Math.max(curr, res);
                    curr = 1;
                }
            }
        }

        return Math.max(curr, res);
    }

    public int solutionSets(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (Integer num : nums) {
            set.add(num);
        }

        for(int num: set) {
            int currStreak = 1;
            if(!set.contains(num-1)) {
                while (set.contains(num+1)) {
                    num+=1;
                    currStreak++;
                }
                res = Math.max(currStreak, res);
            }
        }

        return res;
    }
}
