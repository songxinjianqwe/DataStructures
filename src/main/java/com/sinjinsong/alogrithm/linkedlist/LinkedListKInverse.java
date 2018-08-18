package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class LinkedListKInverse {

    /**
     * 有一个单链表，请设计一个算法，使得每K个节点之间逆序，
     * 如果最后不够K个节点一组，则不调整最后几个节点。
     * 例如链表1->2->3->4->5->6->7->8->null，K=3这个例子。
     * 调整后为，3->2->1->6->5->4->7->8->null。因为K==3，
     * 所以每三个节点之间逆序，但其中的7，8不调整，因为只有两个节点不够一组。
     * <p>
     * 给定一个单链表的头指针head,同时给定K值，返回逆序后的链表的头指针。
     * 每收集k个元素就进行逆序，还是需要每组之间进行重连。
     * 时间复杂度为O(n)，空间复杂度为O(1)。
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode inverse(ListNode head, int k) {
        ListNode resultHead = null;
        ListNode lastGroupTail = null;
        ListNode nextGroupHead = null;
        ListNode curr = head;
        int i = 0;
        // 如果链表长度为k的整数倍，那么要依赖这个条件退出
        while (curr != null) {
            for (; i < k - 1 && curr.next != null; ++i) {
                curr = curr.next;
            }
            // 够k个
            if (i == k - 1) {
                // 此时curr为新的head，head为tail
                i = 0;
                nextGroupHead = curr.next;
                inverse(head, curr);
            } else {
                // 当前组元素个数不足k，退出
                if (lastGroupTail == null) {
                    resultHead = head;
                } else {
                    lastGroupTail.next = head;
                }
                break;
            }
            // curr 指向头，head指向尾
            if (resultHead == null) {
                resultHead = curr;
            }
            if (lastGroupTail != null) {
                lastGroupTail.next = curr;
            }
            lastGroupTail = head;
            curr = head = nextGroupHead;
        }
        return resultHead;
    }

    private static void inverse(ListNode head, ListNode tail) {
        ListNode pre = null, curr = head, post = head.next;
        while (curr != tail) {
            curr.next = pre;
            pre = curr;
            curr = post;
            post = post.next;
        }
        // curr == tail
        curr.next = pre;
    }


    public static void main(String[] args) {
        int[] arr = {0,4,9,11,12,14,20,24};
        ListNode head = ListNode.create(arr);
        ListNode newHead = inverse(head, 2);
        ListNode.print(newHead);
    }
}
