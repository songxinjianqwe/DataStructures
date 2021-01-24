package com.sinjinsong.leetcode.medium;

import com.sinjinsong.leetcode.TreeNode;

public class Offer07 {
    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     *  
     *
     * 例如，给出
     *
     * 前序遍历 preorder = [3,9,20,15,7]
     * 中序遍历 inorder = [9,3,15,20,7]
     * 返回如下的二叉树：
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (inStart > inEnd) {
            return null;
        }
        int rootVal = preorder[preStart];
        int inorderRootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                inorderRootIndex = i;
                break;
            }
        }
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(preorder,
                preStart + 1,
                // preStart + 1 + inorderRootIndex - inStart - 1
                preStart + inorderRootIndex - inStart,
                inorder,
                inStart,
                inorderRootIndex - 1);
        root.right = buildTree(preorder,
                // preStart + 1 + inorderRootIndex - inStart - 1 + 1
                preStart + inorderRootIndex - inStart + 1,
                preEnd,
                inorder,
                inorderRootIndex + 1,
                inEnd);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 3};
        int[] inorder = {3, 2, 1};
        TreeNode treeNode = new Offer07().buildTree(preorder, inorder);
    }
}
