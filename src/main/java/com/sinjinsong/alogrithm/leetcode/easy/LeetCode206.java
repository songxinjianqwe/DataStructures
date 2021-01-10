package com.sinjinsong.alogrithm.leetcode.easy;

import com.sinjinsong.alogrithm.leetcode.ListNode;

public class LeetCode206 {
    public ListNode reverseList(ListNode head) {
        if(head == null) {
            return head;
        }
        ListNode curr = head;
        ListNode post = curr.next;
        ListNode pre = null;
        while(post != null) {
            curr.next = pre;
            pre = curr;
            curr = post;
            post = post.next;
        }
        curr.next = pre;
        return curr;
    }
}
