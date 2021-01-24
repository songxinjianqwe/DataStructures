package com.sinjinsong.leetcode.easy;

import com.sinjinsong.leetcode.ListNode;

public class LeetCode24 {
    /**
     * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：head = [1,2,3,4]
     * 输出：[2,1,4,3]
     * 示例 2：
     *
     * 输入：head = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：head = [1]
     * 输出：[1]
     *  
     *
     * 提示：
     *
     * 链表中节点的数目在范围 [0, 100] 内
     * 0 <= Node.val <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode post = head.next, curr = head, pre = null;
        while(post != null) {
            if(head == curr) {
                head = post;
            }
            if(pre != null) {
                pre.next = post;
            }
            curr.next = post.next;
            post.next = curr;
            if(curr.next == null || curr.next.next == null) {
                break;
            } else {
                pre = curr;
                curr = curr.next;
                post = curr.next;
            }
        }
        return head;
    }
}
