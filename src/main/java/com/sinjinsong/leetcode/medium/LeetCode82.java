package com.sinjinsong.leetcode.medium;

import com.sinjinsong.leetcode.ListNode;

public class LeetCode82 {
    /**
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->3->4->4->5
     * 输出: 1->2->5
     * 示例 2:
     * <p>
     * 输入: 1->1->1->2->3
     * 输出: 2->3
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
//    public ListNode deleteDuplicates(ListNode head) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//        ListNode pre = null;
//        ListNode curr = head;
//        ListNode post = head.next;
//        while (post != null) {
//            boolean same = false;
//            while (post != null && curr.val == post.val) {
//                same = true;
//                post = post.next;
//            }
//            if(same) {
//                if (pre != null) {
//                    pre.next = post;
//                }
//                if(curr == head) {
//                    head = post;
//                }
//                curr = post;
//                if(post != null) {
//                    post = post.next;
//                }
//            } else {
//                pre = curr;
//                curr = curr.next;
//                post = curr.next;
//            }
//        }
//        return head;
//    }
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        if(head.val == head.next.val) {
            while(head != null && head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            return deleteDuplicates(head.next);
        } else {
            head.next = deleteDuplicates(head.next);
            return head;
        }
    }
}
