package com.sinjinsong.leetcode.medium;


import java.util.*;

public class LeetCode402 {
//    public String removeKdigits(String num, int k) {
//        List<String> ans = new ArrayList<>();
//        permutation(new StringBuilder(num),  k, ans);
//        String val = ans.get(0);
//        for (int i = 0; i < val.length(); i++) {
//            if(val.charAt(i) != '0') {
//                return val.substring(i);
//            }
//        }
//        return "0";
//    }
//
//    private void permutation(StringBuilder curr,  int k, List<String> res) {
//        if (0 == k) {
//            String val = curr.toString();
//            if (res.isEmpty()) {
//                res.add(val);
//            } else {
//                if (res.get(0).compareTo(val) > 0) {
//                    res.remove(0);
//                    res.add(val);
//                }
//            }
//        } else {
//            for (int i = 0; i < curr.length(); i++) {
//                char c = curr.charAt(i);
//                curr.deleteCharAt(i);
//                permutation(curr,  k - 1, res);
//                curr.insert(i, c);
//            }
//        }
//    }

    public String removeKdigits(String num, int k) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : num.toCharArray()) {
            if (stack.isEmpty()) {
                stack.addLast(c);
            } else {
                // 单调递增栈，栈底最小，栈顶最大，删除k个不满足单调递增的
                while (!stack.isEmpty() && stack.peekLast() > c && k > 0) {
                    stack.removeLast();
                    k--;
                }
                stack.addLast(c);
            }
        }
        // 可能此时已经是单调递增的时候，但是没有删够，此时有优先删除最后的（也就是最大的）
        while (k-- > 0) {
            stack.removeLast();
        }
        StringBuilder ans = new StringBuilder();
        boolean leadingZero = true;
        while (!stack.isEmpty()) {
            // 这时候反着输出，就是正序的了
            char c = stack.removeFirst();
            if (leadingZero && c == '0') {
                continue;
            }
            leadingZero = false;
            ans.append(c);
        }
        String val = ans.toString();
        return val.isEmpty() ? "0" : val;
    }

    public static void main(String[] args) {
        String res = new LeetCode402().removeKdigits("10200", 1);
        System.out.println(res);
    }
}
