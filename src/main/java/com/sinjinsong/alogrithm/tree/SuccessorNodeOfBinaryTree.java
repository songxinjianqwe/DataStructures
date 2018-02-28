package com.sinjinsong.alogrithm.tree;

/**
 * @author sinjinsong
 * @date 2018/2/24
 */
public class SuccessorNodeOfBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    /**
     * 时间复杂度为O(l)，L为node与其后继节点之间的距离，空间复杂度为O(1)
     * 1）如果node有右子树，那么后继节点是其右子树的最左节点
     * 2）如果noide没有右子树，那么看node是不是其父节点的左孩子，如果是，那么后继节点是node的父节点
     * 3）如果是父节点的右孩子，就向上寻找node的后继节点。设置当前节点为curr，父节点为parent
     * 判断curr == parent.left，则parent为curr的后继，否则继续向上移动
     * 4）如果parent为null，那么说明node没有后继节点
     *
     * @param curr
     * @return
     */
    public static TreeNode successor(TreeNode curr) {
        if (curr == null) {
            return null;
        }
        //1)
        if (curr.right != null) {
            curr = curr.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        } else {
            //2)
            TreeNode parent = curr.parent;
            while (parent != null && parent.left != curr) {
                curr = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.parent = root;
        root.left.left = new TreeNode(1);
        root.left.left.parent = root.left;
        root.left.right = new TreeNode(4);
        root.left.right.parent = root.left;
        
        System.out.println(successor(root));
        System.out.println(successor(root.left));
        System.out.println(successor(root.left.left));
        System.out.println(successor(root.left.right));
        System.out.println(successor(root.right));
    }

}
