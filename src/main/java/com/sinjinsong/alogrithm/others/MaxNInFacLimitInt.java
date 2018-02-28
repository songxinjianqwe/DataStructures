package com.sinjinsong.alogrithm.others;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class MaxNInFacLimitInt {
    
    /**
     * 通过对该数进行n的递增分解，
     * 直到该整数无法再分解（剩余的数，小于下一个待分解的n值），
     * 这时n-1就是最大的值。
     * 结果为12
     * @return
     */
    public static int maxN() {
        int maxValue = Integer.MAX_VALUE;
        int i;
        for (i = 2; i <= maxValue; i++) {
            maxValue /= i;
            // 退出时 i * (i-1) * (i-2) * ...2 * 1 > maxValue，所以最大的n为i-1
        }
        return i - 1;
    }

    public static void main(String[] args) {
        System.out.println(maxN());
        System.out.println(Factorial.fac(12));
    }
}
 