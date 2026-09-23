package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
448. Find All Numbers Disappeared in an Array
Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.


Example 1:
Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]

Example 2:
Input: nums = [1,1]
Output: [2]
 

Constraints:
n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
 

Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
*/

public class MissingElementsInArray {

    public static void main(String[] args) {
        MissingElementsInArray meia = new MissingElementsInArray();
        Arrays.stream(meia.bruteForceSolution(new int[] {4,3,2,7,8,2,3,1})).forEach(System.out::println);
        Arrays.stream(meia.setSolution(new int[] {4,3,2,7,8,2,3,1})).forEach(System.out::println);
        Arrays.stream(meia.optimisedSolution(new int[] {4,3,2,7,8,2,3,1})).forEach(System.out::println);
    }
    
    public int[] bruteForceSolution(int[] nums){
        List<Integer> list = new ArrayList<>();
        int n = nums.length;

        for(int i = 1; i<=n; i++ ) {
            boolean found = false;
            for (Integer num : nums) {
                if(num==i) found = true;
            }
            if(!found) list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] setSolution(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }
        
        for (int i = 1; i <= n; i++) {
            if(set.add(i)) list.add(i);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] optimisedSolution(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1 ;
            if (nums[index] > 0) nums[index] *= -1;
        }

        for (int i = 1; i <= n; i++) {
            if(nums[i-1]>0) list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
