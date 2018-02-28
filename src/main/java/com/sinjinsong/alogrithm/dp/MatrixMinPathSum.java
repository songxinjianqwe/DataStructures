package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class MatrixMinPathSum {
    /**
     * dp[i][j] = matrix[i][j] + Min(dp[i-1][j], dp[i][j-1])
     * @param matrix
     * @return
     */
    public static int minPathSum(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = (i > 0 ? dp[0][i - 1] : 0) + matrix[0][i];
        }
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = (i > 0 ? dp[i - 1][0] : 0) + matrix[i][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[matrix.length - 1][matrix[0].length - 1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,9},
                {8,1,3,4},
                {5,0,6,1},
                {8,8,4,0}
        };
        System.out.println(minPathSum(matrix));
    }
}
