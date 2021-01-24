package com.sinjinsong.leetcode.easy;

public class LeetCode1744 {
    /**
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
     * 双指针，从后往前扫描
     * @param str
     * @return
     */
    public String replaceSpace(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            if(c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
