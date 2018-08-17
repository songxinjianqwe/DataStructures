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
        int[] minHeap = new int[k];
        System.arraycopy(arr, 0, minHeap, 0, k);
        // 将前k个元素调整为小顶堆
        HeapSort.buildMinHeap(minHeap);

        // 每次将小顶堆的堆顶赋给指针，然后将指针+k位置的元素设置为新的堆堆，并且重新调整为小顶堆
        for (int i = k; i < arr.length; i++) {
            arr[i - k] = minHeap[0];
            minHeap[0] = arr[i];
            HeapSort.filterDownForMinHeap(minHeap, k, 0);
        }

        // 最后k个元素进行一次堆排序
        for (int i = arr.length - k; i < arr.length; i++) {
            arr[i] = minHeap[0];

            int temp = minHeap[0];
            minHeap[0] = minHeap[k - 1];
            minHeap[k - 1] = temp;
            HeapSort.filterDownForMinHeap(minHeap, --k, 0);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 3, 6, 5, 8, 7, 10, 9};
        sort(arr, 2);
        System.out.println(Arrays.toString(arr));
        
        int[] arr2 = {1, 4, 3, 2, 5};
        sort(arr2, 3);
        System.out.println(Arrays.toString(arr2));
        
    }
}
