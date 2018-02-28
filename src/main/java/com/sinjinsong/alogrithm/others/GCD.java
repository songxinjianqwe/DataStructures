package com.sinjinsong.alogrithm.others;

public class GCD {

    //辗转相除法
    //每次循环求余数，将除数赋给被除数，将m与n的余数赋给除数,
    //默认是要求m>n的
    public static int gcd(int m, int n) {
        int remainder = 0;
        while (n != 0) {
            remainder = m % n;
            m = n;
            n = remainder;
        }
        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(50, 15));
    }
}
