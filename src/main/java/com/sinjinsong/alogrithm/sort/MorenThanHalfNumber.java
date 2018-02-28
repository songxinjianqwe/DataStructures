package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class MorenThanHalfNumber {


    /**
     * 返回数组中出现次数超过一半的数字
     * 使用类似于快速排序的思想
     *
     * @param arr
     * @return
     */
    public static int moreThanHalf(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int low = 0;
        int high = arr.length - 1;
        int index = QuickSort.partition(arr, low, high);
        int mid = (low + high) / 2;
        while (index != mid) {
            if (index > mid) {
                index = QuickSort.partition(arr, low, index - 1);
            } else {
                index = QuickSort.partition(arr, index + 1, high);
            }
        }
        if(!checkMoreThanHalf(arr,arr[index])) {
            return 0;
        }
        return arr[index];
    }

    /**
     * 检查number是否真的超过了一般
     * @param arr
     * @param number
     * @return
     */
    private static boolean checkMoreThanHalf(int[] arr, int number) {
        int times = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == number) {
                times++;
            }
        }
        return times * 2 > arr.length;
    }


    public static void main(String[] args) {
        int[] arr = {2, 2, 2, 2, 1, 3, 4, 5};
        System.out.println(moreThanHalf(arr));
    }
}
