package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Min Heap (ArrayList) Tests")
class HeapImplementationTest {

    private HeapImplementation heap;

    @BeforeEach
    void setUp() {
        heap = new HeapImplementation();
    }

    private int[] addAll(int... values) {
        for (int v : values) {
            heap.add(v);
        }
        return values;
    }

    private int[] drain(int count) {
        int[] out = new int[count];
        for (int i = 0; i < count; i++) {
            out[i] = heap.poll();
        }
        return out;
    }

    private int[] sorted(int[] values) {
        int[] copy = values.clone();
        Arrays.sort(copy);
        return copy;
    }

    // ═══════════════════════════════════════════════════════════
    //  add() + peek()  — insert and read the minimum, O(log n) / O(1)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("add() + peek() — insert and read the minimum")
    class AddPeekTests {

        @Test
        @DisplayName("Scenario: add [5] → peek 5 (single element)")
        void testSingleElement() {
            heap.add(5);
            assertEquals(5, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [3, 1] → peek 1 (second element becomes new minimum)")
        void testTwoElementsNewMinimum() {
            addAll(3, 1);
            assertEquals(1, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [1, 3] → peek 1 (second element is larger)")
        void testTwoElementsKeepMinimum() {
            addAll(1, 3);
            assertEquals(1, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [7, 7, 7, 7] → peek 7 (all same)")
        void testAllSame() {
            addAll(7, 7, 7, 7);
            assertEquals(7, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [1, 2, 3, 4, 5] → peek 1 (strictly increasing)")
        void testStrictlyIncreasing() {
            addAll(1, 2, 3, 4, 5);
            assertEquals(1, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [5, 4, 3, 2, 1] → peek 1 (strictly decreasing)")
        void testStrictlyDecreasing() {
            addAll(5, 4, 3, 2, 1);
            assertEquals(1, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [10, 20, 30, 40, 50, 5] → peek 5 (new minimum added last must bubble to root)")
        void testNewMinimumAddedLast() {
            addAll(10, 20, 30, 40, 50, 5);
            assertEquals(5, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [50, 40, 30, 20, 10] → peek 10 (value larger than current size)")
        void testValuesLargerThanSize() {
            addAll(50, 40, 30, 20, 10);
            assertEquals(10, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [3, -1, 2, -5, 0] → peek -5 (negatives)")
        void testNegativeValues() {
            addAll(3, -1, 2, -5, 0);
            assertEquals(-5, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [0, Integer.MAX_VALUE, Integer.MIN_VALUE] → peek Integer.MIN_VALUE (extremes)")
        void testIntegerExtremes() {
            addAll(0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            assertEquals(Integer.MIN_VALUE, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [4], peek twice → 4, 4 (peek does not remove)")
        void testPeekDoesNotRemove() {
            heap.add(4);
            assertEquals(4, heap.peek());
            assertEquals(4, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [2, 1, 3], peek after each add → 2, 1, 1 (running minimum)")
        void testPeekAfterEachAdd() {
            int[] seen = new int[3];
            heap.add(2);
            seen[0] = heap.peek();
            heap.add(1);
            seen[1] = heap.peek();
            heap.add(3);
            seen[2] = heap.peek();
            assertEquals(Arrays.toString(new int[]{2, 1, 1}), Arrays.toString(seen));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  poll()  — remove and return the minimum, O(log n)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("poll() — remove and return the minimum")
    class PollTests {

        @Test
        @DisplayName("Scenario: add [5], poll → 5 (single element)")
        void testPollSingleElement() {
            heap.add(5);
            assertEquals(5, heap.poll());
        }

        @Test
        @DisplayName("Scenario: add [2, 1], poll twice → [1, 2] (two elements)")
        void testPollTwoElements() {
            addAll(2, 1);
            assertEquals(Arrays.toString(new int[]{1, 2}), Arrays.toString(drain(2)));
        }

        @Test
        @DisplayName("Scenario: add [3, 1, 2], poll three times → [1, 2, 3]")
        void testPollThreeElements() {
            addAll(3, 1, 2);
            assertEquals(Arrays.toString(new int[]{1, 2, 3}), Arrays.toString(drain(3)));
        }

        @Test
        @DisplayName("Scenario: add [5, 3, 8, 1, 9, 2, 7] → drain [1, 2, 3, 5, 7, 8, 9] (unsorted input)")
        void testDrainUnsorted() {
            addAll(5, 3, 8, 1, 9, 2, 7);
            assertEquals(Arrays.toString(new int[]{1, 2, 3, 5, 7, 8, 9}), Arrays.toString(drain(7)));
        }

        @Test
        @DisplayName("Scenario: add [4, 4, 4, 4] → drain [4, 4, 4, 4] (all same)")
        void testDrainAllSame() {
            addAll(4, 4, 4, 4);
            assertEquals(Arrays.toString(new int[]{4, 4, 4, 4}), Arrays.toString(drain(4)));
        }

        @Test
        @DisplayName("Scenario: add [1..8] ascending → drain [1..8] (strictly increasing)")
        void testDrainIncreasing() {
            addAll(1, 2, 3, 4, 5, 6, 7, 8);
            assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), Arrays.toString(drain(8)));
        }

        @Test
        @DisplayName("Scenario: add [8..1] descending → drain [1..8] (strictly decreasing)")
        void testDrainDecreasing() {
            addAll(8, 7, 6, 5, 4, 3, 2, 1);
            assertEquals(Arrays.toString(new int[]{1, 2, 3, 4, 5, 6, 7, 8}), Arrays.toString(drain(8)));
        }

        @Test
        @DisplayName("Scenario: add [2, 1, 2, 1, 3, 3] → drain [1, 1, 2, 2, 3, 3] (duplicates)")
        void testDrainDuplicates() {
            addAll(2, 1, 2, 1, 3, 3);
            assertEquals(Arrays.toString(new int[]{1, 1, 2, 2, 3, 3}), Arrays.toString(drain(6)));
        }

        @Test
        @DisplayName("Scenario: add [0, -3, 5, -10, 2, -1] → drain [-10, -3, -1, 0, 2, 5] (negatives)")
        void testDrainNegatives() {
            addAll(0, -3, 5, -10, 2, -1);
            assertEquals(Arrays.toString(new int[]{-10, -3, -1, 0, 2, 5}), Arrays.toString(drain(6)));
        }

        @Test
        @DisplayName("Scenario: add [Integer.MAX_VALUE, 0, Integer.MIN_VALUE] → drain [MIN, 0, MAX] (extremes)")
        void testDrainIntegerExtremes() {
            addAll(Integer.MAX_VALUE, 0, Integer.MIN_VALUE);
            assertEquals(Arrays.toString(new int[]{Integer.MIN_VALUE, 0, Integer.MAX_VALUE}),
                    Arrays.toString(drain(3)));
        }

        @Test
        @DisplayName("Scenario: add [10, 20, 30, 40, 50, 60, 70], poll → 10 then peek 20 (peek reflects new minimum)")
        void testPeekAfterPoll() {
            addAll(10, 20, 30, 40, 50, 60, 70);
            assertEquals(10, heap.poll());
            assertEquals(20, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [1, 2], poll twice, add [9], peek → 9 (reuse after emptying)")
        void testReuseAfterEmpty() {
            addAll(1, 2);
            drain(2);
            heap.add(9);
            assertEquals(9, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add [5, 9, 6, 10, 11, 7, 8], poll x3 → 5, 6, 7 (smaller child must be promoted)")
        void testSiftDownChoosesSmallerChild() {
            addAll(5, 9, 6, 10, 11, 7, 8);
            assertEquals(5, heap.poll());
            assertEquals(6, heap.poll());
            assertEquals(7, heap.poll());
        }

        @Test
        @DisplayName("Scenario: add [1, 5, 2, 6, 7, 3, 4], poll once then drain → [2, 3, 4, 5, 6, 7] (left child smaller than right)")
        void testSiftDownLeftChildSmaller() {
            addAll(1, 5, 2, 6, 7, 3, 4);
            assertEquals(1, heap.poll());
            assertEquals(Arrays.toString(new int[]{2, 3, 4, 5, 6, 7}), Arrays.toString(drain(6)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  Interleaved add() / poll()  — mixed operations, O(log n) each
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Interleaved add() / poll() — mixed operations")
    class InterleavedTests {

        @Test
        @DisplayName("Scenario: add 4,2 → poll 2 → add 1,3 → poll 1 → peek 3 (remaining {3, 4})")
        void testInterleavedSmall() {
            addAll(4, 2);
            assertEquals(2, heap.poll());
            addAll(1, 3);
            assertEquals(1, heap.poll());
            assertEquals(3, heap.peek());
        }

        @Test
        @DisplayName("Scenario: add 5,3,8 → poll 3 → add 1 → poll 1 → poll 5 → poll 8")
        void testInterleavedSequence() {
            addAll(5, 3, 8);
            int[] out = new int[4];
            out[0] = heap.poll();
            heap.add(1);
            out[1] = heap.poll();
            out[2] = heap.poll();
            out[3] = heap.poll();
            assertEquals(Arrays.toString(new int[]{3, 1, 5, 8}), Arrays.toString(out));
        }

        @Test
        @DisplayName("Scenario: add value smaller than everything after several polls → it is next to come out")
        void testAddSmallerAfterPolls() {
            addAll(10, 20, 30, 40, 50);
            heap.poll();
            heap.poll();
            heap.add(1);
            assertEquals(1, heap.poll());
            assertEquals(30, heap.poll());
        }

        @Test
        @DisplayName("Scenario: add [3,1,2], poll → 1, add [0], drain → [0, 2, 3] (new minimum after poll)")
        void testPollThenAddNewMinimum() {
            addAll(3, 1, 2);
            assertEquals(1, heap.poll());
            heap.add(0);
            assertEquals(Arrays.toString(new int[]{0, 2, 3}), Arrays.toString(drain(3)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  Empty heap  — peek() / poll() on no elements, O(1)
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Empty heap — peek() / poll() with no elements")
    class EmptyHeapTests {

        private static final String EMPTY = "IllegalStateException: The heap doesn't have values";

        private String peekOutcome() {
            try {
                return String.valueOf(heap.peek());
            } catch (Exception e) {
                return e.getClass().getSimpleName() + ": " + e.getMessage();
            }
        }

        private String pollOutcome() {
            try {
                return String.valueOf(heap.poll());
            } catch (Exception e) {
                return e.getClass().getSimpleName() + ": " + e.getMessage();
            }
        }

        @Test
        @DisplayName("Scenario: new heap, peek → IllegalStateException (nothing added)")
        void testPeekOnNewHeap() {
            assertEquals(EMPTY, peekOutcome());
        }

        @Test
        @DisplayName("Scenario: new heap, poll → IllegalStateException (nothing added)")
        void testPollOnNewHeap() {
            assertEquals(EMPTY, pollOutcome());
        }

        @Test
        @DisplayName("Scenario: add [1], poll, peek → IllegalStateException (emptied by single poll)")
        void testPeekAfterPollingSingleElement() {
            heap.add(1);
            heap.poll();
            assertEquals(EMPTY, peekOutcome());
        }

        @Test
        @DisplayName("Scenario: add [1], poll twice → 1, then IllegalStateException")
        void testPollPastSingleElement() {
            heap.add(1);
            assertEquals("1", pollOutcome());
            assertEquals(EMPTY, pollOutcome());
        }

        @Test
        @DisplayName("Scenario: add [3, 1, 2], drain all, then poll → IllegalStateException")
        void testPollAfterDrain() {
            addAll(3, 1, 2);
            drain(3);
            assertEquals(EMPTY, pollOutcome());
        }

        @Test
        @DisplayName("Scenario: add [3, 1, 2], drain all, then peek → IllegalStateException")
        void testPeekAfterDrain() {
            addAll(3, 1, 2);
            drain(3);
            assertEquals(EMPTY, peekOutcome());
        }

        @Test
        @DisplayName("Scenario: failed poll on empty heap, then add [4, 2] → peek 2 (heap still usable)")
        void testUsableAfterFailedPoll() {
            assertEquals(EMPTY, pollOutcome());
            addAll(4, 2);
            assertEquals("2", peekOutcome());
        }

        @Test
        @DisplayName("Scenario: failed peek on empty heap, then add [4, 2], drain → [2, 4] (heap still usable)")
        void testUsableAfterFailedPeek() {
            assertEquals(EMPTY, peekOutcome());
            addAll(4, 2);
            assertEquals(Arrays.toString(new int[]{2, 4}), Arrays.toString(drain(2)));
        }
    }

    // ═══════════════════════════════════════════════════════════
    //  Max constraint boundary  — large input, heap-sort property
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Large input — draining yields sorted order")
    class LargeInputTests {

        @Test
        @DisplayName("Scenario: 10,000 pseudo-random ints → drain equals sorted array")
        void testLargeRandom() {
            Random random = new Random(42);
            int n = 10_000;
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = random.nextInt(2_000_001) - 1_000_000;
            }
            addAll(values);
            assertEquals(Arrays.toString(sorted(values)), Arrays.toString(drain(n)));
        }

        @Test
        @DisplayName("Scenario: 10,000 descending ints → drain is ascending")
        void testLargeDescending() {
            int n = 10_000;
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = n - i;
            }
            addAll(values);
            assertEquals(Arrays.toString(sorted(values)), Arrays.toString(drain(n)));
        }

        @Test
        @DisplayName("Scenario: 10,000 values in [0, 9] (many duplicates) → drain equals sorted array")
        void testLargeManyDuplicates() {
            Random random = new Random(7);
            int n = 10_000;
            int[] values = new int[n];
            for (int i = 0; i < n; i++) {
                values[i] = random.nextInt(10);
            }
            addAll(values);
            assertEquals(Arrays.toString(sorted(values)), Arrays.toString(drain(n)));
        }
    }
}
