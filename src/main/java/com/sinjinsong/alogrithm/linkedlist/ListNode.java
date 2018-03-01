package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class ListNode {
    public int val;
    public ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + (next == null ? "null" : next.val) +
                '}';
    }

    public static ListNode create(int [] arr) {
        ListNode head = null;
        ListNode tail = null;
        for (int i = 0; i < arr.length; i++) {
            if(head == null) {
                tail = head = new ListNode(arr[i]);
            }else{
                tail.next = new ListNode(arr[i]);
                tail = tail.next;
            }
        }
        return head;
    }
    
    public static void print(ListNode head) {
        while(head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}
