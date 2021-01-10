package com.sinjinsong.alogrithm.leetcode.easy;

import com.sinjinsong.alogrithm.leetcode.ListNode;

public class LeetCode22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        ListNode slow = head;
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
