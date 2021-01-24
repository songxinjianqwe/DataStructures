package com.sinjinsong.leetcode.easy;

public class LeetCode9 {
    public boolean isPalindrome(int x) {
        StringBuilder sb = new StringBuilder();
        while(x != 0) {
            sb.append(x % 10);
            x /= 10;
        }
        char[] chars = sb.toString().toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if(chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean palindrome = new LeetCode9().isPalindrome(10);
        System.out.println(palindrome);
    }
}
