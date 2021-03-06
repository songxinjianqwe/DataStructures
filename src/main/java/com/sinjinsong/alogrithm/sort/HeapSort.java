package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/3/23
 */
public class HeapSort {
    
    public static void heapSort(int[] arr) {
        buildMaxHeap(arr);
        
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            filterDownForMaxHeap(arr,i,0);
        }
    }

    public static void buildMaxHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            filterDownForMaxHeap(arr, arr.length, i);
        }
    }

    public static void filterDownForMaxHeap(int[] arr, int size, int root) {
        int rootData = arr[root];
        int upperChild = 0;
        while (root * 2 + 1 < size) {
            upperChild = root * 2 + 1;
            if (upperChild + 1 < size && arr[upperChild] < arr[upperChild + 1]) {
                upperChild++;
            }
            if (arr[upperChild] < rootData) {
                break;
            } else {
                arr[root] = arr[upperChild];
                root = upperChild;
            }
        }
        arr[root] = rootData;
    }
    
     public static void buildMinHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            filterDownForMinHeap(arr, arr.length, i);
        }
    }

    public static void filterDownForMinHeap(int[] arr, int size, int root) {
        int rootData = arr[root];
        int upperChild = 0;
        while (root * 2 + 1 < size) {
            upperChild = root * 2 + 1;
            if (upperChild + 1 < size && arr[upperChild] > arr[upperChild + 1]) {
                upperChild++;
            }
            if (arr[upperChild] > rootData) {
                break;
            } else {
                arr[root] = arr[upperChild];
                root = upperChild;
            }
        }
        arr[root] = rootData;
    }

    public static void main(String[] args) {
        int[] arr = {4,2,6,1,7,2,1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
