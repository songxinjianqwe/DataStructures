package com.sinjinsong.leetcode.medium;

import com.sinjinsong.alogrithm.tree.TreeNode;

public class LeetCode124 {
    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumStartsWith(root);
        return ans;
    }

    private int maxPathSumStartsWith(TreeNode root) {
        // 以root为起点的最大路径和，注意不一定就是最终结果，最终结果有可能不从root开始
        if(root == null) {
            return 0;
        }
        // 左右孩子的路径和<0时就不走孩子了
        int left = Math.max(0, maxPathSumStartsWith(root.left));
        int right = Math.max(0, maxPathSumStartsWith(root.right));
        // 以自己的为起始节点的最大路径和的话，要么走左边，要么走右边，自己+左孩子 或者  自己+右孩子
        int mlOrmR = root.val + Math.max(left, right);
        // 自己、左孩子、右孩子都算进去
        int lmr = root.val + left + right;
        ans = Math.max(ans, Math.max(mlOrmR, lmr));
        return mlOrmR;
    }
}
