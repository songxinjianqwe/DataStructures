package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class Exchange {

    public static int getExchange(int[] penny, int target) {
//        return getExchange(arr,0,target);
        int[][] map = new int[penny.length + 1][target + 1];
        return getExchange(penny, 0, target, map);
    }

    /**
     * 使用arr[start]~arr[n-1]表示的面额们，去凑齐target
     *
     * @param penny
     * @param start
     * @param target
     * @return
     */
    private static int getExchange(int[] penny, int start, int target) {
        if (start == penny.length) {
            return target == 0 ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; i <= target / penny[start]; i++) {
            sum += getExchange(penny, start + 1, target - i * penny[start]);
        }
        return sum;
    }


    /**
     * 缓存Map<Start,Map<Target,Sum>>
     *
     * @param penny
     * @param start
     * @param target
     * @param map
     * @return
     */
    private static int getExchange(int[] penny, int start, int target, int[][] map) {
        if (start == penny.length) {
            return target == 0 ? 1 : 0;
        }
        int sum = 0;
        for (int i = 0; i <= target / penny[start]; i++) {
            int cachedValue = map[start + 1][target - i * penny[start]];
            if (cachedValue != 0) {
                sum += cachedValue == -1 ? 0 : cachedValue;
            } else {
                sum += getExchange(penny, start + 1, target - i * penny[start]);
            }
        }
        map[start][target] = sum == 0 ? -1 : sum;
        return sum;
    }

    /**
     * 
     * @param penny
     * @param target
     * @return
     */
    public static int getExchangeByDP(int[] penny, int target) {
        if (penny == null || penny.length == 0){
            return 0;
        }
        int[][] dp = new int[penny.length][target + 1];
        for (int i = 0; i < target + 1; i++) {
            dp[0][i] = i % penny[0] == 0 ? 1 : 0;
        }
        for (int i = 1; i < penny.length; i++) {
            for (int j = 0; j < target + 1; j++) {
                if (j < penny[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - penny[i]] + dp[i - 1][j];
                }
            }
        }
        return dp[penny.length - 1][target];
    }

    public static void main(String[] args) {
        System.out.println(getExchangeByDP(new int[]{5, 10, 25, 1}, 1000));
    }
}
