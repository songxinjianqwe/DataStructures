package com.sinjinsong.alogrithm.tree;

import com.sinjinsong.alogrithm.linkedlist.ListNode;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class OrderedLinkedList2BST {
    /**
     * 有序链表转BST
     * @param head
     * @return
     */
    public static TreeNode fromLinkedList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head, fast = head,pre = null;
        while (fast.next != null && fast.next.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        // 希望将链表拆为三部分：左子树，根，右子树
        // 假如链表元素个数为奇数，那么根就是slow,右子树是从slow.next开始的
        if(pre != null) {
            pre.next = null;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = fromLinkedList(slow == head ? null : head);
        root.right = fromLinkedList(slow.next);
        return root;
    }
    
    public static void main(String[] args) {
//        ListNode listNode = ListNode.create(new int[]{1, 2, 3, 4, 5});
          ListNode listNode = ListNode.create(new int[]{1});
        TreeNode root = fromLinkedList(listNode);
        TreeNode.preOrder(root);
    }
}
