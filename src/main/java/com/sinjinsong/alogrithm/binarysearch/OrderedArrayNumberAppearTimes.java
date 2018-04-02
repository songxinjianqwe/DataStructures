package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class OrderedArrayNumberAppearTimes {
    /**
     * 有序数组中某个数字的出现次数
     * 时间复杂度为O(log n)
     *
     * @param arr
     * @param key
     * @return
     */
    public static int getAppearTimes(int[] arr, int key) {
        int min = BinarySearch.binarySearchMaxPosition(arr, key);
        int max = BinarySearch.binarySearchMinPosition(arr, key);
        if (min != -1 && max != -1) {
            if (min == max) {
                return 1;
            } else {
                return max - min - 1;
            }
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
//        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
//        System.out.println(getAppearTimes(arr, 3));
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(getAppearTimes(arr, 3));
    }
}
