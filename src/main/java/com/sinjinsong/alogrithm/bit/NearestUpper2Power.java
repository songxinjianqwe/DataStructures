package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class NearestUpper2Power {

    public static int nearestUpper2Power(int n) {
        if ((n & (n - 1)) == 0) {
            return n;
        }
        // 从最高bit位为1的位开始，从左边往右边复制bit位为1到相邻的bit位
        // 报政府从最高bit位为1的位开始，之后全为1,
        // 这样加1，结果就是2的幂次了
        n = n - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(nearestUpper2Power(15));
    }
}
