package org.shashwatksingh.dsa;

import java.util.Arrays;

/*
252. Meeting Rooms

You are given an array of meeting times intervals where intervals[i] = [starti, endi].

A person can attend all meetings if no two meeting intervals overlap. 
Meetings ending at time t and starting at time t do not overlap.

​​​​​​​Return true if a person can attend all meetings. Otherwise, return false.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
Output: false

Example 2:
Input: intervals = [[7,10],[2,4]]
Output: true
 

Constraints:
0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti < endi <= 106

*/

public class MeetingRooms {

    public static void main(String[] args) {
        MeetingRooms meetingRooms = new MeetingRooms();
        System.out.println(meetingRooms.canAttendMeetingsBruteForce(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.println(meetingRooms.canAttendMeetingsBruteForce(new int[][]{{7,10},{2,4},{5,6}}));
    }

    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length == 0) return true;

        Arrays.sort(intervals, (a, b) -> a[0]-b[0]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0]<intervals[i-1][1]) {
                return false;
            }
        }
        
        return true;
    }

    public boolean canAttendMeetingsSolution(int[][] intervals) {
        if(intervals.length == 0) return true;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        for(int i = 0; i< intervals.length-1; i++) {
            if(intervals[i+1][0] < intervals[i][1]) return false;
        }
        return true;
    }

    public boolean canAttendMeetingsBruteForce(int[][] intervals) {
        // Compare every pair of meetings
        for (int i = 0; i < intervals.length-1; i++) {
            for (int j = i + 1; j < intervals.length; j++) {
                int start1 = intervals[i][0];
                int end1 = intervals[i][1];
                int start2 = intervals[j][0];
                int end2 = intervals[j][1];
                
                // Check if meeting i and meeting j overlap
                if (start1 < end2 && start2 < end1) return false; // Overlap found
            }
        }
        return true;
    }

}
