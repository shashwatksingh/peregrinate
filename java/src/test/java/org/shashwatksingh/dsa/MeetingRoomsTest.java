package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Meeting Rooms Tests")
class MeetingRoomsTest {

    private MeetingRooms instance;

    @BeforeEach
    void setUp() {
        instance = new MeetingRooms();
    }

    // ═══════════════════════════════════════════════════════════
    //  canAttendMeetings()  — sort by start time, adjacent check, O(n log n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("canAttendMeetings() — sort by start time")
    class CanAttendMeetingsTests {

        @Test
        @DisplayName("LeetCode Example 1: [[0,30],[5,10],[15,20]] → false")
        void testLeetCodeExample1() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[7,10],[2,4]] → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{7, 10}, {2, 4}}));
        }

        @Test
        @DisplayName("Empty input: [] → true")
        void testEmptyInput() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{}));
        }

        @Test
        @DisplayName("Single meeting: [[0,1]] → true")
        void testSingleMeeting() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{0, 1}}));
        }

        @Test
        @DisplayName("Back-to-back meetings touching at one time (no overlap): [[0,5],[5,10]] → true")
        void testBackToBackTouching() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{0, 5}, {5, 10}}));
        }

        @Test
        @DisplayName("Back-to-back meetings given out of order: [[5,10],[0,5]] → true")
        void testBackToBackUnsorted() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{5, 10}, {0, 5}}));
        }

        @Test
        @DisplayName("Overlap by one time unit: [[0,5],[4,10]] → false")
        void testOverlapByOne() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{0, 5}, {4, 10}}));
        }

        @Test
        @DisplayName("Meeting fully contained in another: [[0,10],[2,3]] → false")
        void testContainedMeeting() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{0, 10}, {2, 3}}));
        }

        @Test
        @DisplayName("Identical meetings: [[1,2],[1,2]] → false")
        void testIdenticalMeetings() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{1, 2}, {1, 2}}));
        }

        @Test
        @DisplayName("Same start time, different ends: [[1,3],[1,2]] → false")
        void testSameStartTime() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{1, 3}, {1, 2}}));
        }

        @Test
        @DisplayName("Reverse-sorted, non-overlapping: [[7,10],[4,6],[1,2]] → true")
        void testReverseSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{7, 10}, {4, 6}, {1, 2}}));
        }

        @Test
        @DisplayName("Sorted ascending, non-overlapping with gaps: [[1,2],[3,4],[5,6]] → true")
        void testSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        @DisplayName("Overlap only visible after sorting: [[1,3],[10,12],[2,5]] → false")
        void testOverlapAfterSorting() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{1, 3}, {10, 12}, {2, 5}}));
        }

        @Test
        @DisplayName("Chain of touching meetings: [[0,1],[1,2],[2,3],[3,4]] → true")
        void testChainOfTouchingMeetings() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        }

        @Test
        @DisplayName("Overlap at the end of the schedule: [[1,2],[3,4],[3,5]] → false")
        void testOverlapAtEnd() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{1, 2}, {3, 4}, {3, 5}}));
        }

        @Test
        @DisplayName("Max time range, single meeting: [[0,1000000]] → true")
        void testMaxTimeSingle() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{0, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, two touching halves: [[0,500000],[500000,1000000]] → true")
        void testMaxTimeTouching() {
            assertEquals(true, instance.canAttendMeetings(new int[][]{{0, 500000}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, halves overlapping by one: [[0,500001],[500000,1000000]] → false")
        void testMaxTimeOverlapping() {
            assertEquals(false, instance.canAttendMeetings(new int[][]{{0, 500001}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order → true")
        void testMaxSizeNoOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            assertEquals(true, instance.canAttendMeetings(intervals));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order, last one starts a unit early → false")
        void testMaxSizeSingleOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            intervals[0][0] -= 1;
            assertEquals(false, instance.canAttendMeetings(intervals));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  canAttendMeetingsSolution()  — sort by start time, adjacent check, O(n log n) time, O(1) extra space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("canAttendMeetingsSolution() — sort by start time")
    class CanAttendMeetingsSolutionTests {

        @Test
        @DisplayName("LeetCode Example 1: [[0,30],[5,10],[15,20]] → false")
        void testLeetCodeExample1() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[7,10],[2,4]] → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{7, 10}, {2, 4}}));
        }

        @Test
        @DisplayName("Empty input: [] → true")
        void testEmptyInput() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{}));
        }

        @Test
        @DisplayName("Single meeting: [[0,1]] → true")
        void testSingleMeeting() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{0, 1}}));
        }

        @Test
        @DisplayName("Back-to-back meetings touching at one time (no overlap): [[0,5],[5,10]] → true")
        void testBackToBackTouching() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{0, 5}, {5, 10}}));
        }

        @Test
        @DisplayName("Back-to-back meetings given out of order: [[5,10],[0,5]] → true")
        void testBackToBackUnsorted() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{5, 10}, {0, 5}}));
        }

        @Test
        @DisplayName("Overlap by one time unit: [[0,5],[4,10]] → false")
        void testOverlapByOne() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{0, 5}, {4, 10}}));
        }

        @Test
        @DisplayName("Meeting fully contained in another: [[0,10],[2,3]] → false")
        void testContainedMeeting() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{0, 10}, {2, 3}}));
        }

        @Test
        @DisplayName("Identical meetings: [[1,2],[1,2]] → false")
        void testIdenticalMeetings() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{1, 2}, {1, 2}}));
        }

        @Test
        @DisplayName("Same start time, different ends: [[1,3],[1,2]] → false")
        void testSameStartTime() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{1, 3}, {1, 2}}));
        }

        @Test
        @DisplayName("Reverse-sorted, non-overlapping: [[7,10],[4,6],[1,2]] → true")
        void testReverseSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{7, 10}, {4, 6}, {1, 2}}));
        }

        @Test
        @DisplayName("Sorted ascending, non-overlapping with gaps: [[1,2],[3,4],[5,6]] → true")
        void testSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        @DisplayName("Overlap only visible after sorting: [[1,3],[10,12],[2,5]] → false")
        void testOverlapAfterSorting() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{1, 3}, {10, 12}, {2, 5}}));
        }

        @Test
        @DisplayName("Chain of touching meetings: [[0,1],[1,2],[2,3],[3,4]] → true")
        void testChainOfTouchingMeetings() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        }

        @Test
        @DisplayName("Overlap at the end of the schedule: [[1,2],[3,4],[3,5]] → false")
        void testOverlapAtEnd() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{1, 2}, {3, 4}, {3, 5}}));
        }

        @Test
        @DisplayName("Max time range, single meeting: [[0,1000000]] → true")
        void testMaxTimeSingle() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{0, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, two touching halves: [[0,500000],[500000,1000000]] → true")
        void testMaxTimeTouching() {
            assertEquals(true, instance.canAttendMeetingsSolution(new int[][]{{0, 500000}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, halves overlapping by one: [[0,500001],[500000,1000000]] → false")
        void testMaxTimeOverlapping() {
            assertEquals(false, instance.canAttendMeetingsSolution(new int[][]{{0, 500001}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order → true")
        void testMaxSizeNoOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            assertEquals(true, instance.canAttendMeetingsSolution(intervals));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order, last one starts a unit early → false")
        void testMaxSizeSingleOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            intervals[0][0] -= 1;
            assertEquals(false, instance.canAttendMeetingsSolution(intervals));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  canAttendMeetingsBruteForce()  — pairwise comparison of every two meetings, O(n²) time, O(1) space
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("canAttendMeetingsBruteForce() — sort by start time")
    class CanAttendMeetingsBruteForceTests {

        @Test
        @DisplayName("LeetCode Example 1: [[0,30],[5,10],[15,20]] → false")
        void testLeetCodeExample1() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{0, 30}, {5, 10}, {15, 20}}));
        }

        @Test
        @DisplayName("LeetCode Example 2: [[7,10],[2,4]] → true")
        void testLeetCodeExample2() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{7, 10}, {2, 4}}));
        }

        @Test
        @DisplayName("Empty input: [] → true")
        void testEmptyInput() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{}));
        }

        @Test
        @DisplayName("Single meeting: [[0,1]] → true")
        void testSingleMeeting() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{0, 1}}));
        }

        @Test
        @DisplayName("Back-to-back meetings touching at one time (no overlap): [[0,5],[5,10]] → true")
        void testBackToBackTouching() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{0, 5}, {5, 10}}));
        }

        @Test
        @DisplayName("Back-to-back meetings given out of order: [[5,10],[0,5]] → true")
        void testBackToBackUnsorted() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{5, 10}, {0, 5}}));
        }

        @Test
        @DisplayName("Overlap by one time unit: [[0,5],[4,10]] → false")
        void testOverlapByOne() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{0, 5}, {4, 10}}));
        }

        @Test
        @DisplayName("Meeting fully contained in another: [[0,10],[2,3]] → false")
        void testContainedMeeting() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{0, 10}, {2, 3}}));
        }

        @Test
        @DisplayName("Identical meetings: [[1,2],[1,2]] → false")
        void testIdenticalMeetings() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{1, 2}, {1, 2}}));
        }

        @Test
        @DisplayName("Same start time, different ends: [[1,3],[1,2]] → false")
        void testSameStartTime() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{1, 3}, {1, 2}}));
        }

        @Test
        @DisplayName("Reverse-sorted, non-overlapping: [[7,10],[4,6],[1,2]] → true")
        void testReverseSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{7, 10}, {4, 6}, {1, 2}}));
        }

        @Test
        @DisplayName("Sorted ascending, non-overlapping with gaps: [[1,2],[3,4],[5,6]] → true")
        void testSortedNonOverlapping() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{1, 2}, {3, 4}, {5, 6}}));
        }

        @Test
        @DisplayName("Overlap only visible after sorting: [[1,3],[10,12],[2,5]] → false")
        void testOverlapAfterSorting() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{1, 3}, {10, 12}, {2, 5}}));
        }

        @Test
        @DisplayName("Chain of touching meetings: [[0,1],[1,2],[2,3],[3,4]] → true")
        void testChainOfTouchingMeetings() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}));
        }

        @Test
        @DisplayName("Overlap at the end of the schedule: [[1,2],[3,4],[3,5]] → false")
        void testOverlapAtEnd() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{1, 2}, {3, 4}, {3, 5}}));
        }

        @Test
        @DisplayName("Max time range, single meeting: [[0,1000000]] → true")
        void testMaxTimeSingle() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{0, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, two touching halves: [[0,500000],[500000,1000000]] → true")
        void testMaxTimeTouching() {
            assertEquals(true, instance.canAttendMeetingsBruteForce(new int[][]{{0, 500000}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max time range, halves overlapping by one: [[0,500001],[500000,1000000]] → false")
        void testMaxTimeOverlapping() {
            assertEquals(false, instance.canAttendMeetingsBruteForce(new int[][]{{0, 500001}, {500000, 1000000}}));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order → true")
        void testMaxSizeNoOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            assertEquals(true, instance.canAttendMeetingsBruteForce(intervals));
        }

        @Test
        @DisplayName("Max size (n=10000): back-to-back meetings in reverse order, last one starts a unit early → false")
        void testMaxSizeSingleOverlap() {
            int n = 10000;
            int[][] intervals = new int[n][];
            for (int i = 0; i < n; i++) {
                int slot = n - 1 - i;
                intervals[i] = new int[]{slot * 100, slot * 100 + 100};
            }
            intervals[0][0] -= 1;
            assertEquals(false, instance.canAttendMeetingsBruteForce(intervals));
        }
    }
}
