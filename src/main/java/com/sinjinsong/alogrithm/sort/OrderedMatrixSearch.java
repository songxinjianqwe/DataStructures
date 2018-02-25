package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/20
 */
public class OrderedMatrixSearch {
    /**
     * 矩阵行列有序，查找指定元素是否存在
     * 从矩阵右上角开始找，i是行指针，j是列指针
     * 如果大于target，那么j--
     * 如果小于target，那么i++，直至找到或者越界
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean search(int[][] matrix, int target) {
        int i = 0;
        int j = matrix[0].length - 1;
        while (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length) {
            if (matrix[i][j] > target) {
                j--;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 5},
                {2, 3, 4, 7},
                {4, 4, 4, 8},
                {5, 7, 7, 9}
        };
        System.out.println(search(matrix, 3));
    }
}
