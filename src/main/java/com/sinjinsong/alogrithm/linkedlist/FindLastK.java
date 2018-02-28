package com.sinjinsong.alogrithm.linkedlist;


public class FindLastK {

    /**
     * 在不改变单链表结构的前提下，尽可能快地找到倒数第k个元素
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode findLastK(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            } else {
                fast = fast.next;// 先移动index个位置
            }
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{1, 2, 3, 4, 5});
        System.out.println(findLastK(head, 5));
        System.out.println(findLastK(head, 1));
        System.out.println(findLastK(head, 3));

    }
}
