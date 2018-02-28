package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class ZeroOneBag {
    /**
     * 一个背包有一定的承重cap，有N件物品，每件都有自己的价值，
     * 记录在数组v中，也都有自己的重量，记录在数组w中，
     * 每件物品只能选择要装入背包还是不装入背包，
     * 要求在不超过背包承重的前提下，选出物品的总价值最大。
     * 给定物品的重量w价值v及物品数n和承重cap。请返回最大总价值。
     * <p>
     * dp[i][j] = MAX(dp[i-1][j],dp[i-1][j - w[i]] + v[i]  ,if j -w[i] >= 0 )
     *
     * @param w
     * @param v
     * @param n
     * @param cap
     * @return
     */
    public static int maxValue(int[] w, int[] v, int n, int cap) {
        if (w.length == 0 || v.length == 0 || n == 0 || cap == 0) {
            return 0;
        }
        // dp[i][j] 表示前i件物品，不超过重量j时的最大价值
        int[][] dp = new int[n][cap + 1];
        // 第一列 填充cap为0
        for (int i = 0; i < n; i++) {
            dp[i][0] = 0;
        }
        // 第一行 填充第0件物品
        for (int i = 0; i <= cap; i++) {
            dp[0][i] = (w[0] <= i ? v[0] : 0);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= cap; j++) {
                if (j - w[i] >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][cap];
    }

    public static void main(String[] args) {
        int[] w = {1, 2, 3};
        int[] v = {1, 2, 3};

        System.out.println(maxValue(w, v, 3, 6));
    }
}
