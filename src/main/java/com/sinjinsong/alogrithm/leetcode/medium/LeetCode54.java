package com.sinjinsong.alogrithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class LeetCode54 {
    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/spiral-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        int up = 0, down = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while(true) {
            // 上
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            if(++up > down) {
                break;
            }
            // 右
            for (int i = up; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if(--right < left) {
                break;
            }
            // 下
            for (int i = right; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            if(--down < up) {
                break;
            }
            // 左
            for (int i = down; i >= up ; i--) {
                ans.add(matrix[i][left]);
            }
            if(++left > right) {
                break;
            }
        }
        return ans;
    }
}
