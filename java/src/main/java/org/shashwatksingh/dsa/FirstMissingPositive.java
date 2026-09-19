package org.shashwatksingh.dsa;

/*
41. First Missing Positive
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Example 1:
Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.

Example 2:
Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.

Example 3:
Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.
 

Constraints:
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
*/

public class FirstMissingPositive{

    public static void main(String[] args) {
        FirstMissingPositive firstMissingPositive = new FirstMissingPositive();
        System.out.println(firstMissingPositive.firstMissingPositiveIndex(new int[]{3,4,-1,1}));
        System.out.println(firstMissingPositive.firstMissingPositiveIndex(new int[]{7,8,9,11,12}));
        System.out.println(firstMissingPositive.firstMissingPositiveIndex(new int[]{1,2,0}));
    }

    // Let's create an array with the boolean values and look for the missing values
    public int firstMissingPositiveBooleanArray(int[] nums){
        int n = nums.length;
        boolean[] seen = new boolean[n+1];

        for (int i = 0; i < n; i++) {
            if(nums[i]>=0 && nums[i]<=n) seen[nums[i]] = true;
        }
        for (int i = 1; i < n+1; i++) {
            if(seen[i]==false) return i;
        }
        return n+1;
    }

    public int firstMissingPositiveIndex(int[] nums) {
        int n = nums.length;
        boolean oneExists = false;
        for(int i = 0; i< n; i++) {
            if(nums[i] == 1) oneExists = true;
            if(nums[i]<=0 || nums[i]>n) nums[i] = 1;
        }

        if(!oneExists) return 1;

        for(int i = 0; i< n; i++) {
            int a = Math.abs(nums[i]);
            if(a==n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[a] = -Math.abs(nums[a]);
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if(nums[i]>0) return i;
        }

        return nums[0]>0 ? n : n+1;
    }
}
