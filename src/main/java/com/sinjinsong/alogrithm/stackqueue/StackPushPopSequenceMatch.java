package com.sinjinsong.alogrithm.stackqueue;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class StackPushPopSequenceMatch {

    /**
     * [1,2,3,4,5]是压入序列，那么[4,5,3,2,1]是它的一个弹出序列，但[4,3,5,1,2]不是
     *
     * @param pushSequence
     * @param popSequence
     * @return
     */
    public static boolean isMatch(int[] pushSequence, int[] popSequence) {
        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        for (int popIndex = 0; popIndex < popSequence.length; popIndex++) {
            while (stack.isEmpty() || popSequence[popIndex] != stack.peek()) {
                if (pushIndex == pushSequence.length) {
                    return false;
                }
                stack.push(pushSequence[pushIndex++]);
            }
            stack.pop();
        }
        return true;
    }

    public static void main(String[] args) {
        int[] pushSeq = {1,2,3,4,5};
//        int[] popSeq = {4,5,3,2,1};
        int[] popSeq = {4,3,5,1,2};
        System.out.println(isMatch(pushSeq,popSeq));
    }
}
