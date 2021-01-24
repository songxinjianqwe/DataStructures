package com.sinjinsong.leetcode.easy;

import com.sinjinsong.leetcode.TreeNode;

public class LeetCode527 {
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        String sPreOrder = preOrder(s);
//        String tPreOrder = preOrder(t);
//        return sPreOrder.contains(tPreOrder);
//    }
//
//    private String preOrder(TreeNode s) {
//        StringBuilder sb = new StringBuilder();
//        preOrder(s, sb);
//        return sb.toString();
//    }
//
//    private void preOrder(TreeNode s, StringBuilder sb) {
//        if(s == null) {
//            sb.append("#");
//        } else {
//            sb.append("@");
//            sb.append(s.val);
//            sb.append("!");
//            preOrder(s.left, sb);
//            preOrder(s.right, sb);
//        }
//    }

    /**
     * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看做它自身的一棵子树。
     *
     * 示例 1:
     * 给定的树 s:
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     * 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
     *
     * 示例 2:
     * 给定的树 s：
     *
     *      3
     *     / \
     *    4   5
     *   / \
     *  1   2
     *     /
     *    0
     * 给定的树 t：
     *
     *    4
     *   / \
     *  1   2
     * 返回 false。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subtree-of-another-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        } else if (s == null) {
            return false;
        }
        if (isSame(s, t)) {
            return true;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s != null && t != null) {
            if (s.val != t.val) {
                return false;
            }
            return isSame(s.left, t.left) && isSame(s.right, t.right);
        }
        return false;
    }
}
