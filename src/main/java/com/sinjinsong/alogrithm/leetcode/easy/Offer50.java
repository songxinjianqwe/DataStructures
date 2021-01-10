package com.sinjinsong.alogrithm.leetcode.easy;

public class Offer50 {
    /**
     * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
     *
     * 示例:
     *
     * s = "abaccdeff"
     * 返回 "b"
     *
     * s = ""
     * 返回 " "
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        int[] map = new int[256];
        for (char c : s.toCharArray()) {
            map[c]++;
        }
        for (char c : s.toCharArray()) {
            if(map[c] == 1) {
                return c;
            }
        }
        return ' ';
    }
}
