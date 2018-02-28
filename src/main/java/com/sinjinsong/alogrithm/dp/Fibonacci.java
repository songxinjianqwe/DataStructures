package com.sinjinsong.alogrithm.dp;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class Fibonacci {
    
    /**
     * f(0) = 1
     * f(1) = 1
     * f(i) = f(i-1) + f(i-2)
     * @param n
     * @return
     */
    public static int fib(int n) {
        if(n == 0 || n == 1) {
            return 1;
        }
        int first = 1;
        int second = 1;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(fib(4));
    }
}
