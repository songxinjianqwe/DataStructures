package com.sinjinsong.alogrithm.linkedlist;


/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class LinkedListMergeSort {

    /**
     * 时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1）
     * @param head
     * @return
     */
    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // fast 一定不为空
        ListNode secondHalf = slow.next;
        
        slow.next = null;
        // 前半段 head~slow 
        ListNode firstHalf = mergeSort(head);
        // 后半段 slow.next ~ fast
        secondHalf = mergeSort(secondHalf);
        return merge(firstHalf, secondHalf);
    }


    private static ListNode merge(ListNode firstHalf, ListNode secondHalf) {
        if (firstHalf == null || secondHalf == null) {
            return firstHalf != null ? firstHalf : secondHalf;
        }
        ListNode head = null;
        ListNode tail = null;
        while (firstHalf != null && secondHalf != null) {
            if (firstHalf.val <= secondHalf.val) {
                if (head == null) {
                    tail = head = firstHalf;
                } else {
                    tail.next = firstHalf;
                    tail = firstHalf;
                }
                firstHalf = firstHalf.next;
            } else {
                if (head == null) {
                    tail = head = secondHalf;
                } else {
                    tail.next = secondHalf;
                    tail = secondHalf;
                }
                secondHalf = secondHalf.next;
            }
        }
        if (firstHalf != null) {
            tail.next = firstHalf;
        }
        if (secondHalf != null) {
            tail.next = secondHalf;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{5, 2, 4, 1, 3});
        ListNode newHead = mergeSort(head);
        ListNode.print(newHead);
    }
}
