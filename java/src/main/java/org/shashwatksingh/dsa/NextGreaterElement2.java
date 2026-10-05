package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/*
503. Next Greater Element II
Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. 
If it doesn't exist, return -1 for this number.

Example 1:
Input: nums = [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2; 
The number 2 can't find next greater number. 
The second 1's next greater number needs to search circularly, which is also 2.

Example 2:
Input: nums = [1,2,3,4,3]
Output: [2,3,4,-1,4]
 

Constraints:
1 <= nums.length <= 104
-10^9 <= nums[i] <= 10^9
*/

public class NextGreaterElement2 {

    public static void main(String[] args) {
        NextGreaterElement2 nextgGreaterElement2 = new NextGreaterElement2();
        // Arrays.stream(nextgGreaterElement2.bruteForce(new int[]{1,2,1})).forEach(System.out::println);
        // Arrays.stream(nextgGreaterElement2.bruteForce(new int[]{1,2,3,4,3})).forEach(System.out::println);
        Arrays.stream(nextgGreaterElement2.bruteForce(new int[]{1,2,3,2,1})).forEach(System.out::println);
    }

    public int[] bruteForce(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        int[] doublenums = new int[n * 2];

        System.arraycopy(nums, 0, doublenums, 0, n);
        System.arraycopy(nums, 0, doublenums, n, n);

        for (int i = 0; i < n; i++) {
            res[i]=-1;
            for (int j = i + 1; j < n*2; j++) {
                if (doublenums[j] > doublenums[i]) {
                    res[i] = doublenums[j];
                    break;
                }
            }
        }

        return res;
    }

    public int[] optimizedBruteForce(int[] nums) {
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) {
                if (nums[(i + j) % nums.length] > nums[i]) {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }

        return res;
    }

    public int[] nextGreaterElementsStack(int[] nums) { 
        int n = nums.length; 
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()]<nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            if(res[i]!=-1 && stack.isEmpty()) continue;
            while (!stack.isEmpty() && nums[stack.peek()]<nums[i]) {
                res[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        return res;
    }

    public int[] nextGreaterElementsOptimisedStack(int[] nums) { 
        int n = nums.length; 
        Deque<Integer> stack = new ArrayDeque<>();
        int[] res = new int[n];
        Arrays.fill(res, -1);
        
        for (int i = 2*n; i >=0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i%n]) {
                stack.pop();
            }
            res[i % nums.length] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }

        return res;
    }
}
