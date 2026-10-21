package org.shashwatksingh.dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. 
The answer is guaranteed to be unique (except for the order that it is in).


Example 1:
Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].

Example 2:
Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 

Constraints:
1 <= k <= points.length <= 104
-104 <= xi, yi <= 104

*/

public class KClosestPointstoOrigin {

    public int[][] kClosestArraySort(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> (int)squaredEuclideanDistance(a) - (int)squaredEuclideanDistance(b));
        return Arrays.copyOf(points, k);
    }

    private double  squaredEuclideanDistance(int[] point) {
        return point[0]*point[0] + point[1]*point[1];
    }

    public int[][] kClosest(int[][] points, int k) {
        // {index, distance^2}
        Map<Integer, Integer> map = new HashMap<>();
        // PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) ->
        // Integer.compare(map.get(b), map.get(a)));
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> map.get(b) - map.get(a));
        int i = 0;
        for (int[] point : points) {
            int sqDistance = point[0] * point[0] + point[1] * point[1];
            map.put(i, sqDistance);
            heap.add(i++);
            if (heap.size() > k) {
                heap.remove();
            }
        }
        return heap.stream()
                .map(ele -> points[ele])
                .toArray(int[][]::new);
    }

    public int[][] kClosestBinarySearch(int[][] points, int k) {
        int len = points.length;
        double[] distances = new double[len];
        double low = 0, high = 0;
        List<Integer> remaining = new ArrayList<>();
        
        // Computing the Euclidean distance for each point
        // figure out the highes element for the range of the values

        for (int i = 0; i < len; i++) {
            distances[i] = squaredEuclideanDistance(points[i]);
            high = Math.max(high, distances[i]);
            remaining.add(i);
        }

        // Perform a binary search of the distances to find the k closest points
        List<Integer> closest = new ArrayList<>();
        while (k>0) {
            double mid = low + (high-low)/2;
            List<List<Integer>> result = splitDistances(remaining, distances, mid);
            List<Integer> closer = result.get(0), farther = result.get(1);

            if(closer.size()>k) {
                remaining = closer;
                high = mid;
            } else {
                k-=closer.size();
                closest.addAll(closer);
                remaining = farther;
                low = mid;
            }
        }

        return closest.stream().map(ele -> points[ele]).toArray(int[][]::new);
    }

    private List<List<Integer>> splitDistances(List<Integer> remaining, double[] distances, double mid) {
        // Split the distances around the midpoint
        // and return them in separate lists
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> closer = new ArrayList<>();
        List<Integer> farther = new ArrayList<>();
        result.add(closer);
        result.add(farther);

        for (int point : remaining) {
            if (distances[point] <= mid) {
                closer.add(point);
            } else {
                farther.add(point);
            }
        }

        return result;
    } 

    public int[][] kClosestQuickSelect(int[][] points, int k) {
        return quickSelect(points, k);
    }

    private int[][] quickSelect(int[][] points, int k) {
        int left = 0, right = points.length - 1;
        int pivotIndex = points.length;

        while(pivotIndex!=k) {
            // Repeatedly partition the array while narrowing in on the kth element
            pivotIndex = partition(points, left, right);
            if(pivotIndex<k) {
                left = pivotIndex;
            } else {
                right = pivotIndex-1;
            }
        }
        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] points, int left, int right) {
        int[] pivot = choosePivot(points, left, right);
        int pivotDist = (int) squaredEuclideanDistance(pivot);

        while (left<right) {
            if((int) squaredEuclideanDistance(points[left])>=pivotDist) {
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                right--;
            } else {
                left++;
            }
        }

        // Ensure the left pointer is just past the end of the left range then return it as the new pivotIndex
        if ((int) squaredEuclideanDistance(points[left]) < pivotDist) left++;
        return left;
    }

    private int[] choosePivot(int[][] points, int left, int right) {
        return points[left+(right-left)/2];
    }

}
