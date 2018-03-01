package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class QuickSort {

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int low = left, high = right;
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivotKey = arr[low];
        while (low < high) {
            while (low < high && pivotKey <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && pivotKey > arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivotKey;
        return low;
    }

    /**
     * 栈中存放着应该调用partition的low和high指针
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSortNoRec(int[] arr, int left, int right) {
        Stack<Integer> stack = new Stack<>();
        if (left < right) {
            stack.push(left);
            stack.push(right);
            
            while (!stack.isEmpty()) {
                int high = stack.pop();
                int low = stack.pop();
                int pivotIndex = partition(arr, low, high);
                if (low < pivotIndex - 1) {
                    stack.push(low);
                    stack.push(pivotIndex - 1);
                }
                if (pivotIndex + 1 < high) {
                    stack.push(pivotIndex + 1);
                    stack.push(high);
                }
            }
        }
    }


    public static void main(String[] args) {
        int[] arr = {65, 2, 3, 1, 5, 7, 23};
//        quickSort(arr, 0, arr.length - 1);
        quickSortNoRec(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
