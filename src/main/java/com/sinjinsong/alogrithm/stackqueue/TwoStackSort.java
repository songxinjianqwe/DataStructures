package com.sinjinsong.alogrithm.stackqueue;

import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class TwoStackSort {
    /**
     * 将栈中的元素排序为从栈顶到栈底从大到小，只允许使用一个辅助栈。
     * 栈记为stack，辅助栈记为helpStack。
     * stack弹出栈顶元素，如果该元素小于等于helpStack的栈顶，则压入helpStack；否则helpStack不断出栈，压入stack，直至该元素小于等于helpStack的栈顶，然后将该元素压入helpStack。
     * 重复上述过程，直至stack为空。
     * 然后将helpStack全部出栈，压回到stack。
     *
     * @param stack
     */
    public static void sort(Stack<Integer> stack) {
        Stack<Integer> helpStack = new Stack<>();
        int element;
        while (!stack.isEmpty()) {
            element = stack.pop();
            // 仅将小于element的压回stack
            while (!helpStack.isEmpty() && element > helpStack.peek()) {
                stack.push(helpStack.pop());
            }
            helpStack.push(element);
        }
        while (!helpStack.isEmpty()) {
            stack.push(helpStack.pop());
        }
    }

    public static void main(String[] args) {
        int[] arr = {
                54695, 46580, 6418, 52304, 5595, 5149, 51943, 11454, 23596, 6444, 61037, 94146, 50220, 98642, 97292, 57898, 11745, 7286, 31224, 5160, 41550, 25277, 59350, 53353, 68663, 9642, 30406, 5396, 3222, 67194, 7124, 54247, 15077, 97688, 36939, 62888, 80307, 65467, 6882, 97071, 39652, 38268, 88226, 89088, 92198, 39003, 9858, 73803, 83078, 24648, 49891, 34551, 57649, 24443, 30685, 68740, 55407, 53155, 87465, 89282, 41856, 96218, 37292, 24551, 67663, 31715, 46363, 25573, 61921, 56333, 69576, 55919, 19818, 26409, 21590, 70392, 67648, 36909, 89175, 74443, 41856, 11755, 24788, 25975, 25116, 57360, 80998, 62093, 40691, 91189, 29337, 68914, 57653, 64272, 53653, 5975, 27967, 59600, 25803, 13937, 93725, 26457, 16603, 18360, 79926, 63243, 94958, 45131
        };
        Stack<Integer> stack = new Stack<>();
        for (int num : arr) {
            stack.push(num);
        }
        sort(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
