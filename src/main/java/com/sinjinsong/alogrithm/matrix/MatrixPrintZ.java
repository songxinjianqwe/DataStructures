package com.sinjinsong.alogrithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class MatrixPrintZ {

    /**
     * 给定一个矩阵matrix，按照“之”字形的方式打印这个矩阵。例如：
     * 要求额外的空间复杂度为O(1)
     * [[1,2,3],[4,5,6],[7,8,9],[10,11,12]],4,3
     * 返回：[1,2,3,6,5,4,7,8,9,12,11,10]
     *
     * @param matrix
     * @param rows
     * @param cols
     * @return
     */
    public static List<Integer> printZ(int[][] matrix, int rows, int cols) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || rows <= 0 || cols <= 0) {
            return result;
        }
        for (int i = 0; i < rows; i++) {
            // 偶数行，顺序打印
            if ((i & 1) == 0) {
                for (int j = 0; j < cols; j++) {
                    result.add(matrix[i][j]);
                }
            } else {
                for (int j = cols - 1; j >= 0; j--) {
                    result.add(matrix[i][j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {6, 7, 8, 9, 10},
                {11, 12, 13, 14, 15}
        };
        System.out.println(printZ(matrix, 3, 5));
    }
}
