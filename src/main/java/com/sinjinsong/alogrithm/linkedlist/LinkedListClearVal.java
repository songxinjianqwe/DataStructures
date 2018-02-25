package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class LinkedListClearVal {

    public static ListNode remove(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            head = head.next;
        }
        ListNode pre = head,curr = head.next;
        while (curr != null) {
            // 每次删除curr
            if (curr.val == val) {
                pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{5, 2, 5, 7, 4, 5, 5});
        ListNode newHead = remove(head, 5);
        ListNode.print(newHead);
    }
}
