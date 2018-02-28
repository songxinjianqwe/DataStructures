package com.sinjinsong.alogrithm.looktrack;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class MatrixPath {
    private static final int[][] DIRECTION = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    /**
     * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
     * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
     * 如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
     * 例如 a b c e s f c s a d e e
     * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
     * 因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        char[][] m = new char[rows][cols];
        for (int i = 0,idx = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = matrix[idx++];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(backtracking(m,rows,cols,str,new boolean[rows][cols],0,i,j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean backtracking(char[][] matrix, int rows, int cols, char[] str, boolean[][] isUsed, int strIndex, int row, int col) {
        if(strIndex == str.length ) {
            return true;
        }
        if(row < 0 || row >= rows || col <0 || col >= cols) {
            return false;
        }
        if(matrix[row][col] != str[strIndex]){
            return false;
        }
        if(isUsed[row][col]) {
            return false;
        }
        isUsed[row][col] = true;
        for (int i = 0; i < DIRECTION.length; i++) {
            if(backtracking(matrix,rows,cols,str,isUsed,strIndex + 1,row + DIRECTION[i][0],col + DIRECTION[i][1])) {
                return true;
            }
        }
        // 回溯
        isUsed[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        char[] matrix = "abtgcfcsjdeh".toCharArray();
        char[] str = "bfce".toCharArray();
        System.out.println(hasPath(matrix,3,4,str));
    }
}
