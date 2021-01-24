package com.sinjinsong.leetcode.medium;

public class LeetCode64 {
//    public int minPathSum(int[][] grid) {
//        return dfs(grid, 0, 0, grid.length - 1, grid[0].length - 1, grid[0][0]);
//    }
//
//    private int dfs(int[][] grid, int i, int j, int targetRow, int targetCol, int currentSum) {
//        if (i == targetRow && j == targetCol) {
//            return currentSum;
//        } else {
//            int ans = Integer.MAX_VALUE;
//            if(j < targetCol) {
//                ans = Math.min(ans, dfs(grid, i, j + 1, targetRow, targetCol, currentSum + grid[i][j + 1]));
//            }
//            if(i < targetRow) {
//                ans = Math.min(ans, dfs(grid, i + 1, j, targetRow, targetCol, currentSum + grid[i + 1][j]));
//            }
//            return ans;
//        }
//    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // 填充第一行
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        // 填充第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int i = new LeetCode64().minPathSum(new int[][]{{1, 2, 3}, {4,5,6}});
        System.out.println(i);
    }
}
