package com.sinjinsong.alogrithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

public class LeetCode3 {
    /**
     * 最长无重复子串长度
     * 比如”abcb”，其最长无重复子串为”abc”，长度为3
     * 比如”abcd”，长度为4
     * "abcabcbb" 3
     * "pwwkew"   3
     * 滑动窗口，i和j是滑动窗口的两个指针，分别指向不重复子序列的开始和结束
     * 设置一个set保存不重复子序列中的出现的字符
     * 如果发现s[j]没有重复，那么更新ans，j指针向右移动；
     * 如果发现s[j]重复了，那么去掉set中的s[j]（此时一定是不重复子序列的第一个字符），然后i指针向右移动
     * （以i开头的子序列已经到头了）
     * 时间复杂度为O(n)，空间复杂度为O(n)
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        if (s.length() == 0) {
            return 0;
        }
        Set<Character> currentSubstring = new HashSet<>();
        int ans = 0;
        int n = s.length();
        while (i < n && j < n) {
            if (currentSubstring.contains(s.charAt(j))) {
                currentSubstring.remove(s.charAt(i));
                i++;
            } else {
                currentSubstring.add(s.charAt(j));
                ans = Math.max(ans, j - i + 1);
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode3().lengthOfLongestSubstring("pwwkew"));
    }
}
