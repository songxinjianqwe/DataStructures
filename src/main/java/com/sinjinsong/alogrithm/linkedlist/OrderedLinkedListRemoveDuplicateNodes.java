package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class OrderedLinkedListRemoveDuplicateNodes {
    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head, post = head.next;
        while (post != null) {
            if (curr.val == post.val) {
                curr.next = curr.next.next;
                post = curr.next;
            } else {
                curr = post;
                post = post.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{1, 1, 3, 4, 4, 4, 5, 5, 7, 8, 8});
        ListNode newHead = removeDuplicateNodes(head);

        ListNode.print(newHead);
    }
}
