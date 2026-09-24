package org.shashwatksingh.dsa;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
167. Two Sum II - Input Array Is Sorted

You are given a 1-indexed array of integers numbers that is already sorted in non-decreasing order.

Find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.

Return the indices of the two numbers index1 and index2 as an integer array [index1, index2] of length 2.

The tests are generated such that there is exactly one solution. You may not use the same element twice.

Your solution must use only constant extra space.

 

Example 1:
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]
Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].

Example 2:
Input: numbers = [2,3,4], target = 6
Output: [1,3]
Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].

Example 3:
Input: numbers = [-1,0], target = -1
Output: [1,2]
Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 

Constraints:
2 <= numbers.length <= 3 * 104
-1000 <= numbers[i] <= 1000
numbers is sorted in non-decreasing order.
-1000 <= target <= 1000
The tests are generated such that there is exactly one solution.
*/

public class TwoSum2 {

    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target)
                    return new int[] { i, j };
            }
        }
        return new int[] { -1, -1 };
    }

    public int[] twoSumHashMap(int[] numbers, int target) {
        Map<Integer, Integer> complementMap = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            if (complementMap.containsKey(complement))
                return new int[] { complementMap.get(complement), i };
            complementMap.put(numbers[i], i);
        }
        return new int[] { -1, -1 };
    }

    public int[] twoSumBinarySearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            int complement = target - numbers[i];
            int index = Arrays.binarySearch(numbers, complement);
            if (index >= 0) {
                // If it found itself, check if an identical neighbor exists
                if (index == i) {
                    if (index + 1 < numbers.length && numbers[index + 1] == complement) {
                        return new int[] { i, index + 1 };
                    }
                    if (index - 1 >= 0 && numbers[index - 1] == complement) {
                        return new int[] { i, index - 1 };
                    }
                    continue; // No valid distinct duplicate found, skip this 'i'
                }

                // 3. Found a distinct valid complement
                return new int[] { i, index };
            }
        }
        return new int[] { -1, -1 };
    }

    public int[] twoPointers(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target)
                return new int[] { left, right };
            else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left--;
            }
        }
        return new int[] { -1, -1 };
    }

}
