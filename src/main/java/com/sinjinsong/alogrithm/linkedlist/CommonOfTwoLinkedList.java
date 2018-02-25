package com.sinjinsong.alogrithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class CommonOfTwoLinkedList {


    public static int[] printCommon(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return new int[0];
        }
        List<Integer> common = new ArrayList<>();
        while (headA != null && headB != null) {
            if (headA.val < headB.val) {
                headA = headA.next;
            } else if (headA.val > headB.val) {
                headB = headB.next;
            } else {
                // equal
                common.add(headA.val);
                headA = headA.next;
                headB = headB.next;
            }
        }
        return common.stream().mapToInt(Integer::intValue).toArray();
    }
}
