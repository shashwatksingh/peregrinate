package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
56. Merge Intervals

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1:
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].

Example 2:
Input: intervals = [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Example 3:
Input: intervals = [[4,7],[1,4]]
Output: [[1,7]]
Explanation: Intervals [1,4] and [4,7] are considered overlapping.
 

Constraints:
1 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 104
*/

public class MergeIntervals {

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        // int[][] res = mergeIntervals.merge(new int[][] { { 1, 4 }, { 4, 5 } });
        int[][] res = mergeIntervals.merge(new int[][] { {1, 10}, {2, 3} });
        

        Arrays.stream(res).forEach(ele -> {
            System.out.println();
            Arrays.stream(ele).forEach(System.out::println);
        });
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for(int[] interval: intervals) {
            if(res.isEmpty() || res.getLast()[1] < interval[0]) res.add(interval);
            else res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
        }

        return res.toArray(new int[res.size()][]);
    }

    public int[][] mergeIntervalsBruteForce(int[][] intervals) {
        int n = intervals.length;
        boolean mergedAny = true;

        // Keep running the nested loops as long as we are still finding merges
        while (mergedAny) {
            mergedAny = false;

            for (int i = 0; i < n; i++) {
                // Skip if this interval was already consumed/merged away
                if (intervals[i][0] == -1) continue;

                for (int j = 0; j < n; j++) {
                    // Skip self or already consumed intervals
                    if (i == j || intervals[j][0] == -1) continue;

                    int start1 = intervals[i][0];
                    int end1 = intervals[i][1];
                    int start2 = intervals[j][0];
                    int end2 = intervals[j][1];

                    // Check for overlap (Note: intervals can touch, so use <= instead of <)
                    if (start1 <= end2 && start2 <= end1) {
                        // Merge the boundaries directly into interval i
                        intervals[i][0] = Math.min(start1, start2);
                        intervals[i][1] = Math.max(end1, end2);
                        
                        // Mark interval j as consumed
                        intervals[j][0] = -1;
                        intervals[j][1] = -1;
                        
                        // We made a change, so we must check everything again
                        mergedAny = true; 
                    }
                }
            }
        }
        
        return Arrays.stream(intervals).filter(ele -> ele[0]!=-1).toArray(int[][]::new);

        // Collect all intervals that were not marked as -1
        // List<int[]> result = new ArrayList<>();
        // for (int[] interval : intervals) {
        //     if (interval[0] != -1) {
        //         result.add(interval);
        //     }
        // }

        // return result.toArray(new int[result.size()][]);
    }

}
