package com.sinjinsong.leetcode.medium;

import com.sinjinsong.alogrithm.linkedlist.ListNode;

public class LeetCode92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        int index = 1;
        ListNode curr = head, pre = null, post = head.next;
        while (index < m) {
            pre = curr;
            curr = post;
            post = post.next;
            index++;
        }
        if(pre != null) {
            pre.next = null;
        }
        ListNode firstSegTail = pre;
        pre = null;
        ListNode secondSegTail = curr;
        while(index < n) {
            curr.next = pre;
            pre = curr;
            curr = post;
            post = post.next;
            index++;
        }
        curr.next = pre;
        // curr此时是secondSegHead
        if(firstSegTail != null) {
            firstSegTail.next = curr;
        } else {
            // 第一段为空时，新的头结点就是第二段的头
            head = curr;
        }
        // post此时是thirdSegHead
        secondSegTail.next = post;
        return head;
    }
}
