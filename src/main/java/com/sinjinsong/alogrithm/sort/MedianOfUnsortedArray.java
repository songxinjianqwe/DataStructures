package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/8/15
 */
public class MedianOfUnsortedArray {

    public static double getMedian(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int pivotIndex = QuickSort.partition(arr, low, high);
        int targetIndex = arr.length / 2;
        while (pivotIndex != targetIndex) {
            if (pivotIndex > targetIndex) {
                pivotIndex = QuickSort.partition(arr, low, pivotIndex - 1);
            } else {
                pivotIndex = QuickSort.partition(arr, pivotIndex + 1, high);
            }
        }
        if ((arr.length & 1) == 1) {
            return arr[pivotIndex];
        } else {
            return 1.0 * (arr[pivotIndex - 1] + arr[pivotIndex]) / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 4, 2, 9};
        System.out.println(getMedian(arr));
        int[] arr2 = {2, 3, 1, 6, 4, 2, 9, 5};
        System.out.println(getMedian(arr2));
    }
}
