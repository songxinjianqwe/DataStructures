package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/19
 */
public class OrderedArrayMerge {
    /**
     * 将a和b两个有序数组合并至a中
     * a的长度是a的有效元素长度+b的长度
     *
     * @param a
     * @param b
     * @return
     */
    public static void merge(int[] a, int[] b) {
        int i = a.length - b.length - 1;
        int j = b.length - 1;
        int merge = a.length - 1;
        while (i >= 0 && j >= 0) {
            if (a[i] <= b[j]) {
                a[merge] = b[j];
                merge--;
                j--;
            } else {
                a[merge] = a[i];
                merge--;
                i--;
            }
        }
        // 因为是从后向前遍历，这样不需要把a的重新拷贝回a
        while(j >= 0) {
            a[merge] = b[j];
            merge--;
            j--;
        }
    }

    public static void main(String[] args) {
        int [] a = {1,3,6,0,0,0};
        int [] b = {2,4,5};
        merge(a,b);
        System.out.println(Arrays.toString(a));
    }
}
