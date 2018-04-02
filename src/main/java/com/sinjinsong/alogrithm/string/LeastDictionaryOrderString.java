package com.sinjinsong.alogrithm.string;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/4/1
 */
public class LeastDictionaryOrderString {
    public static String leastDicOrder(String str) {
        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        int index = 0;
        while (index < str.length()) {
            while (!stack.isEmpty() && stack.peek() > chars[index]) {
                stack.pop();
            }
            stack.push(chars[index++]);
        }
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result);
    }

    public static void main(String[] args) {
        // abbcc
        System.out.println(leastDicOrder("babbdcc"));
    }
}
