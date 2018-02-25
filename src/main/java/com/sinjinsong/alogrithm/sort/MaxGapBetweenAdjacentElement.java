package com.sinjinsong.alogrithm.sort;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/20
 */
public class MaxGapBetweenAdjacentElement {

    public static int maxGap(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        int n = arr.length;
        // bucket中只存储最小值和最大值
        int[] minBucket = new int[n + 1];
        int[] maxBucket = new int[n + 1];
        boolean[] isEmptyBucket = new boolean[arr.length + 1];
        Arrays.fill(minBucket,Integer.MAX_VALUE);
        Arrays.fill(isEmptyBucket,true);
        int bucketNumber;
        for (int i = 0; i < n; i++) {
            bucketNumber = bucketNumber(arr[i], n, min, max);
            System.out.println("data:"+arr[i]+",bucketNumber:"+bucketNumber);
            minBucket[bucketNumber] = isEmptyBucket[bucketNumber] ? arr[i] : Math.min(minBucket[bucketNumber], arr[i]);
            maxBucket[bucketNumber] = isEmptyBucket[bucketNumber] ? arr[i] : Math.max(maxBucket[bucketNumber], arr[i]);
            isEmptyBucket[bucketNumber] = false; 
        }

        // 桶填充完毕
        int maxGap = 0;
        int i = 0;
        int priorMax = 0;
        while (i < n + 1) {
            // 寻找第一个空桶，取其前一个桶的最大值
            while (i < n + 1 && !isEmptyBucket[i]) {
                i++;
            }
            if (i == n + 1) {
                return maxGap;
            }
            // 前一个桶的最大值
            priorMax = maxBucket[i - 1];
            // 寻找之后的第一个非空桶，取其最小值，相减即为gap
            // 遍历一遍，得到maxGap
            while (i < n + 1 && isEmptyBucket[i]) {
                i++;
            }
            if (i == n + 1) {
                return maxGap;
            }
            maxGap = Math.max(maxGap,minBucket[i] - priorMax);
        }
        return maxGap;
    }

    private static int bucketNumber(int value, int n, int min, int max) {
        return (int) (1.0 * (value - min) * n / (max - min));
    }
    
    public static void main(String[] args) {
        int [] arr = {7,9,3,4,2,1,8};
        System.out.println(maxGap(arr));
    }
}
