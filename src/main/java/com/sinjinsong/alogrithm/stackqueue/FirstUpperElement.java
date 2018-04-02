package com.sinjinsong.alogrithm.stackqueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/4/1
 */
public class FirstUpperElement {


    /**
     * n个整数的无序数组，找到每个元素后面比它大的第一个数，
     * 要求时间复杂度为O(N)
     * input: [2, 5, 3, 7, 1, 2, 8]
     * output:[5, 7, 7, 8, 2, 8]
     *
     * @param arr
     * @return
     */
    public static int[] solve(int[] arr) {
        int index = 0;
        int[] result = new int[arr.length - 1];
        Stack<Integer> stack = new Stack<>();
        while (index < arr.length) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[index]) {
                result[stack.pop()] = arr[index];
            }
            stack.push(index++);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 7, 1, 2, 8};
        System.out.println(Arrays.toString(solve(arr)));
    }
}
