package com.sinjinsong.alogrithm.stackqueue;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/21
 * 要求时间复杂度为O(1)
 * 使用两个栈，一个栈是正常的栈stackData，另一个栈是stackMin。当向stackData压入数据时，如果小于stackMIn的栈顶，那么同时压入stackMin。
 * 弹出时先弹出stackData的数据，然后比较数据与stackMIn的栈顶是否相同，相同的话stackMIn也弹出栈顶。
 * stackMin中始终保存着stackData中的最小值，并且始终保持与stackData中的数据一致。
 */
public class QueryMinStack {
    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        // 第一种
        if (minStack.isEmpty() || node < minStack.peek()) {
            minStack.push(node);
        }
    }

    public int pop() {
        if (dataStack.isEmpty()) {
            throw new IllegalStateException("空");
        }
        int data = dataStack.pop();
        if (data == minStack.peek()) {
            minStack.pop();
        }
        return data;
    }

    public int top() {
        if (dataStack.isEmpty()) {
            throw new IllegalStateException("空");
        }
        return dataStack.peek();
    }

    public int min() {
        if (minStack.isEmpty()) {
            throw new IllegalStateException("空");
        }
        return minStack.peek();
    }

}
