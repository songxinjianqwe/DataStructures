package com.sinjinsong.alogrithm.sort;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class TopK {
    /**
     * 时间复杂度为O(n)
     * 基于划分的思想实现
     *
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> topK(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new ArrayList<>();
        }
        // 如果是BottomK的话就不需要单独处理k==n情况了，因为划分是小于等于放在左
        // topK的话如果是将大于的k个放在右边，那么pivot就变成了-1，会形成死循环。
        if (k == arr.length) {
            return IntStream.of(arr).boxed().collect(Collectors.toList());
        }
        int low = 0;
        int high = arr.length - 1;
        int index = QuickSort.partition(arr, low, high);
        while (index != arr.length - k - 1) {
            if (index > arr.length - k - 1) {
                index = QuickSort.partition(arr, low, index - 1);
            } else {
                index = QuickSort.partition(arr, index + 1, high);
            }
        }
        List<Integer> list = new ArrayList<>(k);
        for (int i = index + 1; i < arr.length; i++) {
            list.add(arr[i]);
        }
        System.out.println(Arrays.toString(arr));
        return list;
    }

    /**
     * 使用最小堆
     * 先往堆中放入k个元素，然后顺序读取剩下的n-k个元素，如果比堆顶小，那么直接放弃，否则删除堆顶，并放入该元素
     * 最终堆中的k个元素是最大的k个元素
     * 时间复杂度为O(n log k)
     * 空间复杂度为O(k)
     * 不会修改输入数据，并且适合海量数据（内存中只需要保存k份即可）
     *
     * @param arr
     * @param k
     * @return
     */
    public static List<Integer> topKByHeap(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new ArrayList<>();
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(arr[i]);
            }
        }
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 6, 4, 8, 23, 2};
        System.out.println(topKByHeap(arr, 7));
    }
}
