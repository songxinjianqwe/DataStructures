package com.sinjinsong.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode17 {
    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     *
     *
     * 示例:
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()) {
            return new ArrayList<>();
        }
        Map<Character, String> map = new HashMap<>();
        map.put('1', "!@#");
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> candidates = new ArrayList<>();
        for (char c : digits.toCharArray()) {
            candidates.add(map.get(c));
        }
        List<String> ans = new ArrayList<>();

        int[] innerIndex = new int[candidates.size()];
        while(true) {
            ans.add(outputEntry(candidates, innerIndex));
            int i = candidates.size() - 1;
            // i指向待推进内部指针的元素
            while(i >= 0) {
                if(innerIndex[i] < candidates.get(i).length() - 1){
                    innerIndex[i]++;
                    // 调整完了以后，后续的元素的指针统一要归0
                    for (int k = i + 1; k < candidates.size(); k++) {
                        innerIndex[k] = 0;
                    }
                    break;
                } else {
                    i--;
                }
            }
            if(i < 0) {
                break;
            }
        }
        return ans;
    }

    private String outputEntry(List<String> candidates, int[] innerIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < candidates.size(); i++) {
            sb.append(candidates.get(i).charAt(innerIndex[i]));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> strings = new LeetCode17().letterCombinations("23");
        System.out.println(strings);
    }
}
