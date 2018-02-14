package com.sinjinsong.datastructure.stack;

/**
 * 用数组实现一个栈
 *
 * @param <E>
 * @author New Song
 */
public class MyStack<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_INCREMENT = 5;
    private Object[] value;
    private int size;

    public MyStack(int length) {
        value = new Object[length];
    }

    public MyStack() {
        this(DEFAULT_CAPACITY);
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("栈空");
        }
    }

    public int size() {
        return size;
    }

    public void push(E e) {
        if (size() == value.length) {
            Object[] dest = new Object[value.length + DEFAULT_INCREMENT];
            System.arraycopy(value, 0, dest, 0, size);
            value = dest;
        }
        value[size++] = e;
    }

    public E pop() {
        checkEmpty();
        Object obj = value[--size];
        return (E) obj;
    }

    public E peek() {
        checkEmpty();
        return (E) value[size - 1];
    }

    // 从栈底遍历到栈顶
    public void print() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(value[i] + (i == size ? "]\n" : ","));
        }
    }

    public String traverse() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(value[i]);
            if (i < size - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 将栈中的数值清空，但栈的结构不变
    public void clear() {
        for (int i = 0; i < size; i++) {
            value[i] = null;
        }
        size = -1;
    }

    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 30; i++) {
            stack.push(i + 1);
        }
        System.out.println(stack.peek());
        System.out.println(stack.traverse());
        for (int i = 20; i >= 0; i--) {
            stack.push(i);
        }
        System.out.println(stack.peek());
        System.out.println(stack.traverse());
        for (int i = 0; i < 50; i++) {
            System.out.print(stack.pop() + ",");
        }
        System.out.println();
        System.out.println(stack.traverse());
        System.out.println(stack.size());
    }
}
