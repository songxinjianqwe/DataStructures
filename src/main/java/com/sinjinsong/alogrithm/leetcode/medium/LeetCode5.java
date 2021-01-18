package com.sinjinsong.alogrithm.leetcode.medium;

public class LeetCode5 {
    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：s = "babad"
     * 输出："bab"
     * 解释："aba" 同样是符合题意的答案。
     * 示例 2：
     *
     * 输入：s = "cbbd"
     * 输出："bb"
     * 示例 3：
     *
     * 输入：s = "a"
     * 输出："a"
     * 示例 4：
     *
     * 输入：s = "ac"
     * 输出："a"
     *  
     *
     * 提示：
     *
     * 1 <= s.length <= 1000
     * s 仅由数字和英文字母（大写和/或小写）组成
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 1, maxStart = 0, maxEnd = 0;
        // right 1 ~ n
        // left  0 ~ right
        for (int right = 1; right < s.length(); right++) {
            for (int left = 0; left < right; left++) {
                // if  left+1,right-1=true && left v == right v - > left,right=true
                if (str[left] == str[right] && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if (right - left + 1 > maxLen) {
                        maxLen = right - left;
                        maxStart = left;
                        maxEnd = right;
                    }
                }
            }
        }
        return s.substring(maxStart, maxEnd + 1);
    }

    public static void main(String[] args) {
        String ccc = new LeetCode5().longestPalindrome("ccc");
        System.out.println(ccc);
    }
}
