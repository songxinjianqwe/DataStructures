package com.sinjinsong.leetcode.medium;

public class LeetCode79 {
    /**
     * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
     *
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     *
     *  
     *
     * 示例:
     *
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * 给定 word = "ABCCED", 返回 true
     * 给定 word = "SEE", 返回 true
     * 给定 word = "ABCB", 返回 false
     *  
     *
     * 提示：
     *
     * board 和 word 中只包含大写和小写英文字母。
     * 1 <= board.length <= 200
     * 1 <= board[i].length <= 200
     * 1 <= word.length <= 10^3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-search
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0) && !used[i][j]) {
                    used[i][j] = true;
                    if (dfs(board, m, n, i, j, word, 1, used)) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int m, int n, int lastRow, int lastCol, String word, int index, boolean[][] used) {
        if (index == word.length()) {
            return true;
        }
        if (lastRow >= 1 && board[lastRow - 1][lastCol] == word.charAt(index) && !used[lastRow - 1][lastCol]) {
            used[lastRow - 1][lastCol] = true;
            if (dfs(board, m, n, lastRow - 1, lastCol, word, index + 1, used)) {
                return true;
            }
            used[lastRow - 1][lastCol] = false;
        }
        if (lastRow < m - 1 && board[lastRow + 1][lastCol] == word.charAt(index) && !used[lastRow + 1][lastCol]) {
            used[lastRow + 1][lastCol] = true;
            if (dfs(board, m, n, lastRow + 1, lastCol, word, index + 1, used)) {
                return true;
            }
            used[lastRow + 1][lastCol] = false;
        }

        if (lastCol >= 1 && board[lastRow][lastCol - 1] == word.charAt(index) && !used[lastRow][lastCol - 1]) {
            used[lastRow][lastCol - 1] = true;
            if (dfs(board, m, n, lastRow, lastCol - 1, word, index + 1, used)) {
                return true;
            }
            used[lastRow][lastCol - 1] = false;
        }

        if (lastCol < n - 1 && board[lastRow][lastCol + 1] == word.charAt(index) && !used[lastRow][lastCol + 1]) {
            used[lastRow][lastCol + 1] = true;
            if (dfs(board, m, n, lastRow, lastCol + 1, word, index + 1, used)) {
                return true;
            }
            used[lastRow][lastCol + 1] = false;
        }
        return false;
    }
}
