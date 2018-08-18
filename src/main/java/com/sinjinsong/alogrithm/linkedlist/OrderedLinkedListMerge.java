package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class OrderedLinkedListMerge {
    public static ListNode merge(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return headA != null ? headA : headB;
        }
        ListNode resultHead = null;
        ListNode resultTail = null;
        while (headA != null && headB != null) {
            if (headA.val <= headB.val) {
                if (resultHead == null) {
                    resultTail = resultHead = headA;
                } else {
                    resultTail.next = headA;
                    resultTail = headA;
                }
                headA = headA.next;
            } else {
                if (resultHead == null) {
                    resultTail = resultHead = headB;
                } else {
                    resultTail.next = headB;
                    resultTail = headB;
                }
                headB = headB.next;
            }
        }

        if (headA != null) {
            resultTail.next = headA;
        }
        if (headB != null) {
            resultTail.next = headB;
        }
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode headA = ListNode.create(new int[]{1,3,5,7});
        ListNode headB = ListNode.create(new int[]{2,4,6,8});
        ListNode listNode = merge(headA, headB);
        ListNode.print(listNode);
    }
}
