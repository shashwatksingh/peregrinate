package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
213. House Robber II

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.

Example 2:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 3:
Input: nums = [1,2,3]
Output: 3
 

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 1000
*/

public class HouseRobber2 {

    public static void main(String[] args) {
        HouseRobber2 hr2 = new HouseRobber2();
        // Expected: 3, 3, 3
        System.out.println(hr2.bruteForce(new int[] {2,3,2}));
        System.out.println(hr2.topDownWithMemoization(new int[] {2,3,2}));
        System.out.println(hr2.bottomUpSolution(new int[] {2,3,2}));

        // Expected: 4, 4, 4
        System.out.println(hr2.bruteForce(new int[] {1,2,3,1}));
        System.out.println(hr2.topDownWithMemoization(new int[] {1,2,3,1}));
        System.out.println(hr2.bottomUpSolution(new int[] {1,2,3,1}));

        // Expected: 3, 3, 3
        System.out.println(hr2.bruteForce(new int[] {1,2,3}));
        System.out.println(hr2.topDownWithMemoization(new int[] {1,2,3}));
        System.out.println(hr2.bottomUpSolution(new int[] {1,2,3}));

        // Expected: 5 (single element)
        System.out.println(hr2.bruteForce(new int[] {5}));
        System.out.println(hr2.topDownWithMemoization(new int[] {5}));
        System.out.println(hr2.bottomUpSolution(new int[] {5}));
        
    }

    public int bruteForce(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        //[0, n-2] — exclude last house, so first and last are never both robbed
        //[1, n-1] — exclude first house
        return Math.max(dfs(nums, 0, n - 1), dfs(nums, 1, n));
    }

    private int dfs(int[] nums, int i, int end) {
        if (i >= end) return 0;
        return Math.max(dfs(nums, i + 1, end), dfs(nums, i + 2, end) + nums[i]);
    }

    private int topDownWithMemoization(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        // Use separate memo arrays for each sub-problem range so they don't pollute each other's cached values
        int[] memo1 = new int[n];
        int[] memo2 = new int[n];
        Arrays.fill(memo1, -1);
        Arrays.fill(memo2, -1);
        return Math.max(dfsMemo(nums, memo1, 0, n - 1), dfsMemo(nums, memo2, 1, n));
    }

    private int dfsMemo(int[] nums, int[] memo, int i, int end) {
        if (i >= end) return 0;
        if (memo[i] != -1) return memo[i];
        memo[i] = Math.max(dfsMemo(nums, memo, i + 1, end), dfsMemo(nums, memo, i + 2, end) + nums[i]);
        return memo[i];
    }

    public int bottomUpSolution(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(
            bottomUpCalculation(nums, 0, n - 1),
            bottomUpCalculation(nums, 1, n)
        );
    }

    private int bottomUpCalculation(int[] nums, int start, int end) {
        int size = end - start;
        int[] dp = new int[size + 1];
        dp[0] = 0;
        dp[1] = nums[start];

        for (int i = 2; i <= size; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[start + i - 1]);
        }

        return dp[size];
    }

    public int bottomUpSolutionSpaceOptimization(int[] nums) {
        int n = nums.length;
        if(n==1) return nums[0];
        return Math.max(
            bottomUpSpaceOptimizedCalculation(nums, 0, n-1), 
            bottomUpSpaceOptimizedCalculation(nums, 1, n));
    }

    private int bottomUpSpaceOptimizedCalculation(int[] nums, int start, int end) {
        int size = end - start;
        int prev2 = 0, prev1 = nums[start];

        for (int i = 2; i <= size; i++) {
            int third = Math.max(prev1, prev2 + nums[start + i - 1]);
            prev2 = prev1;
            prev1 = third;
        }

        return prev1;
    }
}
