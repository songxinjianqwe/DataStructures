package com.sinjinsong.alogrithm.leetcode.dp;

import java.util.*;

public class LeetCode139 {
    /**
     * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
     * <p>
     * 说明：
     * <p>
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     * <p>
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     * <p>
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     * <p>
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        // 以dp[i]: 截止到i的s的子串，是否能与wordDict相匹配
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;// 空串
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // dp[i]=dp[j] && s[j..i−1] in wordDict
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }

//    public boolean wordBreak(String s, List<String> wordDict) {
//        Map<String, Integer> map = new HashMap<>();
//        for (String word : wordDict) {
//            map.put(word, 1);
//        }
//        return wordBreak(s, map);
//    }
//
//    private boolean wordBreak(String s, Map<String, Integer> wordDict) {
//        if(s.isEmpty() && wordDict.values().stream().allMatch(val -> val == 1)) {
//            return true;
//        }
//        List<String> wordsMatched = new ArrayList<>();
//        for (String word : wordDict.keySet()) {
//            if(s.startsWith(word)) {
//                wordsMatched.add(word);
//            }
//        }
//        if(wordsMatched.isEmpty()) {
//            return false;
//        }
//        for (String wordMatched : wordsMatched) {
//            Map<String, Integer> newMap = new HashMap<>(wordDict);
//            newMap.put(wordMatched, 1);
//            // 原字符串里可以有 wordDict里的某个元素的多次匹配，所以这里不应该去掉已经匹配好的
//            if(wordBreak(s.substring(s.indexOf(wordMatched) + wordMatched.length()), newMap)){
//                return true;
//            }
//        }
//        return false;
//    }

    public static void main(String[] args) {
        boolean b = new LeetCode139().wordBreak("leetcode", Arrays.asList("leet", "code"));
    }
}
