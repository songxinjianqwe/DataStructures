package com.sinjinsong.alogrithm.tree;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void preOrder(TreeNode curr) {
        if (curr != null) {
            System.out.print(curr.val + "  ");
            preOrder(curr.left);
            preOrder(curr.right);
        }else{
            System.out.print("#  ");
        }
    }
}   
