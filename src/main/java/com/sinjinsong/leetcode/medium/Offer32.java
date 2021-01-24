package com.sinjinsong.leetcode.medium;

import com.sinjinsong.leetcode.TreeNode;

import java.util.*;

public class Offer32 {
    /**
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
     * <p>
     *  
     * <p>
     * 例如:
     * 给定二叉树: [3,9,20,null,null,15,7],
     * <p>
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * 返回其层次遍历结果：
     * <p>
     * [
     * [3],
     * [20,9],
     * [15,7]
     * ]
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderWithZ(TreeNode root) {
        Deque<TreeNode> q = new ArrayDeque<>();
        if (root == null) {
            return new ArrayList<>();
        }
        q.add(root);
        List<List<Integer>> ans = new ArrayList<>();
        int depth = 0;
        TreeNode curr;
        while (!q.isEmpty()) {
            // depth =0 是根
            int levelSize = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                if (depth % 2 == 0) {
                    // 偶数层是从左往右遍历，所以先从队列头取
                    curr = q.pollFirst();
                    level.add(curr.val);
                    // 因为奇数层是从右往左遍历，所以放元素的时候，后面要先输出的往队尾放（先访问右孩子，所以先放左孩子，最后放右孩子）
                    if (curr.left != null) {
                        q.addLast(curr.left);
                    }
                    if (curr.right != null) {
                        q.addLast(curr.right);
                    }
                } else {
                    // 奇数层从右往左遍历，所以先从最左边（队列头）拿
                    curr = q.pollLast();
                    level.add(curr.val);
                    // 偶数层是从左往右遍历，所以先输出的往队头放（因为左孩子先输出，可以先放右孩子，最后放左孩子）
                    if (curr.right != null) {
                        q.addFirst(curr.right);
                    }
                    if (curr.left != null) {
                        q.addFirst(curr.left);
                    }
                }
            }
            depth++;
            ans.add(level);
        }
        return ans;
    }
}
