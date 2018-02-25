package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class PartialMinBinarySearch {


    /**
     * 给定无序数组arr，已知arr中任意两个相邻的数都不相等，写一个函数，只需返回arr中任意一个局部最小出现的位置即可。
     * 搜索任意一个局部最小的位置
     * 局部最小是指：
     * 1）arr[0] < arr[1] ，0是局部最小的位置
     * 2）arr[N-1] < arr[N-2]，N-1是局部最小的位置
     * 3）arr[i] < arr[i-1] && arr[i] < arr[i+1]，i是局部最小的位置
     * 时间复杂度为O(log n)
     *
     * @param arr
     * @return
     */
    public static int search(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        if (arr.length == 1) {
            return 0;
        }
        // 至少有两个元素
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr.length - 1;
        }
        int low = 1;
        int high = arr.length - 2;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1]) {
                high = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1,0,5,3,4,9};
        System.out.println(search(arr));
    } 
}
