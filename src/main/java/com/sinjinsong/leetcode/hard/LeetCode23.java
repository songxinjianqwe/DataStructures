package com.sinjinsong.leetcode.hard;

import com.sinjinsong.leetcode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode23 {
    public ListNode mergeKLists(ListNode[] lists) {
        /**
         * 给你一个链表数组，每个链表都已经按升序排列。
         *
         * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
         *
         *  
         *
         * 示例 1：
         *
         * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
         * 输出：[1,1,2,3,4,4,5,6]
         * 解释：链表数组如下：
         * [
         *   1->4->5,
         *   1->3->4,
         *   2->6
         * ]
         * 将它们合并到一个有序链表中得到。
         * 1->1->2->3->4->4->5->6
         * 示例 2：
         *
         * 输入：lists = []
         * 输出：[]
         * 示例 3：
         *
         * 输入：lists = [[]]
         * 输出：[]
         *  
         *
         * 提示：
         *
         * k == lists.length
         * 0 <= k <= 10^4
         * 0 <= lists[i].length <= 500
         * -10^4 <= lists[i][j] <= 10^4
         * lists[i] 按 升序 排列
         * lists[i].length 的总和不超过 10^4
         *
         * 来源：力扣（LeetCode）
         * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
         * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
         */
        if(lists.length == 0) {
            return null;
        }
        Queue<ListNode> q = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });
        ListNode head = null, curr = null;
        for (ListNode list : lists) {
            if(list != null) {
                q.add(list);
            }
        }
        while(!q.isEmpty()) {
            ListNode node = q.poll();
            if(head == null) {
                head = curr = node;
            } else {
                curr.next = node;
                curr = node;
            }
            if(node.next != null) {
                q.add(node.next);
            }
        }
        return head;
    }
}
