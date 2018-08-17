package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class FindKth {
    
    /**
     * 无序数组中找到第k大元素
     * @param arr
     * @return
     */
    public static int findKth(int[] arr, int k) {
        if(arr == null || arr.length == 0 || k < 0) {
            throw new IllegalArgumentException();
        }
        int low = 0;
        int high = arr.length - 1;
        int index = QuickSort.partition(arr, low, high);
        while (index != arr.length - k - 1) {
            if (index < arr.length - k - 1) {
                index = QuickSort.partition(arr, index + 1, high);
            } else {
                index = QuickSort.partition(arr, low, index - 1);
            }
        }
        return arr[index];
    }

    public static void main(String[] args) {
        int[] arr = {4,2,1,6,2,5,7};
        System.out.println(findKth(arr,4));
        System.out.println(findKth(arr,0));
        System.out.println(findKth(arr,6));
    }
}
