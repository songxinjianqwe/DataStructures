package com.sinjinsong.alogrithm.linkedlist;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class LinkedListClone {

    /**
     * 单链表拷贝
     *
     * @param head
     * @return
     */
    public static ListNode clone(ListNode head) {
        ListNode newHead = null;
        ListNode newCurr = null;
        while (head != null) {
            if (newHead == null) {
                newCurr = newHead = new ListNode(head.val);
            } else {
                newCurr.next = new ListNode(head.val);
                newCurr = newCurr.next;
            }
            head = head.next;
        }
        return newHead;
    }

    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }

        static RandomListNode create(int[] arr, int[] randomIndex) {
            RandomListNode head = null, tail = null;
            for (int i = 0; i < arr.length; i++) {
                if (head == null) {
                    tail = head = new RandomListNode(arr[i]);
                } else {
                    tail.next = new RandomListNode(arr[i]);
                    tail = tail.next;
                }
            }
            tail = head;
            int i = 0;
            while (tail != null) {
                if (randomIndex[i] == -1) {
                    tail.random = null;
                } else {
                    tail.random = search(head, arr[randomIndex[i]]);
                }
                tail = tail.next;
                i++;
            }
            return head;
        }

        static RandomListNode search(RandomListNode head, int val) {
            while (head != null) {
                if (head.label == val) {
                    return head;
                }
                head = head.next;
            }
            return null;
        }

        public static void print(RandomListNode head) {
            while (head != null) {
                System.out.println(head);
                head = head.next;
            }
            System.out.println();
        }

        @Override
        public String toString() {
            return "RandomListNode{" +
                    "label=" + label +
                    ", next=" + (next == null ? "null" : next.label) +
                    ", random=" + (random == null ? "null" : random.label) +
                    '}';
        }
    }

    /**
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点）。
     * 1）遍历链表，拷贝每一个节点，接在原节点的后面，新节点的random指针为null
     * 2）重新遍历链表，同时维护两个指针，一个指向原节点，一个指向同值的新节点，拷贝random指针
     * 3）将新旧节点分离开来
     *
     * @param head
     * @return
     */
    public static RandomListNode cloneComplexLinkedList(RandomListNode head) {
        RandomListNode curr = head;
        //1)拷贝
        while (curr != null) {
            RandomListNode newNode = new RandomListNode(curr.label);
            newNode.next = curr.next;
            curr.next = newNode;
            curr = curr.next.next;
        }
        //2)设置random
        curr = head;
        RandomListNode newCurr;
        while (curr != null) {
            newCurr = curr.next;
            // 注意random是有空的情况的
            newCurr.random = curr.random == null ? null : curr.random.next;
            curr = curr.next.next;
        }
        //3)拆开
        curr = head;
        RandomListNode newHead = null;
        newCurr = null;
        while (curr != null) {
            if (newHead == null) {
                newCurr = newHead = curr.next;
            } else {
                newCurr.next = curr.next;
                newCurr = curr.next;
            }
            // skip newCurr
            curr.next = curr.next.next;
            curr = curr.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
//        ListNode listNode = ListNode.create(new int[]{1, 2, 3, 4});
//        ListNode newNode = clone(listNode);
//        ListNode.print(newNode);
        RandomListNode head = RandomListNode.create(new int[]{1, 2, 3, 4, 5}, new int[]{2,4,-1,1,-1});
        RandomListNode.print(head);
        RandomListNode newHead = cloneComplexLinkedList(head);
        RandomListNode.print(head);
        RandomListNode.print(newHead);
    }

}
