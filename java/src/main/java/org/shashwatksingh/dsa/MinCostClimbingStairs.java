package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
746. Min Cost Climbing Stairs
You are given an integer array cost where cost[i] is the cost of ith step on a staircase.

Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the staircase, which is the position just past the last step (index cost.length).

 

Example 1:
Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:
Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.
 

Constraints:
2 <= cost.length <= 1000
0 <= cost[i] <= 999
 

*/

public class MinCostClimbingStairs {

    public static void main(String[] args) {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        System.out.println(minCostClimbingStairs.bottomUpWithSpaceOptimisation(new int[] {10,15,20}));
    }

    public int bruteForce(int[] cost) {
        return Math.min(minCostBruteForce(cost, 0, cost.length), minCostBruteForce(cost, 1, cost.length));
    }

    private int minCostBruteForce(int[] cost, int i, int n) {
       if(i>=n) return 0;
       return cost[i] + Math.min(minCostBruteForce(cost, i+1, n), minCostBruteForce(cost, i+2, n));
    }

    public int recursionWithMemoization(int[] cost) {
        int n = cost.length;
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        int res = Math.min(minCostWithMemoization(cost, memo, 0, n), minCostWithMemoization(cost, memo, 1, n));
        Arrays.stream(memo).forEach(System.out::println);
        return res;
    }

    private int minCostWithMemoization(int[] cost, int[] memo, int i, int n) {
        if(i>=n) return 0;
        if(memo[i]!=-1) return memo[i];
        memo[i] = cost[i] + Math.min(minCostWithMemoization(cost, memo, i+1, n), minCostWithMemoization(cost, memo, i+2, n));
        return memo[i];
    }

    public int bottomUpWithTabulation(int[] cost) {
        int n = cost.length;
        if(n<=1) return 0;
        int[] dp = new int[n+1];
        dp[0] = 0; 
        dp[1] = 0;

        for(int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        return dp[n];
    }

    public int bottomUpWithSpaceOptimisation(int[] cost) {
        int n = cost.length;
        int first = 0, second = 0;
        if(n<=1) return 0;

        for(int i = 2; i <= n; i++) {
            int third = Math.min(second + cost[i-1], first + cost[i-2]);
            first = second;
            second = third;
        }

        return second;
    }
}
