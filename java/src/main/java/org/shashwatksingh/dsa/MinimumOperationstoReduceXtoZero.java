package org.shashwatksingh.dsa;

/*
1658. Minimum Operations to Reduce X to Zero
You are given an integer array nums and an integer x. 
In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x. 
Note that this modifies the array for future operations.

Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

Example 1:
Input: nums = [1,1,4,2,3], x = 5
Output: 2
Explanation: The optimal solution is to remove the last two elements to reduce x to zero.

Example 2:
Input: nums = [5,6,7,8,9], x = 4
Output: -1

Example 3:
Input: nums = [3,2,20,1,1,3], x = 10
Output: 5
Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
 

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 104
1 <= x <= 109
*/

public class MinimumOperationstoReduceXtoZero {

    public static void main(String[] args) {
        MinimumOperationstoReduceXtoZero morx20 = new MinimumOperationstoReduceXtoZero();
        System.out.println(morx20.minOperationsSolution2(new int[]{1,1,4,2,3}, 5));
        System.out.println(morx20.minOperationsSolution2(new int[]{5,6,7,8,9}, 4));
        System.out.println(morx20.minOperationsSolution2(new int[]{3,2,20,1,1,3}, 10));
        System.out.println(morx20.minOperationsSolution2(new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309}, 134365));
    }

    // direct solution
    public int minOperationsSolution(int[] nums, int x) {
        int n = nums.length;
        int left = 0;
        int min = Integer.MAX_VALUE;

        int current = 0;
        for (int i : nums) {
            current+=i;
        }

        for (int right = 0; right < n; right++) {
            // sum([0,..,left) + (right,...,n-1]) = x
            current -= nums[right];

            // if smaller, move `left` to left
            while (current < x && left <= right) {
                current += nums[left++];
            }

            if(current == x) min = Math.min(min, (n-1-right)+left);
        }

        return min != Integer.MAX_VALUE ? min : -1; 
    }

    //Antithesis solution
    public int minOperationsSolution2(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int i : nums) {
            total+=i;
        }

        int max = -1;
        int current = 0;
        int left = 0;
        int target = total-x;

        for (int right = 0; right < n; right++) {
            current+=nums[right];
            while (left<=right && current>target) {
                current-=nums[left++];
            }
            if(current==target) max = Math.max(max, right-left+1);
        }

        return max != -1? n - max : max; 
    }

    // O(n^2) solution
    public int minOperationsSolution1(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        int[] PS = new int[n+1];
        PS[0] = 0;

        for (int i = 0; i < n; i++) {
            sum+=nums[i];
            PS[i+1] = sum;
        }

        if(sum-x==0) return n;
        
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < n+1; i++) {
            for (int j = i+1; j < PS.length; j++) {
                if((PS[j]-PS[i]) == (sum-x)) maxLength = Math.max(maxLength, j-i);
            }
        }
        return maxLength == Integer.MIN_VALUE ? -1 : n-maxLength;
    }

}
