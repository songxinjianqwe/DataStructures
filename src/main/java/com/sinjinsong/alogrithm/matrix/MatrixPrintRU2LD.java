package com.sinjinsong.alogrithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class MatrixPrintRU2LD {
    /**
     * 从右上角到左下角打印矩阵
     * | 1  2  3  4 |               4
     * | 5  6  7  8 |     ----->    3 8
     * | 9 10  11 12|               2 7 12
     * |13 14  15 16|               1 6 11 16
     *                              5 10 15
     *                              9 14
     *                              13
     *
     * 总结出规律：
     * row和col从某个点开始，输出matrix[row][col]，然后row++,col++，直至越界
     * 开始点的取法：从右上角，到左上角，到左下角！
     * 先减colStart，减为0后，再加rowStart
     * @param matrix
     * @param n
     * @return
     */
    public static List<Integer> printRU2LD(int[][] matrix, int n) {
        List<Integer> result = new ArrayList<>();
        if(matrix == null || n <= 0) {
            return result;
        }
        int rowStart = 0;
        int colStart = n - 1;
        while (rowStart < n) {
            for (int row = rowStart, col = colStart; row < n && col < n; row++, col++) {
                result.add(matrix[row][col]);
            }
            if (colStart > 0) {
                colStart--;
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
        System.out.println(printRU2LD(matrix, 4));
    }
}
