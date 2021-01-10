package com.sinjinsong.alogrithm.leetcode.easy;

import com.sinjinsong.alogrithm.leetcode.TreeNode;

public class LeetCode110 {
    /**
     * 定一个二叉树，判断它是否是高度平衡的二叉树。
     *
     * 本题中，一棵高度平衡二叉树定义为：
     *
     * 一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1 。
     *
     *  
     *
     * 示例 1：
     *
     *
     * 输入：root = [3,9,20,null,null,15,7]
     * 输出：true
     * 示例 2：
     *
     *
     * 输入：root = [1,2,2,3,3,null,null,4,4]
     * 输出：false
     * 示例 3：
     *
     * 输入：root = []
     * 输出：true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/balanced-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        if(Math.abs(depth(root.left) -  depth(root.right)) >1 ){
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode node) {
        if(node == null) {
            return 0;
        }
        return Math.max(depth(node.left), depth(node.right)) + 1;
    }

}
