package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
198. House Robber
You are a professional robber planning to rob houses along a street. 
Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.
 

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/

public class HouseRobber {

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        System.out.println(hr.bottomUpWithTabulation(new int[] {1,2,3,1}));
        System.out.println(hr.bottomUpWithTabulation(new int[] {2,7,9,3,1}));
        System.out.println(hr.bottomUpWithSpaceOptimisation(new int[] {1,2,3,1}));
        System.out.println(hr.bottomUpWithSpaceOptimisation(new int[] {2,7,9,3,1}));
    }

    public int bruteForce(int[] nums) {
        return recursive(0, nums.length, nums);
    }

    private int recursive(int i, int n, int[] nums) {
        if(i>=n) return 0;
        return Math.max(recursive(i+1, n, nums), recursive(i+2, n, nums)+nums[i]);
    }

    public int topDownWithTabulation(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfsWithMemo(0,n, nums, memo);
    }

    private int dfsWithMemo(int i, int n, int[] nums, int[] memo) {
        if(i>=n) return 0;
        if(memo[i]!=-1) return memo[i];
        memo[i] = Math.max(dfsWithMemo(i+1, n, nums, memo), dfsWithMemo(i+2, n, nums, memo)+nums[i]);
        return memo[i];
    }

    public int bottomUpWithTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n ; i++) {
            dp[i] = Math.max(dp[i-1], nums[i-1]+dp[i-2]);
        }
        return dp[n];
    }

    public int bottomUpWithSpaceOptimisation(int[] nums) {
        int n = nums.length;
        int prev2 = 0;
        int prev1 = nums[0];
        for (int i = 2; i <= n ; i++) {
            int next = Math.max(prev1, nums[i-1]+prev2);
            prev2 = prev1;
            prev1 = next;
        }
        return prev1;
    }
}