package com.sinjinsong.alogrithm.others;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class Factorial {
    public static int fac(int n) {
        if(n < 0){
            throw new IllegalArgumentException();
        }
        if(n == 0 || n == 1) {
            return 1;
        }
        return fac(n -1 ) * n;
    }
}
