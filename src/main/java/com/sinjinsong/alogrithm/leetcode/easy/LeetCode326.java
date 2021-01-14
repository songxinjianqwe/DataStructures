package com.sinjinsong.alogrithm.leetcode.easy;

public class LeetCode326 {
    public boolean isPowerOfThree(int n) {
        int i = 1;
        while(i < n) {
            i = i * 3;
        }
        return i == n;
    }
}
