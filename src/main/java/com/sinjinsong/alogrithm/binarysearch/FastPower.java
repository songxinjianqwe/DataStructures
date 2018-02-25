package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class FastPower {
    /**
     * 求k的n次方，结果对mod取余（避免溢出）
     * 设置当前算子（curr）初值为k。
     * 将N转为二进制形式，从最后一位开始从后向前遍历。
     * 如果当前位为1，那么result * curr，否则result不变。
     * curr乘其自身（指数翻倍），N右移。
     * 直至N为0。
     * 时间复杂度为O(log n)
     * @param k
     * @param n
     * @param mod
     * @return
     */
    public static int power(int k, int n, long mod) {
        if (n == 0) {
            return 1;
        }
        long result = 1;
        long curr = k;
        while (n != 0) {
            // 如果n的最低位为1
            if ((n & 1) == 1) {
                result = (result * curr) % mod;
            }
            n >>= 1;
            curr = (curr * curr) % mod;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(power(10, 75, 1000000007));
    }
}
