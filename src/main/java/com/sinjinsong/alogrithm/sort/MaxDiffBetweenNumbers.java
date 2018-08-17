package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class MaxDiffBetweenNumbers {

    /**
     * 数组中数对的最大差值，要求较大的数字位置在较后面
     * 最简单的算是二层循环，时间复杂度为O(n^2)
     * min始终保存着最小值
     * currentDiff(i) = 较大的数字位置为i时的差值 = arr[i] - min
     * 时间复杂度为O(n)
     *
     * @param arr
     * @return
     */
    public static int maxDiff(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[0];
        int maxDiff = 0;
        int currentDiff;
        for (int i = 1; i < arr.length; i++) {
            currentDiff = arr[i] - min;
            if (arr[i] < min) {
                min = arr[i];
            }
            if (currentDiff > maxDiff) {
                maxDiff = currentDiff;
            }
        }
        return maxDiff;
    }

    public static void main(String[] args) {
        int[] arr = {9, 11, 8, 5, 7, 12, 16, 14};
        System.out.println(maxDiff(arr));
    }
}
