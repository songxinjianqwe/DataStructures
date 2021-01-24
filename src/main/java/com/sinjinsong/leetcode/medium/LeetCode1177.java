package com.sinjinsong.leetcode.medium;

import java.util.*;

public class LeetCode1177 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            if(query[0] == query[1]) {
                ans.add(true);
                continue;
            }
            String substr = s.substring(query[0], query[1] + 1);
            // 将子串尽可能调整为回文串
            // 如果一个字符的出现次数为偶数，那么可以忽略这个字符
            // 如果一个字符的出现次数为奇数，那么就算是一个待调整的
            // 总体是奇数个，那么需要调整的字符个数为 (所有出现奇数次数的字符的个数 - 1) / 2
            // 总体是偶数个，那么需要调整的字符个数为 所有出现奇数次数的字符的个数/ 2
            int[] map = new int[26];
            for (char c : substr.toCharArray()) {
                map[c - 'a']++;
            }
            int oddTimesCharCount = 0;
            for (int i = 0; i < 26; i++) {
                if(map[i] % 2 == 1) {
                    oddTimesCharCount++;
                }
            }
            int adjustCount;
            if(substr.length() % 2 == 0) {
                adjustCount = oddTimesCharCount / 2;
            } else {
                adjustCount = (oddTimesCharCount - 1) / 2;
            }
            ans.add(adjustCount <= query[2]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] ints = {{0,7,0},{1,4,4},{6,12,3},{10,11,1},{5,7,1},{0,10,6}};
        System.out.println(new LeetCode1177().canMakePaliQueries("orsrurgtinans", ints));
    }
}
