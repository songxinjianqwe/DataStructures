package com.sinjinsong.leetcode.medium;

import com.sinjinsong.leetcode.TreeNode;

public class LeetCode236 {
    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     *
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     *
     * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
     *
     *
     *
     *  
     *
     * 示例 1:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * 输出: 3
     * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
     * 示例 2:
     *
     * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * 输出: 5
     * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
     *  
     *
     * 说明:
     *
     * 所有节点的值都是唯一的。
     * p、q 为不同节点且均存在于给定的二叉树中。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 节点的最近公共父节点也是自己
        if(root == null || root == p || root == q) {
            return root;
        }
        // 从左子树找LCA
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 从右子树找LCA
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 都不为空说明p和q分别分布在左右子树上，此时LCA就是根节点
        if(left != null && right != null) {
            return root;
            // 左子树LCA不为空则说明p和q都在左子树上，此时left即为最近的父节点（层层返回）
            //  2
            // 1  3
            // root: 2, p: 1, q:3
            // 此时LCA(1,1,3) -> 1, LCA(3,1,3) -> 3
            // 因为都不为空，说明在两个子树上，那么LCA为根

            //   3
            //  1 2
            // 4 5
            // root:3, p: 4, q: 5
            // LCA(3,4,5) -> LCA(1,4,5)=1, LCA(2,4,5)->null -> 返回1

            //     3
            //   1   2
            //  4 5
            // 6 7
            // root:3, p: 6, q: 7
            // LCA(3,6,7) -> LCA(1,6,7), LCA(2,6,7)=null
            // LCA(1,6,7) -> LCA(4,6,7)=4,LCA(5,6,7)=null -> 返回4，最终也是返回4
        } else if(left != null) {
            return left;
            // 同理
        } else {
            return right;
        }
    }
}
