package com.sinjinsong.leetcode.easy;

import com.sinjinsong.leetcode.ListNode;

import java.util.Stack;

public class LeetCode234 {
    /**
     * 请判断一个链表是否为回文链表。
     *
     * 示例 1:
     *
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     *
     * 输入: 1->2->2->1
     * 输出: true
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        Stack<Integer> stack = new Stack<>();
        ListNode slow = head, fast = head;
        while(fast != null && fast.next != null) {
            stack.add(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        // fast不为空，说明是总共是奇数个，slow正好在最中间；fast为空，说明总共是偶数个，slow在中间靠右的位置
        if(fast != null) {
            slow = slow.next;
        }
        while(slow != null) {
            if(slow.val != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        return stack.isEmpty();
    }
}
