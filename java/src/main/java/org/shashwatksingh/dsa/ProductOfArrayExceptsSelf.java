package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
238. Product of Array Except Self
Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.
 

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 

Constraints:
2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 

Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
*/

public class ProductOfArrayExceptsSelf {

    public static void main(String[] args) {
        ProductOfArrayExceptsSelf prd = new ProductOfArrayExceptsSelf();
        // [27000,27000,-27000,-27000]
        Arrays.stream(prd.solution1(new int[] {30,-30,30,-30})).forEach(System.out::println);
        Arrays.stream(prd.solution2(new int[] {30,-30,30,-30})).forEach(System.out::println);
        Arrays.stream(prd.solution3(new int[] {30,-30,30,-30})).forEach(System.out::println);
    }

    public int[] solution1(int[] nums) {
        int n = nums.length;
        int[] lProd = new int[n];
        int[] rProd = new int[n];
        int[] res = new int[n];

        lProd[0] = 1;
        for (int i = 1; i < n; i++) {
            lProd[i] = lProd[i-1]*nums[i-1];
        }

        rProd[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            rProd[i] = rProd[i+1]*nums[i+1];
        }

        for (int j = 0; j < res.length; j++) {
            res[j] = lProd[j]*rProd[j];
        }
        return res;
    }

    //Space Optimisation from above
    public int[] solution2(int[] nums) {
        int n = nums.length;
        int[] lProd = new int[n];
        int[] res = new int[n];

        lProd[0] = 1;
        for (int i = 1; i < n; i++) {
            lProd[i] = lProd[i-1]*nums[i-1];
        }

        res[n-1] = 1;
        for (int i = n-2; i >= 0; i--) {
            res[i] = res[i+1]*nums[i+1];
        }

        for (int j = 0; j < res.length; j++) {
            res[j] = lProd[j]*res[j];
        }
        return res;
    }

    //Space Optimisation from above
    public int[] solution3(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int prodValue = 1;
        res[0]=prodValue;
        for (int i = 1; i < n; i++) {
            prodValue = prodValue*nums[i-1];
            res[i] = prodValue;
        }

        prodValue = 1;
        for (int i = n-2; i >= 0; i--) {
            prodValue = prodValue*nums[i+1];
            res[i] = res[i]* prodValue;
        }
        return res;
    }
}
