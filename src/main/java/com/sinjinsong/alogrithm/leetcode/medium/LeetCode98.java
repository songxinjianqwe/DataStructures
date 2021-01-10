package com.sinjinsong.alogrithm.leetcode.medium;

import com.sinjinsong.alogrithm.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode98 {
    /**
     * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
     * <p>
     * 假设一个二叉搜索树具有如下特征：
     * <p>
     * 节点的左子树只包含小于当前节点的数。
     * 节点的右子树只包含大于当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     * 示例 1:
     * <p>
     * 输入:
     * 2
     * / \
     * 1   3
     * 输出: true
     * 示例 2:
     * <p>
     * 输入:
     * 5
     * / \
     * 1   4
     *      / \
     *     3   6
     * 输出: false
     * 解释: 输入为: [5,1,4,null,null,3,6]。
     *      根节点的值为 5 ，但是其右子节点值为 4 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 5 4 6 null null 3 7
     *       5
     *     4   6
     *        3  7
     * 此时就会转为    5  between 负无穷 and 正无穷
     *               4  between 负无穷 and 5
     *               6  between 5 and 正无穷
     *               3 between 5 and 6【不满足】
     *               7 between 6 and 正无穷
     * 总结下来就是 左孩子的值必须在 传递下来的最小值 和 父节点的值之间；右孩子的值必须在 父节点的值 和 传递下来的最大值之间
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValid(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val >= min) {
            return false;
        }
        if (root.val <= max) {
            return false;
        }
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }
}
