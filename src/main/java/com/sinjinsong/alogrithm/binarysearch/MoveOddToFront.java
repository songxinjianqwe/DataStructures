package com.sinjinsong.alogrithm.binarysearch;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class MoveOddToFront {
    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
     * 使得所有的奇数位于数组的前半部分，所有的偶数位于位于数组的后半部分，并保证奇数和奇数，
     * 偶数和偶数之间的相对位置不变。
     * 0 2 4 6 1 3 5
     * <p>
     * 1)
     * 5 2 4 6 1 3 0
     * 2)
     * 5 3 4 6 1 2 0
     * 3)
     * 5 3 1 6 4 2 0
     * 4)
     *
     * @param arr
     */
    public static void moveOddToFront(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        while (low < high) {
            while (low <= high && (arr[low] & 1) == 1) {
                low++;
            }
            while (low <= high && (arr[high] & 1) == 0) {
                high--;
            }
            if (low < high) {
                swap(arr, low, high);
            }
        }
    }

    public static void moveOddToFrontFaster(int[] arr) {
        int afterOdd = 0;
        for (int i = arr.length - 1; i >= afterOdd; ) {
            if ((arr[i] & 1) == 1) {
                swap(arr, i, afterOdd);
                afterOdd++;
            } else {
                i--;
            }
        }
    }

    /**
     * 奇数和奇数之间，偶数和偶数之间相对位置不变
     *
     * @param arr
     */
    public static void moveOddToFrontWithFixedRelativePosition(int[] arr) {
        Queue<Integer> queue = new ArrayDeque<>();
        int evenCount = 0;
        for (int i = 0; i < arr.length; i++) {
            // 奇数的话,就放到i-evenCount位置
            if ((arr[i] & 1) == 1) {
                arr[i - evenCount] = arr[i];
            } else {
                // 偶数的话,就保存起来
                queue.add(arr[i]);
                evenCount++;
            }
        }
        // 将奇数顺序放到数组后面
        for (int i = arr.length - evenCount; i < arr.length; i++) {
            arr[i] = queue.remove();
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 4, 6, 1, 3, 5};
        // 5, 3, 1, 6, 4, 2, 0
//        moveOddToFront(arr);
//        moveOddToFrontFaster(arr);
        //5, 3, 1, 6, 4, 2, 0
        moveOddToFrontWithFixedRelativePosition(arr);
        System.out.println(Arrays.toString(arr));
    }
}
