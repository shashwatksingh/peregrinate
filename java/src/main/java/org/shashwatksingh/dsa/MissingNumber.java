package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
268. Missing Number
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:
Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.

Example 2:
Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.

Example 3:
Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.

Constraints:
n == nums.length
1 <= n <= 104
0 <= nums[i] <= n
All the numbers of nums are unique.
 

Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
*/

public class MissingNumber {
    public int bruteForce(int[] nums) {
        int n = nums.length;
        for (int i = 0; i <= n; i++) {
            boolean exist = false;
            for (int j = 0; j < n; j++) {
                 if(nums[j]==i) exist =true;
            }
            if(!exist) return i;
        }
        return -1;
    }

    public int sortSolution(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if(nums[i]!=i) return i;
        }
        return n;
    }

    public int xorSolution(int[] nums) {
        int missingNum = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missingNum ^= i ^ nums[i];
        }
        return missingNum;
    }

    public int gaussSolution(int[] nums) {
        int n = nums.length;
        int expectedSum = (n * (n+1))/2;
        int currSum = 0;
        for (int i : nums) {
            currSum+=i;
        }
        return expectedSum-currSum;
    }

    public int setSolution(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        System.out.println(set);

        for (int i = 0; i <= n; i++ ) {
            if(set.add(i)) return i;
        }
        return n;
    }
}
