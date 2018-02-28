package com.sinjinsong.alogrithm.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class InversePrint {
    /**
     * 逆序打印链表
     * 时间复杂度为O(n)，空间复杂度为O(n)
     * @param head
     * @return
     */
    public static List<Integer> printInversely(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while(head != null) {
            stack.push(head.val);
            head = head.next;
        }
        List<Integer> result = new ArrayList<>();
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    } 
    
}
