package com.sinjinsong.datastructure.sort;

/**
 * @author sinjinsong
 * @date 2018/2/13
 */
public class QuickSort {

    public static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if(left < right) {
            int low = left;
            int high = right;
            T pivotKey = arr[low];
            while(low < high) {
                while(low <= high && pivotKey.compareTo(arr[high]) < 0) {
                    high--;
                }
                arr[low] = arr[high];
                while(low <= high && pivotKey.compareTo(arr[low]) > 0) {
                    low++;
                }
                arr[high] = arr[low];
            }
            arr[low] = pivotKey;
            quickSort(arr,left,low - 1);
            quickSort(arr,low + 1,right);
        }
    }
}
