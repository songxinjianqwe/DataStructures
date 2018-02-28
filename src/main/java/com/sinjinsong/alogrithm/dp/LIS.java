package com.sinjinsong.alogrithm.dp;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class LIS {
    /**
     * 最长上升子序列长度
     * dp[0] = 1
     * dp[i]表示必须以arr[i]为结尾的弃考下，arr[0~i]中的最长上升子序列的长度
     * dp[i[ = MAX(dp[j) + 1 , 0<=j<i , arr[j] < arr[i]
     * arr: 1 7 2 8 3 4
     * dp:  1 2 2 3 3 4
     * 返回值是dp数组中的最大值
     * @param arr
     * @return
     */
    public static int LIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int maxDP = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] > maxDP) {
                    maxDP = dp[j];
                }
            }
            dp[i] = maxDP + 1;
            result = Math.max(result,dp[i]);
        }
        System.out.println(Arrays.toString(dp));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(LIS(new int[]{1, 7, 2, 8, 3, 4}));
    }
}
