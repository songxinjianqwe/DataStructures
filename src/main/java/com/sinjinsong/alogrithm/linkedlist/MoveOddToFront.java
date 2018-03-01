package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class MoveOddToFront {
    //0, 2, 4, 6, 1, 3, 5

    /**
     * 链表分化为两条小链表，再接在一起
     * @param head
     * @return
     */
    public static ListNode moveOddToFrontWithFixedRelativePosition(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode oddHead = null,oddTail = null;
        ListNode evenHead = null,evenTail = null;
        while(head != null) {
            if((head.val & 1) == 1) {
                if(oddHead == null) {
                    oddTail = oddHead = head;
                }else{
                    oddTail.next = head;
                    oddTail = oddTail.next;
                }
            }else{
                if(evenHead == null) {
                    evenTail = evenHead = head;
                }else{
                    evenTail.next = head;
                    evenTail = evenTail.next;
                }
            }
            head = head.next;
        }
        // 合并
        if(oddTail != null) {
            oddTail.next = evenHead;
        }
        if(evenTail != null) {
            evenTail.next = null;
        }
        return oddHead != null ? oddHead : evenHead;
    }

    public static void main(String[] args) {
//        ListNode head = ListNode.create(new int[]{0, 2, 4, 6, 1, 3, 5});
//          ListNode head = ListNode.create(new int[]{0, 1});
//            ListNode head = ListNode.create(new int[]{0});
            ListNode head = ListNode.create(new int[]{0,2,4,6});
        
        ListNode newHead = moveOddToFrontWithFixedRelativePosition(head);
        ListNode.print(newHead);
    }
}
