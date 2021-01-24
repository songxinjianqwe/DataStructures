package com.sinjinsong.leetcode.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode22 {

//    public List<String> generateParenthesis(int n) {
//        char[] curr = new char[n * 2];
//        List<String> ans = new ArrayList<>();
//        dfs(n * 2, 0, curr, ans);
//        return ans;
//    }
//
//    private void dfs(int n, int index, char[] curr, List<String> ans) {
//        if (index == n) {
//            int count = 0;
//            boolean valid = true;
//            for (char c : curr) {
//                if (c == '(') {
//                    count++;
//                } else {
//                    count--;
//                    if (count < 0) {
//                        valid = false;
//                        break;
//                    }
//                }
//            }
//            if (count != 0) {
//                valid = false;
//            }
//            if (valid) {
//                ans.add(new String(curr));
//            }
//        } else {
//            curr[index] = '(';
//            dfs(n, index + 1, curr, ans);
//            curr[index] = ')';
//            dfs(n, index + 1, curr, ans);
//        }
//    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, 0, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(int n, int index, int open, int close, StringBuilder curr, List<String> ans) {
        if(index == 2 * n ) {
            ans.add(curr.toString());
        }else {
            if(open < n) {
                curr.append('(');
                dfs(n, index + 1, open + 1, close, curr, ans);
                curr.deleteCharAt(index);
            }
            if(close < open) {
                curr.append(')');
                dfs(n, index + 1, open, close + 1, curr, ans);
                curr.deleteCharAt(index);
            }
        }
    }

    public static void main(String[] args) {
        List<String> strings = new LeetCode22().generateParenthesis(3);
        System.out.println(strings.size());
        System.out.println(strings);
    }
}
