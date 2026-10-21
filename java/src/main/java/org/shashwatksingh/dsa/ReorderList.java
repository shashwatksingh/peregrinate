package org.shashwatksingh.dsa;

import org.shashwatksingh.dsa.helpers.ListNode;

public class ReorderList {
    public ListNode solution(ListNode head) {
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();

        ListNode slow = head, fast = head;

        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        l2 = slow.next;
        slow.next = null;

        ReverseLinkedList rll = new ReverseLinkedList();
        l2 = rll.solutionIterative(l2);
        ListNode temp = head, temp2 = l2, temp1 = l1;
        while(temp!=null && temp2!=null) {
            temp1.next = temp;
            temp = temp.next;
            temp1.next = temp;
            temp = temp.next;
            temp2 = temp.next;
        }

        return l1.next;
    }
}
