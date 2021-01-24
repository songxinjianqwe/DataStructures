package com.sinjinsong.leetcode.medium;

public class LeetCode50 {
    public double myPow(double x, int n) {
        double curr = x;
        if (n == 0) {
            return 1;
        }
        boolean negative = false;
        if (n < 0) {
            n *= -1;
            negative = true;
        }
        double ans = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                ans *= curr;
            }
            n >>= 1;
            curr *= curr;
        }
        return negative ? 1 / ans : ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode50().myPow(2, 10));
    }
}
