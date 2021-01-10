package com.sinjinsong.alogrithm.leetcode.medium;

import com.sinjinsong.alogrithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LeetCode94 {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> ans = new ArrayList<>();
//        inorder(root, ans);
//        return ans;
//    }
//
//    private void inorder(TreeNode curr, List<Integer> ans) {
//        if(curr == null) {
//            return;
//        }
//        inorder(curr.left, ans);
//        ans.add(curr.val);
//        inorder(curr.right, ans);
//    }

    public List<Integer> inorderTraversal(TreeNode root) {
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
        return ans;
    }
}
