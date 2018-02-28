package com.sinjinsong.alogrithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class MatrixPrintLU2RD {
    /**
     * 从左上角到右下角打印矩阵
     * @param matrix
     * @param n
     * @return
     */
    public static List<Integer> printLU2RD(int[][] matrix, int n) {
        List<Integer> result = new ArrayList<>();
        if (matrix == null || n <= 0) {
            return result;
        }
        int rowStart = 0;
        int colStart = 0;
        while (rowStart < n) {
            for (int row = rowStart, col = colStart; row < n && col >= 0; row++, col--) {
                result.add(matrix[row][col]);
            }
            if (colStart < n - 1) {
                colStart++;
            } else {
                rowStart++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println(printLU2RD(matrix, 4));
    }
}
