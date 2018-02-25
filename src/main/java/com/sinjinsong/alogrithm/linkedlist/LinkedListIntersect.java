package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class LinkedListIntersect {

    /**
     * 无环链表相交：
     * 1）一旦两个链表相交，那么两个链表中的节点一定有相同地址。
     * 2）一旦两个链表相交，那么两个链表从相交节点开始到尾节点一定都是相同的节点。
     * 返回两个链表第一个相交节点
     * 先统计两个链表的长度，比如M和N，假设M > N，则让较长链表先走M-N步，
     * 然后让两个链表同步走，比较是否是同一个节点，如果相同则为第一个相交节点。
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getFirstIntersectNodeOfAcyclicLinkedList(ListNode headA, ListNode headB) {
        return getFirstIntersectNodeBefore(headA, headB, null);
    }

    /**
     * tail可能是null，也可能是两个链表的某个公共节点
     *
     * @param headA
     * @param headB
     * @param tail
     * @return
     */
    public static ListNode getFirstIntersectNodeBefore(ListNode headA, ListNode headB, ListNode tail) {
        ListNode currA = headA;
        int lenA = 1;
        while (currA.next != tail) {
            lenA++;
            currA = currA.next;
        }
        // 此时currA指向第一个链表的tail的前一个节点
        ListNode currB = headB;
        int lenB = 1;
        while (currB.next != tail) {
            lenB++;
            currB = currB.next;
        }

        //一定不相交
        if (currA != currB) {
            return null;
        }
        currA = headA;
        currB = headB;
        if (lenA >= lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                currA = currA.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                currB = currB.next;
            }
        }

        while (currA != tail) {
            if (currA == currB) {
                return currA;
            }
            currA = currA.next;
            currB = currB.next;
        }
        return null;
    }

    /**
     * 有环单链表判交
     * 要求时间复杂度为O(M+N)，空间复杂度为O(1)。
     * 1）分别找到两个链表的第一个入环节点A和B。
     * 2）如果A==B，说明一定相交，如果想找到第一个相交节点，
     * 可以采用同无环单链表判交一样的方法，
     * 只是停止遍历的条件从node为null变为node为A/B。
     * 3）A != B，可以设置一个第一个链表的指针，从A出发遍历，
     * 如果途中没有遇到B，说明不相交，否则相交。相交的话可以将A或B作为第一个相交的节点。
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getFirstIntersectNodeOfCyclicLinkedList(ListNode headA, ListNode headB) {
        ListNode loopStartA = LinkedListLoopStart.getLoopStart(headA);
        ListNode loopStartB = LinkedListLoopStart.getLoopStart(headB);
        // 一定相交，但未必是入环第一个节点
        if (loopStartA == loopStartB) {
            ListNode firstIntersectNode = getFirstIntersectNodeBefore(headA, headB, loopStartA);
            return firstIntersectNode != null ? firstIntersectNode : loopStartA;
        } else {
            // 未必相交，第一个链表指针从A开始遍历，如果回到A时仍然没有遇到B，则无环
            ListNode currA = loopStartA;
            do {
                if (currA == loopStartB) {
                    return loopStartA;
                }
                currA = currA.next;
            } while (currA != loopStartA);
        }
        return null;
    }

    /**
     * 统一几种情况：两个链表可能有环也可能无环
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getFirstIntersectNodeOfLinkedList(ListNode headA, ListNode headB) {
        ListNode loopStartA = LinkedListLoopStart.getLoopStart(headA);
        ListNode loopStartB = LinkedListLoopStart.getLoopStart(headB);
        // 都无环
        if (loopStartA == null && loopStartB == null) {
            return getFirstIntersectNodeOfAcyclicLinkedList(headA, headB);
            // 都有环
        } else if (loopStartA != null && loopStartB != null) {
            return getFirstIntersectNodeOfCyclicLinkedList(headA, headB);
        } else {
            // 一个有环，一个无环，一定不相交
            return null;
        }
    }
}
