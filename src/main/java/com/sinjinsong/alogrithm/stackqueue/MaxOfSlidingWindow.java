package com.sinjinsong.alogrithm.stackqueue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class MaxOfSlidingWindow {
    /**
     * 数组arr，窗口大小w，返回一个长度为n-w+1的数组maxOfEachWindow。
     * 窗口从数组最左边滑到最右边，maxOfEachWindow[i]表示第i个窗口下的最大值。
     * 比如【4,3,5,4,3,3,6,7】，w=3，那么maxOfEachWindow为【5,5,5,4,6,7】
     * 最优解是O(n)
     * 使用双端队列（LinkedList,ArrayDeque），存放数组下标，队头始终为窗口最大值的下标。
     * 遍历每个数组元素，当前下标为i：
     * 1）如果队列为空，则i入队
     * 2）如果队列不空，取出队尾j，如果arr[j] > arr[i] ，则i入队；否则，不断出队直至arr[j] > arr[i]或队列为空，然后将i入队
     * 3）计算窗口最大值之前，如果队头下标为i-w，弹出队头（过期），队头下标对应值为当前窗口的maxOfEachWindow
     *
     * @param arr
     * @param w
     * @return
     */
    public static int[] maxOfWindow(int[] arr, int w) {
        int[] maxOfEachWindow = new int[arr.length - w + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0, windowIndex = 1 - w; i < arr.length; i++, windowIndex++) {
            // 保证deque的队头始终为最大值
            while (!deque.isEmpty() && arr[deque.getLast()] <= arr[i]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (windowIndex >= 0) {
                // 队头已过期，则删除
                if (deque.getFirst() == i - w) {
                    deque.removeFirst();
                }
                maxOfEachWindow[windowIndex] = arr[deque.getFirst()];
            }
        }
        return maxOfEachWindow;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 5, 4, 3, 3, 6, 7};
        System.out.println(Arrays.toString(maxOfWindow(arr, 3)));
    }
}
