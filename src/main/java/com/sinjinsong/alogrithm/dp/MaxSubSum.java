package com.sinjinsong.alogrithm.dp;

import java.util.Arrays;

/**
 * 最大子序列：给定a1,a2,a3,a4.... an 可以有负数 求出某个子序列，它的和在所有子序列中最大
 * 子序列的长度可以是0,1,2,3.。。
 * 如果长度为0，则其值为0
 * 因此子序列和一定大于等于0
 * <p>
 * 最大连续子序列和
 *
 * @author SinjinSong
 */
public class MaxSubSum {
    
    /**
     * 算法1
     * //三重循环
     * //前两重循环得到所有的子序列
     * //第三重循环计算每个子序列的和
     * //i: 0 ~ n
     * //j: i ~ n
     * //k: i ~ j
     *
     * @param arr
     * @return
     */
    public static int maxSubSum1(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                //从i到j是当前子序列
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 算法2
     * //其实第三重循环是没有必要的
     * //在第二重的循环时就可以求该子序列的和
     *
     * @param arr
     * @return
     */
    public static int maxSubSum2(int[] arr) {
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                //从i到j是当前子序列
                sum += arr[j];
                if (sum > maxSum) {
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    /**
     * //算法3
     * //使用分治法解决这个问题
     * //分：分为两个大致相等的子问题，然后递归求解
     * //治：子问题的解合并
     * //4 -3 5 -2 | -1 2 6 -2
     * //结果只有三种情况
     * //左边的最大子序列
     * //右边的最大子序列
     * //跨两边的子序列：从中间的元素往左求最大子序列，再往右求最大子序列
     * //最终其最大子序列为左右之和
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int maxSubSumRec(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        int center = (left + right) / 2;
        int maxLeftSubSum = maxSubSumRec(arr, left, center);
        int maxRightSubSum = maxSubSumRec(arr, center + 1, right);

        int maxLeftBorderSum = Integer.MIN_VALUE, leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }
        int maxRightBorderSum = Integer.MIN_VALUE, rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }
        return max(maxLeftSubSum, maxRightSubSum, maxLeftBorderSum + maxRightBorderSum);
    }

    /**
     * 时间复杂度为O(n)
     * 设a[i]为和最大序列的起点，则如果a[i]是负的，那么它不可能代表最优序列的起点，
     * 因为任何包含a[i]作为起点的子序列都可以通过a[i+1]作起点而得到改进。
     * 类似的，任何负的子序列也不可能是最优子序列的前缀。
     * 最后得到的状态转移方程为：
     * currentSum(i) = arr[i] ,if i ==0 || currentSum(i-1) <= 0
     * currentSum(i) = currentSum(i – 1) + arr[i] , if i != 0 && currentSum(i-1) > 0
     * result = maxSum = Max(currentSum{i}) ,  0<=i<n
     *
     * @param arr
     * @return
     */
    public static int maxSubSumBestStart(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (currentSum <= 0) {
                currentSum = arr[i];
            } else {
                currentSum += arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum;
    }

    /**
     * 同时返回子序列的开始结束下标
     * @param arr
     * @return
     */
    public static int[] maxSubSumBestStartWithIndex(int[] arr) {
        int maxSum = Integer.MIN_VALUE, currentSum = 0;
        int maxStart = 0;
        int currentStart = 0;
        int maxEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (currentSum <= 0) {
                currentSum = arr[i];
                currentStart = i;
            } else {
                currentSum += arr[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxStart = currentStart;
                maxEnd = i;
            }
        }
        return new int[]{maxSum,maxStart,maxEnd};
    }

    private static int max(int i, int j, int k) {
        return Math.max(Math.max(i, j), k);
    }

    public static void main(String[] args) {
//        int[] arr = {4, -3, 5, -2, -1, 2, 6, -2};
        int[] arr = {-2, -8, -1, -5, -9};
        System.out.println(maxSubSum1(arr));
        System.out.println(maxSubSum2(arr));
        System.out.println(maxSubSumRec(arr, 0, arr.length - 1));
        System.out.println(maxSubSumBestStart(arr));
        System.out.println(Arrays.toString(maxSubSumBestStartWithIndex(arr)));
    }
}
