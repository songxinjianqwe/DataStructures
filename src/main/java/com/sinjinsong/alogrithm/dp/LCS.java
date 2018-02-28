package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class LCS {
    /**
     * dp[i][j] = MAX(dp[i-1][j],dp[i][j-1],dp[i-1][j-1] if str1[i] == str2[j])
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int LCS(String str1, String str2) {
        if (str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        int[][] dp = new int[str1.length()][str2.length()];
        // 填充dp的第一列
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) == str2.charAt(0)) {
                for (int j = i; j < str1.length(); j++) {
                    dp[j][0] = 1;
                }
                break;
            } else {
                dp[i][0] = 0;
            }
        }
        // 填充dp的第一行
        for (int i = 0; i < str2.length(); i++) {
            if (str2.charAt(i) == str1.charAt(0)) {
                for (int j = i; j < str2.length(); j++) {
                    dp[0][j] = 1;
                }
                break;
            } else {
                dp[0][i] = 0;
            }
        }
        // 填充剩余部分
        for (int i = 1; i < str1.length(); i++) {
            for (int j = 1; j < str2.length(); j++) {
                int prev = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(prev, 1 + dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = prev;
                }
            }
        }
        return dp[str1.length() - 1][str2.length() - 1];
    }

    public static void main(String[] args) {
        System.out.println(LCS("A1BC2", "AB34C"));
    }
}
