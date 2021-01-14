package com.sinjinsong.alogrithm.leetcode.easy;

public class LeetCode28 {

    public int strStr(String haystack, String needle) {
        if(needle.isEmpty()) {
            return 0;
        }
        char[] src = haystack.toCharArray();
        char[] target = needle.toCharArray();
        if(src.length < target.length) {
            return -1;
        }
        int i = 0, j = 0;
        int iStart = -1;
        while (i < src.length && j < target.length) {
            if (iStart == -1) {
                while (i < src.length && src[i] != target[j]) {
                    i++;
                }
                if (i < src.length) {
                    iStart = i;
                    i++;
                    j++;
                } else {
                    return -1;
                }
            } else if (src[i] != target[j]) {
                j = 0;
                i = iStart + 1;
                iStart = -1;
            } else {
                i++;
                j++;
            }
        }
        return j == target.length ? iStart : -1;
    }
}
