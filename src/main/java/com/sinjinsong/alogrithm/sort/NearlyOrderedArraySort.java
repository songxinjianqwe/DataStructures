package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/19
 */
public class NearlyOrderedArraySort {
    /**
     * 几乎有序的数组排序
     * 几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小
     * 使用改进后的堆排序实现
     *
     * @param arr
     */
    public static void sort(int[] arr, int k) {
        int[] kArr = new int[k];
        for (int i = 0; i < arr.length - k + 1; i++) {
            System.arraycopy(arr, i, kArr, 0, k);
            buildHeap(kArr);
            System.arraycopy(kArr, 0, arr, i, k);
        }
    }

    /**
     * 将low~high的元素调整为小顶堆
     * 调整时将low~high视为0~high-low
     *
     * @param arr
     */
    private static void buildHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            filterDown(arr, arr.length - 1, i);
        }
    }

    private static void filterDown(int[] arr, int maxIndex, int root) {
        int e = arr[root];
        int upperChild;
        // 当root仍有孩子，则继续循环
        while ((root * 2 + 1) <= maxIndex) {
            upperChild = root * 2 + 1;
            if (upperChild < maxIndex && arr[upperChild] > arr[upperChild + 1]) {
                upperChild++;
            }
            // 此时upperChild指向左右孩子较小的
            if (e < arr[upperChild]) {
                break;// 符合小顶堆的规则，直接退出
            } else {
                arr[root] = arr[upperChild];
                root = upperChild;// 继续向下调整
            }
        }
        arr[root] = e;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 3, 6, 5, 8, 7, 10, 9};
        sort(arr, 2);
        System.out.println(Arrays.toString(arr));
    }
}
