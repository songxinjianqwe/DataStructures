package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/20
 */
public class ThreeColorSort {
    /**
     * 三色排序问题
     * 1）若遍历到的位置为0，则说明它一定属于前部，于是就和begin位置进行交换，然后current向前进，begin也向前进（表示前边的已经都排好了）。
     * 2）若遍历到的位置为1，则说明它一定属于中部，根据总思路，中部的我们都不动，然后current向前进。
     * 3）若遍历到的位置为2，则说明它一定属于后部，于是就和end位置进行交换，由于交换完毕后current指向的可能是属于前部的，若此时current前进则会导致该位置不能被交换到前部，所以此时current不前进。而同1），end向后退1。
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int after0 = 0;
        int before2 = arr.length - 1;
        int current = 0;
        while (current <= before2) {
            if (arr[current] == 0) {
                swap(arr, after0, current);
                current++;
                after0++;
            } else if (arr[current] == 2) {
                swap(arr, before2, current);
                before2--;
            } else {
                current++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 0, 0, 2, 1, 1, 0};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
