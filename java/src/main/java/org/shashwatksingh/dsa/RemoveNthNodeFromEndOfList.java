package org.shashwatksingh.dsa;

import org.shashwatksingh.dsa.helpers.ListNode;

/*
19. Remove Nth Node From End of List

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:
The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 

Follow up: Could you do this in one pass?

*/

public class RemoveNthNodeFromEndOfList {

    public ListNode solution2PassWithDummyNode(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        int length = 0;
        ListNode first = head;
        while (first != null) {
            length++;
            first = first.next;
        }

        length -= n;
        first = dummy;

        while (length > 0) {
            length--;
            first = first.next;
        }

        ListNode x = first.next;
        first.next = first.next.next;
        x.next = null;
        return dummy.next;
    }

    public ListNode solution2Pass(ListNode head, int n) {
        int l = 0;
        ListNode temp = head;
        while (temp!=null) {
            temp = temp.next;
            l++;
        }

        if (l==n) {
            return head.next;
        }

        temp = head;

        for (int i = 0; i < l-n-1; i++) {
            temp = temp.next;
        }

        ListNode x = temp.next;
        temp.next = temp.next.next;
        x.next = null;
        return head;
    }

    public ListNode solution1Pass(ListNode head, int n) {
        ListNode curr = head;
        for (int i = 0; i < n; i++) {
            curr = curr.next;
        }

        if(curr == null) return head.next;

        ListNode temp = head;

        while (curr.next != null) {
            temp = temp.next;
            curr = curr.next;
        }

        ListNode x = temp.next;
        temp.next = temp.next.next;
        x.next = null;
        return head;
    }
}
