package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class RopeCut {

    /**
     * 给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)
     * 每段绳子的长度记为k[0],k[1],...,k[m].请问k[0]*k[1]*...*k[m]可能的最大乘积是多少？
     * 例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
     * 绳子至少是2米，并且必须最少剪一刀。
     * <p>
     * f(n)为把长度为n的绳子剪成若干段后各段长度乘积的最大值。
     * 在剪第一刀时，我们有n-1种选择，也就是说第一段绳子的可能长度分别为1,2,3.....，n-1。
     * 因此f(n)=max(f(i)*f(n-i))，其中0<i<n。
     * f(0) = 0
     * f(1) = 1
     * f(2) = 1
     * f(3) = 2
     *
     * @param length
     * @return
     */
    public static int maxMultiplyResult(int length) {
        if (length < 2) {
            return 0;
        }
        if (length == 2) {
            return 1;
        }
        if (length == 3) {
            return 2;
        }
        int dp[] = new int[length + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        int max;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i / 2 ; j++) {
                int product = dp[j] * dp[i-j];
                if(product > max) {
                    max = product;
                }
            }
            dp[i] = max;
        }
        return dp[length];
    }

    public static void main(String[] args) {
        System.out.println(maxMultiplyResult(5));
    }
}