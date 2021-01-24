package com.sinjinsong.leetcode.medium;

import com.sinjinsong.leetcode.ListNode;

public class LeetCode2 {
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     *
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     *
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * 示例 2：
     *
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * 示例 3：
     *
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     *  
     *
     * 提示：
     *
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode currA = l1;
        ListNode currB = l2;

        ListNode ans = null;
        ListNode ansCurr = null;
        boolean plus = false;
        while(currA != null || currB != null || plus) {
            int valA = 0;
            if(currA != null) {
                valA = currA.val;
                currA = currA.next;
            }
            int valB = 0;
            if(currB != null) {
                valB = currB.val;
                currB = currB.next;
            }

            int val = valA + valB + (plus ? 1 : 0);
            if(val >= 10) {
                plus = true;
                val -= 10;
            } else {
                plus = false;
            }
            ListNode sum = new ListNode(val);
            if(ans == null) {
                ans = ansCurr = sum;
            } else {
                ansCurr.next = sum;
                ansCurr = sum;
            }
        }
        return ans;
    }
}
