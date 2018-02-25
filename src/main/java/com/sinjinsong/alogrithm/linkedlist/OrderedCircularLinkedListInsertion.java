package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class OrderedCircularLinkedListInsertion {


    /**
     * arr是有序数组，next是A的每个元素的后继下标
     * val是待插入的元素
     * 1.链表为空，直接插在头部，并作为head返回。
     * 2.插入元素小于head的元素，插于头部之前。必须先遍历到最后一个结点，再插入，并修改头部，然后返回！
     * 3.插入元素在中间或者在尾部，在中间就是普通插入方法，
     * 在尾部意思就是它是所有元素最大的元素，插入尾部后让它的next指向head，
     * 但是，不更新head！这就是和2情况的不同，所以2和3情况不能完全合并！必须在情况2中更新head！
     *
     * @param arr
     * @param next
     * @param val
     * @return
     */
    public static ListNode insert(int[] arr, int[] next, int val) {
        ListNode head = buildCircuitLinkedList(arr, next);
        if (head == null) {
            head = new ListNode(val);
            head.next = head;
        } else if (val <= head.val) {
            // 插头
            ListNode last = head;
            while (last.next != head) {
                last = last.next;
            }
            // node 此时指向最后一个节点
            ListNode newHead = new ListNode(val);
            newHead.next = head;
            last.next = newHead;
            head = newHead;

        } else {
            // 插入中间或者尾部
            ListNode curr = head; // 当前节点
            ListNode post = head.next;// 下一个节点
            while (post != head && post.val < val) {
                curr = post;
                post = post.next;
            }
            // 此时post.val >= val
            // val将被插到curr后面，post前面  curr.val < val < post.val
            // 也有可能是插入到尾部，post为head，curr为尾节点
            ListNode newNode = new ListNode(val);
            newNode.next = post;
            curr.next = newNode;
        }
        return head;
    }

    private static ListNode buildCircuitLinkedList(int[] arr, int[] next) {
        if (arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode tail = head;
        // 最后一个不用创建，一定是head
        for (int i = next[0]; i != 0; i = next[i]) {
            ListNode newNode = new ListNode(arr[i]);
            tail.next = newNode;
            tail = newNode;
        }
        tail.next = head;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {4, 8, 9};
        int[] next = {1, 2, 0};
//        ListNode head = insert(arr, next, 4);
//          ListNode head = insert(arr, next, 7);
//        ListNode head = insert(arr, next, 10);
          ListNode head = insert(arr, next, 9);
        ListNode node = head;
        do {
            System.out.println(node.val);
            node = node.next;
        } while (node != head);

    }
}
