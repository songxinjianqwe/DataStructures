package com.sinjinsong.datastructure.list;

import java.util.NoSuchElementException;

/**
 * 双向链表
 *
 * @param <E>
 * @author New Song
 */
public class MyDoubleLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    // 添加到尾
    public boolean add(E e) {
        if (isEmpty()) {
            // 只有一个结点时它既是头又是尾
            head = tail = new Node<>(e, null, tail);
        } else {
            Node<E> newNode = new Node<>(e, tail, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
        return true;
    }

    private void checkBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("链表元素共" + size + "个，所要访问的元素下标为" + index);
        }
    }

    private void checkEmpty() {
        if (head == null) {
            throw new NoSuchElementException("链表为空");
        }
    }

    private Node<E> node(int index) {
        Node<E> node;
        int i = 0;
        if (index <= size / 2) {
            node = head;
            while (node != null && i < index) {
                node = node.next;
                i++;
            } // node 指向该位置的元素
        } else {
            node = tail;
            i = size - 1;
            while (node != null && i > index) {
                node = node.prev;
                i--;
            }
        }
        return node;
    }

    // 插入到该位置，该位置原有的元素后移
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("链表元素共" + size + "个，所要访问的元素下标为" + index);
        } else if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> node = node(index);
            // 此时node指向index处的元素，即要插入元素的后一个元素
            Node<E> newNode = new Node<>(e, node.prev, node);
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            // 如果链表为空，那么表尾也为第一个结点（参见add方法）
            head = tail = new Node<>(e, null, null);
        } else {
            Node<E> newNode = new Node<>(e, null, head);
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        add(e);
    }

    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(E e) {
        Node<E> node = head;
        while (node != null) {
            if (node.data.equals(e)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    // 返回头
    public E element() {
        checkEmpty();
        return head.data;
    }

    public E get(int index) {
        checkBounds(index);
        return node(index).data;
    }

    public E getFirst() {
        return element();
    }

    public E getLast() {
        checkEmpty();
        return tail.data;
    }

    public int indexOf(E e) {
        Node<E> node = head;
        int i = 0;
        while (node != null) {
            if (node.data.equals(e)) {
                return i;
            }
            node = node.next;
            i++;
        }
        return -1;
    }

    public int lastIndexOf(E e) {
        int i = size;
        for (Node<E> node = tail; node != null; node = node.prev) {
            i--;
            if (node.data.equals(e)) {
                return i;
            }

        }
        return -1;
    }

    // 移除头结点
    public E remove() {
        checkEmpty();
        Node<E> node = head.next;// 用来保存头结点的下一个结点
        E e = head.data;
        if (node == null) { // 如果只有一个头结点，那么头结点设置为空；
            // 如果有多个结点，那么下一个结点的前驱为空，释放头结点，头结点指向下一个结点
            head = tail = null;
        } else {
            node.prev = null;
            head = node;
        }
        size--;
        return e;
    }

    public E remove(int index) {
        checkBounds(index);
        if (index == 0) {
            return remove();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> node = node(index);
            E e = node.data;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
            return e;
        }
    }

    public boolean remove(Object e) {
        checkEmpty();
        if (head.data.equals(e)) {
            removeFirst();
            return true;
        } else if (tail.data.equals(e)) {
            removeLast();
            return true;
        }
        for (Node<E> node = head.next; node != tail; node = node.next) {
            if (node.data.equals(e)) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
        }
        return false;
    }

    public E removeLast() {
        checkEmpty(); // 首先检查链表是否为空
        E e = tail.data;
        if (tail.prev != null) { // 如果多于一个结点
            tail.prev.next = null;
            tail = tail.prev;
        } else { // 如果只有一个结点
            tail = head = null;
        }
        size--;
        return e;
    }

    public E removeFirst() {
        return remove();
    }

    public int size() {
        return size;
    }

    public void traverse() {
        Node<E> node = head;
        System.out.print("[");
        while (node != null) {
            System.out.print(node.data + (node == tail ? "" : ","));
            node = node.next;
        }
        System.out.println("]");
    }

    public void traverseReverse() {// 逆序遍历
        Node<E> node = tail;
        System.out.print("[");
        while (node != null) {
            System.out.print(node.data + (node == head ? "" : ","));
            node = node.prev;
        }
        System.out.println("]");
    }

    private static class Node<E> {
        E data;
        Node<E> prev;
        Node<E> next;

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

    }

    public static void main(String[] args) {
        MyDoubleLinkedList<Integer> list = new MyDoubleLinkedList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.addFirst(i + 1);
        }
        list.traverse();
        list.remove(2);
        list.traverseReverse();
        System.out.println(list.size);
        list.traverse();
        list.remove();
        list.traverseReverse();

        list.traverse();
        list.add(3, 100);
        list.traverse();
        list.addFirst(200);
        list.traverse();
        list.addLast(300);
        list.traverse();

        list.remove(0);
        list.traverse();
        list.removeFirst();
        list.traverse();
        System.out.println(list.remove());
        list.traverse(); // list.clear();
        System.out.println(list.size);
        list.traverse();
        System.out.println(list.contains(21)); // list.clear();
        System.out.println(list.element());
        System.out.println(list.size);
//        System.out.println(list.get(19));
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.indexOf(23));
        System.out.println(list.indexOf(12));
        list.traverse();
//        System.out.println(list.remove(19));
        list.traverse();
        System.out.println(list.remove());
        list.traverse();
        list.remove(0);
        list.traverse();
        list.removeFirst();
        list.traverse();
        list.removeLast();
        list.traverse();
    }
}
