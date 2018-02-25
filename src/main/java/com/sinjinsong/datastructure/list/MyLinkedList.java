package com.sinjinsong.datastructure.list;

import java.util.NoSuchElementException;

/**
 * 单向链表，不带有头结点，全部是有效结点
 *
 * @param <E>
 * @author New Song
 */
public class MyLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 添加到尾
    public boolean add(E e) {
        if (isEmpty()) {
            head = tail = new Node(e, null);
        } else {
            Node newNode = new Node(e, null);
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

    // 插入元素的合理位置下表是从0~表长.对于0和表长要单独处理
    public void add(int index, E e) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("插入元素的位置不合法:" + index);
        } else if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> node = head;
            int i = 0;
            while (node != null && i < index - 1) {
                node = node.next;
                i++;
            }
            // 此时i == index-1，node指向该位置的前一个元素
            Node<E> newNode = new Node<>(e, node.next);
            node.next = newNode;
            size++;
        }
    }

    public void addBefore(E e, Node<E> node) {
        if (node == head) {
            addFirst(e);
        } else {
            Node<E> newNode = new Node<>(node.data, node.next);
            node.data = e;
            node.next = newNode;
            size++;
        }
    }

    public void addAfter(E e, Node<E> node) {
        if (node == tail) {
            addLast(e);
        } else {
            Node<E> newNode = new Node(e, node.next);
            node.next = newNode;
            size++;
        }
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            head = tail = new Node(e, null);
        } else {
            Node<E> newNode = new Node<>(e, head);
            head = newNode;
        }
        size++;
    }

    public void addLast(E e) {
        add(e);
    }

    public void clear() {
        Node curr = head;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = null;
            curr = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    public boolean contains(E e) {
        Node node = head;
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
        // 保证index所指的元素在链表合理范围内
        Node<E> node = head;
        int i = 0;
        while (node != null && i < index) {
            node = node.next;
            i++;
        }
        return node.data;
    }

    public E getFirst() {
        return element();
    }

    public E getLast() {
        checkEmpty();
        return tail.data;
    }

    public int indexOf(E e) {
        int i = 0;
        for (Node<E> node = head; node != null; node = node.next) {
            if (node.data.equals(e)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // 移除头结点
    public E removeFirst() {
        checkEmpty();
        Node<E> node = head.next;// 用来保存头结点的下一个结点
        E e = head.data;
        if (node == null) {
            head = tail = null;
        } else {
            head = node;
        }
        size--;
        return e;
    }

    // 删除的合理位置下表为0~表长-1，0和表长-1要单独处理
    public E remove(int index) {
        checkBounds(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> node = head;
            int i = 0;
            while (node != null && i < index - 1) {
                node = node.next;
                i++;
            }
            // node指向index-1，即所要删除的元素的前一个元素
            Node<E> target = node.next;
            E e = target.data;
            // deleted
            node.next = target.next;
            size--;
            return e;
        }
    }

    public E removeLast() {
        checkEmpty();
        Node<E> node = head;
        while (node.next != tail) {
            node = node.next;
        }
        E e = tail.data;
        node.next = null;
        tail = node;
        size--;
        return e;
    }

    public boolean remove(Object e) {
        checkEmpty();
        if(head.data.equals(e)) {
            removeFirst();
            return true;
        } else if(tail.data.equals(e)) {
            removeLast();
            return true;
        }
        for(Node<E> node = head.next ; node != tail ; node = node.next) {
            if(node.data.equals(e)) {
                node.data = node.next.data;
                node.next = node.next.next;
                size--;
                return true;
            }
        }
        return false;    
    }
    
    
    public E remove(Node<E> node) {
        E e = node.data;
        if (node == head) {
            return removeFirst();
        } else if (node == tail) {
            return removeLast();
        } else {
            // deleted
            // node.next 一定不为空
            node.data = node.next.data;
            node.next = node.next.next;
            size--;
        }
        return e;
    }


    public int size() {
        return size;
    }

    public void traverse() {
        Node node = head;
        System.out.print("[");
        while (node != null) {
            System.out.print(node.data + (node == tail ? "" : ","));
            node = node.next;
        }
        System.out.println("]");
    }

    /**
     * 单链表翻转
     * 三个指针：pre，curr，post
     * 0)进入循环前 
     *       1 -> 2 ->  3  -> 4  ->  5
     * pre curr post
     * 
     * 1) 第一次循环，仅将头节点next置为null
     * 1 -> 2  ->   3  -> 4  -> 5
     * pre  curr   post
     * 2) 
     * 1 <-  2  ->  3    ->  4  -> 5
     *      pre    curr   post
     * 3) 
     * 1 <-  2  <-  3  ->   4  ->   5
     *             pre     curr   post 
     * 4)
     * 1 <-  2  <-  3  <-  4  ->   5
     *                    pre     curr   post 
     * 5) 循环结束
     * 1 <-  2  <-  3  <-  4  <-  5
     *                    pre    curr   post 
     */
    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }
        Node<E> backupHead = head;
        Node<E> pre = null;
        Node<E> curr = head;
        Node<E> post = curr.next;
        while (post != null) {
            // 翻转！
            curr.next = pre;
            // 前进！
            pre  = curr;
            curr = post;
            post = post.next;
        }
        // 最后一次翻转！
        curr.next = pre;
        // 赋值成员变量
        head = curr;
        tail = backupHead;
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<Integer>();
        for (int i = 0; i < 20; i++) {
            list.add(i + 1);
        }

        list.traverse();
        list.remove(Integer.valueOf(1));
        list.remove(Integer.valueOf(2));
        
        list.remove(Integer.valueOf(20));
        list.remove(Integer.valueOf(19));
        
        list.remove(Integer.valueOf(9));
        list.traverse();
//        list.inverse();
//        list.traverse();
//        list.add(3, 100);
//        list.traverse();
//        list.addFirst(200);
//        list.traverse();
//        list.addLast(300);
//        list.traverse();
//
//        // list.clear(); System.out.println(list.size); list.traverse();
//
//        System.out.println(list.contains(21));
//        // list.clear();
//
//        System.out.println(list.element());
//        System.out.println(list.get(19));
//        System.out.println(list.getFirst());
//        System.out.println(list.getLast());
//
//        System.out.println(list.simpleMatch(23));
//        System.out.println(list.simpleMatch(12));
//        System.out.println(list.remove(19));
//        list.traverse();
//        System.out.println(list.removeFirst());
//        list.traverse();
//
//        list.remove(0);
//        list.traverse();
//        list.removeFirst();
//        list.traverse();
//        list.removeLast();
//        list.traverse();
    }
}
