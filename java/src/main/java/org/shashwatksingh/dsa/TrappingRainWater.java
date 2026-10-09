package org.shashwatksingh.dsa;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.


Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9
 

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/

public class TrappingRainWater {

    public static void main(String[] args) {
        TrappingRainWater trappingRainWater = new TrappingRainWater();
        System.out.println(trappingRainWater.trapStack(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(trappingRainWater.trapStack(new int[] {4,2,0,3,2,5}));
    }

    public int trapBruteForce(int[] height) {
        int len = height.length, res = 0;
        
        for (int i = 1; i < len-1; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            for (int j = i; j < len; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            res += Math.min(leftMax, rightMax) - height[i]; 
        }

        return res;
    }

    public int trapMemoization(int[] height) {
        int len = height.length, res = 0;
        if(len == 0) return res;

        int[] leftMax = new int[len];
        leftMax[0] = height[0];
        
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        int[] rightMax = new int[len];
        rightMax[len-1] = height[len-1];

        for (int i = len-2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        for (int i = 1; i < rightMax.length-1; i++) {
            res += Math.min(leftMax[i],rightMax[i]) - height[i];
        }

        return res;
    }

    public int trapStack(int[] height) {
        int res = 0, len = height.length;
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[stack.peek()]<height[i]) {
                int top = stack.pop();
                if(stack.isEmpty()) break;
                int distance = i - stack.peek() - 1;
                int boundedHeight = Math.min(height[i], height[stack.peek()]) - height[top];
                res += distance * boundedHeight;
            }
            stack.push(i);
        }

        return res;
    }

    public int trap(int[] height) {
        int res = 0, len = height.length;
        int left = 0, right = len-1, leftMax = 0, rightMax = 0;
        while(left<right) {
            if(height[left]<height[right]){
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }

        return res;
    }
}
