package com.sinjinsong.alogrithm.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class MatrixPrintClockWise {
    /**
     * 一圈一圈地打印，并不能根据行列求出圈数，但可以根据level * 2 < rows && level * 2 < cols来判断
     *
     * @param matrix
     * @param rows
     * @param cols
     * @return
     */
    public static List<Integer> printClockWise(int[][] matrix, int rows, int cols) {
        List<Integer> result = new ArrayList<>();
        int level = 0;
        while (level * 2 < rows && level * 2 < cols) {
            printACircle(matrix, rows, cols, level, result);
            level++;
        }
        return result;
    }

    private static void printACircle(int[][] matrix, int rows, int cols, int level, List<Integer> result) {
        // 上
        for (int i = level; i < cols - level; i++) {
            result.add(matrix[level][i]);
        }
        // 只有一行、只有一列的这种情况，可能下面这些方向的不会打印
        int endX = cols - level - 1;
        int endY = rows - level - 1;
        // 右
        if (level < endY) {
            for (int i = level + 1; i < rows - level; i++) {
                result.add(matrix[i][cols - level - 1]);
            }
        }
        // 下
        if (level < endX && level < endY) {
            for (int i = cols - level - 2; i >= level; i--) {
                result.add(matrix[rows - level - 1][i]);
            }
        }
        if (level < endX && level < endY - 1) {
            //左
            for (int i = rows - level - 2; i >= 1 + level; i--) {
                result.add(matrix[i][level]);
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
//                {1,2,3,4,5},
//                {10,9,8,7,6},
//                {11,12,13,14,15},
//                {20,19,18,17,16},
//                {21,22,23,24,25}
                {1},
                {2},
                {3},
                {4},
                {5}
        };
        System.out.println(printClockWise(matrix, 5, 1));
    }

}
