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
                fast = fast.next;// 先移动k个位置
            }
        }
        //如果fast为null，那么倒数第k个节点就是头节点
        if (fast == null) {
            return head;
        }
        // 结束时slow指向倒数第k+1个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.next;
    }

    /**
     * 删除倒数第k个节点，返回新的头节点
     * @param head
     * @param k
     * @return
     */
    public static ListNode deleteLastK(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            } else {
                fast = fast.next;// 先移动k个位置
            }
        }
        //如果fast为null，那么倒数第k个节点就是头节点
        if (fast == null) {
            return head.next;
        }
        // 结束时slow指向倒数第k+1个节点
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{1, 2, 3, 4, 5});
//        System.out.println(findLastK(head, 5));
//        System.out.println(findLastK(head, 1));
//        System.out.println(findLastK(head, 3));
//        ListNode.print(deleteLastK(head,3));
        
    }
}
