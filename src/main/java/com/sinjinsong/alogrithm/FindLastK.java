package com.sinjinsong.alogrithm;


//在不改变单链表结构的前提下，尽可能快地找到倒数第k个元素
public class FindLastK<E> {
    private Node head;
    private Node tail;

    // 添加到尾
    public boolean add(E e) {
        if (head == null) {
            head = new Node(e, tail);
            tail = head;
        } else {
            Node newNode = new Node(e, null);
            tail.next = newNode;
            tail = newNode;
        }
        return true;
    }

    public E findLast(int index) {
        Node slow = head, fast = head;
        for (int i = 0; i < index; i++) {
            fast = fast.next;// 先移动index个位置
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }

    class Node {
        E data;
        Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        FindLastK<Integer> list = new FindLastK<>();
        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        System.out.println(list.findLast(2));// 找倒数第2个
    }
}
