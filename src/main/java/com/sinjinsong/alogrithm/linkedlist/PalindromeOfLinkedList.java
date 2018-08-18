package com.sinjinsong.alogrithm.linkedlist;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class PalindromeOfLinkedList {
    /**
     * 方法二：时间复杂度为O(n)，空间复杂度为O(n)）（n/2个）
     * 1）快慢指针遍历，得到中间节点指针（慢指针），将慢指针指向的元素压栈（中间节点不压栈）
     * 2）从中间节点指针开始遍历，比较每一个当前节点与出栈节点是否相同
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeByHalfStack(ListNode head) {
        ListNode fast = head, slow = head;
        // 处理空链表和只有一个节点的链表
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        // stack保存了左半部分的节点
        Stack<Integer> stack = new Stack<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果是奇数个元素(fast.next == null)，那么slow从右半部分开始
        // 如果是偶数个元素(fast == null)，那么slow从中间开始
        if (fast != null) {
            slow = slow.next;
        }
        while (slow != null) {
            if (slow.val != stack.pop()) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    /**
     * 方法三：时间复杂度为O(n)，空间复杂度为O(1)
     * 1）快慢指针遍历，得到中间节点指针（慢指针），将右半部分做逆序
     * 2）链表头尾指针分别向中间移动，比较节点值是否一致
     * 3）将链表调整回来
     * @param head
     * @return
     */
    public static boolean isPalindromeByInversion(ListNode head) {
        ListNode fast = head, slow = head;
        // 处理空链表和只有一个节点的链表
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果是奇数个元素(fast.next == null)，那么slow从右半部分开始
        // 如果是偶数个元素，那么slow从中间偏右开始
        if (fast != null) {
            slow = slow.next;
        }
        // 注意，逆序后fast未必是右半部分的头节点，因为可能为null，以inverse返回的为准
        ListNode rightHead = inverse(slow);
        ListNode rightCurr = rightHead;
        ListNode leftCurr = head;
        
        while(rightCurr != null) {
            if(leftCurr.val != rightCurr.val) {
                return false;
            }
            leftCurr = leftCurr.next;
            rightCurr = rightCurr.next;
        }
        inverse(rightHead);
        return true;
    }

    private static ListNode inverse(ListNode head) {
        ListNode pre = null, curr = head, post = head.next;
        while (post != null) {
            curr.next = pre;
            pre = curr;
            curr = post;
            post = post.next;
        }
        curr.next = pre;
        return curr;
    }


    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{1, 2, 2, 1});
//        ListNode head = ListNode.create(new int[]{1, 2, 3, 2, 1});
        System.out.println(isPalindromeByInversion(head));
        ListNode.print(head);

    }
}
