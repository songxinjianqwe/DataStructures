package com.sinjinsong.alogrithm.leetcode.easy;

import com.sinjinsong.alogrithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode54 {
    public int kthLargest(TreeNode root, int k) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if(!stack.isEmpty()) {
                curr = stack.pop();
                ans.add(curr.val);
                curr = curr.right;
            }
        }
        return ans.get(ans.size() - k);
    }
}
