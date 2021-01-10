package com.sinjinsong.alogrithm.leetcode.medium;

public class LeetCode50 {
    public double myPow(double x, int n) {
        double curr;
        if(n > 0) {
            curr = x;
        } else {
            curr = 1 / x;
        }
        if(n < 0) {
            n *= -1;
        }
        double ans = 1;
        while(n != 0) {
            if ((n & 1) == 1) {
                x *= curr;
            }
            n >>= 1;
            curr *= curr;
        }
        return ans;
    }
}
