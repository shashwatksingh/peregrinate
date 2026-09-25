package org.shashwatksingh.dsa;

import java.util.*;

public class UniqueElements {

    public static void main(String[] args) {
        UniqueElements uniqueElements = new UniqueElements();
        Arrays.stream(uniqueElements.solutionSorting(new int[] { 1, 2, 3, 4, 5, 1, 2, 3 }))
                .forEach(System.out::println);
    }

    public int[] solutionSets(int[] nums) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> seen = new HashSet<>();

        for (int i : nums) {
            if (seen.add(i)) {
                set.add(i);
            } else {
                set.remove(i);
            }
        }

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] solutionHashMap(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            if (!set.add(i))
                list.remove(Integer.valueOf(i));
            else
                list.add(i);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public int[] solutionSorting(int[] nums) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int j = i;
            while (j < nums.length && nums[j] == nums[i]) j++;
            if (j - i == 1) list.add(nums[i]);
            i = j;
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
