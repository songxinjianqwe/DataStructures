package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class Is2Power {

    public static boolean is2Power(int n) {
        return (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(is2Power(2));
        System.out.println(is2Power(16));
        System.out.println(is2Power(99));
        
    }
}
