package com.sinjinsong.alogrithm.leetcode.easy;

public class LeetCode14 {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     *
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     *  
     *
     * 提示：
     *
     * 0 <= strs.length <= 200
     * 0 <= strs[i].length <= 200
     * strs[i] 仅由小写英文字母组成
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        int minLen = Integer.MAX_VALUE;
        if(strs.length == 0) {
            return "";
        }
        if(strs.length == 1) {
            return strs[0];
        }
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        if(minLen == 0) {
            return "";
        }
        int i = 0;
        int ans = 0;
        for (; i < minLen; i++) {
            Character sameChar = null;
            for (String str : strs) {
                if(sameChar == null) {
                    sameChar = str.charAt(i);
                } else if(sameChar != str.charAt(i)){
                    return strs[0].substring(0, ans);
                }
            }
            // 一轮结束后才算能是有一个公共字符
            ans++;
        }
        return strs[0].substring(0, ans);
    }
}
