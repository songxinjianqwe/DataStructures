package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class BitMax {
    /**
     * 最低位取反，其他位不变
     *
     * @param n
     * @return
     */
    public static int flip(int n) {
        return n ^ 1;
    }

    /**
     * 逻辑右移就是不考虑符号位，右移一位，左边补零即可。 >>>
     * 算术右移需要考虑符号位，右移一位，若符号位为1，就在左边补1,；否则，就补0。 >>
     * n >> 31后，所有位都是符号位。
     * 再与1取与，保留最低位（符号位），其他位全是0
     * 如果n为正数，则返回0，如果n为负数，则返回1
     *
     * @param n
     * @return
     */
    public static int sign(int n) {
        return (n >> 31) & 1;
    }

    public static int Max(int a, int b) {
        int c = a - b;
        int signC = sign(c);
        int flipSignC = flip(signC);
        return flipSignC * a + signC * b;
    }



    public static void main(String[] args) {
        System.out.println(Max(1, 2));
    }
}
