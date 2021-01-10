package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/20
 */
public class ShortestSubArrayToBeSorted {
    public static int lenOfShortestSubArray(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < max) {
                maxIndex = i;
            }
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] < min) {
                min = arr[i];
            } else if (arr[i] > min) {
                minIndex = i;
            }
        }
        // 如果minIndex和maxIndex相同，那么返回0
        if (minIndex == maxIndex) {
            return 0;
        } else {
            return maxIndex - minIndex + 1;
        }
    }

    public static void main(String[] args) {
//        int[] arr = {1, 5, 4, 3, 2, 6, 7};
        int[] arr = {1, 2, 3, 4, 6, 7};
        System.out.println(lenOfShortestSubArray(arr));
    }
}
