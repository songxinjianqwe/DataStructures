package com.sinjinsong.alogrithm.stackqueue;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/21
 */
public class TwoStackQueue {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void offer(int node) {
        pushStack.push(node);
    }

    /**
     * 如果popStack为空的话，将pushStack倒入popStack，否则就等popStack
     * 全部弹出后再倒入，避免顺序混乱
     * @return
     */
    public int poll() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }

    public int peek() {
        if (popStack.isEmpty()) {
            throw new IllegalStateException("空");
        }
        return popStack.peek();
    }
}
