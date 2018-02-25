package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class LinkedListPartition {

    

    /**
     * 给定一个链表的头节点，再给一个数字num，将链表调整为节点值小于num的节点都放在左边，等于的放在中间，大于的放在右边。
     * 简单做法：链表转数组，三色排序问题/荷兰国旗问题。需要额外空间。
     * 最优解：将一个链表转为三个小链表，然后再链到一起。
     * @param head
     * @param val
     * @return
     */
    public static ListNode partition(ListNode head, int val) {
        ListNode lowerHead = null, equalHead = null, upperHead = null;
        ListNode lowerTail = null, equalTail = null, upperTail = null;
        ListNode curr = head, post;

        while (curr != null) {
            post = curr.next;
            // 避免遗留的指针影响
            curr.next = null;

            if (curr.val < val) {
                if (lowerHead == null) {
                    lowerTail = lowerHead = curr;
                } else {
                    lowerTail.next = curr;
                    lowerTail = curr;
                }
            } else if (curr.val > val) {
                if (upperHead == null) {
                    upperTail = upperHead = curr;
                } else {
                    upperTail.next = curr;
                    upperTail = curr;
                }
            } else {
                if (equalHead == null) {
                    equalTail = equalHead = curr;
                } else {
                    equalTail.next = curr;
                    equalTail = curr;
                }
            }
            curr = post;
        }
        // 三条链表连接起来
        // 注意需要考虑每条链表为空的情况
        if (lowerHead != null) {
            if (equalHead != null) {
                lowerTail.next = equalHead;
            } else {
                // 中间链表为空，跳过
                lowerTail.next = upperHead;
            }
        }
        if (equalHead != null) {
            equalTail.next = upperHead;
        }
        return lowerHead != null ? lowerHead : equalHead != null ? equalHead : upperHead;
    }


    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5};
        ListNode node = new ListNode(360);
        node.next = new ListNode(220);
        node.next.next = new ListNode(2);
        node.next.next.next = new ListNode(1);
        ListNode head = partition(node, 2);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
