package com.sinjinsong.alogrithm.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class UnOrderedLinkedListRemoveDuplicateNodes {
    /**
     * 时间复杂度为O(n^2)
     * @param head
     * @return
     */
    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode outer = head;
        // 外层循环
        while (outer != null) {
            ListNode inner = outer;
            while (inner != null) {
                // inner 下一个节点值等于outer值，则删除inner的下一个节点
                if (inner.next != null && inner.next.val == outer.val) {
                    // 删除后inner不移动，否则出现连续重复时无法删掉子集
                    inner.next = inner.next.next;
                } else {
                    inner = inner.next;
                }
            }
            outer = outer.next;
        }
        return head;
    }

    /**
     * 时间复杂度为O(n)
     * @param head
     * @return
     */
    public static ListNode removeDuplicateNodesByHashMap(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode curr = head,pre = null;
        Set<Integer> map = new HashSet<>();
        while(curr != null) {
            if(map.contains(curr.val)) {
                // 删除curr
                pre.next = curr.next;
                curr = curr.next;
            }else{
                map.add(curr.val);
                pre = curr;
                curr = curr.next;
            }
        }
        return head;
    }
    
    public static void main(String[] args) {
        ListNode head = ListNode.create(new int[]{6, 3, 4, 4, 4, 1, 3, 7, 2, 6});
//        ListNode newHead = removeDuplicateNodes(head);
          ListNode newHead = removeDuplicateNodesByHashMap(head);
        
        ListNode.print(newHead);
    }

}
