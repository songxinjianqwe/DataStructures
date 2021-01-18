package com.sinjinsong.alogrithm.leetcode.medium;

import com.sinjinsong.alogrithm.leetcode.ListNode;

public class LeetCode148 {
    /**
     * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
     *
     * 进阶：
     *
     * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
     *  
     *
     * 示例 1：
     *
     *
     * 输入：head = [4,2,1,3]
     * 输出：[1,2,3,4]
     * 示例 2：
     *
     *
     * 输入：head = [-1,5,3,4,0]
     * 输出：[-1,0,3,4,5]
     * 示例 3：
     *
     * 输入：head = []
     * 输出：[]
     *  
     *
     * 提示：
     *
     * 链表中节点的数目在范围 [0, 5 * 104] 内
     * -105 <= Node.val <= 105
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // fast初始值为head.next时，slow结束时时中间或者前半部分
        // fast初始值为head式，slow结束时时中间或者后半部分
        ListNode slow = head, fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode l1 = head,  l2 = slow.next;
        slow.next = null;
        l1 = sortList(l1);
        l2 = sortList(l2);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode resHead = null, resCurr = null;
        while(l1 != null && l2 != null) {
            if(l1.val <= l2.val) {
                if(resHead == null) {
                    resHead = resCurr = l1;
                } else {
                    resCurr.next = l1;
                    resCurr = l1;
                }
                l1 = l1.next;
            } else {
                if(resHead == null) {
                    resHead = resCurr = l2;
                } else {
                    resCurr.next = l2;
                    resCurr = l2;
                }
                l2 = l2.next;
            }
        }
        if(l1 != null) {
            resCurr.next = l1;
        }
        if(l2 != null) {
            resCurr.next = l2;
        }
        return resHead;
    }
}
