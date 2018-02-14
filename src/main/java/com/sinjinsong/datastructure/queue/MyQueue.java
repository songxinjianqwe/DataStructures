package com.sinjinsong.datastructure.queue;

/**
 * 数组实现循环队列，没有空余的元素，全部是有效元素
 * 判断空满使用elements成员变量来指示
 *
 * @param <E>
 * @author New Song
 */
public class MyQueue<E> {
    private Object[] value;
    private int front;// 指向头结点
    private int rear;// 指向尾结点的下一个结点
    private int elements;// 有效元素个数

    public MyQueue(int length) {
        if (length <= 0) {
            throw new IndexOutOfBoundsException("长度必须大于0");
        }
        value = new Object[length];
    }

    //入队
    public void enQueue(E e) {
        if (isFull()) {
            return;
        }
        value[rear] = e;
        rear = (rear + 1) % value.length;
        elements++;
    }


    //出队
    public E deQueue() {
        if (isEmpty())
            return null;
        Object obj = value[front];
        value[front] = null;
        front = (front + 1) % value.length;
        elements--;
        return (E) obj;
    }

    public E peek() {
        if (isEmpty())
            return null;
        return (E) value[front];
    }

    public boolean isEmpty() {
        // 一律使用elements来判断是否空满，不再使用rear和front之间的关系来判断
        return elements == 0;
    }

    public boolean isFull() {
        return elements == value.length;
    }

    public int size() {
        return elements;
    }

    public void traverse() {
        System.out.print("[");
        int j = front;
        for (int i = 0; i < elements; i++) { // 遍历次数用elements来决定
            System.out.print(value[j] + (i + 1 == elements ? "" : ","));
            j = (j + 1) % value.length;
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>(10);
        for (int i = 0; i < 20; i++) {
            queue.enQueue(i + 1);
        }
//		System.out.println(queue.isFull());
//		System.out.println(queue.size());
        // queue.destroy();
        queue.traverse();
        System.out.println(queue.peek());
    }
}
