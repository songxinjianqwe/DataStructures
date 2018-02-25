package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class BitSwap {
    public static void swap(int a, int b) {
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
    }
}
