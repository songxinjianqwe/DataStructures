package com.sinjinsong.alogrithm.tree;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class OrderedArray2BST {
    /**
     * 有序数组重建二叉搜索树
     * 时间复杂度为O(n)
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static TreeNode fromArray(int[] arr, int low, int high) {
        if (low > high) {
            return null;
        }
        if (low == high) {
            return new TreeNode(arr[low]);
        }
        int mid = (low + high) / 2;
        TreeNode root = new TreeNode(arr[mid]);
        root.left = fromArray(arr, low, mid - 1);
        root.right = fromArray(arr, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode head = fromArray(arr, 0, arr.length - 1);
        TreeNode.preOrder(head);
    }
}
