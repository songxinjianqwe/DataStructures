package com.sinjinsong.alogrithm.leetcode.easy;

import com.sinjinsong.alogrithm.leetcode.ListNode;

public class LeetCode21 {
    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     * 示例 2：
     *
     * 输入：l1 = [], l2 = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：l1 = [], l2 = [0]
     * 输出：[0]
     *  
     *
     * 提示：
     *
     * 两个链表的节点数目范围是 [0, 50]
     * -100 <= Node.val <= 100
     * l1 和 l2 均按 非递减顺序 排列
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode curr = null;
        if (l2 == null) {
            return l1;
        }
        if (l1 == null) {
            return l2;
        }
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                if (head == null) {
                    head = curr = l1;
                } else {
                    curr.next = l1;
                    curr = l1;
                }
                l1 = l1.next;
            } else {
                if (head == null) {
                    head = curr = l2;
                } else {
                    curr.next = l2;
                    curr = l2;
                }
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return head;
    }
}
