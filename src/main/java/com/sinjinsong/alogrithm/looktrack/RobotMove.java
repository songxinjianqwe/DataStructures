package com.sinjinsong.alogrithm.looktrack;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class RobotMove {
    private static int count = 0;
    private static final int[][] DIRECTION = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private static int[][] DIGIT_SUM;

    /**
     * 地上有一个 m 行和 n 列的方格。一个机器人从坐标 0, 0 的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于 k 的格子。
     * 例如，当 k 为 18 时，机器人能够进入方格（35, 37），因为 3+5+3+7 = 18。
     * 但是，它不能进入方格（35, 38），因为 3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     *
     * @param threshold
     * @param rows
     * @param cols
     * @return
     */
    public static int movingCount(int threshold, int rows, int cols) {
        count = 0;
        if (threshold <= 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        initDigitSum(rows, cols);
        boolean[][] visited = new boolean[rows][cols];
        movingCount(threshold, rows, cols, 0, 0, visited);
        return count;
    }

    private static void movingCount(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (DIGIT_SUM[row][col] > threshold) {
            return;
        }
        count++;
        for (int i = 0; i < DIRECTION.length; i++) {
            movingCount(threshold, rows, cols, row + DIRECTION[i][0], col + DIRECTION[i][1], visited);
        }
    }

    private static void initDigitSum(int rows, int cols) {
        int[] digitSumOne = new int[Math.max(rows, cols)];
        for (int i = 0; i < digitSumOne.length; i++) {
            int n = i;
            while (n > 0) {
                digitSumOne[i] += n % 10;
                n /= 10;
            }
        }
        System.out.println(Arrays.toString(digitSumOne));
        DIGIT_SUM = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                DIGIT_SUM[i][j] = digitSumOne[i] + digitSumOne[j];
            }
        }
        System.out.println(Arrays.deepToString(DIGIT_SUM));
    }

    public static void main(String[] args) {
        System.out.println(movingCount(5,10,10));
    }
}
