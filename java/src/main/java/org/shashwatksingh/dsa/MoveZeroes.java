package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
283. Move Zeroes

Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]
 
Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
 

Follow up: Could you minimize the total number of operations done?
*/

public class MoveZeroes {

    public static void main(String[] args) {
        MoveZeroes moveZeroes = new MoveZeroes();
        int[] arr = new int[] {0,1,0,3,12};
        moveZeroes.onePassSolution(arr);
        // moveZeroes.twoPassSolution(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    public void twoPassSolution(int[] nums) {
        int left = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i]!=0) {
                nums[left++] = nums[i];
            }
        }

        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public void onePassSolution(int[] nums) {
        int left = 0, right = 0, n = nums.length;

        while(right<n) {
            if(nums[right]!=0) {
                int temp = nums[left];
                nums[left++] = nums[right];
                nums[right] = temp;
            }
            right++;
        }

    }
    
}
