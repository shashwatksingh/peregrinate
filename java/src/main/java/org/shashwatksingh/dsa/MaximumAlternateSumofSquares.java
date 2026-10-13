package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
3727. Maximum Alternating Sum of Squares

You are given an integer array nums. You may rearrange the elements in any order.

The alternating score of an array arr is defined as:

score = arr[0]2 - arr[1]2 + arr[2]2 - arr[3]2 + ...
Return an integer denoting the maximum possible alternating score of nums after rearranging its elements.

 

Example 1:
Input: nums = [1,2,3]
Output: 12

Explanation:
A possible rearrangement for nums is [2,1,3], which gives the maximum alternating score among all possible rearrangements.
The alternating score is calculated as:
score = 22 - 12 + 32 = 4 - 1 + 9 = 12

Example 2:
Input: nums = [1,-1,2,-2,3,-3]
Output: 16
Explanation:
A possible rearrangement for nums is [-3,-1,-2,1,3,2], which gives the maximum alternating score among all possible rearrangements.
The alternating score is calculated as:
score = (-3)2 - (-1)2 + (-2)2 - (1)2 + (3)2 - (2)2 = 9 - 1 + 4 - 1 + 9 - 4 = 16

Constraints:
1 <= nums.length <= 105
-4 * 104 <= nums[i] <= 4 * 104

*/

public class MaximumAlternateSumofSquares {

    public static void main(String[] args) {
        MaximumAlternateSumofSquares mass = new MaximumAlternateSumofSquares();
        System.out.println(mass.maxAlternatingSum(new int[]{1,2,3}));
        System.out.println(mass.maxAlternatingSum(new int[]{-3,-1,-2,1,3,2}));
    }

    public long maxAlternatingSumStreams(int[] nums) {
        int[] sqNum = Arrays.stream(nums).map(ele -> ele*ele).sorted().toArray();
        long res  = 0l;

        int left = 0, right = nums.length-1;

        while(left<right) {
            res+=sqNum[right--] - sqNum[left++];
        }

        if(left==right) {
            res+=sqNum[left];
        }

        return res;
    }

    public long maxAlternatingSum(int[] nums) {
        long res  = 0l;

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i]*nums[i];
        }

        Arrays.sort(nums);

        int left = 0, right = nums.length-1;

        while(left<right) {
            res+=nums[right--] - nums[left++];
        }

        if(left==right) {
            res+=nums[left];
        }

        return res;
    } 
}
