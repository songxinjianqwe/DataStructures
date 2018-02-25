package com.sinjinsong.alogrithm.probability;

import java.util.Arrays;
import java.util.Random;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class RandomArrayPrint {
    public static int[] print(int[] arr, int m) {
        int[] result = new int[m];
        Random r = new Random();
        int randomIndex;
        for (int i = 0; i < m; i++) {
            randomIndex = r.nextInt(arr.length - m);
            result[i] = arr[randomIndex];
            swap(arr, arr.length - i - 1, randomIndex);
        }
        return result;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] print = print(new int[]{29, 24, 17, 1, 3, 11, 8, 19, 12, 15, 10, 28, 20, 18, 2, 26, 14, 7, 22, 27, 23, 5, 6, 9, 21, 16, 25, 4, 13}, 10);
        System.out.println(Arrays.toString(print));
    }
}
