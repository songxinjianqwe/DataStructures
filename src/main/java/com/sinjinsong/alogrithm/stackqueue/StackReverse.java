package com.sinjinsong.alogrithm.stackqueue;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/21
 */
public class StackReverse {
    /**
     * 栈逆序
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {
        if (!stack.isEmpty()) {
            int bottom = popBottom(stack);
            reverse(stack);
            stack.push(bottom);
        }
    }

    /**
     * 弹出栈底
     * @param stack
     * @return
     */
    private static int popBottom(Stack<Integer> stack) {
        int data = stack.pop();
        // 不是栈底，则重新压回，并将栈底的结果返回
        if (!stack.isEmpty()) {
            int bottom = popBottom(stack);
            stack.push(data);
            return bottom;
        } else {
            // pop完为空，说明是栈底了
            // 栈底不重新压回栈中
            return data;
        }
    }
    
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}
